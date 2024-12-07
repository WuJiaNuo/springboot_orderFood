package edu.gdbtu.lzq.system.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
//用户导入Vo
@Data
@ApiModel(value = "导入实体")
public class UserExcelVo {
    @ColumnWidth(80)
    @ExcelProperty("用户名")
    @ApiModelProperty(value = "用户名")
    private String username;

    @ColumnWidth(80)
    @ExcelProperty("用户密码")
    @ApiModelProperty(value = "用户密码")
    private String password;

    @ColumnWidth(80)
    @ExcelProperty("用户姓名")
    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ExcelProperty("联系电话")
    @ApiModelProperty(value = "联系电话")
    private String phone;

    //无需导出字段使用此注解
    //@JsonSerialize(using = ToStringSerializer.class)

}
