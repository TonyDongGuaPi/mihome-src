package com.mi.mistatistic.sdk.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class MiStatDatabaseHelper extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f7333a = "mistat_data";
    protected static final String b = "mistat_session";
    protected static final String c = "ts";
    public static final Object d = new Object();
    private static final String e = "miglobalstat.db";
    private static final int f = 1;
    private static final String g = "create table %s(_id integer primary key autoincrement, ts integer,start_time integer, end_time integer,network text)";
    private static final String h = "create table %s(_id integer primary key autoincrement, ts integer,category text,data text)";

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public MiStatDatabaseHelper(Context context) {
        super(context, e, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (d) {
            sQLiteDatabase.execSQL(String.format(g, new Object[]{"mistat_session"}));
            sQLiteDatabase.execSQL(String.format(h, new Object[]{f7333a}));
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        try {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables(str);
            return sQLiteQueryBuilder.query(getReadableDatabase(), strArr, str2, strArr2, (String) null, (String) null, str3);
        } catch (SQLiteException unused) {
            return null;
        }
    }

    public long a(String str, ContentValues contentValues) {
        return getWritableDatabase().insert(str, "", contentValues);
    }

    public int a(String str, String str2, String[] strArr) {
        return getWritableDatabase().delete(str, str2, strArr);
    }

    public int a(String str, ContentValues contentValues, String str2, String[] strArr) {
        return getWritableDatabase().update(str, contentValues, str2, strArr);
    }
}
