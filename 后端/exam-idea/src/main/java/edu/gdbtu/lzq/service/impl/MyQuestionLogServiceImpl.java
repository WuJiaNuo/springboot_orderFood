package edu.gdbtu.lzq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.model.MyQuestionLog;
import edu.gdbtu.lzq.vo.MyQuestionLogQueryVo;
import edu.gdbtu.lzq.mapper.MyQuestionLogMapper;
import edu.gdbtu.lzq.service.MyQuestionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import edu.gdbtu.lzq.system.exception.LanfException;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试答题记录 Service实现类
 * @date 2023-10-16 15:56:10
 */
@Transactional
@Service
public class MyQuestionLogServiceImpl extends ServiceImpl
        <MyQuestionLogMapper, MyQuestionLog> implements MyQuestionLogService {
    @Autowired
    private MyQuestionLogMapper MyQuestionLogMapper;

    @Override
    public IPage<MyQuestionLog> selectPage(Page<MyQuestionLog> pageParam, MyQuestionLogQueryVo MyQuestionLogQueryVo) {
        return MyQuestionLogMapper.selectPage(pageParam, MyQuestionLogQueryVo);
    }

    @Override
    public List<MyQuestionLog> queryList(MyQuestionLogQueryVo MyQuestionLogQueryVo) {
        List<MyQuestionLog> result = MyQuestionLogMapper.queryList(MyQuestionLogQueryVo);
        return result;
    }


    @Override
    public boolean save(MyQuestionLog MyQuestionLog) {
        int result = this.MyQuestionLogMapper.insert(MyQuestionLog);
        return result > 0;
    }

    @Override
    public boolean updateById(MyQuestionLog MyQuestionLog) {
        int row = this.MyQuestionLogMapper.updateById(MyQuestionLog);
        if (row <= 0) {
            throw new LanfException(ResultCodeEnum.UPDATE_ERROR);
        }
        return row > 0;
    }

    @Override
    public MyQuestionLog geyId(String id) {
        MyQuestionLog MyQuestionLog = MyQuestionLogMapper.selectById(id);
        return MyQuestionLog;
    }

    @Override
    public List<MyQuestionLog> geyIds(List<String> ids) {
        List<MyQuestionLog> list = this.MyQuestionLogMapper.selectBatchIds(ids);
        return list;
    }
    /**
     @Override public void updateStatus(String id, Integer status) {
     MyQuestionLog MyQuestionLog = MyQuestionLogMapper.selecyId(id);
     MyQuestionLog.setStatus(status);
     MyQuestionLogMapper.updateById(MyQuestionLog);
     }**/
}
