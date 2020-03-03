package org.xutils.db.converter;

import android.database.Cursor;
import java.util.Date;
import org.xutils.db.sqlite.ColumnDbType;

public class DateColumnConverter implements ColumnConverter<Date> {
    /* renamed from: a */
    public Date b(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return new Date(cursor.getLong(i));
    }

    public Object a(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime());
    }

    public ColumnDbType a() {
        return ColumnDbType.INTEGER;
    }
}
