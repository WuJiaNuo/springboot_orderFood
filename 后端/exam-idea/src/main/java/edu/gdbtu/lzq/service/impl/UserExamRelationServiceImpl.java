package edu.gdbtu.lzq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdbtu.lzq.common.result.ResultCodeEnum;
import edu.gdbtu.lzq.mapper.UserExamRelationMapper;
import edu.gdbtu.lzq.model.UserExamRelation;
import edu.gdbtu.lzq.service.UserExamRelationService;
import edu.gdbtu.lzq.system.exception.LanfException;
import edu.gdbtu.lzq.vo.UserExamRelationQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试关系表 Service实现类
 * @date 2023-09-25 16:12:14
 */
@Transactional
@Service
public class UserExamRelationServiceImpl extends ServiceImpl
        <UserExamRelationMapper, UserExamRelation> implements UserExamRelationService {
    @Autowired
    private UserExamRelationMapper UserExamRelationMapper;

    @Override
    public IPage<UserExamRelation> selectPage(Page<UserExamRelation> pageParam, UserExamRelationQueryVo UserExamRelationQueryVo) {
        return UserExamRelationMapper.selectPage(pageParam, UserExamRelationQueryVo);
    }

    @Override
    public List<UserExamRelation> queryList(UserExamRelationQueryVo UserExamRelationQueryVo) {
        List<UserExamRelation> result = UserExamRelationMapper.queryList(UserExamRelationQueryVo);
        return result;
    }


    @Override
    public boolean save(UserExamRelation UserExamRelation) {
        int result = this.UserExamRelationMapper.insert(UserExamRelation);
        return result > 0;
    }

    @Override
    public boolean updateById(UserExamRelation UserExamRelation) {
        int row = this.UserExamRelationMapper.updateById(UserExamRelation);
        if (row <= 0) {
            throw new LanfException(ResultCodeEnum.UPLOAD_ERROR);
        }
        return row > 0;
    }

    @Override
    public UserExamRelation geyId(String id) {
        UserExamRelation UserExamRelation = UserExamRelationMapper.selectById(id);
        return UserExamRelation;
    }

    @Override
    public List<UserExamRelation> geyIds(List<String> ids) {
        List<UserExamRelation> list = this.UserExamRelationMapper.selectBatchIds(ids);
        return list;
    }
    /**
     @Override public void updateStatus(String id, Integer status) {
     UserExamRelation UserExamRelation = UserExamRelationMapper.selecyId(id);
     UserExamRelation.setStatus(status);
     UserExamRelationMapper.updateById(UserExamRelation);
     }**/
}
