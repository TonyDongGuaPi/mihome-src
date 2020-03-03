package com.xiaomi.phonenum.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.utils.Logger;
import com.xiaomi.phonenum.utils.LoggerManager;

public class PhoneNumDBHelper extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12556a = "PhoneNumberDBHelper";
    private static final String c = "phone_num3.db";
    private static final int d = 1;
    private static final String e = "iccid";
    private static final String f = "number";
    private static final String g = "number_hash";
    private static final String h = "update_time";
    private static final String i = "token";
    private static final String j = "copywriter";
    private static final String k = "operator_link";
    private static final String l = "phone_level";
    private static final String m = "phone_number";
    private static final String n = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY, %s TEXT not null unique, %s TEXT not null, %s TEXT not null, %s TEXT not null, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)", new Object[]{"phone_number", "_id", "iccid", "number", g, "update_time", "token", j, k, l});
    private static volatile PhoneNumDBHelper p = null;
    private Logger b = LoggerManager.a();
    private Context o;

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public static synchronized PhoneNumDBHelper a(Context context) {
        PhoneNumDBHelper phoneNumDBHelper;
        synchronized (PhoneNumDBHelper.class) {
            if (p == null) {
                p = new PhoneNumDBHelper(context.getApplicationContext());
            }
            phoneNumDBHelper = p;
        }
        return phoneNumDBHelper;
    }

    PhoneNumDBHelper(Context context) {
        super(context, c, (SQLiteDatabase.CursorFactory) null, 1);
        this.o = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(n);
    }

    public synchronized void a(@NonNull PhoneNum phoneNum) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("iccid", phoneNum.e);
        contentValues.put("number", phoneNum.c);
        contentValues.put(g, phoneNum.d);
        contentValues.put("update_time", phoneNum.h);
        contentValues.put("token", phoneNum.f);
        contentValues.put(j, phoneNum.i);
        contentValues.put(k, phoneNum.j);
        contentValues.put(l, Integer.valueOf(phoneNum.m));
        if (0 < getWritableDatabase().replace("phone_number", (String) null, contentValues)) {
            this.b.a(f12556a, "1 entry updated in phone_number database");
        } else {
            Logger logger = this.b;
            logger.a(f12556a, "updatePhoneNum failed:" + phoneNum);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d9, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e2, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e8 A[SYNTHETIC, Splitter:B:37:0x00e8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.xiaomi.phonenum.bean.PhoneNum a(@android.support.annotation.NonNull java.lang.String r19, int r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            monitor-enter(r18)
            android.database.sqlite.SQLiteDatabase r2 = r18.getWritableDatabase()     // Catch:{ all -> 0x00ec }
            java.lang.String r3 = "phone_number"
            r4 = 7
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = "number"
            r11 = 0
            r4[r11] = r5     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = "number_hash"
            r12 = 1
            r4[r12] = r5     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = "token"
            r13 = 2
            r4[r13] = r5     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = "phone_level"
            r14 = 3
            r4[r14] = r5     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = "update_time"
            r15 = 4
            r4[r15] = r5     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = "copywriter"
            r9 = 5
            r4[r9] = r5     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = "operator_link"
            r8 = 6
            r4[r8] = r5     // Catch:{ all -> 0x00e4 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e4 }
            r5.<init>()     // Catch:{ all -> 0x00e4 }
            java.lang.String r6 = "iccid=\""
            r5.append(r6)     // Catch:{ all -> 0x00e4 }
            r5.append(r0)     // Catch:{ all -> 0x00e4 }
            java.lang.String r6 = "\""
            r5.append(r6)     // Catch:{ all -> 0x00e4 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00e4 }
            r6 = 0
            r7 = 0
            r16 = 0
            r17 = 0
            r10 = 6
            r8 = r16
            r10 = 5
            r9 = r17
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00e4 }
            if (r2 == 0) goto L_0x00dc
            int r3 = r2.getCount()     // Catch:{ all -> 0x00da }
            if (r3 <= 0) goto L_0x00dc
            r2.moveToPosition(r11)     // Catch:{ all -> 0x00da }
            java.lang.String r3 = r2.getString(r11)     // Catch:{ all -> 0x00da }
            java.lang.String r4 = r2.getString(r12)     // Catch:{ all -> 0x00da }
            java.lang.String r5 = r2.getString(r13)     // Catch:{ all -> 0x00da }
            int r6 = r2.getInt(r14)     // Catch:{ all -> 0x00da }
            java.lang.String r7 = r2.getString(r15)     // Catch:{ all -> 0x00da }
            boolean r8 = r2.isNull(r10)     // Catch:{ all -> 0x00da }
            if (r8 == 0) goto L_0x007f
            r8 = 6
            r10 = 0
            goto L_0x0084
        L_0x007f:
            java.lang.String r10 = r2.getString(r10)     // Catch:{ all -> 0x00da }
            r8 = 6
        L_0x0084:
            boolean r9 = r2.isNull(r8)     // Catch:{ all -> 0x00da }
            if (r9 == 0) goto L_0x008c
            r8 = 0
            goto L_0x0090
        L_0x008c:
            java.lang.String r8 = r2.getString(r8)     // Catch:{ all -> 0x00da }
        L_0x0090:
            com.xiaomi.phonenum.utils.Logger r9 = r1.b     // Catch:{ all -> 0x00da }
            java.lang.String r13 = "PhoneNumberDBHelper"
            java.lang.String r14 = "phoneNum loaded from db"
            r9.a(r13, r14)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r9 = new com.xiaomi.phonenum.bean.PhoneNum$Builder     // Catch:{ all -> 0x00da }
            r9.<init>()     // Catch:{ all -> 0x00da }
            r13 = r20
            com.xiaomi.phonenum.bean.PhoneNum$Builder r9 = r9.b((int) r13)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r9.e((java.lang.String) r0)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r0.a((java.lang.String) r3)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r0.b((java.lang.String) r4)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r0.f((java.lang.String) r7)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r0.d((java.lang.String) r5)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r0.g((java.lang.String) r10)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r0.h((java.lang.String) r8)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.obtain.PhoneLevel r3 = com.xiaomi.phonenum.obtain.PhoneLevel.SMS_VERIFY     // Catch:{ all -> 0x00da }
            int r3 = r3.value     // Catch:{ all -> 0x00da }
            if (r6 < r3) goto L_0x00c7
            r11 = 1
        L_0x00c7:
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r0.a((boolean) r11)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum$Builder r0 = r0.c((int) r6)     // Catch:{ all -> 0x00da }
            com.xiaomi.phonenum.bean.PhoneNum r0 = r0.a()     // Catch:{ all -> 0x00da }
            if (r2 == 0) goto L_0x00d8
            r2.close()     // Catch:{ all -> 0x00ec }
        L_0x00d8:
            monitor-exit(r18)
            return r0
        L_0x00da:
            r0 = move-exception
            goto L_0x00e6
        L_0x00dc:
            if (r2 == 0) goto L_0x00e1
            r2.close()     // Catch:{ all -> 0x00ec }
        L_0x00e1:
            monitor-exit(r18)
            r2 = 0
            return r2
        L_0x00e4:
            r0 = move-exception
            r2 = 0
        L_0x00e6:
            if (r2 == 0) goto L_0x00eb
            r2.close()     // Catch:{ all -> 0x00ec }
        L_0x00eb:
            throw r0     // Catch:{ all -> 0x00ec }
        L_0x00ec:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.phonenum.db.PhoneNumDBHelper.a(java.lang.String, int):com.xiaomi.phonenum.bean.PhoneNum");
    }

    public synchronized boolean a(@NonNull String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase.delete("phone_number", "iccid=\"" + str + "\"", (String[]) null) > 0) {
            this.b.a(f12556a, "1 entry deletePhoneNum from phone_number database");
            return true;
        }
        Logger logger = this.b;
        logger.a(f12556a, "deletePhoneNum failed:" + str);
        return false;
    }
}
