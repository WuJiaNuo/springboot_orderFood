package edu.gdbtu.lzq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.model.QuestionOption;
import edu.gdbtu.lzq.vo.QuestionOptionQueryVo;
import edu.gdbtu.lzq.mapper.QuestionOptionMapper;
import edu.gdbtu.lzq.service.QuestionOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import edu.gdbtu.lzq.system.exception.LanfException;
/**
* @author tanlingfei
* @version 1.0
* @description 题目选项 Service实现类
* @date 2023-09-25 21:23:12
*/
@Transactional
@Service
public class QuestionOptionServiceImpl extends ServiceImpl
<QuestionOptionMapper, QuestionOption> implements QuestionOptionService {
    @Autowired
    private QuestionOptionMapper QuestionOptionMapper;

    @Override
    public IPage<QuestionOption> selectPage(Page<QuestionOption> pageParam, QuestionOptionQueryVo QuestionOptionQueryVo) {
        return QuestionOptionMapper.selectPage(pageParam,QuestionOptionQueryVo);
    }

    @Override
    public List<QuestionOption> queryList(QuestionOptionQueryVo QuestionOptionQueryVo){
     List<QuestionOption> result = QuestionOptionMapper.queryList(QuestionOptionQueryVo);
     return result;
    }


    @Override
    public boolean save(QuestionOption QuestionOption){
        int result = this.QuestionOptionMapper.insert(QuestionOption);
        return result>0;
    }
    @Override
    public boolean updateById(QuestionOption QuestionOption){
        int row = this.QuestionOptionMapper.updateById(QuestionOption);
        if(row <= 0){
            throw new LanfException(ResultCodeEnum.UPLOAD_ERROR);
         }
        return row>0;
    }
    @Override
    public QuestionOption geyId(String id){
         QuestionOption QuestionOption = QuestionOptionMapper.selectById(id);
        return QuestionOption;
    }
   @Override
   public List<QuestionOption> geyIds(List<String> ids) {
      List<QuestionOption> list = this.QuestionOptionMapper.selectBatchIds(ids);
      return list;
   }
    /**
    @Override
    public void updateStatus(String id, Integer status) {
        QuestionOption QuestionOption = QuestionOptionMapper.selecyId(id);
        QuestionOption.setStatus(status);
        QuestionOptionMapper.updateById(QuestionOption);
    }**/
}
