package edu.gdbtu.lzq.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tanlingfei
 * @version 1.0
 * @description TODO
 * @date 2023/9/17 16:13
 */
public class DateUtil {
    public static Date strToDateMM(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = format.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
