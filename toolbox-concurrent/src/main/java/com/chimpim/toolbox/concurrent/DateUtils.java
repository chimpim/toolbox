package com.chimpim.toolbox.concurrent;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发的时间格式化处理工具
 */
public class DateUtils {

    private ConcurrentHashMap<String, ThreadLocal<DateFormat>> mDateFormats;

    private DateUtils() {
        this.mDateFormats = new ConcurrentHashMap<>();
    }


    private static class SingletonHolder {
        private static DateUtils INSTANCE = new DateUtils();
    }

    public static Date parse(@NotNull String source, @NotNull String format) throws ParseException {
        return SingletonHolder.INSTANCE.getDateFormat(format).parse(source);
    }

    public static String format(@NotNull Date date, @NotNull String format) {
        return SingletonHolder.INSTANCE.getDateFormat(format).format(date);
    }

    public static long getTicks(@NotNull Date date) {
        String format = format(date, "yyyy/MM/dd/HH/mm/ss");
        String[] ds = format.split("/");
        Calendar calStart = Calendar.getInstance();
        calStart.set(1, 1, 3, 0, 0, 0);
        Calendar calEnd = Calendar.getInstance();
        calEnd.set(
            /* year      */ Integer.parseInt(ds[0]),
            /* month     */ Integer.parseInt(ds[1]),
            /* date      */ Integer.parseInt(ds[2]),
            /* hourOfDay */ Integer.parseInt(ds[3]),
            /* minute    */ Integer.parseInt(ds[4]),
            /* second    */ Integer.parseInt(ds[5]));
        long epochStart = calStart.getTime().getTime();
        long epochEnd = calEnd.getTime().getTime();
        long all = epochEnd - epochStart;
        long ticks = all / 10_000_000_000L;
        return ticks;
    }

    @NotNull
    private DateFormat getDateFormat(@NotNull String format) {
        ThreadLocal<DateFormat> threadLocal = mDateFormats.get(format);
        if (threadLocal == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            threadLocal = new ThreadLocal<>();
            threadLocal.set(dateFormat);
            mDateFormats.put(format, threadLocal);
            return dateFormat;
        }
        DateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(format);
            threadLocal.set(dateFormat);
        }
        return dateFormat;
    }
}
