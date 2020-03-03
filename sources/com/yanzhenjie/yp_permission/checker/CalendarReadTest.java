package com.yanzhenjie.yp_permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;
import com.yanzhenjie.yp_permission.checker.PermissionTest;

class CalendarReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2436a;

    CalendarReadTest(Context context) {
        this.f2436a = context.getContentResolver();
    }

    public boolean a() throws Throwable {
        Cursor query = this.f2436a.query(CalendarContract.Calendars.CONTENT_URI, new String[]{"_id", "name"}, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return false;
        }
        try {
            PermissionTest.CursorTest.a(query);
            return true;
        } finally {
            query.close();
        }
    }
}
