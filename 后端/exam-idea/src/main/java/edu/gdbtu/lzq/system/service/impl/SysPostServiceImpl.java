package edu.gdbtu.lzq.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.system.model.SysPost;
import edu.gdbtu.lzq.system.vo.SysPostQueryVo;
import edu.gdbtu.lzq.system.mapper.SysPostMapper;
import edu.gdbtu.lzq.system.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
* @author tanlingfei
* @version 1.0
* @description 岗位信息表 Service实现类
* @date 2023-04-30 12:37:35
*/
@Transactional
@Service
public class SysPostServiceImpl extends ServiceImpl
<SysPostMapper, SysPost> implements SysPostService {
    @Autowired
    private SysPostMapper sysPostMapper;

    @Override
    public IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo) {
        //QueryWrapper<SysPost> queryWrapper = new QueryWrapper<>();
        //return sysPostMapper.selectPage(pageParam,queryWrapper);
        return sysPostMapper.selectPage(pageParam,sysPostQueryVo);
    }

    @Override
    public List<SysPost> queryList(SysPostQueryVo sysPostQueryVo){
        return sysPostMapper.queryList(sysPostQueryVo);
    }
    @Override
    public boolean save(SysPost sysPost){
        int result = this.sysPostMapper.insert(sysPost);
        return result>0;
    }
    @Override
    public boolean updateById(SysPost sysPost){
        int row = this.sysPostMapper.updateById(sysPost);
        return row>0;
    }
    @Override
    public SysPost getById(String id){
         SysPost sysPost = sysPostMapper.selectById(id);
        return sysPost;
    }
   @Override
   public List<SysPost> getByIds(List<String> ids) {
      List<SysPost> list = this.sysPostMapper.selectBatchIds(ids);
           return list;
     }
    /**
    @Override
    public void updateStatus(String id, Integer status) {
        SysPost sysPost = sysPostMapper.selectById(id);
        sysPost.setStatus(status);
        sysPostMapper.updateById(sysPost);
    }**/
}
