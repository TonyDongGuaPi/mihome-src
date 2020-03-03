package com.amap.openapi;

import android.database.sqlite.SQLiteDatabase;

public class bz {

    /* renamed from: a  reason: collision with root package name */
    public static String f4647a = "id";
    public static String b = "originid";
    public static String c = "frequency";
    public static String d = "time";
    private static final String e = ("CREATE TABLE IF NOT EXISTS AP ( " + f4647a + " LONG PRIMARY KEY, " + b + " LONG, " + c + " INTEGER DEFAULT 0, " + d + " LONG DEFAULT 0);");

    public static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(e);
    }
}
