package edu.gdbtu.lzq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdbtu.lzq.model.UserExamRelation;
import edu.gdbtu.lzq.vo.UserExamRelationQueryVo;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试关系表 Service接口
 * @date 2023-09-25 16:12:14
 */
public interface UserExamRelationService extends IService<UserExamRelation> {
    IPage<UserExamRelation> selectPage(Page<UserExamRelation> pageParam, UserExamRelationQueryVo queryVo);

    List<UserExamRelation> queryList(UserExamRelationQueryVo queryVo);

    public boolean save(UserExamRelation UserExamRelation);

    public boolean updateById(UserExamRelation UserExamRelation);

    public UserExamRelation geyId(String id);

    public List<UserExamRelation> geyIds(List<String> ids);
}
