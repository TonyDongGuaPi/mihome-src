package com.yanzhenjie.permission.checker;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.provider.CallLog;
import android.support.annotation.RequiresPermission;

class CallLogWriteTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2410a;

    CallLogWriteTest(Context context) {
        this.f2410a = context.getContentResolver();
    }

    @RequiresPermission("android.permission.WRITE_CALL_LOG")
    public boolean a() throws Throwable {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("type", 1);
            contentValues.put("number", "1");
            contentValues.put("date", 20080808);
            contentValues.put("new", "0");
            boolean z = ContentUris.parseId(this.f2410a.insert(CallLog.Calls.CONTENT_URI, contentValues)) > 0;
            this.f2410a.delete(CallLog.Calls.CONTENT_URI, "number=?", new String[]{"1"});
            return z;
        } catch (Throwable th) {
            this.f2410a.delete(CallLog.Calls.CONTENT_URI, "number=?", new String[]{"1"});
            throw th;
        }
    }
}
