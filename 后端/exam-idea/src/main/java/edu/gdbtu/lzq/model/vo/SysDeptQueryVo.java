package edu.gdbtu.lzq.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 部门查询vo
 * @date 2023/4/28 0:08
 */
@Data
public class SysDeptQueryVo {
    private String deptId;
    private List<String> curDeptIds;
}
