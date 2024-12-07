package edu.gdbtu.lzq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.model.ExamResult;
import edu.gdbtu.lzq.vo.ExamResultQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试结果表 Mapper层
 * @date 2023-10-02 14:01:00
 */
@Repository
@Mapper
public interface ExamResultMapper extends BaseMapper<ExamResult> {
    IPage<ExamResult> selectPage(Page<ExamResult> page, @Param("vo") ExamResultQueryVo ExamResultQueryVo);

    List<ExamResult> queryList(@Param("vo") ExamResultQueryVo ExamResultQueryVo);
}
