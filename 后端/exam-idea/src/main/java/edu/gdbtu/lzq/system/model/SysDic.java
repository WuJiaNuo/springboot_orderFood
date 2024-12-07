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
* @description 字典分类 po类
* @date 2020-04-13 09:55:26
*/
@Data
@ApiModel(description = "字典分类")
@TableName("sys_dic")
public class SysDic extends BaseEntity {

private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "名称")
        @TableField("name")
        private String name;
        @ApiModelProperty(value = "编码")
        @TableField("code")
        private String code;
}
