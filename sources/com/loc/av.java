package com.loc;

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
import java.util.Map;

public final class av {
    private static Map<Class<? extends au>, au> d = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    private ay f6492a;
    private SQLiteDatabase b;
    private au c;

    public av(Context context, au auVar) {
        try {
            this.f6492a = new ay(context.getApplicationContext(), auVar.a(), auVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = auVar;
    }

    private static ContentValues a(Object obj, aw awVar) {
        ContentValues contentValues = new ContentValues();
        for (Field field : a(obj.getClass(), awVar.b())) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ax.class);
            if (annotation != null) {
                ax axVar = (ax) annotation;
                switch (axVar.b()) {
                    case 1:
                        contentValues.put(axVar.a(), Short.valueOf(field.getShort(obj)));
                        break;
                    case 2:
                        contentValues.put(axVar.a(), Integer.valueOf(field.getInt(obj)));
                        break;
                    case 3:
                        contentValues.put(axVar.a(), Float.valueOf(field.getFloat(obj)));
                        break;
                    case 4:
                        contentValues.put(axVar.a(), Double.valueOf(field.getDouble(obj)));
                        break;
                    case 5:
                        contentValues.put(axVar.a(), Long.valueOf(field.getLong(obj)));
                        break;
                    case 6:
                        contentValues.put(axVar.a(), (String) field.get(obj));
                        break;
                    case 7:
                        try {
                            contentValues.put(axVar.a(), (byte[]) field.get(obj));
                            break;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            break;
                        }
                }
            }
        }
        return contentValues;
    }

    private SQLiteDatabase a() {
        try {
            if (this.b == null || this.b.isReadOnly()) {
                if (this.b != null) {
                    this.b.close();
                }
                this.b = this.f6492a.getWritableDatabase();
            }
        } catch (Throwable th) {
            an.a(th, "dbs", "gwd");
        }
        return this.b;
    }

