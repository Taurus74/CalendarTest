package com.aconst.eventsdatatest;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;

import java.util.TimeZone;

public class Events {
    private static final String[] EVENTS_PROJECTION = {
            CalendarContract.Events._ID,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND,
            CalendarContract.Events.ALL_DAY
    };

    private static final int EVENT_ID_IDX = 0;
    private static final int EVENT_TITLE_IDX = 1;
    private static final int EVENT_DTSTART_IDX = 2;
    private static final int EVENT_DTEND_IDX = 3;
    private static final int EVENT_ALLDAY_IDX = 4;

    private Cursor cur = null;

    public Events(Context context, long calendarId) {
        // Run query
        ContentResolver cr = context.getContentResolver();
        Uri uri = CalendarContract.Events.CONTENT_URI;
        String selection = "(" + CalendarContract.Events.CALENDAR_ID + " = ?)";
        String[] selectionArgs = new String[1];

        // Submit the query and get a Cursor object back.
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        selectionArgs[0] = Long.toString(calendarId);
        cur = cr.query(uri, EVENTS_PROJECTION, selection, selectionArgs, null);
    }

    public SparseArray<EventItem> getEvents() {
        SparseArray<EventItem> events = new SparseArray<>();
        int diff = CalendarHelper.getDiffFromGMT(TimeZone.getDefault());

        if (cur != null) {
            while (cur.moveToNext()) {
                int mId = Integer.parseInt(cur.getString(EVENT_ID_IDX));

                String sStart = cur.getString(EVENT_DTSTART_IDX);
                long dtStart = 0;
                if (sStart != null && !sStart.isEmpty())
                    dtStart = Long.parseLong(sStart);

                String sEnd = cur.getString(EVENT_DTEND_IDX);
                long dtEnd = 0;
                if (sEnd != null && !sEnd.isEmpty())
                    dtEnd = Long.parseLong(sEnd);

                boolean allDay = "1".equals(cur.getString(EVENT_ALLDAY_IDX));

                if (allDay) {
                    dtStart += diff;
                    dtEnd += diff;
                }

                EventItem eventItem = new EventItem(
                        mId, cur.getString(EVENT_TITLE_IDX), dtStart, dtEnd, allDay
                );
                events.put(mId, eventItem);
            }
            cur.close();
        }

        return events;
    }

}
