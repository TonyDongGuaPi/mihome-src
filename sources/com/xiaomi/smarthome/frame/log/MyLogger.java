package com.xiaomi.smarthome.frame.log;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.dianping.logan.OnLoganProtocolStatus;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.config.PropertyConfigurator;
import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.file.FileUtils;
import com.xiaomi.smarthome.frame.log.SerializedAsyncTaskProcessor;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.sdk.R;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
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
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class MyLogger {

    /* renamed from: a  reason: collision with root package name */
    public static String f16122a = "log.zip";
    public static final long b = 104857600;
    private static final String d = "app";
    /* access modifiers changed from: private */
    public static String e = null;
    private static String f = null;
    private static final String g = "plug_";
    private static MyLogger h = null;
    private static Object i = new Object();
    /* access modifiers changed from: private */
    public static Logger j = null;
    private static final int k = 10485760;
    private static SimpleDateFormat l = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
    private static SerializedAsyncTaskProcessor m;
    /* access modifiers changed from: private */
    public static SimpleDateFormat n = new SimpleDateFormat("yyyy-MM-dd");
    private static Context o;
    private static String p;
    /* access modifiers changed from: private */
    public static String q = "MyLogger";
    /* access modifiers changed from: private */
    public static List<LogData> r = Collections.synchronizedList(new ArrayList());
    OkHttpClient c;
    private volatile boolean s = false;

    private MyLogger() {
        o = CommonApplication.getAppContext();
        e = "/Android/data/" + o.getPackageName() + "/files/logs/";
        File externalFilesDir = o.getExternalFilesDir(Tags.RepairOrder.SERVICE_LOG);
        if (externalFilesDir != null) {
            f = externalFilesDir.getAbsolutePath() + "/";
            FileUtils.k(f);
        }
        m = new SerializedAsyncTaskProcessor(true);
        p = o.getPackageName();
    }

    public static MyLogger a() {
        if (h == null) {
            synchronized (i) {
                if (h == null) {
                    h = new MyLogger();
                }
            }
        }
        return h;
    }

    public void b() {
        j = LoggerFactory.getLogger();
        j.setClientID(q);
        PropertyConfigurator.getConfigurator(o).configure(R.raw.microlog);
        File file = new File(d() + "app");
        File externalCacheDir = o.getExternalCacheDir();
        if (externalCacheDir == null) {
            externalCacheDir = new File(d() + "cache");
        }
        file.mkdirs();
        externalCacheDir.mkdirs();
        Logan.a(new LoganConfig.Builder().a(externalCacheDir.getAbsolutePath()).b(file.getAbsolutePath()).a(100).c(10).a());
        Logan.a(GlobalSetting.j);
        Logan.a((OnLoganProtocolStatus) new OnLoganProtocolStatus() {
            public void a(String str, int i) {
                String g = MyLogger.q;
                Log.d(g, "clogan > cmd : " + str + " | code : " + i);
            }
        });
        this.s = true;
    }

    public boolean c() {
        return this.s;
    }

    public String d() {
        return f;
    }

    private String l() {
        return e;
    }

    public void a(int i2, String str, String str2) {
        if (c()) {
            if (TextUtils.isEmpty(str)) {
                a(i2, str2);
            } else {
                a(str, str2);
            }
        }
    }

    public void a(int i2, String str) {
        if (c()) {
            Logan.a(str, i2);
        }
    }

    public void a(String str) {
        a(0, str);
    }

    public void a(final String str, String str2) {
        if (c() && !TextUtils.isEmpty(str2) && str2.getBytes().length <= 10485760 && str2.getBytes().length > 0 && m != null) {
            LogData logData = new LogData();
            logData.f16131a = l.format(new Date());
            logData.b = str2;
            r.add(logData);
            m.a(new SerializedAsyncTaskProcessor.SerializedAsyncTask() {
                /* JADX WARNING: Code restructure failed: missing block: B:43:0x0122, code lost:
                    r1 = th;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:44:0x0123, code lost:
                    r3 = null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
                    r3.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:48:0x012a, code lost:
                    r2 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:49:0x012b, code lost:
                    android.util.Log.e(com.xiaomi.smarthome.frame.log.MyLogger.g(), (java.lang.String) null, r2);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:51:0x0133, code lost:
                    return;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Removed duplicated region for block: B:31:0x00ee A[SYNTHETIC, Splitter:B:31:0x00ee] */
                /* JADX WARNING: Removed duplicated region for block: B:43:0x0122 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000c] */
                /* JADX WARNING: Removed duplicated region for block: B:46:0x0126 A[SYNTHETIC, Splitter:B:46:0x0126] */
                /* JADX WARNING: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void a() {
                    /*
                        r9 = this;
                        java.util.List r0 = com.xiaomi.smarthome.frame.log.MyLogger.r
                        boolean r0 = r0.isEmpty()
                        if (r0 == 0) goto L_0x000b
                        return
                    L_0x000b:
                        r0 = 0
                        boolean r1 = com.xiaomi.smarthome.frame.file.FileUtils.b()     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        r2 = 0
                        if (r1 != 0) goto L_0x00fd
                        boolean r1 = com.xiaomi.smarthome.frame.file.FileUtils.c()     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        if (r1 == 0) goto L_0x001b
                        goto L_0x00fd
                    L_0x001b:
                        long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r1.<init>()     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r5 = com.xiaomi.smarthome.frame.log.MyLogger.e     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r1.append(r5)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r5 = "plug_"
                        r1.append(r5)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r5 = r4     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r1.append(r5)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r5 = "/"
                        r1.append(r5)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.text.SimpleDateFormat r5 = com.xiaomi.smarthome.frame.log.MyLogger.n     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.util.Date r6 = new java.util.Date     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r6.<init>(r3)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r3 = r5.format(r6)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r4.<init>()     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r4.append(r1)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r4.append(r3)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r1 = ".txt"
                        r4.append(r1)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r1 = r4.toString()     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.io.File r4 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r3.<init>(r4, r1)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.io.File r4 = r3.getParentFile()     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        com.xiaomi.smarthome.frame.log.MyLogger r5 = com.xiaomi.smarthome.frame.log.MyLogger.this     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r5.c(r4)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        com.xiaomi.smarthome.frame.log.MyLogger r5 = com.xiaomi.smarthome.frame.log.MyLogger.this     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r5.e(r4)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        com.xiaomi.smarthome.frame.log.MyLogger r5 = com.xiaomi.smarthome.frame.log.MyLogger.this     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        r5.d(r4)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        com.xiaomi.smarthome.frame.file.FileUtils.i(r3)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        com.google.code.microlog4android.Logger r3 = com.xiaomi.smarthome.frame.log.MyLogger.j     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        com.google.code.microlog4android.appender.Appender r3 = r3.getAppender(r2)     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        com.google.code.microlog4android.appender.FileAppender r3 = (com.google.code.microlog4android.appender.FileAppender) r3     // Catch:{ Exception -> 0x00e3, all -> 0x0122 }
                        if (r3 == 0) goto L_0x0094
                        r3.close()     // Catch:{ Exception -> 0x0092 }
                        goto L_0x0094
                    L_0x0092:
                        r1 = move-exception
                        goto L_0x00e5
                    L_0x0094:
                        r3.setFileName(r1)     // Catch:{ Exception -> 0x0092 }
                        r1 = 1
                        r3.setAppend(r1)     // Catch:{ Exception -> 0x0092 }
                        r3.open()     // Catch:{ Exception -> 0x0092 }
                    L_0x009e:
                        java.util.List r4 = com.xiaomi.smarthome.frame.log.MyLogger.r     // Catch:{ Exception -> 0x0092 }
                        boolean r4 = r4.isEmpty()     // Catch:{ Exception -> 0x0092 }
                        if (r4 != 0) goto L_0x00da
                        java.util.List r4 = com.xiaomi.smarthome.frame.log.MyLogger.r     // Catch:{ Exception -> 0x0092 }
                        java.lang.Object r4 = r4.remove(r2)     // Catch:{ Exception -> 0x0092 }
                        com.xiaomi.smarthome.frame.log.MyLogger$LogData r4 = (com.xiaomi.smarthome.frame.log.MyLogger.LogData) r4     // Catch:{ Exception -> 0x0092 }
                        com.google.code.microlog4android.Logger r5 = com.xiaomi.smarthome.frame.log.MyLogger.j     // Catch:{ Exception -> 0x0092 }
                        java.lang.String r6 = "%1$s %2$s"
                        r7 = 2
                        java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0092 }
                        java.lang.String r8 = r4.f16131a     // Catch:{ Exception -> 0x0092 }
                        r7[r2] = r8     // Catch:{ Exception -> 0x0092 }
                        java.lang.String r8 = r4.b     // Catch:{ Exception -> 0x0092 }
                        r7[r1] = r8     // Catch:{ Exception -> 0x0092 }
                        java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch:{ Exception -> 0x0092 }
                        r5.debug(r6)     // Catch:{ Exception -> 0x0092 }
                        boolean r5 = com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager.o()     // Catch:{ Exception -> 0x0092 }
                        if (r5 != 0) goto L_0x009e
                        java.lang.String r5 = com.xiaomi.smarthome.frame.log.MyLogger.q     // Catch:{ Exception -> 0x0092 }
                        java.lang.String r4 = r4.b     // Catch:{ Exception -> 0x0092 }
                        android.util.Log.d(r5, r4)     // Catch:{ Exception -> 0x0092 }
                        goto L_0x009e
                    L_0x00da:
                        r3.close()     // Catch:{ Exception -> 0x0092 }
                        if (r3 == 0) goto L_0x00fa
                        r3.close()     // Catch:{ IOException -> 0x00f2 }
                        goto L_0x00fa
                    L_0x00e3:
                        r1 = move-exception
                        r3 = r0
                    L_0x00e5:
                        java.lang.String r2 = com.xiaomi.smarthome.frame.log.MyLogger.q     // Catch:{ all -> 0x00fb }
                        android.util.Log.e(r2, r0, r1)     // Catch:{ all -> 0x00fb }
                        if (r3 == 0) goto L_0x00fa
                        r3.close()     // Catch:{ IOException -> 0x00f2 }
                        goto L_0x00fa
                    L_0x00f2:
                        r1 = move-exception
                        java.lang.String r2 = com.xiaomi.smarthome.frame.log.MyLogger.q
                        android.util.Log.e(r2, r0, r1)
                    L_0x00fa:
                        return
                    L_0x00fb:
                        r1 = move-exception
                        goto L_0x0124
                    L_0x00fd:
                        java.util.List r1 = com.xiaomi.smarthome.frame.log.MyLogger.r     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        boolean r1 = r1.isEmpty()     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        if (r1 != 0) goto L_0x0121
                        java.util.List r1 = com.xiaomi.smarthome.frame.log.MyLogger.r     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        java.lang.Object r1 = r1.remove(r2)     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        com.xiaomi.smarthome.frame.log.MyLogger$LogData r1 = (com.xiaomi.smarthome.frame.log.MyLogger.LogData) r1     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        boolean r3 = com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager.o()     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        if (r3 != 0) goto L_0x00fd
                        java.lang.String r3 = com.xiaomi.smarthome.frame.log.MyLogger.q     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        java.lang.String r1 = r1.b     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        android.util.Log.v(r3, r1)     // Catch:{ Exception -> 0x0133, all -> 0x0122 }
                        goto L_0x00fd
                    L_0x0121:
                        return
                    L_0x0122:
                        r1 = move-exception
                        r3 = r0
                    L_0x0124:
                        if (r3 == 0) goto L_0x0132
                        r3.close()     // Catch:{ IOException -> 0x012a }
                        goto L_0x0132
                    L_0x012a:
                        r2 = move-exception
                        java.lang.String r3 = com.xiaomi.smarthome.frame.log.MyLogger.q
                        android.util.Log.e(r3, r0, r2)
                    L_0x0132:
                        throw r1
                    L_0x0133:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.log.MyLogger.AnonymousClass2.a():void");
                }
            }, 0);
        }
    }

    public void e() {
        Logan.a();
        m.b();
    }

    public static boolean a(File file) {
        if (!file.isFile() || !file.getName().endsWith(Constants.n)) {
            return true;
        }
        String name = file.getName();
        try {
            n.parse(name.substring(0, name.length() - 4)).getTime();
            return false;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static boolean b(File file) {
        if (!file.isDirectory() || !file.getName().startsWith(g)) {
            return true;
        }
        for (File a2 : file.listFiles()) {
            if (a(a2)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void c(File file) {
        File[] listFiles = file.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return MyLogger.a(file);
            }
        });
        if (listFiles != null) {
            for (File path : listFiles) {
                FileUtils.j(path.getPath());
            }
        }
    }

    /* access modifiers changed from: private */
    public void d(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            FileUtils.a(listFiles, 10485760, (Comparator<File>) new Comparator<File>() {
                /* renamed from: a */
                public int compare(File file, File file2) {
                    String name = file.getName();
                    String name2 = file2.getName();
                    try {
                        return (int) (MyLogger.n.parse(name.substring(0, name.length() - 4)).getTime() - MyLogger.n.parse(name2.substring(0, name2.length() - 4)).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void e(File file) {
        if (file.isDirectory()) {
            final long currentTimeMillis = System.currentTimeMillis();
            File[] listFiles = file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    try {
                        long time = MyLogger.n.parse(str.substring(0, str.length() - 4)).getTime();
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

    public void a(String str, String str2, String[] strArr, boolean z, AsyncCallback<Void, Error> asyncCallback) {
        if (c()) {
            if (!FileUtils.b()) {
                final String str3 = str2;
                final String[] strArr2 = strArr;
                final boolean z2 = z;
                final String str4 = str;
                final AsyncCallback<Void, Error> asyncCallback2 = asyncCallback;
                AsyncTaskUtils.a(new AsyncTask<Void, Void, String>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public String doInBackground(Void... voidArr) {
                        String a2 = MyLogger.this.a(str3, strArr2, z2);
                        if (TextUtils.isEmpty(a2)) {
                            return "emptyFilePath";
                        }
                        if (MyLogger.this.b(a2, str4)) {
                            return a2;
                        }
                        return null;
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(String str) {
                        if (!TextUtils.isEmpty(str)) {
                            if (asyncCallback2 != null) {
                                asyncCallback2.sendSuccessMessage(null);
                            }
                        } else if (asyncCallback2 != null) {
                            asyncCallback2.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
                        }
                    }
                }, new Void[0]);
            } else if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-999, "SDCardBusy"));
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00c3 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r17, java.lang.String[] r18, boolean r19) {
        /*
            r16 = this;
            r1 = r16
            r2 = r18
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            r0.append(r3)
            java.lang.String r3 = r16.l()
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r4 = f16122a
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5 = 0
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x004e }
            r0.<init>(r3)     // Catch:{ Exception -> 0x004e }
            java.io.File[] r0 = r0.listFiles()     // Catch:{ Exception -> 0x004e }
            int r6 = r0.length     // Catch:{ Exception -> 0x004e }
            r7 = 0
        L_0x0038:
            if (r7 >= r6) goto L_0x0052
            r8 = r0[r7]     // Catch:{ Exception -> 0x004e }
            boolean r9 = h(r8)     // Catch:{ Exception -> 0x004e }
            if (r9 != 0) goto L_0x0048
            boolean r9 = g(r8)     // Catch:{ Exception -> 0x004e }
            if (r9 == 0) goto L_0x004b
        L_0x0048:
            r8.delete()     // Catch:{ Exception -> 0x004e }
        L_0x004b:
            int r7 = r7 + 1
            goto L_0x0038
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0052:
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            r7 = 0
            if (r2 == 0) goto L_0x00d1
            int r0 = r2.length
            if (r0 <= 0) goto L_0x00d1
            int r8 = r2.length
            r9 = 0
        L_0x005f:
            if (r9 >= r8) goto L_0x00d1
            r0 = r2[r9]
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x00b5, all -> 0x00b2 }
            java.io.File r11 = new java.io.File     // Catch:{ Exception -> 0x00b5, all -> 0x00b2 }
            r11.<init>(r0)     // Catch:{ Exception -> 0x00b5, all -> 0x00b2 }
            java.lang.String r11 = r11.getName()     // Catch:{ Exception -> 0x00b5, all -> 0x00b2 }
            r10.<init>(r3, r11)     // Catch:{ Exception -> 0x00b5, all -> 0x00b2 }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00b5, all -> 0x00b2 }
            r11.<init>(r10)     // Catch:{ Exception -> 0x00b5, all -> 0x00b2 }
            android.graphics.BitmapFactory$Options r12 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r12.<init>()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r13 = 1
            r12.inJustDecodeBounds = r13     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            android.graphics.BitmapFactory.decodeFile(r0, r12)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            int r13 = r12.outWidth     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            int r12 = r12.outHeight     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r14 = 1080(0x438, float:1.513E-42)
            r15 = 2700000(0x2932e0, float:3.783506E-39)
            int r12 = com.xiaomi.smarthome.library.common.util.BitmapUtils.a((int) r13, (int) r12, (int) r14, (int) r15)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            android.graphics.BitmapFactory$Options r13 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r13.<init>()     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r13.inJustDecodeBounds = r5     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            r13.inSampleSize = r12     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            android.graphics.Bitmap r12 = android.graphics.BitmapFactory.decodeFile(r0, r13)     // Catch:{ Exception -> 0x00af, all -> 0x00ad }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x00ab }
            r13 = 50
            r12.compress(r0, r13, r11)     // Catch:{ Exception -> 0x00ab }
            r6.add(r10)     // Catch:{ Exception -> 0x00ab }
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r11)
            if (r12 == 0) goto L_0x00c3
            goto L_0x00c0
        L_0x00ab:
            r0 = move-exception
            goto L_0x00b8
        L_0x00ad:
            r0 = move-exception
            goto L_0x00c8
        L_0x00af:
            r0 = move-exception
            r12 = r7
            goto L_0x00b8
        L_0x00b2:
            r0 = move-exception
            r11 = r7
            goto L_0x00c8
        L_0x00b5:
            r0 = move-exception
            r11 = r7
            r12 = r11
        L_0x00b8:
            r0.printStackTrace()     // Catch:{ all -> 0x00c6 }
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r11)
            if (r12 == 0) goto L_0x00c3
        L_0x00c0:
            r12.recycle()
        L_0x00c3:
            int r9 = r9 + 1
            goto L_0x005f
        L_0x00c6:
            r0 = move-exception
            r7 = r12
        L_0x00c8:
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r11)
            if (r7 == 0) goto L_0x00d0
            r7.recycle()
        L_0x00d0:
            throw r0
        L_0x00d1:
            if (r19 == 0) goto L_0x017d
            com.xiaomi.smarthome.frame.log.MyLogger$7 r0 = new com.xiaomi.smarthome.frame.log.MyLogger$7     // Catch:{ Exception -> 0x00fd }
            r0.<init>()     // Catch:{ Exception -> 0x00fd }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00fd }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00fd }
            java.io.File[] r2 = r2.listFiles(r0)     // Catch:{ Exception -> 0x00fd }
            r8 = 104857600(0x6400000, double:5.1806538E-316)
            com.xiaomi.smarthome.frame.log.MyLogger$8 r5 = new com.xiaomi.smarthome.frame.log.MyLogger$8     // Catch:{ Exception -> 0x00fd }
            r5.<init>()     // Catch:{ Exception -> 0x00fd }
            com.xiaomi.smarthome.frame.file.FileUtils.a((java.io.File[]) r2, (long) r8, (java.util.Comparator<java.io.File>) r5)     // Catch:{ Exception -> 0x00fd }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00fd }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00fd }
            java.io.File[] r0 = r2.listFiles(r0)     // Catch:{ Exception -> 0x00fd }
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch:{ Exception -> 0x00fd }
            r6.addAll(r0)     // Catch:{ Exception -> 0x00fd }
            goto L_0x0101
        L_0x00fd:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0101:
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x016f, all -> 0x016c }
            java.lang.String r2 = "plugin.txt"
            r0.<init>(r3, r2)     // Catch:{ Exception -> 0x016f, all -> 0x016c }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x016f, all -> 0x016c }
            r2.<init>(r0)     // Catch:{ Exception -> 0x016f, all -> 0x016c }
            com.xiaomi.smarthome.frame.core.CoreApi r3 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ Exception -> 0x016a }
            java.util.List r3 = r3.O()     // Catch:{ Exception -> 0x016a }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x016a }
        L_0x0119:
            boolean r5 = r3.hasNext()     // Catch:{ Exception -> 0x016a }
            if (r5 == 0) goto L_0x0163
            java.lang.Object r5 = r3.next()     // Catch:{ Exception -> 0x016a }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r5 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r5     // Catch:{ Exception -> 0x016a }
            java.lang.String r8 = r5.o()     // Catch:{ Exception -> 0x016a }
            if (r8 == 0) goto L_0x0119
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r8 = r5.h()     // Catch:{ Exception -> 0x016a }
            if (r8 == 0) goto L_0x0119
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x016a }
            r8.<init>()     // Catch:{ Exception -> 0x016a }
            java.lang.String r9 = "Model - "
            r8.append(r9)     // Catch:{ Exception -> 0x016a }
            java.lang.String r9 = r5.o()     // Catch:{ Exception -> 0x016a }
            r8.append(r9)     // Catch:{ Exception -> 0x016a }
            java.lang.String r9 = ", PackageId - "
            r8.append(r9)     // Catch:{ Exception -> 0x016a }
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r5 = r5.h()     // Catch:{ Exception -> 0x016a }
            long r9 = r5.b()     // Catch:{ Exception -> 0x016a }
            r8.append(r9)     // Catch:{ Exception -> 0x016a }
            java.lang.String r5 = "\n"
            r8.append(r5)     // Catch:{ Exception -> 0x016a }
            java.lang.String r5 = r8.toString()     // Catch:{ Exception -> 0x016a }
            byte[] r5 = r5.getBytes()     // Catch:{ Exception -> 0x016a }
            r2.write(r5)     // Catch:{ Exception -> 0x016a }
            goto L_0x0119
        L_0x0163:
            r2.close()     // Catch:{ Exception -> 0x016a }
            r6.add(r0)     // Catch:{ Exception -> 0x016a }
            goto L_0x0174
        L_0x016a:
            r0 = move-exception
            goto L_0x0171
        L_0x016c:
            r0 = move-exception
            r2 = r7
            goto L_0x0179
        L_0x016f:
            r0 = move-exception
            r2 = r7
        L_0x0171:
            r0.printStackTrace()     // Catch:{ all -> 0x0178 }
        L_0x0174:
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r2)
            goto L_0x017d
        L_0x0178:
            r0 = move-exception
        L_0x0179:
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.OutputStream) r2)
            throw r0
        L_0x017d:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r6)
            boolean r0 = com.xiaomi.smarthome.library.common.util.IOUtils.a((java.util.List<java.io.File>) r0, (java.lang.String) r4)
            if (r0 == 0) goto L_0x0189
            goto L_0x018a
        L_0x0189:
            r4 = r7
        L_0x018a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.log.MyLogger.a(java.lang.String, java.lang.String[], boolean):java.lang.String");
    }

    /* access modifiers changed from: private */
    public boolean f(File file) {
        if (file == null || file.isFile()) {
            return false;
        }
        if (file.getName().startsWith(g) || file.getName().startsWith("app")) {
            return true;
        }
        return false;
    }

    private static boolean g(File file) {
        return a(file, ArchiveStreamFactory.g);
    }

    private static boolean h(File file) {
        return a(file, "image");
    }

    private static boolean a(File file, String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(file.getPath());
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        return file.isFile() && !TextUtils.isEmpty(fileExtensionFromUrl) && mimeTypeFromExtension != null && mimeTypeFromExtension.contains(str);
    }

    /* access modifiers changed from: package-private */
    public boolean b(String str, String str2) {
        try {
            if (f().newCall(new Request.Builder().url(str2).put(RequestBody.create(MediaType.parse(""), new File(str))).build()).execute().isSuccessful()) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public OkHttpClient f() {
        if (this.c == null) {
            synchronized (i) {
                if (this.c == null) {
                    this.c = ClientUtil.a();
                }
            }
        }
        return this.c;
    }

    private static class LogData {

        /* renamed from: a  reason: collision with root package name */
        public String f16131a;
        public String b;

        private LogData() {
        }
    }
}
