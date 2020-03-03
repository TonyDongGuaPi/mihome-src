package com.lidroid.xutils.db.converter;

import android.database.Cursor;
import com.lidroid.xutils.db.sqlite.ColumnDbType;

public interface ColumnConverter<T> {
    ColumnDbType a();

    Object a(T t);

    T b(Cursor cursor, int i);

    T b(String str);
}
