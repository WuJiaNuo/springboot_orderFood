package edu.gdbtu.lzq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.system.SysRole;
import edu.gdbtu.lzq.model.vo.AssginRoleVo;
import edu.gdbtu.lzq.model.vo.SysRoleQueryVo;

import java.util.Map;
//角色Service
public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo);

    public Map<String, Object> getRolesByUserId(String userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
