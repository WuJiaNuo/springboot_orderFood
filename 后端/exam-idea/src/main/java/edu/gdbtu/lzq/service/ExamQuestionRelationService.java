package edu.gdbtu.lzq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.ExamQuestionRelation;
import edu.gdbtu.lzq.vo.ExamQuestionRelationQueryVo;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试题目关系表 Service接口
 * @date 2023-09-20 15:15:19
 */
public interface ExamQuestionRelationService extends IService<ExamQuestionRelation> {
    IPage<ExamQuestionRelation> selectPage(Page<ExamQuestionRelation> pageParam, ExamQuestionRelationQueryVo queryVo);

    List<ExamQuestionRelation> queryList(ExamQuestionRelationQueryVo queryVo);

    public boolean save(ExamQuestionRelation ExamQuestionRelation);

    public boolean updateById(ExamQuestionRelation ExamQuestionRelation);

    public ExamQuestionRelation geyId(String id);

    public List<ExamQuestionRelation> geyIds(List<String> ids);
}
