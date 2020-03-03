package com.xiaomi.smarthome.scenenew.debug;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.config.PropertyConfigurator;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.file.FileUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.scenenew.debug.DebugSerializedAsyncTaskProcessor;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyDebugLogger {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21933a = "debug_scene";
    public static final String b = "print_log";
    public static final String c = "open_log_t";
    public static final String d = "[0-9]{1,2}\\.txt";
    public static String e = null;
    public static String f = "log.zip";
    public static final long g = 104857600;
    private static final String i = "plug_";
    private static MyDebugLogger j = null;
    private static Object k = new Object();
    /* access modifiers changed from: private */
    public static Logger l = null;
    private static final int m = 10485760;
    private static SimpleDateFormat n = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
    private static DebugSerializedAsyncTaskProcessor o;
    /* access modifiers changed from: private */
    public static SimpleDateFormat p = new SimpleDateFormat("yyyy-MM-dd");
    /* access modifiers changed from: private */
    public static Context q;
    private static String r;
    /* access modifiers changed from: private */
    public static String s = "SmartHome";
    /* access modifiers changed from: private */
    public static List<LogData> t = Collections.synchronizedList(new ArrayList());
    OkHttpClient h;

    private MyDebugLogger() {
        j();
    }

    public static MyDebugLogger a() {
        if (j == null) {
            synchronized (k) {
                if (j == null) {
                    j = new MyDebugLogger();
                }
            }
        }
        return j;
    }

    private void j() {
        q = SHApplication.getAppContext();
        e = "/SmartHome/logs/";
        l = LoggerFactory.getLogger();
        l.setClientID(s);
        o = new DebugSerializedAsyncTaskProcessor(true);
        r = q.getPackageName();
        PropertyConfigurator.getConfigurator(q).configure((int) R.raw.microlog);
    }

    public String b() {
        return e;
    }

    public static String a(File file) {
        if (!c(file)) {
            return file.getName().substring(i.length());
        }
        throw new IllegalArgumentException("illegal logFile");
    }

    public void a(String str) {
        if (o != null && SharePrefsManager.b(SHApplication.getAppContext().getSharedPreferences(f21933a, 0), b, false)) {
            LogData logData = new LogData();
            logData.f21944a = n.format(new Date());
            logData.b = str;
            t.add(logData);
            o.a(new DebugSerializedAsyncTaskProcessor.SerializedAsyncTask() {
                /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d3, code lost:
                    r1 = e;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d4, code lost:
                    r3 = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
                    r3.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:47:0x010c, code lost:
                    r1 = th;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:48:0x010d, code lost:
                    r3 = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
                    r3.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:52:0x0114, code lost:
                    r2 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:53:0x0115, code lost:
                    android.util.Log.e(com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.f(), (java.lang.String) null, r2);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
                    return;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Removed duplicated region for block: B:37:0x00de A[SYNTHETIC, Splitter:B:37:0x00de] */
                /* JADX WARNING: Removed duplicated region for block: B:47:0x010c A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000c] */
                /* JADX WARNING: Removed duplicated region for block: B:50:0x0110 A[SYNTHETIC, Splitter:B:50:0x0110] */
                /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void a() {
                    /*
                        r9 = this;
                        java.util.List r0 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.t
                        boolean r0 = r0.isEmpty()
                        if (r0 == 0) goto L_0x000b
                        return
                    L_0x000b:
                        r0 = 0
                        boolean r1 = com.xiaomi.smarthome.frame.file.FileUtils.b()     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        r2 = 0
                        if (r1 != 0) goto L_0x00ed
                        boolean r1 = com.xiaomi.smarthome.frame.file.FileUtils.c()     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        if (r1 == 0) goto L_0x001b
                        goto L_0x00ed
                    L_0x001b:
                        long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.io.File r5 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.lang.String r6 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.e     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r1.<init>(r5, r6)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        boolean r5 = r1.isDirectory()     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        if (r5 == 0) goto L_0x0047
                        com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$1$1 r5 = new com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$1$1     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r5.<init>(r3)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.io.File[] r1 = r1.listFiles(r5)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        if (r1 == 0) goto L_0x004a
                        int r5 = r1.length     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r6 = 0
                    L_0x003d:
                        if (r6 >= r5) goto L_0x004a
                        r7 = r1[r6]     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r7.delete()     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        int r6 = r6 + 1
                        goto L_0x003d
                    L_0x0047:
                        r1.mkdirs()     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                    L_0x004a:
                        java.text.SimpleDateFormat r1 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.p     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.util.Date r5 = new java.util.Date     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r5.<init>(r3)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.lang.String r1 = r1.format(r5)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r3.<init>()     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.lang.String r4 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.e     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r3.append(r4)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r3.append(r1)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        android.content.Context r1 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.q     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.lang.String r1 = com.xiaomi.smarthome.frame.process.ProcessUtil.g(r1)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        r3.append(r1)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.lang.String r1 = ".txt"
                        r3.append(r1)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        com.google.code.microlog4android.Logger r3 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.l     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        com.google.code.microlog4android.appender.Appender r3 = r3.getAppender(r2)     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        com.google.code.microlog4android.appender.FileAppender r3 = (com.google.code.microlog4android.appender.FileAppender) r3     // Catch:{ Exception -> 0x00d3, all -> 0x010c }
                        if (r3 == 0) goto L_0x008a
                        r3.close()     // Catch:{ Exception -> 0x0088 }
                        goto L_0x008a
                    L_0x0088:
                        r1 = move-exception
                        goto L_0x00d5
                    L_0x008a:
                        r3.setFileName(r1)     // Catch:{ Exception -> 0x0088 }
                        r1 = 1
                        r3.setAppend(r1)     // Catch:{ Exception -> 0x0088 }
                        r3.open()     // Catch:{ Exception -> 0x0088 }
                    L_0x0094:
                        java.util.List r4 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.t     // Catch:{ Exception -> 0x0088 }
                        boolean r4 = r4.isEmpty()     // Catch:{ Exception -> 0x0088 }
                        if (r4 != 0) goto L_0x00ca
                        java.util.List r4 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.t     // Catch:{ Exception -> 0x0088 }
                        java.lang.Object r4 = r4.remove(r2)     // Catch:{ Exception -> 0x0088 }
                        com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$LogData r4 = (com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.LogData) r4     // Catch:{ Exception -> 0x0088 }
                        com.google.code.microlog4android.Logger r5 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.l     // Catch:{ Exception -> 0x0088 }
                        java.lang.String r6 = "%1$s %2$s"
                        r7 = 2
                        java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0088 }
                        java.lang.String r8 = r4.f21944a     // Catch:{ Exception -> 0x0088 }
                        r7[r2] = r8     // Catch:{ Exception -> 0x0088 }
                        java.lang.String r8 = r4.b     // Catch:{ Exception -> 0x0088 }
                        r7[r1] = r8     // Catch:{ Exception -> 0x0088 }
                        java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch:{ Exception -> 0x0088 }
                        r5.debug(r6)     // Catch:{ Exception -> 0x0088 }
                        java.lang.String r5 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.s     // Catch:{ Exception -> 0x0088 }
                        java.lang.String r4 = r4.b     // Catch:{ Exception -> 0x0088 }
                        android.util.Log.d(r5, r4)     // Catch:{ Exception -> 0x0088 }
                        goto L_0x0094
                    L_0x00ca:
                        r3.close()     // Catch:{ Exception -> 0x0088 }
                        if (r3 == 0) goto L_0x00ea
                        r3.close()     // Catch:{ IOException -> 0x00e2 }
                        goto L_0x00ea
                    L_0x00d3:
                        r1 = move-exception
                        r3 = r0
                    L_0x00d5:
                        java.lang.String r2 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.s     // Catch:{ all -> 0x00eb }
                        android.util.Log.e(r2, r0, r1)     // Catch:{ all -> 0x00eb }
                        if (r3 == 0) goto L_0x00ea
                        r3.close()     // Catch:{ IOException -> 0x00e2 }
                        goto L_0x00ea
                    L_0x00e2:
                        r1 = move-exception
                        java.lang.String r2 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.s
                        android.util.Log.e(r2, r0, r1)
                    L_0x00ea:
                        return
                    L_0x00eb:
                        r1 = move-exception
                        goto L_0x010e
                    L_0x00ed:
                        java.util.List r1 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.t     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        boolean r1 = r1.isEmpty()     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        if (r1 != 0) goto L_0x010b
                        java.util.List r1 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.t     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        java.lang.Object r1 = r1.remove(r2)     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$LogData r1 = (com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.LogData) r1     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        java.lang.String r3 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.s     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        java.lang.String r1 = r1.b     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        android.util.Log.v(r3, r1)     // Catch:{ Exception -> 0x011d, all -> 0x010c }
                        goto L_0x00ed
                    L_0x010b:
                        return
                    L_0x010c:
                        r1 = move-exception
                        r3 = r0
                    L_0x010e:
                        if (r3 == 0) goto L_0x011c
                        r3.close()     // Catch:{ IOException -> 0x0114 }
                        goto L_0x011c
                    L_0x0114:
                        r2 = move-exception
                        java.lang.String r3 = com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.s
                        android.util.Log.e(r3, r0, r2)
                    L_0x011c:
                        throw r1
                    L_0x011d:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.AnonymousClass1.a():void");
                }
            }, 0);
        }
    }

    public void c() {
        o.b();
    }

    public static boolean b(File file) {
        if (!file.isFile() || !file.getName().endsWith(Constants.n)) {
            return true;
        }
        String name = file.getName();
        try {
            p.parse(name.substring(0, name.length() - 4)).getTime();
            return false;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static boolean c(File file) {
        if (!file.isDirectory() || !file.getName().startsWith(i)) {
            return true;
        }
        for (File b2 : file.listFiles()) {
            if (b(b2)) {
                return true;
            }
        }
        return false;
    }

    private void d(File file) {
        File[] listFiles = file.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return MyDebugLogger.b(file);
            }
        });
        if (listFiles != null) {
            for (File path : listFiles) {
                FileUtils.j(path.getPath());
            }
        }
    }

    private void e(File file) {
        FileUtils.a(file.listFiles(), 10485760, (Comparator<File>) new Comparator<File>() {
            /* renamed from: a */
            public int compare(File file, File file2) {
                String name = file.getName();
                String name2 = file2.getName();
                try {
                    return (int) (MyDebugLogger.p.parse(name.substring(0, name.length() - 4)).getTime() - MyDebugLogger.p.parse(name2.substring(0, name2.length() - 4)).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }

    private void f(File file) {
        if (file.isDirectory()) {
            final long currentTimeMillis = System.currentTimeMillis();
            File[] listFiles = file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    try {
                        long time = MyDebugLogger.p.parse(str.substring(0, str.length() - 4)).getTime();
                        if (currentTimeMillis - time > 259200000 || currentTimeMillis - time < 0) {
                            return true;
                        }
                        return false;
                    } catch (ParseException unused) {
                        return false;
                    }
                }
            });
            if (listFiles != null) {
                for (File path : listFiles) {
                    FileUtils.j(path.getPath());
                }
                return;
            }
            return;
        }
        file.mkdirs();
    }

    public void a(final String str, final String str2, final AsyncCallback<Void, Error> asyncCallback) {
        if (!FileUtils.b()) {
            AsyncTaskUtils.a(new AsyncTask<Void, Void, String>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public String doInBackground(Void... voidArr) {
                    String b2 = MyDebugLogger.this.b(str2);
                    if (TextUtils.isEmpty(b2)) {
                        return "emptyFilePath";
                    }
                    if (MyDebugLogger.this.a(b2, str)) {
                        return b2;
                    }
                    return null;
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(String str) {
                    if (!TextUtils.isEmpty(str)) {
                        if (asyncCallback != null) {
                            asyncCallback.sendSuccessMessage(null);
                        }
                    } else if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
                    }
                }
            }, new Void[0]);
        } else if (asyncCallback != null) {
            asyncCallback.sendFailureMessage(new Error(-999, "SDCardBusy"));
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x00d4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b(final java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = f
            java.lang.String r1 = r10.b()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            r2.append(r3)
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.io.File r2 = new java.io.File
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            r2.<init>(r3, r1)
            com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$6 r3 = new com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$6
            r3.<init>()
            java.io.File[] r3 = r2.listFiles(r3)
            r4 = 0
            if (r3 == 0) goto L_0x0041
            int r5 = r3.length
            r6 = 0
        L_0x0033:
            if (r6 >= r5) goto L_0x0041
            r7 = r3[r6]
            java.lang.String r7 = r7.getPath()
            com.xiaomi.smarthome.frame.file.FileUtils.j(r7)
            int r6 = r6 + 1
            goto L_0x0033
        L_0x0041:
            com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$7 r3 = new com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$7
            r3.<init>(r11)
            java.io.File[] r3 = r2.listFiles(r3)
            r5 = 0
            if (r3 != 0) goto L_0x004e
            return r5
        L_0x004e:
            r6 = 104857600(0x6400000, double:5.1806538E-316)
            com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$8 r8 = new com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$8
            r8.<init>()
            com.xiaomi.smarthome.frame.file.FileUtils.a((java.io.File[]) r3, (long) r6, (java.util.Comparator<java.io.File>) r8)
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r6.<init>()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.io.File r7 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r6.append(r7)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r6.append(r1)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.String r1 = "plugin.txt"
            r6.append(r1)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.String r1 = r6.toString()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r3.<init>(r1)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r1.<init>(r3)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            com.xiaomi.smarthome.frame.core.CoreApi r3 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.util.List r3 = r3.O()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
        L_0x0087:
            boolean r6 = r3.hasNext()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            if (r6 == 0) goto L_0x00d1
            java.lang.Object r6 = r3.next()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r6 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r6     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.String r7 = r6.o()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            if (r7 == 0) goto L_0x0087
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r7 = r6.h()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            if (r7 == 0) goto L_0x0087
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r7.<init>()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.String r8 = "Model - "
            r7.append(r8)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.String r8 = r6.o()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r7.append(r8)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.String r8 = ", PackageId - "
            r7.append(r8)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r6 = r6.h()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            long r8 = r6.b()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r7.append(r8)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.String r6 = "\n"
            r7.append(r6)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            java.lang.String r6 = r7.toString()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            byte[] r6 = r6.getBytes()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            r1.write(r6)     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
            goto L_0x0087
        L_0x00d1:
            r1.close()     // Catch:{ FileNotFoundException | IOException -> 0x00d4 }
        L_0x00d4:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            r1.<init>(r0, r4)     // Catch:{ IOException -> 0x00fc, all -> 0x00f8 }
            java.util.zip.ZipOutputStream r3 = new java.util.zip.ZipOutputStream     // Catch:{ IOException -> 0x00f5, all -> 0x00f2 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x00f5, all -> 0x00f2 }
            com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$9 r4 = new com.xiaomi.smarthome.scenenew.debug.MyDebugLogger$9     // Catch:{ IOException -> 0x00f0 }
            r4.<init>(r11)     // Catch:{ IOException -> 0x00f0 }
            com.xiaomi.smarthome.library.common.util.IOUtils.b(r3, r2, r5, r4)     // Catch:{ IOException -> 0x00f0 }
            r3.flush()     // Catch:{ IOException -> 0x00f0 }
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r3)
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r1)
            return r0
        L_0x00f0:
            r11 = move-exception
            goto L_0x00ff
        L_0x00f2:
            r11 = move-exception
            r3 = r5
            goto L_0x0110
        L_0x00f5:
            r11 = move-exception
            r3 = r5
            goto L_0x00ff
        L_0x00f8:
            r11 = move-exception
            r1 = r5
            r3 = r1
            goto L_0x0110
        L_0x00fc:
            r11 = move-exception
            r1 = r5
            r3 = r1
        L_0x00ff:
            java.lang.String r0 = s     // Catch:{ all -> 0x010f }
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x010f }
            android.util.Log.e(r0, r11)     // Catch:{ all -> 0x010f }
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r3)
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r1)
            return r5
        L_0x010f:
            r11 = move-exception
        L_0x0110:
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r3)
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r1)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.debug.MyDebugLogger.b(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, String str2) {
        try {
            return d().newCall(new Request.Builder().url(str2).put(RequestBody.create(MediaType.parse(""), new File(str))).build()).execute().isSuccessful();
        } catch (IOException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public OkHttpClient d() {
        if (this.h == null) {
            synchronized (k) {
                if (this.h == null) {
                    this.h = ClientUtil.a();
                }
            }
        }
        return this.h;
    }

    private static class LogData {

        /* renamed from: a  reason: collision with root package name */
        public String f21944a;
        public String b;

        private LogData() {
        }
    }
}
