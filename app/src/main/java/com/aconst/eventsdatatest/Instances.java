package com.aconst.eventsdatatest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

public class Instances {
    private static final String[] INSTANCES_PROJECTION = {
            CalendarContract.Instances.EVENT_ID,
            CalendarContract.Instances.BEGIN,
            CalendarContract.Instances.END,
            CalendarContract.Instances.START_MINUTE,
            CalendarContract.Instances.END_MINUTE
    };

    private static final int INSTANCE_EVENT_ID_IDX = 0;
    private static final int INSTANCE_BEGIN_IDX = 1;
    private static final int INSTANCE_END_IDX = 2;
    private static final int INSTANCE_START_MINUTE_IDX = 3;
    private static final int INSTANCE_END_MINUTE_IDX = 4;

    private Cursor cur = null;

    @SuppressLint("Recycle")
    public Instances(Context context, long fromDate, long toDate) {
        ContentResolver cr = context.getContentResolver();

        Uri uri = CalendarContract.Instances.CONTENT_URI;
        Uri.Builder builder = uri.buildUpon();
        ContentUris.appendId(builder, fromDate);
        ContentUris.appendId(builder, toDate);

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        cur = cr.query(builder.build(), INSTANCES_PROJECTION,
                null, null, null);
    }

    public ArrayList<InstanceItem> getInstances() {
        ArrayList<InstanceItem> instances = new ArrayList<>();

        if (cur != null) {
            while (cur.moveToNext()) {
                int mEventId = Integer.parseInt(cur.getString(INSTANCE_EVENT_ID_IDX));
                long lBegin = Long.parseLong(cur.getString(INSTANCE_BEGIN_IDX));
                long lEnd = Long.parseLong(cur.getString(INSTANCE_END_IDX));
                int mStartMinute = Integer.parseInt(cur.getString(INSTANCE_START_MINUTE_IDX));
                int mEndMinute = Integer.parseInt(cur.getString(INSTANCE_END_MINUTE_IDX));

                InstanceItem eventInstance = new InstanceItem(
                        mEventId, lBegin, lEnd, mStartMinute, mEndMinute);
                instances.add(eventInstance);
            }

            cur.close();
        }

        return instances;
    }

}
