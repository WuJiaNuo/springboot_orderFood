package edu.gdbtu.lzq.system.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
//用户导出Vo
@Data
@ApiModel(value = "导出实体")
public class UserExportVo {
    @ExcelProperty("用户名")
    @ApiModelProperty(value = "用户名")
    private String username;


    @ExcelProperty("用户姓名")
    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ExcelProperty("联系电话")
    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ExcelProperty("部门名称")
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ExcelProperty("岗位名称")
    @ApiModelProperty(value = "岗位名称")
    private String postName;

    //无需导出字段使用此注解
    //@JsonSerialize(using = ToStringSerializer.class)

}
