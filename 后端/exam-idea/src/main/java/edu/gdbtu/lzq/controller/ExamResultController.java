package edu.gdbtu.lzq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.model.ExamResult;
import edu.gdbtu.lzq.system.easyexcel.ExcelHandler;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.system.utils.UserUtil;
import edu.gdbtu.lzq.vo.ExamResultExportVo;
import edu.gdbtu.lzq.vo.ExamResultQueryVo;
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
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试结果表Controller
 * @date 2023-10-02 14:01:00
 */
@Api(tags = "用户考试结果表")
@RestController
@RequestMapping("/ExamResult")
public class ExamResultController {
    @Autowired
    private edu.gdbtu.lzq.service.ExamResultService ExamResultService;

    @Resource
    private ExcelHandler excelHandler;

    @PreAuthorize("hasAuthority('bnt.ExamResult.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "ExamResultQueryVo", value = "查询对象", required = false)
                        ExamResultQueryVo ExamResultQueryVo) {
        Page<ExamResult> pageParam = new Page<>(page, limit);
        ExamResultQueryVo.setUserId(UserUtil.getUserId());
        IPage<ExamResult> pageModel = ExamResultService.selectPage(pageParam, ExamResultQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/all/{page}/{limit}")
    public Result all(@ApiParam(name = "page", value = "当前页码", required = true)
                      @PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                      @PathVariable Long limit,
                      @ApiParam(name = "ExamResultQueryVo", value = "查询对象", required = false)
                      ExamResultQueryVo ExamResultQueryVo) {
        Page<ExamResult> pageParam = new Page<>(page, limit);
        IPage<ExamResult> pageModel = ExamResultService.selectPage(pageParam, ExamResultQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.list')")
    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "ExamResultQueryVo", value = "查询对象", required = false)
                       ExamResultQueryVo ExamResultQueryVo) {
        List<ExamResult> list = ExamResultService.queryList(ExamResultQueryVo);

        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.list')")
    @ApiOperation(value = "所有用户考试结果表列表")
    @GetMapping("findAll")
    public Result findAllExamResult() {
        //调用service的方法实现查询所有的操作
        List<ExamResult> list = ExamResultService.list(null);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.list')")
    @ApiOperation(value = "获取用户考试结果表")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        ExamResult ExamResult = ExamResultService.geyId(id);
        return Result.ok(ExamResult);
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.list')")
    @ApiOperation(value = "获取用户考试结果表集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<ExamResult> list = ExamResultService.geyIds(idList);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.add')")
    @ApiOperation(value = "保存用户考试结果表")
    @PostMapping("/save")
    public Result save(@RequestBody ExamResult ExamResult) {
        ExamResultService.save(ExamResult);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.update')")
    @ApiOperation(value = "更新用户考试结果表")
    @PutMapping("/update")
    public Result updateById(@RequestBody ExamResult ExamResult) {
        ExamResultService.updateById(ExamResult);
        return Result.ok();
    }


    @PreAuthorize("hasAuthority('bnt.ExamResult.remove')")
    @ApiOperation(value = "删除用户考试结果表")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        ExamResultService.removeById(id);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = ExamResultService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @PreAuthorize("hasAuthority('bnt.ExamResult.list')")
    @ApiOperation(value = "导出用户考试结果表")
    @GetMapping("/export")
    public void exportUser(HttpServletResponse response) {
        List<ExamResult> list = this.ExamResultService.list();
        List<ExamResultExportVo> expList = new ArrayList<>();
        list.forEach(po -> {
            ExamResultExportVo vo = new ExamResultExportVo();
            BeanUtils.copyProperties(po, vo);
            expList.add(vo);
        });
        try {
            this.excelHandler.exportExcel(response, expList, ExamResultExportVo.class, "用户考试结果表数据", "用户考试结果表数据");
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9005, "导出失败");
        }
    }
}
