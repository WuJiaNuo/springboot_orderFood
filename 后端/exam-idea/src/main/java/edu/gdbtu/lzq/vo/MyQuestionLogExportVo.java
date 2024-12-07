package edu.gdbtu.lzq.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试答题记录 导出类
 * @date 2023-10-16 15:56:10
 */
@Data
@ApiModel(description = "考试答题记录")
public class MyQuestionLogExportVo {
    @ApiModelProperty(value = "考试id")
    @ExcelProperty("考试id")
    private String examId;
    @ApiModelProperty(value = "题目id")
    @ExcelProperty("题目id")
    private String questionId;
    @ApiModelProperty(value = "我的选项")
    @ExcelProperty("我的选项")
    private String myOptions;
    @ApiModelProperty(value = "正确选项")
    @ExcelProperty("正确选项")
    private String rightOptions;
    @ApiModelProperty(value = "我的回答")
    @ExcelProperty("我的回答")
    private String myAnswer;
}
