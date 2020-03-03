package com.yanzhenjie.yp_permission.checker;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.xiaomi.smarthome.download.Downloads;

class ContactsWriteTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2442a = "PERMISSION";
    private ContentResolver b;

    ContactsWriteTest(ContentResolver contentResolver) {
        this.b = contentResolver;
    }

    public boolean a() throws Throwable {
        Cursor query = this.b.query(ContactsContract.Data.CONTENT_URI, new String[]{"raw_contact_id"}, "mimetype=? and data1=?", new String[]{"vnd.android.cursor.item/name", f2442a}, (String) null);
        if (query == null) {
            return false;
        }
        if (query.moveToFirst()) {
            long j = query.getLong(0);
            query.close();
            return a(j);
        }
        query.close();
        return b();
    }

    private boolean b() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("raw_contact_id", Long.valueOf(ContentUris.parseId(this.b.insert(ContactsContract.RawContacts.CONTENT_URI, contentValues))));
        contentValues.put("data1", f2442a);
        contentValues.put("data2", f2442a);
        contentValues.put(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/name");
        return ContentUris.parseId(this.b.insert(ContactsContract.Data.CONTENT_URI, contentValues)) > 0;
    }

    private void a(long j, long j2) {
        this.b.delete(ContactsContract.RawContacts.CONTENT_URI, "_id=?", new String[]{Long.toString(j)});
        this.b.delete(ContactsContract.Data.CONTENT_URI, "_id=?", new String[]{Long.toString(j2)});
    }

    private boolean a(long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("raw_contact_id", Long.valueOf(j));
        contentValues.put("data1", f2442a);
        contentValues.put("data2", f2442a);
        contentValues.put(Downloads.COLUMN_MIME_TYPE, "vnd.android.cursor.item/name");
        return ContentUris.parseId(this.b.insert(ContactsContract.Data.CONTENT_URI, contentValues)) > 0;
    }
}
