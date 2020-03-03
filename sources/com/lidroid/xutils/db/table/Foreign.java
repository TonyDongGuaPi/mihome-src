package com.lidroid.xutils.db.table;

import com.lidroid.xutils.db.converter.ColumnConverter;
import com.lidroid.xutils.db.converter.ColumnConverterFactory;
import com.lidroid.xutils.db.sqlite.ColumnDbType;
import com.lidroid.xutils.db.sqlite.ForeignLazyLoader;
import com.lidroid.xutils.util.LogUtils;
import java.lang.reflect.Field;
import java.util.List;

public class Foreign extends Column {
    private final String f;
    private final ColumnConverter g = ColumnConverterFactory.a(TableUtils.a(i(), this.f).d.getType());

    public Object d() {
        return null;
    }

    Foreign(Class<?> cls, Field field) {
        super(cls, field);
        this.f = ColumnUtils.b(field);
    }

    public String h() {
        return this.f;
    }

    public Class<?> i() {
        return ColumnUtils.a(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0052 A[SYNTHETIC, Splitter:B:19:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0066 A[SYNTHETIC, Splitter:B:23:0x0066] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.Object r3, android.database.Cursor r4, int r5) {
        /*
            r2 = this;
            com.lidroid.xutils.db.converter.ColumnConverter r0 = r2.g
            java.lang.Object r4 = r0.b(r4, r5)
            if (r4 != 0) goto L_0x0009
            return
        L_0x0009:
            r5 = 0
            java.lang.reflect.Field r0 = r2.d
            java.lang.Class r0 = r0.getType()
            java.lang.Class<com.lidroid.xutils.db.sqlite.ForeignLazyLoader> r1 = com.lidroid.xutils.db.sqlite.ForeignLazyLoader.class
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x001f
            com.lidroid.xutils.db.sqlite.ForeignLazyLoader r5 = new com.lidroid.xutils.db.sqlite.ForeignLazyLoader
            r5.<init>(r2, r4)
        L_0x001d:
            r4 = r5
            goto L_0x004d
        L_0x001f:
            java.lang.Class<java.util.List> r1 = java.util.List.class
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003a
            com.lidroid.xutils.db.sqlite.ForeignLazyLoader r0 = new com.lidroid.xutils.db.sqlite.ForeignLazyLoader     // Catch:{ DbException -> 0x0031 }
            r0.<init>(r2, r4)     // Catch:{ DbException -> 0x0031 }
            java.util.List r4 = r0.a()     // Catch:{ DbException -> 0x0031 }
            goto L_0x004d
        L_0x0031:
            r4 = move-exception
            java.lang.String r0 = r4.getMessage()
            com.lidroid.xutils.util.LogUtils.b(r0, r4)
            goto L_0x001d
        L_0x003a:
            com.lidroid.xutils.db.sqlite.ForeignLazyLoader r0 = new com.lidroid.xutils.db.sqlite.ForeignLazyLoader     // Catch:{ DbException -> 0x0044 }
            r0.<init>(r2, r4)     // Catch:{ DbException -> 0x0044 }
            java.lang.Object r4 = r0.b()     // Catch:{ DbException -> 0x0044 }
            goto L_0x004d
        L_0x0044:
            r4 = move-exception
            java.lang.String r0 = r4.getMessage()
            com.lidroid.xutils.util.LogUtils.b(r0, r4)
            goto L_0x001d
        L_0x004d:
            java.lang.reflect.Method r5 = r2.c
            r0 = 1
            if (r5 == 0) goto L_0x0066
            java.lang.reflect.Method r5 = r2.c     // Catch:{ Throwable -> 0x005d }
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x005d }
            r1 = 0
            r0[r1] = r4     // Catch:{ Throwable -> 0x005d }
            r5.invoke(r3, r0)     // Catch:{ Throwable -> 0x005d }
            goto L_0x0079
        L_0x005d:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()
            com.lidroid.xutils.util.LogUtils.b(r4, r3)
            goto L_0x0079
        L_0x0066:
            java.lang.reflect.Field r5 = r2.d     // Catch:{ Throwable -> 0x0071 }
            r5.setAccessible(r0)     // Catch:{ Throwable -> 0x0071 }
            java.lang.reflect.Field r5 = r2.d     // Catch:{ Throwable -> 0x0071 }
            r5.set(r3, r4)     // Catch:{ Throwable -> 0x0071 }
            goto L_0x0079
        L_0x0071:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()
            com.lidroid.xutils.util.LogUtils.b(r4, r3)
        L_0x0079:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.db.table.Foreign.a(java.lang.Object, android.database.Cursor, int):void");
    }

    public Object a(Object obj) {
        Object b = b(obj);
        Object obj2 = null;
        if (b == null) {
            return null;
        }
        Class<?> type = this.d.getType();
        if (type.equals(ForeignLazyLoader.class)) {
            return ((ForeignLazyLoader) b).c();
        }
        if (type.equals(List.class)) {
            try {
                List list = (List) b;
                if (list.size() <= 0) {
                    return null;
                }
                Column a2 = TableUtils.a(ColumnUtils.a(this), this.f);
                Object a3 = a2.a(list.get(0));
                try {
                    Table a4 = a();
                    if (a4 != null && (a2 instanceof Id)) {
                        for (Object next : list) {
                            if (a2.a(next) == null) {
                                a4.f6328a.a(next);
                            }
                        }
                    }
                    return a2.a(list.get(0));
                } catch (Throwable th) {
                    th = th;
                    obj2 = a3;
                    LogUtils.b(th.getMessage(), th);
                    return obj2;
                }
            } catch (Throwable th2) {
                th = th2;
                LogUtils.b(th.getMessage(), th);
                return obj2;
            }
        } else {
            try {
                Column a5 = TableUtils.a(type, this.f);
                Object a6 = a5.a(b);
                try {
                    Table a7 = a();
                    if (a7 != null && a6 == null && (a5 instanceof Id)) {
                        a7.f6328a.a(b);
                    }
                    return a5.a(b);
                } catch (Throwable th3) {
                    th = th3;
                    obj2 = a6;
                    LogUtils.b(th.getMessage(), th);
                    return obj2;
                }
            } catch (Throwable th4) {
                th = th4;
                LogUtils.b(th.getMessage(), th);
                return obj2;
            }
        }
    }

    public ColumnDbType g() {
        return this.g.a();
    }
}
