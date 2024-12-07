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
* @description 岗位信息表 po类
* @date 2023-04-30 12:37:35
*/
@Data
@ApiModel(description = "岗位信息表")
@TableName("sys_post")
public class SysPost extends BaseEntity {

private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "岗位编码")
        @TableField("post_code")
        private String postCode;
        @ApiModelProperty(value = "岗位名称")
        @TableField("name")
        private String name;
        @ApiModelProperty(value = "描述")
        @TableField("description")
        private String description;
        @ApiModelProperty(value = "状态（1正常 0停用）")
        @TableField("status")
        private String status;
        @TableField(exist = false)
        private String statusName;
}
