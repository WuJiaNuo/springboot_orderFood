package edu.gdbtu.lzq.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试答题记录 vo类
 * @date 2023-10-16 15:56:10
 */
@Data
public class MyQuestionLogQueryVo {
    private String examId;
    private String questionId;
    private String myOptions;
    private String rightOptions;
    private String myAnswer;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
    private String resultId;
    private String content;
    private String type;
}

