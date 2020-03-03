package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class LongColumnConverter implements ColumnConverter<Long> {
    public Object a(Long l) {
        return l;
    }

    /* renamed from: a */
    public Long b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    public ColumnDbType a() {
        return ColumnDbType.INTEGER;
    }
}
