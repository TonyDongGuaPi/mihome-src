package com.xiaomi.youpin.unionpay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.unionpay.UPPayAssistEx;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.IPayProvider;
import com.xiaomi.youpin.log.LogUtils;
import java.util.HashMap;

public class UnionpayProvider implements IPayProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23761a = "25";
    private static String b = "unionmipay";
    private static final String c = "UnionpayProvider";
    /* access modifiers changed from: private */
    public static String e;
    /* access modifiers changed from: private */
    public static String f;
    private ICallback d;

    public static boolean a(Context context, final IUnionpayCallback iUnionpayCallback) {
        if (TextUtils.isEmpty(e)) {
            try {
                UPPayAssistEx.getSEPayInfo(context, new UPQuerySEPayInfoCallback() {
                    public void onError(String str, String str2, String str3, String str4) {
                    }

                    public void onResult(String str, String str2, int i, Bundle bundle) {
                        String unused = UnionpayProvider.f = str;
                        String unused2 = UnionpayProvider.e = str2;
                        if (iUnionpayCallback != null) {
                            iUnionpayCallback.onInfoLoaded(TextUtils.equals(UnionpayProvider.e, "25"));
                        }
                    }
                });
            } catch (Throwable unused) {
                return false;
            }
        }
        return TextUtils.equals(e, "25");
    }

    public String name() {
        return b;
    }

    public void pay(Activity activity, String str, ICallback iCallback) {
        this.d = iCallback;
        UPPayAssistEx.startSEPay(activity, (String) null, (String) null, str, "00", e);
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        LogUtils.d(c, "pay result " + intent);
        String str = "fail";
        if (intent != null) {
            str = intent.getExtras().getString(AnalyticManager.KEY_PAY_RESULT);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("result", str);
        if (this.d == null) {
            return false;
        }
        this.d.callback(hashMap);
        return true;
    }

    public void clear() {
        this.d = null;
    }
}
