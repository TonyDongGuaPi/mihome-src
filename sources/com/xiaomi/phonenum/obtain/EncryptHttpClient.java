package com.xiaomi.phonenum.obtain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.xiaomi.phonenum.Constant;
import com.xiaomi.phonenum.bean.HttpError;
import com.xiaomi.phonenum.http.HttpClient;
import com.xiaomi.phonenum.http.HttpClientConfig;
import com.xiaomi.phonenum.http.Request;
import com.xiaomi.phonenum.http.Response;
import com.xiaomi.phonenum.http.UrlConnHttpFactory;
import com.xiaomi.phonenum.utils.Logger;
import com.xiaomi.phonenum.utils.LoggerManager;
import com.xiaomi.phonenum.utils.MapUtil;
import com.xiaomi.phonenum.utils.RSAEncryptUtil;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EncryptHttpClient implements HttpClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12568a = "EncryptHttpClient";
    private HttpClient b;
    private RSAEncryptUtil c;
    private Logger d = LoggerManager.a();

    EncryptHttpClient(@NonNull HttpClient httpClient) {
        this.b = httpClient;
        try {
            this.c = new RSAEncryptUtil();
        } catch (RSAEncryptUtil.EncryptException e) {
            e.printStackTrace();
        }
    }

    public Response a(@NonNull Request request) throws IOException {
        HashMap hashMap;
        if (!request.f12559a.startsWith(Constant.e)) {
            return this.b.a(request);
        }
        if (this.c == null) {
            return HttpError.ENCRYPT.result();
        }
        Request request2 = null;
        try {
            URI uri = request.b;
            ArrayList arrayList = new ArrayList();
            arrayList.add(MapUtil.a(request.d));
            arrayList.add(uri.getQuery());
            String a2 = MapUtil.a(arrayList, a.b);
            if (!TextUtils.isEmpty(a2)) {
                RSAEncryptUtil.EncryptResult a3 = this.c.a(a2);
                hashMap = new HashMap();
                hashMap.put("params", a3.f12580a);
                hashMap.put("secretKey", a3.b);
            } else {
                hashMap = null;
            }
            request2 = new Request.Builder().a(new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), (String) null, uri.getFragment()).toString()).b(request.c).c((Map<String, String>) hashMap).a();
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("unexpected newQuery: " + request.f12559a);
        } catch (RSAEncryptUtil.EncryptException e) {
            this.d.a(f12568a, "encryptedRequest Exception" + request, e);
        }
        if (request2 == null) {
            return HttpError.ENCRYPT.result();
        }
        Response a4 = this.b.a(request2);
        if (a4 == null) {
            return HttpError.DECRYPT.result();
        }
        if (a4.b == null) {
            return a4;
        }
        try {
            return new Response.Builder(a4).a(this.c.b(a4.b)).a();
        } catch (RSAEncryptUtil.EncryptException e2) {
            this.d.a(f12568a, "decryptedResponse Exception" + a4, e2);
            return HttpError.DECRYPT.result();
        }
    }

    public static class HttpFactory extends UrlConnHttpFactory {
        public HttpFactory(Context context) {
            super(context);
        }

        public HttpClient a(HttpClientConfig httpClientConfig) {
            return new EncryptHttpClient(super.a(httpClientConfig));
        }
    }
}
