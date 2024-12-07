package edu.gdbtu.lzq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.system.model.SysPost;
import edu.gdbtu.lzq.system.vo.SysPostQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
* @author tanlingfei
* @version 1.0
* @description 岗位信息表 Mapper层
* @date 2023-04-30 12:37:35
*/
@Repository
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {
    IPage<SysPost> selectPage(Page<SysPost> page, @Param("vo") SysPostQueryVo sysPostQueryVo);
    List<SysPost> queryList(@Param("vo") SysPostQueryVo sysPostQueryVo);
}
