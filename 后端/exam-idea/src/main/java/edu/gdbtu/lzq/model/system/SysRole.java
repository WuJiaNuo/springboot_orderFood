package edu.gdbtu.lzq.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import edu.gdbtu.lzq.model.base.BaseEntity;
import lombok.Data;

//角色实体类
@Data
@TableName("sys_role")
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableField("role_name")
	private String roleName;

	@TableField("role_code")
	private String roleCode;

	@TableField("description")
	private String description;

}

