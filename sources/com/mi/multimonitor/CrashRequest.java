package com.mi.multimonitor;

import android.content.Context;
import android.text.TextUtils;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class CrashRequest implements Request {
    Context mContext;
    CrashReportBean mCrashReportBean;
    DiskCache mDiskCache;
    Thread mOccurThread;
    Throwable mThrowable;

    CrashRequest(Context context, Thread thread, Throwable th, CrashReportBean crashReportBean) {
        this.mContext = context;
        this.mCrashReportBean = crashReportBean;
        this.mOccurThread = thread;
        this.mThrowable = th;
        this.mDiskCache = new DiskCache(context);
    }

    private void execute() {
        new CrashPostRunnable().run();
    }

    public String getBody() {
        return this.mCrashReportBean == null ? "" : JsonMaker.json(this.mCrashReportBean).toString();
    }

    public String getUrl() {
        return getPostUrl();
    }

    /* access modifiers changed from: private */
    public void postCrash(HttpURLConnection httpURLConnection) throws IOException {
        PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
        printWriter.write(getBody());
        printWriter.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                printWriter.close();
                bufferedReader.close();
                checkResult(sb.toString());
                return;
            }
        }
    }

    private void checkResult(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (new JSONObject(str).getInt(Request.RESULT_CODE_KEY) == 0) {
                    this.mDiskCache.clear();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public HttpURLConnection getHttpURLConnection() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(getUrl()).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);
        return httpURLConnection;
    }

    private String getPostUrl() {
        return RemoteDataUploadManager.b;
    }

    public void run() {
        execute();
    }

    private class CrashPostRunnable implements Runnable {
        private CrashPostRunnable() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x002d  */
        /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
                com.mi.multimonitor.CrashRequest r0 = com.mi.multimonitor.CrashRequest.this
                com.mi.multimonitor.DiskCache r0 = r0.mDiskCache
                com.mi.multimonitor.CrashRequest r1 = com.mi.multimonitor.CrashRequest.this
                java.lang.String r1 = r1.getBody()
                r0.saveCrash(r1)
                r0 = 0
                com.mi.multimonitor.CrashRequest r1 = com.mi.multimonitor.CrashRequest.this     // Catch:{ MalformedURLException -> 0x0037, IOException -> 0x0034, Throwable -> 0x0031, all -> 0x0027 }
                java.net.HttpURLConnection r1 = r1.getHttpURLConnection()     // Catch:{ MalformedURLException -> 0x0037, IOException -> 0x0034, Throwable -> 0x0031, all -> 0x0027 }
                com.mi.multimonitor.CrashRequest r0 = com.mi.multimonitor.CrashRequest.this     // Catch:{ MalformedURLException -> 0x0025, IOException -> 0x0023, Throwable -> 0x0021, all -> 0x001f }
                r0.postCrash(r1)     // Catch:{ MalformedURLException -> 0x0025, IOException -> 0x0023, Throwable -> 0x0021, all -> 0x001f }
                if (r1 == 0) goto L_0x003c
                r1.disconnect()
                goto L_0x003c
            L_0x001f:
                r0 = move-exception
                goto L_0x002b
            L_0x0021:
                r0 = r1
                goto L_0x0031
            L_0x0023:
                r0 = r1
                goto L_0x0034
            L_0x0025:
                r0 = r1
                goto L_0x0037
            L_0x0027:
                r1 = move-exception
                r2 = r1
                r1 = r0
                r0 = r2
            L_0x002b:
                if (r1 == 0) goto L_0x0030
                r1.disconnect()
            L_0x0030:
                throw r0
            L_0x0031:
                if (r0 == 0) goto L_0x003c
                goto L_0x0039
            L_0x0034:
                if (r0 == 0) goto L_0x003c
                goto L_0x0039
            L_0x0037:
                if (r0 == 0) goto L_0x003c
            L_0x0039:
                r0.disconnect()
            L_0x003c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.multimonitor.CrashRequest.CrashPostRunnable.run():void");
        }
    }
}
