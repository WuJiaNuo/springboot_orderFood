package edu.gdbtu.lzq.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 导入类
 * @date 2023-09-21 00:54:13
 */
@Data
@ApiModel(description = "题库表")
public class QuestionExcelVo {
    @ExcelProperty("部门")
    private String deptName;
    @ExcelProperty("题目内容")
    private String content;
    @ExcelProperty("题目分数")
    private Integer score;
    @ExcelProperty("题目类型")
    private String typeName;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("选项")
    private String optionName;
    @ExcelProperty("选项内容")
    private String optionVal;
    @ExcelProperty("是否正确")
    private String isRight;
}
