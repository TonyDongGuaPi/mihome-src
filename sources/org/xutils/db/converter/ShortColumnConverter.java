package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class ShortColumnConverter implements ColumnConverter<Short> {
    public Object a(Short sh) {
        return sh;
    }

    /* renamed from: a */
    public Short b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Short.valueOf(cursor.getShort(i));
    }

    public ColumnDbType a() {
        return ColumnDbType.INTEGER;
    }
}
