package com.aconst.eventsdatatest;

class EventItem {
    private int id;
    private String name;
    private long dtStart;
    private long dtEnd;
    private boolean allDay;

    EventItem(int id, String name, long dtStart, long dtEnd, boolean allDay) {
        this.id = id;
        this.name = name;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
        this.allDay = allDay;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    long getDtStart() {
        return dtStart;
    }

    long getDtEnd() {
        return dtEnd;
    }

    boolean isAllDay() {
        return allDay;
    }

    @Override
    public String toString() {
        return "EventItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dtStart=" + dtStart +
                ", dtEnd=" + dtEnd +
                ", allDay=" + allDay +
                '}';
    }
}
