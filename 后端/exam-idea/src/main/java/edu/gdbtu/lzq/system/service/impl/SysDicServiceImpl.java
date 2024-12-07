package edu.gdbtu.lzq.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.system.mapper.SysDicMapper;
import edu.gdbtu.lzq.system.model.SysDic;
import edu.gdbtu.lzq.system.service.SysDicService;
import edu.gdbtu.lzq.system.vo.SysDicQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author tanlingfei
* @version 1.0
* @description 字典分类 Service实现类
* @date 2020-04-13 09:55:26
*/
@Transactional
@Service
public class SysDicServiceImpl extends ServiceImpl
<SysDicMapper, SysDic> implements SysDicService {
    @Autowired
    private SysDicMapper sysDicMapper;

    @Override
    public IPage<SysDic> selectPage(Page<SysDic> pageParam, SysDicQueryVo sysDicQueryVo) {
        //QueryWrapper<SysDic> queryWrapper = new QueryWrapper<>();
        //return sysDicMapper.selectPage(pageParam,queryWrapper);
        return sysDicMapper.selectPage(pageParam,sysDicQueryVo);
    }

    /**
    @Override
    public void updateStatus(String id, Integer status) {
        SysDic sysDic = sysDicMapper.selectById(id);
        sysDic.setStatus(status);
        sysDicMapper.updateById(sysDic);
    }**/
}
