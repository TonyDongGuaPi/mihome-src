package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public interface ColumnConverter<T> {
    Object a(T t);

    ColumnDbType a();

    T b(Cursor cursor, int i);
}
