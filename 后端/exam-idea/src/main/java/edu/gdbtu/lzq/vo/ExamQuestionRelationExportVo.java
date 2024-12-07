package edu.gdbtu.lzq.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试题目关系表 导出类
 * @date 2023-09-20 15:15:19
 */
@Data
@ApiModel(description = "考试题目关系表")
public class ExamQuestionRelationExportVo {
    @ApiModelProperty(value = "考试id")
    @ExcelProperty("考试id")
    private String examId;
    @ApiModelProperty(value = "题目id")
    @ExcelProperty("题目id")
    private String questionId;
}
