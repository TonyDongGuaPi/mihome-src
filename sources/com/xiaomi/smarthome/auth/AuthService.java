package com.xiaomi.smarthome.auth;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.authlib.IAuthCallBack;
import com.xiaomi.smarthome.authlib.ICallAuth;

public class AuthService extends Service {
    public static final String REQUEST_CODE_AUTH = "request_auth_code";

    /* renamed from: a  reason: collision with root package name */
    private static final String f13831a = "AuthService";
    ICallAuth.Stub binder = new ICallAuth.Stub() {
        public void callAuth(int i, Bundle bundle, IAuthCallBack iAuthCallBack) throws RemoteException {
            AuthService.this.a(i, bundle, iAuthCallBack);
        }
    };

    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    /* access modifiers changed from: private */
    public void a(int i, Bundle bundle, IAuthCallBack iAuthCallBack) {
        AuthManager.h().l();
        AuthManager.h().a(iAuthCallBack);
        Intent intent = new Intent(this, AuthCheckActivity.class);
        bundle.putInt(REQUEST_CODE_AUTH, i);
        AuthManager.h().a(bundle);
        AuthManager.h().a(i);
        intent.putExtras(bundle);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        startActivity(intent);
    }
}
