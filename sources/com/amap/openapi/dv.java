package com.amap.openapi;

public class dv {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f4711a = {"ID", "type", "value", "time", "size"};

    public static String a(String str) {
        return "create table if not exists " + str + " (ID integer PRIMARY KEY AUTOINCREMENT NOT NULL, type integer, value blob, time long, size integer);";
    }
}
