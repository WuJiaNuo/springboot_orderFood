package edu.gdbtu.lzq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.system.model.SysDic;
import edu.gdbtu.lzq.system.vo.SysDicQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author tanlingfei
* @version 1.0
* @description 字典分类 Mapper层
* @date 2020-04-13 09:55:26
*/
@Repository
@Mapper
public interface SysDicMapper extends BaseMapper<SysDic> {
    IPage<SysDic> selectPage(Page<SysDic> page, @Param("vo") SysDicQueryVo sysDicQueryVo);
}
