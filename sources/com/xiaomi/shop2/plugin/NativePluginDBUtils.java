package com.xiaomi.shop2.plugin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.cache.DatabaseHelper;
import com.xiaomi.mishopsdk.plugin.PluginSyncManager;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.FileUtil;
import com.xiaomi.mishopsdk.utils.ArrayUtils;
import com.xiaomi.mishopsdk.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class NativePluginDBUtils {
    private static final String TABLENAME = "plugins";
    public static final String TAG = "NativePluginDBUtils";
    private static final String TB_KEYID = "id";
    private static final String TB_KEYINFO = "pluginInfo";
    private static NativePluginDBUtils instance;
    private DBHelper mDBOpener;
    public ArrayList<PluginInfo> mLatestPlugins = new ArrayList<>();
    private ConcurrentHashMap<String, PluginInfo> mNativeInfos = new ConcurrentHashMap<>();
    private SQLiteDatabase mSqliteDB;
    private Integer nbOpenedInstances = new Integer(0);

    public native boolean dencryptFile(String str, String str2);

    public static synchronized NativePluginDBUtils getInstance() {
        NativePluginDBUtils nativePluginDBUtils;
        synchronized (NativePluginDBUtils.class) {
            if (instance == null) {
                instance = new NativePluginDBUtils();
            }
            nativePluginDBUtils = instance;
        }
        return nativePluginDBUtils;
    }

    public synchronized void initNativeInfos() {
        if (this.mNativeInfos != null) {
            this.mNativeInfos.clear();
            ArrayList<PluginInfo> pluginsFromDB = getPluginsFromDB((String[]) null);
            if (pluginsFromDB != null) {
                Iterator<PluginInfo> it = pluginsFromDB.iterator();
                while (it.hasNext()) {
                    PluginInfo next = it.next();
                    if (next.isValid()) {
                        this.mNativeInfos.put(next.id, next);
                    } else {
                        deletePluginToDB(next);
                    }
                }
            }
        }
    }

    public ConcurrentHashMap<String, PluginInfo> getNativePluginInfos() {
        if (this.mNativeInfos == null || this.mNativeInfos.isEmpty()) {
            initNativeInfos();
        }
        return this.mNativeInfos;
    }

    public synchronized void clearNativeInfos() {
        if (ShopApp.instance != null) {
            FileUtil.deleteFloder(ShopApp.instance.getDir(PluginSyncManager.PATH_PLUGINSIGNED, 0).getAbsoluteFile());
        }
    }

    public synchronized PluginInfo getPluginByDB(String str) {
        ArrayList<PluginInfo> pluginsFromDB = getPluginsFromDB(new String[]{str});
        if (ArrayUtils.checkArrayEmpty((ArrayList<?>) pluginsFromDB)) {
            return null;
        }
        return pluginsFromDB.get(0);
    }

    public synchronized boolean updatePluginInfo(PluginInfo pluginInfo) {
        if (pluginInfo != null) {
            if (!StringUtils.isEmpty(pluginInfo.id)) {
                if (this.mNativeInfos == null) {
                    this.mNativeInfos = new ConcurrentHashMap<>();
                }
                if (this.mNativeInfos.containsKey(pluginInfo.id)) {
                    this.mNativeInfos.remove(pluginInfo.id);
                }
                this.mNativeInfos.put(pluginInfo.id, new PluginInfo(pluginInfo));
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean writePluginToDB(com.xiaomi.mishopsdk.plugin.lib.PluginInfo r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            if (r6 == 0) goto L_0x004e
            java.lang.String r1 = r6.id     // Catch:{ all -> 0x004b }
            boolean r1 = com.xiaomi.mishopsdk.utils.StringUtils.isEmpty(r1)     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x000d
            goto L_0x004e
        L_0x000d:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch:{ all -> 0x004b }
            r1.<init>()     // Catch:{ all -> 0x004b }
            java.lang.String r2 = r6.id     // Catch:{ all -> 0x004b }
            com.xiaomi.mishopsdk.plugin.lib.PluginInfo r2 = r5.getPluginByDB(r2)     // Catch:{ all -> 0x004b }
            if (r2 != 0) goto L_0x0030
            java.lang.String r0 = "id"
            java.lang.String r2 = r6.id     // Catch:{ all -> 0x004b }
            r1.put(r0, r2)     // Catch:{ all -> 0x004b }
            java.lang.String r0 = "pluginInfo"
            java.lang.String r6 = com.xiaomi.mishopsdk.util.JSONUtil.format(r6)     // Catch:{ all -> 0x004b }
            r1.put(r0, r6)     // Catch:{ all -> 0x004b }
            boolean r6 = r5.insert(r1)     // Catch:{ all -> 0x004b }
            monitor-exit(r5)
            return r6
        L_0x0030:
            java.lang.String r2 = "pluginInfo"
            java.lang.String r3 = com.xiaomi.mishopsdk.util.JSONUtil.format(r6)     // Catch:{ all -> 0x004b }
            r1.put(r2, r3)     // Catch:{ all -> 0x004b }
            java.lang.String r2 = "id=?"
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ all -> 0x004b }
            java.lang.String r6 = r6.id     // Catch:{ all -> 0x004b }
            r4[r0] = r6     // Catch:{ all -> 0x004b }
            int r6 = r5.update(r1, r2, r4)     // Catch:{ all -> 0x004b }
            if (r6 <= 0) goto L_0x0049
            r0 = 1
        L_0x0049:
            monitor-exit(r5)
            return r0
        L_0x004b:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x004e:
            monitor-exit(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.NativePluginDBUtils.writePluginToDB(com.xiaomi.mishopsdk.plugin.lib.PluginInfo):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean deletePluginToDB(com.xiaomi.mishopsdk.plugin.lib.PluginInfo r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            if (r5 == 0) goto L_0x002d
            java.lang.String r1 = r5.id     // Catch:{ all -> 0x002a }
            boolean r1 = com.xiaomi.mishopsdk.utils.StringUtils.isEmpty(r1)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x000d
            goto L_0x002d
        L_0x000d:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.xiaomi.mishopsdk.plugin.lib.PluginInfo> r1 = r4.mNativeInfos     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0018
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.xiaomi.mishopsdk.plugin.lib.PluginInfo> r1 = r4.mNativeInfos     // Catch:{ all -> 0x002a }
            java.lang.String r2 = r5.id     // Catch:{ all -> 0x002a }
            r1.remove(r2)     // Catch:{ all -> 0x002a }
        L_0x0018:
            java.lang.String r1 = "id =?"
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]     // Catch:{ all -> 0x002a }
            java.lang.String r5 = r5.id     // Catch:{ all -> 0x002a }
            r3[r0] = r5     // Catch:{ all -> 0x002a }
            int r5 = r4.delete(r1, r3)     // Catch:{ all -> 0x002a }
            if (r5 <= 0) goto L_0x0028
            r0 = 1
        L_0x0028:
            monitor-exit(r4)
            return r0
        L_0x002a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x002d:
            monitor-exit(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.NativePluginDBUtils.deletePluginToDB(com.xiaomi.mishopsdk.plugin.lib.PluginInfo):boolean");
    }

    public synchronized boolean deleteAllPluginInfosToDB() {
        if (this.mNativeInfos != null) {
            this.mNativeInfos.clear();
        }
        return delete((String) null, (String[]) null) > 0;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|23|24) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:7|8|(2:26|27)|28|29) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        if (r0 != null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        if (r0 == null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        closeDataBase();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0059 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0063 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:28:0x0063=Splitter:B:28:0x0063, B:21:0x0059=Splitter:B:21:0x0059} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.ArrayList<com.xiaomi.mishopsdk.plugin.lib.PluginInfo> getPluginsFromDB(java.lang.String[] r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r11.openDataBase()     // Catch:{ all -> 0x0064 }
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0064 }
            r1.<init>()     // Catch:{ all -> 0x0064 }
            if (r12 != 0) goto L_0x0020
            android.database.sqlite.SQLiteDatabase r2 = r11.mSqliteDB     // Catch:{ Exception -> 0x001e }
            java.lang.String r3 = "plugins"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x001e }
        L_0x001a:
            r0 = r12
            goto L_0x0031
        L_0x001c:
            r12 = move-exception
            goto L_0x005e
        L_0x001e:
            r12 = move-exception
            goto L_0x0053
        L_0x0020:
            android.database.sqlite.SQLiteDatabase r2 = r11.mSqliteDB     // Catch:{ Exception -> 0x001e }
            java.lang.String r3 = "plugins"
            r4 = 0
            java.lang.String r5 = "id=?"
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r6 = r12
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x001e }
            goto L_0x001a
        L_0x0031:
            boolean r12 = r0.moveToNext()     // Catch:{ Exception -> 0x001e }
            if (r12 == 0) goto L_0x004d
            java.lang.String r12 = "pluginInfo"
            int r12 = r0.getColumnIndex(r12)     // Catch:{ Exception -> 0x001e }
            java.lang.String r12 = r0.getString(r12)     // Catch:{ Exception -> 0x001e }
            java.lang.Class<com.xiaomi.mishopsdk.plugin.lib.PluginInfo> r2 = com.xiaomi.mishopsdk.plugin.lib.PluginInfo.class
            java.lang.Object r12 = com.xiaomi.mishopsdk.util.JSONUtil.parse(r12, r2)     // Catch:{ Exception -> 0x001e }
            com.xiaomi.mishopsdk.plugin.lib.PluginInfo r12 = (com.xiaomi.mishopsdk.plugin.lib.PluginInfo) r12     // Catch:{ Exception -> 0x001e }
            r1.add(r12)     // Catch:{ Exception -> 0x001e }
            goto L_0x0031
        L_0x004d:
            if (r0 == 0) goto L_0x0059
        L_0x004f:
            r0.close()     // Catch:{ Exception -> 0x0059 }
            goto L_0x0059
        L_0x0053:
            r12.printStackTrace()     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0059
            goto L_0x004f
        L_0x0059:
            r11.closeDataBase()     // Catch:{ all -> 0x0064 }
            monitor-exit(r11)
            return r1
        L_0x005e:
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            throw r12     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.NativePluginDBUtils.getPluginsFromDB(java.lang.String[]):java.util.ArrayList");
    }

    private synchronized void openDataBase() {
        synchronized (this.nbOpenedInstances) {
            if (this.nbOpenedInstances.intValue() == 0 || this.mSqliteDB == null) {
                try {
                    this.mDBOpener = new DBHelper(ShopApp.instance);
                    this.mSqliteDB = this.mDBOpener.getWritableDatabase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Integer num = this.nbOpenedInstances;
            this.nbOpenedInstances = Integer.valueOf(this.nbOpenedInstances.intValue() + 1);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:4|5|(1:7)|8|(2:14|15)|16|17) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0035 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void closeDataBase() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.Integer r0 = r2.nbOpenedInstances     // Catch:{ all -> 0x003b }
            monitor-enter(r0)     // Catch:{ all -> 0x003b }
            java.lang.Integer r1 = r2.nbOpenedInstances     // Catch:{ all -> 0x0038 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x0038 }
            if (r1 <= 0) goto L_0x001c
            java.lang.Integer r1 = r2.nbOpenedInstances     // Catch:{ all -> 0x0038 }
            java.lang.Integer r1 = r2.nbOpenedInstances     // Catch:{ all -> 0x0038 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x0038 }
            int r1 = r1 + -1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0038 }
            r2.nbOpenedInstances = r1     // Catch:{ all -> 0x0038 }
        L_0x001c:
            java.lang.Integer r1 = r2.nbOpenedInstances     // Catch:{ all -> 0x0038 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x0038 }
            if (r1 != 0) goto L_0x0035
            android.database.sqlite.SQLiteDatabase r1 = r2.mSqliteDB     // Catch:{ all -> 0x0038 }
            if (r1 == 0) goto L_0x0035
            android.database.sqlite.SQLiteDatabase r1 = r2.mSqliteDB     // Catch:{ all -> 0x0038 }
            boolean r1 = r1.isOpen()     // Catch:{ all -> 0x0038 }
            if (r1 == 0) goto L_0x0035
            android.database.sqlite.SQLiteDatabase r1 = r2.mSqliteDB     // Catch:{ Exception -> 0x0035 }
            r1.close()     // Catch:{ Exception -> 0x0035 }
        L_0x0035:
            monitor-exit(r0)     // Catch:{ all -> 0x0038 }
            monitor-exit(r2)
            return
        L_0x0038:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0038 }
            throw r1     // Catch:{ all -> 0x003b }
        L_0x003b:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.NativePluginDBUtils.closeDataBase():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        openDataBase();
        r4.mSqliteDB.insert(TABLENAME, (java.lang.String) null, r5);
        closeDataBase();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (checkRecoveryError() != false) goto L_0x001a;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean insert(android.content.ContentValues r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 1
            r1 = 0
            r4.openDataBase()     // Catch:{ Exception -> 0x0014 }
            android.database.sqlite.SQLiteDatabase r2 = r4.mSqliteDB     // Catch:{ Exception -> 0x0014 }
            java.lang.String r3 = "plugins"
            r2.insert(r3, r1, r5)     // Catch:{ Exception -> 0x0014 }
            r4.closeDataBase()     // Catch:{ Exception -> 0x0014 }
            monitor-exit(r4)
            return r0
        L_0x0012:
            r5 = move-exception
            goto L_0x002c
        L_0x0014:
            boolean r2 = r4.checkRecoveryError()     // Catch:{ all -> 0x0012 }
            if (r2 == 0) goto L_0x0029
            r4.openDataBase()     // Catch:{ all -> 0x0012 }
            android.database.sqlite.SQLiteDatabase r2 = r4.mSqliteDB     // Catch:{ all -> 0x0012 }
            java.lang.String r3 = "plugins"
            r2.insert(r3, r1, r5)     // Catch:{ all -> 0x0012 }
            r4.closeDataBase()     // Catch:{ all -> 0x0012 }
            monitor-exit(r4)
            return r0
        L_0x0029:
            r5 = 0
            monitor-exit(r4)
            return r5
        L_0x002c:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.NativePluginDBUtils.insert(android.content.ContentValues):boolean");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0016 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002c A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int delete(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = -1
            r3.openDataBase()     // Catch:{ Exception -> 0x0016 }
            android.database.sqlite.SQLiteDatabase r1 = r3.mSqliteDB     // Catch:{ Exception -> 0x0016 }
            java.lang.String r2 = "plugins"
            int r1 = r1.delete(r2, r4, r5)     // Catch:{ Exception -> 0x0016 }
            r3.closeDataBase()     // Catch:{ Exception -> 0x0012 }
            monitor-exit(r3)
            return r1
        L_0x0012:
            r0 = r1
            goto L_0x0016
        L_0x0014:
            r4 = move-exception
            goto L_0x002e
        L_0x0016:
            boolean r1 = r3.checkRecoveryError()     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x002c
            r3.openDataBase()     // Catch:{ all -> 0x0014 }
            android.database.sqlite.SQLiteDatabase r0 = r3.mSqliteDB     // Catch:{ all -> 0x0014 }
            java.lang.String r1 = "plugins"
            int r4 = r0.delete(r1, r4, r5)     // Catch:{ all -> 0x0014 }
            r3.closeDataBase()     // Catch:{ all -> 0x0014 }
            monitor-exit(r3)
            return r4
        L_0x002c:
            monitor-exit(r3)
            return r0
        L_0x002e:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.NativePluginDBUtils.delete(java.lang.String, java.lang.String[]):int");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0016 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002c A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int update(android.content.ContentValues r4, java.lang.String r5, java.lang.String[] r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = -1
            r3.openDataBase()     // Catch:{ Exception -> 0x0016 }
            android.database.sqlite.SQLiteDatabase r1 = r3.mSqliteDB     // Catch:{ Exception -> 0x0016 }
            java.lang.String r2 = "plugins"
            int r1 = r1.update(r2, r4, r5, r6)     // Catch:{ Exception -> 0x0016 }
            r3.closeDataBase()     // Catch:{ Exception -> 0x0012 }
            monitor-exit(r3)
            return r1
        L_0x0012:
            r0 = r1
            goto L_0x0016
        L_0x0014:
            r4 = move-exception
            goto L_0x002e
        L_0x0016:
            boolean r1 = r3.checkRecoveryError()     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x002c
            r3.openDataBase()     // Catch:{ all -> 0x0014 }
            android.database.sqlite.SQLiteDatabase r0 = r3.mSqliteDB     // Catch:{ all -> 0x0014 }
            java.lang.String r1 = "plugins"
            int r4 = r0.update(r1, r4, r5, r6)     // Catch:{ all -> 0x0014 }
            r3.closeDataBase()     // Catch:{ all -> 0x0014 }
            monitor-exit(r3)
            return r4
        L_0x002c:
            monitor-exit(r3)
            return r0
        L_0x002e:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.NativePluginDBUtils.update(android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private boolean checkRecoveryError() {
        openDataBase();
        if (this.mSqliteDB == null) {
            return false;
        }
        if (this.mDBOpener == null) {
            this.mDBOpener = new DBHelper(ShopApp.instance);
        }
        return this.mDBOpener.checkUpateDB(this.mSqliteDB);
    }

    class DBHelper extends DatabaseHelper {
        public DBHelper(Context context) {
            super(context);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            createNewTable(sQLiteDatabase);
        }

        private void createNewTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table plugins (id text primary key,pluginInfo text)");
        }

        private void dropTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("drop table plugins");
        }

        public boolean checkUpateDB(SQLiteDatabase sQLiteDatabase) {
            try {
                dropTable(sQLiteDatabase);
                createNewTable(sQLiteDatabase);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            checkUpateDB(sQLiteDatabase);
        }
    }
}
