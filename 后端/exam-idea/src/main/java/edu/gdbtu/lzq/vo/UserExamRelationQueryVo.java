package edu.gdbtu.lzq.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试关系表 vo类
 * @date 2023-09-25 16:12:14
 */
@Data
public class UserExamRelationQueryVo {
    private String examId;
    private String userId;
    private Integer status;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
}

