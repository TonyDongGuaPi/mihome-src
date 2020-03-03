package com.xiaomi.smarthome.core.server.internal.device;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class MiioDBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "miio2.db";
    public static final int DATABASE_VERSION = 39;
    private static MiioDBHelper sInstance;
    private static Object sLock = new Object();
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private Dao<DeviceRecord, Integer> mDeviceRecordDao;

    public static MiioDBHelper getsInstance(Context context) {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = getHelper(context);
            }
        }
        return sInstance;
    }

    public static synchronized MiioDBHelper getHelper(Context context) {
        MiioDBHelper miioDBHelper;
        synchronized (MiioDBHelper.class) {
            if (sInstance == null) {
                sInstance = new MiioDBHelper(context);
            }
            usageCounter.incrementAndGet();
            miioDBHelper = sInstance;
        }
        return miioDBHelper;
    }

    public void close() {
        if (usageCounter.decrementAndGet() == 0) {
            super.close();
            sInstance = null;
        }
    }

    public MiioDBHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 39);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.d("MiioDBHelper2", "onCreate");
            TableUtils.createTableIfNotExists(connectionSource, DeviceRecord.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            Log.d("MiioDBHelper2", "onUpgrade oldVersion = " + i + ", newVersion = " + i2);
            TableUtils.dropTable(connectionSource, DeviceRecord.class, true);
            onCreate(sQLiteDatabase, connectionSource);
        } catch (SQLException unused) {
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        boolean z;
        Log.d("MiioDBHelper2", "onDowngrade, oldVersion = " + i + ", newVersion = " + i2);
        ConnectionSource connectionSource = getConnectionSource();
        DatabaseConnection specialConnection = connectionSource.getSpecialConnection((String) null);
        if (specialConnection == null) {
            specialConnection = new AndroidDatabaseConnection(sQLiteDatabase, true);
            try {
                connectionSource.saveSpecialConnection(specialConnection);
                z = true;
            } catch (SQLException e) {
                throw new IllegalStateException("Could not save special connection", e);
            }
        } else {
            z = false;
        }
        try {
            TableUtils.dropTable(connectionSource, DeviceRecord.class, true);
            onCreate(sQLiteDatabase, connectionSource);
            if (!z) {
                return;
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
            if (!z) {
                return;
            }
        } catch (Throwable th) {
            if (z) {
                connectionSource.clearSpecialConnection(specialConnection);
            }
            throw th;
        }
        connectionSource.clearSpecialConnection(specialConnection);
    }

    public Dao<DeviceRecord, Integer> getDeviceRecordDao() throws SQLException {
        if (this.mDeviceRecordDao == null) {
            synchronized (DeviceRecord.class) {
                this.mDeviceRecordDao = getDao(DeviceRecord.class);
            }
        }
        return this.mDeviceRecordDao;
    }
}
