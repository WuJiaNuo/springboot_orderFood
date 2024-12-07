package edu.gdbtu.lzq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.QuestionOption;
import edu.gdbtu.lzq.vo.QuestionOptionQueryVo;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题目选项 Service接口
 * @date 2023-09-25 21:23:12
 */
public interface QuestionOptionService extends IService<QuestionOption> {
    IPage<QuestionOption> selectPage(Page<QuestionOption> pageParam, QuestionOptionQueryVo queryVo);

    List<QuestionOption> queryList(QuestionOptionQueryVo queryVo);

    public boolean save(QuestionOption QuestionOption);

    public boolean updateById(QuestionOption QuestionOption);

    public QuestionOption geyId(String id);

    public List<QuestionOption> geyIds(List<String> ids);
}
