package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class ByteArrayColumnConverter implements ColumnConverter<byte[]> {
    public Object a(byte[] bArr) {
        return bArr;
    }

    /* renamed from: a */
    public byte[] b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return cursor.getBlob(i);
    }

    public ColumnDbType a() {
        return ColumnDbType.BLOB;
    }
}
