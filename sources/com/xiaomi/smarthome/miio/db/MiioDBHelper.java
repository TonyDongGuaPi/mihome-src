package com.xiaomi.smarthome.miio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;
import com.xiaomi.smarthome.miio.db.record.FamilyRecord;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.db.record.MessageRecordShop;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class MiioDBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "miio.db";
    private static final int DATABASE_VERSION = 37;
    private static final Object mLock = new Object();
    private static final AtomicInteger refCounter = new AtomicInteger(0);
    private static MiioDBHelper sInstance;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private Dao<FamilyRecord, Integer> mFamilyRecordDao = null;
    private Dao<MessageRecord, Integer> mMessageRecordDao = null;
    private Dao<MessageRecordShop, Integer> mMessageRecordShopDao = null;
    private Dao<ShareUserRecord, Integer> mShareRecordDao = null;

    public void release() {
    }

    public MiioDBHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 37);
    }

    public static MiioDBHelper getInstance(Context context) {
        synchronized (mLock) {
            if (sInstance == null) {
                sInstance = getHelper(context);
            }
            refCounter.incrementAndGet();
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

    private void createTableIfNotExists(ConnectionSource connectionSource, Class<?> cls) throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, cls);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            createTableIfNotExists(connectionSource, MessageRecord.class);
            createTableIfNotExists(connectionSource, ShareUserRecord.class);
            createTableIfNotExists(connectionSource, FamilyRecord.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.dropTable(connectionSource, MessageRecord.class, true);
            TableUtils.dropTable(connectionSource, ShareUserRecord.class, true);
            TableUtils.dropTable(connectionSource, FamilyRecord.class, true);
            onCreate(sQLiteDatabase, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        boolean z;
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
            TableUtils.dropTable(connectionSource, MessageRecord.class, true);
            TableUtils.dropTable(connectionSource, ShareUserRecord.class, true);
            TableUtils.dropTable(connectionSource, FamilyRecord.class, true);
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

    public Dao<MessageRecord, Integer> getMessageRecordDao() throws SQLException {
        if (this.mMessageRecordDao == null) {
            synchronized (MessageRecord.class) {
                if (this.mMessageRecordDao == null) {
                    this.mMessageRecordDao = getRecordDao(MessageRecord.class);
                }
            }
        }
        return this.mMessageRecordDao;
    }

    public Dao<MessageRecordShop, Integer> getMessageRecordShopDao() throws SQLException {
        if (this.mMessageRecordShopDao == null) {
            synchronized (MessageRecord.class) {
                if (this.mMessageRecordShopDao == null) {
                    this.mMessageRecordShopDao = getRecordDao(MessageRecordShop.class);
                }
            }
        }
        return this.mMessageRecordShopDao;
    }

    public Dao<ShareUserRecord, Integer> getShareUserRecordDao() throws SQLException {
        if (this.mShareRecordDao == null) {
            synchronized (ShareUserRecord.class) {
                if (this.mShareRecordDao == null) {
                    this.mShareRecordDao = getRecordDao(ShareUserRecord.class);
                }
            }
        }
        return this.mShareRecordDao;
    }

    public Dao<FamilyRecord, Integer> getFamilyRecordDao() throws SQLException {
        if (this.mFamilyRecordDao == null) {
            synchronized (FamilyRecord.class) {
                if (this.mFamilyRecordDao == null) {
                    this.mFamilyRecordDao = getRecordDao(FamilyRecord.class);
                }
            }
        }
        return this.mFamilyRecordDao;
    }

    private Dao getRecordDao(Class<?> cls) throws SQLException {
        return getDao(cls);
    }
}
