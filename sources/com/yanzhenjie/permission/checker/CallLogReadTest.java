package com.yanzhenjie.permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.RequiresPermission;
import com.yanzhenjie.permission.checker.PermissionTest;

class CallLogReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2409a;

    CallLogReadTest(Context context) {
        this.f2409a = context.getContentResolver();
    }

    @RequiresPermission("android.permission.READ_CALL_LOG")
    public boolean a() throws Throwable {
        Cursor query = this.f2409a.query(CallLog.Calls.CONTENT_URI, new String[]{"_id", "number", "type"}, (String) null, (String[]) null, (String) null);
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
