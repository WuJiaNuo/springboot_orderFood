package edu.gdbtu.lzq.model.vo;

import lombok.Data;
//操作日志查询Vo
@Data
public class SysOperLogQueryVo {

	private String title;
	private String operName;

	private String createTimeBegin;
	private String createTimeEnd;

}

