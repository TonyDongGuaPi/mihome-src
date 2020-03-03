package com.yanzhenjie.yp_permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import com.yanzhenjie.yp_permission.checker.PermissionTest;

class SmsReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2450a;

    SmsReadTest(Context context) {
        this.f2450a = context.getContentResolver();
    }

    public boolean a() throws Throwable {
        Cursor query = this.f2450a.query(Telephony.Sms.CONTENT_URI, new String[]{"_id", "address", "person", "body"}, (String) null, (String[]) null, (String) null);
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
