package com.mi.global.bbs.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.mi.global.bbs.entity.Subcribtion;
import com.mi.global.bbs.http.ParamKey;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class SubcribtionDao extends AbstractDao<Subcribtion, Long> {
    public static final String TABLENAME = "SUBCRIBTION";

    public static class Properties {
        public static final Property ColumnID = new Property(1, Long.class, ParamKey.columnID, false, "COLUMN_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property LastAppendTime = new Property(2, Long.class, "lastAppendTime", false, "LAST_APPEND_TIME");
    }

    /* access modifiers changed from: protected */
    public final boolean isEntityUpdateable() {
        return true;
    }

    public SubcribtionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public SubcribtionDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.a("CREATE TABLE " + str + "\"SUBCRIBTION\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"COLUMN_ID\" INTEGER,\"LAST_APPEND_TIME\" INTEGER);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SUBCRIBTION\"");
        database.a(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final void bindValues(DatabaseStatement databaseStatement, Subcribtion subcribtion) {
        databaseStatement.d();
        Long id = subcribtion.getId();
        if (id != null) {
            databaseStatement.a(1, id.longValue());
        }
        Long columnID = subcribtion.getColumnID();
        if (columnID != null) {
            databaseStatement.a(2, columnID.longValue());
        }
        Long lastAppendTime = subcribtion.getLastAppendTime();
        if (lastAppendTime != null) {
            databaseStatement.a(3, lastAppendTime.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public final void bindValues(SQLiteStatement sQLiteStatement, Subcribtion subcribtion) {
        sQLiteStatement.clearBindings();
        Long id = subcribtion.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        Long columnID = subcribtion.getColumnID();
        if (columnID != null) {
            sQLiteStatement.bindLong(2, columnID.longValue());
        }
        Long lastAppendTime = subcribtion.getLastAppendTime();
        if (lastAppendTime != null) {
            sQLiteStatement.bindLong(3, lastAppendTime.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public Subcribtion readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long l = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        Long valueOf2 = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            l = Long.valueOf(cursor.getLong(i4));
        }
        return new Subcribtion(valueOf, valueOf2, l);
    }

    public void readEntity(Cursor cursor, Subcribtion subcribtion, int i) {
        int i2 = i + 0;
        Long l = null;
        subcribtion.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        subcribtion.setColumnID(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        int i4 = i + 2;
        if (!cursor.isNull(i4)) {
            l = Long.valueOf(cursor.getLong(i4));
        }
        subcribtion.setLastAppendTime(l);
    }

    /* access modifiers changed from: protected */
    public final Long updateKeyAfterInsert(Subcribtion subcribtion, long j) {
        subcribtion.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(Subcribtion subcribtion) {
        if (subcribtion != null) {
            return subcribtion.getId();
        }
        return null;
    }

    public boolean hasKey(Subcribtion subcribtion) {
        return subcribtion.getId() != null;
    }
}
