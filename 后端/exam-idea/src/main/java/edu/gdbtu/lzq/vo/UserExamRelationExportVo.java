package edu.gdbtu.lzq.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 用户考试关系表 导出类
 * @date 2023-09-25 16:12:14
 */
@Data
@ApiModel(description = "用户考试关系表")
public class UserExamRelationExportVo {
    @ApiModelProperty(value = "考试id")
    @ExcelProperty("考试id")
    private String examId;
    @ApiModelProperty(value = "用户id")
    @ExcelProperty("用户id")
    private String userId;
    @ApiModelProperty(value = "0:未开始 1:进行中 2:已完成")
    @ExcelProperty("0:未开始 1:进行中 2:已完成")
    private Integer status;
}
