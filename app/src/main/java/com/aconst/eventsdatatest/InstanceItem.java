package com.aconst.eventsdatatest;

public class InstanceItem {
    private int eventId;
    private long start;
    private long end;
    private int startMinute;
    private int endMinute;

    public InstanceItem(int eventId, long start, long end, int startMinute, int endMinute) {
        this.eventId = eventId;
        this.start = start;
        this.end = end;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
    }

    public int getEventId() {
        return eventId;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    @Override
    public String toString() {
        return "InstanceItem{" +
                "eventId=" + eventId +
                ", start=" + start +
                ", end=" + end +
                ", startMinute=" + startMinute +
                ", endMinute=" + endMinute +
                '}';
    }
}
