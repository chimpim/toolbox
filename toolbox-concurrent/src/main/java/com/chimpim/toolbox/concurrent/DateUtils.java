package com.chimpim.toolbox.concurrent;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        private static DateUtils instance = new DateUtils();
    }

    public static Date parse(@NotNull String source, @NotNull String format) throws ParseException {
        return SingletonHolder.instance.getDateFormat(format).parse(source);
    }

    public static String format(@NotNull Date date, @NotNull String format) {
        return SingletonHolder.instance.getDateFormat(format).format(date);
    }

    public static long toTicks(@NotNull Date date) {
        // 0001-01-03 00:00:00.000 的时间戳
        long epochStart = -62135625600000L;
        long epochEnd = date.getTime();
        long ticks = (epochEnd - epochStart) * 10_000;
        return ticks;
    }

    public static Date fromTicks(long ticks) {
        // 0001-01-03 00:00:00.000 的时间戳
        long epochStart = -62135625600000L;
        long epochEnd = (ticks / 10_000) + epochStart;
        Date date = new Date(epochEnd);
        return date;
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
