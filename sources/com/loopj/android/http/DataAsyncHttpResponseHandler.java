package com.loopj.android.http;

import android.os.Message;

public abstract class DataAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "DataAsyncHttpRH";
    protected static final int PROGRESS_DATA_MESSAGE = 7;

    public static byte[] copyOfRange(byte[] bArr, int i, int i2) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
        if (i <= i2) {
            int length = bArr.length;
            if (i < 0 || i > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i, bArr2, 0, min);
            return bArr2;
        }
        throw new IllegalArgumentException();
    }

    public void onProgressData(byte[] bArr) {
        AsyncHttpClient.log.d(LOG_TAG, "onProgressData(byte[]) was not overriden, but callback was received");
    }

    public final void sendProgressDataMessage(byte[] bArr) {
        sendMessage(obtainMessage(7, new Object[]{bArr}));
    }

    /* access modifiers changed from: protected */
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 7) {
            Object[] objArr = (Object[]) message.obj;
            if (objArr == null || objArr.length < 1) {
                AsyncHttpClient.log.e(LOG_TAG, "PROGRESS_DATA_MESSAGE didn't got enough params");
                return;
            }
            try {
                onProgressData((byte[]) objArr[0]);
            } catch (Throwable th) {
                AsyncHttpClient.log.e(LOG_TAG, "custom onProgressData contains an error", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return r7.toByteArray();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getResponseData(cz.msebera.android.httpclient.HttpEntity r7) throws java.io.IOException {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x0066
            java.io.InputStream r0 = r7.getContent()
            if (r0 == 0) goto L_0x0066
            long r1 = r7.getContentLength()
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 > 0) goto L_0x005e
            r3 = 0
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 >= 0) goto L_0x001b
            r1 = 4096(0x1000, double:2.0237E-320)
        L_0x001b:
            cz.msebera.android.httpclient.util.ByteArrayBuffer r7 = new cz.msebera.android.httpclient.util.ByteArrayBuffer     // Catch:{ OutOfMemoryError -> 0x0053 }
            int r3 = (int) r1     // Catch:{ OutOfMemoryError -> 0x0053 }
            r7.<init>(r3)     // Catch:{ OutOfMemoryError -> 0x0053 }
            r3 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r3]     // Catch:{ all -> 0x004e }
        L_0x0025:
            int r4 = r0.read(r3)     // Catch:{ all -> 0x004e }
            r5 = -1
            if (r4 == r5) goto L_0x0046
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x004e }
            boolean r5 = r5.isInterrupted()     // Catch:{ all -> 0x004e }
            if (r5 != 0) goto L_0x0046
            r5 = 0
            r7.append(r3, r5, r4)     // Catch:{ all -> 0x004e }
            byte[] r4 = copyOfRange(r3, r5, r4)     // Catch:{ all -> 0x004e }
            r6.sendProgressDataMessage(r4)     // Catch:{ all -> 0x004e }
            long r4 = (long) r5     // Catch:{ all -> 0x004e }
            r6.sendProgressMessage(r4, r1)     // Catch:{ all -> 0x004e }
            goto L_0x0025
        L_0x0046:
            com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r0)     // Catch:{ OutOfMemoryError -> 0x0053 }
            byte[] r7 = r7.toByteArray()     // Catch:{ OutOfMemoryError -> 0x0053 }
            goto L_0x0067
        L_0x004e:
            r7 = move-exception
            com.loopj.android.http.AsyncHttpClient.silentCloseInputStream(r0)     // Catch:{ OutOfMemoryError -> 0x0053 }
            throw r7     // Catch:{ OutOfMemoryError -> 0x0053 }
        L_0x0053:
            java.lang.System.gc()
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r0 = "File too large to fit into available memory"
            r7.<init>(r0)
            throw r7
        L_0x005e:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "HTTP entity too large to be buffered in memory"
            r7.<init>(r0)
            throw r7
        L_0x0066:
            r7 = 0
        L_0x0067:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.DataAsyncHttpResponseHandler.getResponseData(cz.msebera.android.httpclient.HttpEntity):byte[]");
    }
}
