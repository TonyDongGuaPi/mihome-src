package com.yanzhenjie.yp_permission.checker;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.provider.CallLog;

class CallLogWriteTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2439a;

    CallLogWriteTest(Context context) {
        this.f2439a = context.getContentResolver();
    }

    public boolean a() throws Throwable {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("type", 1);
            contentValues.put("number", "1");
            contentValues.put("date", 20080808);
            contentValues.put("new", "0");
            boolean z = ContentUris.parseId(this.f2439a.insert(CallLog.Calls.CONTENT_URI, contentValues)) > 0;
            this.f2439a.delete(CallLog.Calls.CONTENT_URI, "number=?", new String[]{"1"});
            return z;
        } catch (Throwable th) {
            this.f2439a.delete(CallLog.Calls.CONTENT_URI, "number=?", new String[]{"1"});
            throw th;
        }
    }
}
