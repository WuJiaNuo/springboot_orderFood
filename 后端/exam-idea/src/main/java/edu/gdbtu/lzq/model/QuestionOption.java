package edu.gdbtu.lzq.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题目选项 po类
 * @date 2023-09-25 21:23:12
 */
@Data
@ApiModel(description = "题目选项")
@TableName("question_option")
public class QuestionOption {
    private static final long serialVersionUID = 1L;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Integer isDeleted;
    @ApiModelProperty(value = "版本号")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Long version;
    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();
    @ApiModelProperty(value = "题目id")
    @TableField("question_id")
    private String questionId;
    @ApiModelProperty(value = "选项")
    @TableField("options")
    private String options;
    @ApiModelProperty(value = "选项内容")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "是否正确1:是 0:否")
    @TableField("is_right")
    private Integer isRight;
}
