package com.mijia.model;

import android.content.Context;
import android.text.TextUtils;
import com.Utils.Network;
import com.Utils.VideoDecryption;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraImageLoaderEx extends BaseImageDownloader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7957a = "fds://";
    public static String b = "camera://";
    public static String c = "time:";
    private static final String d = "CameraImageLoaderEx";
    private int e = 0;
    private Context f;
    private ExecutorService g;

    static /* synthetic */ int a(CameraImageLoaderEx cameraImageLoaderEx) {
        int i = cameraImageLoaderEx.e;
        cameraImageLoaderEx.e = i + 1;
        return i;
    }

    public CameraImageLoaderEx(Context context) {
        super(context);
        this.f = context.getApplicationContext();
        this.g = Executors.newFixedThreadPool(3);
    }

    public InputStream getStream(String str, Object obj) throws IOException {
        String str2;
        HttpURLConnection httpURLConnection;
        if (str.startsWith("fds://")) {
            String str3 = null;
            int lastIndexOf = str.lastIndexOf("?");
            if (lastIndexOf < 0) {
                str2 = str.substring("fds://".length());
            } else {
                String substring = str.substring("fds://".length(), lastIndexOf);
                str3 = str.substring(lastIndexOf + 1);
                str2 = substring;
            }
            String a2 = a(str2, str3);
            if (!TextUtils.isEmpty(a2)) {
                URL url = new URL(a2);
                int i = 0;
                if (Network.c(XmPluginHostApi.instance().context())) {
                    HttpURLConnection.setFollowRedirects(false);
                    String a3 = Network.a(url);
                    String host = url.getHost();
                    httpURLConnection = (HttpURLConnection) new URL(a3).openConnection();
                    httpURLConnection.setRequestProperty("X-Online-Host", host);
                    int responseCode = httpURLConnection.getResponseCode();
                    while (responseCode >= 300 && responseCode < 400) {
                        String headerField = httpURLConnection.getHeaderField("location");
                        if (TextUtils.isEmpty(headerField)) {
                            break;
                        }
                        URL url2 = new URL(headerField);
                        String a4 = Network.a(url2);
                        String host2 = url2.getHost();
                        HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(a4).openConnection();
                        httpURLConnection2.setRequestProperty("X-Online-Host", host2);
                        HttpURLConnection httpURLConnection3 = httpURLConnection2;
                        responseCode = httpURLConnection2.getResponseCode();
                        httpURLConnection = httpURLConnection3;
                    }
                } else {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    HttpURLConnection.setFollowRedirects(true);
                }
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                if (TextUtils.isEmpty(str3)) {
                    return inputStream;
                }
                int contentLength = httpURLConnection.getContentLength();
                byte[] bArr = new byte[contentLength];
                while (i < contentLength) {
                    int read = inputStream.read(bArr, i, contentLength - i);
                    if (read == -1) {
                        break;
                    }
                    i += read;
                }
                return new ByteArrayInputStream(VideoDecryption.a(bArr, str3));
            }
            throw new IOException("not get url");
        } else if (str.startsWith(b)) {
            int indexOf = str.indexOf(b) + b.length();
            int indexOf2 = str.indexOf(c);
            return a(str.substring(indexOf, indexOf2), Long.valueOf(str.substring(indexOf2 + c.length())).longValue());
        } else if ((str.contains("processor.smartcamera") || str.contains("business.smartcamera")) && this.f != null) {
            return new ByteArrayInputStream(XmPluginHostApi.instance().sendImageDownloadRequest(this.f, str));
        } else {
            return super.getStream(str, obj);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:1|2|3|4|5|6|(2:8|9)(3:11|12|13)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0020 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0028 A[SYNTHETIC, Splitter:B:11:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.io.InputStream a(java.lang.String r13, long r14) throws java.io.IOException {
        /*
            r12 = this;
            monitor-enter(r12)
            java.util.concurrent.CountDownLatch r7 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x0030 }
            r0 = 1
            r7.<init>(r0)     // Catch:{ all -> 0x0030 }
            java.io.InputStream[] r8 = new java.io.InputStream[r0]     // Catch:{ all -> 0x0030 }
            r0 = 0
            r9 = 0
            r8[r9] = r0     // Catch:{ all -> 0x0030 }
            java.util.concurrent.ExecutorService r10 = r12.g     // Catch:{ all -> 0x0030 }
            com.mijia.model.CameraImageLoaderEx$1 r11 = new com.mijia.model.CameraImageLoaderEx$1     // Catch:{ all -> 0x0030 }
            r0 = r11
            r1 = r12
            r2 = r14
            r4 = r13
            r5 = r7
            r6 = r8
            r0.<init>(r2, r4, r5, r6)     // Catch:{ all -> 0x0030 }
            r10.execute(r11)     // Catch:{ all -> 0x0030 }
            r7.await()     // Catch:{ InterruptedException -> 0x0020 }
        L_0x0020:
            r13 = r8[r9]     // Catch:{ all -> 0x0030 }
            if (r13 == 0) goto L_0x0028
            r13 = r8[r9]     // Catch:{ all -> 0x0030 }
            monitor-exit(r12)
            return r13
        L_0x0028:
            java.io.IOException r13 = new java.io.IOException     // Catch:{ all -> 0x0030 }
            java.lang.String r14 = "download cameraSdCard thumb file error"
            r13.<init>(r14)     // Catch:{ all -> 0x0030 }
            throw r13     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.model.CameraImageLoaderEx.a(java.lang.String, long):java.io.InputStream");
    }

    private String a(final String str, String str2) throws IOException {
        final String[] strArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.g.execute(new Runnable() {
            public void run() {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("obj_name", str);
                } catch (JSONException unused) {
                }
                XmPluginHostApi.instance().callSmartHomeApi("mijia.camera.v1", "/home/getfileurl", jSONObject, new Callback<String>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        strArr[0] = str;
                        countDownLatch.countDown();
                    }

                    public void onFailure(int i, String str) {
                        strArr[0] = null;
                        countDownLatch.countDown();
                    }
                }, new Parser<String>() {
                    /* renamed from: a */
                    public String parse(String str) throws JSONException {
                        return new JSONObject(str).optString("url");
                    }
                });
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
        }
        return strArr[0];
    }
}
