package com.mijia.model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.Utils.Network;
import com.Utils.VideoDecryption;
import com.mijia.app.AppConfig;
import com.mijia.camera.MijiaCameraDevice;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.tutk.IOTC.Packet;
import com.xiaomi.smarthome.camera.P2pResponse;
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

public class CameraImageLoader extends BaseImageDownloader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7951a = "fds://";
    public static final String b = "processor.smartcamera";
    public static final String c = "business.smartcamera";
    public static String d = "camera://";
    public static String e = "time:";
    private static final String f = "CameraImageLoader";
    private Context g;
    private ExecutorService h = Executors.newFixedThreadPool(3);

    public CameraImageLoader(Context context) {
        super(context);
        this.g = context.getApplicationContext();
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
        } else if (str.startsWith(d)) {
            int indexOf = str.indexOf(d) + d.length();
            int indexOf2 = str.indexOf(e);
            return a(str.substring(indexOf, indexOf2), Long.valueOf(str.substring(indexOf2 + e.length())).longValue());
        } else if ((str.contains("processor.smartcamera") || str.contains("business.smartcamera")) && this.g != null) {
            return new ByteArrayInputStream(XmPluginHostApi.instance().sendImageDownloadRequest(this.g, str));
        } else {
            return super.getStream(str, obj);
        }
    }

    private InputStream a(String str, long j) throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        InputStream[] inputStreamArr = {null};
        final long j2 = j;
        final String str2 = str;
        final CountDownLatch countDownLatch2 = countDownLatch;
        final InputStream[] inputStreamArr2 = inputStreamArr;
        this.h.execute(new Runnable() {

            /* renamed from: a  reason: collision with root package name */
            byte[] f7952a;
            int b;

            public void run() {
                byte[] intToByteArray = Packet.intToByteArray((int) (j2 / 1000), AppConfig.b());
                MijiaCameraDevice a2 = MijiaCameraDevice.a(str2);
                if (a2 == null || a2.u() == null) {
                    countDownLatch2.countDown();
                } else {
                    a2.u().sendMsg(5, 5, intToByteArray, new P2pResponse() {
                        public void onResponse(int i, byte[] bArr) {
                            if (bArr == null) {
                                countDownLatch2.countDown();
                                return;
                            }
                            Log.d(CameraImageLoader.f, "downloadFile:" + i);
                            if (AnonymousClass1.this.f7952a == null) {
                                AnonymousClass1.this.f7952a = new byte[(bArr.length + i)];
                                AnonymousClass1.this.b = 0;
                            }
                            System.arraycopy(bArr, 0, AnonymousClass1.this.f7952a, AnonymousClass1.this.b, bArr.length);
                            AnonymousClass1.this.b += bArr.length;
                            if (i == 0) {
                                inputStreamArr2[0] = new ByteArrayInputStream(AnonymousClass1.this.f7952a);
                                AnonymousClass1.this.f7952a = null;
                                countDownLatch2.countDown();
                            } else if (i < 0) {
                                countDownLatch2.countDown();
                            }
                        }

                        public void onError(int i) {
                            Log.d(CameraImageLoader.f, "downloadFile failled:" + i);
                            inputStreamArr2[0] = null;
                            AnonymousClass1.this.f7952a = null;
                            countDownLatch2.countDown();
                        }
                    });
                }
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
        }
        if (inputStreamArr[0] != null) {
            return inputStreamArr[0];
        }
        throw new IOException("download cameraSdCard thumb file error");
    }

    private String a(final String str, String str2) throws IOException {
        final String[] strArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.h.execute(new Runnable() {
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
