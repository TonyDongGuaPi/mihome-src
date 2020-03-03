package com.xiaomi.smarthome.miio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.miio.db.record.MessageRecordTypeList;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class TypeListMsgDBHelper extends OrmLiteSqliteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13556a = "typelist.db";
    private static final int b = 1;
    private static TypeListMsgDBHelper c;
    private static final Object d = new Object();
    private static final AtomicInteger f = new AtomicInteger(0);
    private Dao<MessageRecordTypeList, Integer> e = null;

    public void a() {
    }

    public TypeListMsgDBHelper(Context context) {
        super(context, f13556a, (SQLiteDatabase.CursorFactory) null, 1);
        try {
            a(this.connectionSource, MessageRecordTypeList.class);
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public static TypeListMsgDBHelper a(Context context) {
        synchronized (d) {
            if (c == null) {
                c = new TypeListMsgDBHelper(SHApplication.getAppContext());
            }
            f.incrementAndGet();
        }
        return c;
    }

    private void a(ConnectionSource connectionSource, Class<?> cls) throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, cls);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            a(connectionSource, MessageRecordTypeList.class);
        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.dropTable(connectionSource, MessageRecordTypeList.class, true);
            onCreate(sQLiteDatabase, connectionSource);
        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void close() {
        super.close();
    }

    public Dao<MessageRecordTypeList, Integer> b() throws SQLException {
        if (this.e == null) {
            synchronized (MessageRecordTypeList.class) {
                if (this.e == null) {
                    this.e = a((Class<?>) MessageRecordTypeList.class);
                }
            }
        }
        return this.e;
    }

    private Dao a(Class<?> cls) throws SQLException {
        return getDao(cls);
    }
}
