package edu.gdbtu.lzq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import edu.gdbtu.lzq.mapper.QuestionMapper;
import edu.gdbtu.lzq.model.*;
import edu.gdbtu.lzq.model.system.SysUser;
import edu.gdbtu.lzq.service.MyQuestionLogService;
import edu.gdbtu.lzq.service.QuestionService;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.system.utils.UserUtil;
import edu.gdbtu.lzq.vo.ExamVo;
import edu.gdbtu.lzq.vo.QuestionOptionQueryVo;
import edu.gdbtu.lzq.vo.QuestionQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 Service实现类
 * @date 2023-09-21 00:54:13
 */
@Transactional
@Service
public class QuestionServiceImpl extends ServiceImpl
        <QuestionMapper, Question> implements QuestionService {
    @Autowired
    private QuestionMapper QuestionMapper;
    @Autowired
    private edu.gdbtu.lzq.service.ExamQuestionRelationService ExamQuestionRelationService;
    @Autowired
    private edu.gdbtu.lzq.service.QuestionOptionService QuestionOptionService;
    @Autowired
    private edu.gdbtu.lzq.service.ExamResultService ExamResultService;
    @Autowired
    private edu.gdbtu.lzq.service.ExamService ExamService;
    @Autowired
    private MyQuestionLogService MyQuestionLogService;

    @Override
    public IPage<Question> selectPage(Page<Question> pageParam, QuestionQueryVo QuestionQueryVo) {
        //QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        //return QuestionMapper.selectPage(pageParam,queryWrapper);
        //只查询当前登录所属部门数据
        SysUser sysUser = UserUtil.getUserInfo();
        if ("1".equals(sysUser.getId())) {
            QuestionQueryVo.setCurDeptIds(null);
        } else {
            if (CollectionUtils.isEmpty(sysUser.getDeptIds())) {
                return null;
            }
            QuestionQueryVo.setCurDeptIds(sysUser.getDeptIds());
        }
        return QuestionMapper.selectPage(pageParam, QuestionQueryVo);
    }

    @Override
    public List<Question> queryList(QuestionQueryVo QuestionQueryVo) {
        //只查询当前登录所属部门数据
        SysUser sysUser = UserUtil.getUserInfo();
        if ("1".equals(sysUser.getId())) {
            QuestionQueryVo.setCurDeptIds(null);
        } else {
            if (CollectionUtils.isEmpty(sysUser.getDeptIds())) {
                return null;
            }
            QuestionQueryVo.setCurDeptIds(sysUser.getDeptIds());
        }
        List<Question> result = QuestionMapper.queryList(QuestionQueryVo);
        return result;
    }


    @Override
    public boolean save(Question Question) {
        int result = this.QuestionMapper.insert(Question);
        List<String> examIdList = Question.getExamIdList();
        if (examIdList != null && examIdList.size() > 0) {
            for (String examId : examIdList) {
                ExamQuestionRelation ExamQuestionRelation = new ExamQuestionRelation();
                ExamQuestionRelation.setQuestionId(Question.getId());
                ExamQuestionRelation.setExamId(examId);
                ExamQuestionRelationService.save(ExamQuestionRelation);
            }
        }
        String questionType = Question.getType();
        if (!"4004".equals(questionType)) { //如果不是填空题
            List<QuestionOption> optionList = Question.getOptionList();
            if (optionList != null && optionList.size() > 0) {
                for (QuestionOption vo : optionList) {
                    if (StringUtils.isEmpty(vo.getOptions()) || StringUtils.isEmpty(vo.getName()) || vo.getIsRight() == null) {
                        throw new LanfException(5240, "选项信息不能留空");
                    }
                    QuestionOption QuestionOption = new QuestionOption();
                    QuestionOption.setQuestionId(Question.getId());
                    QuestionOption.setOptions(vo.getOptions());
                    QuestionOption.setName(vo.getName());
                    QuestionOption.setIsRight(vo.getIsRight());
                    QuestionOptionService.save(QuestionOption);
                }
            }
        }
        return result > 0;
    }

    @Override
    public boolean updateById(Question Question) {
        int row = this.QuestionMapper.updateById(Question);
        if (row <= 0) {
            throw new LanfException(ResultCodeEnum.UPLOAD_ERROR);
        }
        QueryWrapper<ExamQuestionRelation> ExamQuestionRelationQueryWrapper = new QueryWrapper<ExamQuestionRelation>();
        ExamQuestionRelationQueryWrapper.eq("question_id", Question.getId());
        ExamQuestionRelationService.remove(ExamQuestionRelationQueryWrapper);
        List<String> examIdList = Question.getExamIdList();
        if (examIdList != null && examIdList.size() > 0) {
            for (String examId : examIdList) {
                ExamQuestionRelation ExamQuestionRelation = new ExamQuestionRelation();
                ExamQuestionRelation.setQuestionId(Question.getId());
                ExamQuestionRelation.setExamId(examId);
                ExamQuestionRelationService.save(ExamQuestionRelation);
            }
        }
        String questionType = Question.getType();
        if (!"4004".equals(questionType)) { //如果不是填空题
            QueryWrapper<QuestionOption> queryWrapper = new QueryWrapper<QuestionOption>();
            queryWrapper.eq("question_id", Question.getId());
            QuestionOptionService.remove(queryWrapper);
            List<QuestionOption> optionList = Question.getOptionList();
            if (optionList != null && optionList.size() > 0) {
                for (QuestionOption vo : optionList) {
                    if (StringUtils.isEmpty(vo.getOptions()) || StringUtils.isEmpty(vo.getName()) || vo.getIsRight() == null) {
                        throw new LanfException(5240, "选项信息不能留空");
                    }
                    QuestionOption QuestionOption = new QuestionOption();
                    QuestionOption.setQuestionId(Question.getId());
                    QuestionOption.setOptions(vo.getOptions());
                    QuestionOption.setName(vo.getName());
                    QuestionOption.setIsRight(vo.getIsRight());
                    QuestionOptionService.save(QuestionOption);
                }
            }
        }
        return row > 0;
    }

    @Override
    public Question geyId(String id) {
        Question Question = QuestionMapper.getById(id);
        Function<Object, String> f = (o -> o.toString());
        QueryWrapper<ExamQuestionRelation> ExamQuestionRelationQueryWrapper = new QueryWrapper<ExamQuestionRelation>();
        ExamQuestionRelationQueryWrapper.select("exam_id");
        ExamQuestionRelationQueryWrapper.eq("question_id", Question.getId());
        List<String> ExamQuestionRelationList = ExamQuestionRelationService.listObjs(ExamQuestionRelationQueryWrapper, f);
        Question.setExamIdList(ExamQuestionRelationList);

        QuestionOptionQueryVo QuestionOptionQueryVo = new QuestionOptionQueryVo();
        QuestionOptionQueryVo.setQuestionId(Question.getId());
        List<QuestionOption> optionList = QuestionOptionService.queryList(QuestionOptionQueryVo);
        Question.setOptionList(optionList);
        return Question;
    }

    @Override
    public List<Question> geyIds(List<String> ids) {
        List<Question> list = this.QuestionMapper.selectBatchIds(ids);
        return list;
    }

    @Override
    public List<Question> queryMyList(QuestionQueryVo queryVo) {
        //只查询当前登录所属部门数据
        SysUser sysUser = UserUtil.getUserInfo();
        if ("1".equals(sysUser.getId())) {
            queryVo.setCurDeptIds(null);
        } else {
            if (CollectionUtils.isEmpty(sysUser.getDeptIds())) {
                return null;
            }
            queryVo.setCurDeptIds(sysUser.getDeptIds());
        }
        List<Question> result = QuestionMapper.queryList(queryVo);
        if (result != null && result.size() > 0) {
            for (Question question : result) {
                QuestionOptionQueryVo qv = new QuestionOptionQueryVo();
                qv.setQuestionId(question.getId());
                List optionList = QuestionOptionService.queryList(qv);
                question.setOptionList(optionList);
            }
        }
        return result;
    }

    @Override
    public void saveExam(ExamVo ExamVo) {
        long currTime = System.currentTimeMillis();
        Exam Exam = this.ExamService.geyId(ExamVo.getExamId());
        String userId = UserUtil.getUserId();
        if ("5002".equals(Exam.getType())) { //正式考试才做限制
            long endTime = Exam.getEndTime().getTime();
            if (currTime + 30000 > endTime) {
                throw new LanfException(5240, "考试已结束，不能再考试");
            }
            QueryWrapper<ExamResult> queryWrapper = new QueryWrapper<ExamResult>();
            queryWrapper.select("id");
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("exam_id", ExamVo.getExamId());
            int ct = ExamResultService.getBaseMapper().selectCount(queryWrapper);
            if (ct > 0) {
                throw new LanfException(5240, "已提交考试，请不要重复提交");
            }
        }
        int sumScore = 0; //得分
        int sum = 0; //试卷总分
        List<Question> sigList = ExamVo.getSigList();
        List<Question> mulList = ExamVo.getMulList();
        List<Question> pdList = ExamVo.getPdList();
        List<Question> wdList = ExamVo.getWdList();
        List<MyQuestionLog> qLog = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sigList)) {
            for (Question question : sigList) {
                List<QuestionOption> optionList = question.getOptionList();
                String myCheck = question.getMyCheck();
                MyQuestionLog MyQuestionLog = new MyQuestionLog();
                MyQuestionLog.setExamId(ExamVo.getExamId());
                MyQuestionLog.setQuestionId(question.getId());
                MyQuestionLog.setMyOptions(myCheck);
                int score = question.getScore();
                sum += score;
                if (checkAnswer(optionList, myCheck, MyQuestionLog)) {
                    sumScore += score;
                    MyQuestionLog.setIsRight(1);
                } else {
                    MyQuestionLog.setIsRight(0);
                }
                qLog.add(MyQuestionLog);
            }
        }
        if (!CollectionUtils.isEmpty(mulList)) {
            for (Question question : mulList) {
                List<QuestionOption> optionList = question.getOptionList();
                List<String> myCheck = question.getMyCheckList();
                MyQuestionLog MyQuestionLog = new MyQuestionLog();
                MyQuestionLog.setExamId(ExamVo.getExamId());
                MyQuestionLog.setQuestionId(question.getId());
                MyQuestionLog.setMyOptions(String.join(",", myCheck));
                int score = question.getScore();
                sum += score;
                if (checkAnswer(optionList, myCheck, MyQuestionLog)) {
                    sumScore += score;
                    MyQuestionLog.setIsRight(1);
                } else {
                    MyQuestionLog.setIsRight(0);
                }
                qLog.add(MyQuestionLog);
            }
        }
        if (!CollectionUtils.isEmpty(pdList)) {
            for (Question question : pdList) {
                List<QuestionOption> optionList = question.getOptionList();
                String myCheck = question.getMyCheck();
                MyQuestionLog MyQuestionLog = new MyQuestionLog();
                MyQuestionLog.setExamId(ExamVo.getExamId());
                MyQuestionLog.setQuestionId(question.getId());
                MyQuestionLog.setMyOptions(myCheck);
                int score = question.getScore();
                sum += score;
                if (checkAnswer(optionList, myCheck, MyQuestionLog)) {
                    sumScore += score;
                    MyQuestionLog.setIsRight(1);
                } else {
                    MyQuestionLog.setIsRight(0);
                }
                qLog.add(MyQuestionLog);
            }
        }
        if (!CollectionUtils.isEmpty(wdList)) {
            for (Question question : wdList) {
                List<QuestionOption> optionList = question.getOptionList();
                String myAnswer = question.getMyAnswer();
                String answer = question.getAnswer();
                MyQuestionLog MyQuestionLog = new MyQuestionLog();
                MyQuestionLog.setExamId(ExamVo.getExamId());
                MyQuestionLog.setQuestionId(question.getId());
                MyQuestionLog.setMyAnswer(myAnswer);
                if (StringUtils.isEmpty(myAnswer) || StringUtils.isEmpty(answer)) {
                    break;
                }
                myAnswer = myAnswer.replaceAll("\\s", "");
                answer = answer.replaceAll("\\s", "");
                int score = question.getScore();
                sum += score;
                if (myAnswer.equals(answer)) {
                    sumScore += score;
                    MyQuestionLog.setIsRight(1);
                } else {
                    MyQuestionLog.setIsRight(0);
                }
                qLog.add(MyQuestionLog);
            }
        }
        ExamResult ExamResult = new ExamResult();
        ExamResult.setUserId(userId);
        ExamResult.setExamId(ExamVo.getExamId());
        ExamResult.setScore(sumScore);
        ExamResult.setSumScore(sum);

        String resultId = null;
        if (!StringUtils.isEmpty(ExamVo.getResultId())) {
            ExamResult ExamResult1 = this.ExamResultService.geyId(ExamVo.getResultId());
            if (ExamResult1 != null) {
                ExamResult1.setScore(ExamResult1.getScore() + ExamResult.getScore());
                this.ExamResultService.updateById(ExamResult1);
                resultId = ExamResult1.getId();
            }
        } else {
            this.ExamResultService.save(ExamResult);
            resultId = ExamResult.getId();
        }
        for (MyQuestionLog MyQuestionLog : qLog) {
            MyQuestionLog.setResultId(resultId);
        }
        this.MyQuestionLogService.saveBatch(qLog);
    }

    private boolean checkAnswer(List<QuestionOption> optionList, List<String> myCheck, MyQuestionLog MyQuestionLog) {
        List<String> rightList = new ArrayList<>();
        for (QuestionOption option : optionList) {
            if ("1".equals("" + option.getIsRight())) {
                rightList.add(option.getOptions());
            }
        }
        MyQuestionLog.setRightOptions(String.join(",", rightList));
        if (CollectionUtils.isEmpty(optionList) || CollectionUtils.isEmpty(myCheck)) {
            return false;
        }
        return this.compareLists(rightList, myCheck);
    }

    private boolean checkAnswer(List<QuestionOption> optionList, String myCheck, MyQuestionLog MyQuestionLog) {
        List<String> rightList = new ArrayList<>();
        for (QuestionOption option : optionList) {
            if ("1".equals("" + option.getIsRight())) {
                rightList.add(option.getOptions());
            }
        }
        MyQuestionLog.setRightOptions(String.join(",", rightList));
        if (CollectionUtils.isEmpty(optionList) || StringUtils.isEmpty(myCheck)) {
            return false;
        }
        return rightList.contains(myCheck);
    }

    private boolean compareLists(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        Set<String> set1 = new HashSet<>(list1);
        Set<String> set2 = new HashSet<>(list2);
        return set1.equals(set2);
    }

}
