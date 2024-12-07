package edu.gdbtu.lzq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import edu.gdbtu.lzq.mapper.ExamResultMapper;
import edu.gdbtu.lzq.model.ExamResult;
import edu.gdbtu.lzq.service.ExamResultService;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.vo.ExamResultQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试结果表 Service实现类
 * @date 2023-10-02 14:01:00
 */
@Transactional
@Service
public class ExamResultServiceImpl extends ServiceImpl
        <ExamResultMapper, ExamResult> implements ExamResultService {
    @Autowired
    private ExamResultMapper ExamResultMapper;

    @Override
    public IPage<ExamResult> selectPage(Page<ExamResult> pageParam, ExamResultQueryVo ExamResultQueryVo) {
        return ExamResultMapper.selectPage(pageParam, ExamResultQueryVo);
    }

    @Override
    public List<ExamResult> queryList(ExamResultQueryVo ExamResultQueryVo) {
        List<ExamResult> result = ExamResultMapper.queryList(ExamResultQueryVo);
        return result;
    }


    @Override
    public boolean save(ExamResult ExamResult) {
        int result = this.ExamResultMapper.insert(ExamResult);
        return result > 0;
    }

    @Override
    public boolean updateById(ExamResult ExamResult) {
        int row = this.ExamResultMapper.updateById(ExamResult);
        if (row <= 0) {
            throw new LanfException(ResultCodeEnum.UPLOAD_ERROR);
        }
        return row > 0;
    }

    @Override
    public ExamResult geyId(String id) {
        ExamResult ExamResult = ExamResultMapper.selectById(id);
        return ExamResult;
    }

    @Override
    public List<ExamResult> geyIds(List<String> ids) {
        List<ExamResult> list = this.ExamResultMapper.selectBatchIds(ids);
        return list;
    }
    /**
     @Override public void updateStatus(String id, Integer status) {
     ExamResult ExamResult = ExamResultMapper.selecyId(id);
     ExamResult.setStatus(status);
     ExamResultMapper.updateById(ExamResult);
     }**/
}
