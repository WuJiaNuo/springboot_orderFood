package edu.gdbtu.lzq.system.easyexcel;

import lombok.Getter;

@Getter
public enum ExcelTemplateEnum {
    /**单sheet导出*/
    TEMPLATE_1("1","complex"),

    /**模板格式*/
    TEMPLATE_SUFFIX("xlsx",".xlsx"),
    TEMPLATE_SUFFIX_XLS("xls",".xls"),
    TEMPLATE_SUFFIX_DOCX("docx",".docx"),
    /**模板路径*/
    TEMPLATE_PATH("path","excel"),
    ;

    private final String code;
    private final String desc;

    ExcelTemplateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过code获取msg
     *
     * @param code 枚举值
     * @return
     */
    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        }
        ExcelTemplateEnum enumList = getByCode(code);
        if (enumList == null) {
            return null;
        }
        return enumList.getDesc();
    }

    public static String getCode(ExcelTemplateEnum enumList) {
        if (enumList == null) {
            return null;
        }
        return enumList.getCode();
    }

    public static ExcelTemplateEnum getByCode(String code) {
        for (ExcelTemplateEnum enumList : values()) {
            if (enumList.getCode().equals(code)) {
                return enumList;
            }
        }
        return null;
    }
}

