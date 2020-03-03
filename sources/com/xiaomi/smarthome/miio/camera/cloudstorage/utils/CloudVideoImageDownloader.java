package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor;
import com.xiaomi.smarthome.library.common.network.Network;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.aspectj.runtime.internal.AroundClosure;

public class CloudVideoImageDownloader extends BaseImageDownloader {
    private static final String HTTPS_PREFIX = "https";

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return CloudVideoImageDownloader.openConnection_aroundBody0((CloudVideoImageDownloader) objArr2[0], (URL) objArr2[1]);
        }
    }

    public class AjcClosure3 extends AroundClosure {
        public AjcClosure3(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return CloudVideoImageDownloader.openConnection_aroundBody2((CloudVideoImageDownloader) objArr2[0], (URL) objArr2[1]);
        }
    }

    public class AjcClosure5 extends AroundClosure {
        public AjcClosure5(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return CloudVideoImageDownloader.openConnection_aroundBody4((CloudVideoImageDownloader) objArr2[0], (URL) objArr2[1]);
        }
    }

    public CloudVideoImageDownloader(Context context) {
        super(context);
    }

    public CloudVideoImageDownloader(Context context, int i, int i2) {
        super(context, i, i2);
    }

    public InputStream getStream(String str, Object obj) throws IOException {
        HttpURLConnection httpURLConnection;
        if (!str.startsWith("https")) {
            return super.getStream(str, obj);
        }
        URL url = new URL(str);
        int i = 0;
        if (Network.c(this.context)) {
            HttpURLConnection.setFollowRedirects(false);
            String a2 = Network.a(url);
            String host = url.getHost();
            httpURLConnection = (HttpURLConnection) TraceNetTrafficMonitor.b().b(new URL(a2));
            httpURLConnection.setRequestProperty("X-Online-Host", host);
            int responseCode = httpURLConnection.getResponseCode();
            while (responseCode >= 300 && responseCode < 400) {
                String headerField = httpURLConnection.getHeaderField("location");
                if (TextUtils.isEmpty(headerField)) {
                    break;
                }
                URL url2 = new URL(headerField);
                String a3 = Network.a(url2);
                String host2 = url2.getHost();
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) TraceNetTrafficMonitor.b().b(new URL(a3));
                httpURLConnection2.setRequestProperty("X-Online-Host", host2);
                HttpURLConnection httpURLConnection3 = httpURLConnection2;
                responseCode = httpURLConnection2.getResponseCode();
                httpURLConnection = httpURLConnection3;
            }
        } else {
            httpURLConnection = (HttpURLConnection) TraceNetTrafficMonitor.b().b(url);
            HttpURLConnection.setFollowRedirects(true);
        }
        httpURLConnection.setRequestProperty("Cookie", "yetAnotherServiceToken=" + CloudVideoNetUtils.getInstance().getTokenInfo().c);
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        int contentLength = httpURLConnection.getContentLength();
        byte[] bArr = new byte[contentLength];
        while (i < contentLength) {
            try {
                int read = inputStream.read(bArr, i, contentLength - i);
                if (read == -1) {
                    break;
                }
                i += read;
            } catch (Exception unused) {
            }
        }
        return new ByteArrayInputStream(CloudVideoCryptoUtils.getInstance().decrypt(bArr));
    }

    static final URLConnection openConnection_aroundBody0(CloudVideoImageDownloader cloudVideoImageDownloader, URL url) {
        return url.openConnection();
    }

    static final URLConnection openConnection_aroundBody2(CloudVideoImageDownloader cloudVideoImageDownloader, URL url) {
        return url.openConnection();
    }

    static final URLConnection openConnection_aroundBody4(CloudVideoImageDownloader cloudVideoImageDownloader, URL url) {
        return url.openConnection();
    }

    /* access modifiers changed from: protected */
    public InputStream getStreamFromNetwork(String str, Object obj) throws IOException {
        return super.getStreamFromNetwork(str, obj);
    }

    /* access modifiers changed from: protected */
    public boolean shouldBeProcessed(HttpURLConnection httpURLConnection) throws IOException {
        return super.shouldBeProcessed(httpURLConnection);
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection createConnection(String str, Object obj) throws IOException {
        return super.createConnection(str, obj);
    }

    /* access modifiers changed from: protected */
    public InputStream getStreamFromFile(String str, Object obj) throws IOException {
        return super.getStreamFromFile(str, obj);
    }

    /* access modifiers changed from: protected */
    public InputStream getStreamFromContent(String str, Object obj) throws FileNotFoundException {
        return super.getStreamFromContent(str, obj);
    }

    /* access modifiers changed from: protected */
    public InputStream getContactPhotoStream(Uri uri) {
        return super.getContactPhotoStream(uri);
    }

    /* access modifiers changed from: protected */
    public InputStream getStreamFromAssets(String str, Object obj) throws IOException {
        return super.getStreamFromAssets(str, obj);
    }

    /* access modifiers changed from: protected */
    public InputStream getStreamFromDrawable(String str, Object obj) {
        return super.getStreamFromDrawable(str, obj);
    }

    /* access modifiers changed from: protected */
    public InputStream getStreamFromOtherSource(String str, Object obj) throws IOException {
        return super.getStreamFromOtherSource(str, obj);
    }
}
