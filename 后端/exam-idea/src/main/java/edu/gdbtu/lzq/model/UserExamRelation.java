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
 * @description 用户考试关系表 po类
 * @date 2023-09-25 16:12:14
 */
@Data
@ApiModel(description = "用户考试关系表")
@TableName("user_exam_relation")
public class UserExamRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "考试id")
    @TableField("exam_id")
    private String examId;
    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;
    @ApiModelProperty(value = "0:未开始 1:进行中 2:已完成")
    @TableField("status")
    private Integer status;
}
