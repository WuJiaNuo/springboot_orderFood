package edu.gdbtu.lzq.system.model;

import edu.gdbtu.lzq.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @author tanlingfei
* @version 1.0
* @description 用户和部门中间表 po类
* @date 2020-04-20 13:13:24
*/
@Data
@ApiModel(description = "用户和部门中间表")
@TableName("sys_user_dept")
public class SysUserDept extends BaseEntity {

private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "")
        @TableField("user_id")
        private String userId;
        @ApiModelProperty(value = "")
        @TableField("dept_id")
        private String deptId;
}
