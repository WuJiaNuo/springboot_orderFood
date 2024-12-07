package edu.gdbtu.lzq.model.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础model实体类，所有实体类都继承于它
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic  //逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableField(value = "is_deleted",fill = FieldFill.INSERT)
    private Integer isDeleted;

    @ApiModelProperty(value = "版本号")
    @TableField(value = "version",fill = FieldFill.INSERT)
    @Version
    private Long version;

    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
}
