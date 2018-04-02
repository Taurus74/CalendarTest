package com.aconst.eventsdatatest;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarHelper {
    public static final long MSEC_OF_DAY = 86400000L;

    public static int getDiffFromGMT() {
        return getDiffFromGMT(TimeZone.getDefault());
    }

    public static int getDiffFromGMT(TimeZone tz) {
        return tz.getOffset(new Date().getTime());
    }

    // Преобразовать дату из long в Date
    public static Date convertLongToDate(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.getTime();
    }

    // Проверить, принадлежат ли даты к одному дню
    public static boolean isSameDay(long lDate, long compareDate) {
        return clearTime(lDate) == clearTime(compareDate);
    }

    // Привести дату к началу суток
    public static long clearTime(long timeInMillis) {
        long diff = getDiffFromGMT();
        return (timeInMillis + diff) / MSEC_OF_DAY * MSEC_OF_DAY - diff;
    }

    public static long getDateFrom(int mYear, int mMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() + getDiffFromGMT();
    }

    public static long getDateTo(int mYear, int mMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set((mMonth == 11? mYear + 1: mYear),
                (mMonth == 11? 0: mMonth + 1),
                1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() + getDiffFromGMT();
    }

    public static int getWeekNum(int mYear, int mMonth, int mDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDay, 0, 0, 0);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

}
