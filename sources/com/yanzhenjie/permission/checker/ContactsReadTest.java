package com.yanzhenjie.permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.yanzhenjie.permission.checker.PermissionTest;

class ContactsReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private ContentResolver f2412a;

    ContactsReadTest(Context context) {
        this.f2412a = context.getContentResolver();
    }

    public boolean a() throws Throwable {
        Cursor query = this.f2412a.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"_id", "data1"}, (String) null, (String[]) null, (String) null);
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
