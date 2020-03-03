package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class IntegerColumnConverter implements ColumnConverter<Integer> {
    public Object a(Integer num) {
        return num;
    }

    /* renamed from: a */
    public Integer b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Integer.valueOf(cursor.getInt(i));
    }

    public ColumnDbType a() {
        return ColumnDbType.INTEGER;
    }
}
