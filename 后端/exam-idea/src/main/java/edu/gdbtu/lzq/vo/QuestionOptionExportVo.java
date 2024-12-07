package edu.gdbtu.lzq.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题目选项 导出类
 * @date 2023-09-25 21:23:12
 */
@Data
@ApiModel(description = "题目选项")
public class QuestionOptionExportVo {
    @ApiModelProperty(value = "题目id")
    @ExcelProperty("题目id")
    private String questionId;
    @ApiModelProperty(value = "选项")
    @ExcelProperty("选项")
    private String option;
    @ApiModelProperty(value = "选项内容")
    @ExcelProperty("选项内容")
    private String name;
    @ApiModelProperty(value = "是否正确1:是 0:否")
    @ExcelProperty("是否正确1:是 0:否")
    private Integer isRight;
}
