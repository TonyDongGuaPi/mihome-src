package com.xiaomi.market.sdk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.xiaomi.market.sdk.Constants;

public class SDKDatabaseHelper extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    static final String f11112a = "xiaomi_market_sdk_update.db";
    static final int b = 1;
    private static SDKDatabaseHelper c = null;
    private static final String d = "MarketSDKDatabaseHelper";

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static SDKDatabaseHelper a(Context context) {
        if (c == null) {
            c = new SDKDatabaseHelper(context);
        }
        return c;
    }

    private SDKDatabaseHelper(Context context) {
        super(context, f11112a, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.a(d, "create database");
        a(sQLiteDatabase);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(Constants.Update.k);
    }

    public synchronized long a(String str, ContentValues contentValues) {
        return getWritableDatabase().insert(str, (String) null, contentValues);
    }

    public long a(ContentValues contentValues) {
        SQLiteStatement compileStatement = getWritableDatabase().compileStatement("INSERT OR REPLACE INTO update_download(package_name,download_id,version_code,apk_url,apk_hash,diff_url,diff_hash,apk_path) VALUES(?,?,?,?,?,?,?,?)");
        compileStatement.bindString(1, contentValues.getAsString("package_name"));
        compileStatement.bindLong(2, contentValues.getAsLong("download_id").longValue());
        compileStatement.bindLong(3, (long) contentValues.getAsInteger(Constants.Update.e).intValue());
        compileStatement.bindString(4, contentValues.getAsString(Constants.Update.f));
        compileStatement.bindString(5, contentValues.getAsString(Constants.Update.g));
        compileStatement.bindString(6, contentValues.getAsString(Constants.Update.h));
        compileStatement.bindString(7, contentValues.getAsString(Constants.Update.i));
        compileStatement.bindString(8, contentValues.getAsString(Constants.Update.j));
        compileStatement.execute();
        return 1;
    }

    public Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        return getReadableDatabase().query(str, strArr, str2, strArr2, str3, str4, str5);
    }
}
