package com.taobao.weex.appfram.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.taobao.weex.utils.WXLogUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXSQLiteOpenHelper extends SQLiteOpenHelper {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    static final String COLUMN_KEY = "key";
    static final String COLUMN_PERSISTENT = "persistent";
    static final String COLUMN_TIMESTAMP = "timestamp";
    static final String COLUMN_VALUE = "value";
    private static final String DATABASE_NAME = "WXStorage";
    private static final int DATABASE_VERSION = 2;
    private static final int SLEEP_TIME_MS = 30;
    private static final String STATEMENT_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS default_wx_storage (key TEXT PRIMARY KEY,value TEXT NOT NULL,timestamp TEXT NOT NULL,persistent INTEGER DEFAULT 0)";
    static final String TABLE_STORAGE = "default_wx_storage";
    static final String TAG_STORAGE = "weex_storage";
    static SimpleDateFormat sDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private Context mContext;
    private SQLiteDatabase mDb;
    private long mMaximumDatabaseSize = 52428800;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4381360671336259262L, "com/taobao/weex/appfram/storage/WXSQLiteOpenHelper", 85);
        $jacocoData = a2;
        return a2;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[84] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 2);
        boolean[] $jacocoInit = $jacocoInit();
        this.mContext = context;
        $jacocoInit[0] = true;
    }

    @Nullable
    public SQLiteDatabase getDatabase() {
        boolean[] $jacocoInit = $jacocoInit();
        ensureDatabase();
        SQLiteDatabase sQLiteDatabase = this.mDb;
        $jacocoInit[1] = true;
        return sQLiteDatabase;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        boolean[] $jacocoInit = $jacocoInit();
        sQLiteDatabase.execSQL(STATEMENT_CREATE_TABLE);
        $jacocoInit[2] = true;
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (i == i2) {
            $jacocoInit[3] = true;
        } else {
            if (i2 != 2) {
                $jacocoInit[4] = true;
            } else if (i != 1) {
                $jacocoInit[5] = true;
            } else {
                $jacocoInit[6] = true;
                WXLogUtils.d(TAG_STORAGE, "storage is updating from version " + i + " to version " + i2);
                try {
                    $jacocoInit[7] = true;
                    long currentTimeMillis = System.currentTimeMillis();
                    $jacocoInit[8] = true;
                    sQLiteDatabase.beginTransaction();
                    $jacocoInit[9] = true;
                    WXLogUtils.d(TAG_STORAGE, "exec sql : " + "ALTER TABLE default_wx_storage ADD COLUMN timestamp TEXT;");
                    $jacocoInit[10] = true;
                    sQLiteDatabase.execSQL("ALTER TABLE default_wx_storage ADD COLUMN timestamp TEXT;");
                    $jacocoInit[11] = true;
                    WXLogUtils.d(TAG_STORAGE, "exec sql : " + "ALTER TABLE default_wx_storage ADD COLUMN persistent INTEGER;");
                    $jacocoInit[12] = true;
                    sQLiteDatabase.execSQL("ALTER TABLE default_wx_storage ADD COLUMN persistent INTEGER;");
                    $jacocoInit[13] = true;
                    String str = "UPDATE default_wx_storage SET timestamp = '" + sDateFormatter.format(new Date()) + "' , " + COLUMN_PERSISTENT + " = 0";
                    $jacocoInit[14] = true;
                    WXLogUtils.d(TAG_STORAGE, "exec sql : " + str);
                    $jacocoInit[15] = true;
                    sQLiteDatabase.execSQL(str);
                    $jacocoInit[16] = true;
                    sQLiteDatabase.setTransactionSuccessful();
                    $jacocoInit[17] = true;
                    $jacocoInit[18] = true;
                    WXLogUtils.d(TAG_STORAGE, "storage updated success (" + (System.currentTimeMillis() - currentTimeMillis) + "ms)");
                    $jacocoInit[19] = true;
                    sQLiteDatabase.endTransaction();
                    $jacocoInit[20] = true;
                    z = true;
                } catch (Exception e) {
                    $jacocoInit[21] = true;
                    WXLogUtils.d(TAG_STORAGE, "storage updated failed from version " + i + " to version " + i2 + "," + e.getMessage());
                    z = false;
                    $jacocoInit[22] = true;
                    sQLiteDatabase.endTransaction();
                    $jacocoInit[23] = true;
                } catch (Throwable th) {
                    sQLiteDatabase.endTransaction();
                    $jacocoInit[24] = true;
                    throw th;
                }
                if (z) {
                    $jacocoInit[25] = true;
                } else {
                    $jacocoInit[26] = true;
                    WXLogUtils.d(TAG_STORAGE, "storage is rollback,all data will be removed");
                    $jacocoInit[27] = true;
                    deleteDB();
                    $jacocoInit[28] = true;
                    onCreate(sQLiteDatabase);
                    $jacocoInit[29] = true;
                }
                $jacocoInit[30] = true;
            }
            deleteDB();
            $jacocoInit[31] = true;
            onCreate(sQLiteDatabase);
            $jacocoInit[32] = true;
        }
        $jacocoInit[33] = true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        if (r1 > 0) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        r0[40] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        r0[41] = true;
        deleteDB();
        r0[42] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        r5.mDb = getWritableDatabase();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0[43] = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void ensureDatabase() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean[] r0 = $jacocoInit()     // Catch:{ all -> 0x009a }
            android.database.sqlite.SQLiteDatabase r1 = r5.mDb     // Catch:{ all -> 0x009a }
            r2 = 1
            if (r1 != 0) goto L_0x000f
            r1 = 34
            r0[r1] = r2     // Catch:{ all -> 0x009a }
            goto L_0x001b
        L_0x000f:
            android.database.sqlite.SQLiteDatabase r1 = r5.mDb     // Catch:{ all -> 0x009a }
            boolean r1 = r1.isOpen()     // Catch:{ all -> 0x009a }
            if (r1 != 0) goto L_0x0094
            r1 = 35
            r0[r1] = r2     // Catch:{ all -> 0x009a }
        L_0x001b:
            r1 = 0
            r3 = 37
            r0[r3] = r2     // Catch:{ all -> 0x009a }
        L_0x0020:
            r3 = 2
            if (r1 < r3) goto L_0x0028
            r1 = 38
            r0[r1] = r2     // Catch:{ all -> 0x009a }
            goto L_0x0048
        L_0x0028:
            r3 = 39
            r0[r3] = r2     // Catch:{ SQLiteException -> 0x0068 }
            if (r1 > 0) goto L_0x0033
            r3 = 40
            r0[r3] = r2     // Catch:{ SQLiteException -> 0x0068 }
            goto L_0x003e
        L_0x0033:
            r3 = 41
            r0[r3] = r2     // Catch:{ SQLiteException -> 0x0068 }
            r5.deleteDB()     // Catch:{ SQLiteException -> 0x0068 }
            r3 = 42
            r0[r3] = r2     // Catch:{ SQLiteException -> 0x0068 }
        L_0x003e:
            android.database.sqlite.SQLiteDatabase r3 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0068 }
            r5.mDb = r3     // Catch:{ SQLiteException -> 0x0068 }
            r1 = 43
            r0[r1] = r2     // Catch:{ all -> 0x009a }
        L_0x0048:
            android.database.sqlite.SQLiteDatabase r1 = r5.mDb     // Catch:{ all -> 0x009a }
            if (r1 != 0) goto L_0x0052
            r1 = 50
            r0[r1] = r2     // Catch:{ all -> 0x009a }
            monitor-exit(r5)
            return
        L_0x0052:
            android.database.sqlite.SQLiteDatabase r1 = r5.mDb     // Catch:{ all -> 0x009a }
            r5.createTableIfNotExists(r1)     // Catch:{ all -> 0x009a }
            r1 = 51
            r0[r1] = r2     // Catch:{ all -> 0x009a }
            android.database.sqlite.SQLiteDatabase r1 = r5.mDb     // Catch:{ all -> 0x009a }
            long r3 = r5.mMaximumDatabaseSize     // Catch:{ all -> 0x009a }
            r1.setMaximumSize(r3)     // Catch:{ all -> 0x009a }
            r1 = 52
            r0[r1] = r2     // Catch:{ all -> 0x009a }
            monitor-exit(r5)
            return
        L_0x0068:
            r3 = move-exception
            r4 = 44
            r0[r4] = r2     // Catch:{ all -> 0x009a }
            r3.printStackTrace()     // Catch:{ all -> 0x009a }
            r3 = 45
            r0[r3] = r2     // Catch:{ InterruptedException -> 0x007e }
            r3 = 30
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x007e }
            r3 = 46
            r0[r3] = r2     // Catch:{ all -> 0x009a }
            goto L_0x008d
        L_0x007e:
            r3 = 47
            r0[r3] = r2     // Catch:{ all -> 0x009a }
            java.lang.Thread r3 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x009a }
            r3.interrupt()     // Catch:{ all -> 0x009a }
            r3 = 48
            r0[r3] = r2     // Catch:{ all -> 0x009a }
        L_0x008d:
            int r1 = r1 + 1
            r3 = 49
            r0[r3] = r2     // Catch:{ all -> 0x009a }
            goto L_0x0020
        L_0x0094:
            r1 = 36
            r0[r1] = r2     // Catch:{ all -> 0x009a }
            monitor-exit(r5)
            return
        L_0x009a:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.appfram.storage.WXSQLiteOpenHelper.ensureDatabase():void");
    }

    public synchronized void setMaximumSize(long j) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mMaximumDatabaseSize = j;
        if (this.mDb == null) {
            $jacocoInit[53] = true;
        } else {
            $jacocoInit[54] = true;
            this.mDb.setMaximumSize(this.mMaximumDatabaseSize);
            $jacocoInit[55] = true;
        }
        $jacocoInit[56] = true;
    }

    private boolean deleteDB() {
        boolean[] $jacocoInit = $jacocoInit();
        closeDatabase();
        $jacocoInit[57] = true;
        boolean deleteDatabase = this.mContext.deleteDatabase(DATABASE_NAME);
        $jacocoInit[58] = true;
        return deleteDatabase;
    }

    public synchronized void closeDatabase() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mDb == null) {
            $jacocoInit[59] = true;
        } else if (!this.mDb.isOpen()) {
            $jacocoInit[60] = true;
        } else {
            $jacocoInit[61] = true;
            this.mDb.close();
            this.mDb = null;
            $jacocoInit[62] = true;
        }
        $jacocoInit[63] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createTableIfNotExists(@android.support.annotation.NonNull android.database.sqlite.SQLiteDatabase r5) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            r1 = 64
            r2 = 0
            r3 = 1
            r0[r1] = r3     // Catch:{ Exception -> 0x005c }
            java.lang.String r1 = "SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = 'default_wx_storage'"
            android.database.Cursor r1 = r5.rawQuery(r1, r2)     // Catch:{ Exception -> 0x005c }
            r2 = 65
            r0[r2] = r3     // Catch:{ Exception -> 0x0056, all -> 0x0054 }
            if (r1 != 0) goto L_0x001b
            r2 = 66
            r0[r2] = r3     // Catch:{ Exception -> 0x0056, all -> 0x0054 }
            goto L_0x0025
        L_0x001b:
            int r2 = r1.getCount()     // Catch:{ Exception -> 0x0056, all -> 0x0054 }
            if (r2 > 0) goto L_0x003d
            r2 = 67
            r0[r2] = r3     // Catch:{ Exception -> 0x0056, all -> 0x0054 }
        L_0x0025:
            java.lang.String r2 = "CREATE TABLE IF NOT EXISTS default_wx_storage (key TEXT PRIMARY KEY,value TEXT NOT NULL,timestamp TEXT NOT NULL,persistent INTEGER DEFAULT 0)"
            r5.execSQL(r2)     // Catch:{ Exception -> 0x0056, all -> 0x0054 }
            if (r1 != 0) goto L_0x0031
            r5 = 72
            r0[r5] = r3
            goto L_0x0076
        L_0x0031:
            r5 = 73
            r0[r5] = r3
            r1.close()
            r5 = 74
            r0[r5] = r3
            goto L_0x0076
        L_0x003d:
            if (r1 != 0) goto L_0x0044
            r5 = 68
            r0[r5] = r3
            goto L_0x004f
        L_0x0044:
            r5 = 69
            r0[r5] = r3
            r1.close()
            r5 = 70
            r0[r5] = r3
        L_0x004f:
            r5 = 71
            r0[r5] = r3
            return
        L_0x0054:
            r5 = move-exception
            goto L_0x007b
        L_0x0056:
            r5 = move-exception
            r2 = r1
            goto L_0x005d
        L_0x0059:
            r5 = move-exception
            r1 = r2
            goto L_0x007b
        L_0x005c:
            r5 = move-exception
        L_0x005d:
            r1 = 75
            r0[r1] = r3     // Catch:{ all -> 0x0059 }
            r5.printStackTrace()     // Catch:{ all -> 0x0059 }
            if (r2 != 0) goto L_0x006b
            r5 = 76
            r0[r5] = r3
            goto L_0x0076
        L_0x006b:
            r5 = 77
            r0[r5] = r3
            r2.close()
            r5 = 78
            r0[r5] = r3
        L_0x0076:
            r5 = 83
            r0[r5] = r3
            return
        L_0x007b:
            if (r1 != 0) goto L_0x0082
            r1 = 79
            r0[r1] = r3
            goto L_0x008d
        L_0x0082:
            r2 = 80
            r0[r2] = r3
            r1.close()
            r1 = 81
            r0[r1] = r3
        L_0x008d:
            r1 = 82
            r0[r1] = r3
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.appfram.storage.WXSQLiteOpenHelper.createTableIfNotExists(android.database.sqlite.SQLiteDatabase):void");
    }
}
