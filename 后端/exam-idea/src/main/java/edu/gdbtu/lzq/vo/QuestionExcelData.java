package edu.gdbtu.lzq.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 导入类
 * @date 2023-09-21 00:54:13
 */
@Data
@ApiModel(description = "题库表")
public class QuestionExcelData {
    private String deptName;
    private String content;
    private Integer score;
    private String typeName;
    private String remark;
    private List<QuestionOptionExcelData> optionList = new ArrayList<>();
}
