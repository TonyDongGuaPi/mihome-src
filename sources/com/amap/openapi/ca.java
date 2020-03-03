package com.amap.openapi;

import android.database.sqlite.SQLiteDatabase;

public class ca {

    /* renamed from: a  reason: collision with root package name */
    public static String f4653a = "id";
    public static String b = "originid";
    public static String c = "frequency";
    public static String d = "time";
    private static final String e = ("CREATE TABLE IF NOT EXISTS CL ( " + f4653a + " LONG PRIMARY KEY, " + b + " TEXT, " + c + " INTEGER DEFAULT 0, " + d + " LONG DEFAULT 0);");

    public static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(e);
    }
}
