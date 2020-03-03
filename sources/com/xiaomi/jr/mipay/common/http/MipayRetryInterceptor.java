package com.xiaomi.jr.mipay.common.http;

import android.content.Context;
import com.xiaomi.jr.account.XiaomiAccountManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.json.JSONObject;

public class MipayRetryInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private Context f10948a;

    public MipayRetryInterceptor(Context context) {
        this.f10948a = context;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        HttpUtils.a("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        Response proceed = chain.proceed(chain.request());
        boolean z = true;
        if (proceed.code() == 401) {
            HttpUtils.a("[authenticate] token expired with 401, retry again");
        } else {
            if (proceed.isSuccessful() && proceed.body() != null) {
                try {
                    int optInt = new JSONObject(HttpUtils.a(proceed)).optInt("errcode");
                    if (optInt == 2000003) {
                        HttpUtils.a("[authenticate] token expired with " + optInt + ", retry again");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            z = false;
        }
        if (z && XiaomiAccountManager.a().b(this.f10948a, HostManager.b(), "mipay_token_expired") != null) {
            proceed = chain.proceed(chain.request());
        }
        HttpUtils.a(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return proceed;
    }
}
