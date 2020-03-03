package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class FloatColumnConverter implements ColumnConverter<Float> {
    public Object a(Float f) {
        return f;
    }

    /* renamed from: a */
    public Float b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Float.valueOf(cursor.getFloat(i));
    }

    public ColumnDbType a() {
        return ColumnDbType.REAL;
    }
}
