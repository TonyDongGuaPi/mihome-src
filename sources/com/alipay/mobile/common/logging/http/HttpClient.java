package com.alipay.mobile.common.logging.http;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.http.AndroidHttpClient;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.util.LoggingSPCache;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.alipay.mobile.common.logging.util.NetUtil;
import com.alipay.security.mobile.module.http.constant.a;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.youpin.network.annotation.ContentType;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpClient {

    /* renamed from: a  reason: collision with root package name */
    private static AndroidHttpClient f960a;
    private Context b;
    private String c;
    private HttpRequest d;
    private HttpResponse e;

    public HttpClient(String str, Context context) {
        this.b = context;
        this.c = str;
    }

    public HttpRequest getRequest() {
        return this.d;
    }

    public HttpResponse getResponse() {
        return this.e;
    }

    public int getResponseCode() {
        if (this.e == null) {
            return -1;
        }
        try {
            StatusLine statusLine = this.e.getStatusLine();
            if (statusLine != null) {
                return statusLine.getStatusCode();
            }
            return -1;
        } catch (Throwable th) {
            Log.w("LoggingHttpClient", th);
            return -1;
        }
    }

    public String getResponseString() {
        if (this.e == null) {
            return null;
        }
        try {
            HttpEntity entity = this.e.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            return null;
        } catch (Throwable th) {
            Log.w("LoggingHttpClient", th);
            return null;
        }
    }

    public URL getURL() {
        if (TextUtils.isEmpty(this.c)) {
            return null;
        }
        try {
            return new URL(this.c);
        } catch (Throwable th) {
            Log.w("LoggingHttpClient", th);
            return null;
        }
    }

    public HttpHost getUrlHost() {
        URL url = getURL();
        if (url == null) {
            return null;
        }
        String host = url.getHost();
        if (TextUtils.isEmpty(host)) {
            return null;
        }
        String protocol = url.getProtocol();
        if (TextUtils.isEmpty(protocol)) {
            return null;
        }
        try {
            return new HttpHost(host, "https".equalsIgnoreCase(protocol) ? 443 : 80, protocol);
        } catch (Throwable th) {
            Log.w("LoggingHttpClient", th);
            return null;
        }
    }

    public HttpHost getProxyHost() {
        NetworkInfo activeNetworkInfo = NetUtil.getActiveNetworkInfo(this.b);
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable() || activeNetworkInfo.getType() != 0) {
            return null;
        }
        try {
            String defaultHost = Proxy.getDefaultHost();
            if (!TextUtils.isEmpty(defaultHost)) {
                return new HttpHost(defaultHost, Proxy.getDefaultPort());
            }
            return null;
        } catch (Throwable th) {
            Log.w("LoggingHttpClient", th);
            return null;
        }
    }

    public void closeStreamForNextExecute() {
        InputStream content;
        if (this.d != null) {
            try {
                if (this.d instanceof HttpGet) {
                    ((HttpGet) this.d).abort();
                } else if (this.d instanceof HttpPost) {
                    ((HttpPost) this.d).abort();
                }
            } catch (Throwable unused) {
            }
            this.d = null;
        }
        if (this.e != null) {
            try {
                HttpEntity entity = this.e.getEntity();
                if (!(entity == null || (content = entity.getContent()) == null)) {
                    content.close();
                }
            } catch (Throwable unused2) {
            }
            this.e = null;
        }
    }

    public HttpResponse synchronousRequestByGET(Map<String, String> map) {
        String str;
        closeStreamForNextExecute();
        try {
            String formatParamStringForGET = NetUtil.formatParamStringForGET(map);
            if (TextUtils.isEmpty(formatParamStringForGET)) {
                str = this.c;
            } else {
                str = this.c + Operators.CONDITION_IF + formatParamStringForGET;
            }
            this.d = new HttpGet(str);
            this.d.addHeader("Content-type", ContentType.XML);
            a();
            this.e = f960a.execute(getUrlHost(), this.d);
            return this.e;
        } catch (Throwable th) {
            closeStreamForNextExecute();
            throw new IllegalStateException(th);
        }
    }

    public HttpResponse synchronousRequestForLog(String str, String str2) {
        closeStreamForNextExecute();
        try {
            if (TextUtils.isEmpty(str)) {
                this.d = new HttpGet(this.c);
            } else {
                byte[] gzipDataByString = LoggingUtil.gzipDataByString(str);
                a(str2, str.length(), gzipDataByString.length);
                HttpPost httpPost = new HttpPost(this.c);
                httpPost.setEntity(new ByteArrayEntity(gzipDataByString));
                httpPost.addHeader("Content-Encoding", "Gzip");
                this.d = httpPost;
            }
            this.d.addHeader("Content-type", ContentType.XML);
            this.d.addHeader("ProcessName", LoggerFactory.getProcessInfo().getProcessAlias());
            a();
            this.e = f960a.execute(getUrlHost(), this.d);
            return this.e;
        } catch (Throwable th) {
            closeStreamForNextExecute();
            throw new IllegalStateException(th);
        }
    }

    private void a(String str, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis() / TimeUnit.DAYS.toMillis(1);
        LoggingSPCache instance = LoggingSPCache.getInstance();
        if (currentTimeMillis != instance.getLong(LoggingSPCache.KEY_CUR_UPLOAD_DAY + str, 0)) {
            LoggingSPCache instance2 = LoggingSPCache.getInstance();
            instance2.putLong(LoggingSPCache.KEY_CUR_UPLOAD_DAY + str, currentTimeMillis);
            LoggingSPCache instance3 = LoggingSPCache.getInstance();
            instance3.putInt(LoggingSPCache.KEY_CUR_UPLOAD_TRAFIC + str, i2);
            return;
        }
        LoggingSPCache instance4 = LoggingSPCache.getInstance();
        int i3 = instance4.getInt(LoggingSPCache.KEY_CUR_UPLOAD_TRAFIC + str, 0);
        int i4 = i3 + i2;
        TraceLogger traceLogger = LoggerFactory.getTraceLogger();
        traceLogger.info("LoggingHttpClient", str + " trafic(" + i + "->" + i2 + ") total=" + i4);
        if (i3 <= 2097152000) {
            LoggingSPCache instance5 = LoggingSPCache.getInstance();
            instance5.putInt(LoggingSPCache.KEY_CUR_UPLOAD_TRAFIC + str, i4);
            return;
        }
        throw new IllegalStateException(str + " upload trafic limited:" + i3);
    }

    private void a() {
        if (f960a == null) {
            synchronized (HttpClient.class) {
                if (f960a == null) {
                    try {
                        f960a = AndroidHttpClient.newInstance("alipay", this.b);
                        HttpParams params = f960a.getParams();
                        if (params != null) {
                            params.setParameter("http.connection.timeout", 30000);
                            params.setParameter("http.socket.timeout", Integer.valueOf(a.f1173a));
                        }
                    } catch (Throwable th) {
                        Log.w("LoggingHttpClient", th);
                    }
                }
            }
        }
        if (f960a != null) {
            try {
                HttpParams params2 = f960a.getParams();
                if (params2 != null) {
                    params2.setParameter(ConnRoutePNames.DEFAULT_PROXY, getProxyHost());
                }
            } catch (Throwable th2) {
                Log.w("LoggingHttpClient", th2);
            }
        }
    }
}
