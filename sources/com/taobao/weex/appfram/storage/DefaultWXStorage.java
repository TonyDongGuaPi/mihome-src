package com.taobao.weex.appfram.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import com.taobao.weex.appfram.storage.IWXStorageAdapter;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.utils.WXLogUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class DefaultWXStorage implements IWXStorageAdapter {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private WXSQLiteOpenHelper mDatabaseSupplier;
    private ExecutorService mExecutorService;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5233390843031345453L, "com/taobao/weex/appfram/storage/DefaultWXStorage", 138);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ boolean access$000(DefaultWXStorage defaultWXStorage, String str, String str2, boolean z, boolean z2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean performSetItem = defaultWXStorage.performSetItem(str, str2, z, z2);
        $jacocoInit[133] = true;
        return performSetItem;
    }

    static /* synthetic */ String access$100(DefaultWXStorage defaultWXStorage, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        String performGetItem = defaultWXStorage.performGetItem(str);
        $jacocoInit[134] = true;
        return performGetItem;
    }

    static /* synthetic */ boolean access$200(DefaultWXStorage defaultWXStorage, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean performRemoveItem = defaultWXStorage.performRemoveItem(str);
        $jacocoInit[135] = true;
        return performRemoveItem;
    }

    static /* synthetic */ long access$300(DefaultWXStorage defaultWXStorage) {
        boolean[] $jacocoInit = $jacocoInit();
        long performGetLength = defaultWXStorage.performGetLength();
        $jacocoInit[136] = true;
        return performGetLength;
    }

    static /* synthetic */ List access$400(DefaultWXStorage defaultWXStorage) {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> performGetAllKeys = defaultWXStorage.performGetAllKeys();
        $jacocoInit[137] = true;
        return performGetAllKeys;
    }

    private void execute(@Nullable Runnable runnable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mExecutorService != null) {
            $jacocoInit[0] = true;
        } else {
            $jacocoInit[1] = true;
            this.mExecutorService = Executors.newSingleThreadExecutor();
            $jacocoInit[2] = true;
        }
        if (runnable == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            this.mExecutorService.execute(WXThread.secure(runnable));
            $jacocoInit[5] = true;
        }
        $jacocoInit[6] = true;
    }

    public DefaultWXStorage(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[7] = true;
        this.mDatabaseSupplier = new WXSQLiteOpenHelper(context);
        $jacocoInit[8] = true;
    }

    public void setItem(final String str, final String str2, final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        boolean[] $jacocoInit = $jacocoInit();
        execute(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ DefaultWXStorage this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-3527384961534035747L, "com/taobao/weex/appfram/storage/DefaultWXStorage$1", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Map<String, Object> itemResult = StorageResultHandler.setItemResult(DefaultWXStorage.access$000(this.this$0, str, str2, false, true));
                if (onResultReceivedListener == null) {
                    $jacocoInit[1] = true;
                    return;
                }
                onResultReceivedListener.onReceived(itemResult);
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[9] = true;
    }

    public void getItem(final String str, final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        boolean[] $jacocoInit = $jacocoInit();
        execute(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ DefaultWXStorage this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5936730646513937059L, "com/taobao/weex/appfram/storage/DefaultWXStorage$2", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Map<String, Object> itemResult = StorageResultHandler.getItemResult(DefaultWXStorage.access$100(this.this$0, str));
                if (onResultReceivedListener == null) {
                    $jacocoInit[1] = true;
                    return;
                }
                onResultReceivedListener.onReceived(itemResult);
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[10] = true;
    }

    public void removeItem(final String str, final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        boolean[] $jacocoInit = $jacocoInit();
        execute(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ DefaultWXStorage this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-8943243318746150872L, "com/taobao/weex/appfram/storage/DefaultWXStorage$3", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Map<String, Object> removeItemResult = StorageResultHandler.removeItemResult(DefaultWXStorage.access$200(this.this$0, str));
                if (onResultReceivedListener == null) {
                    $jacocoInit[1] = true;
                    return;
                }
                onResultReceivedListener.onReceived(removeItemResult);
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[11] = true;
    }

    public void length(final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        boolean[] $jacocoInit = $jacocoInit();
        execute(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ DefaultWXStorage this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-441714780299216192L, "com/taobao/weex/appfram/storage/DefaultWXStorage$4", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Map<String, Object> lengthResult = StorageResultHandler.getLengthResult(DefaultWXStorage.access$300(this.this$0));
                if (onResultReceivedListener == null) {
                    $jacocoInit[1] = true;
                    return;
                }
                onResultReceivedListener.onReceived(lengthResult);
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[12] = true;
    }

    public void getAllKeys(final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        boolean[] $jacocoInit = $jacocoInit();
        execute(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ DefaultWXStorage this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-536294888831802971L, "com/taobao/weex/appfram/storage/DefaultWXStorage$5", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Map<String, Object> allkeysResult = StorageResultHandler.getAllkeysResult(DefaultWXStorage.access$400(this.this$0));
                if (onResultReceivedListener == null) {
                    $jacocoInit[1] = true;
                    return;
                }
                onResultReceivedListener.onReceived(allkeysResult);
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[13] = true;
    }

    public void setItemPersistent(final String str, final String str2, final IWXStorageAdapter.OnResultReceivedListener onResultReceivedListener) {
        boolean[] $jacocoInit = $jacocoInit();
        execute(new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ DefaultWXStorage this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-535837917216607832L, "com/taobao/weex/appfram/storage/DefaultWXStorage$6", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Map<String, Object> itemResult = StorageResultHandler.setItemResult(DefaultWXStorage.access$000(this.this$0, str, str2, true, true));
                if (onResultReceivedListener == null) {
                    $jacocoInit[1] = true;
                    return;
                }
                onResultReceivedListener.onReceived(itemResult);
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[14] = true;
    }

    public void close() {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            this.mDatabaseSupplier.closeDatabase();
            if (this.mExecutorService == null) {
                $jacocoInit[15] = true;
            } else {
                $jacocoInit[16] = true;
                this.mExecutorService.shutdown();
                this.mExecutorService = null;
                $jacocoInit[17] = true;
            }
            $jacocoInit[18] = true;
        } catch (Exception e) {
            $jacocoInit[19] = true;
            WXLogUtils.e("weex_storage", e.getMessage());
            $jacocoInit[20] = true;
        }
        $jacocoInit[21] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e0 A[Catch:{ all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e5 A[Catch:{ all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x015e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean performSetItem(java.lang.String r9, java.lang.String r10, boolean r11, boolean r12) {
        /*
            r8 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.appfram.storage.WXSQLiteOpenHelper r1 = r8.mDatabaseSupplier
            android.database.sqlite.SQLiteDatabase r1 = r1.getDatabase()
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0013
            r9 = 22
            r0[r9] = r3
            return r2
        L_0x0013:
            java.lang.String r4 = "weex_storage"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "set k-v to storage(key:"
            r5.append(r6)
            r5.append(r9)
            java.lang.String r6 = ",value:"
            r5.append(r6)
            r5.append(r10)
            java.lang.String r6 = ",isPersistent:"
            r5.append(r6)
            r5.append(r11)
            java.lang.String r6 = ",allowRetry:"
            r5.append(r6)
            r5.append(r12)
            java.lang.String r6 = ")"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.taobao.weex.utils.WXLogUtils.d((java.lang.String) r4, (java.lang.String) r5)
            java.lang.String r4 = "INSERT OR REPLACE INTO default_wx_storage VALUES (?,?,?,?);"
            r5 = 0
            r6 = 23
            r0[r6] = r3
            java.text.SimpleDateFormat r6 = com.taobao.weex.appfram.storage.WXSQLiteOpenHelper.sDateFormatter
            java.util.Date r7 = new java.util.Date
            r7.<init>()
            java.lang.String r6 = r6.format(r7)
            r7 = 24
            r0[r7] = r3     // Catch:{ Exception -> 0x00bd }
            android.database.sqlite.SQLiteStatement r1 = r1.compileStatement(r4)     // Catch:{ Exception -> 0x00bd }
            r4 = 25
            r0[r4] = r3     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r1.clearBindings()     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r4 = 26
            r0[r4] = r3     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r1.bindString(r3, r9)     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r4 = 27
            r0[r4] = r3     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r4 = 2
            r1.bindString(r4, r10)     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r4 = 28
            r0[r4] = r3     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r4 = 3
            r1.bindString(r4, r6)     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r4 = 29
            r0[r4] = r3     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r4 = 4
            if (r11 == 0) goto L_0x008c
            r5 = 1
            r7 = 30
            r0[r7] = r3     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            goto L_0x0092
        L_0x008c:
            r5 = 0
            r7 = 31
            r0[r7] = r3     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
        L_0x0092:
            r1.bindLong(r4, r5)     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r4 = 32
            r0[r4] = r3     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            r1.execute()     // Catch:{ Exception -> 0x00b7, all -> 0x00b3 }
            if (r1 != 0) goto L_0x00a3
            r9 = 33
            r0[r9] = r3
            goto L_0x00ae
        L_0x00a3:
            r9 = 34
            r0[r9] = r3
            r1.close()
            r9 = 35
            r0[r9] = r3
        L_0x00ae:
            r9 = 36
            r0[r9] = r3
            return r3
        L_0x00b3:
            r9 = move-exception
            r5 = r1
            goto L_0x0157
        L_0x00b7:
            r4 = move-exception
            r5 = r1
            goto L_0x00be
        L_0x00ba:
            r9 = move-exception
            goto L_0x0157
        L_0x00bd:
            r4 = move-exception
        L_0x00be:
            r1 = 37
            r0[r1] = r3     // Catch:{ all -> 0x00ba }
            java.lang.String r1 = "weex_storage"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r6.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r7 = "DefaultWXStorage occurred an exception when execute setItem :"
            r6.append(r7)     // Catch:{ all -> 0x00ba }
            java.lang.String r7 = r4.getMessage()     // Catch:{ all -> 0x00ba }
            r6.append(r7)     // Catch:{ all -> 0x00ba }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00ba }
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r1, (java.lang.String) r6)     // Catch:{ all -> 0x00ba }
            boolean r1 = r4 instanceof android.database.sqlite.SQLiteFullException     // Catch:{ all -> 0x00ba }
            if (r1 != 0) goto L_0x00e5
            r9 = 38
            r0[r9] = r3     // Catch:{ all -> 0x00ba }
            goto L_0x00fa
        L_0x00e5:
            r1 = 39
            r0[r1] = r3     // Catch:{ all -> 0x00ba }
            if (r12 != 0) goto L_0x00f0
            r9 = 40
            r0[r9] = r3     // Catch:{ all -> 0x00ba }
            goto L_0x00fa
        L_0x00f0:
            boolean r12 = r8.trimToSize()     // Catch:{ all -> 0x00ba }
            if (r12 != 0) goto L_0x0111
            r9 = 41
            r0[r9] = r3     // Catch:{ all -> 0x00ba }
        L_0x00fa:
            if (r5 != 0) goto L_0x0101
            r9 = 48
            r0[r9] = r3
            goto L_0x010c
        L_0x0101:
            r9 = 49
            r0[r9] = r3
            r5.close()
            r9 = 50
            r0[r9] = r3
        L_0x010c:
            r9 = 51
            r0[r9] = r3
            return r2
        L_0x0111:
            r12 = 42
            r0[r12] = r3     // Catch:{ all -> 0x00ba }
            java.lang.String r12 = "weex_storage"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r1.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = "retry set k-v to storage(key:"
            r1.append(r4)     // Catch:{ all -> 0x00ba }
            r1.append(r9)     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = ",value:"
            r1.append(r4)     // Catch:{ all -> 0x00ba }
            r1.append(r10)     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = ")"
            r1.append(r4)     // Catch:{ all -> 0x00ba }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ba }
            com.taobao.weex.utils.WXLogUtils.d((java.lang.String) r12, (java.lang.String) r1)     // Catch:{ all -> 0x00ba }
            r12 = 43
            r0[r12] = r3     // Catch:{ all -> 0x00ba }
            boolean r9 = r8.performSetItem(r9, r10, r11, r2)     // Catch:{ all -> 0x00ba }
            if (r5 != 0) goto L_0x0147
            r10 = 44
            r0[r10] = r3
            goto L_0x0152
        L_0x0147:
            r10 = 45
            r0[r10] = r3
            r5.close()
            r10 = 46
            r0[r10] = r3
        L_0x0152:
            r10 = 47
            r0[r10] = r3
            return r9
        L_0x0157:
            if (r5 != 0) goto L_0x015e
            r10 = 52
            r0[r10] = r3
            goto L_0x0169
        L_0x015e:
            r10 = 53
            r0[r10] = r3
            r5.close()
            r10 = 54
            r0[r10] = r3
        L_0x0169:
            r10 = 55
            r0[r10] = r3
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.appfram.storage.DefaultWXStorage.performSetItem(java.lang.String, java.lang.String, boolean, boolean):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean trimToSize() {
        /*
            r12 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.appfram.storage.WXSQLiteOpenHelper r1 = r12.mDatabaseSupplier
            android.database.sqlite.SQLiteDatabase r2 = r1.getDatabase()
            r1 = 0
            r10 = 1
            if (r2 != 0) goto L_0x0013
            r2 = 56
            r0[r2] = r10
            return r1
        L_0x0013:
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r3 = 57
            r0[r3] = r10
            java.lang.String r3 = "default_wx_storage"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]
            java.lang.String r5 = "key"
            r4[r1] = r5
            java.lang.String r5 = "persistent"
            r4[r10] = r5
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "timestamp ASC"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)
            r3 = 58
            r0[r3] = r10     // Catch:{ Exception -> 0x00a8 }
            int r3 = r2.getCount()     // Catch:{ Exception -> 0x00a8 }
            int r3 = r3 / 10
            r4 = 59
            r0[r4] = r10     // Catch:{ Exception -> 0x00a8 }
            r4 = 0
        L_0x0042:
            boolean r5 = r2.moveToNext()     // Catch:{ Exception -> 0x00a4 }
            if (r5 != 0) goto L_0x004d
            r3 = 60
            r0[r3] = r10     // Catch:{ Exception -> 0x00a4 }
            goto L_0x009c
        L_0x004d:
            r5 = 61
            r0[r5] = r10     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r5 = "key"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Exception -> 0x00a4 }
            r6 = 62
            r0[r6] = r10     // Catch:{ Exception -> 0x00a4 }
            java.lang.String r6 = "persistent"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ Exception -> 0x00a4 }
            int r6 = r2.getInt(r6)     // Catch:{ Exception -> 0x00a4 }
            if (r6 != r10) goto L_0x0071
            r6 = 63
            r0[r6] = r10     // Catch:{ Exception -> 0x00a4 }
            r6 = 1
            goto L_0x0076
        L_0x0071:
            r6 = 64
            r0[r6] = r10     // Catch:{ Exception -> 0x00a4 }
            r6 = 0
        L_0x0076:
            if (r6 == 0) goto L_0x007d
            r5 = 65
            r0[r5] = r10     // Catch:{ Exception -> 0x00a4 }
            goto L_0x0093
        L_0x007d:
            if (r5 != 0) goto L_0x0084
            r5 = 66
            r0[r5] = r10     // Catch:{ Exception -> 0x00a4 }
            goto L_0x0093
        L_0x0084:
            int r4 = r4 + 1
            r6 = 67
            r0[r6] = r10     // Catch:{ Exception -> 0x00a4 }
            r11.add(r5)     // Catch:{ Exception -> 0x00a4 }
            if (r4 == r3) goto L_0x0098
            r5 = 68
            r0[r5] = r10     // Catch:{ Exception -> 0x00a4 }
        L_0x0093:
            r5 = 70
            r0[r5] = r10     // Catch:{ Exception -> 0x00a4 }
            goto L_0x0042
        L_0x0098:
            r3 = 69
            r0[r3] = r10     // Catch:{ Exception -> 0x00a4 }
        L_0x009c:
            r2.close()
            r2 = 71
            r0[r2] = r10
            goto L_0x00d3
        L_0x00a4:
            r3 = move-exception
            goto L_0x00aa
        L_0x00a6:
            r1 = move-exception
            goto L_0x011a
        L_0x00a8:
            r3 = move-exception
            r4 = 0
        L_0x00aa:
            r5 = 72
            r0[r5] = r10     // Catch:{ all -> 0x00a6 }
            java.lang.String r5 = "weex_storage"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a6 }
            r6.<init>()     // Catch:{ all -> 0x00a6 }
            java.lang.String r7 = "DefaultWXStorage occurred an exception when execute trimToSize:"
            r6.append(r7)     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x00a6 }
            r6.append(r3)     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x00a6 }
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r5, (java.lang.String) r3)     // Catch:{ all -> 0x00a6 }
            r3 = 73
            r0[r3] = r10     // Catch:{ all -> 0x00a6 }
            r2.close()
            r2 = 74
            r0[r2] = r10
        L_0x00d3:
            if (r4 > 0) goto L_0x00da
            r2 = 76
            r0[r2] = r10
            return r1
        L_0x00da:
            java.util.Iterator r1 = r11.iterator()
            r2 = 77
            r0[r2] = r10
        L_0x00e2:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00fa
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            r3 = 78
            r0[r3] = r10
            r12.performRemoveItem(r2)
            r2 = 79
            r0[r2] = r10
            goto L_0x00e2
        L_0x00fa:
            java.lang.String r1 = "weex_storage"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "remove "
            r2.append(r3)
            r2.append(r4)
            java.lang.String r3 = " items by lru"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r1, (java.lang.String) r2)
            r1 = 80
            r0[r1] = r10
            return r10
        L_0x011a:
            r2.close()
            r2 = 75
            r0[r2] = r10
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.appfram.storage.DefaultWXStorage.trimToSize():boolean");
    }

    private String performGetItem(String str) {
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase();
        if (database == null) {
            $jacocoInit[81] = true;
            return null;
        }
        Cursor query = database.query("default_wx_storage", new String[]{"value"}, "key=?", new String[]{str}, (String) null, (String) null, (String) null);
        try {
            $jacocoInit[82] = true;
            if (!query.moveToNext()) {
                $jacocoInit[83] = true;
                $jacocoInit[93] = true;
                return null;
            }
            $jacocoInit[84] = true;
            ContentValues contentValues = new ContentValues();
            $jacocoInit[85] = true;
            contentValues.put("timestamp", WXSQLiteOpenHelper.sDateFormatter.format(new Date()));
            $jacocoInit[86] = true;
            int update = this.mDatabaseSupplier.getDatabase().update("default_wx_storage", contentValues, "key= ?", new String[]{str});
            $jacocoInit[87] = true;
            StringBuilder sb = new StringBuilder();
            sb.append("update timestamp ");
            if (update == 1) {
                str2 = "success";
                $jacocoInit[88] = true;
            } else {
                str2 = "failed";
                $jacocoInit[89] = true;
            }
            sb.append(str2);
            sb.append(" for operation [getItem(key = ");
            sb.append(str);
            sb.append(")]");
            WXLogUtils.d("weex_storage", sb.toString());
            $jacocoInit[90] = true;
            String string = query.getString(query.getColumnIndex("value"));
            $jacocoInit[91] = true;
            query.close();
            $jacocoInit[92] = true;
            return string;
        } catch (Exception e) {
            $jacocoInit[95] = true;
            WXLogUtils.e("weex_storage", "DefaultWXStorage occurred an exception when execute getItem:" + e.getMessage());
            $jacocoInit[96] = true;
            return null;
        } finally {
            query.close();
            $jacocoInit['b'] = true;
        }
    }

    private boolean performRemoveItem(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase();
        boolean z = false;
        if (database == null) {
            $jacocoInit[99] = true;
            return false;
        }
        try {
            $jacocoInit[100] = true;
            if (database.delete("default_wx_storage", "key=?", new String[]{str}) == 1) {
                $jacocoInit[103] = true;
                z = true;
            } else {
                $jacocoInit[104] = true;
            }
            $jacocoInit[105] = true;
            return z;
        } catch (Exception e) {
            $jacocoInit[101] = true;
            WXLogUtils.e("weex_storage", "DefaultWXStorage occurred an exception when execute removeItem:" + e.getMessage());
            $jacocoInit[102] = true;
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long performGetLength() {
        /*
            r9 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.appfram.storage.WXSQLiteOpenHelper r1 = r9.mDatabaseSupplier
            android.database.sqlite.SQLiteDatabase r1 = r1.getDatabase()
            r2 = 0
            r4 = 1
            if (r1 != 0) goto L_0x0014
            r1 = 106(0x6a, float:1.49E-43)
            r0[r1] = r4
            return r2
        L_0x0014:
            java.lang.String r5 = "SELECT count(key) FROM default_wx_storage"
            r6 = 0
            r7 = 107(0x6b, float:1.5E-43)
            r0[r7] = r4     // Catch:{ Exception -> 0x0046 }
            android.database.sqlite.SQLiteStatement r1 = r1.compileStatement(r5)     // Catch:{ Exception -> 0x0046 }
            r5 = 108(0x6c, float:1.51E-43)
            r0[r5] = r4     // Catch:{ Exception -> 0x0041, all -> 0x003e }
            long r5 = r1.simpleQueryForLong()     // Catch:{ Exception -> 0x0041, all -> 0x003e }
            if (r1 != 0) goto L_0x002e
            r1 = 109(0x6d, float:1.53E-43)
            r0[r1] = r4
            goto L_0x0039
        L_0x002e:
            r2 = 110(0x6e, float:1.54E-43)
            r0[r2] = r4
            r1.close()
            r1 = 111(0x6f, float:1.56E-43)
            r0[r1] = r4
        L_0x0039:
            r1 = 112(0x70, float:1.57E-43)
            r0[r1] = r4
            return r5
        L_0x003e:
            r2 = move-exception
            r6 = r1
            goto L_0x007c
        L_0x0041:
            r5 = move-exception
            r6 = r1
            goto L_0x0047
        L_0x0044:
            r2 = move-exception
            goto L_0x007c
        L_0x0046:
            r5 = move-exception
        L_0x0047:
            r1 = 113(0x71, float:1.58E-43)
            r0[r1] = r4     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = "weex_storage"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r7.<init>()     // Catch:{ all -> 0x0044 }
            java.lang.String r8 = "DefaultWXStorage occurred an exception when execute getLength:"
            r7.append(r8)     // Catch:{ all -> 0x0044 }
            java.lang.String r5 = r5.getMessage()     // Catch:{ all -> 0x0044 }
            r7.append(r5)     // Catch:{ all -> 0x0044 }
            java.lang.String r5 = r7.toString()     // Catch:{ all -> 0x0044 }
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r1, (java.lang.String) r5)     // Catch:{ all -> 0x0044 }
            if (r6 != 0) goto L_0x006c
            r1 = 114(0x72, float:1.6E-43)
            r0[r1] = r4
            goto L_0x0077
        L_0x006c:
            r1 = 115(0x73, float:1.61E-43)
            r0[r1] = r4
            r6.close()
            r1 = 116(0x74, float:1.63E-43)
            r0[r1] = r4
        L_0x0077:
            r1 = 117(0x75, float:1.64E-43)
            r0[r1] = r4
            return r2
        L_0x007c:
            if (r6 != 0) goto L_0x0083
            r1 = 118(0x76, float:1.65E-43)
            r0[r1] = r4
            goto L_0x008e
        L_0x0083:
            r1 = 119(0x77, float:1.67E-43)
            r0[r1] = r4
            r6.close()
            r1 = 120(0x78, float:1.68E-43)
            r0[r1] = r4
        L_0x008e:
            r1 = 121(0x79, float:1.7E-43)
            r0[r1] = r4
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.appfram.storage.DefaultWXStorage.performGetLength():long");
    }

    private List<String> performGetAllKeys() {
        boolean[] $jacocoInit = $jacocoInit();
        SQLiteDatabase database = this.mDatabaseSupplier.getDatabase();
        if (database == null) {
            $jacocoInit[122] = true;
            return null;
        }
        ArrayList arrayList = new ArrayList();
        $jacocoInit[123] = true;
        Cursor query = database.query("default_wx_storage", new String[]{"key"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
        try {
            $jacocoInit[124] = true;
            while (query.moveToNext()) {
                $jacocoInit[125] = true;
                arrayList.add(query.getString(query.getColumnIndex("key")));
                $jacocoInit[126] = true;
            }
            $jacocoInit[127] = true;
            return arrayList;
        } catch (Exception e) {
            $jacocoInit[129] = true;
            WXLogUtils.e("weex_storage", "DefaultWXStorage occurred an exception when execute getAllKeys:" + e.getMessage());
            $jacocoInit[130] = true;
            return arrayList;
        } finally {
            query.close();
            $jacocoInit[132] = true;
        }
    }
}
