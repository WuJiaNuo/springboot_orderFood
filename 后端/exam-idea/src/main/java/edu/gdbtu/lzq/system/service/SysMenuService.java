package edu.gdbtu.lzq.system.service;

import edu.gdbtu.lzq.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.vo.AssginMenuVo;
import edu.gdbtu.lzq.model.vo.RouterVo;

import java.util.List;
//菜单管理Service
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单树形数据
     *
     * @return
     */
    List<SysMenu> findNodes();

    public List<SysMenu> findDir();

    List<SysMenu> findSysMenuByRoleId(String roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    /**
     * 获取用户菜单
     *
     * @param userId
     * @return
     */
    List<RouterVo> findUserMenuList(String userId);

    /**
     * 获取用户按钮权限
     * @param userId
     * @return
     */
    List<String> findUserPermsList(String userId);

}
