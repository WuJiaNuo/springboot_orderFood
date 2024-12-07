package edu.gdbtu.lzq.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 导出类
 * @date 2023-09-21 00:54:13
 */
@Data
@ApiModel(description = "题库表")
public class QuestionExportVo {
    @ApiModelProperty(value = "部门")
    @ExcelProperty("部门")
    private String deptName;
    @ApiModelProperty(value = "题目内容")
    @ExcelProperty("题目内容")
    private String content;
    @ApiModelProperty(value = "题目分数")
    @ExcelProperty("题目分数")
    private Integer score;
    @ExcelProperty("题目类型")
    private String typeName;
    @ApiModelProperty(value = "备注")
    @ExcelProperty("备注")
    private String remark;
}
