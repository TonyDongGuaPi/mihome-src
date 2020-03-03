package com.alipay.sdk.tid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alipay.sdk.util.c;
import java.lang.ref.WeakReference;

final class a extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1123a = "msp.db";
    private static final int b = 1;
    private WeakReference<Context> c;

    a(Context context) {
        super(context, f1123a, (SQLiteDatabase.CursorFactory) null, 1);
        this.c = new WeakReference<>(context);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
    }

    /* access modifiers changed from: package-private */
    public void a() {
        SQLiteDatabase sQLiteDatabase;
        Exception e;
        try {
            sQLiteDatabase = getWritableDatabase();
            try {
                sQLiteDatabase.execSQL("drop table if exists tb_tid");
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    c.a(e);
                    return;
                } catch (Throwable th) {
                    th = th;
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e3) {
            Exception exc = e3;
            sQLiteDatabase = null;
            e = exc;
            c.a(e);
            if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                return;
            }
            sQLiteDatabase.close();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            sQLiteDatabase = null;
            th = th3;
            sQLiteDatabase.close();
            throw th;
        }
        sQLiteDatabase.close();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r2.isOpen() != false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r2.close();
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0058, code lost:
        if (r2.isOpen() != false) goto L_0x002d;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch:{ Exception -> 0x004b, all -> 0x0038 }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            java.lang.String r5 = r4.c(r5, r6)     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            r6 = 0
            r3[r6] = r5     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            boolean r0 = r5.moveToFirst()     // Catch:{ Exception -> 0x004d, all -> 0x0031 }
            if (r0 == 0) goto L_0x0020
            java.lang.String r6 = r5.getString(r6)     // Catch:{ Exception -> 0x004d, all -> 0x0031 }
            r1 = r6
        L_0x0020:
            if (r5 == 0) goto L_0x0025
            r5.close()
        L_0x0025:
            if (r2 == 0) goto L_0x005b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005b
        L_0x002d:
            r2.close()
            goto L_0x005b
        L_0x0031:
            r6 = move-exception
            r1 = r5
            goto L_0x003a
        L_0x0034:
            r6 = move-exception
            goto L_0x003a
        L_0x0036:
            r5 = r1
            goto L_0x004d
        L_0x0038:
            r6 = move-exception
            r2 = r1
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()
        L_0x003f:
            if (r2 == 0) goto L_0x004a
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x004a
            r2.close()
        L_0x004a:
            throw r6
        L_0x004b:
            r5 = r1
            r2 = r5
        L_0x004d:
            if (r5 == 0) goto L_0x0052
            r5.close()
        L_0x0052:
            if (r2 == 0) goto L_0x005b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005b
            goto L_0x002d
        L_0x005b:
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 != 0) goto L_0x0071
            java.lang.ref.WeakReference<android.content.Context> r5 = r4.c
            java.lang.Object r5 = r5.get()
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r5 = com.alipay.sdk.util.a.c(r5)
            java.lang.String r1 = com.alipay.sdk.encrypt.b.b(r1, r5)
        L_0x0071:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r2.isOpen() != false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r2.close();
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0058, code lost:
        if (r2.isOpen() != false) goto L_0x002d;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select key_tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch:{ Exception -> 0x004b, all -> 0x0038 }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            java.lang.String r5 = r4.c(r5, r6)     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            r6 = 0
            r3[r6] = r5     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            boolean r0 = r5.moveToFirst()     // Catch:{ Exception -> 0x004d, all -> 0x0031 }
            if (r0 == 0) goto L_0x0020
            java.lang.String r6 = r5.getString(r6)     // Catch:{ Exception -> 0x004d, all -> 0x0031 }
            r1 = r6
        L_0x0020:
            if (r5 == 0) goto L_0x0025
            r5.close()
        L_0x0025:
            if (r2 == 0) goto L_0x005b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005b
        L_0x002d:
            r2.close()
            goto L_0x005b
        L_0x0031:
            r6 = move-exception
            r1 = r5
            goto L_0x003a
        L_0x0034:
            r6 = move-exception
            goto L_0x003a
        L_0x0036:
            r5 = r1
            goto L_0x004d
        L_0x0038:
            r6 = move-exception
            r2 = r1
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()
        L_0x003f:
            if (r2 == 0) goto L_0x004a
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x004a
            r2.close()
        L_0x004a:
            throw r6
        L_0x004b:
            r5 = r1
            r2 = r5
        L_0x004d:
            if (r5 == 0) goto L_0x0052
            r5.close()
        L_0x0052:
            if (r2 == 0) goto L_0x005b
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005b
            goto L_0x002d
        L_0x005b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.b(java.lang.String, java.lang.String):java.lang.String");
    }

    private String c(String str, String str2) {
        return str + str2;
    }
}
