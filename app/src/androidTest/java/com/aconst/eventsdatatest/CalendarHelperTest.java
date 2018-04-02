package com.aconst.eventsdatatest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CalendarHelperTest {
    @Test
    public void getDiffFromGMTTestOK() {
        assertEquals(0, CalendarHelper.getDiffFromGMT(TimeZone.getTimeZone("UTC")));
        assertEquals(3 * 60 * 60 * 1000, CalendarHelper.getDiffFromGMT(TimeZone.getTimeZone("GMT+3")));
    }

    @Test
    public void getDiffFromGMTTestError() {
        assertNotEquals(1, CalendarHelper.getDiffFromGMT(TimeZone.getTimeZone("UTC")));
    }

    @Test
    public void getDateFromTestOK() {
        assertEquals(1519862400000L, CalendarHelper.getDateFrom(2018, 2));
    }

    @Test
    public void getDateFromTestError() {
        assertNotEquals(1519862400001L, CalendarHelper.getDateFrom(2018, 2));
    }

    @Test
    public void getDateToTestOK() {
        assertEquals(1514764800000L, CalendarHelper.getDateTo(2017, 11));
        assertEquals(1522540800000L, CalendarHelper.getDateTo(2018, 2));
    }

    @Test
    public void getDateToTestError() {
        assertNotEquals(1522540800001L, CalendarHelper.getDateTo(2018, 2));
    }

    @Test
    public void clearTimeTestOK() {
        assertEquals(1519851600000L,
                CalendarHelper.clearTime(1519937999000L));
        assertEquals(1519851600000L,
                CalendarHelper.clearTime(1519862400000L));
    }

    @Test
    public void clearTimeTestError() {
        assertNotEquals(1519862400000L,
                CalendarHelper.clearTime(1519948800000L));
    }

    @Test
    public void isSameDayTestOK() {
        assertEquals(true,
                CalendarHelper.isSameDay(1519851600000L, 1519937999000L));
    }

    @Test
    public void isSameDayTestError() {
        assertNotEquals(true,
                CalendarHelper.isSameDay(1519851599999L, 1519937999000L));
    }

    @Test
    public void convertLongToDateTestOK() {
        long lDate = 1519862400000L;
        Date date = new Date(lDate);
        assertEquals(date, CalendarHelper.convertLongToDate(lDate));
    }

    @Test
    public void convertLongToDateTestError() {
        long lDate = 1519862400000L;
        Date date = new Date(lDate - CalendarHelper.MSEC_OF_DAY);
        assertNotEquals(date, CalendarHelper.convertLongToDate(lDate));
    }

    @Test
    public void getWeekNumTestOK() {
        assertEquals(10, CalendarHelper.getWeekNum(2016, 2, 1));
        assertEquals(52, CalendarHelper.getWeekNum(2016, 11, 25));
        // Surprise!
        assertEquals(1, CalendarHelper.getWeekNum(2016, 11, 26));
        assertEquals(53, CalendarHelper.getWeekNum(2017, 11, 31));
        assertEquals(1, CalendarHelper.getWeekNum(2018, 0, 1));
        assertEquals(2, CalendarHelper.getWeekNum(2018, 0, 8));
        // Surprise again...
        assertEquals(1, CalendarHelper.getWeekNum(2018, 11, 31));
    }

    @Test
    public void getWeekNumTestError() {
        assertNotEquals(2, CalendarHelper.getWeekNum(2018, 0, 1));
    }

}