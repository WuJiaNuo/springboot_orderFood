package edu.gdbtu.lzq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.model.ExamQuestionRelation;
import edu.gdbtu.lzq.vo.ExamQuestionRelationQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试题目关系表 Mapper层
 * @date 2023-09-20 15:15:19
 */
@Repository
@Mapper
public interface ExamQuestionRelationMapper extends BaseMapper<ExamQuestionRelation> {
    IPage<ExamQuestionRelation> selectPage(Page<ExamQuestionRelation> page, @Param("vo") ExamQuestionRelationQueryVo ExamQuestionRelationQueryVo);

    List<ExamQuestionRelation> queryList(@Param("vo") ExamQuestionRelationQueryVo ExamQuestionRelationQueryVo);
}
