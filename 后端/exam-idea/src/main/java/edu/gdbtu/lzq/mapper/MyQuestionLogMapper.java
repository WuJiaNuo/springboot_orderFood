package edu.gdbtu.lzq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdbtu.lzq.model.MyQuestionLog;
import edu.gdbtu.lzq.vo.MyQuestionLogQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试答题记录 Mapper层
 * @date 2023-10-16 15:56:10
 */
@Repository
@Mapper
public interface MyQuestionLogMapper extends BaseMapper<MyQuestionLog> {
    IPage<MyQuestionLog> selectPage(Page<MyQuestionLog> page, @Param("vo") MyQuestionLogQueryVo MyQuestionLogQueryVo);

    List<MyQuestionLog> queryList(@Param("vo") MyQuestionLogQueryVo MyQuestionLogQueryVo);
}
