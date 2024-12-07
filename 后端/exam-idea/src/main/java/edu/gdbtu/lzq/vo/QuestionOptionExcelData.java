package edu.gdbtu.lzq.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 题库表 导入类
 * @date 2023-09-21 00:54:13
 */
@Data
@ApiModel(description = "题库选项表")
public class QuestionOptionExcelData {
    private String optionName;
    private String optionVal;
    private String isRight;
}
