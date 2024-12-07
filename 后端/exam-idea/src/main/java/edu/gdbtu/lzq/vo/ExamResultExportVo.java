package edu.gdbtu.lzq.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试结果表 导出类
 * @date 2023-10-02 14:01:00
 */
@Data
@ApiModel(description = "用户考试结果表")
public class ExamResultExportVo {
    @ApiModelProperty(value = "考试id")
    @ExcelProperty("考试id")
    private String examId;
    @ApiModelProperty(value = "用户id")
    @ExcelProperty("用户id")
    private String userId;
    @ApiModelProperty(value = "得分")
    @ExcelProperty("得分")
    private Integer score;
}
