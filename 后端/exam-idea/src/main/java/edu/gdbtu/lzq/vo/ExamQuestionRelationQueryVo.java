package edu.gdbtu.lzq.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试题目关系表 vo类
 * @date 2023-09-20 15:15:19
 */
@Data
public class ExamQuestionRelationQueryVo {
    private String examId;
    private String questionId;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
}

