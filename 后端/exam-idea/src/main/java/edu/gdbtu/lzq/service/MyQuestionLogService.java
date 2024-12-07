package edu.gdbtu.lzq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.MyQuestionLog;
import edu.gdbtu.lzq.vo.MyQuestionLogQueryVo;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试答题记录 Service接口
 * @date 2023-10-16 15:56:10
 */
public interface MyQuestionLogService extends IService<MyQuestionLog> {
    IPage<MyQuestionLog> selectPage(Page<MyQuestionLog> pageParam, MyQuestionLogQueryVo queryVo);

    List<MyQuestionLog> queryList(MyQuestionLogQueryVo queryVo);

    public boolean save(MyQuestionLog MyQuestionLog);

    public boolean updateById(MyQuestionLog MyQuestionLog);

    public MyQuestionLog geyId(String id);

    public List<MyQuestionLog> geyIds(List<String> ids);
}
