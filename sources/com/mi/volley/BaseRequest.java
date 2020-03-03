package com.mi.volley;

import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseRequest<T> extends Request<T> {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f7429a = "EasyBuyBaseRequest";
    protected static final String b = "Content-Encoding";
    protected static final String c = "Accept-Encoding";
    protected static final String d = "gzip";
    protected static final String e = "utf-8";
    protected ConcurrentHashMap<String, String> f = new ConcurrentHashMap<>();
    protected ConcurrentHashMap<String, String> g = new ConcurrentHashMap<>();
    protected boolean h = true;
    private Response.Listener<T> i;
    private String j;
    private Class<T> k;

    public abstract String a();

    public BaseRequest(int i2, String str, Response.ErrorListener errorListener) {
        super(i2, str, errorListener);
    }

    public BaseRequest(int i2, String str, Map<String, String> map, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(i2, str, errorListener);
        this.i = listener;
        if (map != null) {
            this.f.putAll(map);
            this.j = this.f.toString();
        }
    }

    public BaseRequest(String str, String str2, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(1, str, errorListener);
        this.i = listener;
        if (!TextUtils.isEmpty(str2)) {
            this.j = str2;
        }
    }

    public void a(Class cls) {
        this.k = cls;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        if (this.h) {
            this.g.put("Accept-Encoding", "gzip");
        }
        String a2 = a();
        if (!TextUtils.isEmpty(a2)) {
            this.g.put("Cookie", a2);
        }
        return this.g;
    }

    /* access modifiers changed from: protected */
    public final Map<String, String> getParams() throws AuthFailureError {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public boolean a(NetworkResponse networkResponse) {
        Map<String, String> map = networkResponse.headers;
        return map != null && !map.isEmpty() && map.containsKey("Content-Encoding") && map.get("Content-Encoding").equalsIgnoreCase("gzip");
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(T t) {
        this.i.onResponse(t);
    }

    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        return super.setRetryPolicy(new DefaultRetryPolicy(5000, 3, 1.0f));
    }

    /* access modifiers changed from: protected */
    public Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String parseCharset = HttpHeaderParser.parseCharset(networkResponse.headers);
            String str = null;
            if (this.h && a(networkResponse)) {
                try {
                    str = new String(a(networkResponse.data), parseCharset);
                } catch (IOException unused) {
                }
            }
            if (str == null) {
                str = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            }
            if (this.k.isAssignableFrom(JSONObject.class)) {
                return Response.success(new JSONObject(str), HttpHeaderParser.parseCacheHeaders(networkResponse));
            }
            return Response.success(str, HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e2) {
            return Response.error(new ParseError((Throwable) e2));
        } catch (JSONException e3) {
            e3.printStackTrace();
            return Response.error(new ParseError((Throwable) e3));
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a(byte[] r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x002b }
            r1.<init>(r7)     // Catch:{ all -> 0x002b }
            java.util.zip.GZIPInputStream r7 = new java.util.zip.GZIPInputStream     // Catch:{ all -> 0x002b }
            r7.<init>(r1)     // Catch:{ all -> 0x002b }
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r2 = new byte[r1]     // Catch:{ all -> 0x002b }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x002b }
            r3.<init>()     // Catch:{ all -> 0x002b }
        L_0x0014:
            r0 = 0
            int r4 = r7.read(r2, r0, r1)     // Catch:{ all -> 0x0028 }
            r5 = -1
            if (r4 == r5) goto L_0x0020
            r3.write(r2, r0, r4)     // Catch:{ all -> 0x0028 }
            goto L_0x0014
        L_0x0020:
            byte[] r7 = r3.toByteArray()     // Catch:{ all -> 0x0028 }
            r3.close()
            return r7
        L_0x0028:
            r7 = move-exception
            r0 = r3
            goto L_0x002c
        L_0x002b:
            r7 = move-exception
        L_0x002c:
            if (r0 == 0) goto L_0x0031
            r0.close()
        L_0x0031:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.volley.BaseRequest.a(byte[]):byte[]");
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.f.put(str, str2);
        }
    }

    public void a(HashMap<String, String> hashMap) {
        if (hashMap != null && hashMap.size() > 0) {
            this.f.putAll(hashMap);
        }
    }

    public byte[] getPostBody() throws AuthFailureError {
        return getBody();
    }

    public byte[] getBody() throws AuthFailureError {
        try {
            if (this.j == null) {
                return null;
            }
            return this.j.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", this.j, "utf-8");
            return null;
        }
    }
}
