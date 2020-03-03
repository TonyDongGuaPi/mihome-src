package com.mi.multimonitor;

import android.text.TextUtils;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class CrashCacheRequest implements Request {
    private DiskCache mDiskCache;

    public CrashCacheRequest(DiskCache diskCache, File file) {
        this.mDiskCache = diskCache;
    }

    private void execute() {
        new CrashPostRunnable().run();
    }

    public String getBody() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.mDiskCache.getCrashCacheFile()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    sb.toString();
                    return sb.toString();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return sb.toString();
        } catch (IOException e2) {
            e2.printStackTrace();
            return sb.toString();
        } catch (Throwable unused) {
            return sb.toString();
        }
    }

    public String getUrl() {
        return getPostUrl();
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

    private String getPostUrl() {
        return RemoteDataUploadManager.b;
    }

    public void run() {
        execute();
    }

    private class CrashPostRunnable implements Runnable {
        private CrashPostRunnable() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0020  */
        /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
                r0 = 0
                com.mi.multimonitor.CrashCacheRequest r1 = com.mi.multimonitor.CrashCacheRequest.this     // Catch:{ MalformedURLException -> 0x002a, IOException -> 0x0027, Throwable -> 0x0024, all -> 0x001a }
                java.net.HttpURLConnection r1 = r1.getHttpURLConnection()     // Catch:{ MalformedURLException -> 0x002a, IOException -> 0x0027, Throwable -> 0x0024, all -> 0x001a }
                com.mi.multimonitor.CrashCacheRequest r0 = com.mi.multimonitor.CrashCacheRequest.this     // Catch:{ MalformedURLException -> 0x0018, IOException -> 0x0016, Throwable -> 0x0014, all -> 0x0012 }
                r0.postCrash(r1)     // Catch:{ MalformedURLException -> 0x0018, IOException -> 0x0016, Throwable -> 0x0014, all -> 0x0012 }
                if (r1 == 0) goto L_0x002f
                r1.disconnect()
                goto L_0x002f
            L_0x0012:
                r0 = move-exception
                goto L_0x001e
            L_0x0014:
                r0 = r1
                goto L_0x0024
            L_0x0016:
                r0 = r1
                goto L_0x0027
            L_0x0018:
                r0 = r1
                goto L_0x002a
            L_0x001a:
                r1 = move-exception
                r2 = r1
                r1 = r0
                r0 = r2
            L_0x001e:
                if (r1 == 0) goto L_0x0023
                r1.disconnect()
            L_0x0023:
                throw r0
            L_0x0024:
                if (r0 == 0) goto L_0x002f
                goto L_0x002c
            L_0x0027:
                if (r0 == 0) goto L_0x002f
                goto L_0x002c
            L_0x002a:
                if (r0 == 0) goto L_0x002f
            L_0x002c:
                r0.disconnect()
            L_0x002f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.multimonitor.CrashCacheRequest.CrashPostRunnable.run():void");
        }
    }
}
