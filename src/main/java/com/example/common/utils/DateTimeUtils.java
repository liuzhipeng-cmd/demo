package com.example.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateTimeUtils {

    /**
     * 获取时间戳
     *
     * @return
     */
    public Long getTimeStamp() {
        long time_stamp = 0;
        long time = new Date().getTime();
        time_stamp = time / 1000;
        return time_stamp;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public String getDateToString(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String obj = f.format(date);
        return obj;
    }

    /**
     * 字符串转日期
     *
     * @param date
     * @return
     */
    public Date getStringToDate(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date obj = f.parse(date);
            return obj;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取年
     *
     * @param date
     * @return
     */
    public String getYear(String date) {
        String year = "";
        SimpleDateFormat f = new SimpleDateFormat("yyyy");
        try {
            if (!"".equals(date) && null != date) {
                year = f.format(f.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year;
    }

    /**
     * 获取年月
     *
     * @param date
     * @return
     */
    public String getYearMonth(String date) {
        String yearMonth = "";
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        try {
            if (!"".equals(date) && null != date) {
                yearMonth = f.format(f.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return yearMonth;
    }

    /**
     * 获取当前时间（日期+时分秒）
     *
     * @return
     */
    public String getYearMonthDayHourMinuteSecond() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String yearMonthDayHourMinuteSecond = f.format(new Date());
        return yearMonthDayHourMinuteSecond;
    }
}
