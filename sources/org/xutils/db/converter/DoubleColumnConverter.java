package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class DoubleColumnConverter implements ColumnConverter<Double> {
    public Object a(Double d) {
        return d;
    }

    /* renamed from: a */
    public Double b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Double.valueOf(cursor.getDouble(i));
    }

    public ColumnDbType a() {
        return ColumnDbType.REAL;
    }
}
