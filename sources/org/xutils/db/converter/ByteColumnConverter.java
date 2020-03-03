package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class ByteColumnConverter implements ColumnConverter<Byte> {
    public Object a(Byte b) {
        return b;
    }

    /* renamed from: a */
    public Byte b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Byte.valueOf((byte) cursor.getInt(i));
    }

    public ColumnDbType a() {
        return ColumnDbType.INTEGER;
    }
}
