package edu.gdbtu.lzq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.system.model.SysUserDept;
import edu.gdbtu.lzq.system.vo.SysUserDeptQueryVo;

import java.util.List;
/**
* @author tanlingfei
* @version 1.0
* @description 用户和部门中间表 Service接口
* @date 2020-04-20 13:13:24
*/
public interface SysUserDeptService extends IService<SysUserDept> {
    IPage<SysUserDept> selectPage(Page<SysUserDept> pageParam, SysUserDeptQueryVo queryVo);
    List<SysUserDept> queryList(SysUserDeptQueryVo queryVo);
    public boolean save(SysUserDept sysUserDept);
    public boolean updateById(SysUserDept sysUserDept);
    public SysUserDept getById(String id);
    //void updateStatus(String id, Integer status);
}
