package edu.gdbtu.lzq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.Question;
import edu.gdbtu.lzq.vo.ExamVo;
import edu.gdbtu.lzq.vo.QuestionQueryVo;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 Service接口
 * @date 2023-09-21 00:54:13
 */
public interface QuestionService extends IService<Question> {
    IPage<Question> selectPage(Page<Question> pageParam, QuestionQueryVo queryVo);

    List<Question> queryList(QuestionQueryVo queryVo);

    List<Question> queryMyList(QuestionQueryVo queryVo);

    public boolean save(Question Question);

    public boolean updateById(Question Question);

    public Question geyId(String id);

    public List<Question> geyIds(List<String> ids);

    public void saveExam(ExamVo ExamVo);
    //void updateStatus(String id, Integer status);
}
