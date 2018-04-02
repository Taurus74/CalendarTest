package com.aconst.eventsdatatest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventItemTest {
    private EventItem item;

    @Before
    public void setUp() {
        item = new EventItem(1, "test", 0, 60 * 60 * 1000, false);
    }

    @Test
    public void getIdTest() throws Exception {
        assertEquals(1, item.getId());
        assertNotEquals(2, item.getId());
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("test", item.getName());
        assertNotEquals("Test", item.getName());
    }

    @Test
    public void getDtStartTest() throws Exception {
        assertEquals(0, item.getDtStart());
        assertNotEquals(1, item.getDtStart());
    }

    @Test
    public void getDtEndTest() throws Exception {
        assertEquals(60 * 60 * 1000, item.getDtEnd());
        assertNotEquals(1000, item.getDtEnd());
    }

    @Test
    public void isAllDayTest() throws Exception {
        assertEquals(false, item.isAllDay());
        assertNotEquals(true, item.isAllDay());
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals("EventItem{"
                + "id=1"
                + ", name='test'"
                + ", dtStart=0"
                + ", dtEnd=3600000"
                + ", allDay=false}", item.toString());
        assertNotEquals("", item.toString());
    }

}