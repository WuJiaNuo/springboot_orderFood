package edu.gdbtu.lzq.model.vo;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
//登陆日志查询VO
@Data
public class SysLoginLogQueryVo {

	@ApiModelProperty(value = "用户账号")
	private String username;

	private String createTimeBegin;
	private String createTimeEnd;

}

