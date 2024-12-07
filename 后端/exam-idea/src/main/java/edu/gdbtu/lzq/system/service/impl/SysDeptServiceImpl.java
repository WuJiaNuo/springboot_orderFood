package edu.gdbtu.lzq.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import edu.gdbtu.lzq.model.system.SysDept;
import edu.gdbtu.lzq.model.system.SysUser;
import edu.gdbtu.lzq.model.vo.SysDeptQueryVo;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.system.mapper.SysDeptMapper;
import edu.gdbtu.lzq.system.service.SysDeptService;
import edu.gdbtu.lzq.system.utils.DeptHelper;
import edu.gdbtu.lzq.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
//部门Service类
@Transactional
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public boolean save(SysDept sysDept) {
        this.sysDeptMapper.insert(sysDept);
        if ("0".equals(sysDept.getParentId())) {
            sysDept.setLevel(1);
        } else {
            SysDept sysDept1 = this.getById(sysDept.getParentId());
            sysDept.setLevel(sysDept1.getLevel() + 1);
        }
        String treePath = this.getTreePath(sysDept);
        sysDept.setTreePath(treePath);
        this.sysDeptMapper.updateById(sysDept);
        return true;
    }

    public boolean updateById(SysDept sysDept) {
        if ("0".equals(sysDept.getParentId())) {
            sysDept.setLevel(1);
        } else {
            SysDept sysDept1 = this.getById(sysDept.getParentId());
            sysDept.setLevel(sysDept1.getLevel() + 1);
        }
        String treePath = this.getTreePath(sysDept);
        sysDept.setTreePath(treePath);
        this.sysDeptMapper.updateById(sysDept);
        return true;
    }

    private String getTreePath(SysDept sysDept) {
        if (sysDept.getParentId() == null || "0".equals(sysDept.getParentId())) {
            return "," + sysDept.getId() + ",";
        } else {
            SysDept sysDept1 = this.getById(sysDept.getParentId());
            if (!StringUtils.isEmpty(sysDept1.getTreePath())) {
                return sysDept1.getTreePath() + sysDept.getId() + ",";
            }
            return getTreePath(sysDept1) + sysDept.getId() + ",";
        }
    }

    @Override
    public List<SysDept> findNodes() {
        //全部部门列表
        SysDeptQueryVo sysDeptQueryVo = new SysDeptQueryVo();
        SysUser sysUser = UserUtil.getUserInfo();
        if ("1".equals(sysUser.getId())) {
            sysDeptQueryVo.setCurDeptIds(null);
        } else {
            if (CollectionUtils.isEmpty(sysUser.getDeptIds())) {
                return null;
            }
            sysDeptQueryVo.setCurDeptIds(sysUser.getDeptIds());
        }
        List<SysDept> sysDptList = sysDeptMapper.queryList(sysDeptQueryVo);
        if (CollectionUtils.isEmpty(sysDptList)) return null;
        //构建树形数据
        List<SysDept> result = DeptHelper.buildTree(sysDptList);
        return result;
    }

    @Override
    public List<SysDept> findNodesByParent(String parentId) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        SysUser sysUser = UserUtil.getUserInfo();
        List<String> deptIds = sysUser.getDeptIds();
        if (!"1".equals(sysUser.getId()) && "0".equals(parentId)) {
            if (CollectionUtils.isEmpty(deptIds)) {
                return null;
            }
            //找到当前用户所属部门根节点
            QueryWrapper<SysDept> findWrapper = new QueryWrapper<>();
            findWrapper.in("id", deptIds);
            findWrapper.orderByAsc("level");
            List<SysDept> findList = this.list(findWrapper);
            if (!CollectionUtils.isEmpty(findList)) {
                return null;
            }
            SysDept sysDept = findList.get(0);
            queryWrapper.eq("id", sysDept.getId());
        } else {
            queryWrapper.eq("parent_id", parentId);
        }
        List<SysDept> sysDptList = this.list(queryWrapper);
        return sysDptList;
    }

    @Override
    public List<Map> findSelectNodes() {
        //全部部门列表
        SysDeptQueryVo sysDeptQueryVo = new SysDeptQueryVo();
        SysUser sysUser = UserUtil.getUserInfo();
        if ("1".equals(sysUser.getId())) {
            sysDeptQueryVo.setCurDeptIds(null);
        } else {
            if (CollectionUtils.isEmpty(sysUser.getDeptIds())) {
                return null;
            }
            sysDeptQueryVo.setCurDeptIds(sysUser.getDeptIds());
        }
        List<Map> sysDptList = sysDeptMapper.findList(sysDeptQueryVo);
        if (CollectionUtils.isEmpty(sysDptList)) return null;
        //构建树形数据
        List<Map> result = DeptHelper.buildTreeMap(sysDptList);
        return result;
    }

    @Override
    public boolean removeById(Serializable id) {
        int count = this.count(new QueryWrapper<SysDept>().eq("parent_id", id));
        if (count > 0) {
            throw new LanfException(ResultCodeEnum.NODE_ERROR);
        }
        sysDeptMapper.deleteById(id);
        return false;
    }
}
