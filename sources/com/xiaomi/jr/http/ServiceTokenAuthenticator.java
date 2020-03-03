package com.xiaomi.jr.http;

import android.content.Context;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.common.utils.MifiLog;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class ServiceTokenAuthenticator implements Authenticator {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10818a = "ServiceTokenAuthenticator";
    private Context b;

    ServiceTokenAuthenticator(Context context) {
        this.b = context.getApplicationContext();
    }

    public Request authenticate(Route route, Response response) throws IOException {
        try {
            String httpUrl = response.request().url().toString();
            MifiLog.b(f10818a, "authenticate failed, reset account info and try again. url=" + httpUrl);
            if (XiaomiAccountManager.a().b(this.b, httpUrl, "mifi_401") != null) {
                return response.request().newBuilder().build();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
