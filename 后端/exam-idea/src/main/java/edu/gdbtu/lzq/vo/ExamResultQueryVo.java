package edu.gdbtu.lzq.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试结果表 vo类
 * @date 2023-10-02 14:01:00
 */
@Data
public class ExamResultQueryVo {
    private String examId;
    private String userId;
    private Integer score;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
    private String examTitle;
}

