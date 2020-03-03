package com.xiaomi.youpin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xiaomi.smarthome.application.SHApplication;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PluginDBHelper extends OrmLiteSqliteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23160a = "plugin.db";
    private static final int b = 1;
    private static final Class[] c = {PluginDeveloperInfo.class};
    private static PluginDBHelper e;
    private Map<String, Dao> d = new HashMap();

    public static PluginDBHelper a() {
        if (e == null) {
            synchronized (PluginDBHelper.class) {
                if (e == null) {
                    e = (PluginDBHelper) OpenHelperManager.getHelper(SHApplication.getAppContext(), PluginDBHelper.class);
                }
            }
        }
        return e;
    }

    public PluginDBHelper(Context context) {
        super(context, f23160a, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            for (Class createTableIfNotExists : c) {
                TableUtils.createTableIfNotExists(connectionSource, createTableIfNotExists);
            }
        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            for (Class dropTable : c) {
                TableUtils.dropTable(connectionSource, dropTable, true);
            }
            onCreate(sQLiteDatabase, connectionSource);
        } catch (SQLException unused) {
        }
    }

    public synchronized Dao getDao(Class cls) throws SQLException {
        Dao dao;
        dao = null;
        String simpleName = cls.getSimpleName();
        if (this.d.containsKey(simpleName)) {
            dao = this.d.get(simpleName);
        }
        if (dao == null) {
            dao = super.getDao(cls);
            this.d.put(simpleName, dao);
        }
        return dao;
    }

    public void close() {
        super.close();
        this.d.clear();
        synchronized (PluginDBHelper.class) {
            e = null;
        }
    }
}
