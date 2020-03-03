package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.os.AsyncTask;
import java.net.URL;
import java.net.URLConnection;
import org.aspectj.runtime.internal.AroundClosure;

public class FileDownloadAndDecryptTask extends AsyncTask<String, Integer, Integer> {
    private static final int CODE_CONTENT_ZERO = -1;
    private static final int CODE_EXCEPTION = -3;
    private static final int CODE_FILE_NOT_EXIST = -2;
    private static final int CODE_SUCCESS = 101;
    private static final String TAG = "FileDownloadAndDecryptTask";
    private IFileDownloadCallback callback;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return FileDownloadAndDecryptTask.openConnection_aroundBody0((FileDownloadAndDecryptTask) objArr2[0], (URL) objArr2[1]);
        }
    }

    public interface IFileDownloadCallback {
        void onFailure(int i);

        void onProgress(int i);

        void onSuccess();
    }

    public FileDownloadAndDecryptTask(IFileDownloadCallback iFileDownloadCallback) {
        this.callback = iFileDownloadCallback;
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.io.File] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v6, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8, types: [java.io.File] */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008d, code lost:
        r14 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e2, code lost:
        r1 = r0;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e6, code lost:
        r8 = r2;
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0103, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0104, code lost:
        r14 = r17;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c8 A[SYNTHETIC, Splitter:B:40:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00dd A[SYNTHETIC, Splitter:B:48:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e1 A[ExcHandler: all (r0v13 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 r4 
      PHI: (r2v5 ?) = (r2v0 ?), (r2v13 ?), (r2v14 ?) binds: [B:24:0x00a2, B:48:0x00dd, B:49:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v9 java.io.File) = (r4v10 java.io.File), (r4v10 java.io.File), (r4v13 java.io.File) binds: [B:48:0x00dd, B:49:?, B:24:0x00a2] A[DONT_GENERATE, DONT_INLINE], Splitter:B:24:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00eb A[SYNTHETIC, Splitter:B:55:0x00eb] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0103 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0002] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0134 A[SYNTHETIC, Splitter:B:77:0x0134] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0155 A[SYNTHETIC, Splitter:B:92:0x0155] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0160  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Integer doInBackground(java.lang.String... r18) {
        /*
            r17 = this;
            r2 = 0
            r3 = -3
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            r5 = 0
            r6 = r18[r5]     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            r4.<init>(r6)     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r6 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            java.net.URLConnection r4 = r6.b(r4)     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            java.lang.String r6 = "GET"
            r4.setRequestMethod(r6)     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            r4.setUseCaches(r5)     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            java.lang.String r6 = "Cookie"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            r7.<init>()     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            java.lang.String r8 = "yetAnotherServiceToken="
            r7.append(r8)     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils r8 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getInstance()     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r8 = r8.getTokenInfo()     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            java.lang.String r8 = r8.c     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            r7.append(r8)     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            r4.setRequestProperty(r6, r7)     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            int r6 = r4.getResponseCode()     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            java.io.InputStream r7 = r4.getInputStream()     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            int r4 = r4.getContentLength()     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            r8 = -1
            if (r4 <= 0) goto L_0x00d8
            byte[] r9 = new byte[r4]     // Catch:{ Exception -> 0x0109, all -> 0x0103 }
            r10 = 0
            r11 = 0
        L_0x004f:
            r12 = 1
            if (r10 >= r4) goto L_0x0091
            int r13 = r4 - r10
            int r13 = r7.read(r9, r10, r13)     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            if (r13 == r8) goto L_0x0091
            int r10 = r10 + r13
            float r13 = (float) r10     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            float r14 = (float) r4     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            float r13 = r13 / r14
            r14 = 1120403456(0x42c80000, float:100.0)
            float r13 = r13 * r14
            int r13 = (int) r13     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            java.lang.String r14 = "FileDownloadAndDecryptTask"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            r15.<init>()     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            java.lang.String r1 = "progress="
            r15.append(r1)     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            r15.append(r13)     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            java.lang.String r1 = r15.toString()     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            com.xiaomi.smarthome.framework.log.LogUtil.c(r14, r1)     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            if (r13 <= r11) goto L_0x008a
            java.lang.Integer[] r1 = new java.lang.Integer[r12]     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            r1[r5] = r11     // Catch:{ Exception -> 0x008d, all -> 0x0103 }
            r14 = r17
            r14.publishProgress(r1)     // Catch:{ Exception -> 0x008f, all -> 0x00c4 }
            r11 = r13
            goto L_0x004f
        L_0x008a:
            r14 = r17
            goto L_0x004f
        L_0x008d:
            r14 = r17
        L_0x008f:
            r8 = r2
            goto L_0x00c6
        L_0x0091:
            r14 = r17
            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils r1 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils.getInstance()     // Catch:{ Exception -> 0x008f, all -> 0x00c4 }
            byte[] r1 = r1.decrypt(r9)     // Catch:{ Exception -> 0x008f, all -> 0x00c4 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x008f, all -> 0x00c4 }
            r0 = r18[r12]     // Catch:{ Exception -> 0x008f, all -> 0x00c4 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x008f, all -> 0x00c4 }
            boolean r0 = r4.exists()     // Catch:{ Exception -> 0x00c1, all -> 0x00e1 }
            if (r0 == 0) goto L_0x00ab
            r4.deleteOnExit()     // Catch:{ Exception -> 0x00c1, all -> 0x00e1 }
        L_0x00ab:
            r4.createNewFile()     // Catch:{ Exception -> 0x00c1, all -> 0x00e1 }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00c1, all -> 0x00e1 }
            r8.<init>(r4)     // Catch:{ Exception -> 0x00c1, all -> 0x00e1 }
            int r0 = r1.length     // Catch:{ Exception -> 0x00c2, all -> 0x00bd }
            r8.write(r1, r5, r0)     // Catch:{ Exception -> 0x00c2, all -> 0x00bd }
            r8.flush()     // Catch:{ Exception -> 0x00c2, all -> 0x00bd }
            r2 = r8
            r8 = r6
            goto L_0x00db
        L_0x00bd:
            r0 = move-exception
            r1 = r0
            goto L_0x0152
        L_0x00c1:
            r8 = r2
        L_0x00c2:
            r2 = r4
            goto L_0x00c6
        L_0x00c4:
            r0 = move-exception
            goto L_0x0106
        L_0x00c6:
            if (r2 == 0) goto L_0x00d4
            boolean r0 = r2.exists()     // Catch:{ Exception -> 0x00d2 }
            if (r0 == 0) goto L_0x00d4
            r2.delete()     // Catch:{ Exception -> 0x00d2 }
            goto L_0x00d4
        L_0x00d2:
            r0 = move-exception
            goto L_0x010d
        L_0x00d4:
            r4 = r2
            r2 = r8
            r8 = -3
            goto L_0x00db
        L_0x00d8:
            r14 = r17
            r4 = r2
        L_0x00db:
            if (r7 == 0) goto L_0x00e9
            r7.close()     // Catch:{ Exception -> 0x00e5, all -> 0x00e1 }
            goto L_0x00e9
        L_0x00e1:
            r0 = move-exception
            r1 = r0
            goto L_0x0153
        L_0x00e5:
            r0 = move-exception
            r8 = r2
            r2 = r4
            goto L_0x010d
        L_0x00e9:
            if (r2 == 0) goto L_0x00f6
            r2.close()     // Catch:{ Exception -> 0x00ef }
            goto L_0x00f6
        L_0x00ef:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
            r1 = -3
            goto L_0x00f7
        L_0x00f6:
            r1 = r8
        L_0x00f7:
            if (r4 == 0) goto L_0x0100
            boolean r0 = r4.exists()
            if (r0 == 0) goto L_0x0100
            goto L_0x0145
        L_0x0100:
            r16 = r1
            goto L_0x014a
        L_0x0103:
            r0 = move-exception
            r14 = r17
        L_0x0106:
            r1 = r0
            r4 = r2
            goto L_0x0153
        L_0x0109:
            r0 = move-exception
            r14 = r17
            r8 = r2
        L_0x010d:
            java.lang.String r1 = "FileDownloadAndDecryptTask"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r4.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r5 = "error:"
            r4.append(r5)     // Catch:{ all -> 0x014f }
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x014f }
            r4.append(r0)     // Catch:{ all -> 0x014f }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x014f }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)     // Catch:{ all -> 0x014f }
            if (r2 == 0) goto L_0x0132
            boolean r0 = r2.exists()     // Catch:{ all -> 0x014f }
            if (r0 == 0) goto L_0x0132
            r2.delete()     // Catch:{ all -> 0x014f }
        L_0x0132:
            if (r8 == 0) goto L_0x013d
            r8.close()     // Catch:{ Exception -> 0x0138 }
            goto L_0x013d
        L_0x0138:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x013d:
            if (r2 == 0) goto L_0x0148
            boolean r0 = r2.exists()
            if (r0 == 0) goto L_0x0148
        L_0x0145:
            r16 = 101(0x65, float:1.42E-43)
            goto L_0x014a
        L_0x0148:
            r16 = -3
        L_0x014a:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r16)
            return r0
        L_0x014f:
            r0 = move-exception
            r1 = r0
            r4 = r2
        L_0x0152:
            r2 = r8
        L_0x0153:
            if (r2 == 0) goto L_0x015e
            r2.close()     // Catch:{ Exception -> 0x0159 }
            goto L_0x015e
        L_0x0159:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x015e:
            if (r4 == 0) goto L_0x0164
            boolean r0 = r4.exists()
        L_0x0164:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.FileDownloadAndDecryptTask.doInBackground(java.lang.String[]):java.lang.Integer");
    }

    static final URLConnection openConnection_aroundBody0(FileDownloadAndDecryptTask fileDownloadAndDecryptTask, URL url) {
        return url.openConnection();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Integer num) {
        super.onPostExecute(num);
        if (this.callback == null) {
            return;
        }
        if (num.intValue() == 101) {
            this.callback.onSuccess();
        } else {
            this.callback.onFailure(num.intValue());
        }
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(Integer... numArr) {
        super.onProgressUpdate(numArr);
        if (this.callback != null) {
            this.callback.onProgress(numArr[0].intValue());
        }
    }
}
