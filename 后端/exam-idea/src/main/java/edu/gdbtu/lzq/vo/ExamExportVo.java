package edu.gdbtu.lzq.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 考试试卷表 导出类
 * @date 2023-09-21 00:57:32
 */
@Data
@ApiModel(description = "考试试卷表")
public class ExamExportVo {
    @ApiModelProperty(value = "部门")
    @ExcelProperty("部门")
    private String deptName;
    @ApiModelProperty(value = "标题")
    @ExcelProperty("标题")
    private String title;
    @ApiModelProperty(value = "时长")
    @ExcelProperty("时长")
    private Integer duration;
    @ApiModelProperty(value = "及格标准")
    @ExcelProperty("及格标准")
    private Integer criteria;
    @ApiModelProperty(value = "开始时间")
    @ExcelProperty("开始时间")
    private java.util.Date startTime;
    @ApiModelProperty(value = "结束时间")
    @ExcelProperty("结束时间")
    private java.util.Date endTime;
    @ApiModelProperty(value = "备注")
    @ExcelProperty("备注")
    private String remark;

}
