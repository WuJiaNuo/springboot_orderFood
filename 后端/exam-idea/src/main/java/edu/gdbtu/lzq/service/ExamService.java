package edu.gdbtu.lzq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.Exam;
import edu.gdbtu.lzq.vo.ExamQueryVo;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试试卷表 Service接口
 * @date 2023-09-21 00:57:32
 */
public interface ExamService extends IService<Exam> {
    IPage<Exam> selectPage(Page<Exam> pageParam, ExamQueryVo queryVo);

    List<Exam> queryList(ExamQueryVo queryVo);

    public boolean save(Exam Exam);

    public boolean updateById(Exam Exam);

    public Exam geyId(String id);

    public List<Exam> geyIds(List<String> ids);
}
