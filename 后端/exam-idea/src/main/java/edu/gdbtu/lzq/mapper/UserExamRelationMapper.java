package edu.gdbtu.lzq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.model.UserExamRelation;
import edu.gdbtu.lzq.vo.UserExamRelationQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试关系表 Mapper层
 * @date 2023-09-25 16:12:14
 */
@Repository
@Mapper
public interface UserExamRelationMapper extends BaseMapper<UserExamRelation> {
    IPage<UserExamRelation> selectPage(Page<UserExamRelation> page, @Param("vo") UserExamRelationQueryVo UserExamRelationQueryVo);

    List<UserExamRelation> queryList(@Param("vo") UserExamRelationQueryVo UserExamRelationQueryVo);
}
