package com.amap.api.services.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class cs extends SQLiteOpenHelper {
    private static boolean b = true;
    private static boolean c = false;

    /* renamed from: a  reason: collision with root package name */
    private co f4380a;

    public cs(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, co coVar) {
        super(context, str, cursorFactory, i);
        this.f4380a = coVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f4380a.a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f4380a.a(sQLiteDatabase, i, i2);
    }
}
