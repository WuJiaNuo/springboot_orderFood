package edu.gdbtu.lzq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.model.MyQuestionLog;
import edu.gdbtu.lzq.system.easyexcel.ExcelHandler;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.vo.MyQuestionLogExportVo;
import edu.gdbtu.lzq.vo.MyQuestionLogQueryVo;
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
 * @description 考试答题记录Controller
 * @date 2023-10-16 15:56:10
 */
@Api(tags = "考试答题记录")
@RestController
@RequestMapping("/MyQuestionLog")
public class MyQuestionLogController {
    @Autowired
    private edu.gdbtu.lzq.service.MyQuestionLogService MyQuestionLogService;

    @Resource
    private ExcelHandler excelHandler;

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "MyQuestionLogQueryVo", value = "查询对象", required = false)
                        MyQuestionLogQueryVo MyQuestionLogQueryVo) {
        Page<MyQuestionLog> pageParam = new Page<>(page, limit);
        IPage<MyQuestionLog> pageModel = MyQuestionLogService.selectPage(pageParam, MyQuestionLogQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.list')")
    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "MyQuestionLogQueryVo", value = "查询对象", required = false)
                       MyQuestionLogQueryVo MyQuestionLogQueryVo) {
        List<MyQuestionLog> list = MyQuestionLogService.queryList(MyQuestionLogQueryVo);

        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.list')")
    @ApiOperation(value = "所有考试答题记录列表")
    @GetMapping("findAll")
    public Result findAllMyQuestionLog() {
        //调用service的方法实现查询所有的操作
        List<MyQuestionLog> list = MyQuestionLogService.list(null);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.list')")
    @ApiOperation(value = "获取考试答题记录")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        MyQuestionLog MyQuestionLog = MyQuestionLogService.geyId(id);
        return Result.ok(MyQuestionLog);
    }

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.list')")
    @ApiOperation(value = "获取考试答题记录集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<MyQuestionLog> list = MyQuestionLogService.geyIds(idList);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.add')")
    @ApiOperation(value = "保存考试答题记录")
    @PostMapping("/save")
    public Result save(@RequestBody MyQuestionLog MyQuestionLog) {
        MyQuestionLogService.save(MyQuestionLog);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.update')")
    @ApiOperation(value = "更新考试答题记录")
    @PutMapping("/update")
    public Result updateById(@RequestBody MyQuestionLog MyQuestionLog) {
        MyQuestionLogService.updateById(MyQuestionLog);
        return Result.ok();
    }


    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.remove')")
    @ApiOperation(value = "删除考试答题记录")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        MyQuestionLogService.removeById(id);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = MyQuestionLogService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @PreAuthorize("hasAuthority('bnt.MyQuestionLog.list')")
    @ApiOperation(value = "导出考试答题记录")
    @GetMapping("/export")
    public void exportUser(HttpServletResponse response) {
        List<MyQuestionLog> list = this.MyQuestionLogService.queryList(new MyQuestionLogQueryVo());
        List<MyQuestionLogExportVo> expList = new ArrayList<>();
        list.forEach(po -> {
            MyQuestionLogExportVo vo = new MyQuestionLogExportVo();
            BeanUtils.copyProperties(po, vo);
            expList.add(vo);
        });
        try {
            this.excelHandler.exportExcel(response, expList, MyQuestionLogExportVo.class, "考试答题记录数据", "考试答题记录数据");
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9005, "导出失败");
        }
    }
}
