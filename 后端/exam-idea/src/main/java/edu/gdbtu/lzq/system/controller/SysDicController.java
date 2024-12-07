package edu.gdbtu.lzq.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.system.model.SysDic;
import edu.gdbtu.lzq.system.service.SysDicService;
import edu.gdbtu.lzq.system.vo.SysDicQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 字典分类
 * @date 2020-04-13 09:55:26
 */
@Api(tags = "字典分类")
@RestController
@RequestMapping("/system/sysDic")
public class SysDicController {
    @Autowired
    private SysDicService sysDicService;

    @PreAuthorize("hasAuthority('bnt.sysDic.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "sysDicQueryVo", value = "查询对象", required = false)
                        SysDicQueryVo sysDicQueryVo) {
        Page<SysDic> pageParam = new Page<>(page, limit);
        IPage<SysDic> pageModel = sysDicService.selectPage(pageParam, sysDicQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.sysDic.list')")
    @ApiOperation(value = "所有字典分类列表")
    @GetMapping("findAllSpec")
    public Result findAllSpec() {
        //调用service的方法实现查询所有的操作
        List<SysDic> list = sysDicService.list(null);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysDic.list')")
    @ApiOperation(value = "获取字典分类")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        SysDic sysDic = sysDicService.getById(id);
        return Result.ok(sysDic);
    }

    @PreAuthorize("hasAuthority('bnt.sysDic.add')")
    @ApiOperation(value = "保存字典分类")
    @PostMapping("/save")
    public Result save(@RequestBody SysDic sysDic) {
        sysDicService.save(sysDic);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysDic.update')")
    @ApiOperation(value = "更新字典分类")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysDic sysDic) {
        sysDicService.updateById(sysDic);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysDic.remove')")
    @ApiOperation(value = "删除字典分类")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        sysDicService.removeById(id);
        return Result.ok();
    }
}
