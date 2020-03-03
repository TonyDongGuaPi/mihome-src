package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class StringColumnConverter implements ColumnConverter<String> {
    public Object a(String str) {
        return str;
    }

    /* renamed from: a */
    public String b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return cursor.getString(i);
    }

    public ColumnDbType a() {
        return ColumnDbType.TEXT;
    }
}
