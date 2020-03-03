package org.greenrobot.greendao.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StandardDatabase implements Database {

    /* renamed from: a  reason: collision with root package name */
    private final SQLiteDatabase f3523a;

    public StandardDatabase(SQLiteDatabase sQLiteDatabase) {
        this.f3523a = sQLiteDatabase;
    }

    public Cursor a(String str, String[] strArr) {
        return this.f3523a.rawQuery(str, strArr);
    }

    public void a(String str) throws SQLException {
        this.f3523a.execSQL(str);
    }

    public void a() {
        this.f3523a.beginTransaction();
    }

    public void b() {
        this.f3523a.endTransaction();
    }

    public boolean c() {
        return this.f3523a.inTransaction();
    }

    public void d() {
        this.f3523a.setTransactionSuccessful();
    }

    public void a(String str, Object[] objArr) throws SQLException {
        this.f3523a.execSQL(str, objArr);
    }

    public DatabaseStatement b(String str) {
        return new StandardDatabaseStatement(this.f3523a.compileStatement(str));
    }

    public boolean e() {
        return this.f3523a.isDbLockedByCurrentThread();
    }

    public void f() {
        this.f3523a.close();
    }

    public Object g() {
        return this.f3523a;
    }

    public SQLiteDatabase h() {
        return this.f3523a;
    }
}
