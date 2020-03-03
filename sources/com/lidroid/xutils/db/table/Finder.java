package com.lidroid.xutils.db.table;

import com.lidroid.xutils.db.sqlite.ColumnDbType;
import java.lang.reflect.Field;

public class Finder extends Column {
    private final String f;
    private final String g;

    public Object a(Object obj) {
        return null;
    }

    public Object d() {
        return null;
    }

    Finder(Class<?> cls, Field field) {
        super(cls, field);
        com.lidroid.xutils.db.annotation.Finder finder = (com.lidroid.xutils.db.annotation.Finder) field.getAnnotation(com.lidroid.xutils.db.annotation.Finder.class);
        this.f = finder.valueColumn();
        this.g = finder.targetColumn();
    }

    public Class<?> h() {
        return ColumnUtils.a(this);
    }

    public String i() {
        return this.g;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055 A[SYNTHETIC, Splitter:B:16:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0069 A[SYNTHETIC, Splitter:B:20:0x0069] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.Object r3, android.database.Cursor r4, int r5) {
        /*
            r2 = this;
            java.lang.reflect.Field r4 = r2.d
            java.lang.Class r4 = r4.getType()
            java.lang.Class r5 = r3.getClass()
            java.lang.String r0 = r2.f
            com.lidroid.xutils.db.table.Column r5 = com.lidroid.xutils.db.table.TableUtils.a(r5, r0)
            java.lang.Object r5 = r5.a((java.lang.Object) r3)
            java.lang.Class<com.lidroid.xutils.db.sqlite.FinderLazyLoader> r0 = com.lidroid.xutils.db.sqlite.FinderLazyLoader.class
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0022
            com.lidroid.xutils.db.sqlite.FinderLazyLoader r4 = new com.lidroid.xutils.db.sqlite.FinderLazyLoader
            r4.<init>(r2, r5)
            goto L_0x0050
        L_0x0022:
            java.lang.Class<java.util.List> r0 = java.util.List.class
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x003d
            com.lidroid.xutils.db.sqlite.FinderLazyLoader r4 = new com.lidroid.xutils.db.sqlite.FinderLazyLoader     // Catch:{ DbException -> 0x0034 }
            r4.<init>(r2, r5)     // Catch:{ DbException -> 0x0034 }
            java.util.List r4 = r4.a()     // Catch:{ DbException -> 0x0034 }
            goto L_0x0050
        L_0x0034:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            com.lidroid.xutils.util.LogUtils.b(r5, r4)
            goto L_0x004f
        L_0x003d:
            com.lidroid.xutils.db.sqlite.FinderLazyLoader r4 = new com.lidroid.xutils.db.sqlite.FinderLazyLoader     // Catch:{ DbException -> 0x0047 }
            r4.<init>(r2, r5)     // Catch:{ DbException -> 0x0047 }
            java.lang.Object r4 = r4.b()     // Catch:{ DbException -> 0x0047 }
            goto L_0x0050
        L_0x0047:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            com.lidroid.xutils.util.LogUtils.b(r5, r4)
        L_0x004f:
            r4 = 0
        L_0x0050:
            java.lang.reflect.Method r5 = r2.c
            r0 = 1
            if (r5 == 0) goto L_0x0069
            java.lang.reflect.Method r5 = r2.c     // Catch:{ Throwable -> 0x0060 }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0060 }
            r1 = 0
            r0[r1] = r4     // Catch:{ Throwable -> 0x0060 }
            r5.invoke(r3, r0)     // Catch:{ Throwable -> 0x0060 }
            goto L_0x007c
        L_0x0060:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()
            com.lidroid.xutils.util.LogUtils.b(r4, r3)
            goto L_0x007c
        L_0x0069:
            java.lang.reflect.Field r5 = r2.d     // Catch:{ Throwable -> 0x0074 }
            r5.setAccessible(r0)     // Catch:{ Throwable -> 0x0074 }
            java.lang.reflect.Field r5 = r2.d     // Catch:{ Throwable -> 0x0074 }
            r5.set(r3, r4)     // Catch:{ Throwable -> 0x0074 }
            goto L_0x007c
        L_0x0074:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()
            com.lidroid.xutils.util.LogUtils.b(r4, r3)
        L_0x007c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.db.table.Finder.a(java.lang.Object, android.database.Cursor, int):void");
    }

    public ColumnDbType g() {
        return ColumnDbType.TEXT;
    }
}
