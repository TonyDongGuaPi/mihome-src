package org.xutils.db;

import android.database.Cursor;
import java.util.LinkedHashMap;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;

final class CursorUtils {
    CursorUtils() {
    }

    public static <T> T a(TableEntity<T> tableEntity, Cursor cursor) throws Throwable {
        T a2 = tableEntity.a();
        LinkedHashMap<String, ColumnEntity> h = tableEntity.h();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            ColumnEntity columnEntity = h.get(cursor.getColumnName(i));
            if (columnEntity != null) {
                columnEntity.a(a2, cursor, i);
            }
        }
        return a2;
    }

    public static DbModel a(Cursor cursor) {
        DbModel dbModel = new DbModel();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            dbModel.a(cursor.getColumnName(i), cursor.getString(i));
        }
        return dbModel;
    }
}
