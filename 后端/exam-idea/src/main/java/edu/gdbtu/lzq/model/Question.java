package edu.gdbtu.lzq.model;

import edu.gdbtu.lzq.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 po类
 * @date 2023-09-21 00:54:13
 */
@Data
@ApiModel(description = "题库表")
@TableName("question")
public class Question extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "题目内容")
    @TableField("content")
    private String content;
    @ApiModelProperty(value = "题目答案")
    @TableField("answer")
    private String answer;
    @ApiModelProperty(value = "题目分数")
    @TableField("score")
    private Integer score;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "题目类型")
    @TableField("type")
    private String type;
    @TableField(exist = false)
    private String typeName;
    @ApiModelProperty(value = "部门")
    @TableField("dept_id")
    private String deptId;
    @ApiModelProperty(value = "对应试卷列表")
    @TableField(exist = false)
    private List<String> examIdList;
    @TableField(exist = false)
    private String deptName;
    @ApiModelProperty(value = "对应选项列表")
    @TableField(exist = false)
    private List<QuestionOption> optionList;
    @ApiModelProperty(value = "我的选择列表")
    @TableField(exist = false)
    private List<String> myCheckList = new ArrayList<>();
    @ApiModelProperty(value = "我的选择")
    @TableField(exist = false)
    private String myCheck;
    @ApiModelProperty(value = "我的回答")
    @TableField(exist = false)
    private String myAnswer;
}
