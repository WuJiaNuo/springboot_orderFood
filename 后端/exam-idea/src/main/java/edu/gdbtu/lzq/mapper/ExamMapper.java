package edu.gdbtu.lzq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.model.Exam;
import edu.gdbtu.lzq.vo.ExamQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试试卷表 Mapper层
 * @date 2023-09-21 00:57:32
 */
@Repository
@Mapper
public interface ExamMapper extends BaseMapper<Exam> {
    IPage<Exam> selectPage(Page<Exam> page, @Param("vo") ExamQueryVo ExamQueryVo);

    List<Exam> queryList(@Param("vo") ExamQueryVo ExamQueryVo);

    Exam getById(@Param("id") String id);
}
