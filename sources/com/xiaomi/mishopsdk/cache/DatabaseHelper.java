package com.xiaomi.mishopsdk.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "shop_new.db";
    private static final int DB_VERSION = 1;
    private static DatabaseHelper sInstance;

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            if (sInstance == null) {
                sInstance = new DatabaseHelper(context);
            }
            databaseHelper = sInstance;
        }
        return databaseHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE service_token_cache(sid TEXT PRIMARY KEY,token TEXT NOT NULL);");
    }
}
