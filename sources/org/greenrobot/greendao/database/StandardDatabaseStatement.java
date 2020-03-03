package org.greenrobot.greendao.database;

import android.database.sqlite.SQLiteStatement;

public class StandardDatabaseStatement implements DatabaseStatement {

    /* renamed from: a  reason: collision with root package name */
    private final SQLiteStatement f3524a;

    public StandardDatabaseStatement(SQLiteStatement sQLiteStatement) {
        this.f3524a = sQLiteStatement;
    }

    public void a() {
        this.f3524a.execute();
    }

    public long b() {
        return this.f3524a.simpleQueryForLong();
    }

    public void a(int i) {
        this.f3524a.bindNull(i);
    }

    public long c() {
        return this.f3524a.executeInsert();
    }

    public void a(int i, String str) {
        this.f3524a.bindString(i, str);
    }

    public void a(int i, byte[] bArr) {
        this.f3524a.bindBlob(i, bArr);
    }

    public void a(int i, long j) {
        this.f3524a.bindLong(i, j);
    }

    public void d() {
        this.f3524a.clearBindings();
    }

    public void a(int i, double d) {
        this.f3524a.bindDouble(i, d);
    }

    public void e() {
        this.f3524a.close();
    }

    public Object f() {
        return this.f3524a;
    }
}
