package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class HttpUrlFetcher implements DataFetcher<InputStream> {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final HttpUrlConnectionFactory f4842a = new DefaultHttpUrlConnectionFactory();
    private static final String b = "HttpUrlFetcher";
    private static final int c = 5;
    private static final int d = -1;
    private final GlideUrl e;
    private final int f;
    private final HttpUrlConnectionFactory g;
    private HttpURLConnection h;
    private InputStream i;
    private volatile boolean j;

    interface HttpUrlConnectionFactory {
        HttpURLConnection a(URL url) throws IOException;
    }

    public HttpUrlFetcher(GlideUrl glideUrl, int i2) {
        this(glideUrl, i2, f4842a);
    }

    @VisibleForTesting
    HttpUrlFetcher(GlideUrl glideUrl, int i2, HttpUrlConnectionFactory httpUrlConnectionFactory) {
        this.e = glideUrl;
        this.f = i2;
        this.g = httpUrlConnectionFactory;
    }

    public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super InputStream> dataCallback) {
        StringBuilder sb;
        String str;
        long a2 = LogTime.a();
        try {
            dataCallback.a(a(this.e.a(), 0, (URL) null, this.e.c()));
            if (Log.isLoggable(b, 2)) {
                str = b;
                sb = new StringBuilder();
                sb.append("Finished http url fetcher fetch in ");
                sb.append(LogTime.a(a2));
                Log.v(str, sb.toString());
            }
        } catch (IOException e2) {
            if (Log.isLoggable(b, 3)) {
                Log.d(b, "Failed to load data for url", e2);
            }
            dataCallback.a((Exception) e2);
            if (Log.isLoggable(b, 2)) {
                str = b;
                sb = new StringBuilder();
            }
        } catch (Throwable th) {
            if (Log.isLoggable(b, 2)) {
                Log.v(b, "Finished http url fetcher fetch in " + LogTime.a(a2));
            }
            throw th;
        }
    }

    private InputStream a(URL url, int i2, URL url2, Map<String, String> map) throws IOException {
        if (i2 < 5) {
            if (url2 != null) {
                try {
                    if (url.toURI().equals(url2.toURI())) {
                        throw new HttpException("In re-direct loop");
                    }
                } catch (URISyntaxException unused) {
                }
            }
            this.h = this.g.a(url);
            for (Map.Entry next : map.entrySet()) {
                this.h.addRequestProperty((String) next.getKey(), (String) next.getValue());
            }
            this.h.setConnectTimeout(this.f);
            this.h.setReadTimeout(this.f);
            this.h.setUseCaches(false);
            this.h.setDoInput(true);
            this.h.setInstanceFollowRedirects(false);
            this.h.connect();
            this.i = this.h.getInputStream();
            if (this.j) {
                return null;
            }
            int responseCode = this.h.getResponseCode();
            if (a(responseCode)) {
                return a(this.h);
            }
            if (b(responseCode)) {
                String headerField = this.h.getHeaderField("Location");
                if (!TextUtils.isEmpty(headerField)) {
                    URL url3 = new URL(url, headerField);
                    b();
                    return a(url3, i2 + 1, url, map);
                }
                throw new HttpException("Received empty or null redirect url");
            } else if (responseCode == -1) {
                throw new HttpException(responseCode);
            } else {
                throw new HttpException(this.h.getResponseMessage(), responseCode);
            }
        } else {
            throw new HttpException("Too many (> 5) redirects!");
        }
    }

    private static boolean a(int i2) {
        return i2 / 100 == 2;
    }

    private static boolean b(int i2) {
        return i2 / 100 == 3;
    }

    private InputStream a(HttpURLConnection httpURLConnection) throws IOException {
        if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
            this.i = ContentLengthInputStream.a(httpURLConnection.getInputStream(), (long) httpURLConnection.getContentLength());
        } else {
            if (Log.isLoggable(b, 3)) {
                Log.d(b, "Got non empty content encoding: " + httpURLConnection.getContentEncoding());
            }
            this.i = httpURLConnection.getInputStream();
        }
        return this.i;
    }

    public void b() {
        if (this.i != null) {
            try {
                this.i.close();
            } catch (IOException unused) {
            }
        }
        if (this.h != null) {
            this.h.disconnect();
        }
        this.h = null;
    }

    public void c() {
        this.j = true;
    }

    @NonNull
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @NonNull
    public DataSource d() {
        return DataSource.REMOTE;
    }

    private static class DefaultHttpUrlConnectionFactory implements HttpUrlConnectionFactory {
        DefaultHttpUrlConnectionFactory() {
        }

        public HttpURLConnection a(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }
}
