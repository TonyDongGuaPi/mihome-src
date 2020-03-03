package com.xiaomi.jr.idcardverifier.http;

import android.content.Context;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.idcardverifier.utils.VerifyUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class VerifyHttpManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile VerifyHttpManager f10872a;
    private OkHttpClient b;

    public static VerifyHttpManager a(Context context) {
        if (f10872a == null) {
            synchronized (VerifyHttpManager.class) {
                if (f10872a == null) {
                    f10872a = new VerifyHttpManager(context.getApplicationContext());
                }
            }
        }
        return f10872a;
    }

    private VerifyHttpManager(Context context) {
        this.b = b(context);
    }

    public OkHttpClient a() {
        return this.b;
    }

    private OkHttpClient b(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(MifiLog.f1417a ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder().authenticator(new ServiceTokenAuthenticator(context)).cookieJar(new XiaomiAccountCookieJar(context)).addInterceptor(new BasicParamsInterceptor(VerifyUtils.a(context))).addInterceptor(httpLoggingInterceptor).build();
    }
}
