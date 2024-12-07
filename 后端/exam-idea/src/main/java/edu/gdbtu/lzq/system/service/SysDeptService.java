package edu.gdbtu.lzq.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.system.SysDept;

import java.util.List;
import java.util.Map;
//部门Service
public interface SysDeptService extends IService<SysDept> {

    /**
     * 部门树形数据
     * @return
     */
    public List<SysDept> findNodes();
    public List<Map> findSelectNodes();
    public boolean save(SysDept sysDept);
    public boolean updateById(SysDept sysDept);
    public List<SysDept> findNodesByParent(String parentId);
}
