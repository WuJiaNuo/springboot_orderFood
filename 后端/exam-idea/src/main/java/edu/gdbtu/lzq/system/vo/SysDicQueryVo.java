package edu.gdbtu.lzq.system.vo;

import lombok.Data;
import java.util.Date;

/**
* @author tanlingfei
* @version 1.0
* @description 字典分类 vo类
* @date 2020-04-13 09:55:26
*/
@Data
public class SysDicQueryVo {

        private String name;
        private String code;
        private Date createTimeBegin;
        private Date createTimeEnd;
        private Date updateTimeBegin;
        private Date updateTimeEnd;

}

