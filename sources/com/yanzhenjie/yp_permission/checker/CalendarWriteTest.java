package com.yanzhenjie.yp_permission.checker;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.provider.CalendarContract;
import com.libra.Color;
import java.util.TimeZone;

class CalendarWriteTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2437a = "PERMISSION";
    private static final String b = "permission@gmail.com";
    private ContentResolver c;

    CalendarWriteTest(Context context) {
        this.c = context.getContentResolver();
    }

    public boolean a() throws Throwable {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", f2437a);
            contentValues.put("account_name", b);
            contentValues.put("account_type", "LOCAL");
            contentValues.put("calendar_displayName", f2437a);
            contentValues.put("visible", 1);
            contentValues.put("calendar_color", Integer.valueOf(Color.h));
            contentValues.put("calendar_access_level", 700);
            contentValues.put("sync_events", 1);
            contentValues.put("calendar_timezone", timeZone.getID());
            contentValues.put("ownerAccount", f2437a);
            contentValues.put("canOrganizerRespond", 0);
            boolean z = ContentUris.parseId(this.c.insert(CalendarContract.Calendars.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", f2437a).appendQueryParameter("account_type", "LOCAL").build(), contentValues)) > 0;
            this.c.delete(CalendarContract.Calendars.CONTENT_URI.buildUpon().build(), "account_name=?", new String[]{b});
            return z;
        } catch (Throwable th) {
            this.c.delete(CalendarContract.Calendars.CONTENT_URI.buildUpon().build(), "account_name=?", new String[]{b});
            throw th;
        }
    }
}
