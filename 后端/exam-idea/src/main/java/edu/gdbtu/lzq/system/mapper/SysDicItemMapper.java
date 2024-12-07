package edu.gdbtu.lzq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.system.model.SysDicItem;
import edu.gdbtu.lzq.system.vo.SysDicItemQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author tanlingfei
* @version 1.0
* @description 字典选项 Mapper层
* @date 2020-04-13 16:12:32
*/
@Repository
@Mapper
public interface SysDicItemMapper extends BaseMapper<SysDicItem> {
    IPage<SysDicItem> selectPage(Page<SysDicItem> page, @Param("vo") SysDicItemQueryVo sysDicItemQueryVo);
    List<SysDicItem> queryList(@Param("vo") SysDicItemQueryVo sysDicItemQueryVo);
}
