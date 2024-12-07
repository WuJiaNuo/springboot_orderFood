package edu.gdbtu.lzq.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题目选项 vo类
 * @date 2023-09-25 21:23:12
 */
@Data
public class QuestionOptionQueryVo {
    private String questionId;
    private String options;
    private String name;
    private Integer isRight;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
}

