package org.xutils.db.table;

import android.database.Cursor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.xutils.common.util.LogUtil;
import org.xutils.db.annotation.Column;
import org.xutils.db.converter.ColumnConverter;
import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.sqlite.ColumnDbType;

public final class ColumnEntity {

    /* renamed from: a  reason: collision with root package name */
    protected final String f4246a;
    protected final Method b;
    protected final Method c;
    protected final Field d;
    protected final ColumnConverter e;
    private final String f;
    private final boolean g;
    private final boolean h;

    ColumnEntity(Class<?> cls, Field field, Column column) {
        field.setAccessible(true);
        this.d = field;
        this.f4246a = column.name();
        this.f = column.property();
        this.g = column.isId();
        Class<?> type = field.getType();
        this.h = this.g && column.autoGen() && ColumnUtils.a(type);
        this.e = ColumnConverterFactory.a(type);
        this.b = ColumnUtils.a(cls, field);
        if (this.b != null && !this.b.isAccessible()) {
            this.b.setAccessible(true);
        }
        this.c = ColumnUtils.b(cls, field);
        if (this.c != null && !this.c.isAccessible()) {
            this.c.setAccessible(true);
        }
    }

    public void a(Object obj, Cursor cursor, int i) {
        Object b2 = this.e.b(cursor, i);
        if (b2 != null) {
            if (this.c != null) {
                try {
                    this.c.invoke(obj, new Object[]{b2});
                } catch (Throwable th) {
                    LogUtil.b(th.getMessage(), th);
                }
            } else {
                try {
                    this.d.set(obj, b2);
                } catch (Throwable th2) {
                    LogUtil.b(th2.getMessage(), th2);
                }
            }
        }
    }

    public Object a(Object obj) {
        Object b2 = b(obj);
        if (!this.h || (!b2.equals(0L) && !b2.equals(0))) {
            return this.e.a(b2);
        }
        return null;
    }

    public void a(Object obj, long j) {
        Object valueOf = Long.valueOf(j);
        if (ColumnUtils.b(this.d.getType())) {
            valueOf = Integer.valueOf((int) j);
        }
        if (this.c != null) {
            try {
                this.c.invoke(obj, new Object[]{valueOf});
            } catch (Throwable th) {
                LogUtil.b(th.getMessage(), th);
            }
        } else {
            try {
                this.d.set(obj, valueOf);
            } catch (Throwable th2) {
                LogUtil.b(th2.getMessage(), th2);
            }
        }
    }

    public Object b(Object obj) {
        if (obj != null) {
            if (this.b != null) {
                try {
                    return this.b.invoke(obj, new Object[0]);
                } catch (Throwable th) {
                    LogUtil.b(th.getMessage(), th);
                }
            } else {
                try {
                    return this.d.get(obj);
                } catch (Throwable th2) {
                    LogUtil.b(th2.getMessage(), th2);
                }
            }
        }
        return null;
    }

    public String a() {
        return this.f4246a;
    }

    public String b() {
        return this.f;
    }

    public boolean c() {
        return this.g;
    }

    public boolean d() {
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

    public String toString() {
        return this.f4246a;
    }
}
