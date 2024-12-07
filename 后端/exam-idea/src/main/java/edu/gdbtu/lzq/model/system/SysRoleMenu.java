package edu.gdbtu.lzq.model.system;

import edu.gdbtu.lzq.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
//角色菜单关系实体类
@Data
@ApiModel(description = "角色菜单")
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "角色id")
	@TableField("role_id")
	private String roleId;

	@ApiModelProperty(value = "菜单id")
	@TableField("menu_id")
	private String menuId;

}

