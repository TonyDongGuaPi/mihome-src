package org.greenrobot.greendao.internal;

import android.support.media.ExifInterface;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

public class TableStatements {

    /* renamed from: a  reason: collision with root package name */
    private final Database f3532a;
    private final String b;
    private final String[] c;
    private final String[] d;
    private DatabaseStatement e;
    private DatabaseStatement f;
    private DatabaseStatement g;
    private DatabaseStatement h;
    private DatabaseStatement i;
    private volatile String j;
    private volatile String k;
    private volatile String l;
    private volatile String m;

    public TableStatements(Database database, String str, String[] strArr, String[] strArr2) {
        this.f3532a = database;
        this.b = str;
        this.c = strArr;
        this.d = strArr2;
    }

    public DatabaseStatement a() {
        if (this.e == null) {
            DatabaseStatement b2 = this.f3532a.b(SqlUtils.a("INSERT INTO ", this.b, this.c));
            synchronized (this) {
                if (this.e == null) {
                    this.e = b2;
                }
            }
            if (this.e != b2) {
                b2.e();
            }
        }
        return this.e;
    }

    public DatabaseStatement b() {
        if (this.f == null) {
            DatabaseStatement b2 = this.f3532a.b(SqlUtils.a("INSERT OR REPLACE INTO ", this.b, this.c));
            synchronized (this) {
                if (this.f == null) {
                    this.f = b2;
                }
            }
            if (this.f != b2) {
                b2.e();
            }
        }
        return this.f;
    }

    public DatabaseStatement c() {
        if (this.h == null) {
            DatabaseStatement b2 = this.f3532a.b(SqlUtils.a(this.b, this.d));
            synchronized (this) {
                if (this.h == null) {
                    this.h = b2;
                }
            }
            if (this.h != b2) {
                b2.e();
            }
        }
        return this.h;
    }

    public DatabaseStatement d() {
        if (this.g == null) {
            DatabaseStatement b2 = this.f3532a.b(SqlUtils.a(this.b, this.c, this.d));
            synchronized (this) {
                if (this.g == null) {
                    this.g = b2;
                }
            }
            if (this.g != b2) {
                b2.e();
            }
        }
        return this.g;
    }

    public DatabaseStatement e() {
        if (this.i == null) {
            this.i = this.f3532a.b(SqlUtils.a(this.b));
        }
        return this.i;
    }

    public String f() {
        if (this.j == null) {
            this.j = SqlUtils.a(this.b, ExifInterface.GPS_DIRECTION_TRUE, this.c, false);
        }
        return this.j;
    }

    public String g() {
        if (this.m == null) {
            this.m = SqlUtils.a(this.b, ExifInterface.GPS_DIRECTION_TRUE, this.d, false);
        }
        return this.m;
    }

    public String h() {
        if (this.k == null) {
            StringBuilder sb = new StringBuilder(f());
            sb.append("WHERE ");
            SqlUtils.b(sb, ExifInterface.GPS_DIRECTION_TRUE, this.d);
            this.k = sb.toString();
        }
        return this.k;
    }

    public String i() {
        if (this.l == null) {
            this.l = f() + "WHERE ROWID=?";
        }
        return this.l;
    }
}
