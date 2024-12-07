package edu.gdbtu.lzq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.system.model.SysDicItem;
import edu.gdbtu.lzq.system.vo.SysDicItemQueryVo;

import java.util.List;

/**
* @author tanlingfei
* @version 1.0
* @description 字典选项 Service接口
* @date 2020-04-13 16:12:32
*/
public interface SysDicItemService extends IService<SysDicItem> {
    IPage<SysDicItem> selectPage(Page<SysDicItem> pageParam, SysDicItemQueryVo queryVo);
    List<SysDicItem> queryList(SysDicItemQueryVo queryVo);
    //void updateStatus(String id, Integer status);
}
