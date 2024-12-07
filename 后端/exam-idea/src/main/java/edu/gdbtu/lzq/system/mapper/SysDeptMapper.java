package edu.gdbtu.lzq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdbtu.lzq.model.system.SysDept;
import edu.gdbtu.lzq.model.vo.SysDeptQueryVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
    @MapKey("id")
    public List<Map> findList(@Param("vo") SysDeptQueryVo sysDeptQueryVo);
    public List<SysDept> queryList(@Param("vo") SysDeptQueryVo sysDeptQueryVo);
}
