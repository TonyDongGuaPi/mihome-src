package com.xiaomi.mishopsdk.io.http;

import android.content.Context;
import com.mishopsdk.volley.AuthFailureError;
import com.mishopsdk.volley.Request;
import com.mishopsdk.volley.toolbox.HttpStack;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;

public class OkHttpStack implements HttpStack {
    private static final String DEFAULT_CACHE = "okhttp-cache";
    private static final long DEFAULT_CACHE_SIZE = 10485760;
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLI_SECOND = 2500;
    public static final int DEFAULT_READ_TIMEOUT_MILLI_SECOND = 2500;
    public static final int DEFAULT_WRITE_TIMEOUT_MILLI_SECOND = 2500;
    private static final String TAG = "OkHttpStack";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    protected OkHttpClient mOkHttpClient = createOkhttpClient(createCache());

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return OkHttpStack.build_aroundBody0((OkHttpStack) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    public class AjcClosure3 extends AroundClosure {
        public AjcClosure3(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return OkHttpStack.build_aroundBody2((OkHttpStack) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("OkHttpStack.java", OkHttpStack.class);
        ajc$tjp_0 = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 110);
        ajc$tjp_1 = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 144);
    }

    public void shutdown() {
        Cache cache = this.mOkHttpClient.cache();
        if (cache != null) {
            try {
                cache.close();
            } catch (IOException e) {
                Log.e(TAG, "close mCache failed.", (Object) e);
            }
        }
    }

    public HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        Call call;
        Request.Builder builder = new Request.Builder();
        builder.url(request.getUrl());
        Map<String, String> headers = request.getHeaders();
        for (String next : headers.keySet()) {
            builder.addHeader(next, headers.get(next));
        }
        for (String next2 : map.keySet()) {
            builder.addHeader(next2, map.get(next2));
        }
        setConnectionParametersForRequest(builder, request);
        okhttp3.Request build = builder.build();
        long timeoutMs = (long) request.getTimeoutMs();
        if (timeoutMs != 2500) {
            OkHttpClient.Builder readTimeout = this.mOkHttpClient.newBuilder().connectTimeout(timeoutMs, TimeUnit.MILLISECONDS).writeTimeout(timeoutMs, TimeUnit.MILLISECONDS).readTimeout(timeoutMs, TimeUnit.MILLISECONDS);
            JoinPoint a2 = Factory.a(ajc$tjp_0, (Object) this, (Object) readTimeout);
            call = ((OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, readTimeout, a2}).linkClosureAndJoinPoint(4112))).newCall(build);
        } else {
            call = this.mOkHttpClient.newCall(build);
        }
        Response execute = call.execute();
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(parseProtocol(execute.protocol()), execute.code(), execute.message()));
        basicHttpResponse.setEntity(entityFromOkHttpResponse(execute));
        Headers headers2 = execute.headers();
        int size = headers2.size();
        for (int i = 0; i < size; i++) {
            String name = headers2.name(i);
            String value = headers2.value(i);
            if (name != null) {
                basicHttpResponse.addHeader(new BasicHeader(name, value));
            }
        }
        return basicHttpResponse;
    }

    static final OkHttpClient build_aroundBody0(OkHttpStack okHttpStack, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public Cache createCache() {
        return new Cache(createDefaultCacheDir(ShopApp.instance, DEFAULT_CACHE).getAbsoluteFile(), DEFAULT_CACHE_SIZE);
    }

    /* access modifiers changed from: protected */
    public OkHttpClient createOkhttpClient(Cache cache) {
        OkHttpClient.Builder cache2 = new OkHttpClient.Builder().connectTimeout(2500, TimeUnit.MILLISECONDS).writeTimeout(2500, TimeUnit.MILLISECONDS).readTimeout(2500, TimeUnit.MILLISECONDS).cache(cache);
        JoinPoint a2 = Factory.a(ajc$tjp_1, (Object) this, (Object) cache2);
        return (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure3(new Object[]{this, cache2, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient build_aroundBody2(OkHttpStack okHttpStack, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public File createDefaultCacheDir(Context context, String str) {
        File file = new File(context.getApplicationContext().getCacheDir(), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static HttpEntity entityFromOkHttpResponse(Response response) throws IOException {
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        ResponseBody body = response.body();
        basicHttpEntity.setContent(body.byteStream());
        basicHttpEntity.setContentLength(body.contentLength());
        basicHttpEntity.setContentEncoding(response.header("Content-Encoding"));
        if (body.contentType() != null) {
            basicHttpEntity.setContentType(body.contentType().type());
        }
        return basicHttpEntity;
    }

    private static void setConnectionParametersForRequest(Request.Builder builder, com.mishopsdk.volley.Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    builder.post(RequestBody.create(MediaType.parse(request.getPostBodyContentType()), postBody));
                    return;
                }
                return;
            case 0:
                builder.get();
                return;
            case 1:
                builder.post(createRequestBody(request));
                return;
            case 2:
                builder.put(createRequestBody(request));
                return;
            case 3:
                builder.delete();
                return;
            case 4:
                builder.head();
                return;
            case 5:
                builder.method(HttpOptions.METHOD_NAME, (RequestBody) null);
                return;
            case 6:
                builder.method(HttpTrace.METHOD_NAME, (RequestBody) null);
                return;
            case 7:
                builder.patch(createRequestBody(request));
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static ProtocolVersion parseProtocol(Protocol protocol) {
        switch (protocol) {
            case HTTP_1_0:
                return new ProtocolVersion(HttpVersion.HTTP, 1, 0);
            case HTTP_1_1:
                return new ProtocolVersion(HttpVersion.HTTP, 1, 1);
            case SPDY_3:
                return new ProtocolVersion("SPDY", 3, 1);
            case HTTP_2:
                return new ProtocolVersion(HttpVersion.HTTP, 2, 0);
            default:
                throw new IllegalAccessError("Unkwown protocol");
        }
    }

    private static RequestBody createRequestBody(com.mishopsdk.volley.Request request) throws AuthFailureError {
        byte[] body = request.getBody();
        if (body == null) {
            return RequestBody.create((MediaType) null, new byte[0]);
        }
        return RequestBody.create(MediaType.parse(request.getBodyContentType()), body);
    }
}
