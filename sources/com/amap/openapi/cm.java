package com.amap.openapi;

import android.database.sqlite.SQLiteDatabase;

public class cm {

    /* renamed from: a  reason: collision with root package name */
    public static String f4663a = "id";
    public static String b = "frequency";
    private static final String c = ("CREATE TABLE IF NOT EXISTS ACL ( " + f4663a + " TEXT PRIMARY KEY, " + b + " INTEGER DEFAULT 0);");

    public static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(c);
    }
}
