package com.xiaomi.jr.http;

import android.content.Context;
import com.xiaomi.jr.common.utils.MifiLog;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class WebHttpManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile WebHttpManager f1432a;
    private OkHttpClient b;
    private Pattern c;

    public static WebHttpManager a() {
        return f1432a;
    }

    private WebHttpManager(Context context, Map<String, String[]> map) {
        this.b = a(context, map);
        this.c = a(map);
    }

    public OkHttpClient b() {
        return this.b;
    }

    public Pattern c() {
        return this.c;
    }

    private Pattern a(Map<String, String[]> map) {
        String str;
        if (map != null) {
            str = null;
            for (String replace : map.keySet()) {
                String replace2 = replace.replace(".", "\\.").replace("*", ".*");
                if (str == null) {
                    str = replace2;
                } else {
                    str = str + "|" + replace2;
                }
            }
        } else {
            str = null;
        }
        if (str != null) {
            return Pattern.compile(str);
        }
        return null;
    }

    private static OkHttpClient a(Context context, Map<String, String[]> map) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(MifiLog.f1417a ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.authenticator(new ServiceTokenAuthenticator(context)).cookieJar(new XiaomiAccountCookieJar(context)).addInterceptor(httpLoggingInterceptor).followRedirects(false);
        HttpManager.a(builder, map);
        return builder.build();
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f1433a;
        private Map<String, String[]> b = new HashMap();

        public Builder(Context context) {
            this.f1433a = context;
        }

        public Builder a(String str, String[] strArr) {
            this.b.put(str, strArr);
            return this;
        }

        public WebHttpManager a() {
            return new WebHttpManager(this.f1433a, this.b);
        }
    }

    public static synchronized void a(WebHttpManager webHttpManager) {
        synchronized (WebHttpManager.class) {
            f1432a = webHttpManager;
        }
    }
}
