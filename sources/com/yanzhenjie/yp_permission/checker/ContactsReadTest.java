package com.yanzhenjie.yp_permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.yanzhenjie.yp_permission.checker.PermissionTest;

class ContactsReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2441a;

    ContactsReadTest(Context context) {
        this.f2441a = context.getContentResolver();
    }

    public boolean a() throws Throwable {
        Cursor query = this.f2441a.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"_id", "data1"}, (String) null, (String[]) null, (String) null);
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
