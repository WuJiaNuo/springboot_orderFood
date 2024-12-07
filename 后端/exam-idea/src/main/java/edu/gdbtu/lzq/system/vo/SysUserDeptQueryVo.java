package edu.gdbtu.lzq.system.vo;

import lombok.Data;
import java.util.Date;

/**
* @author tanlingfei
* @version 1.0
* @description 用户和部门中间表 vo类
* @date 2020-04-20 13:13:24
*/
@Data
public class SysUserDeptQueryVo {

        private String userId;
        private String deptId;
       private Date createTimeBegin;
       private Date createTimeEnd;
       private Date updateTimeBegin;
       private Date updateTimeEnd;
}

