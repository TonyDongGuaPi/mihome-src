package com.lidroid.xutils.db.table;

import android.database.Cursor;
import com.lidroid.xutils.db.converter.ColumnConverter;
import com.lidroid.xutils.db.converter.ColumnConverterFactory;
import com.lidroid.xutils.db.sqlite.ColumnDbType;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Column {

    /* renamed from: a  reason: collision with root package name */
    protected final String f6324a;
    protected final Method b;
    protected final Method c;
    protected final Field d;
    protected final ColumnConverter e;
    private Table f;
    private int g = -1;
    private final Object h;

    Column(Class<?> cls, Field field) {
        this.d = field;
        this.e = ColumnConverterFactory.a(field.getType());
        this.f6324a = ColumnUtils.a(field);
        if (this.e != null) {
            this.h = this.e.b(ColumnUtils.c(field));
        } else {
            this.h = null;
        }
        this.b = ColumnUtils.a(cls, field);
        this.c = ColumnUtils.b(cls, field);
    }

    public void a(Object obj, Cursor cursor, int i) {
        this.g = i;
        Object b2 = this.e.b(cursor, i);
        if (b2 != null || this.h != null) {
            if (this.c != null) {
                try {
                    Method method = this.c;
                    Object[] objArr = new Object[1];
                    if (b2 == null) {
                        b2 = this.h;
                    }
                    objArr[0] = b2;
                    method.invoke(obj, objArr);
                } catch (Throwable th) {
                    LogUtils.b(th.getMessage(), th);
                }
            } else {
                try {
                    this.d.setAccessible(true);
                    Field field = this.d;
                    if (b2 == null) {
                        b2 = this.h;
                    }
                    field.set(obj, b2);
                } catch (Throwable th2) {
                    LogUtils.b(th2.getMessage(), th2);
                }
            }
        }
    }

    public Object a(Object obj) {
        return this.e.a(b(obj));
    }

    public Object b(Object obj) {
        if (obj != null) {
            if (this.b != null) {
                try {
                    return this.b.invoke(obj, new Object[0]);
                } catch (Throwable th) {
                    LogUtils.b(th.getMessage(), th);
                }
            } else {
                try {
                    this.d.setAccessible(true);
                    return this.d.get(obj);
                } catch (Throwable th2) {
                    LogUtils.b(th2.getMessage(), th2);
                }
            }
        }
        return null;
    }

    public Table a() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void a(Table table) {
        this.f = table;
    }

    public int b() {
        return this.g;
    }

    public String c() {
        return this.f6324a;
    }

    public Object d() {
        return this.h;
    }

    public Field e() {
        return this.d;
    }

    public ColumnConverter f() {
        return this.e;
    }

    public ColumnDbType g() {
        return this.e.a();
    }
}
