package edu.gdbtu.lzq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.model.Question;
import edu.gdbtu.lzq.vo.QuestionQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 Mapper层
 * @date 2023-09-21 00:54:13
 */
@Repository
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    IPage<Question> selectPage(Page<Question> page, @Param("vo") QuestionQueryVo QuestionQueryVo);

    List<Question> queryList(@Param("vo") QuestionQueryVo QuestionQueryVo);

    Question getById(@Param("id") String id);
}
