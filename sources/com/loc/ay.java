package com.loc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class ay extends SQLiteOpenHelper {
    private static boolean b = true;
    private static boolean c = false;

    /* renamed from: a  reason: collision with root package name */
    private au f6493a;

    public ay(Context context, String str, au auVar) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.f6493a = auVar;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f6493a.a(sQLiteDatabase);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
