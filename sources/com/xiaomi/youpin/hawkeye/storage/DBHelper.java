package com.xiaomi.youpin.hawkeye.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.utils.DeviceUtils;
import com.xiaomi.youpin.hawkeye.utils.HLog;

public class DBHelper extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23368a = "DBHelper";
    public static final String b = "hawkeye.db";
    public static final int c = 1;
    public static final String d = "CREATE TABLE IF NOT EXISTS ";
    public static final String e = " INTEGER );";
    public static final String f = " TEXT,";
    public static final String g = "hapm";
    public static final String h = "_id";
    public static final String i = "timestamp";
    public static final String j = "statType";
    public static final String k = "system";
    public static final String l = "taskName";
    public static final String m = "statInfo";
    public static final String n = "platform";
    public static final String o = "isUpload";
    private SQLiteDatabase p;
    private BaseInfo.SystemInfo q;

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public DBHelper(Context context) {
        super(context, b, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        HLog.b(f23368a, "db create ******* sql : " + a());
        sQLiteDatabase.execSQL(a());
    }

    public String a() {
        return TextUtils.concat(new CharSequence[]{d, g, " ( ", "_id", " INTEGER PRIMARY KEY AUTOINCREMENT,", "statType", f, "timestamp", f, "system", f, l, f, "statInfo", f, "platform", f, o, e}).toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(com.xiaomi.youpin.hawkeye.entity.BaseInfo r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 != 0) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            r1 = -1
            android.database.sqlite.SQLiteDatabase r3 = r7.p     // Catch:{ Exception -> 0x0082 }
            if (r3 != 0) goto L_0x0012
            android.database.sqlite.SQLiteDatabase r3 = r7.getWritableDatabase()     // Catch:{ Exception -> 0x0082 }
            r7.p = r3     // Catch:{ Exception -> 0x0082 }
        L_0x0012:
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ Exception -> 0x0082 }
            r3.<init>()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "timestamp"
            long r5 = r8.timestamp     // Catch:{ Exception -> 0x0082 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0082 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "statType"
            java.lang.String r5 = r8.mStatType     // Catch:{ Exception -> 0x0082 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "system"
            java.lang.String r5 = r7.c()     // Catch:{ Exception -> 0x0082 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "taskName"
            java.lang.String r5 = r8.mTaskName     // Catch:{ Exception -> 0x0082 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "statInfo"
            java.lang.String r5 = r8.toJson()     // Catch:{ Exception -> 0x0082 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "platform"
            java.lang.String r5 = "android"
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "isUpload"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0082 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0082 }
            android.database.sqlite.SQLiteDatabase r4 = r7.p     // Catch:{ Exception -> 0x0082 }
            java.lang.String r5 = "hapm"
            r6 = 0
            long r3 = r4.insert(r5, r6, r3)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = "DBHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007e }
            r2.<init>()     // Catch:{ Exception -> 0x007e }
            java.lang.String r5 = " insert : id   =   "
            r2.append(r5)     // Catch:{ Exception -> 0x007e }
            r2.append(r3)     // Catch:{ Exception -> 0x007e }
            java.lang.String r5 = " 数据为    :  "
            r2.append(r5)     // Catch:{ Exception -> 0x007e }
            java.lang.String r8 = r8.toJson()     // Catch:{ Exception -> 0x007e }
            r2.append(r8)     // Catch:{ Exception -> 0x007e }
            java.lang.String r8 = r2.toString()     // Catch:{ Exception -> 0x007e }
            com.xiaomi.youpin.hawkeye.utils.HLog.b(r1, r8)     // Catch:{ Exception -> 0x007e }
            goto L_0x0087
        L_0x007e:
            r8 = move-exception
            goto L_0x0084
        L_0x0080:
            r8 = move-exception
            goto L_0x0090
        L_0x0082:
            r8 = move-exception
            r3 = r1
        L_0x0084:
            r8.printStackTrace()     // Catch:{ all -> 0x0080 }
        L_0x0087:
            r1 = 0
            int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r8 < 0) goto L_0x008e
            r0 = 1
        L_0x008e:
            monitor-exit(r7)
            return r0
        L_0x0090:
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.hawkeye.storage.DBHelper.a(com.xiaomi.youpin.hawkeye.entity.BaseInfo):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0091, code lost:
        if (r1 != null) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0093, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b8, code lost:
        if (r1 != null) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00bb, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.xiaomi.youpin.hawkeye.entity.BaseInfo> b() {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r7.p
            if (r1 != 0) goto L_0x000f
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()
            r7.p = r1
        L_0x000f:
            java.lang.String r1 = "select * from hapm where isUpload = 0"
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r7.p     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            android.database.Cursor r1 = r3.rawQuery(r1, r2)     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            if (r1 == 0) goto L_0x0091
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x008f }
            if (r2 == 0) goto L_0x0091
        L_0x0020:
            com.xiaomi.youpin.hawkeye.entity.BaseInfo r2 = new com.xiaomi.youpin.hawkeye.entity.BaseInfo     // Catch:{ Exception -> 0x008f }
            r2.<init>()     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "_id"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x008f }
            int r3 = r1.getInt(r3)     // Catch:{ Exception -> 0x008f }
            r2.id = r3     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "timestamp"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x008f }
            long r3 = r1.getLong(r3)     // Catch:{ Exception -> 0x008f }
            r2.timestamp = r3     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "statInfo"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x008f }
            r2.statInfoJson = r3     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "statType"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x008f }
            r2.mStatType = r3     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "platform"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x008f }
            r2.platform = r3     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "system"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x008f }
            r2.systemJson = r3     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "taskName"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x008f }
            r2.mTaskName = r3     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "isUpload"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ Exception -> 0x008f }
            int r3 = r1.getInt(r3)     // Catch:{ Exception -> 0x008f }
            r2.isUpload = r3     // Catch:{ Exception -> 0x008f }
            r0.add(r2)     // Catch:{ Exception -> 0x008f }
            boolean r2 = r1.moveToNext()     // Catch:{ Exception -> 0x008f }
            if (r2 != 0) goto L_0x0020
            goto L_0x0091
        L_0x008f:
            r2 = move-exception
            goto L_0x009e
        L_0x0091:
            if (r1 == 0) goto L_0x00bb
        L_0x0093:
            r1.close()
            goto L_0x00bb
        L_0x0097:
            r0 = move-exception
            r1 = r2
            goto L_0x00bd
        L_0x009a:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L_0x009e:
            java.lang.String r3 = "DBHelper"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bc }
            r4.<init>()     // Catch:{ all -> 0x00bc }
            java.lang.String r5 = " upload was error, msg  :  "
            r4.append(r5)     // Catch:{ all -> 0x00bc }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x00bc }
            r4.append(r2)     // Catch:{ all -> 0x00bc }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x00bc }
            com.xiaomi.youpin.hawkeye.utils.HLog.e(r3, r2)     // Catch:{ all -> 0x00bc }
            if (r1 == 0) goto L_0x00bb
            goto L_0x0093
        L_0x00bb:
            return r0
        L_0x00bc:
            r0 = move-exception
        L_0x00bd:
            if (r1 == 0) goto L_0x00c2
            r1.close()
        L_0x00c2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.hawkeye.storage.DBHelper.b():java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0093, code lost:
        if (r7 == null) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0095, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ba, code lost:
        if (r7 != null) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00bd, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.youpin.hawkeye.entity.BaseInfo a(int r7) {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.p
            if (r0 != 0) goto L_0x000a
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()
            r6.p = r0
        L_0x000a:
            java.lang.String r0 = "select * from hapm where _id=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r6.p     // Catch:{ Exception -> 0x009c, all -> 0x0099 }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x009c, all -> 0x0099 }
            r4 = 0
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x009c, all -> 0x0099 }
            r3[r4] = r7     // Catch:{ Exception -> 0x009c, all -> 0x0099 }
            android.database.Cursor r7 = r2.rawQuery(r0, r3)     // Catch:{ Exception -> 0x009c, all -> 0x0099 }
            if (r7 == 0) goto L_0x0092
            boolean r0 = r7.moveToFirst()     // Catch:{ Exception -> 0x008d }
            if (r0 == 0) goto L_0x0092
            com.xiaomi.youpin.hawkeye.entity.BaseInfo r0 = new com.xiaomi.youpin.hawkeye.entity.BaseInfo     // Catch:{ Exception -> 0x008d }
            r0.<init>()     // Catch:{ Exception -> 0x008d }
            java.lang.String r1 = "_id"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x008b }
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x008b }
            r0.id = r1     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = "timestamp"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x008b }
            long r1 = r7.getLong(r1)     // Catch:{ Exception -> 0x008b }
            r0.timestamp = r1     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = "statInfo"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x008b }
            r0.statInfoJson = r1     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = "statType"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x008b }
            r0.mStatType = r1     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = "platform"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x008b }
            r0.platform = r1     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = "system"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x008b }
            r0.systemJson = r1     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = "taskName"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x008b }
            r0.mTaskName = r1     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = "isUpload"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x008b }
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x008b }
            r0.isUpload = r1     // Catch:{ Exception -> 0x008b }
            goto L_0x0093
        L_0x008b:
            r1 = move-exception
            goto L_0x00a0
        L_0x008d:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00a0
        L_0x0092:
            r0 = r1
        L_0x0093:
            if (r7 == 0) goto L_0x00bd
        L_0x0095:
            r7.close()
            goto L_0x00bd
        L_0x0099:
            r0 = move-exception
            r7 = r1
            goto L_0x00bf
        L_0x009c:
            r7 = move-exception
            r0 = r1
            r1 = r7
            r7 = r0
        L_0x00a0:
            java.lang.String r2 = "DBHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00be }
            r3.<init>()     // Catch:{ all -> 0x00be }
            java.lang.String r4 = " upload was error, msg  :  "
            r3.append(r4)     // Catch:{ all -> 0x00be }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00be }
            r3.append(r1)     // Catch:{ all -> 0x00be }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x00be }
            com.xiaomi.youpin.hawkeye.utils.HLog.e(r2, r1)     // Catch:{ all -> 0x00be }
            if (r7 == 0) goto L_0x00bd
            goto L_0x0095
        L_0x00bd:
            return r0
        L_0x00be:
            r0 = move-exception
        L_0x00bf:
            if (r7 == 0) goto L_0x00c4
            r7.close()
        L_0x00c4:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.hawkeye.storage.DBHelper.a(int):com.xiaomi.youpin.hawkeye.entity.BaseInfo");
    }

    public boolean b(int i2) {
        if (this.p == null) {
            this.p = getWritableDatabase();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(o, 1);
        this.p.update(g, contentValues, "_id=?", new String[]{String.valueOf(i2)});
        return false;
    }

    public boolean c(int i2) {
        if (this.p == null) {
            this.p = getWritableDatabase();
        }
        return ((long) this.p.delete(g, "_id=?", new String[]{String.valueOf(i2)})) > 0;
    }

    public String c() {
        if (this.q == null) {
            this.q = new BaseInfo.SystemInfo();
            this.q.screenWidth = DeviceUtils.c(HawkEye.d());
            this.q.screenHeight = DeviceUtils.d(HawkEye.d());
            this.q.networkType = DeviceUtils.b(HawkEye.d());
            this.q.deviceModel = DeviceUtils.b();
            this.q.osVersion = DeviceUtils.a();
            this.q.appVersion = HawkEye.c().b();
            this.q.totalMemory = DeviceUtils.f(HawkEye.d());
        }
        this.q.availableMemory = DeviceUtils.e(HawkEye.d());
        return new Gson().toJson((Object) this.q);
    }
}
