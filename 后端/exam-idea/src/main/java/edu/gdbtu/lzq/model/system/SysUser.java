package edu.gdbtu.lzq.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import edu.gdbtu.lzq.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

//用户实体类
@Data
@ApiModel(description = "用户")
@TableName("sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "新密码")
    @TableField(exist = false)
    private String newpassword;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "手机")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "头像地址")
    @TableField("head_url")
    private String headUrl;

    @ApiModelProperty(value = "部门id")
    @TableField("dept_id")
    private String deptId;

    @ApiModelProperty(value = "部门id集合")
    @TableField(exist = false)
    private List<String> deptIds;

    @ApiModelProperty(value = "岗位id")
    @TableField("post_id")
    private String postId;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "状态（1：正常 0：停用）")
    @TableField("status")
    private Integer status;

    @TableField(exist = false)
    private List<SysRole> roleList;
    //岗位
    @TableField(exist = false)
    private String postName;
    //部门
    @TableField(exist = false)
    private String deptName;
}

