package com.tencent.smtt.sdk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.j;
import java.io.File;

public class x {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9194a = CookieManager.LOGTAG;
    static File b;

    public static File a(Context context) {
        if (b == null && context != null) {
            b = new File(context.getDir("webview", 0), "Cookies");
        }
        if (b == null) {
            b = new File("/data/data/" + context.getPackageName() + File.separator + "app_webview" + File.separator + "Cookies");
        }
        return b;
    }

    private static String a(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + str, (String[]) null);
        int count = rawQuery.getCount();
        int columnCount = rawQuery.getColumnCount();
        StringBuilder sb = new StringBuilder();
        sb.append("raws:" + count + ",columns:" + columnCount + "\n");
        if (count <= 0 || !rawQuery.moveToFirst()) {
            return sb.toString();
        }
        do {
            sb.append("\n");
            for (int i = 0; i < columnCount; i++) {
                try {
                    sb.append(rawQuery.getString(i));
                    sb.append(",");
                } catch (Exception unused) {
                }
            }
            sb.append("\n");
        } while (rawQuery.moveToNext());
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r4.isOpen() != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005a, code lost:
        if (r4.isOpen() != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005c, code lost:
        r4.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.String> a(android.database.sqlite.SQLiteDatabase r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "select * from sqlite_master where type='table'"
            android.database.Cursor r2 = r4.rawQuery(r2, r0)     // Catch:{ Throwable -> 0x004e, all -> 0x003a }
            boolean r0 = r2.moveToFirst()     // Catch:{ Throwable -> 0x004f, all -> 0x0038 }
            if (r0 == 0) goto L_0x002a
        L_0x0015:
            r0 = 1
            java.lang.String r0 = r2.getString(r0)     // Catch:{ Throwable -> 0x004f, all -> 0x0038 }
            r3 = 4
            r2.getString(r3)     // Catch:{ Throwable -> 0x004f, all -> 0x0038 }
            r1.add(r0)     // Catch:{ Throwable -> 0x004f, all -> 0x0038 }
            a(r4, r0)     // Catch:{ Throwable -> 0x004f, all -> 0x0038 }
            boolean r0 = r2.moveToNext()     // Catch:{ Throwable -> 0x004f, all -> 0x0038 }
            if (r0 != 0) goto L_0x0015
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.close()
        L_0x002f:
            if (r4 == 0) goto L_0x005f
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x005f
            goto L_0x005c
        L_0x0038:
            r0 = move-exception
            goto L_0x003d
        L_0x003a:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x003d:
            if (r2 == 0) goto L_0x0042
            r2.close()
        L_0x0042:
            if (r4 == 0) goto L_0x004d
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x004d
            r4.close()
        L_0x004d:
            throw r0
        L_0x004e:
            r2 = r0
        L_0x004f:
            if (r2 == 0) goto L_0x0054
            r2.close()
        L_0x0054:
            if (r4 == 0) goto L_0x005f
            boolean r0 = r4.isOpen()
            if (r0 == 0) goto L_0x005f
        L_0x005c:
            r4.close()
        L_0x005f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.x.a(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00be, code lost:
        if (r12.isOpen() != false) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c0, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f4, code lost:
        if (r12.isOpen() != false) goto L_0x00c0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00fd A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0157  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r8, com.tencent.smtt.sdk.CookieManager.a r9, java.lang.String r10, boolean r11, boolean r12) {
        /*
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            com.tencent.smtt.sdk.CookieManager$a r12 = com.tencent.smtt.sdk.CookieManager.a.MODE_KEYS
            if (r9 != r12) goto L_0x000e
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 == 0) goto L_0x000e
            return
        L_0x000e:
            java.lang.String r12 = ","
            java.lang.String[] r10 = r10.split(r12)
            if (r10 == 0) goto L_0x0166
            int r12 = r10.length
            r0 = 1
            if (r12 >= r0) goto L_0x001c
            goto L_0x0166
        L_0x001c:
            android.database.sqlite.SQLiteDatabase r12 = c(r8)
            if (r12 != 0) goto L_0x0023
            return
        L_0x0023:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r2 = 0
            java.lang.String r3 = "select * from cookies"
            android.database.Cursor r3 = r12.rawQuery(r3, r2)     // Catch:{ Throwable -> 0x00ce }
            int r2 = r3.getCount()     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            if (r2 <= 0) goto L_0x00b3
            boolean r2 = r3.moveToFirst()     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            if (r2 == 0) goto L_0x00b3
        L_0x003b:
            java.lang.String r2 = "host_key"
            int r2 = r3.getColumnIndex(r2)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r2 = r3.getString(r2)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            com.tencent.smtt.sdk.CookieManager$a r4 = com.tencent.smtt.sdk.CookieManager.a.MODE_KEYS     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            if (r9 != r4) goto L_0x005e
            int r4 = r10.length     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            r5 = 0
            r6 = 0
        L_0x004c:
            if (r6 >= r4) goto L_0x005b
            r7 = r10[r6]     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            boolean r7 = r2.equals(r7)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            if (r7 == 0) goto L_0x0058
            r5 = 1
            goto L_0x005b
        L_0x0058:
            int r6 = r6 + 1
            goto L_0x004c
        L_0x005b:
            if (r5 != 0) goto L_0x005e
            goto L_0x00ad
        L_0x005e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            r4.<init>()     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = "value"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = r3.getString(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            r4.append(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = ";"
            r4.append(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = "name"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = r3.getString(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            r4.append(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = ";"
            r4.append(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = "expires_utc"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            int r5 = r3.getInt(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            r4.append(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = ";"
            r4.append(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = "priority"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            int r5 = r3.getInt(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            r4.append(r5)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            r1.put(r2, r4)     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
        L_0x00ad:
            boolean r2 = r3.moveToNext()     // Catch:{ Throwable -> 0x00c7, all -> 0x00c4 }
            if (r2 != 0) goto L_0x003b
        L_0x00b3:
            if (r3 == 0) goto L_0x00b8
            r3.close()
        L_0x00b8:
            if (r12 == 0) goto L_0x00f7
            boolean r9 = r12.isOpen()
            if (r9 == 0) goto L_0x00f7
        L_0x00c0:
            r12.close()
            goto L_0x00f7
        L_0x00c4:
            r8 = move-exception
            goto L_0x0155
        L_0x00c7:
            r9 = move-exception
            r2 = r3
            goto L_0x00cf
        L_0x00ca:
            r8 = move-exception
            r3 = r2
            goto L_0x0155
        L_0x00ce:
            r9 = move-exception
        L_0x00cf:
            java.lang.String r10 = f9194a     // Catch:{ all -> 0x00ca }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ca }
            r3.<init>()     // Catch:{ all -> 0x00ca }
            java.lang.String r4 = "getCookieDBVersion exception:"
            r3.append(r4)     // Catch:{ all -> 0x00ca }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00ca }
            r3.append(r9)     // Catch:{ all -> 0x00ca }
            java.lang.String r9 = r3.toString()     // Catch:{ all -> 0x00ca }
            android.util.Log.e(r10, r9)     // Catch:{ all -> 0x00ca }
            if (r2 == 0) goto L_0x00ee
            r2.close()
        L_0x00ee:
            if (r12 == 0) goto L_0x00f7
            boolean r9 = r12.isOpen()
            if (r9 == 0) goto L_0x00f7
            goto L_0x00c0
        L_0x00f7:
            boolean r9 = r1.isEmpty()
            if (r9 == 0) goto L_0x00fe
            return
        L_0x00fe:
            b(r8)
            java.util.Set r9 = r1.entrySet()
            java.util.Iterator r9 = r9.iterator()
        L_0x0109:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0129
            java.lang.Object r10 = r9.next()
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10
            java.lang.Object r12 = r10.getKey()
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r10 = r10.getValue()
            java.lang.String r10 = (java.lang.String) r10
            com.tencent.smtt.sdk.CookieManager r1 = com.tencent.smtt.sdk.CookieManager.getInstance()
            r1.setCookie((java.lang.String) r12, (java.lang.String) r10, (boolean) r0)
            goto L_0x0109
        L_0x0129:
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 21
            if (r9 < r10) goto L_0x0137
            com.tencent.smtt.sdk.CookieManager r9 = com.tencent.smtt.sdk.CookieManager.getInstance()
            r9.flush()
            goto L_0x013e
        L_0x0137:
            com.tencent.smtt.sdk.CookieSyncManager r9 = com.tencent.smtt.sdk.CookieSyncManager.getInstance()
            r9.sync()
        L_0x013e:
            if (r11 == 0) goto L_0x0154
            android.database.sqlite.SQLiteDatabase r9 = c(r8)
            a((android.database.sqlite.SQLiteDatabase) r9)
            int r9 = d(r8)
            r10 = -1
            if (r9 == r10) goto L_0x0154
            com.tencent.smtt.sdk.CookieManager.getInstance()
            com.tencent.smtt.sdk.CookieManager.setROMCookieDBVersion(r8, r9)
        L_0x0154:
            return
        L_0x0155:
            if (r3 == 0) goto L_0x015a
            r3.close()
        L_0x015a:
            if (r12 == 0) goto L_0x0165
            boolean r9 = r12.isOpen()
            if (r9 == 0) goto L_0x0165
            r12.close()
        L_0x0165:
            throw r8
        L_0x0166:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.x.a(android.content.Context, com.tencent.smtt.sdk.CookieManager$a, java.lang.String, boolean, boolean):void");
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        j.a(a(context), false);
        return true;
    }

    public static SQLiteDatabase c(Context context) {
        File a2;
        SQLiteDatabase sQLiteDatabase;
        if (context == null || (a2 = a(context)) == null) {
            return null;
        }
        try {
            sQLiteDatabase = SQLiteDatabase.openDatabase(a2.getAbsolutePath(), (SQLiteDatabase.CursorFactory) null, 0);
        } catch (Exception unused) {
            sQLiteDatabase = null;
        }
        if (sQLiteDatabase == null) {
            TbsLog.i(f9194a, "dbPath is not exist!");
        }
        return sQLiteDatabase;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        if (r4.isOpen() != false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0083, code lost:
        if (r4.isOpen() != false) goto L_0x0057;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int d(android.content.Context r4) {
        /*
            java.lang.System.currentTimeMillis()
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r4 = c(r4)     // Catch:{ Throwable -> 0x0076, all -> 0x0062 }
            if (r4 != 0) goto L_0x0018
            r0 = -1
            if (r4 == 0) goto L_0x0017
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0017
            r4.close()
        L_0x0017:
            return r0
        L_0x0018:
            java.lang.String r2 = "select * from meta"
            android.database.Cursor r2 = r4.rawQuery(r2, r1)     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            int r1 = r2.getCount()     // Catch:{ Throwable -> 0x0078, all -> 0x005b }
            r2.getColumnCount()     // Catch:{ Throwable -> 0x0078, all -> 0x005b }
            if (r1 <= 0) goto L_0x004a
            boolean r1 = r2.moveToFirst()     // Catch:{ Throwable -> 0x0078, all -> 0x005b }
            if (r1 == 0) goto L_0x004a
        L_0x002d:
            java.lang.String r1 = r2.getString(r0)     // Catch:{ Throwable -> 0x0078, all -> 0x005b }
            java.lang.String r3 = "version"
            boolean r1 = r1.equals(r3)     // Catch:{ Throwable -> 0x0078, all -> 0x005b }
            if (r1 == 0) goto L_0x0044
            r1 = 1
            java.lang.String r1 = r2.getString(r1)     // Catch:{ Throwable -> 0x0078, all -> 0x005b }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x0078, all -> 0x005b }
            r0 = r1
            goto L_0x004a
        L_0x0044:
            boolean r1 = r2.moveToNext()     // Catch:{ Throwable -> 0x0078, all -> 0x005b }
            if (r1 != 0) goto L_0x002d
        L_0x004a:
            if (r2 == 0) goto L_0x004f
            r2.close()
        L_0x004f:
            if (r4 == 0) goto L_0x0086
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0086
        L_0x0057:
            r4.close()
            goto L_0x0086
        L_0x005b:
            r0 = move-exception
            goto L_0x0065
        L_0x005d:
            r0 = move-exception
            r2 = r1
            goto L_0x0065
        L_0x0060:
            r2 = r1
            goto L_0x0078
        L_0x0062:
            r0 = move-exception
            r4 = r1
            r2 = r4
        L_0x0065:
            if (r2 == 0) goto L_0x006a
            r2.close()
        L_0x006a:
            if (r4 == 0) goto L_0x0075
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0075
            r4.close()
        L_0x0075:
            throw r0
        L_0x0076:
            r4 = r1
            r2 = r4
        L_0x0078:
            if (r2 == 0) goto L_0x007d
            r2.close()
        L_0x007d:
            if (r4 == 0) goto L_0x0086
            boolean r1 = r4.isOpen()
            if (r1 == 0) goto L_0x0086
            goto L_0x0057
        L_0x0086:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.x.d(android.content.Context):int");
    }
}
