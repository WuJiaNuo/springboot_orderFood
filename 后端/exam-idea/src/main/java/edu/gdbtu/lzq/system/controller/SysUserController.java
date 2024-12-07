package edu.gdbtu.lzq.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.common.result.Result;
import edu.gdbtu.lzq.common.utils.MD5;
import edu.gdbtu.lzq.model.system.SysUser;
import edu.gdbtu.lzq.model.vo.SysUserQueryVo;
import edu.gdbtu.lzq.system.easyexcel.ExcelHandler;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.system.service.SysUserService;
import edu.gdbtu.lzq.system.vo.UserExcelVo;
import edu.gdbtu.lzq.system.vo.UserExportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Resource
    private ExcelHandler excelHandler;

    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
            SysUserQueryVo userQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, userQueryVo);
        return Result.ok(pageModel);
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @ApiOperation(value = "获取用户")
    @GetMapping("/getUser/{id}")
    public Result get(@PathVariable String id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @ApiOperation(value = "获取题库表集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<SysUser> list = sysUserService.getByIds(idList);
        return Result.ok(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.add')")
    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser user) {
        String pwd = MD5.encrypt(user.getPassword());
        user.setPassword(pwd);
        user.setStatus(1);
        sysUserService.save(user);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.add')")
    @ApiOperation(value = "导入用户")
    @PostMapping("/import")
    public Result importUser(@RequestParam("file") MultipartFile multipartFile) {
        try {
            List<Object> plist = new ArrayList<>();
            plist.add(new UserExcelVo());
            plist.add(new UserExcelVo());
            plist.add(new UserExcelVo());
            List<List<UserExcelVo>> voList = excelHandler.importExcels(multipartFile, 3, plist);
            if (CollectionUtils.isNotEmpty(voList)) {
                voList.forEach(vo -> {
                    vo.forEach(v -> {
                        SysUser sysUser = new SysUser();
                        BeanUtils.copyProperties(v, sysUser);
                        String pwd = MD5.encrypt(sysUser.getPassword());
                        sysUser.setPassword(pwd);
                        sysUser.setStatus(1);
                        sysUserService.save(sysUser);
                    });
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9004, "导入失败");
        }
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @ApiOperation(value = "导出用户")
    @GetMapping("/export")
    public void exportUser(HttpServletResponse response) {
        List<SysUser> list = this.sysUserService.selectPage(new Page<SysUser>(1, 10000), new SysUserQueryVo()).getRecords();
        List<UserExportVo> expList = new ArrayList<>();
        list.forEach(po -> {
            UserExportVo vo = new UserExportVo();
            BeanUtils.copyProperties(po, vo);
            expList.add(vo);
        });
        try {
            this.excelHandler.exportExcel(response, expList, UserExportVo.class, "用户数据", "用户数据");
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9005, "导出失败");
        }
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.update')")
    @ApiOperation(value = "更新用户")
    @PostMapping("/update")
    public Result updateById(@RequestBody SysUser user) {
        if (!StringUtils.isEmpty(user.getNewpassword())) {
            String pwd = MD5.encrypt(user.getNewpassword());
            user.setPassword(pwd);
        }
        sysUserService.updateById(user);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.remove')")
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        sysUserService.removeById(id);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.update')")
    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id, @PathVariable Integer status) {
        sysUserService.updateStatus(id, status);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysUser.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = sysUserService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}
