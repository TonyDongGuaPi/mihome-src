package org.greenrobot.greendao.database;

import android.database.Cursor;
import android.database.SQLException;

public interface Database {
    Cursor a(String str, String[] strArr);

    void a();

    void a(String str) throws SQLException;

    void a(String str, Object[] objArr) throws SQLException;

    DatabaseStatement b(String str);

    void b();

    boolean c();

    void d();

    boolean e();

    void f();

    Object g();
}
