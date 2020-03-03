package com.xiaomi.mishopsdk.utils;

import android.os.Handler;
import com.mishopsdk.volley.AuthFailureError;
import com.mishopsdk.volley.toolbox.BaseRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileDownloadQuest extends BaseRequest<File> {
    private static final Object sDecodeLock = new Object();
    private String mFilePath;
    private Handler mHandler;
    private Map<String, String> vParams = new HashMap();

    public FileDownloadQuest(Builder<?> builder) {
        super(builder);
        this.mFilePath = builder.mFilePath;
        this.mHandler = builder.mHandler;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getPostParams() throws AuthFailureError {
        Map<String, String> postParams = super.getPostParams();
        if (postParams != null) {
            postParams.putAll(this.vParams);
        }
        return postParams;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008e, code lost:
        throw new com.mishopsdk.volley.ParseError(r10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.mishopsdk.volley.Response<java.io.File> parseNetworkResponse(com.mishopsdk.volley.NetworkResponse r10) throws com.mishopsdk.volley.VolleyError {
        /*
            r9 = this;
            java.lang.Object r0 = sDecodeLock
            monitor-enter(r0)
            byte[] r1 = r10.data     // Catch:{ all -> 0x008f }
            boolean r2 = r9.mGzipEnabled     // Catch:{ all -> 0x008f }
            if (r2 == 0) goto L_0x0019
            boolean r2 = r9.isGzipped(r10)     // Catch:{ all -> 0x008f }
            if (r2 == 0) goto L_0x0019
            byte[] r2 = r9.decompressResponse(r1)     // Catch:{ IOException -> 0x0015 }
            r1 = r2
            goto L_0x0019
        L_0x0015:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x008f }
        L_0x0019:
            r2 = 0
            if (r1 == 0) goto L_0x0088
            int r3 = r1.length     // Catch:{ all -> 0x008f }
            if (r3 == 0) goto L_0x0088
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException | IOException -> 0x0075 }
            java.lang.String r4 = r9.mFilePath     // Catch:{ FileNotFoundException | IOException -> 0x0075 }
            r3.<init>(r4)     // Catch:{ FileNotFoundException | IOException -> 0x0075 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            android.os.Handler r4 = r9.mHandler     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            if (r4 == 0) goto L_0x006b
            r4 = 0
            int r5 = r1.length     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
        L_0x0031:
            if (r4 >= r5) goto L_0x006e
            int r6 = r5 - r4
            r7 = 1024(0x400, float:1.435E-42)
            if (r6 <= r7) goto L_0x003b
            r6 = 1024(0x400, float:1.435E-42)
        L_0x003b:
            r2.write(r1, r4, r6)     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            int r4 = r4 + 1024
            android.os.Message r6 = new android.os.Message     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            r6.<init>()     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            r7.<init>()     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            r7.append(r4)     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            java.lang.String r8 = "k / "
            r7.append(r8)     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            r7.append(r5)     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            java.lang.String r8 = "k"
            r7.append(r8)     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            java.lang.String r7 = r7.toString()     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            r6.obj = r7     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            int r7 = r4 * 100
            int r7 = r7 / r5
            r6.arg1 = r7     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            android.os.Handler r7 = r9.mHandler     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            r7.sendMessage(r6)     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            goto L_0x0031
        L_0x006b:
            r2.write(r1)     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
        L_0x006e:
            r2.flush()     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            r2.close()     // Catch:{ FileNotFoundException | IOException -> 0x0076 }
            goto L_0x0076
        L_0x0075:
            r3 = r2
        L_0x0076:
            if (r3 == 0) goto L_0x0082
            com.mishopsdk.volley.Cache$Entry r10 = com.mishopsdk.volley.toolbox.HttpHeaderParser.parseCacheHeaders(r10)     // Catch:{ all -> 0x008f }
            com.mishopsdk.volley.Response r10 = com.mishopsdk.volley.Response.success(r3, r10)     // Catch:{ all -> 0x008f }
            monitor-exit(r0)     // Catch:{ all -> 0x008f }
            return r10
        L_0x0082:
            com.mishopsdk.volley.ParseError r1 = new com.mishopsdk.volley.ParseError     // Catch:{ all -> 0x008f }
            r1.<init>((com.mishopsdk.volley.NetworkResponse) r10)     // Catch:{ all -> 0x008f }
            throw r1     // Catch:{ all -> 0x008f }
        L_0x0088:
            monitor-exit(r0)     // Catch:{ all -> 0x008f }
            com.mishopsdk.volley.ParseError r0 = new com.mishopsdk.volley.ParseError
            r0.<init>((com.mishopsdk.volley.NetworkResponse) r10)
            throw r0
        L_0x008f:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008f }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.utils.FileDownloadQuest.parseNetworkResponse(com.mishopsdk.volley.NetworkResponse):com.mishopsdk.volley.Response");
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> extends BaseRequest.Builder<B> {
        String mFilePath;
        Handler mHandler;

        public B setFileLocalPath(String str) {
            this.mFilePath = str;
            return (Builder) self();
        }

        public B setDownloadHander(Handler handler) {
            this.mHandler = handler;
            return (Builder) self();
        }

        public FileDownloadQuest build() {
            return new FileDownloadQuest(this);
        }
    }
}
