package edu.gdbtu.lzq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.ExamResult;
import edu.gdbtu.lzq.vo.ExamResultQueryVo;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试结果表 Service接口
 * @date 2023-10-02 14:01:00
 */
public interface ExamResultService extends IService<ExamResult> {
    IPage<ExamResult> selectPage(Page<ExamResult> pageParam, ExamResultQueryVo queryVo);

    List<ExamResult> queryList(ExamResultQueryVo queryVo);

    public boolean save(ExamResult ExamResult);

    public boolean updateById(ExamResult ExamResult);

    public ExamResult geyId(String id);

    public List<ExamResult> geyIds(List<String> ids);
}
