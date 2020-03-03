package com.xiaomi.smarthome.download;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.xiaomi.smarthome.download.Helpers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private Context f15562a;
    private final DownloadInfo b;
    private SystemFacade c;

    private void b() {
    }

    public DownloadThread(Context context, SystemFacade systemFacade, DownloadInfo downloadInfo) {
        this.f15562a = context;
        this.c = systemFacade;
        this.b = downloadInfo;
    }

    private String a() {
        String str = this.b.y;
        return str == null ? Constants.t : str;
    }

    private static class State {

        /* renamed from: a  reason: collision with root package name */
        public String f15564a;
        public FileOutputStream b;
        public String c;
        public boolean d = false;
        public int e = 0;
        public int f = 0;
        public String g;
        public boolean h = false;
        public String i;

        public State(DownloadInfo downloadInfo) {
            this.c = DownloadThread.b(downloadInfo.m);
            this.i = downloadInfo.i;
            this.f15564a = downloadInfo.l;
        }
    }

    private static class InnerState {

        /* renamed from: a  reason: collision with root package name */
        public int f15563a;
        public String b;
        public boolean c;
        public String d;
        public String e;
        public String f;
        public int g;
        public long h;

        private InnerState() {
            this.f15563a = 0;
            this.c = false;
            this.g = 0;
            this.h = 0;
        }
    }

    private class StopRequest extends Throwable {
        private static final long serialVersionUID = 1;
        public int mFinalStatus;

        public StopRequest(int i, String str) {
            super(str);
            this.mFinalStatus = i;
        }

        public StopRequest(int i, String str, Throwable th) {
            super(str, th);
            this.mFinalStatus = i;
        }
    }

    private class RetryDownload extends Throwable {
        private static final long serialVersionUID = 1;

        private RetryDownload() {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x013d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r12 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
            com.xiaomi.smarthome.download.DownloadThread$State r1 = new com.xiaomi.smarthome.download.DownloadThread$State
            com.xiaomi.smarthome.download.DownloadInfo r0 = r12.b
            r1.<init>(r0)
            r2 = 491(0x1eb, float:6.88E-43)
            r3 = 0
            r9 = 0
            android.content.Context r0 = r12.f15562a     // Catch:{ StopRequest -> 0x00f7, Throwable -> 0x00bc, all -> 0x00b8 }
            java.lang.String r4 = "power"
            java.lang.Object r0 = r0.getSystemService(r4)     // Catch:{ StopRequest -> 0x00f7, Throwable -> 0x00bc, all -> 0x00b8 }
            android.os.PowerManager r0 = (android.os.PowerManager) r0     // Catch:{ StopRequest -> 0x00f7, Throwable -> 0x00bc, all -> 0x00b8 }
            java.lang.String r4 = "DownloadManager"
            r5 = 1
            android.os.PowerManager$WakeLock r4 = r0.newWakeLock(r5, r4)     // Catch:{ StopRequest -> 0x00f7, Throwable -> 0x00bc, all -> 0x00b8 }
            r4.acquire()     // Catch:{ StopRequest -> 0x00b4, Throwable -> 0x00b0, all -> 0x00ab }
            okhttp3.OkHttpClient r6 = new okhttp3.OkHttpClient     // Catch:{ StopRequest -> 0x00b4, Throwable -> 0x00b0, all -> 0x00ab }
            r6.<init>()     // Catch:{ StopRequest -> 0x00b4, Throwable -> 0x00b0, all -> 0x00ab }
            r0 = 0
        L_0x002a:
            if (r0 != 0) goto L_0x0086
            java.lang.String r7 = "DownloadManager"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r8.<init>()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r10 = "Initiating request for download "
            r8.append(r10)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            com.xiaomi.smarthome.download.DownloadInfo r10 = r12.b     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            long r10 = r10.h     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r8.append(r10)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r8 = r8.toString()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            android.util.Log.i(r7, r8)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            okhttp3.Request$Builder r7 = new okhttp3.Request$Builder     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r7.<init>()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r8 = r1.i     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            okhttp3.Request$Builder r7 = r7.url((java.lang.String) r8)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            java.lang.String r8 = "User-Agent"
            java.lang.String r10 = r12.a()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            okhttp3.Request$Builder r7 = r7.header(r8, r10)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            com.xiaomi.smarthome.download.DownloadThread$InnerState r8 = new com.xiaomi.smarthome.download.DownloadThread$InnerState     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r8.<init>()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r12.d(r1, r8)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r12.a((com.xiaomi.smarthome.download.DownloadThread.InnerState) r8, (okhttp3.Request.Builder) r7)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            okhttp3.Request r7 = r7.build()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            okhttp3.Call r7 = r6.newCall(r7)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r12.a((com.xiaomi.smarthome.download.DownloadThread.State) r1, (com.xiaomi.smarthome.download.DownloadThread.InnerState) r8, (okhttp3.Call) r7)     // Catch:{ RetryDownload -> 0x007c, all -> 0x0076 }
            r7.cancel()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r0 = 1
            goto L_0x002a
        L_0x0076:
            r0 = move-exception
            r3 = r0
            r7.cancel()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            throw r3     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
        L_0x007c:
            r7.cancel()     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            goto L_0x002a
        L_0x0080:
            r0 = move-exception
            goto L_0x00ad
        L_0x0082:
            r0 = move-exception
            goto L_0x00b2
        L_0x0084:
            r0 = move-exception
            goto L_0x00b6
        L_0x0086:
            r12.b((com.xiaomi.smarthome.download.DownloadThread.State) r1)     // Catch:{ StopRequest -> 0x0084, Throwable -> 0x0082, all -> 0x0080 }
            r0 = 200(0xc8, float:2.8E-43)
            if (r4 == 0) goto L_0x0090
            r4.release()
        L_0x0090:
            r12.a((com.xiaomi.smarthome.download.DownloadThread.State) r1, (int) r0)
            boolean r3 = r1.d
            int r4 = r1.e
            boolean r5 = r1.h
            java.lang.String r6 = r1.f15564a
            java.lang.String r7 = r1.g
            java.lang.String r8 = r1.c
            r2 = 200(0xc8, float:2.8E-43)
        L_0x00a1:
            r1 = r12
        L_0x00a2:
            r1.a(r2, r3, r4, r5, r6, r7, r8)
            com.xiaomi.smarthome.download.DownloadInfo r0 = r12.b
            r0.L = r9
            goto L_0x0139
        L_0x00ab:
            r0 = move-exception
            r6 = r3
        L_0x00ad:
            r3 = r4
            goto L_0x013b
        L_0x00b0:
            r0 = move-exception
            r6 = r3
        L_0x00b2:
            r3 = r4
            goto L_0x00be
        L_0x00b4:
            r0 = move-exception
            r6 = r3
        L_0x00b6:
            r3 = r4
            goto L_0x00f9
        L_0x00b8:
            r0 = move-exception
            r6 = r3
            goto L_0x013b
        L_0x00bc:
            r0 = move-exception
            r6 = r3
        L_0x00be:
            java.lang.String r4 = "DownloadManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x013a }
            r5.<init>()     // Catch:{ all -> 0x013a }
            java.lang.String r7 = "Exception for id "
            r5.append(r7)     // Catch:{ all -> 0x013a }
            com.xiaomi.smarthome.download.DownloadInfo r7 = r12.b     // Catch:{ all -> 0x013a }
            long r7 = r7.h     // Catch:{ all -> 0x013a }
            r5.append(r7)     // Catch:{ all -> 0x013a }
            java.lang.String r7 = ": "
            r5.append(r7)     // Catch:{ all -> 0x013a }
            r5.append(r0)     // Catch:{ all -> 0x013a }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x013a }
            android.util.Log.w(r4, r0)     // Catch:{ all -> 0x013a }
            if (r3 == 0) goto L_0x00e5
            r3.release()
        L_0x00e5:
            r12.a((com.xiaomi.smarthome.download.DownloadThread.State) r1, (int) r2)
            boolean r3 = r1.d
            int r4 = r1.e
            boolean r5 = r1.h
            java.lang.String r6 = r1.f15564a
            java.lang.String r7 = r1.g
            java.lang.String r8 = r1.c
            r2 = 491(0x1eb, float:6.88E-43)
            goto L_0x00a1
        L_0x00f7:
            r0 = move-exception
            r6 = r3
        L_0x00f9:
            java.lang.String r4 = "DownloadManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x013a }
            r5.<init>()     // Catch:{ all -> 0x013a }
            java.lang.String r7 = "Aborting request for download "
            r5.append(r7)     // Catch:{ all -> 0x013a }
            com.xiaomi.smarthome.download.DownloadInfo r7 = r12.b     // Catch:{ all -> 0x013a }
            long r7 = r7.h     // Catch:{ all -> 0x013a }
            r5.append(r7)     // Catch:{ all -> 0x013a }
            java.lang.String r7 = ": "
            r5.append(r7)     // Catch:{ all -> 0x013a }
            java.lang.String r7 = r0.getMessage()     // Catch:{ all -> 0x013a }
            r5.append(r7)     // Catch:{ all -> 0x013a }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x013a }
            android.util.Log.w(r4, r5)     // Catch:{ all -> 0x013a }
            int r0 = r0.mFinalStatus     // Catch:{ all -> 0x013a }
            if (r3 == 0) goto L_0x0126
            r3.release()
        L_0x0126:
            r12.a((com.xiaomi.smarthome.download.DownloadThread.State) r1, (int) r0)
            boolean r3 = r1.d
            int r4 = r1.e
            boolean r5 = r1.h
            java.lang.String r6 = r1.f15564a
            java.lang.String r7 = r1.g
            java.lang.String r8 = r1.c
            r1 = r12
            r2 = r0
            goto L_0x00a2
        L_0x0139:
            return
        L_0x013a:
            r0 = move-exception
        L_0x013b:
            if (r3 == 0) goto L_0x0140
            r3.release()
        L_0x0140:
            r12.a((com.xiaomi.smarthome.download.DownloadThread.State) r1, (int) r2)
            boolean r3 = r1.d
            int r4 = r1.e
            boolean r5 = r1.h
            java.lang.String r6 = r1.f15564a
            java.lang.String r7 = r1.g
            java.lang.String r8 = r1.c
            r2 = 491(0x1eb, float:6.88E-43)
            r1 = r12
            r1.a(r2, r3, r4, r5, r6, r7, r8)
            com.xiaomi.smarthome.download.DownloadInfo r1 = r12.b
            r1.L = r9
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.download.DownloadThread.run():void");
    }

    private void a(State state, InnerState innerState, Call call) throws StopRequest, RetryDownload {
        a(state);
        Response a2 = a(state, call);
        c(state, innerState, a2);
        a(state, innerState, a2);
        a(state, innerState, new byte[4096], a(state, a2));
    }

    private void a(State state) throws StopRequest {
        int d = this.b.d();
        if (d != 1) {
            int i = Downloads.STATUS_QUEUED_FOR_WIFI;
            if (d == 3) {
                this.b.a(true);
            } else if (d == 4) {
                this.b.a(false);
            } else {
                i = 195;
            }
            throw new StopRequest(i, this.b.a(d));
        }
    }

    private void a(State state, InnerState innerState, byte[] bArr, InputStream inputStream) throws StopRequest {
        do {
            int b2 = b(state, innerState, bArr, inputStream);
            if (b2 == -1) {
                b(state, innerState);
                return;
            }
            state.h = true;
            a(state, bArr, b2);
            innerState.f15563a += b2;
            a(state, innerState);
            Log.v(Constants.f15548a, "downloaded " + innerState.f15563a + " for " + this.b.i);
            e(state);
        } while (!this.c.h());
        throw new StopRequest(192, "download thread exit");
    }

    private void b(State state) throws StopRequest {
        c(state);
    }

    private void a(State state, int i) {
        d(state);
        if (state.f15564a != null && Downloads.isStatusError(i)) {
            new File(state.f15564a).delete();
            state.f15564a = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0039 A[SYNTHETIC, Splitter:B:23:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0074 A[SYNTHETIC, Splitter:B:34:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009b A[SYNTHETIC, Splitter:B:41:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c2 A[SYNTHETIC, Splitter:B:48:0x00c2] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c8 A[SYNTHETIC, Splitter:B:51:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(com.xiaomi.smarthome.download.DownloadThread.State r7) {
        /*
            r6 = this;
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x009f, SyncFailedException -> 0x0078, IOException -> 0x0051, RuntimeException -> 0x002f }
            java.lang.String r2 = r7.f15564a     // Catch:{ FileNotFoundException -> 0x009f, SyncFailedException -> 0x0078, IOException -> 0x0051, RuntimeException -> 0x002f }
            r3 = 1
            r1.<init>(r2, r3)     // Catch:{ FileNotFoundException -> 0x009f, SyncFailedException -> 0x0078, IOException -> 0x0051, RuntimeException -> 0x002f }
            java.io.FileDescriptor r0 = r1.getFD()     // Catch:{ FileNotFoundException -> 0x0026, SyncFailedException -> 0x0021, IOException -> 0x001c, RuntimeException -> 0x0019, all -> 0x0015 }
            r0.sync()     // Catch:{ FileNotFoundException -> 0x0026, SyncFailedException -> 0x0021, IOException -> 0x001c, RuntimeException -> 0x0019, all -> 0x0015 }
            r1.close()     // Catch:{ IOException -> 0x0048, RuntimeException -> 0x003e }
            goto L_0x00c5
        L_0x0015:
            r7 = move-exception
            r0 = r1
            goto L_0x00c6
        L_0x0019:
            r7 = move-exception
            r0 = r1
            goto L_0x0030
        L_0x001c:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0052
        L_0x0021:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0079
        L_0x0026:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00a0
        L_0x002c:
            r7 = move-exception
            goto L_0x00c6
        L_0x002f:
            r7 = move-exception
        L_0x0030:
            java.lang.String r1 = "DownloadManager"
            java.lang.String r2 = "exception while syncing file: "
            android.util.Log.w(r1, r2, r7)     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x00c5
            r0.close()     // Catch:{ IOException -> 0x0048, RuntimeException -> 0x003e }
            goto L_0x00c5
        L_0x003e:
            r7 = move-exception
            java.lang.String r0 = "DownloadManager"
            java.lang.String r1 = "exception while closing file: "
            android.util.Log.w(r0, r1, r7)
            goto L_0x00c5
        L_0x0048:
            r7 = move-exception
            java.lang.String r0 = "DownloadManager"
            java.lang.String r1 = "IOException while closing synced file: "
            android.util.Log.w(r0, r1, r7)
            goto L_0x00c5
        L_0x0051:
            r1 = move-exception
        L_0x0052:
            java.lang.String r2 = "DownloadManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x002c }
            r3.<init>()     // Catch:{ all -> 0x002c }
            java.lang.String r4 = "IOException trying to sync "
            r3.append(r4)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = r7.f15564a     // Catch:{ all -> 0x002c }
            r3.append(r7)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = ": "
            r3.append(r7)     // Catch:{ all -> 0x002c }
            r3.append(r1)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x002c }
            android.util.Log.w(r2, r7)     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x00c5
            r0.close()     // Catch:{ IOException -> 0x0048, RuntimeException -> 0x003e }
            goto L_0x00c5
        L_0x0078:
            r1 = move-exception
        L_0x0079:
            java.lang.String r2 = "DownloadManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x002c }
            r3.<init>()     // Catch:{ all -> 0x002c }
            java.lang.String r4 = "file "
            r3.append(r4)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = r7.f15564a     // Catch:{ all -> 0x002c }
            r3.append(r7)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = " sync failed: "
            r3.append(r7)     // Catch:{ all -> 0x002c }
            r3.append(r1)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x002c }
            android.util.Log.w(r2, r7)     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x00c5
            r0.close()     // Catch:{ IOException -> 0x0048, RuntimeException -> 0x003e }
            goto L_0x00c5
        L_0x009f:
            r1 = move-exception
        L_0x00a0:
            java.lang.String r2 = "DownloadManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x002c }
            r3.<init>()     // Catch:{ all -> 0x002c }
            java.lang.String r4 = "file "
            r3.append(r4)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = r7.f15564a     // Catch:{ all -> 0x002c }
            r3.append(r7)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = " not found: "
            r3.append(r7)     // Catch:{ all -> 0x002c }
            r3.append(r1)     // Catch:{ all -> 0x002c }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x002c }
            android.util.Log.w(r2, r7)     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x00c5
            r0.close()     // Catch:{ IOException -> 0x0048, RuntimeException -> 0x003e }
        L_0x00c5:
            return
        L_0x00c6:
            if (r0 == 0) goto L_0x00dd
            r0.close()     // Catch:{ IOException -> 0x00d5, RuntimeException -> 0x00cc }
            goto L_0x00dd
        L_0x00cc:
            r0 = move-exception
            java.lang.String r1 = "DownloadManager"
            java.lang.String r2 = "exception while closing file: "
            android.util.Log.w(r1, r2, r0)
            goto L_0x00dd
        L_0x00d5:
            r0 = move-exception
            java.lang.String r1 = "DownloadManager"
            java.lang.String r2 = "IOException while closing synced file: "
            android.util.Log.w(r1, r2, r0)
        L_0x00dd:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.download.DownloadThread.c(com.xiaomi.smarthome.download.DownloadThread$State):void");
    }

    private void d(State state) {
        try {
            if (state.b != null) {
                state.b.close();
                state.b = null;
            }
        } catch (IOException unused) {
        }
    }

    private void e(State state) throws StopRequest {
        synchronized (this.b) {
            if (this.b.p == 1) {
                throw new StopRequest(193, "download paused by owner");
            }
        }
        if (this.b.q == 490) {
            throw new StopRequest(Downloads.STATUS_CANCELED, "download canceled");
        }
    }

    private void a(State state, InnerState innerState) {
        long a2 = this.c.a();
        if (innerState.f15563a - innerState.g > 4096 && a2 - innerState.h > Constants.x) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Downloads.COLUMN_CURRENT_BYTES, Integer.valueOf(innerState.f15563a));
            this.f15562a.getContentResolver().update(this.b.f(), contentValues, (String) null, (String[]) null);
            innerState.g = innerState.f15563a;
            innerState.h = a2;
        }
    }

    private void a(State state, byte[] bArr, int i) throws StopRequest {
        try {
            if (state.b == null) {
                state.b = new FileOutputStream(state.f15564a, true);
            }
            state.b.write(bArr, 0, i);
            if (this.b.n == 0) {
                d(state);
            }
        } catch (IOException e) {
            if (!Helpers.a()) {
                throw new StopRequest(Downloads.STATUS_DEVICE_NOT_FOUND_ERROR, "external media not mounted while writing destination file");
            } else if (Helpers.a(Helpers.a(state.f15564a)) < ((long) i)) {
                throw new StopRequest(Downloads.STATUS_INSUFFICIENT_SPACE_ERROR, "insufficient space while writing destination file", e);
            } else {
                throw new StopRequest(Downloads.STATUS_FILE_ERROR, "while writing destination file: " + e.toString(), e);
            }
        }
    }

    private void b(State state, InnerState innerState) throws StopRequest {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Downloads.COLUMN_CURRENT_BYTES, Integer.valueOf(innerState.f15563a));
        if (innerState.d == null) {
            contentValues.put(Downloads.COLUMN_TOTAL_BYTES, Integer.valueOf(innerState.f15563a));
        }
        this.f15562a.getContentResolver().update(this.b.f(), contentValues, (String) null, (String[]) null);
        if (!((innerState.d == null || innerState.f15563a == Integer.parseInt(innerState.d)) ? false : true)) {
            return;
        }
        if (a(innerState)) {
            throw new StopRequest(Downloads.STATUS_CANNOT_RESUME, "mismatched content length");
        }
        throw new StopRequest(f(state), "closed socket before end of file");
    }

    private boolean a(InnerState innerState) {
        return innerState.f15563a > 0 && !this.b.j && innerState.b == null;
    }

    private int b(State state, InnerState innerState, byte[] bArr, InputStream inputStream) throws StopRequest {
        try {
            return inputStream.read(bArr);
        } catch (IOException e) {
            b();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Downloads.COLUMN_CURRENT_BYTES, Integer.valueOf(innerState.f15563a));
            this.f15562a.getContentResolver().update(this.b.f(), contentValues, (String) null, (String[]) null);
            if (a(innerState)) {
                throw new StopRequest(Downloads.STATUS_CANNOT_RESUME, "while reading response: " + e.toString() + ", can't resume interrupted download with no ETag", e);
            }
            int f = f(state);
            throw new StopRequest(f, "while reading response: " + e.toString(), e);
        }
    }

    private InputStream a(State state, Response response) throws StopRequest {
        try {
            return response.body().byteStream();
        } catch (Exception e) {
            b();
            int f = f(state);
            throw new StopRequest(f, "while getting entity: " + e.toString(), e);
        }
    }

    private void a(State state, InnerState innerState, Response response) throws StopRequest {
        long j;
        if (!innerState.c) {
            b(state, innerState, response);
            try {
                Context context = this.f15562a;
                String str = this.b.i;
                String str2 = this.b.k;
                String str3 = innerState.e;
                String str4 = innerState.f;
                String str5 = state.c;
                int i = this.b.n;
                if (innerState.d != null) {
                    j = Long.parseLong(innerState.d);
                } else {
                    j = 0;
                }
                state.f15564a = Helpers.a(context, str, str2, str3, str4, str5, i, j, this.b.E);
                try {
                    state.b = new FileOutputStream(state.f15564a);
                    c(state, innerState);
                    a(state);
                } catch (FileNotFoundException e) {
                    throw new StopRequest(Downloads.STATUS_FILE_ERROR, "while opening destination file: " + e.toString(), e);
                }
            } catch (Helpers.GenerateSaveFileError e2) {
                throw new StopRequest(e2.mStatus, e2.mMessage);
            }
        }
    }

    private void c(State state, InnerState innerState) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Downloads._DATA, state.f15564a);
        if (innerState.b != null) {
            contentValues.put("etag", innerState.b);
        }
        if (state.c != null) {
            contentValues.put(Downloads.COLUMN_MIME_TYPE, state.c);
        }
        contentValues.put(Downloads.COLUMN_TOTAL_BYTES, Long.valueOf(this.b.A));
        this.f15562a.getContentResolver().update(this.b.f(), contentValues, (String) null, (String[]) null);
    }

    private void b(State state, InnerState innerState, Response response) throws StopRequest {
        String header;
        String header2 = response.header("Content-Disposition");
        if (header2 != null) {
            innerState.e = header2;
        }
        String header3 = response.header(HttpHeaders.CONTENT_LOCATION);
        if (header3 != null) {
            innerState.f = header3;
        }
        if (state.c == null && (header = response.header("Content-Type")) != null) {
            state.c = b(header);
        }
        String header4 = response.header(HttpHeaders.ETAG);
        if (header4 != null) {
            innerState.b = header4;
        }
        String str = null;
        String header5 = response.header("Transfer-Encoding");
        if (header5 != null) {
            str = header5;
        }
        if (str == null) {
            String header6 = response.header("Content-Length");
            if (header6 != null) {
                innerState.d = header6;
                this.b.A = Long.parseLong(innerState.d);
            }
        } else {
            Log.v(Constants.f15548a, "ignoring content-length because of xfer-encoding");
        }
        Log.v(Constants.f15548a, "Content-Disposition: " + innerState.e);
        Log.v(Constants.f15548a, "Content-Length: " + innerState.d);
        Log.v(Constants.f15548a, "Content-Location: " + innerState.f);
        Log.v(Constants.f15548a, "Content-Type: " + state.c);
        Log.v(Constants.f15548a, "ETag: " + innerState.b);
        Log.v(Constants.f15548a, "Transfer-Encoding: " + str);
        boolean z = innerState.d == null && (str == null || !str.equalsIgnoreCase("chunked"));
        if (!this.b.j && z) {
            throw new StopRequest(Downloads.STATUS_HTTP_DATA_ERROR, "can't know size of download, giving up");
        }
    }

    private void c(State state, InnerState innerState, Response response) throws StopRequest, RetryDownload {
        int code = response.code();
        if (code == 503 && this.b.r < 5) {
            b(state, response);
        }
        if (!(code == 301 || code == 302)) {
        }
        if (code != (innerState.c ? 206 : 200)) {
            a(state, innerState, code);
        }
    }

    private void a(State state, InnerState innerState, int i) throws StopRequest {
        int i2 = !Downloads.isStatusError(i) ? (i < 300 || i >= 400) ? (!innerState.c || i != 200) ? Downloads.STATUS_UNHANDLED_HTTP_CODE : Downloads.STATUS_CANNOT_RESUME : 493 : i;
        throw new StopRequest(i2, "http error " + i);
    }

    private void a(State state, Response response, int i) throws StopRequest, RetryDownload {
        Log.v(Constants.f15548a, "got HTTP redirect " + i);
        if (state.f < 5) {
            String header = response.header("Location");
            if (header != null) {
                Log.v(Constants.f15548a, "Location :" + header);
                try {
                    String uri = new URI(this.b.i).resolve(new URI(header)).toString();
                    state.f++;
                    state.i = uri;
                    if (i == 301 || i == 303) {
                        state.g = uri;
                    }
                    throw new RetryDownload();
                } catch (URISyntaxException unused) {
                    throw new StopRequest(Downloads.STATUS_HTTP_DATA_ERROR, "Couldn't resolve redirect URI");
                }
            }
        } else {
            throw new StopRequest(Downloads.STATUS_TOO_MANY_REDIRECTS, "too many redirects");
        }
    }

    private void b(State state, Response response) throws StopRequest {
        Log.v(Constants.f15548a, "got HTTP response code 503");
        state.d = true;
        String header = response.header(HttpHeaders.RETRY_AFTER);
        if (header != null) {
            try {
                Log.v(Constants.f15548a, "Retry-After :" + header);
                state.e = Integer.parseInt(header);
                if (state.e >= 0) {
                    if (state.e < 30) {
                        state.e = 30;
                    } else if (state.e > 86400) {
                        state.e = 86400;
                    }
                    state.e += Helpers.f15566a.nextInt(31);
                    state.e *= 1000;
                } else {
                    state.e = 0;
                }
            } catch (NumberFormatException unused) {
            }
        }
        throw new StopRequest(194, "got 503 Service Unavailable, will retry later");
    }

    private Response a(State state, Call call) throws StopRequest {
        try {
            return call.execute();
        } catch (IllegalArgumentException e) {
            throw new StopRequest(Downloads.STATUS_HTTP_DATA_ERROR, "while trying to execute request: " + e.toString(), e);
        } catch (IOException e2) {
            b();
            int f = f(state);
            throw new StopRequest(f, "while trying to execute request: " + e2.toString(), e2);
        }
    }

    private int f(State state) {
        if (!Helpers.a(this.c)) {
            return 195;
        }
        if (this.b.r < 5) {
            state.d = true;
            return 194;
        }
        Log.w(Constants.f15548a, "reached max retries for " + this.b.h);
        return Downloads.STATUS_HTTP_DATA_ERROR;
    }

    private void d(State state, InnerState innerState) throws StopRequest {
        if (!TextUtils.isEmpty(state.f15564a)) {
            if (Helpers.b(state.f15564a)) {
                File file = new File(state.f15564a);
                if (file.exists()) {
                    long length = file.length();
                    if (length == 0) {
                        file.delete();
                        state.f15564a = null;
                    } else if (this.b.C != null || this.b.j) {
                        try {
                            state.b = new FileOutputStream(state.f15564a, true);
                            innerState.f15563a = (int) length;
                            if (this.b.A != -1) {
                                innerState.d = Long.toString(this.b.A);
                            }
                            innerState.b = this.b.C;
                            innerState.c = true;
                        } catch (FileNotFoundException e) {
                            throw new StopRequest(Downloads.STATUS_FILE_ERROR, "while opening destination for resuming: " + e.toString(), e);
                        }
                    } else {
                        file.delete();
                        throw new StopRequest(Downloads.STATUS_CANNOT_RESUME, "Trying to resume a download that can't be resumed");
                    }
                }
            } else {
                throw new StopRequest(Downloads.STATUS_FILE_ERROR, "found invalid internal destination filename");
            }
        }
        if (state.b != null && this.b.n == 0) {
            d(state);
        }
    }

    private void a(InnerState innerState, Request.Builder builder) {
        for (Pair next : this.b.a()) {
            builder.addHeader((String) next.first, (String) next.second);
        }
        if (innerState.c) {
            if (innerState.b != null) {
                builder.addHeader(HttpHeaders.IF_MATCH, innerState.b);
            }
            builder.addHeader("Range", "bytes=" + innerState.f15563a + "-");
        }
    }

    private void a(int i, boolean z, int i2, boolean z2, String str, String str2, String str3) {
        b(i, z, i2, z2, str, str2, str3);
        if (Downloads.isStatusCompleted(i)) {
            this.b.b();
        }
    }

    private void b(int i, boolean z, int i2, boolean z2, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Integer.valueOf(i));
        contentValues.put(Downloads._DATA, str);
        if (str2 != null) {
            contentValues.put("uri", str2);
        }
        contentValues.put(Downloads.COLUMN_MIME_TYPE, str3);
        contentValues.put(Downloads.COLUMN_LAST_MODIFICATION, Long.valueOf(this.c.a()));
        contentValues.put("method", Integer.valueOf(i2));
        if (!z) {
            contentValues.put(Constants.g, 0);
        } else if (z2) {
            contentValues.put(Constants.g, 1);
        } else {
            contentValues.put(Constants.g, Integer.valueOf(this.b.r + 1));
        }
        this.f15562a.getContentResolver().update(this.b.f(), contentValues, (String) null, (String[]) null);
    }

    /* access modifiers changed from: private */
    public static String b(String str) {
        try {
            String lowerCase = str.trim().toLowerCase(Locale.ENGLISH);
            int indexOf = lowerCase.indexOf(59);
            return indexOf != -1 ? lowerCase.substring(0, indexOf) : lowerCase;
        } catch (NullPointerException unused) {
            return null;
        }
    }
}
