package org.greenrobot.greendao.database;

import android.database.Cursor;
import android.database.SQLException;
import net.sqlcipher.database.SQLiteDatabase;

public class EncryptedDatabase implements Database {

    /* renamed from: a  reason: collision with root package name */
    private final SQLiteDatabase f3521a;

    public EncryptedDatabase(SQLiteDatabase sQLiteDatabase) {
        this.f3521a = sQLiteDatabase;
    }

    public Cursor a(String str, String[] strArr) {
        return this.f3521a.rawQuery(str, strArr);
    }

    public void a(String str) throws SQLException {
        this.f3521a.execSQL(str);
    }

    public void a() {
        this.f3521a.beginTransaction();
    }

    public void b() {
        this.f3521a.endTransaction();
    }

    public boolean c() {
        return this.f3521a.inTransaction();
    }

    public void d() {
        this.f3521a.setTransactionSuccessful();
    }

    public void a(String str, Object[] objArr) throws SQLException {
        this.f3521a.execSQL(str, objArr);
    }

    public DatabaseStatement b(String str) {
        return new EncryptedDatabaseStatement(this.f3521a.compileStatement(str));
    }

    public boolean e() {
        return this.f3521a.isDbLockedByCurrentThread();
    }

    public void f() {
        this.f3521a.close();
    }

    public Object g() {
        return this.f3521a;
    }

    public SQLiteDatabase h() {
        return this.f3521a;
    }
}
