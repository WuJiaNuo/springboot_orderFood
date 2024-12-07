package edu.gdbtu.lzq.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试试卷表 vo类
 * @date 2023-09-21 00:57:32
 */
@Data
public class ExamQueryVo {
    private String title;
    private Integer duration;
    private Integer criteria;
    private String remark;
    private String deptId;
    private List<String> curDeptIds;
    private java.util.Date startTime;
    private java.util.Date endTime;
    private String questionId;
    private String deptName;
    private Date createTimeBegin;
    private Date createTimeEnd;
    private Date updateTimeBegin;
    private Date updateTimeEnd;
    private String curUserId;
}

