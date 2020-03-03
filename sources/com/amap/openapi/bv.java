package com.amap.openapi;

import android.database.sqlite.SQLiteDatabase;

public class bv {

    /* renamed from: a  reason: collision with root package name */
    public static String f4644a = "id";
    public static String b = "lat";
    public static String c = "lng";
    public static String d = "acc";
    public static String e = "conf";
    public static String f = "timestamp";
    public static String g = "frequency";
    private static final String h = ("CREATE TABLE IF NOT EXISTS AP ( " + f4644a + " LONG PRIMARY KEY, " + b + " INTEGER, " + c + " INTEGER, " + d + " INTEGER, " + e + " INTEGER, " + f + " LONG, " + g + " INTEGER DEFAULT 0);");

    public static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(h);
    }
}
