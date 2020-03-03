package org.greenrobot.greendao.database;

import net.sqlcipher.database.SQLiteStatement;

public class EncryptedDatabaseStatement implements DatabaseStatement {

    /* renamed from: a  reason: collision with root package name */
    private final SQLiteStatement f3522a;

    public EncryptedDatabaseStatement(SQLiteStatement sQLiteStatement) {
        this.f3522a = sQLiteStatement;
    }

    public void a() {
        this.f3522a.execute();
    }

    public long b() {
        return this.f3522a.simpleQueryForLong();
    }

    public void a(int i) {
        this.f3522a.bindNull(i);
    }

    public long c() {
        return this.f3522a.executeInsert();
    }

    public void a(int i, String str) {
        this.f3522a.bindString(i, str);
    }

    public void a(int i, byte[] bArr) {
        this.f3522a.bindBlob(i, bArr);
    }

    public void a(int i, long j) {
        this.f3522a.bindLong(i, j);
    }

    public void d() {
        this.f3522a.clearBindings();
    }

    public void a(int i, double d) {
        this.f3522a.bindDouble(i, d);
    }

    public void e() {
        this.f3522a.close();
    }

    public Object f() {
        return this.f3522a;
    }
}
