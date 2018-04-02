package com.aconst.eventsdatatest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstanceItemTest {
    private InstanceItem instanceItem;

    @Before
    public void setUp() throws Exception {
        instanceItem = new InstanceItem(1, 0, 60 * 60 * 1000,
                0, 60 * 60 * 1000);
    }

    @Test
    public void getIdTest() throws Exception {
        assertEquals(1, instanceItem.getEventId());
        assertNotEquals(2, instanceItem.getEventId());
    }

    @Test
    public void getStartTest() throws Exception {
        assertEquals(0, instanceItem.getStart());
        assertNotEquals(2, instanceItem.getStart());
    }

    @Test
    public void getEndTest() throws Exception {
        assertEquals(60 * 60 * 1000, instanceItem.getEnd());
        assertNotEquals(2000, instanceItem.getEnd());
    }

    @Test
    public void getStartMinuteTest() throws Exception {
        assertEquals(0, instanceItem.getStartMinute());
        assertNotEquals(1, instanceItem.getStartMinute());
    }

    @Test
    public void getEndMinuteTest() throws Exception {
        assertEquals(60 * 60 * 1000, instanceItem.getEndMinute());
        assertNotEquals(2000, instanceItem.getEndMinute());
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals("InstanceItem{"
                + "eventId=1"
                + ", start=0"
                + ", end=" + (60 * 60 * 1000)
                + ", startMinute=0"
                + ", endMinute=" + (60 * 60 * 1000)
                + "}", instanceItem.toString());
        assertNotEquals("", instanceItem.toString());
    }

}