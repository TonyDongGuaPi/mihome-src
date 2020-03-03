package com.xiaomi.market.sdk;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.market.sdk.Constants;
import com.xiaomi.market.sdk.DownloadInstallManager;
import com.xiaomi.market.sdk.XiaomiUpdateAgent;
import com.xiaomi.smarthome.library.common.util.DownloadManagerPro;
import java.io.File;
import java.lang.reflect.Method;

public class DownloadInstallManager {

    /* renamed from: a  reason: collision with root package name */
    public static DownloadInstallManager f11103a = null;
    private static final String b = "MarketUpdateDownload";
    private static final String c = "file_path";
    /* access modifiers changed from: private */
    public static DownloadManager g;
    /* access modifiers changed from: private */
    public XiaomiUpdateAgent.UpdateInfo d;
    /* access modifiers changed from: private */
    public LocalAppInfo e;
    /* access modifiers changed from: private */
    public long f = -1;
    private HandlerThread h;
    private WorkerHandler i;

    private DownloadInstallManager(Context context) {
        Client.a(context.getApplicationContext());
        b(context.getApplicationContext());
        this.h = new HandlerThread("Worker Thread");
        this.h.start();
        this.i = new WorkerHandler(this.h.getLooper());
    }

    private void b(Context context) {
        g = (DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        if (Client.q()) {
            try {
                Method declaredMethod = g.getClass().getDeclaredMethod("setAccessFilename", new Class[]{Boolean.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(g, new Object[]{true});
            } catch (Exception e2) {
                Log.b(b, e2.getMessage(), (Throwable) e2);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.xiaomi.market.sdk.LocalAppInfo r1, com.xiaomi.market.sdk.XiaomiUpdateAgent.UpdateInfo r2) {
        /*
            r0 = this;
            monitor-enter(r0)
            if (r2 == 0) goto L_0x0014
            if (r1 != 0) goto L_0x0006
            goto L_0x0014
        L_0x0006:
            r0.d = r2     // Catch:{ all -> 0x0011 }
            r0.e = r1     // Catch:{ all -> 0x0011 }
            com.xiaomi.market.sdk.DownloadInstallManager$WorkerHandler r1 = r0.i     // Catch:{ all -> 0x0011 }
            r1.b()     // Catch:{ all -> 0x0011 }
            monitor-exit(r0)
            return
        L_0x0011:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x0014:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.market.sdk.DownloadInstallManager.a(com.xiaomi.market.sdk.LocalAppInfo, com.xiaomi.market.sdk.XiaomiUpdateAgent$UpdateInfo):void");
    }

    public long a() {
        c(XiaomiUpdateAgent.f());
        return this.f;
    }

    public void a(long j) {
        DownloadManagerInfo a2;
        if (j >= 0 && this.f == j && (a2 = DownloadManagerInfo.a(this.f)) != null && a2.b != 16 && !TextUtils.isEmpty(a2.f)) {
            this.i.a(a2.f, !TextUtils.isEmpty(this.d.j));
        }
    }

    public static synchronized DownloadInstallManager a(Context context) {
        DownloadInstallManager downloadInstallManager;
        synchronized (DownloadInstallManager.class) {
            if (f11103a == null) {
                f11103a = new DownloadInstallManager(context);
            }
            downloadInstallManager = f11103a;
        }
        return downloadInstallManager;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.xiaomi.market.sdk.LocalAppInfo r11) {
        /*
            r10 = this;
            android.content.Context r0 = com.xiaomi.market.sdk.XiaomiUpdateAgent.f()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.xiaomi.market.sdk.SDKDatabaseHelper r2 = com.xiaomi.market.sdk.SDKDatabaseHelper.a((android.content.Context) r0)
            java.lang.String r3 = "update_download"
            java.lang.String[] r4 = com.xiaomi.market.sdk.Constants.Update.l
            java.lang.String r5 = "package_name=?"
            r0 = 1
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.String r11 = r11.f11109a
            r6[r1] = r11
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r11 = r2.a(r3, r4, r5, r6, r7, r8, r9)
            r2 = -1
            if (r11 == 0) goto L_0x003c
            boolean r4 = r11.moveToFirst()     // Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x003c
            java.lang.String r4 = "download_id"
            int r4 = r11.getColumnIndex(r4)     // Catch:{ all -> 0x0035 }
            long r4 = r11.getLong(r4)     // Catch:{ all -> 0x0035 }
            goto L_0x003d
        L_0x0035:
            r0 = move-exception
            if (r11 == 0) goto L_0x003b
            r11.close()
        L_0x003b:
            throw r0
        L_0x003c:
            r4 = r2
        L_0x003d:
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x0047
            if (r11 == 0) goto L_0x0046
            r11.close()
        L_0x0046:
            return r1
        L_0x0047:
            if (r11 == 0) goto L_0x004c
            r11.close()
        L_0x004c:
            android.app.DownloadManager$Query r11 = new android.app.DownloadManager$Query
            r11.<init>()
            long[] r2 = new long[r0]
            r2[r1] = r4
            r11.setFilterById(r2)
            android.app.DownloadManager r2 = g
            android.database.Cursor r11 = r2.query(r11)
            r2 = -1
            if (r11 == 0) goto L_0x0079
            boolean r3 = r11.moveToFirst()     // Catch:{ all -> 0x0072 }
            if (r3 == 0) goto L_0x0079
            java.lang.String r2 = "status"
            int r2 = r11.getColumnIndexOrThrow(r2)     // Catch:{ all -> 0x0072 }
            int r2 = r11.getInt(r2)     // Catch:{ all -> 0x0072 }
            goto L_0x0079
        L_0x0072:
            r0 = move-exception
            if (r11 == 0) goto L_0x0078
            r11.close()
        L_0x0078:
            throw r0
        L_0x0079:
            r3 = 4
            if (r2 == r3) goto L_0x0087
            if (r2 == r0) goto L_0x0087
            r3 = 2
            if (r2 == r3) goto L_0x0087
            if (r11 == 0) goto L_0x0086
            r11.close()
        L_0x0086:
            return r1
        L_0x0087:
            if (r11 == 0) goto L_0x008c
            r11.close()
        L_0x008c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.market.sdk.DownloadInstallManager.a(com.xiaomi.market.sdk.LocalAppInfo):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0090, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0099, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a0 A[SYNTHETIC, Splitter:B:35:0x00a0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c(android.content.Context r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            com.xiaomi.market.sdk.XiaomiUpdateAgent$UpdateInfo r0 = r10.d     // Catch:{ all -> 0x00a4 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r10)
            return
        L_0x0007:
            com.xiaomi.market.sdk.LocalAppInfo r0 = r10.e     // Catch:{ all -> 0x00a4 }
            if (r0 != 0) goto L_0x0017
            com.xiaomi.market.sdk.LocalAppInfo r0 = com.xiaomi.market.sdk.XiaomiUpdateAgent.a((android.content.Context) r11)     // Catch:{ all -> 0x00a4 }
            r10.e = r0     // Catch:{ all -> 0x00a4 }
            com.xiaomi.market.sdk.LocalAppInfo r0 = r10.e     // Catch:{ all -> 0x00a4 }
            if (r0 != 0) goto L_0x0017
            monitor-exit(r10)
            return
        L_0x0017:
            r0 = 0
            com.xiaomi.market.sdk.SDKDatabaseHelper r1 = com.xiaomi.market.sdk.SDKDatabaseHelper.a((android.content.Context) r11)     // Catch:{ all -> 0x009a }
            java.lang.String r2 = "update_download"
            java.lang.String[] r3 = com.xiaomi.market.sdk.Constants.Update.l     // Catch:{ all -> 0x009a }
            java.lang.String r4 = "package_name=?"
            r11 = 1
            java.lang.String[] r5 = new java.lang.String[r11]     // Catch:{ all -> 0x009a }
            r11 = 0
            com.xiaomi.market.sdk.LocalAppInfo r6 = r10.e     // Catch:{ all -> 0x009a }
            java.lang.String r6 = r6.f11109a     // Catch:{ all -> 0x009a }
            r5[r11] = r6     // Catch:{ all -> 0x009a }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r11 = r1.a(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x009a }
            if (r11 == 0) goto L_0x0093
            boolean r0 = r11.moveToFirst()     // Catch:{ all -> 0x0091 }
            if (r0 == 0) goto L_0x0093
            java.lang.String r0 = "download_id"
            int r0 = r11.getColumnIndex(r0)     // Catch:{ all -> 0x0091 }
            long r0 = r11.getLong(r0)     // Catch:{ all -> 0x0091 }
            r10.f = r0     // Catch:{ all -> 0x0091 }
            com.xiaomi.market.sdk.XiaomiUpdateAgent$UpdateInfo r0 = new com.xiaomi.market.sdk.XiaomiUpdateAgent$UpdateInfo     // Catch:{ all -> 0x0091 }
            r0.<init>()     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = "version_code"
            int r1 = r11.getColumnIndex(r1)     // Catch:{ all -> 0x0091 }
            int r1 = r11.getInt(r1)     // Catch:{ all -> 0x0091 }
            r0.e = r1     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = "apk_url"
            int r1 = r11.getColumnIndex(r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = r11.getString(r1)     // Catch:{ all -> 0x0091 }
            r0.g = r1     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = "apk_hash"
            int r1 = r11.getColumnIndex(r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = r11.getString(r1)     // Catch:{ all -> 0x0091 }
            r0.h = r1     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = "diff_url"
            int r1 = r11.getColumnIndex(r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = r11.getString(r1)     // Catch:{ all -> 0x0091 }
            r0.j = r1     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = "diff_hash"
            int r1 = r11.getColumnIndex(r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = r11.getString(r1)     // Catch:{ all -> 0x0091 }
            r0.k = r1     // Catch:{ all -> 0x0091 }
            r10.d = r0     // Catch:{ all -> 0x0091 }
            if (r11 == 0) goto L_0x008f
            r11.close()     // Catch:{ all -> 0x00a4 }
        L_0x008f:
            monitor-exit(r10)
            return
        L_0x0091:
            r0 = move-exception
            goto L_0x009e
        L_0x0093:
            if (r11 == 0) goto L_0x0098
            r11.close()     // Catch:{ all -> 0x00a4 }
        L_0x0098:
            monitor-exit(r10)
            return
        L_0x009a:
            r11 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
        L_0x009e:
            if (r11 == 0) goto L_0x00a3
            r11.close()     // Catch:{ all -> 0x00a4 }
        L_0x00a3:
            throw r0     // Catch:{ all -> 0x00a4 }
        L_0x00a4:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.market.sdk.DownloadInstallManager.c(android.content.Context):void");
    }

    public class WorkerHandler extends Handler {
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        public void a() {
            Context f = XiaomiUpdateAgent.f();
            if (f != null) {
                if (DownloadInstallManager.this.e == null || DownloadInstallManager.this.d == null) {
                    LocalAppInfo unused = DownloadInstallManager.this.e = XiaomiUpdateAgent.a(f);
                    if (DownloadInstallManager.this.e != null) {
                        DownloadInstallManager.this.c(f);
                    }
                }
            }
        }

        public void b() {
            post(new Runnable() {
                public final void run() {
                    DownloadInstallManager.WorkerHandler.this.d();
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void d() {
            File a2;
            Context f = XiaomiUpdateAgent.f();
            if (f != null) {
                if (!Utils.a(true)) {
                    DownloadInstallResultNotifier.a(-1);
                } else if (DownloadInstallManager.g != null && (a2 = a(f)) != null) {
                    if (a2.exists()) {
                        if (TextUtils.equals(Coder.a(a2), DownloadInstallManager.this.d.h)) {
                            a(a2.getAbsolutePath(), false);
                            return;
                        }
                        a2.delete();
                    }
                    a(c(), a2.getAbsolutePath());
                }
            }
        }

        private void a(Uri uri, String str) {
            Uri parse = Uri.parse("file://" + str);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setMimeType("application/apk-ota");
            request.setTitle(DownloadInstallManager.this.e.b);
            if (parse != null) {
                request.setDestinationUri(parse);
            }
            try {
                long unused = DownloadInstallManager.this.f = DownloadInstallManager.g.enqueue(request);
                ContentValues contentValues = new ContentValues();
                contentValues.put("package_name", DownloadInstallManager.this.e.f11109a);
                contentValues.put("download_id", Long.valueOf(DownloadInstallManager.this.f));
                contentValues.put(Constants.Update.e, Integer.valueOf(DownloadInstallManager.this.d.e));
                contentValues.put(Constants.Update.f, DownloadInstallManager.this.d.g);
                contentValues.put(Constants.Update.g, DownloadInstallManager.this.d.h);
                contentValues.put(Constants.Update.h, DownloadInstallManager.this.d.j);
                contentValues.put(Constants.Update.i, DownloadInstallManager.this.d.k);
                contentValues.put(Constants.Update.j, str);
                SDKDatabaseHelper.a(AppGlobal.a()).a(contentValues);
            } catch (Throwable th) {
                Log.b(DownloadInstallManager.b, th.toString());
                DownloadInstallResultNotifier.a(-2);
            }
        }

        private Uri c() {
            String str;
            if (TextUtils.isEmpty(DownloadInstallManager.this.d.j)) {
                str = Connection.a(DownloadInstallManager.this.d.f11119a, DownloadInstallManager.this.d.g);
            } else {
                str = Connection.a(DownloadInstallManager.this.d.f11119a, DownloadInstallManager.this.d.j);
            }
            return Uri.parse(str);
        }

        private File a(Context context) {
            File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            if (externalFilesDir == null) {
                return null;
            }
            File file = new File(externalFilesDir.getAbsolutePath() + "/xiaomi_update_sdk");
            if (!file.exists()) {
                file.mkdirs();
            }
            return new File(file.getAbsolutePath() + "/" + DownloadInstallManager.this.e.f11109a + JSMethod.NOT_SET + DownloadInstallManager.this.d.e + ".apk");
        }

        public void a(String str, boolean z) {
            post(new Runnable(str, z) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ boolean f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    DownloadInstallManager.WorkerHandler.this.b(this.f$1, this.f$2);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(String str, boolean z) {
            Context f = XiaomiUpdateAgent.f();
            if (f != null && !TextUtils.isEmpty(str)) {
                a();
                if (z) {
                    str = a(str, DownloadInstallManager.this.d.k);
                }
                if (!a(str)) {
                    Log.b(DownloadInstallManager.b, "verify downloaded apk failed");
                } else {
                    a(f, str);
                }
            }
        }

        private void a(Context context, String str) {
            Uri a2 = DownloadInstallManager.this.a(context, str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(a2, "application/vnd.android.package-archive");
            String a3 = PkgUtils.a(intent);
            if (TextUtils.isEmpty(a3)) {
                Log.b(DownloadInstallManager.b, "no activity found to install apk");
                return;
            }
            if (TextUtils.equals(a2.getScheme(), "content")) {
                context.grantUriPermission(a3, a2, 1);
            }
            intent.setPackage(a3);
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return TextUtils.equals(Coder.a(new File(str)), DownloadInstallManager.this.d.h);
        }

        private String a(String str, String str2) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.equals(Coder.a(new File(str)), str2)) {
                return null;
            }
            String str3 = str + ".apk";
            if (DownloadInstallManager.this.e == null || TextUtils.isEmpty(DownloadInstallManager.this.e.f)) {
                return null;
            }
            Patcher.a(DownloadInstallManager.this.e.f, str3, str);
            try {
                new File(str).delete();
            } catch (Exception unused) {
            }
            return str3;
        }
    }

    /* access modifiers changed from: private */
    public Uri a(Context context, String str) {
        if (Client.q()) {
            File file = new File(str);
            return LazyFileProvider.getUriForFile(context, context.getPackageName() + ".selfupdate.fileprovider", file);
        }
        return Uri.parse("file://" + str);
    }

    private static class DownloadManagerInfo {

        /* renamed from: a  reason: collision with root package name */
        public long f11104a;
        public int b;
        public int c;
        public int d;
        public int e;
        public String f;

        private DownloadManagerInfo() {
        }

        public static DownloadManagerInfo a(long j) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(new long[]{j});
            try {
                Cursor query2 = DownloadInstallManager.g.query(query);
                if (query2 != null) {
                    try {
                        if (query2.moveToFirst()) {
                            return a(query2);
                        }
                    } finally {
                        if (query2 != null) {
                            query2.close();
                        }
                    }
                }
                if (query2 != null) {
                    query2.close();
                }
                return null;
            } catch (Exception e2) {
                Log.b(DownloadInstallManager.b, "Query download from DownloadManager failed - " + e2.toString());
                return null;
            }
        }

        @SuppressLint({"InlinedApi"})
        private static DownloadManagerInfo a(Cursor cursor) {
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("status");
            int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("reason");
            int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("bytes_so_far");
            int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("total_size");
            int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow(Client.n() ? DownloadManagerPro.b : DownloadInstallManager.c);
            DownloadManagerInfo downloadManagerInfo = new DownloadManagerInfo();
            downloadManagerInfo.f11104a = cursor.getLong(columnIndexOrThrow);
            downloadManagerInfo.b = cursor.getInt(columnIndexOrThrow2);
            downloadManagerInfo.c = cursor.getInt(columnIndexOrThrow3);
            downloadManagerInfo.d = cursor.getInt(columnIndexOrThrow4);
            downloadManagerInfo.e = cursor.getInt(columnIndexOrThrow5);
            downloadManagerInfo.f = cursor.getString(columnIndexOrThrow6);
            return downloadManagerInfo;
        }
    }
}
