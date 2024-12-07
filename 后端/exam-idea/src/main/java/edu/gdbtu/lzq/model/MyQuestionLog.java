package edu.gdbtu.lzq.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.gdbtu.lzq.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试答题记录 po类
 * @date 2023-10-16 15:56:10
 */
@Data
@ApiModel(description = "考试答题记录")
@TableName("my_question_log")
public class MyQuestionLog extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "考试id")
    @TableField("exam_id")
    private String examId;
    @ApiModelProperty(value = "题目id")
    @TableField("question_id")
    private String questionId;
    @ApiModelProperty(value = "我的选项")
    @TableField("my_options")
    private String myOptions;
    @ApiModelProperty(value = "正确选项")
    @TableField("right_options")
    private String rightOptions;
    @ApiModelProperty(value = "我的回答")
    @TableField("my_answer")
    private String myAnswer;
    @ApiModelProperty(value = "是否正确")
    @TableField("is_right")
    private int isRight;

    @ApiModelProperty(value = "是否正确")
    @TableField(exist = false)
    private String isRightText;

    @ApiModelProperty(value = "考试结果记录id")
    @TableField("result_id")
    private String resultId;
    @ApiModelProperty(value = "所有选项")
    @TableField(exist = false)
    private String allOption;

    @ApiModelProperty(value = "题目类型")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "题目内容")
    @TableField(exist = false)
    private String content;

    @ApiModelProperty(value = "题目分数")
    @TableField(exist = false)
    private int score;
}
