package com.xiaomi.jr.http.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.smarthome.download.DownloadManager;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.library.common.util.DownloadManagerPro;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class PrivateFileDownloader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10821a = "PrivateFileDownloader";
    private static final int b = 1;
    private static final int c = 2;
    private static final String d = "downloadSize";
    private static final String e = "size";
    private static final String f = "status";
    /* access modifiers changed from: private */
    public final Context g;
    /* access modifiers changed from: private */
    public DownloadHandler h = new DownloadHandler(this);
    private PrivateFileDownloadObserver i;
    /* access modifiers changed from: private */
    public final HashMap<Long, DownloadConfig> j = new HashMap<>();
    private BroadcastReceiver k = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.D.equals(intent.getAction())) {
                long longExtra = intent.getLongExtra(DownloadManager.G, 0);
                if (PrivateFileDownloader.this.j.containsKey(Long.valueOf(longExtra))) {
                    DownloadConfig downloadConfig = (DownloadConfig) PrivateFileDownloader.this.j.get(Long.valueOf(longExtra));
                    PrivateFileDownloader.this.j.remove(Long.valueOf(longExtra));
                    PrivateFileDownloader.this.h.removeMessages(2, Long.valueOf(longExtra));
                    if (!(downloadConfig.f10824a == null || downloadConfig.f10824a.get() == null)) {
                        ((DownloadListener) downloadConfig.f10824a.get()).a(longExtra, 100, 100, 8);
                    }
                    if (PrivateFileDownloader.a(context, longExtra)[2] == 8) {
                        android.app.DownloadManager downloadManager = (android.app.DownloadManager) PrivateFileDownloader.this.g.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
                        final String mimeTypeForDownloadedFile = downloadManager.getMimeTypeForDownloadedFile(longExtra);
                        Uri uriForDownloadedFile = downloadManager.getUriForDownloadedFile(longExtra);
                        final File file = new File(PrivateFileDownloader.a(context, downloadConfig.b == null ? "" : downloadConfig.b, downloadConfig.c));
                        ParcelFileDescriptor a2 = FileUtils.a(PrivateFileDownloader.this.g, uriForDownloadedFile);
                        if (a2 != null) {
                            final FileInputStream fileInputStream = new FileInputStream(a2.getFileDescriptor());
                            final DownloadConfig downloadConfig2 = downloadConfig;
                            final long j = longExtra;
                            new AsyncTask<Void, Void, Void>() {
                                /* access modifiers changed from: protected */
                                /* renamed from: a */
                                public Void doInBackground(Void... voidArr) {
                                    FileUtils.a(file, (InputStream) fileInputStream);
                                    return null;
                                }

                                /* access modifiers changed from: protected */
                                /* renamed from: a */
                                public void onPostExecute(Void voidR) {
                                    if (!(downloadConfig2.f10824a == null || downloadConfig2.f10824a.get() == null)) {
                                        ((DownloadListener) downloadConfig2.f10824a.get()).a(j, downloadConfig2.c, mimeTypeForDownloadedFile);
                                    }
                                    PrivateFileDownloader.b(PrivateFileDownloader.this.g, j);
                                    PrivateFileDownloader.this.h.removeMessages(1, Long.valueOf(j));
                                    super.onPostExecute(voidR);
                                }
                            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        }
                    }
                }
            }
        }
    };

    public static class DownloadConfig {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<DownloadListener> f10824a;
        String b;
        String c;
    }

    public interface DownloadListener {
        void a(long j);

        void a(long j, int i, int i2, int i3);

        void a(long j, String str, String str2);
    }

    public PrivateFileDownloader(Context context) {
        this.g = context;
        context.registerReceiver(this.k, new IntentFilter(DownloadManager.D));
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r13, com.xiaomi.jr.http.download.PrivateFileDownloader.DownloadListener r14, java.lang.String r15, boolean r16) {
        /*
            r12 = this;
            r1 = 0
            r2 = 1
            java.net.URL r0 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0027 }
            r4 = r13
            r0.<init>(r13)     // Catch:{ MalformedURLException -> 0x0025 }
            java.lang.String r0 = r0.getPath()     // Catch:{ MalformedURLException -> 0x0025 }
            if (r0 == 0) goto L_0x002c
            java.lang.String r3 = "/"
            int r3 = r0.lastIndexOf(r3)     // Catch:{ MalformedURLException -> 0x0025 }
            r5 = -1
            if (r3 == r5) goto L_0x002c
            int r5 = r0.length()     // Catch:{ MalformedURLException -> 0x0025 }
            int r5 = r5 - r2
            if (r3 >= r5) goto L_0x002c
            int r3 = r3 + r2
            java.lang.String r0 = r0.substring(r3)     // Catch:{ MalformedURLException -> 0x0025 }
            r1 = r0
            goto L_0x002c
        L_0x0025:
            r0 = move-exception
            goto L_0x0029
        L_0x0027:
            r0 = move-exception
            r4 = r13
        L_0x0029:
            r0.printStackTrace()
        L_0x002c:
            r5 = r1
            if (r5 == 0) goto L_0x0043
            r0 = -1
            r8 = 0
            r11 = 0
            r3 = r12
            r4 = r13
            r6 = r14
            r7 = r15
            r10 = r16
            long r3 = r3.a(r4, r5, r6, r7, r8, r10, r11)
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r2 = 0
        L_0x0044:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.http.download.PrivateFileDownloader.a(java.lang.String, com.xiaomi.jr.http.download.PrivateFileDownloader$DownloadListener, java.lang.String, boolean):boolean");
    }

    public long a(String str, String str2, DownloadListener downloadListener, String str3, long j2, boolean z, boolean z2) {
        long j3;
        android.app.DownloadManager downloadManager = (android.app.DownloadManager) this.g.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        if (a(downloadManager, str, str2)) {
            return -1;
        }
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(z ? 0 : 2);
        try {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str2);
            try {
                j3 = downloadManager.enqueue(request);
            } catch (Exception e2) {
                MifiLog.e(f10821a, "enqueue download throws " + e2.toString());
                j3 = -1;
            }
            if (j3 < 0) {
                return -1;
            }
            DownloadConfig downloadConfig = new DownloadConfig();
            downloadConfig.f10824a = new WeakReference<>(downloadListener);
            downloadConfig.b = str3;
            downloadConfig.c = str2;
            this.j.put(Long.valueOf(j3), downloadConfig);
            if (j2 > 0) {
                Message obtain = Message.obtain(this.h, 1);
                obtain.obj = Long.valueOf(j3);
                this.h.sendMessageDelayed(obtain, j2);
            }
            if (z2) {
                b(a(j3));
            }
            return j3;
        } catch (IllegalStateException e3) {
            MifiLog.d(f10821a, "setDestinationInExternalPublicDir fail.", e3);
            return -1;
        }
    }

    private boolean a(android.app.DownloadManager downloadManager, String str, String str2) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterByStatus(7);
        Cursor query2 = downloadManager.query(query);
        if (query2 != null) {
            try {
                if (query2.moveToFirst()) {
                    do {
                        String str3 = query2.getString(query2.getColumnIndex("uri")).split("\\?")[0];
                        String str4 = null;
                        if (Build.VERSION.SDK_INT >= 24) {
                            String string = query2.getString(query2.getColumnIndex("local_uri"));
                            if (string != null) {
                                str4 = Uri.parse(string).getLastPathSegment();
                            }
                        } else {
                            String string2 = query2.getString(query2.getColumnIndex(DownloadManagerPro.b));
                            if (string2 != null) {
                                str4 = new File(string2).getName();
                            }
                        }
                        if (TextUtils.equals(str3, str.split("\\?")[0]) && TextUtils.equals(str4, str2)) {
                            query2.close();
                            return true;
                        }
                    } while (query2.moveToNext());
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                query2.close();
                throw th;
            }
            query2.close();
        }
        return false;
    }

    public void a() {
        if (this.k != null) {
            this.g.unregisterReceiver(this.k);
            this.k = null;
        }
        this.h.removeMessages(1);
        this.h.removeMessages(2);
        b();
    }

    public Uri a(long j2) {
        return Uri.parse("content://downloads/my_downloads" + File.separator + j2);
    }

    public long a(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (TextUtils.isEmpty(lastPathSegment) || !lastPathSegment.matches("^[0-9]*$")) {
            return -1;
        }
        return Long.parseLong(lastPathSegment);
    }

    public void b(Uri uri) {
        if (this.i == null) {
            this.i = new PrivateFileDownloadObserver(this);
        }
        this.g.getContentResolver().registerContentObserver(uri, true, this.i);
    }

    public void b() {
        if (this.i != null) {
            this.g.getContentResolver().unregisterContentObserver(this.i);
        }
        this.i = null;
    }

    public static class PrivateFileDownloadObserver extends ContentObserver {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<PrivateFileDownloader> f10826a;

        public PrivateFileDownloadObserver(PrivateFileDownloader privateFileDownloader) {
            super((Handler) null);
            this.f10826a = new WeakReference<>(privateFileDownloader);
        }

        public void onChange(boolean z) {
            onChange(z, (Uri) null);
        }

        public void onChange(boolean z, Uri uri) {
            PrivateFileDownloader privateFileDownloader;
            if (uri != null && (privateFileDownloader = (PrivateFileDownloader) this.f10826a.get()) != null) {
                long a2 = privateFileDownloader.a(uri);
                if (a2 == -1) {
                    MifiLog.e(PrivateFileDownloader.f10821a, "parse downloadId=-1 from uri " + uri);
                    return;
                }
                int[] a3 = PrivateFileDownloader.a(privateFileDownloader.g, a2);
                Bundle bundle = new Bundle();
                bundle.putInt(PrivateFileDownloader.d, a3[0]);
                bundle.putInt(PrivateFileDownloader.e, a3[1]);
                bundle.putInt("status", a3[2]);
                Message obtain = Message.obtain(privateFileDownloader.h, 2);
                obtain.obj = Long.valueOf(a2);
                obtain.setData(bundle);
                privateFileDownloader.h.sendMessage(obtain);
            }
        }
    }

    public static int[] a(Context context, long j2) {
        int[] iArr = {-1, -1, -1};
        Cursor query = ((android.app.DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).query(new DownloadManager.Query().setFilterById(new long[]{j2}));
        if (query != null && query.moveToFirst()) {
            iArr[0] = query.getInt(query.getColumnIndex("bytes_so_far"));
            iArr[1] = query.getInt(query.getColumnIndex("total_size"));
            iArr[2] = query.getInt(query.getColumnIndex("status"));
            query.close();
        }
        return iArr;
    }

    private static String a(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!"content".equals(scheme)) {
            return null;
        }
        String[] strArr = {Downloads._DATA};
        Cursor query = context.getContentResolver().query(uri, strArr, (String) null, (String[]) null, (String) null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        String string = query.getString(query.getColumnIndex(strArr[0]));
        query.close();
        return string;
    }

    public static void b(Context context, long j2) {
        android.app.DownloadManager downloadManager = (android.app.DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        Uri uriForDownloadedFile = downloadManager.getUriForDownloadedFile(j2);
        if (uriForDownloadedFile != null) {
            String a2 = a(context, uriForDownloadedFile);
            if (!TextUtils.isEmpty(a2)) {
                FileUtils.a(new File(a2));
            }
            downloadManager.remove(new long[]{j2});
        }
    }

    public static String c(Context context, long j2) {
        Uri uriForDownloadedFile;
        if (j2 == -1) {
            return null;
        }
        android.app.DownloadManager downloadManager = (android.app.DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(new long[]{j2});
        Cursor query2 = downloadManager.query(query);
        if (query2 != null && query2.moveToFirst()) {
            int i2 = query2.getInt(query2.getColumnIndex("status"));
            query2.close();
            if (i2 != 8 || (uriForDownloadedFile = downloadManager.getUriForDownloadedFile(j2)) == null) {
                return null;
            }
            return a(context, uriForDownloadedFile);
        }
        return null;
    }

    public static String a(Context context, String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            str3 = File.separator + str;
        }
        return context.getFilesDir() + str3 + File.separator + str2;
    }

    private static class DownloadHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<PrivateFileDownloader> f10825a;

        DownloadHandler(PrivateFileDownloader privateFileDownloader) {
            super(Looper.getMainLooper());
            this.f10825a = new WeakReference<>(privateFileDownloader);
        }

        public void handleMessage(Message message) {
            WeakReference<DownloadListener> weakReference;
            PrivateFileDownloader privateFileDownloader = (PrivateFileDownloader) this.f10825a.get();
            if (privateFileDownloader != null && message != null) {
                if (message.what == 1) {
                    MifiLog.b(PrivateFileDownloader.f10821a, "download timeout: " + message.toString());
                    long longValue = ((Long) message.obj).longValue();
                    if (PrivateFileDownloader.a(privateFileDownloader.g, longValue)[2] != 8 && privateFileDownloader.j.containsKey(Long.valueOf(longValue))) {
                        WeakReference<DownloadListener> weakReference2 = ((DownloadConfig) privateFileDownloader.j.get(Long.valueOf(longValue))).f10824a;
                        if (!(weakReference2 == null || weakReference2.get() == null)) {
                            ((DownloadListener) weakReference2.get()).a(longValue);
                        }
                        privateFileDownloader.j.remove(Long.valueOf(longValue));
                    }
                } else if (message.what == 2) {
                    long longValue2 = ((Long) message.obj).longValue();
                    Bundle data = message.getData();
                    int i = data.getInt(PrivateFileDownloader.d, -1);
                    int i2 = data.getInt(PrivateFileDownloader.e, -1);
                    int i3 = data.getInt("status", -1);
                    if (privateFileDownloader.j.get(Long.valueOf(longValue2)) != null && (weakReference = ((DownloadConfig) privateFileDownloader.j.get(Long.valueOf(longValue2))).f10824a) != null && weakReference.get() != null) {
                        ((DownloadListener) weakReference.get()).a(longValue2, i, i2, i3);
                    }
                }
            }
        }
    }
}
