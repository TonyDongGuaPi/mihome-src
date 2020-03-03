package com.xiaomi.jr.idcardverifier.http;

import android.content.Context;
import com.xiaomi.jr.account.XiaomiAccountManager;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class ServiceTokenAuthenticator implements Authenticator {

    /* renamed from: a  reason: collision with root package name */
    private Context f10868a;

    ServiceTokenAuthenticator(Context context) {
        this.f10868a = context.getApplicationContext();
    }

    public Request authenticate(Route route, Response response) throws IOException {
        try {
            if (XiaomiAccountManager.a().b(this.f10868a, response.request().url().toString(), "idcard_verifier_401") != null) {
                return response.request().newBuilder().build();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
