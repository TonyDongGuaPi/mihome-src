package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

public class StringColumnConverter implements ColumnConverter<String> {
    /* renamed from: a */
    public String b(String str) {
        return str;
    }

    /* renamed from: c */
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
