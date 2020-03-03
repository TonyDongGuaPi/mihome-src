package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.os.AsyncTask;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import java.net.URL;
import java.net.URLConnection;
import org.aspectj.runtime.internal.AroundClosure;

public class FileDownloadTask extends AsyncTask<String, Void, Integer> {
    private static final int CODE_FAILURE = 1002;
    private static final int CODE_FILE_NOT_EXIST = 1003;
    private static final int CODE_SUCCESS = 1001;
    private static final String TAG = "FileDownloadTask";
    private ICloudDataCallback callback;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return FileDownloadTask.openConnection_aroundBody0((FileDownloadTask) objArr2[0], (URL) objArr2[1]);
        }
    }

    public FileDownloadTask(ICloudDataCallback iCloudDataCallback) {
        this.callback = iCloudDataCallback;
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.File] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
        if (r6.exists() != false) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007c, code lost:
        r2 = 1001;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007f, code lost:
        r2 = 1003;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c6, code lost:
        if (r3.exists() == false) goto L_0x007f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bc A[SYNTHETIC, Splitter:B:40:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d6 A[SYNTHETIC, Splitter:B:52:0x00d6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Integer doInBackground(java.lang.String... r10) {
        /*
            r9 = this;
            r0 = 1003(0x3eb, float:1.406E-42)
            r1 = 1001(0x3e9, float:1.403E-42)
            r2 = 1002(0x3ea, float:1.404E-42)
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r5 = 0
            r6 = r10[r5]     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r4.<init>(r6)     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r6 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.net.URLConnection r4 = r6.b(r4)     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.lang.String r6 = "GET"
            r4.setRequestMethod(r6)     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r4.setUseCaches(r5)     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.lang.String r6 = "Cookie"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r7.<init>()     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.lang.String r8 = "yetAnotherServiceToken="
            r7.append(r8)     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils r8 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getInstance()     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r8 = r8.getTokenInfo()     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.lang.String r8 = r8.c     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r7.append(r8)     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r4.setRequestProperty(r6, r7)     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r4.getResponseCode()     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r7 = 1
            r10 = r10[r7]     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            r6.<init>(r10)     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            boolean r10 = r6.exists()     // Catch:{ Exception -> 0x008c, all -> 0x008a }
            if (r10 == 0) goto L_0x0059
            r6.delete()     // Catch:{ Exception -> 0x008c, all -> 0x008a }
        L_0x0059:
            r6.createNewFile()     // Catch:{ Exception -> 0x008c, all -> 0x008a }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x008c, all -> 0x008a }
            r10.<init>(r6)     // Catch:{ Exception -> 0x008c, all -> 0x008a }
            r3 = 2048(0x800, float:2.87E-42)
            byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x0086, all -> 0x0082 }
        L_0x0065:
            int r7 = r4.read(r3)     // Catch:{ Exception -> 0x0086, all -> 0x0082 }
            r8 = -1
            if (r7 == r8) goto L_0x0070
            r10.write(r3, r5, r7)     // Catch:{ Exception -> 0x0086, all -> 0x0082 }
            goto L_0x0065
        L_0x0070:
            r10.flush()     // Catch:{ Exception -> 0x0086, all -> 0x0082 }
            r10.close()     // Catch:{ Exception -> 0x00c0 }
            boolean r10 = r6.exists()     // Catch:{ Exception -> 0x00c0 }
            if (r10 == 0) goto L_0x007f
        L_0x007c:
            r2 = 1001(0x3e9, float:1.403E-42)
            goto L_0x00cc
        L_0x007f:
            r2 = 1003(0x3eb, float:1.406E-42)
            goto L_0x00cc
        L_0x0082:
            r0 = move-exception
            r3 = r10
            r10 = r0
            goto L_0x00d4
        L_0x0086:
            r3 = move-exception
            r4 = r10
            r10 = r3
            goto L_0x008e
        L_0x008a:
            r10 = move-exception
            goto L_0x00d4
        L_0x008c:
            r10 = move-exception
            r4 = r3
        L_0x008e:
            r3 = r6
            goto L_0x0095
        L_0x0090:
            r10 = move-exception
            r6 = r3
            goto L_0x00d4
        L_0x0093:
            r10 = move-exception
            r4 = r3
        L_0x0095:
            java.lang.String r5 = "FileDownloadTask"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d1 }
            r6.<init>()     // Catch:{ all -> 0x00d1 }
            java.lang.String r7 = "error:"
            r6.append(r7)     // Catch:{ all -> 0x00d1 }
            java.lang.String r10 = r10.getLocalizedMessage()     // Catch:{ all -> 0x00d1 }
            r6.append(r10)     // Catch:{ all -> 0x00d1 }
            java.lang.String r10 = r6.toString()     // Catch:{ all -> 0x00d1 }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r5, r10)     // Catch:{ all -> 0x00d1 }
            if (r3 == 0) goto L_0x00ba
            boolean r10 = r3.exists()     // Catch:{ all -> 0x00d1 }
            if (r10 == 0) goto L_0x00ba
            r3.delete()     // Catch:{ all -> 0x00d1 }
        L_0x00ba:
            if (r4 == 0) goto L_0x00c2
            r4.close()     // Catch:{ Exception -> 0x00c0 }
            goto L_0x00c2
        L_0x00c0:
            r10 = move-exception
            goto L_0x00c9
        L_0x00c2:
            boolean r10 = r3.exists()     // Catch:{ Exception -> 0x00c0 }
            if (r10 == 0) goto L_0x007f
            goto L_0x007c
        L_0x00c9:
            r10.printStackTrace()
        L_0x00cc:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            return r10
        L_0x00d1:
            r10 = move-exception
            r6 = r3
            r3 = r4
        L_0x00d4:
            if (r3 == 0) goto L_0x00dc
            r3.close()     // Catch:{ Exception -> 0x00da }
            goto L_0x00dc
        L_0x00da:
            r0 = move-exception
            goto L_0x00e1
        L_0x00dc:
            boolean r0 = r6.exists()     // Catch:{ Exception -> 0x00da }
            goto L_0x00e4
        L_0x00e1:
            r0.printStackTrace()
        L_0x00e4:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.FileDownloadTask.doInBackground(java.lang.String[]):java.lang.Integer");
    }

    static final URLConnection openConnection_aroundBody0(FileDownloadTask fileDownloadTask, URL url) {
        return url.openConnection();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Integer num) {
        super.onPostExecute(num);
        if (this.callback == null) {
            return;
        }
        if (num.intValue() == 1001) {
            this.callback.onCloudDataSuccess(null, (Object) null);
        } else if (num.intValue() == 1002) {
            this.callback.onCloudDataFailed(num.intValue(), "exception CODE_FAILURE");
        } else if (num.intValue() == 1003) {
            this.callback.onCloudDataFailed(num.intValue(), "CODE_FILE_NOT_EXIST");
        } else {
            this.callback.onCloudDataFailed(num.intValue(), "unknown error");
        }
    }
}
