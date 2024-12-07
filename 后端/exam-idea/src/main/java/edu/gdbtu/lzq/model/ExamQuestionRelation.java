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
 * @description 考试题目关系表 po类
 * @date 2023-09-20 15:15:19
 */
@Data
@ApiModel(description = "考试题目关系表")
@TableName("exam_question_relation")
public class ExamQuestionRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "考试id")
    @TableField("exam_id")
    private String examId;
    @ApiModelProperty(value = "题目id")
    @TableField("question_id")
    private String questionId;
}
