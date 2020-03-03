package org.pgsqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Base64;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.exoplayer2.C;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLitePlugin extends ReactContextBaseJavaModule {
    private static final Pattern FIRST_WORD = Pattern.compile("^\\s*(\\S+)", 2);
    private static final String PLUGIN_NAME = "SQLite";
    public static final String TAG = "SQLitePlugin";
    static ConcurrentHashMap<String, DBRunner> dbrmap = new ConcurrentHashMap<>();
    protected Context context = null;
    protected ExecutorService threadPool;

    private enum Action {
        open,
        close,
        attach,
        delete,
        executeSqlBatch,
        backgroundExecuteSqlBatch,
        echoStringValue
    }

    private enum QueryType {
        update,
        insert,
        delete,
        select,
        begin,
        commit,
        rollback,
        other
    }

    public String getName() {
        return PLUGIN_NAME;
    }

    public SQLitePlugin(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext.getApplicationContext();
        this.threadPool = Executors.newCachedThreadPool();
    }

    @ReactMethod
    public void open(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            execute("open", readableMap, new CallbackContext(callback, callback2));
        } catch (Exception e) {
            callback2.invoke("Unexpected error:" + e.getMessage());
        }
    }

    @ReactMethod
    public void close(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            execute("close", readableMap, new CallbackContext(callback, callback2));
        } catch (Exception e) {
            callback2.invoke("Unexpected error" + e.getMessage());
        }
    }

    @ReactMethod
    public void attach(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            execute("attach", readableMap, new CallbackContext(callback, callback2));
        } catch (Exception e) {
            callback2.invoke("Unexpected error" + e.getMessage());
        }
    }

    @ReactMethod
    public void delete(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            execute("delete", readableMap, new CallbackContext(callback, callback2));
        } catch (Exception e) {
            callback2.invoke("Unexpected error" + e.getMessage());
        }
    }

    @ReactMethod
    public void backgroundExecuteSqlBatch(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            execute("backgroundExecuteSqlBatch", readableMap, new CallbackContext(callback, callback2));
        } catch (Exception e) {
            callback2.invoke("Unexpected error" + e.getMessage());
        }
    }

    @ReactMethod
    public void executeSqlBatch(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            execute("executeSqlBatch", readableMap, new CallbackContext(callback, callback2));
        } catch (Exception unused) {
            callback2.invoke("Unexpected error");
        }
    }

    @ReactMethod
    public void echoStringValue(ReadableMap readableMap, Callback callback, Callback callback2) {
        try {
            execute("echoStringValue", readableMap, new CallbackContext(callback, callback2));
        } catch (Exception unused) {
            callback2.invoke("Unexpected error");
        }
    }

    /* access modifiers changed from: protected */
    public ExecutorService getThreadPool() {
        return this.threadPool;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public boolean execute(String str, ReadableMap readableMap, CallbackContext callbackContext) throws Exception {
        try {
            try {
                return executeAndPossiblyThrow(Action.valueOf(str), readableMap, callbackContext);
            } catch (Exception e) {
                FLog.e(TAG, "unexpected error", (Throwable) e);
                callbackContext.b("Unexpected error executing processing SQLite query");
                throw e;
            }
        } catch (IllegalArgumentException e2) {
            FLog.e(TAG, "unexpected error", (Throwable) e2);
            callbackContext.b("Unexpected error executing processing SQLite query");
            throw e2;
        }
    }

    private boolean executeAndPossiblyThrow(Action action, ReadableMap readableMap, CallbackContext callbackContext) {
        ReadableArray[] readableArrayArr;
        String[] strArr;
        String[] strArr2;
        switch (action) {
            case echoStringValue:
                callbackContext.a(SQLitePluginConverter.a(readableMap, "value", ""));
                return true;
            case open:
                startDatabase(SQLitePluginConverter.a(readableMap, "name", ""), readableMap, callbackContext);
                return true;
            case close:
                closeDatabase(SQLitePluginConverter.a(readableMap, "path", ""), callbackContext);
                return true;
            case attach:
                attachDatabase(SQLitePluginConverter.a(readableMap, "path", ""), SQLitePluginConverter.a(readableMap, "dbName", ""), SQLitePluginConverter.a(readableMap, "dbAlias", ""), callbackContext);
                return true;
            case delete:
                deleteDatabase(SQLitePluginConverter.a(readableMap, "path", ""), callbackContext);
                return true;
            case executeSqlBatch:
            case backgroundExecuteSqlBatch:
                String a2 = SQLitePluginConverter.a((ReadableMap) SQLitePluginConverter.a(readableMap, "dbargs", (Object) null), "dbname", "");
                ReadableArray readableArray = (ReadableArray) SQLitePluginConverter.a(readableMap, "executes", (Object) null);
                if (readableArray.isNull(0)) {
                    strArr2 = new String[0];
                    strArr = null;
                    readableArrayArr = null;
                } else {
                    int size = readableArray.size();
                    String[] strArr3 = new String[size];
                    String[] strArr4 = new String[size];
                    ReadableArray[] readableArrayArr2 = new ReadableArray[size];
                    for (int i = 0; i < size; i++) {
                        ReadableMap readableMap2 = (ReadableMap) SQLitePluginConverter.a(readableArray, i, (Object) null);
                        strArr3[i] = SQLitePluginConverter.a(readableMap2, "sql", "");
                        strArr4[i] = SQLitePluginConverter.a(readableMap2, "qid", "");
                        readableArrayArr2[i] = (ReadableArray) SQLitePluginConverter.a(readableMap2, "params", (Object) null);
                    }
                    strArr2 = strArr3;
                    strArr = strArr4;
                    readableArrayArr = readableArrayArr2;
                }
                DBQuery dBQuery = new DBQuery(strArr2, strArr, readableArrayArr, callbackContext);
                DBRunner dBRunner = dbrmap.get(a2);
                if (dBRunner != null) {
                    try {
                        dBRunner.c.put(dBQuery);
                        return true;
                    } catch (Exception e) {
                        FLog.e(TAG, "couldn't add to queue", (Throwable) e);
                        callbackContext.b("couldn't add to queue");
                        return true;
                    }
                } else {
                    callbackContext.b("database not open");
                    return true;
                }
            default:
                return true;
        }
    }

    public void closeAllOpenDatabases() {
        while (!dbrmap.isEmpty()) {
            String next = dbrmap.keySet().iterator().next();
            closeDatabaseNow(next);
            try {
                dbrmap.get(next).c.put(new DBQuery());
            } catch (Exception e) {
                String str = TAG;
                FLog.e(str, "couldn't stop db thread for db: " + next, (Throwable) e);
            }
            dbrmap.remove(next);
        }
    }

    private void startDatabase(String str, ReadableMap readableMap, CallbackContext callbackContext) {
        if (dbrmap.get(str) != null) {
            callbackContext.a("database started");
            return;
        }
        DBRunner dBRunner = new DBRunner(str, readableMap, callbackContext);
        dbrmap.put(str, dBRunner);
        getThreadPool().execute(dBRunner);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:26|27) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:54|55|60|61|29) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:33|(1:35)(1:36)|(4:37|38|39|40)|43|44|28|29) */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        com.facebook.common.logging.FLog.e(TAG, "pre-populated DB asset NOT FOUND in app bundle www subdirectory: " + r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0156, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        com.facebook.common.logging.FLog.e(TAG, "Error importing pre-populated DB asset", (java.lang.Throwable) r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0165, code lost:
        throw new java.lang.Exception("Error importing pre-populated DB asset");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0183, code lost:
        r10 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0064 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00bc */
    /* JADX WARNING: Missing exception handler attribute for start block: B:60:0x0119 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:26:0x0064=Splitter:B:26:0x0064, B:60:0x0119=Splitter:B:60:0x0119, B:43:0x00bc=Splitter:B:43:0x00bc} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.sqlite.SQLiteDatabase openDatabase(java.lang.String r10, java.lang.String r11, int r12, org.pgsqlite.CallbackContext r13) throws java.lang.Exception {
        /*
            r9 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r9.getDatabase(r10)     // Catch:{ all -> 0x01b2 }
            if (r1 == 0) goto L_0x0016
            boolean r1 = r1.isOpen()     // Catch:{ all -> 0x01b2 }
            if (r1 != 0) goto L_0x000e
            goto L_0x0016
        L_0x000e:
            java.lang.Exception r10 = new java.lang.Exception     // Catch:{ all -> 0x01b2 }
            java.lang.String r11 = "Database already open"
            r10.<init>(r11)     // Catch:{ all -> 0x01b2 }
            throw r10     // Catch:{ all -> 0x01b2 }
        L_0x0016:
            r1 = 0
            r2 = 1
            if (r11 == 0) goto L_0x0022
            int r3 = r11.length()     // Catch:{ all -> 0x01b2 }
            if (r3 <= 0) goto L_0x0022
            r3 = 1
            goto L_0x0023
        L_0x0022:
            r3 = 0
        L_0x0023:
            if (r3 == 0) goto L_0x0131
            java.lang.String r4 = "1"
            int r4 = r11.compareTo(r4)     // Catch:{ all -> 0x01b2 }
            if (r4 != 0) goto L_0x007e
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b2 }
            r11.<init>()     // Catch:{ all -> 0x01b2 }
            java.lang.String r4 = "www/"
            r11.append(r4)     // Catch:{ all -> 0x01b2 }
            r11.append(r10)     // Catch:{ all -> 0x01b2 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x01b2 }
            android.content.Context r4 = r9.getContext()     // Catch:{ Exception -> 0x0063 }
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch:{ Exception -> 0x0063 }
            java.io.InputStream r4 = r4.open(r11)     // Catch:{ Exception -> 0x0063 }
            java.lang.String r5 = TAG     // Catch:{ Exception -> 0x0064 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0064 }
            r6.<init>()     // Catch:{ Exception -> 0x0064 }
            java.lang.String r7 = "Pre-populated DB asset FOUND  in app bundle www subdirectory: "
            r6.append(r7)     // Catch:{ Exception -> 0x0064 }
            r6.append(r11)     // Catch:{ Exception -> 0x0064 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0064 }
            com.facebook.common.logging.FLog.v((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x0064 }
        L_0x0060:
            r5 = r0
            goto L_0x0133
        L_0x0063:
            r4 = r0
        L_0x0064:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0183 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0183 }
            r5.<init>()     // Catch:{ all -> 0x0183 }
            java.lang.String r6 = "pre-populated DB asset NOT FOUND in app bundle www subdirectory: "
            r5.append(r6)     // Catch:{ all -> 0x0183 }
            r5.append(r11)     // Catch:{ all -> 0x0183 }
            java.lang.String r11 = r5.toString()     // Catch:{ all -> 0x0183 }
            com.facebook.common.logging.FLog.e((java.lang.String) r1, (java.lang.String) r11)     // Catch:{ all -> 0x0183 }
        L_0x007a:
            r5 = r0
        L_0x007b:
            r1 = 1
            goto L_0x0133
        L_0x007e:
            char r4 = r11.charAt(r1)     // Catch:{ all -> 0x01b2 }
            r5 = 126(0x7e, float:1.77E-43)
            if (r4 != r5) goto L_0x00d3
            java.lang.String r4 = "~/"
            boolean r4 = r11.startsWith(r4)     // Catch:{ all -> 0x01b2 }
            if (r4 == 0) goto L_0x0094
            r4 = 2
            java.lang.String r11 = r11.substring(r4)     // Catch:{ all -> 0x01b2 }
            goto L_0x0098
        L_0x0094:
            java.lang.String r11 = r11.substring(r2)     // Catch:{ all -> 0x01b2 }
        L_0x0098:
            android.content.Context r4 = r9.getContext()     // Catch:{ Exception -> 0x00bb }
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch:{ Exception -> 0x00bb }
            java.io.InputStream r4 = r4.open(r11)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r5 = TAG     // Catch:{ Exception -> 0x00bc }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bc }
            r6.<init>()     // Catch:{ Exception -> 0x00bc }
            java.lang.String r7 = "Pre-populated DB asset FOUND in app bundle subdirectory: "
            r6.append(r7)     // Catch:{ Exception -> 0x00bc }
            r6.append(r11)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00bc }
            com.facebook.common.logging.FLog.v((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x00bc }
            goto L_0x0060
        L_0x00bb:
            r4 = r0
        L_0x00bc:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0183 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0183 }
            r5.<init>()     // Catch:{ all -> 0x0183 }
            java.lang.String r6 = "pre-populated DB asset NOT FOUND in app bundle www subdirectory: "
            r5.append(r6)     // Catch:{ all -> 0x0183 }
            r5.append(r11)     // Catch:{ all -> 0x0183 }
            java.lang.String r11 = r5.toString()     // Catch:{ all -> 0x0183 }
            com.facebook.common.logging.FLog.e((java.lang.String) r1, (java.lang.String) r11)     // Catch:{ all -> 0x0183 }
            goto L_0x007a
        L_0x00d3:
            android.content.Context r4 = r9.getContext()     // Catch:{ all -> 0x01b2 }
            java.io.File r4 = r4.getFilesDir()     // Catch:{ all -> 0x01b2 }
            java.lang.String r5 = "/"
            boolean r5 = r11.startsWith(r5)     // Catch:{ all -> 0x01b2 }
            if (r5 == 0) goto L_0x00e7
            java.lang.String r11 = r11.substring(r2)     // Catch:{ all -> 0x01b2 }
        L_0x00e7:
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0117 }
            r5.<init>(r4, r11)     // Catch:{ Exception -> 0x0117 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0117 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0117 }
            java.lang.String r6 = TAG     // Catch:{ Exception -> 0x0115 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0115 }
            r7.<init>()     // Catch:{ Exception -> 0x0115 }
            java.lang.String r8 = "Pre-populated DB asset FOUND in Files subdirectory: "
            r7.append(r8)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r8 = r5.getCanonicalPath()     // Catch:{ Exception -> 0x0115 }
            r7.append(r8)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0115 }
            com.facebook.common.logging.FLog.v((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x0115 }
            if (r12 != r2) goto L_0x0060
            java.lang.String r6 = TAG     // Catch:{ Exception -> 0x0119 }
            java.lang.String r7 = "Detected read-only mode request for external asset."
            com.facebook.common.logging.FLog.v((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x0119 }
            goto L_0x0133
        L_0x0115:
            r5 = r0
            goto L_0x0119
        L_0x0117:
            r4 = r0
            r5 = r4
        L_0x0119:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0183 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0183 }
            r6.<init>()     // Catch:{ all -> 0x0183 }
            java.lang.String r7 = "Error opening pre-populated DB asset in app bundle www subdirectory: "
            r6.append(r7)     // Catch:{ all -> 0x0183 }
            r6.append(r11)     // Catch:{ all -> 0x0183 }
            java.lang.String r11 = r6.toString()     // Catch:{ all -> 0x0183 }
            com.facebook.common.logging.FLog.e((java.lang.String) r1, (java.lang.String) r11)     // Catch:{ all -> 0x0183 }
            goto L_0x007b
        L_0x0131:
            r4 = r0
            r5 = r4
        L_0x0133:
            if (r5 != 0) goto L_0x0185
            r12 = 268435456(0x10000000, float:2.5243549E-29)
            android.content.Context r11 = r9.getContext()     // Catch:{ all -> 0x0183 }
            java.io.File r5 = r11.getDatabasePath(r10)     // Catch:{ all -> 0x0183 }
            boolean r11 = r5.exists()     // Catch:{ all -> 0x0183 }
            if (r11 != 0) goto L_0x0175
            if (r3 == 0) goto L_0x0175
            if (r1 != 0) goto L_0x0166
            if (r4 == 0) goto L_0x0166
            java.lang.String r11 = TAG     // Catch:{ all -> 0x0183 }
            java.lang.String r1 = "Copying pre-populated db asset to destination"
            com.facebook.common.logging.FLog.v((java.lang.String) r11, (java.lang.String) r1)     // Catch:{ all -> 0x0183 }
            r9.createFromAssets(r10, r5, r4)     // Catch:{ Exception -> 0x0156 }
            goto L_0x0175
        L_0x0156:
            r10 = move-exception
            java.lang.String r11 = TAG     // Catch:{ all -> 0x0183 }
            java.lang.String r12 = "Error importing pre-populated DB asset"
            com.facebook.common.logging.FLog.e((java.lang.String) r11, (java.lang.String) r12, (java.lang.Throwable) r10)     // Catch:{ all -> 0x0183 }
            java.lang.Exception r10 = new java.lang.Exception     // Catch:{ all -> 0x0183 }
            java.lang.String r11 = "Error importing pre-populated DB asset"
            r10.<init>(r11)     // Catch:{ all -> 0x0183 }
            throw r10     // Catch:{ all -> 0x0183 }
        L_0x0166:
            java.lang.String r10 = TAG     // Catch:{ all -> 0x0183 }
            java.lang.String r11 = "Unable to import pre-populated db asset"
            com.facebook.common.logging.FLog.e((java.lang.String) r10, (java.lang.String) r11)     // Catch:{ all -> 0x0183 }
            java.lang.Exception r10 = new java.lang.Exception     // Catch:{ all -> 0x0183 }
            java.lang.String r11 = "Unable to import pre-populated db asset"
            r10.<init>(r11)     // Catch:{ all -> 0x0183 }
            throw r10     // Catch:{ all -> 0x0183 }
        L_0x0175:
            boolean r10 = r5.exists()     // Catch:{ all -> 0x0183 }
            if (r10 != 0) goto L_0x0185
            java.io.File r10 = r5.getParentFile()     // Catch:{ all -> 0x0183 }
            r10.mkdirs()     // Catch:{ all -> 0x0183 }
            goto L_0x0185
        L_0x0183:
            r10 = move-exception
            goto L_0x01b4
        L_0x0185:
            java.lang.String r10 = TAG     // Catch:{ all -> 0x0183 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0183 }
            r11.<init>()     // Catch:{ all -> 0x0183 }
            java.lang.String r1 = "DB file is ready, proceeding to OPEN SQLite DB: "
            r11.append(r1)     // Catch:{ all -> 0x0183 }
            java.lang.String r1 = r5.getAbsolutePath()     // Catch:{ all -> 0x0183 }
            r11.append(r1)     // Catch:{ all -> 0x0183 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0183 }
            com.facebook.common.logging.FLog.v((java.lang.String) r10, (java.lang.String) r11)     // Catch:{ all -> 0x0183 }
            java.lang.String r10 = r5.getAbsolutePath()     // Catch:{ all -> 0x0183 }
            android.database.sqlite.SQLiteDatabase r10 = android.database.sqlite.SQLiteDatabase.openDatabase(r10, r0, r12)     // Catch:{ all -> 0x0183 }
            if (r13 == 0) goto L_0x01ae
            java.lang.String r11 = "Database opened"
            r13.a((java.lang.String) r11)     // Catch:{ all -> 0x0183 }
        L_0x01ae:
            r9.closeQuietly(r4)
            return r10
        L_0x01b2:
            r10 = move-exception
            r4 = r0
        L_0x01b4:
            r9.closeQuietly(r4)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.pgsqlite.SQLitePlugin.openDatabase(java.lang.String, java.lang.String, int, org.pgsqlite.CallbackContext):android.database.sqlite.SQLiteDatabase");
    }

    private void createFromAssets(String str, File file, InputStream inputStream) throws Exception {
        FileOutputStream fileOutputStream = null;
        try {
            FLog.v(TAG, "Copying pre-populated DB content");
            String absolutePath = file.getAbsolutePath();
            String substring = absolutePath.substring(0, absolutePath.lastIndexOf("/") + 1);
            File file2 = new File(substring);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(substring + str);
            FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream2.write(bArr, 0, read);
                    } else {
                        String str2 = TAG;
                        FLog.v(str2, "Copied pre-populated DB asset to: " + file3.getAbsolutePath());
                        closeQuietly(fileOutputStream2);
                        return;
                    }
                }
            } catch (Throwable th) {
                fileOutputStream = fileOutputStream2;
                th = th;
                closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(fileOutputStream);
            throw th;
        }
    }

    private void closeDatabase(String str, CallbackContext callbackContext) {
        DBRunner dBRunner = dbrmap.get(str);
        if (dBRunner != null) {
            try {
                dBRunner.c.put(new DBQuery(false, callbackContext));
            } catch (Exception e) {
                if (callbackContext != null) {
                    callbackContext.b("couldn't close database" + e);
                }
                FLog.e(TAG, "couldn't close database", (Throwable) e);
            }
        } else if (callbackContext != null) {
            callbackContext.a("database closed");
        }
    }

    /* access modifiers changed from: private */
    public void closeDatabaseNow(String str) {
        SQLiteDatabase database = getDatabase(str);
        if (database != null) {
            database.close();
        }
    }

    private void attachDatabase(String str, String str2, String str3, CallbackContext callbackContext) {
        DBRunner dBRunner = dbrmap.get(str);
        if (dBRunner != null) {
            String absolutePath = getContext().getDatabasePath(str2).getAbsolutePath();
            try {
                dBRunner.c.put(new DBQuery(new String[]{"ATTACH DATABASE '" + absolutePath + "' AS " + str3}, new String[]{"1111"}, (ReadableArray[]) null, callbackContext));
            } catch (InterruptedException unused) {
                callbackContext.b("Can't put query in the queue. Interrupted.");
            }
        } else {
            callbackContext.b("Database " + str + "i s not created yet");
        }
    }

    private void deleteDatabase(String str, CallbackContext callbackContext) {
        DBRunner dBRunner = dbrmap.get(str);
        if (dBRunner != null) {
            try {
                dBRunner.c.put(new DBQuery(true, callbackContext));
            } catch (Exception e) {
                if (callbackContext != null) {
                    callbackContext.b("couldn't close database" + e);
                }
                FLog.e(TAG, "couldn't close database", (Throwable) e);
            }
        } else if (deleteDatabaseNow(str)) {
            callbackContext.a("database deleted");
        } else {
            callbackContext.b("couldn't delete database");
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public boolean deleteDatabaseNow(String str) {
        return SQLiteDatabase.deleteDatabase(getContext().getDatabasePath(str));
    }

    private SQLiteDatabase getDatabase(String str) {
        DBRunner dBRunner = dbrmap.get(str);
        if (dBRunner == null) {
            return null;
        }
        return dBRunner.e;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0135 A[Catch:{ Exception -> 0x0156 }] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0141 A[Catch:{ Exception -> 0x013f }] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0145 A[Catch:{ Exception -> 0x013f }] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0182  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeSqlBatch(java.lang.String r20, java.lang.String[] r21, com.facebook.react.bridge.ReadableArray[] r22, java.lang.String[] r23, org.pgsqlite.CallbackContext r24) {
        /*
            r19 = this;
            r1 = r19
            r2 = r21
            r3 = r24
            android.database.sqlite.SQLiteDatabase r4 = r19.getDatabase(r20)
            if (r4 != 0) goto L_0x0012
            java.lang.String r0 = "database has been closed"
            r3.b((java.lang.String) r0)
            return
        L_0x0012:
            int r5 = r2.length
            com.facebook.react.bridge.WritableArray r6 = com.facebook.react.bridge.Arguments.createArray()
            r8 = 0
        L_0x0018:
            if (r8 >= r5) goto L_0x01a7
            r9 = r23[r8]
            java.lang.String r0 = "unknown"
            r11 = r2[r8]     // Catch:{ Exception -> 0x0158 }
            org.pgsqlite.SQLitePlugin$QueryType r12 = r1.getQueryType(r11)     // Catch:{ Exception -> 0x0158 }
            org.pgsqlite.SQLitePlugin$QueryType r13 = org.pgsqlite.SQLitePlugin.QueryType.update     // Catch:{ Exception -> 0x0158 }
            if (r12 == r13) goto L_0x0105
            org.pgsqlite.SQLitePlugin$QueryType r13 = org.pgsqlite.SQLitePlugin.QueryType.delete     // Catch:{ Exception -> 0x0158 }
            if (r12 != r13) goto L_0x002e
            goto L_0x0105
        L_0x002e:
            org.pgsqlite.SQLitePlugin$QueryType r13 = org.pgsqlite.SQLitePlugin.QueryType.insert     // Catch:{ Exception -> 0x0158 }
            if (r12 != r13) goto L_0x008f
            if (r22 == 0) goto L_0x008f
            java.lang.String r12 = "executeSqlBatch"
            java.lang.String r13 = "INSERT"
            com.facebook.common.logging.FLog.d((java.lang.String) r12, (java.lang.String) r13)     // Catch:{ Exception -> 0x0158 }
            android.database.sqlite.SQLiteStatement r12 = r4.compileStatement(r11)     // Catch:{ Exception -> 0x0158 }
            r13 = r22[r8]     // Catch:{ Exception -> 0x0158 }
            r1.bindArgsToStatement(r12, r13)     // Catch:{ Exception -> 0x0158 }
            r15 = r8
            long r7 = r12.executeInsert()     // Catch:{ SQLiteException -> 0x0079, all -> 0x0076 }
            com.facebook.react.bridge.WritableMap r13 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ SQLiteException -> 0x0079, all -> 0x0076 }
            r16 = -1
            int r18 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r18 == 0) goto L_0x0066
            java.lang.String r10 = "insertId"
            double r7 = (double) r7     // Catch:{ SQLiteException -> 0x0063, all -> 0x0060 }
            r13.putDouble(r10, r7)     // Catch:{ SQLiteException -> 0x0063, all -> 0x0060 }
            java.lang.String r7 = "rowsAffected"
            r8 = 1
            r13.putInt(r7, r8)     // Catch:{ SQLiteException -> 0x0063, all -> 0x0060 }
            goto L_0x006c
        L_0x0060:
            r0 = move-exception
            r10 = r13
            goto L_0x008b
        L_0x0063:
            r0 = move-exception
            r10 = r13
            goto L_0x007b
        L_0x0066:
            java.lang.String r7 = "rowsAffected"
            r8 = 0
            r13.putInt(r7, r8)     // Catch:{ SQLiteException -> 0x0063, all -> 0x0060 }
        L_0x006c:
            r1.closeQuietly(r12)     // Catch:{ Exception -> 0x0072 }
            r10 = r13
            goto L_0x0142
        L_0x0072:
            r0 = move-exception
            r10 = r13
            goto L_0x015b
        L_0x0076:
            r0 = move-exception
            r10 = 0
            goto L_0x008b
        L_0x0079:
            r0 = move-exception
            r10 = 0
        L_0x007b:
            java.lang.String r7 = r0.getMessage()     // Catch:{ all -> 0x008a }
            java.lang.String r8 = TAG     // Catch:{ all -> 0x008a }
            java.lang.String r13 = "SQLiteDatabase.executeInsert() failed"
            com.facebook.common.logging.FLog.e((java.lang.String) r8, (java.lang.String) r13, (java.lang.Throwable) r0)     // Catch:{ all -> 0x008a }
            r1.closeQuietly(r12)     // Catch:{ Exception -> 0x013f }
            goto L_0x00b3
        L_0x008a:
            r0 = move-exception
        L_0x008b:
            r1.closeQuietly(r12)     // Catch:{ Exception -> 0x013f }
            throw r0     // Catch:{ Exception -> 0x013f }
        L_0x008f:
            r15 = r8
            r8 = 1
            org.pgsqlite.SQLitePlugin$QueryType r7 = org.pgsqlite.SQLitePlugin.QueryType.begin     // Catch:{ Exception -> 0x0156 }
            if (r12 != r7) goto L_0x00b6
            r4.beginTransaction()     // Catch:{ SQLiteException -> 0x00a6 }
            com.facebook.react.bridge.WritableMap r10 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ SQLiteException -> 0x00a6 }
            java.lang.String r7 = "rowsAffected"
            r8 = 0
            r10.putInt(r7, r8)     // Catch:{ SQLiteException -> 0x00a4 }
            goto L_0x0142
        L_0x00a4:
            r0 = move-exception
            goto L_0x00a8
        L_0x00a6:
            r0 = move-exception
            r10 = 0
        L_0x00a8:
            java.lang.String r7 = r0.getMessage()     // Catch:{ Exception -> 0x013f }
            java.lang.String r8 = TAG     // Catch:{ Exception -> 0x013f }
            java.lang.String r12 = "SQLiteDatabase.beginTransaction() failed"
            com.facebook.common.logging.FLog.e((java.lang.String) r8, (java.lang.String) r12, (java.lang.Throwable) r0)     // Catch:{ Exception -> 0x013f }
        L_0x00b3:
            r0 = r7
            goto L_0x0142
        L_0x00b6:
            org.pgsqlite.SQLitePlugin$QueryType r7 = org.pgsqlite.SQLitePlugin.QueryType.commit     // Catch:{ Exception -> 0x0156 }
            if (r12 != r7) goto L_0x00dc
            r4.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x00ce }
            r4.endTransaction()     // Catch:{ SQLiteException -> 0x00ce }
            com.facebook.react.bridge.WritableMap r10 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ SQLiteException -> 0x00ce }
            java.lang.String r7 = "rowsAffected"
            r8 = 0
            r10.putInt(r7, r8)     // Catch:{ SQLiteException -> 0x00cc }
            goto L_0x0142
        L_0x00cc:
            r0 = move-exception
            goto L_0x00d0
        L_0x00ce:
            r0 = move-exception
            r10 = 0
        L_0x00d0:
            java.lang.String r7 = r0.getMessage()     // Catch:{ Exception -> 0x013f }
            java.lang.String r8 = TAG     // Catch:{ Exception -> 0x013f }
            java.lang.String r12 = "SQLiteDatabase.setTransactionSuccessful/endTransaction() failed"
            com.facebook.common.logging.FLog.e((java.lang.String) r8, (java.lang.String) r12, (java.lang.Throwable) r0)     // Catch:{ Exception -> 0x013f }
            goto L_0x00b3
        L_0x00dc:
            org.pgsqlite.SQLitePlugin$QueryType r7 = org.pgsqlite.SQLitePlugin.QueryType.rollback     // Catch:{ Exception -> 0x0156 }
            if (r12 != r7) goto L_0x0102
            r4.endTransaction()     // Catch:{ SQLiteException -> 0x00f3 }
            com.facebook.react.bridge.WritableMap r10 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ SQLiteException -> 0x00f3 }
            java.lang.String r7 = "rowsAffected"
            r12 = 0
            r10.putInt(r7, r12)     // Catch:{ SQLiteException -> 0x00ee }
            goto L_0x0142
        L_0x00ee:
            r0 = move-exception
            goto L_0x00f6
        L_0x00f0:
            r0 = move-exception
            r12 = 0
            goto L_0x00f6
        L_0x00f3:
            r0 = move-exception
            r12 = 0
            r10 = 0
        L_0x00f6:
            java.lang.String r7 = r0.getMessage()     // Catch:{ Exception -> 0x013f }
            java.lang.String r8 = TAG     // Catch:{ Exception -> 0x013f }
            java.lang.String r13 = "SQLiteDatabase.endTransaction() failed"
            com.facebook.common.logging.FLog.e((java.lang.String) r8, (java.lang.String) r13, (java.lang.Throwable) r0)     // Catch:{ Exception -> 0x013f }
            goto L_0x00b3
        L_0x0102:
            r10 = 0
            r14 = 1
            goto L_0x0143
        L_0x0105:
            r15 = r8
            r12 = 0
            r7 = -1
            android.database.sqlite.SQLiteStatement r10 = r4.compileStatement(r11)     // Catch:{ SQLiteException -> 0x0121, all -> 0x011e }
            if (r22 == 0) goto L_0x0116
            r8 = r22[r15]     // Catch:{ SQLiteException -> 0x0114 }
            r1.bindArgsToStatement(r10, r8)     // Catch:{ SQLiteException -> 0x0114 }
            goto L_0x0116
        L_0x0114:
            r0 = move-exception
            goto L_0x0123
        L_0x0116:
            int r8 = r10.executeUpdateDelete()     // Catch:{ SQLiteException -> 0x0114 }
            r1.closeQuietly(r10)     // Catch:{ Exception -> 0x0156 }
            goto L_0x0133
        L_0x011e:
            r0 = move-exception
            r10 = 0
            goto L_0x0152
        L_0x0121:
            r0 = move-exception
            r10 = 0
        L_0x0123:
            java.lang.String r8 = r0.getMessage()     // Catch:{ all -> 0x0151 }
            java.lang.String r13 = TAG     // Catch:{ all -> 0x0151 }
            java.lang.String r12 = "SQLiteStatement.executeUpdateDelete() failed"
            com.facebook.common.logging.FLog.e((java.lang.String) r13, (java.lang.String) r12, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0151 }
            r1.closeQuietly(r10)     // Catch:{ Exception -> 0x0156 }
            r0 = r8
            r8 = -1
        L_0x0133:
            if (r8 == r7) goto L_0x0141
            com.facebook.react.bridge.WritableMap r10 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ Exception -> 0x0156 }
            java.lang.String r7 = "rowsAffected"
            r10.putInt(r7, r8)     // Catch:{ Exception -> 0x013f }
            goto L_0x0142
        L_0x013f:
            r0 = move-exception
            goto L_0x015b
        L_0x0141:
            r10 = 0
        L_0x0142:
            r14 = 0
        L_0x0143:
            if (r14 == 0) goto L_0x0167
            if (r22 == 0) goto L_0x014a
            r7 = r22[r15]     // Catch:{ Exception -> 0x013f }
            goto L_0x014b
        L_0x014a:
            r7 = 0
        L_0x014b:
            com.facebook.react.bridge.WritableMap r7 = r1.executeSqlStatementQuery(r4, r11, r7, r3)     // Catch:{ Exception -> 0x013f }
            r10 = r7
            goto L_0x0167
        L_0x0151:
            r0 = move-exception
        L_0x0152:
            r1.closeQuietly(r10)     // Catch:{ Exception -> 0x0156 }
            throw r0     // Catch:{ Exception -> 0x0156 }
        L_0x0156:
            r0 = move-exception
            goto L_0x015a
        L_0x0158:
            r0 = move-exception
            r15 = r8
        L_0x015a:
            r10 = 0
        L_0x015b:
            java.lang.String r7 = r0.getMessage()
            java.lang.String r8 = TAG
            java.lang.String r11 = "SQLitePlugin.executeSql[Batch](): failed"
            com.facebook.common.logging.FLog.e((java.lang.String) r8, (java.lang.String) r11, (java.lang.Throwable) r0)
            r0 = r7
        L_0x0167:
            if (r10 == 0) goto L_0x0182
            com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String r7 = "qid"
            r0.putString(r7, r9)
            java.lang.String r7 = "type"
            java.lang.String r8 = "success"
            r0.putString(r7, r8)
            java.lang.String r7 = "result"
            r0.putMap(r7, r10)
            r6.pushMap(r0)
            goto L_0x01a3
        L_0x0182:
            com.facebook.react.bridge.WritableMap r7 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String r8 = "qid"
            r7.putString(r8, r9)
            java.lang.String r8 = "type"
            java.lang.String r9 = "error"
            r7.putString(r8, r9)
            com.facebook.react.bridge.WritableMap r8 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String r9 = "message"
            r8.putString(r9, r0)
            java.lang.String r0 = "result"
            r7.putMap(r0, r8)
            r6.pushMap(r7)
        L_0x01a3:
            int r8 = r15 + 1
            goto L_0x0018
        L_0x01a7:
            r3.a((com.facebook.react.bridge.WritableArray) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.pgsqlite.SQLitePlugin.executeSqlBatch(java.lang.String, java.lang.String[], com.facebook.react.bridge.ReadableArray[], java.lang.String[], org.pgsqlite.CallbackContext):void");
    }

    private QueryType getQueryType(String str) {
        Matcher matcher = FIRST_WORD.matcher(str);
        if (matcher.find()) {
            try {
                return QueryType.valueOf(matcher.group(1).toLowerCase(Locale.US));
            } catch (IllegalArgumentException unused) {
            }
        }
        return QueryType.other;
    }

    private void bindArgsToStatement(SQLiteStatement sQLiteStatement, ReadableArray readableArray) {
        for (int i = 0; i < readableArray.size(); i++) {
            if (readableArray.getType(i) == ReadableType.Number) {
                double d = readableArray.getDouble(i);
                long j = (long) d;
                if (d == ((double) j)) {
                    sQLiteStatement.bindLong(i + 1, j);
                } else {
                    sQLiteStatement.bindDouble(i + 1, d);
                }
            } else if (readableArray.isNull(i)) {
                sQLiteStatement.bindNull(i + 1);
            } else {
                sQLiteStatement.bindString(i + 1, SQLitePluginConverter.a(readableArray, i, ""));
            }
        }
    }

    private WritableMap executeSqlStatementQuery(SQLiteDatabase sQLiteDatabase, String str, ReadableArray readableArray, CallbackContext callbackContext) throws Exception {
        WritableMap createMap = Arguments.createMap();
        Cursor cursor = null;
        try {
            String[] strArr = new String[0];
            if (readableArray != null) {
                int size = readableArray.size();
                String[] strArr2 = new String[size];
                for (int i = 0; i < size; i++) {
                    if (readableArray.isNull(i)) {
                        strArr2[i] = "";
                    } else {
                        strArr2[i] = SQLitePluginConverter.a(readableArray, i, "");
                    }
                }
                strArr = strArr2;
            }
            Cursor rawQuery = sQLiteDatabase.rawQuery(str, strArr);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        WritableArray createArray = Arguments.createArray();
                        int columnCount = rawQuery.getColumnCount();
                        do {
                            WritableMap createMap2 = Arguments.createMap();
                            for (int i2 = 0; i2 < columnCount; i2++) {
                                bindRow(createMap2, rawQuery.getColumnName(i2), rawQuery, i2);
                            }
                            createArray.pushMap(createMap2);
                        } while (rawQuery.moveToNext());
                        createMap.putArray("rows", createArray);
                    }
                } catch (Throwable th) {
                    cursor = rawQuery;
                    th = th;
                    closeQuietly(cursor);
                    throw th;
                }
            }
            closeQuietly(rawQuery);
            return createMap;
        } catch (Exception e) {
            FLog.e(TAG, "SQLitePlugin.executeSql[Batch]() failed", (Throwable) e);
            throw e;
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(cursor);
            throw th;
        }
    }

    @SuppressLint({"NewApi"})
    private void bindRow(WritableMap writableMap, String str, Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type != 4) {
            switch (type) {
                case 0:
                    writableMap.putNull(str);
                    return;
                case 1:
                    writableMap.putDouble(str, (double) cursor.getLong(i));
                    return;
                case 2:
                    writableMap.putDouble(str, cursor.getDouble(i));
                    return;
                default:
                    writableMap.putString(str, cursor.getString(i));
                    return;
            }
        } else {
            writableMap.putString(str, new String(Base64.encode(cursor.getBlob(i), 0)));
        }
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private class DBRunner implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final String f4117a;
        final int b;
        final BlockingQueue<DBQuery> c;
        final CallbackContext d;
        SQLiteDatabase e;
        private String g;
        private boolean h;

        DBRunner(String str, ReadableMap readableMap, CallbackContext callbackContext) {
            this.f4117a = str;
            int i = C.ENCODING_PCM_MU_LAW;
            try {
                this.g = SQLitePluginConverter.a(readableMap, "assetFilename", (String) null);
                if (this.g != null && this.g.length() > 0 && SQLitePluginConverter.a(readableMap, "readOnly", false)) {
                    i = 1;
                }
            } catch (Exception e2) {
                FLog.e(SQLitePlugin.TAG, "Error retrieving assetFilename or mode from options:", (Throwable) e2);
            }
            this.b = i;
            this.h = SQLitePluginConverter.a(readableMap, "androidLockWorkaround", false);
            if (this.h) {
                FLog.i(SQLitePlugin.TAG, "Android db closing/locking workaround applied");
            }
            this.c = new LinkedBlockingQueue();
            this.d = callbackContext;
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x0075 A[SYNTHETIC, Splitter:B:24:0x0075] */
        /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r10 = this;
                org.pgsqlite.SQLitePlugin r0 = org.pgsqlite.SQLitePlugin.this     // Catch:{ SQLiteException -> 0x0115, Exception -> 0x00eb }
                java.lang.String r1 = r10.f4117a     // Catch:{ SQLiteException -> 0x0115, Exception -> 0x00eb }
                java.lang.String r2 = r10.g     // Catch:{ SQLiteException -> 0x0115, Exception -> 0x00eb }
                int r3 = r10.b     // Catch:{ SQLiteException -> 0x0115, Exception -> 0x00eb }
                org.pgsqlite.CallbackContext r4 = r10.d     // Catch:{ SQLiteException -> 0x0115, Exception -> 0x00eb }
                android.database.sqlite.SQLiteDatabase r0 = r0.openDatabase(r1, r2, r3, r4)     // Catch:{ SQLiteException -> 0x0115, Exception -> 0x00eb }
                r10.e = r0     // Catch:{ SQLiteException -> 0x0115, Exception -> 0x00eb }
                r0 = 0
                java.util.concurrent.BlockingQueue<org.pgsqlite.SQLitePlugin$DBQuery> r1 = r10.c     // Catch:{ Exception -> 0x0064 }
                java.lang.Object r1 = r1.take()     // Catch:{ Exception -> 0x0064 }
                org.pgsqlite.SQLitePlugin$DBQuery r1 = (org.pgsqlite.SQLitePlugin.DBQuery) r1     // Catch:{ Exception -> 0x0064 }
            L_0x0019:
                boolean r2 = r1.f4116a     // Catch:{ Exception -> 0x0062 }
                if (r2 != 0) goto L_0x006f
                org.pgsqlite.SQLitePlugin r3 = org.pgsqlite.SQLitePlugin.this     // Catch:{ Exception -> 0x0062 }
                java.lang.String r4 = r10.f4117a     // Catch:{ Exception -> 0x0062 }
                java.lang.String[] r5 = r1.d     // Catch:{ Exception -> 0x0062 }
                com.facebook.react.bridge.ReadableArray[] r6 = r1.f     // Catch:{ Exception -> 0x0062 }
                java.lang.String[] r7 = r1.e     // Catch:{ Exception -> 0x0062 }
                org.pgsqlite.CallbackContext r8 = r1.g     // Catch:{ Exception -> 0x0062 }
                r3.executeSqlBatch(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0062 }
                boolean r2 = r10.h     // Catch:{ Exception -> 0x0062 }
                if (r2 == 0) goto L_0x0058
                java.lang.String[] r2 = r1.d     // Catch:{ Exception -> 0x0062 }
                int r2 = r2.length     // Catch:{ Exception -> 0x0062 }
                r3 = 1
                if (r2 != r3) goto L_0x0058
                java.lang.String[] r2 = r1.d     // Catch:{ Exception -> 0x0062 }
                r3 = 0
                r2 = r2[r3]     // Catch:{ Exception -> 0x0062 }
                java.lang.String r3 = "COMMIT"
                boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x0062 }
                if (r2 == 0) goto L_0x0058
                org.pgsqlite.SQLitePlugin r2 = org.pgsqlite.SQLitePlugin.this     // Catch:{ Exception -> 0x0062 }
                java.lang.String r3 = r10.f4117a     // Catch:{ Exception -> 0x0062 }
                r2.closeDatabaseNow(r3)     // Catch:{ Exception -> 0x0062 }
                org.pgsqlite.SQLitePlugin r2 = org.pgsqlite.SQLitePlugin.this     // Catch:{ Exception -> 0x0062 }
                java.lang.String r3 = r10.f4117a     // Catch:{ Exception -> 0x0062 }
                java.lang.String r4 = ""
                int r5 = r10.b     // Catch:{ Exception -> 0x0062 }
                android.database.sqlite.SQLiteDatabase r2 = r2.openDatabase(r3, r4, r5, r0)     // Catch:{ Exception -> 0x0062 }
                r10.e = r2     // Catch:{ Exception -> 0x0062 }
            L_0x0058:
                java.util.concurrent.BlockingQueue<org.pgsqlite.SQLitePlugin$DBQuery> r2 = r10.c     // Catch:{ Exception -> 0x0062 }
                java.lang.Object r2 = r2.take()     // Catch:{ Exception -> 0x0062 }
                org.pgsqlite.SQLitePlugin$DBQuery r2 = (org.pgsqlite.SQLitePlugin.DBQuery) r2     // Catch:{ Exception -> 0x0062 }
                r1 = r2
                goto L_0x0019
            L_0x0062:
                r0 = move-exception
                goto L_0x0068
            L_0x0064:
                r1 = move-exception
                r9 = r1
                r1 = r0
                r0 = r9
            L_0x0068:
                java.lang.String r2 = org.pgsqlite.SQLitePlugin.TAG
                java.lang.String r3 = "unexpected error"
                com.facebook.common.logging.FLog.e((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r0)
            L_0x006f:
                if (r1 == 0) goto L_0x00ea
                boolean r0 = r1.b
                if (r0 == 0) goto L_0x00ea
                org.pgsqlite.SQLitePlugin r0 = org.pgsqlite.SQLitePlugin.this     // Catch:{ Exception -> 0x00c8 }
                java.lang.String r2 = r10.f4117a     // Catch:{ Exception -> 0x00c8 }
                r0.closeDatabaseNow(r2)     // Catch:{ Exception -> 0x00c8 }
                java.util.concurrent.ConcurrentHashMap<java.lang.String, org.pgsqlite.SQLitePlugin$DBRunner> r0 = org.pgsqlite.SQLitePlugin.dbrmap     // Catch:{ Exception -> 0x00c8 }
                java.lang.String r2 = r10.f4117a     // Catch:{ Exception -> 0x00c8 }
                r0.remove(r2)     // Catch:{ Exception -> 0x00c8 }
                boolean r0 = r1.c     // Catch:{ Exception -> 0x00c8 }
                if (r0 != 0) goto L_0x008f
                org.pgsqlite.CallbackContext r0 = r1.g     // Catch:{ Exception -> 0x00c8 }
                java.lang.String r2 = "database removed"
                r0.a((java.lang.String) r2)     // Catch:{ Exception -> 0x00c8 }
                goto L_0x00ea
            L_0x008f:
                org.pgsqlite.SQLitePlugin r0 = org.pgsqlite.SQLitePlugin.this     // Catch:{ Exception -> 0x00a9 }
                java.lang.String r2 = r10.f4117a     // Catch:{ Exception -> 0x00a9 }
                boolean r0 = r0.deleteDatabaseNow(r2)     // Catch:{ Exception -> 0x00a9 }
                if (r0 == 0) goto L_0x00a1
                org.pgsqlite.CallbackContext r0 = r1.g     // Catch:{ Exception -> 0x00a9 }
                java.lang.String r2 = "database removed"
                r0.a((java.lang.String) r2)     // Catch:{ Exception -> 0x00a9 }
                goto L_0x00ea
            L_0x00a1:
                org.pgsqlite.CallbackContext r0 = r1.g     // Catch:{ Exception -> 0x00a9 }
                java.lang.String r2 = "couldn't delete database"
                r0.b((java.lang.String) r2)     // Catch:{ Exception -> 0x00a9 }
                goto L_0x00ea
            L_0x00a9:
                r0 = move-exception
                java.lang.String r2 = org.pgsqlite.SQLitePlugin.TAG     // Catch:{ Exception -> 0x00c8 }
                java.lang.String r3 = "couldn't delete database"
                com.facebook.common.logging.FLog.e((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r0)     // Catch:{ Exception -> 0x00c8 }
                org.pgsqlite.CallbackContext r2 = r1.g     // Catch:{ Exception -> 0x00c8 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c8 }
                r3.<init>()     // Catch:{ Exception -> 0x00c8 }
                java.lang.String r4 = "couldn't delete database: "
                r3.append(r4)     // Catch:{ Exception -> 0x00c8 }
                r3.append(r0)     // Catch:{ Exception -> 0x00c8 }
                java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x00c8 }
                r2.b((java.lang.String) r0)     // Catch:{ Exception -> 0x00c8 }
                goto L_0x00ea
            L_0x00c8:
                r0 = move-exception
                java.lang.String r2 = org.pgsqlite.SQLitePlugin.TAG
                java.lang.String r3 = "couldn't close database"
                com.facebook.common.logging.FLog.e((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r0)
                org.pgsqlite.CallbackContext r2 = r1.g
                if (r2 == 0) goto L_0x00ea
                org.pgsqlite.CallbackContext r1 = r1.g
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "couldn't close database: "
                r2.append(r3)
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                r1.b((java.lang.String) r0)
            L_0x00ea:
                return
            L_0x00eb:
                r0 = move-exception
                java.lang.String r1 = org.pgsqlite.SQLitePlugin.TAG
                java.lang.String r2 = "Unexpected error opening database, stopping db thread"
                com.facebook.common.logging.FLog.e((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
                org.pgsqlite.CallbackContext r1 = r10.d
                if (r1 == 0) goto L_0x010d
                org.pgsqlite.CallbackContext r1 = r10.d
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Can't open database."
                r2.append(r3)
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                r1.b((java.lang.String) r0)
            L_0x010d:
                java.util.concurrent.ConcurrentHashMap<java.lang.String, org.pgsqlite.SQLitePlugin$DBRunner> r0 = org.pgsqlite.SQLitePlugin.dbrmap
                java.lang.String r1 = r10.f4117a
                r0.remove(r1)
                return
            L_0x0115:
                r0 = move-exception
                java.lang.String r1 = org.pgsqlite.SQLitePlugin.TAG
                java.lang.String r2 = "SQLite error opening database, stopping db thread"
                com.facebook.common.logging.FLog.e((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
                org.pgsqlite.CallbackContext r1 = r10.d
                if (r1 == 0) goto L_0x0137
                org.pgsqlite.CallbackContext r1 = r10.d
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Can't open database."
                r2.append(r3)
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                r1.b((java.lang.String) r0)
            L_0x0137:
                java.util.concurrent.ConcurrentHashMap<java.lang.String, org.pgsqlite.SQLitePlugin$DBRunner> r0 = org.pgsqlite.SQLitePlugin.dbrmap
                java.lang.String r1 = r10.f4117a
                r0.remove(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.pgsqlite.SQLitePlugin.DBRunner.run():void");
        }
    }

    private final class DBQuery {

        /* renamed from: a  reason: collision with root package name */
        final boolean f4116a;
        final boolean b;
        final boolean c;
        final String[] d;
        final String[] e;
        final ReadableArray[] f;
        final CallbackContext g;

        DBQuery(String[] strArr, String[] strArr2, ReadableArray[] readableArrayArr, CallbackContext callbackContext) {
            this.f4116a = false;
            this.b = false;
            this.c = false;
            this.d = strArr;
            this.e = strArr2;
            this.f = readableArrayArr;
            this.g = callbackContext;
        }

        DBQuery(boolean z, CallbackContext callbackContext) {
            this.f4116a = true;
            this.b = true;
            this.c = z;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = callbackContext;
        }

        DBQuery() {
            this.f4116a = true;
            this.b = false;
            this.c = false;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
        }
    }
}
