package edu.gdbtu.lzq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.system.model.SysUserDept;
import edu.gdbtu.lzq.system.vo.SysUserDeptQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
* @author tanlingfei
* @version 1.0
* @description 用户和部门中间表 Mapper层
* @date 2020-04-20 13:13:24
*/
@Repository
@Mapper
public interface SysUserDeptMapper extends BaseMapper<SysUserDept> {
    IPage<SysUserDept> selectPage(Page<SysUserDept> page, @Param("vo") SysUserDeptQueryVo sysUserDeptQueryVo);
    List<SysUserDept> queryList(@Param("vo") SysUserDeptQueryVo sysUserDeptQueryVo);
}
