package com.xiaomi.miot.store.verify;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaomi.miot.store.verify.poji.RegisterSessionBean;
import com.xiaomi.miot.store.verify.poji.RegisterSessionData;
import com.xiaomi.verificationsdk.VerificationManager;
import com.xiaomi.verificationsdk.internal.RegisterInfo;
import com.xiaomi.verificationsdk.internal.VerifyError;
import com.xiaomi.verificationsdk.internal.VerifyResult;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import com.xiaomi.youpin.youpin_network.bean.RequestParams;
import com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser;
import com.xiaomi.youpin.youpin_network.util.YouPinParamsUtil;

public class YouPinVerificationManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11429a = "CloudSDK";
    private static final String b = "VertifyRegister";
    private static volatile YouPinVerificationManager c;
    private VerificationManager d;

    public static YouPinVerificationManager a() {
        if (c == null) {
            synchronized (YouPinVerificationManager.class) {
                if (c == null) {
                    c = new YouPinVerificationManager();
                }
            }
        }
        return c;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public static RegisterSessionData c() {
        RequestParams requestParams = new RequestParams(f11429a, b, (JsonObject) null);
        RegisterSessionBean registerSessionBean = (RegisterSessionBean) YouPinHttpsApi.a().a(requestParams, YouPinParamsUtil.a(1, YouPinParamsUtil.f23868a, requestParams), new YouPinJsonParser<RegisterSessionBean>() {
            /* renamed from: a */
            public RegisterSessionBean b(JsonElement jsonElement) {
                return (RegisterSessionBean) new Gson().fromJson(jsonElement.toString(), RegisterSessionBean.class);
            }
        }).first;
        if (registerSessionBean != null) {
            return registerSessionBean.d();
        }
        return null;
    }

    public void a(Activity activity) {
        this.d = new VerificationManager(activity);
        this.d.a();
    }

    public void a(boolean z, boolean z2, VerificationCallback verificationCallback) {
        if (this.d != null) {
            final VerificationRemovableCallback verificationRemovableCallback = new VerificationRemovableCallback(verificationCallback);
            this.d.b(z2);
            this.d.a(z);
            this.d.a((VerificationManager.SessionRegister) new VerificationManager.SessionRegister() {
                public RegisterInfo a() {
                    RegisterSessionData b2 = YouPinVerificationManager.c();
                    if (b2 != null) {
                        return new RegisterInfo(b2.a(), b2.b());
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            verificationRemovableCallback.a(-2, "register session fail");
                            verificationRemovableCallback.b();
                        }
                    });
                    return null;
                }
            });
            this.d.a((VerificationManager.VerifyResultCallback) new VerificationManager.VerifyResultCallback() {
                public void a(final VerifyResult verifyResult) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            verificationRemovableCallback.a(verifyResult);
                            verificationRemovableCallback.b();
                        }
                    });
                }

                public void a() {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            verificationRemovableCallback.a();
                            verificationRemovableCallback.b();
                        }
                    });
                }

                public void a(final VerifyError verifyError) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            verificationRemovableCallback.a(verifyError.a(), verifyError.b());
                            verificationRemovableCallback.b();
                        }
                    });
                }
            });
            this.d.b();
        } else if (verificationCallback != null) {
            verificationCallback.a(-101, "not init");
        }
    }
}
