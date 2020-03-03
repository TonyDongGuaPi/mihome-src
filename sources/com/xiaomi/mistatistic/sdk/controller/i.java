package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class i extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f12033a = new Object();

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public i(Context context) {
        super(context, "mistat.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (f12033a) {
            sQLiteDatabase.execSQL(String.format("create table %s(_id integer primary key autoincrement, category text, ts integer, key text, value text, type text, extra text)", new Object[]{"mistat_event"}));
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
