package edu.gdbtu.lzq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.model.Exam;
import edu.gdbtu.lzq.model.Question;
import edu.gdbtu.lzq.model.QuestionOption;
import edu.gdbtu.lzq.model.system.SysDept;
import edu.gdbtu.lzq.system.easyexcel.ExcelHandler;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.system.service.SysDeptService;
import edu.gdbtu.lzq.system.utils.UserUtil;
import edu.gdbtu.lzq.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表Controller
 * @date 2023-09-21 00:54:13
 */
@Api(tags = "题库表")
@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    private edu.gdbtu.lzq.service.QuestionService QuestionService;

    @Autowired
    private edu.gdbtu.lzq.service.QuestionOptionService QuestionOptionService;

    @Autowired
    private edu.gdbtu.lzq.service.ExamService ExamService;

    @Autowired
    private SysDeptService sysDeptService;

    @Resource
    private ExcelHandler excelHandler;

    @PreAuthorize("hasAuthority('bnt.Question.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "QuestionQueryVo", value = "查询对象", required = false)
                        QuestionQueryVo QuestionQueryVo) {
        Page<Question> pageParam = new Page<>(page, limit);
        IPage<Question> pageModel = QuestionService.selectPage(pageParam, QuestionQueryVo);
        Map<String, Object> map = new HashMap<>();
        map.put("pageModel", pageModel);
        String deptId = UserUtil.getDeptIds().get(0);
        SysDept sysDept = sysDeptService.getById(deptId);
        map.put("curDeptName", sysDept.getName());
        map.put("curDept", deptId);
        return Result.ok(map);
    }

    @PreAuthorize("hasAuthority('bnt.Question.list')")
    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "QuestionQueryVo", value = "查询对象", required = false)
                       QuestionQueryVo QuestionQueryVo) {
        List<Question> list = QuestionService.queryList(QuestionQueryVo);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.Question.list')")
    @ApiOperation(value = "查询列表")
    @GetMapping("/myList")
    public Result myList(@ApiParam(name = "QuestionQueryVo", value = "查询对象", required = false)
                         QuestionQueryVo QuestionQueryVo) {
        List<Question> sigList = new ArrayList<>();
        List<Question> mulList = new ArrayList<>();
        List<Question> pdList = new ArrayList<>();
        List<Question> wdList = new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        Exam Exam = this.ExamService.geyId(QuestionQueryVo.getExamId());
        if ("6002".equals(Exam.getGetType())) {
            QuestionQueryVo.setIsRand(1); //随机抽题
            QuestionQueryVo.setGetNum(Exam.getGetNum()); //随机抽题数量
        }
        List<Question> list = QuestionService.queryMyList(QuestionQueryVo);
        if (list != null && list.size() > 0) {
            for (Question question : list) {
                if ("4001".equals(question.getType())) {
                    sigList.add(question);
                } else if ("4002".equals(question.getType())) {
                    mulList.add(question);
                } else if ("4003".equals(question.getType())) {
                    pdList.add(question);
                } else if ("4004".equals(question.getType())) {
                    wdList.add(question);
                }
            }
        }
        map.put("sigList", sigList);
        map.put("mulList", mulList);
        map.put("pdList", pdList);
        map.put("wdList", wdList);
        return Result.ok(map);
    }

    @PreAuthorize("hasAuthority('bnt.Question.list')")
    @ApiOperation(value = "所有题库表列表")
    @GetMapping("findAll")
    public Result findAllQuestion() {
        //调用service的方法实现查询所有的操作
        List<Question> list = QuestionService.list(null);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.Question.list')")
    @ApiOperation(value = "获取题库表")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        Question Question = QuestionService.geyId(id);
        return Result.ok(Question);
    }

    @PreAuthorize("hasAuthority('bnt.Question.list')")
    @ApiOperation(value = "获取题库表集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<Question> list = QuestionService.geyIds(idList);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.Question.add')")
    @ApiOperation(value = "保存题库表")
    @PostMapping("/save")
    public Result save(@RequestBody Question Question) {
        QuestionService.save(Question);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.Question.add')")
    @ApiOperation(value = "保存题库表")
    @PostMapping("/saveExam")
    public Result saveExam(@RequestBody ExamVo ExamVo) {
        this.QuestionService.saveExam(ExamVo);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.Question.update')")
    @ApiOperation(value = "更新题库表")
    @PutMapping("/update")
    public Result updateById(@RequestBody Question Question) {
        QuestionService.updateById(Question);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.Question.remove')")
    @ApiOperation(value = "删除题库表")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        QuestionService.removeById(id);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.Question.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = QuestionService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.add')")
    @ApiOperation(value = "导入用户")
    @PostMapping("/import")
    public Result importData(@RequestParam("file") MultipartFile multipartFile) {
        try {
            List<Object> plist = new ArrayList<>();
            plist.add(new QuestionExcelVo());
            plist.add(new QuestionExcelVo());
            plist.add(new QuestionExcelVo());
            List<List<QuestionExcelVo>> voList = excelHandler.importExcels(multipartFile, 3, plist);
            List<QuestionExcelData> dataList = new ArrayList<>();
            Map<String, QuestionExcelData> contentMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(voList)) {
                for (List<QuestionExcelVo> vlist : voList) {
                    for (QuestionExcelVo vo : vlist) {
                        String content = vo.getContent();
                        QuestionExcelData data = contentMap.get(content);
                        if (data == null) {
                            data = new QuestionExcelData();
                            data.setDeptName(vo.getDeptName());
                            data.setContent(content);
                            data.setScore(vo.getScore());
                            data.setTypeName(vo.getTypeName());
                            data.setRemark(vo.getRemark());
                            contentMap.put(content, data);
                            dataList.add(data);
                        }
                        QuestionOptionExcelData optionData = new QuestionOptionExcelData();
                        optionData.setOptionName(vo.getOptionName());
                        optionData.setOptionVal(vo.getOptionVal());
                        optionData.setIsRight(vo.getIsRight());
                        data.getOptionList().add(optionData);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(dataList)) {
                dataList.forEach(v -> {
                    Question Question = new Question();
                    String deptName = v.getDeptName();
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("name", deptName);
                    SysDept sysDept = sysDeptService.getOne(queryWrapper);
                    Question.setDeptId(sysDept.getId());
                    Question.setContent(v.getContent());
                    Question.setScore(v.getScore());
                    String typeName = v.getTypeName();
                    String type = "";
                    if ("单选题".equals(typeName)) {
                        type = "4001";
                    } else if ("多选题".equals(typeName)) {
                        type = "4002";
                    } else if ("判断题".equals(typeName)) {
                        type = "4003";
                    } else if ("填空题".equals(typeName)) {
                        type = "4004";
                    }
                    Question.setType(type);
                    Question.setRemark(v.getRemark());
                    this.QuestionService.save(Question);
                    List<QuestionOptionExcelData> optionList = v.getOptionList();
                    if (!CollectionUtils.isEmpty(optionList)) {
                        if (!"4004".equals(type)) {
                            List<QuestionOption> olist = new ArrayList<>();
                            for (QuestionOptionExcelData optionExcelData : optionList) {
                                //题目选项
                                String optionName = optionExcelData.getOptionName();
                                String optionVal = optionExcelData.getOptionVal();
                                String isRight = optionExcelData.getIsRight();
                                QuestionOption QuestionOption = new QuestionOption();
                                QuestionOption.setName(optionVal);
                                QuestionOption.setOptions(optionName);
                                QuestionOption.setIsRight("是".equals(isRight) ? 1 : 0);
                                QuestionOption.setQuestionId(Question.getId());
                                olist.add(QuestionOption);
                            }
                            if (olist.size() > 0) {
                                this.QuestionOptionService.saveBatch(olist);
                            }
                        } else {
                            QuestionOptionExcelData optionExcelData = optionList.get(0);
                            Question.setAnswer(optionExcelData.getOptionVal());
                            this.QuestionService.updateById(Question);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9004, "导入失败");
        }
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.Question.list')")
    @ApiOperation(value = "导出题库表")
    @GetMapping("/export")
    public void exportUser(HttpServletResponse response) {
        List<Question> list = this.QuestionService.queryList(new QuestionQueryVo());
        List<QuestionExportVo> expList = new ArrayList<>();
        list.forEach(po -> {
            QuestionExportVo vo = new QuestionExportVo();
            BeanUtils.copyProperties(po, vo);
            expList.add(vo);
        });
        try {
            this.excelHandler.exportExcel(response, expList, QuestionExportVo.class, "题库表数据", "题库表数据");
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9005, "导出失败");
        }
    }
}
