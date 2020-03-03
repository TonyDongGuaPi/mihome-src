package com.xiaomi.payment.channel;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.base.StepActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.payment.channel.model.WXpayModel;

public class WXPayEntryActivity extends StepActivity implements IWXAPIEventHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12169a = "WXPayEntryActivity";

    public void onReq(BaseReq baseReq) {
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        String a2 = a();
        if (TextUtils.isEmpty(a2) && !TextUtils.isEmpty(WXPayUtils.b) && WXPayUtils.f1495a != null) {
            a2 = WXPayUtils.b;
        }
        if (TextUtils.isEmpty(a2)) {
            finish();
        } else {
            WXAPIFactory.createWXAPI(getApplicationContext(), a2, false).handleIntent(getIntent(), this);
        }
    }

    private String a() {
        return PreferenceManager.getDefaultSharedPreferences(this).getString("appid", "");
    }

    public void onResp(BaseResp baseResp) {
        if (WXPayUtils.f1495a != null) {
            WXPayUtils.f1495a.a(baseResp);
        } else if (baseResp.getType() == 5) {
            String str = ((PayResp) baseResp).prepayId;
            WXpayModel.f12197a.put(str, Integer.valueOf(baseResp.errCode));
            Log.d(f12169a, "WXPayEntryAcitivty put sWXPayResult prepayId =" + str + "  " + "resp.errCode =" + baseResp.errCode);
        }
        finish();
    }
}
