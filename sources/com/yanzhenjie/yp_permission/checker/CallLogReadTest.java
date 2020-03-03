package com.yanzhenjie.yp_permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import com.yanzhenjie.yp_permission.checker.PermissionTest;

class CallLogReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2438a;

    CallLogReadTest(Context context) {
        this.f2438a = context.getContentResolver();
    }

    public boolean a() throws Throwable {
        Cursor query = this.f2438a.query(CallLog.Calls.CONTENT_URI, new String[]{"_id", "number", "type"}, (String) null, (String[]) null, (String) null);
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
