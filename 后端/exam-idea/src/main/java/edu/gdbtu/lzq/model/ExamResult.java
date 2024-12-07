package edu.gdbtu.lzq.model;

import edu.gdbtu.lzq.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试结果表 po类
 * @date 2023-10-02 14:01:00
 */
@Data
@ApiModel(description = "用户考试结果表")
@TableName("exam_result")
public class ExamResult extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "考试id")
    @TableField("exam_id")
    private String examId;
    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;
    @ApiModelProperty(value = "得分")
    @TableField("score")
    private Integer score;
    @ApiModelProperty(value = "总分")
    @TableField("sum_score")
    private Integer sumScore;
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String examTitle;
}
