package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class BooleanColumnConverter implements ColumnConverter<Boolean> {
    /* renamed from: a */
    public Boolean b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        int i2 = cursor.getInt(i);
        boolean z = true;
        if (i2 != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public Object a(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return Integer.valueOf(bool.booleanValue() ? 1 : 0);
    }

    public ColumnDbType a() {
        return ColumnDbType.INTEGER;
    }
}
