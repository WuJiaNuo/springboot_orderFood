package edu.gdbtu.lzq.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.gdbtu.lzq.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试试卷表 po类
 * @date 2023-09-21 00:57:32
 */
@Data
@ApiModel(description = "考试试卷表")
@TableName("exam")
public class Exam extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;
    @ApiModelProperty(value = "试卷类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "试卷类型")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "选题方式")
    @TableField("get_type")
    private String getType;

    @ApiModelProperty(value = "随机抽题个数")
    @TableField("get_num")
    private int getNum;

    @ApiModelProperty(value = "选题方式")
    @TableField(exist = false)
    private String getTypeName;

    @ApiModelProperty(value = "时长")
    @TableField("duration")
    private Integer duration;
    @ApiModelProperty(value = "及格标准")
    @TableField("criteria")
    private Integer criteria;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "部门")
    @TableField("dept_id")
    private String deptId;
    @ApiModelProperty(value = "开始时间")
    @TableField("start_time")
    private java.util.Date startTime;
    @ApiModelProperty(value = "结束时间")
    @TableField("end_time")
    private java.util.Date endTime;
    @TableField(exist = false)
    private List<String> questionIdList;
    @TableField(exist = false)
    private String deptName;
    @TableField(exist = false)
    private List<String> userIdList;
    @TableField(exist = false)
    private String examSta;
}
