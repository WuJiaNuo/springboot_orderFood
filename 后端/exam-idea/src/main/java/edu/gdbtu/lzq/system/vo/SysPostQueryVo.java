package edu.gdbtu.lzq.system.vo;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
* @author tanlingfei
* @version 1.0
* @description 岗位信息表 vo类
* @date 2023-04-30 12:37:35
*/
@Data
public class SysPostQueryVo {
       private String postCode;
       private String name;
       private String description;
       private String status;
       private String statusName;
       private Date createTimeBegin;
       private Date createTimeEnd;
       private Date updateTimeBegin;
       private Date updateTimeEnd;
}

