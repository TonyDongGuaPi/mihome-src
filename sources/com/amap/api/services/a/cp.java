package com.amap.api.services.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cp {
    private static Map<Class<? extends co>, co> d = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    private cs f4379a;
    private SQLiteDatabase b;
    private co c;

    private boolean a(Annotation annotation) {
        return annotation != null;
    }

    public static synchronized co a(Class<? extends co> cls) throws IllegalAccessException, InstantiationException {
        co coVar;
        synchronized (cp.class) {
            if (d.get(cls) == null) {
                d.put(cls, cls.newInstance());
            }
            coVar = d.get(cls);
        }
        return coVar;
    }

    public cp(Context context, co coVar) {
        try {
            this.f4379a = new cs(context.getApplicationContext(), coVar.a(), (SQLiteDatabase.CursorFactory) null, coVar.b(), coVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = coVar;
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String next : map.keySet()) {
            if (z) {
                sb.append(next);
                sb.append(" = '");
                sb.append(map.get(next));
                sb.append("'");
                z = false;
            } else {
                sb.append(" and ");
                sb.append(next);
                sb.append(" = '");
                sb.append(map.get(next));
                sb.append("'");
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0043, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0062, code lost:
        if (r4.b != null) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0064, code lost:
        r4.b.close();
        r4.b = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x006b, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x006d, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0007, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:3:0x0005, B:32:0x004d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> void a(java.lang.String r5, java.lang.Object r6, boolean r7) {
        /*
            r4 = this;
            com.amap.api.services.a.co r0 = r4.c
            monitor-enter(r0)
            if (r6 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x0007:
            r5 = move-exception
            goto L_0x006c
        L_0x0009:
            java.lang.Class r1 = r6.getClass()     // Catch:{ all -> 0x0007 }
            com.amap.api.services.a.cq r1 = r4.b(r1)     // Catch:{ all -> 0x0007 }
            java.lang.String r2 = r4.a((com.amap.api.services.a.cq) r1)     // Catch:{ all -> 0x0007 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0007 }
            if (r3 == 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x001d:
            android.content.ContentValues r6 = r4.a((java.lang.Object) r6, (com.amap.api.services.a.cq) r1)     // Catch:{ all -> 0x0007 }
            if (r6 != 0) goto L_0x0025
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x0025:
            android.database.sqlite.SQLiteDatabase r1 = r4.b((boolean) r7)     // Catch:{ all -> 0x0007 }
            r4.b = r1     // Catch:{ all -> 0x0007 }
            android.database.sqlite.SQLiteDatabase r1 = r4.b     // Catch:{ all -> 0x0007 }
            if (r1 != 0) goto L_0x0031
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x0031:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r3 = r4.b     // Catch:{ Throwable -> 0x0045 }
            r3.update(r2, r6, r5, r1)     // Catch:{ Throwable -> 0x0045 }
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch:{ all -> 0x0007 }
            if (r5 == 0) goto L_0x005e
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch:{ all -> 0x0007 }
            r5.close()     // Catch:{ all -> 0x0007 }
        L_0x0040:
            r4.b = r1     // Catch:{ all -> 0x0007 }
            goto L_0x005e
        L_0x0043:
            r5 = move-exception
            goto L_0x0060
        L_0x0045:
            r5 = move-exception
            if (r7 != 0) goto L_0x0051
            java.lang.String r6 = "dbs"
            java.lang.String r7 = "udd"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r5, (java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0043 }
            goto L_0x0054
        L_0x0051:
            r5.printStackTrace()     // Catch:{ all -> 0x0043 }
        L_0x0054:
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch:{ all -> 0x0007 }
            if (r5 == 0) goto L_0x005e
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch:{ all -> 0x0007 }
            r5.close()     // Catch:{ all -> 0x0007 }
            goto L_0x0040
        L_0x005e:
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x0060:
            android.database.sqlite.SQLiteDatabase r6 = r4.b     // Catch:{ all -> 0x0007 }
            if (r6 == 0) goto L_0x006b
            android.database.sqlite.SQLiteDatabase r6 = r4.b     // Catch:{ all -> 0x0007 }
            r6.close()     // Catch:{ all -> 0x0007 }
            r4.b = r1     // Catch:{ all -> 0x0007 }
        L_0x006b:
            throw r5     // Catch:{ all -> 0x0007 }
        L_0x006c:
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cp.a(java.lang.String, java.lang.Object, boolean):void");
    }

    public <T> void a(String str, Object obj) {
        a(str, obj, false);
    }

    public <T> void a(T t) {
        a(t, false);
    }

    /* JADX INFO: finally extract failed */
    public <T> void a(T t, boolean z) {
        synchronized (this.c) {
            this.b = b(z);
            if (this.b != null) {
                try {
                    a(this.b, t);
                    if (this.b != null) {
                        this.b.close();
                        this.b = null;
                    }
                } catch (Throwable th) {
                    try {
                        ci.a(th, "dbs", "itd");
                        if (this.b != null) {
                            this.b.close();
                        }
                    } catch (Throwable th2) {
                        if (this.b != null) {
                            this.b.close();
                            this.b = null;
                        }
                        throw th2;
                    }
                }
            }
        }
    }

    private <T> void a(SQLiteDatabase sQLiteDatabase, T t) {
        ContentValues a2;
        cq b2 = b(t.getClass());
        String a3 = a(b2);
        if (!TextUtils.isEmpty(a3) && t != null && sQLiteDatabase != null && (a2 = a((Object) t, b2)) != null) {
            sQLiteDatabase.insert(a3, (String) null, a2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0105, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00de A[SYNTHETIC, Splitter:B:86:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x00f1 A[Catch:{ Throwable -> 0x00f9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> java.util.List<T> a(java.lang.String r13, java.lang.Class<T> r14, boolean r15) {
        /*
            r12 = this;
            com.amap.api.services.a.co r0 = r12.c
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0106 }
            r1.<init>()     // Catch:{ all -> 0x0106 }
            com.amap.api.services.a.cq r2 = r12.b(r14)     // Catch:{ all -> 0x0106 }
            java.lang.String r4 = r12.a((com.amap.api.services.a.cq) r2)     // Catch:{ all -> 0x0106 }
            android.database.sqlite.SQLiteDatabase r3 = r12.b     // Catch:{ all -> 0x0106 }
            if (r3 != 0) goto L_0x001a
            android.database.sqlite.SQLiteDatabase r3 = r12.a((boolean) r15)     // Catch:{ all -> 0x0106 }
            r12.b = r3     // Catch:{ all -> 0x0106 }
        L_0x001a:
            android.database.sqlite.SQLiteDatabase r3 = r12.b     // Catch:{ all -> 0x0106 }
            if (r3 == 0) goto L_0x0104
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0106 }
            if (r3 != 0) goto L_0x0104
            if (r13 != 0) goto L_0x0028
            goto L_0x0104
        L_0x0028:
            r11 = 0
            android.database.sqlite.SQLiteDatabase r3 = r12.b     // Catch:{ Throwable -> 0x00a5, all -> 0x00a2 }
            r5 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r6 = r13
            android.database.Cursor r13 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x00a5, all -> 0x00a2 }
            if (r13 != 0) goto L_0x006a
            android.database.sqlite.SQLiteDatabase r14 = r12.b     // Catch:{ Throwable -> 0x0068 }
            r14.close()     // Catch:{ Throwable -> 0x0068 }
            r12.b = r11     // Catch:{ Throwable -> 0x0068 }
            if (r13 == 0) goto L_0x004f
            r13.close()     // Catch:{ Throwable -> 0x0044 }
            goto L_0x004f
        L_0x0044:
            r13 = move-exception
            if (r15 != 0) goto L_0x004f
            java.lang.String r14 = "dbs"
            java.lang.String r2 = "sld"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x0106 }
        L_0x004f:
            android.database.sqlite.SQLiteDatabase r13 = r12.b     // Catch:{ Throwable -> 0x005b }
            if (r13 == 0) goto L_0x0066
            android.database.sqlite.SQLiteDatabase r13 = r12.b     // Catch:{ Throwable -> 0x005b }
            r13.close()     // Catch:{ Throwable -> 0x005b }
            r12.b = r11     // Catch:{ Throwable -> 0x005b }
            goto L_0x0066
        L_0x005b:
            r13 = move-exception
            if (r15 != 0) goto L_0x0066
            java.lang.String r14 = "dbs"
            java.lang.String r15 = "sld"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r15)     // Catch:{ all -> 0x0106 }
        L_0x0066:
            monitor-exit(r0)     // Catch:{ all -> 0x0106 }
            return r1
        L_0x0068:
            r14 = move-exception
            goto L_0x00a7
        L_0x006a:
            boolean r3 = r13.moveToNext()     // Catch:{ Throwable -> 0x0068 }
            if (r3 == 0) goto L_0x0078
            java.lang.Object r3 = r12.a((android.database.Cursor) r13, r14, (com.amap.api.services.a.cq) r2)     // Catch:{ Throwable -> 0x0068 }
            r1.add(r3)     // Catch:{ Throwable -> 0x0068 }
            goto L_0x006a
        L_0x0078:
            if (r13 == 0) goto L_0x0089
            r13.close()     // Catch:{ Throwable -> 0x007e }
            goto L_0x0089
        L_0x007e:
            r13 = move-exception
            if (r15 != 0) goto L_0x0089
            java.lang.String r14 = "dbs"
            java.lang.String r2 = "sld"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x0106 }
        L_0x0089:
            android.database.sqlite.SQLiteDatabase r13 = r12.b     // Catch:{ Throwable -> 0x0096 }
            if (r13 == 0) goto L_0x0102
            android.database.sqlite.SQLiteDatabase r13 = r12.b     // Catch:{ Throwable -> 0x0096 }
            r13.close()     // Catch:{ Throwable -> 0x0096 }
            r12.b = r11     // Catch:{ Throwable -> 0x0096 }
            goto L_0x0102
        L_0x0096:
            r13 = move-exception
            if (r15 != 0) goto L_0x0102
            java.lang.String r14 = "dbs"
            java.lang.String r15 = "sld"
        L_0x009e:
            com.amap.api.services.a.ci.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r15)     // Catch:{ all -> 0x0106 }
            goto L_0x0102
        L_0x00a2:
            r14 = move-exception
            r13 = r11
            goto L_0x00b3
        L_0x00a5:
            r14 = move-exception
            r13 = r11
        L_0x00a7:
            if (r15 != 0) goto L_0x00dc
            java.lang.String r2 = "dbs"
            java.lang.String r3 = "sld"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r14, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x00b2 }
            goto L_0x00dc
        L_0x00b2:
            r14 = move-exception
        L_0x00b3:
            if (r13 == 0) goto L_0x00c4
            r13.close()     // Catch:{ Throwable -> 0x00b9 }
            goto L_0x00c4
        L_0x00b9:
            r13 = move-exception
            if (r15 != 0) goto L_0x00c4
            java.lang.String r1 = "dbs"
            java.lang.String r2 = "sld"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r13, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x0106 }
        L_0x00c4:
            android.database.sqlite.SQLiteDatabase r13 = r12.b     // Catch:{ Throwable -> 0x00d0 }
            if (r13 == 0) goto L_0x00db
            android.database.sqlite.SQLiteDatabase r13 = r12.b     // Catch:{ Throwable -> 0x00d0 }
            r13.close()     // Catch:{ Throwable -> 0x00d0 }
            r12.b = r11     // Catch:{ Throwable -> 0x00d0 }
            goto L_0x00db
        L_0x00d0:
            r13 = move-exception
            if (r15 != 0) goto L_0x00db
            java.lang.String r15 = "dbs"
            java.lang.String r1 = "sld"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r13, (java.lang.String) r15, (java.lang.String) r1)     // Catch:{ all -> 0x0106 }
        L_0x00db:
            throw r14     // Catch:{ all -> 0x0106 }
        L_0x00dc:
            if (r13 == 0) goto L_0x00ed
            r13.close()     // Catch:{ Throwable -> 0x00e2 }
            goto L_0x00ed
        L_0x00e2:
            r13 = move-exception
            if (r15 != 0) goto L_0x00ed
            java.lang.String r14 = "dbs"
            java.lang.String r2 = "sld"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x0106 }
        L_0x00ed:
            android.database.sqlite.SQLiteDatabase r13 = r12.b     // Catch:{ Throwable -> 0x00f9 }
            if (r13 == 0) goto L_0x0102
            android.database.sqlite.SQLiteDatabase r13 = r12.b     // Catch:{ Throwable -> 0x00f9 }
            r13.close()     // Catch:{ Throwable -> 0x00f9 }
            r12.b = r11     // Catch:{ Throwable -> 0x00f9 }
            goto L_0x0102
        L_0x00f9:
            r13 = move-exception
            if (r15 != 0) goto L_0x0102
            java.lang.String r14 = "dbs"
            java.lang.String r15 = "sld"
            goto L_0x009e
        L_0x0102:
            monitor-exit(r0)     // Catch:{ all -> 0x0106 }
            return r1
        L_0x0104:
            monitor-exit(r0)     // Catch:{ all -> 0x0106 }
            return r1
        L_0x0106:
            r13 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0106 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.cp.a(java.lang.String, java.lang.Class, boolean):java.util.List");
    }

    public <T> List<T> a(String str, Class<T> cls) {
        return a(str, cls, false);
    }

    private <T> T a(Cursor cursor, Class<T> cls, cq cqVar) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] a2 = a((Class<?>) cls, cqVar.b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a2) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(cr.class);
            if (annotation != null) {
                cr crVar = (cr) annotation;
                int b2 = crVar.b();
                int columnIndex = cursor.getColumnIndex(crVar.a());
                switch (b2) {
                    case 1:
                        field.set(newInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        break;
                    case 2:
                        field.set(newInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        break;
                    case 3:
                        field.set(newInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        break;
                    case 4:
                        field.set(newInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        break;
                    case 5:
                        field.set(newInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        break;
                    case 6:
                        field.set(newInstance, cursor.getString(columnIndex));
                        break;
                    case 7:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        break;
                }
            }
        }
        return newInstance;
    }

    private void a(Object obj, Field field, ContentValues contentValues) {
        Annotation annotation = field.getAnnotation(cr.class);
        if (annotation != null) {
            cr crVar = (cr) annotation;
            switch (crVar.b()) {
                case 1:
                    contentValues.put(crVar.a(), Short.valueOf(field.getShort(obj)));
                    return;
                case 2:
                    contentValues.put(crVar.a(), Integer.valueOf(field.getInt(obj)));
                    return;
                case 3:
                    contentValues.put(crVar.a(), Float.valueOf(field.getFloat(obj)));
                    return;
                case 4:
                    contentValues.put(crVar.a(), Double.valueOf(field.getDouble(obj)));
                    return;
                case 5:
                    contentValues.put(crVar.a(), Long.valueOf(field.getLong(obj)));
                    return;
                case 6:
                    contentValues.put(crVar.a(), (String) field.get(obj));
                    return;
                case 7:
                    try {
                        contentValues.put(crVar.a(), (byte[]) field.get(obj));
                        return;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private ContentValues a(Object obj, cq cqVar) {
        ContentValues contentValues = new ContentValues();
        for (Field field : a(obj.getClass(), cqVar.b())) {
            field.setAccessible(true);
            a(obj, field, contentValues);
        }
        return contentValues;
    }

    private Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        if (z) {
            return cls.getSuperclass().getDeclaredFields();
        }
        return cls.getDeclaredFields();
    }

    private SQLiteDatabase a(boolean z) {
        try {
            if (this.b == null) {
                this.b = this.f4379a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (!z) {
                ci.a(th, "dbs", "grd");
            } else {
                th.printStackTrace();
            }
        }
        return this.b;
    }

    private SQLiteDatabase b(boolean z) {
        try {
            if (this.b == null || this.b.isReadOnly()) {
                if (this.b != null) {
                    this.b.close();
                }
                this.b = this.f4379a.getWritableDatabase();
            }
        } catch (Throwable th) {
            ci.a(th, "dbs", "gwd");
        }
        return this.b;
    }

    private <T> String a(cq cqVar) {
        if (cqVar == null) {
            return null;
        }
        return cqVar.a();
    }

    private <T> cq b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(cq.class);
        if (!a(annotation)) {
            return null;
        }
        return (cq) annotation;
    }
}
