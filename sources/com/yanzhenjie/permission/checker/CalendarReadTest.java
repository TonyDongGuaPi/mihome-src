package com.yanzhenjie.permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import com.yanzhenjie.permission.checker.PermissionTest;

class CalendarReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2407a;

    CalendarReadTest(Context context) {
        this.f2407a = context.getContentResolver();
    }

    @RequiresApi(14)
    @RequiresPermission("android.permission.READ_CALENDAR")
    public boolean a() throws Throwable {
        Cursor query = this.f2407a.query(CalendarContract.Calendars.CONTENT_URI, new String[]{"_id", "name"}, (String) null, (String[]) null, (String) null);
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
