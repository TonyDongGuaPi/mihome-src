package com.lidroid.xutils.db.table;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.util.HashSet;

public class Id extends Column {
    private static final HashSet<String> i = new HashSet<>(2);
    private static final HashSet<String> j = new HashSet<>(4);
    private String f = this.d.getType().getName();
    private boolean g = false;
    private boolean h = false;

    Id(Class<?> cls, Field field) {
        super(cls, field);
    }

    public boolean h() {
        if (!this.g) {
            boolean z = true;
            this.g = true;
            if (this.d.getAnnotation(NoAutoIncrement.class) != null || !j.contains(this.f)) {
                z = false;
            }
            this.h = z;
        }
        return this.h;
    }

    public void a(Object obj, long j2) {
        Object valueOf = Long.valueOf(j2);
        if (i.contains(this.f)) {
            valueOf = Integer.valueOf((int) j2);
        }
        if (this.c != null) {
            try {
                this.c.invoke(obj, new Object[]{valueOf});
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
            }
        } else {
            try {
                this.d.setAccessible(true);
                this.d.set(obj, valueOf);
            } catch (Throwable th2) {
                LogUtils.b(th2.getMessage(), th2);
            }
        }
    }

    public Object a(Object obj) {
        Object a2 = super.a(obj);
        if (a2 == null) {
            return null;
        }
        if (!h() || (!a2.equals(0) && !a2.equals(0L))) {
            return a2;
        }
        return null;
    }

    static {
        i.add(Integer.TYPE.getName());
        i.add(Integer.class.getName());
        j.addAll(i);
        j.add(Long.TYPE.getName());
        j.add(Long.class.getName());
    }
}
