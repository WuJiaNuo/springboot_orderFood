package edu.gdbtu.lzq.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.gdbtu.lzq.model.system.SysUser;
import edu.gdbtu.lzq.model.vo.RouterVo;
import edu.gdbtu.lzq.model.vo.SysUserQueryVo;
import edu.gdbtu.lzq.system.mapper.SysUserMapper;
import edu.gdbtu.lzq.system.model.SysUserDept;
import edu.gdbtu.lzq.system.service.SysMenuService;
import edu.gdbtu.lzq.system.service.SysUserDeptService;
import edu.gdbtu.lzq.system.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
//用户管理Service实现类
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserDeptService sysUserDeptService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo) {
        //只查询当前登录所属部门数据
        SysUser sysUser = UserUtil.getUserInfo();
        if ("1".equals(sysUser.getId())) {
            userQueryVo.setCurDeptIds(null);
        } else {
            if (CollectionUtils.isEmpty(sysUser.getDeptIds())) {
                return null;
            }
            userQueryVo.setCurDeptIds(sysUser.getDeptIds());
        }
        return sysUserMapper.selectPage(pageParam, userQueryVo);
    }

    @Override
    public void updateStatus(String id, Integer status) {
        SysUser sysUser = sysUserMapper.selectById(id);
        sysUser.setStatus(status);
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public SysUser getByUsername(String username) {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
        QueryWrapper<SysUserDept> queryWrapper = new QueryWrapper<SysUserDept>();
        queryWrapper.select("dept_id");
        queryWrapper.eq("user_id", sysUser.getId());
        Function<Object, String> f = (o -> o.toString());
        List<String> deptIds = sysUserDeptService.listObjs(queryWrapper, f);
        if (deptIds == null) {
            deptIds = new ArrayList<String>();
        }
        sysUser.setDeptIds(deptIds);
        return sysUser;
    }

    @Override
    public boolean save(SysUser sysUser) {
        int result = this.sysUserMapper.insert(sysUser);
        List<String> deptIds = sysUser.getDeptIds();
        if (deptIds != null && deptIds.size() > 0) {
            for (String deptId : deptIds) {
                SysUserDept sysUserDept = new SysUserDept();
                sysUserDept.setUserId(sysUser.getId());
                sysUserDept.setDeptId(deptId);
                sysUserDeptService.save(sysUserDept);
            }
        }
        return result > 0;
    }

    @Override
    public boolean updateById(SysUser sysUser) {
        int row = this.sysUserMapper.updateById(sysUser);
        List<String> deptIds = sysUser.getDeptIds();
        QueryWrapper<SysUserDept> queryWrapper = new QueryWrapper<SysUserDept>();
        queryWrapper.eq("user_id", sysUser.getId());
        sysUserDeptService.remove(queryWrapper);
        if (deptIds != null && deptIds.size() > 0) {
            for (String deptId : deptIds) {
                SysUserDept sysUserDept = new SysUserDept();
                sysUserDept.setUserId(sysUser.getId());
                sysUserDept.setDeptId(deptId);
                sysUserDeptService.save(sysUserDept);
            }
        }
        return row > 0;
    }

    @Override
    public SysUser getById(String id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        QueryWrapper<SysUserDept> studentInterQueryWrapper = new QueryWrapper<SysUserDept>();
        studentInterQueryWrapper.select("dept_id");
        studentInterQueryWrapper.eq("user_id", sysUser.getId());
        Function<Object, String> f = (o -> o.toString());
        List<String> deptIds = sysUserDeptService.listObjs(studentInterQueryWrapper, f);
        if (deptIds == null) {
            deptIds = new ArrayList<String>();
        }
        sysUser.setDeptIds(deptIds);
        return sysUser;
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        SysUser sysUser = this.getByUsername(username);
        //根据用户id获取菜单权限值
        List<RouterVo> routerVoList = sysMenuService.findUserMenuList(sysUser.getId());
        //根据用户id获取用户按钮权限
        List<String> permsList = sysMenuService.findUserPermsList(sysUser.getId());

        result.put("name", sysUser.getName());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", new HashSet<>());
        result.put("buttons", permsList);
        result.put("routers", routerVoList);
        return result;
    }

    @Override
    public List<SysUser> getByIds(List<String> ids) {
        List<SysUser> list = this.sysUserMapper.selectBatchIds(ids);
        return list;
    }

}