    private SQLiteDatabase a(boolean z) {
        try {
            if (this.b == null) {
                this.b = this.f6492a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (!z) {
                an.a(th, "dbs", "grd");
            } else {
                th.printStackTrace();
            }
        }
        return this.b;
    }

    public static synchronized au a(Class<? extends au> cls) throws IllegalAccessException, InstantiationException {
        au auVar;
        synchronized (av.class) {
            if (d.get(cls) == null) {
                d.put(cls, cls.newInstance());
            }
            auVar = d.get(cls);
        }
        return auVar;
    }

    private static <T> T a(Cursor cursor, Class<T> cls, aw awVar) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object obj;
        Field[] a2 = a((Class<?>) cls, awVar.b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a2) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ax.class);
            if (annotation != null) {
                ax axVar = (ax) annotation;
                int b2 = axVar.b();
                int columnIndex = cursor.getColumnIndex(axVar.a());
                switch (b2) {
                    case 1:
                        obj = Short.valueOf(cursor.getShort(columnIndex));
                        break;
                    case 2:
                        obj = Integer.valueOf(cursor.getInt(columnIndex));
                        break;
                    case 3:
                        obj = Float.valueOf(cursor.getFloat(columnIndex));
                        break;
                    case 4:
                        obj = Double.valueOf(cursor.getDouble(columnIndex));
                        break;
                    case 5:
                        obj = Long.valueOf(cursor.getLong(columnIndex));
                        break;
                    case 6:
                        obj = cursor.getString(columnIndex);
                        break;
                    case 7:
                        obj = cursor.getBlob(columnIndex);
                        break;
                }
                field.set(newInstance, obj);
            }
        }
        return newInstance;
    }

    private static <T> String a(aw awVar) {
        if (awVar == null) {
            return null;
        }
        return awVar.a();
    }

    public static String a(Map<String, String> map) {
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

    private static Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        return z ? cls.getSuperclass().getDeclaredFields() : cls.getDeclaredFields();
    }

    private static <T> aw b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(aw.class);
        if (!(annotation != null)) {
            return null;
        }
        return (aw) annotation;
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
    public final <T> java.util.List<T> a(java.lang.String r13, java.lang.Class<T> r14, boolean r15) {
        /*
            r12 = this;
            com.loc.au r0 = r12.c
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0106 }
            r1.<init>()     // Catch:{ all -> 0x0106 }
            com.loc.aw r2 = b(r14)     // Catch:{ all -> 0x0106 }
            java.lang.String r4 = a((com.loc.aw) r2)     // Catch:{ all -> 0x0106 }
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
            com.loc.an.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x0106 }
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
            com.loc.an.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r15)     // Catch:{ all -> 0x0106 }
        L_0x0066:
            monitor-exit(r0)     // Catch:{ all -> 0x0106 }
            return r1
        L_0x0068:
            r14 = move-exception
            goto L_0x00a7
        L_0x006a:
            boolean r3 = r13.moveToNext()     // Catch:{ Throwable -> 0x0068 }
            if (r3 == 0) goto L_0x0078
            java.lang.Object r3 = a((android.database.Cursor) r13, r14, (com.loc.aw) r2)     // Catch:{ Throwable -> 0x0068 }
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
            com.loc.an.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x0106 }
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
            com.loc.an.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r15)     // Catch:{ all -> 0x0106 }
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
            com.loc.an.a((java.lang.Throwable) r14, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x00b2 }
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
            com.loc.an.a((java.lang.Throwable) r13, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x0106 }
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
            com.loc.an.a((java.lang.Throwable) r13, (java.lang.String) r15, (java.lang.String) r1)     // Catch:{ all -> 0x0106 }
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
            com.loc.an.a((java.lang.Throwable) r13, (java.lang.String) r14, (java.lang.String) r2)     // Catch:{ all -> 0x0106 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.loc.av.a(java.lang.String, java.lang.Class, boolean):java.util.List");
    }

    /* JADX INFO: finally extract failed */
    public final <T> void a(T t) {
        synchronized (this.c) {
            this.b = a();
            if (this.b != null) {
                try {
                    SQLiteDatabase sQLiteDatabase = this.b;
                    aw b2 = b(t.getClass());
                    String a2 = a(b2);
                    if (!TextUtils.isEmpty(a2) && t != null) {
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.insert(a2, (String) null, a((Object) t, b2));
                        }
                    }
                    if (this.b != null) {
                        this.b.close();
                        this.b = null;
                    }
                } catch (Throwable th) {
                    try {
                        an.a(th, "dbs", "itd");
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

    public final void a(Object obj, String str) {
        synchronized (this.c) {
            if (a(str, obj.getClass(), false).size() == 0) {
                a(obj);
            } else {
                a(str, obj);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0058, code lost:
        if (r4.b != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005a, code lost:
        r4.b.close();
        r4.b = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0061, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0063, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0007, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:3:0x0005, B:27:0x0047] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> void a(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            com.loc.au r0 = r4.c
            monitor-enter(r0)
            if (r6 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x0007:
            r5 = move-exception
            goto L_0x0062
        L_0x0009:
            java.lang.Class r1 = r6.getClass()     // Catch:{ all -> 0x0007 }
            com.loc.aw r1 = b(r1)     // Catch:{ all -> 0x0007 }
            java.lang.String r2 = a((com.loc.aw) r1)     // Catch:{ all -> 0x0007 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0007 }
            if (r3 == 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x001d:
            android.content.ContentValues r6 = a((java.lang.Object) r6, (com.loc.aw) r1)     // Catch:{ all -> 0x0007 }
            android.database.sqlite.SQLiteDatabase r1 = r4.a()     // Catch:{ all -> 0x0007 }
            r4.b = r1     // Catch:{ all -> 0x0007 }
            android.database.sqlite.SQLiteDatabase r1 = r4.b     // Catch:{ all -> 0x0007 }
            if (r1 != 0) goto L_0x002d
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x002d:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r3 = r4.b     // Catch:{ Throwable -> 0x0041 }
            r3.update(r2, r6, r5, r1)     // Catch:{ Throwable -> 0x0041 }
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch:{ all -> 0x0007 }
            if (r5 == 0) goto L_0x0054
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch:{ all -> 0x0007 }
            r5.close()     // Catch:{ all -> 0x0007 }
        L_0x003c:
            r4.b = r1     // Catch:{ all -> 0x0007 }
            goto L_0x0054
        L_0x003f:
            r5 = move-exception
            goto L_0x0056
        L_0x0041:
            r5 = move-exception
            java.lang.String r6 = "dbs"
            java.lang.String r2 = "udd"
            com.loc.an.a((java.lang.Throwable) r5, (java.lang.String) r6, (java.lang.String) r2)     // Catch:{ all -> 0x003f }
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch:{ all -> 0x0007 }
            if (r5 == 0) goto L_0x0054
            android.database.sqlite.SQLiteDatabase r5 = r4.b     // Catch:{ all -> 0x0007 }
            r5.close()     // Catch:{ all -> 0x0007 }
            goto L_0x003c
        L_0x0054:
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            return
        L_0x0056:
            android.database.sqlite.SQLiteDatabase r6 = r4.b     // Catch:{ all -> 0x0007 }
            if (r6 == 0) goto L_0x0061
            android.database.sqlite.SQLiteDatabase r6 = r4.b     // Catch:{ all -> 0x0007 }
            r6.close()     // Catch:{ all -> 0x0007 }
            r4.b = r1     // Catch:{ all -> 0x0007 }
        L_0x0061:
            throw r5     // Catch:{ all -> 0x0007 }
        L_0x0062:
            monitor-exit(r0)     // Catch:{ all -> 0x0007 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.av.a(java.lang.String, java.lang.Object):void");
    }
}
