package edu.gdbtu.lzq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.model.ExamQuestionRelation;
import edu.gdbtu.lzq.vo.ExamQuestionRelationQueryVo;
import edu.gdbtu.lzq.mapper.ExamQuestionRelationMapper;
import edu.gdbtu.lzq.service.ExamQuestionRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import edu.gdbtu.lzq.system.exception.LanfException;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试题目关系表 Service实现类
 * @date 2023-09-20 15:15:19
 */
@Transactional
@Service
public class ExamQuestionRelationServiceImpl extends ServiceImpl
        <ExamQuestionRelationMapper, ExamQuestionRelation> implements ExamQuestionRelationService {
    @Autowired
    private ExamQuestionRelationMapper ExamQuestionRelationMapper;

    @Override
    public IPage<ExamQuestionRelation> selectPage(Page<ExamQuestionRelation> pageParam, ExamQuestionRelationQueryVo ExamQuestionRelationQueryVo) {
        return ExamQuestionRelationMapper.selectPage(pageParam, ExamQuestionRelationQueryVo);
    }

    @Override
    public List<ExamQuestionRelation> queryList(ExamQuestionRelationQueryVo ExamQuestionRelationQueryVo) {
        List<ExamQuestionRelation> result = ExamQuestionRelationMapper.queryList(ExamQuestionRelationQueryVo);
        return result;
    }


    @Override
    public boolean save(ExamQuestionRelation ExamQuestionRelation) {
        int result = this.ExamQuestionRelationMapper.insert(ExamQuestionRelation);
        return result > 0;
    }

    @Override
    public boolean updateById(ExamQuestionRelation ExamQuestionRelation) {
        int row = this.ExamQuestionRelationMapper.updateById(ExamQuestionRelation);
        if (row <= 0) {
            throw new LanfException(ResultCodeEnum.UPLOAD_ERROR);
        }
        return row > 0;
    }

    @Override
    public ExamQuestionRelation geyId(String id) {
        ExamQuestionRelation ExamQuestionRelation = ExamQuestionRelationMapper.selectById(id);
        return ExamQuestionRelation;
    }

    @Override
    public List<ExamQuestionRelation> geyIds(List<String> ids) {
        List<ExamQuestionRelation> list = this.ExamQuestionRelationMapper.selectBatchIds(ids);
        return list;
    }
    /**
     @Override public void updateStatus(String id, Integer status) {
     ExamQuestionRelation ExamQuestionRelation = ExamQuestionRelationMapper.selecyId(id);
     ExamQuestionRelation.setStatus(status);
     ExamQuestionRelationMapper.updateById(ExamQuestionRelation);
     }**/
}
