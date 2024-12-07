package edu.gdbtu.lzq.model.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 树形操作辅助类
 * @date 2020/3/27 10:49
 */
@Data
public class TreeEntity extends  BaseEntity {
    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "上级分类id")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    private Integer sortValue;

    @ApiModelProperty(value = "完整路径")
    @TableField("tree_path")
    private String treePath;

    @ApiModelProperty(value = "级别")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "下级所有分类")
    @TableField(exist = false)
    private List<TreeEntity> children;
}
