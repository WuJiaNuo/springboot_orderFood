package edu.gdbtu.lzq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.model.Exam;
import edu.gdbtu.lzq.model.UserExamRelation;
import edu.gdbtu.lzq.vo.ExamQueryVo;
import edu.gdbtu.lzq.mapper.ExamMapper;
import edu.gdbtu.lzq.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.system.utils.UserUtil;
import org.springframework.util.CollectionUtils;
import edu.gdbtu.lzq.model.system.SysUser;
import edu.gdbtu.lzq.model.ExamQuestionRelation;

import java.util.function.Function;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试试卷表 Service实现类
 * @date 2023-09-21 00:57:32
 */
@Transactional
@Service
public class ExamServiceImpl extends ServiceImpl
        <ExamMapper, Exam> implements ExamService {
    @Autowired
    private ExamMapper ExamMapper;
    @Autowired
    private edu.gdbtu.lzq.service.ExamQuestionRelationService ExamQuestionRelationService;
    @Autowired
    private edu.gdbtu.lzq.service.UserExamRelationService UserExamRelationService;

    @Override
    public IPage<Exam> selectPage(Page<Exam> pageParam, ExamQueryVo ExamQueryVo) {
        //只查询当前登录所属部门数据
        SysUser sysUser = UserUtil.getUserInfo();
        if ("1".equals(sysUser.getId())) {
            ExamQueryVo.setCurDeptIds(null);
        } else {
            if (CollectionUtils.isEmpty(sysUser.getDeptIds())) {
                return null;
            }
            ExamQueryVo.setCurDeptIds(sysUser.getDeptIds());
        }
        return ExamMapper.selectPage(pageParam, ExamQueryVo);
    }

    @Override
    public List<Exam> queryList(ExamQueryVo ExamQueryVo) {
        //只查询当前登录所属部门数据
        SysUser sysUser = UserUtil.getUserInfo();
        if ("1".equals(sysUser.getId())) {
            ExamQueryVo.setCurDeptIds(null);
        } else {
            if (CollectionUtils.isEmpty(sysUser.getDeptIds())) {
                return null;
            }
            ExamQueryVo.setCurDeptIds(sysUser.getDeptIds());
        }
        List<Exam> result = ExamMapper.queryList(ExamQueryVo);
        return result;
    }


    @Override
    public boolean save(Exam Exam) {
        Date startTime = Exam.getStartTime();
        Exam.setEndTime(new Date(startTime.getTime() + Exam.getDuration() * 60 * 1000));
        int result = this.ExamMapper.insert(Exam);
        List<String> questionIdList = Exam.getQuestionIdList();
        if (questionIdList != null && questionIdList.size() > 0) {
            for (String questionId : questionIdList) {
                ExamQuestionRelation ExamQuestionRelation = new ExamQuestionRelation();
                ExamQuestionRelation.setExamId(Exam.getId());
                ExamQuestionRelation.setQuestionId(questionId);
                ExamQuestionRelationService.save(ExamQuestionRelation);
            }
        }
        List<String> userIdList = Exam.getUserIdList();
        if (userIdList != null && userIdList.size() > 0) {
            for (String userId : userIdList) {
                UserExamRelation UserExamRelation = new UserExamRelation();
                UserExamRelation.setExamId(Exam.getId());
                UserExamRelation.setUserId(userId);
                UserExamRelationService.save(UserExamRelation);
            }
        }
        return result > 0;
    }

    @Override
    public boolean updateById(Exam Exam) {
        Date startTime = Exam.getStartTime();
        Exam.setEndTime(new Date(startTime.getTime() + Exam.getDuration() * 60 * 1000));
        int row = this.ExamMapper.updateById(Exam);
        if (row <= 0) {
            throw new LanfException(ResultCodeEnum.UPLOAD_ERROR);
        }
        QueryWrapper<ExamQuestionRelation> ExamQuestionRelationQueryWrapper = new QueryWrapper<ExamQuestionRelation>();
        ExamQuestionRelationQueryWrapper.eq("exam_id", Exam.getId());
        ExamQuestionRelationService.remove(ExamQuestionRelationQueryWrapper);
        List<String> questionIdList = Exam.getQuestionIdList();
        if (questionIdList != null && questionIdList.size() > 0) {
            for (String questionId : questionIdList) {
                ExamQuestionRelation ExamQuestionRelation = new ExamQuestionRelation();
                ExamQuestionRelation.setExamId(Exam.getId());
                ExamQuestionRelation.setQuestionId(questionId);
                ExamQuestionRelationService.save(ExamQuestionRelation);
            }
        }
        QueryWrapper<UserExamRelation> UserExamRelationQueryWrapper = new QueryWrapper<UserExamRelation>();
        UserExamRelationQueryWrapper.eq("exam_id", Exam.getId());
        UserExamRelationService.remove(UserExamRelationQueryWrapper);
        List<String> userIdList = Exam.getUserIdList();
        if (userIdList != null && userIdList.size() > 0) {
            for (String userId : userIdList) {
                UserExamRelation UserExamRelation = new UserExamRelation();
                UserExamRelation.setExamId(Exam.getId());
                UserExamRelation.setUserId(userId);
                UserExamRelationService.save(UserExamRelation);
            }
        }
        return row > 0;
    }

    @Override
    public Exam geyId(String id) {
        Exam Exam = ExamMapper.getById(id);
        Function<Object, String> f = (o -> o.toString());
        QueryWrapper<ExamQuestionRelation> ExamQuestionRelationQueryWrapper = new QueryWrapper<ExamQuestionRelation>();
        ExamQuestionRelationQueryWrapper.select("question_id");
        ExamQuestionRelationQueryWrapper.eq("exam_id", Exam.getId());
        List<String> ExamQuestionRelationList = ExamQuestionRelationService.listObjs(ExamQuestionRelationQueryWrapper, f);
        Exam.setQuestionIdList(ExamQuestionRelationList);

        QueryWrapper<UserExamRelation> tue = new QueryWrapper<UserExamRelation>();
        tue.select("user_id");
        ExamQuestionRelationQueryWrapper.eq("exam_id", Exam.getId());
        List<String> tueList = UserExamRelationService.listObjs(tue, f);
        Exam.setUserIdList(tueList);
        return Exam;
    }

    @Override
    public List<Exam> geyIds(List<String> ids) {
        List<Exam> list = this.ExamMapper.selectBatchIds(ids);
        return list;
    }
}
