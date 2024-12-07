package edu.gdbtu.lzq.vo;

import edu.gdbtu.lzq.model.Question;
import lombok.Data;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试 vo类
 * @date 2023-09-21 00:57:32
 */
@Data
public class ExamVo {
    //单选题目信息集合
    private List<Question> sigList;
    //多选题目信息集合
    private List<Question> mulList;
    //判断题目信息集合
    private List<Question> pdList;
    //问答题目信息集合
    private List<Question> wdList;
    //考试id
    private String examId;
    //考试结果id
    private String resultId;
}

