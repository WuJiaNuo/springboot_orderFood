package edu.gdbtu.lzq.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 vo类
 * @date 2023-09-21 00:54:13
 */
@Data
public class QuestionQueryVo {
    private String content;
    private Integer score;
    private String remark;
    private String type;
    private String typeName;
    private String deptId;
    private List<String> curDeptIds;
    private String examId;
    private String deptName;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
    //是否随机查询题目
    private int isRand;
    //随机查询题目数量
    private int getNum;
    //考试结果id
    private String resultId;
}

