package edu.gdbtu.lzq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.model.QuestionOption;
import edu.gdbtu.lzq.vo.QuestionOptionQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题目选项 Mapper层
 * @date 2023-09-25 21:23:12
 */
@Repository
@Mapper
public interface QuestionOptionMapper extends BaseMapper<QuestionOption> {
    IPage<QuestionOption> selectPage(Page<QuestionOption> page, @Param("vo") QuestionOptionQueryVo QuestionOptionQueryVo);

    List<QuestionOption> queryList(@Param("vo") QuestionOptionQueryVo QuestionOptionQueryVo);
}
