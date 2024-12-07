package edu.gdbtu.lzq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.model.Exam;
import edu.gdbtu.lzq.model.system.SysDept;
import edu.gdbtu.lzq.system.easyexcel.ExcelHandler;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.system.service.SysDeptService;
import edu.gdbtu.lzq.system.utils.UserUtil;
import edu.gdbtu.lzq.vo.ExamExportVo;
import edu.gdbtu.lzq.vo.ExamQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试试卷列表管理Controller
 * @date 2023-09-21 00:57:32
 */
@Api(tags = "考试试卷表")
@RestController
@RequestMapping("/Exam")
public class ExamController {
    @Autowired
    private edu.gdbtu.lzq.service.ExamService ExamService;
    @Resource
    private ExcelHandler excelHandler;

    @Autowired
    private SysDeptService sysDeptService;

    @PreAuthorize("hasAuthority('bnt.Exam.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "ExamQueryVo", value = "查询对象", required = false)
                        ExamQueryVo ExamQueryVo) {
        Page<Exam> pageParam = new Page<>(page, limit);
        IPage<Exam> pageModel = ExamService.selectPage(pageParam, ExamQueryVo);
        Map<String, Object> map = new HashMap<>();
        map.put("pageModel", pageModel);
        String deptId = UserUtil.getDeptIds().get(0);
        SysDept sysDept = sysDeptService.getById(deptId);
        map.put("curDeptName", sysDept.getName());
        map.put("curDept", deptId);
        return Result.ok(map);
    }

    @PreAuthorize("hasAuthority('bnt.Exam.list')")
    @ApiOperation(value = "我的考试")
    @GetMapping("/myExam/{page}/{limit}")
    public Result myExam(@ApiParam(name = "page", value = "当前页码", required = true)
                         @PathVariable Long page,
                         @ApiParam(name = "limit", value = "每页记录数", required = true)
                         @PathVariable Long limit,
                         @ApiParam(name = "ExamQueryVo", value = "查询对象", required = false)
                         ExamQueryVo ExamQueryVo) {
        Page<Exam> pageParam = new Page<>(page, limit);
        ExamQueryVo.setCurUserId(UserUtil.getUserId());
        IPage<Exam> pageModel = ExamService.selectPage(pageParam, ExamQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.Exam.list')")
    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "ExamQueryVo", value = "查询对象", required = false)
                       ExamQueryVo ExamQueryVo) {
        List<Exam> list = ExamService.queryList(ExamQueryVo);

        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.Exam.list')")
    @ApiOperation(value = "所有考试试卷表列表")
    @GetMapping("findAll")
    public Result findAllExam() {
        //调用service的方法实现查询所有的操作
        List<Exam> list = ExamService.list(null);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.Exam.list')")
    @ApiOperation(value = "获取考试试卷表")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        Exam Exam = ExamService.geyId(id);
        return Result.ok(Exam);
    }

    @PreAuthorize("hasAuthority('bnt.Exam.list')")
    @ApiOperation(value = "获取考试试卷表集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<Exam> list = ExamService.geyIds(idList);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.Exam.add')")
    @ApiOperation(value = "保存考试试卷表")
    @PostMapping("/save")
    public Result save(@RequestBody Exam Exam) {
        ExamService.save(Exam);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.Exam.update')")
    @ApiOperation(value = "更新考试试卷表")
    @PutMapping("/update")
    public Result updateById(@RequestBody Exam Exam) {
        ExamService.updateById(Exam);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.Exam.remove')")
    @ApiOperation(value = "删除考试试卷表")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        ExamService.removeById(id);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.Exam.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = ExamService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @PreAuthorize("hasAuthority('bnt.Exam.list')")
    @ApiOperation(value = "导出考试试卷表")
    @GetMapping("/export")
    public void exportUser(HttpServletResponse response) {
        List<Exam> list = this.ExamService.queryList(new ExamQueryVo());
        List<ExamExportVo> expList = new ArrayList<>();
        list.forEach(po -> {
            ExamExportVo vo = new ExamExportVo();
            BeanUtils.copyProperties(po, vo);
            expList.add(vo);
        });
        try {
            this.excelHandler.exportExcel(response, expList, ExamExportVo.class, "考试试卷表数据", "考试试卷表数据");
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9005, "导出失败");
        }
    }
}
