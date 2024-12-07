package edu.gdbtu.lzq.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.system.model.SysPost;
import edu.gdbtu.lzq.system.service.SysPostService;
import edu.gdbtu.lzq.system.vo.SysPostQueryVo;
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
 * @description 岗位信息表
 * @date 2023-04-30 12:37:35
 */
@Api(tags = "岗位信息表")
@RestController
@RequestMapping("/system/sysPost")
public class SysPostController {
    @Autowired
    private SysPostService sysPostService;

    @PreAuthorize("hasAuthority('bnt.sysPost.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "sysPostQueryVo", value = "查询对象", required = false)
                        SysPostQueryVo sysPostQueryVo) {
        Page<SysPost> pageParam = new Page<>(page, limit);
        IPage<SysPost> pageModel = sysPostService.selectPage(pageParam, sysPostQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.sysPost.list')")
    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "sysPostQueryVo", value = "查询对象", required = false)
                       SysPostQueryVo sysPostQueryVo) {
        List<SysPost> list = sysPostService.queryList(sysPostQueryVo);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysPost.list')")
    @ApiOperation(value = "所有岗位信息表列表")
    @GetMapping("findAll")
    public Result findAllSysPost() {
        //调用service的方法实现查询所有的操作
        List<SysPost> list = sysPostService.list(null);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysPost.list')")
    @ApiOperation(value = "获取岗位信息表")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        SysPost sysPost = sysPostService.getById(id);
        return Result.ok(sysPost);
    }

    @PreAuthorize("hasAuthority('bnt.sysPost.list')")
    @ApiOperation(value = "获取岗位信息表集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<SysPost> list = sysPostService.getByIds(idList);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysPost.add')")
    @ApiOperation(value = "保存岗位信息表")
    @PostMapping("/save")
    public Result save(@RequestBody SysPost sysPost) {
        sysPostService.save(sysPost);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysPost.update')")
    @ApiOperation(value = "更新岗位信息表")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysPost sysPost) {
        sysPostService.updateById(sysPost);
        return Result.ok();
    }


    @PreAuthorize("hasAuthority('bnt.sysPost.remove')")
    @ApiOperation(value = "删除岗位信息表")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        sysPostService.removeById(id);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysPost.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = sysPostService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
