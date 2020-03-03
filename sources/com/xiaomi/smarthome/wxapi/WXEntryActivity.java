package com.xiaomi.smarthome.wxapi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.app.startup.CTAHelper;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.MihomeOAuthUI;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.shop.share.ShareManager;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import org.json.JSONException;
import org.json.JSONObject;

public class WXEntryActivity extends FragmentActivity implements IWXAPIEventHandler {
    public static final String ACTION_LOGIN_COMPLETE = "action.wx.login.complete";
    public static final String AUTH_CODE = "auth_code";
    public static final String ERROR_CODE = "error_code";
    public static final String LOGIN_START = "action.wx.login.start";
    public static final String LOGIN_SUCCESS = "login_success";
    public static final String STATE = "state";

    /* renamed from: a  reason: collision with root package name */
    private IWXAPI f22973a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        this.f22973a = SHApplication.getIWXAPI();
        this.f22973a.handleIntent(getIntent(), this);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("action.wx.login.start"));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.f22973a.handleIntent(intent, this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        a(-9999);
    }

    public void onReq(BaseReq baseReq) {
        ShowMessageFromWX.Req req = (ShowMessageFromWX.Req) baseReq;
        WXMediaMessage wXMediaMessage = req.message;
        WXAppExtendObject wXAppExtendObject = (WXAppExtendObject) wXMediaMessage.mediaObject;
        int type = req.getType();
        String str = baseReq.transaction;
        if (4 != type) {
            return;
        }
        if (!CTAHelper.a(SHApplication.getAppContext()) || (!CTAHelper.b(SHApplication.getAppContext()) && ServerCompact.a(SHApplication.getAppContext()) != null)) {
            String str2 = null;
            if (SHApplication.getStateNotifier().a() != 4) {
                LoginApi.a().a((Context) this, 1, (LoginApi.LoginCallback) null);
                finish();
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(wXMediaMessage.messageExt);
                if (jSONObject.has("type")) {
                    str2 = jSONObject.optString("type");
                }
                if (TextUtils.isEmpty(str2)) {
                    Intent intent = new Intent(this, MihomeOAuthUI.class);
                    intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                    intent.putExtra("transaction", str);
                    intent.putExtra("message", wXMediaMessage.messageExt);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_anim_empty, R.anim.activity_anim_empty);
                } else {
                    char c = 65535;
                    if (str2.hashCode() == -1788317377) {
                        if (str2.equals("share_home")) {
                            c = 0;
                        }
                    }
                    if (c == 0) {
                        if (jSONObject.has("shareKey")) {
                            String optString = jSONObject.optString("shareKey");
                            if (!TextUtils.isEmpty(optString)) {
                                Intent intent2 = new Intent(this, SmartHomeMainActivity.class);
                                intent2.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                intent2.putExtra("source", 16);
                                intent2.putExtra(ApiConst.l, optString);
                                startActivity(intent2);
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            finish();
            return;
        }
        Intent intent3 = new Intent(this, SmartHomeMainActivity.class);
        intent3.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        startActivity(intent3);
        finish();
    }

    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 2) {
            if (baseResp.errCode != 0) {
                YouPinShareApi.a(this, false, baseResp.transaction, baseResp.errCode, "");
            } else {
                YouPinShareApi.a(this, true, baseResp.transaction, baseResp.errCode, "");
            }
        }
        if (baseResp.errCode != 0 || !(baseResp instanceof SendAuth.Resp)) {
            overridePendingTransition(R.anim.activity_anim_empty, R.anim.activity_anim_empty);
            finish();
            a(baseResp.errCode);
            if (baseResp.getType() == 2) {
                a(ShareManager.b, baseResp.errCode, baseResp.errStr, (Bundle) null);
                return;
            }
            return;
        }
        overridePendingTransition(R.anim.activity_anim_empty, R.anim.activity_anim_empty);
        finish();
        a((SendAuth.Resp) baseResp);
    }

    private void a(SendAuth.Resp resp) {
        sendCompleteBroadcast(true, resp.errCode, resp.code, resp.state);
    }

    private void a(int i) {
        sendCompleteBroadcast(false, i, (String) null, (String) null);
    }

    /* access modifiers changed from: package-private */
    public void sendCompleteBroadcast(boolean z, int i, String str, String str2) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent("action.wx.login.complete");
        intent.putExtra("login_success", z);
        intent.putExtra("auth_code", str);
        intent.putExtra("error_code", i);
        intent.putExtra("state", str2);
        instance.sendBroadcast(intent);
    }

    private void a(String str, int i, String str2, Bundle bundle) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getApplicationContext());
        Intent intent = new Intent(str);
        intent.putExtra("result_code", i);
        intent.putExtra("message", str2);
        intent.putExtra("extras", bundle);
        instance.sendBroadcast(intent);
    }
}
