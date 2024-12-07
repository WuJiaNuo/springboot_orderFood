package edu.gdbtu.lzq.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.system.model.SysDicItem;
import edu.gdbtu.lzq.system.vo.SysDicItemQueryVo;
import edu.gdbtu.lzq.system.service.SysDicItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 字典选项
 * @date 2020-04-13 16:12:32
 */
@Api(tags = "字典选项")
@RestController
@RequestMapping("/system/sysDicItem")
public class SysDicItemController {
    @Autowired
    private SysDicItemService sysDicItemService;

    @PreAuthorize("hasAuthority('bnt.sysDicItem.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "sysDicItemQueryVo", value = "查询对象", required = false)
                        SysDicItemQueryVo sysDicItemQueryVo) {
        Page<SysDicItem> pageParam = new Page<>(page, limit);
        IPage<SysDicItem> pageModel = sysDicItemService.selectPage(pageParam, sysDicItemQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.sysDicItem.list')")
    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "sysDicItemQueryVo", value = "查询对象", required = false)
                       SysDicItemQueryVo sysDicItemQueryVo) {
        List<SysDicItem> list = sysDicItemService.queryList(sysDicItemQueryVo);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysDicItem.list')")
    @ApiOperation(value = "所有字典选项列表")
    @GetMapping("findAll/{dicCode}")
    public Result findAllSysDicItem(@PathVariable String dicCode) {
        //调用service的方法实现查询所有的操作
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(dicCode)) {
            queryWrapper.eq("dic_code", dicCode);
        }
        List<SysDicItem> list = sysDicItemService.list(queryWrapper);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysDicItem.list')")
    @ApiOperation(value = "获取字典选项")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        SysDicItem sysDicItem = sysDicItemService.getById(id);
        return Result.ok(sysDicItem);
    }

    @PreAuthorize("hasAuthority('bnt.sysDicItem.add')")
    @ApiOperation(value = "保存字典选项")
    @PostMapping("/save")
    public Result save(@RequestBody SysDicItem sysDicItem) {
        sysDicItemService.save(sysDicItem);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysDicItem.update')")
    @ApiOperation(value = "更新字典选项")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysDicItem sysDicItem) {
        sysDicItemService.updateById(sysDicItem);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysDicItem.remove')")
    @ApiOperation(value = "删除字典选项")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        sysDicItemService.removeById(id);
        return Result.ok();
    }
}
