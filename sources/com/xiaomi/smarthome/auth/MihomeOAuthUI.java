package com.xiaomi.smarthome.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mm.sdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.entity.WXDeviceShareLinkResult;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.miniprogram.MiniProgramManager;
import org.json.JSONException;
import org.json.JSONObject;

public class MihomeOAuthUI extends BaseActivity implements View.OnClickListener {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f13832a;
    @BindView(2131427443)
    TextView acceptStatusText;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public String d;
    @BindView(2131428814)
    TextView deviceNameText;
    private String e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public int h = -9999;
    /* access modifiers changed from: private */
    public boolean i = false;
    @BindView(2131429685)
    SimpleDraweeView imageIcon;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public boolean l = false;
    @BindView(2131427771)
    View leftBtn;
    @BindView(2131430638)
    View loadingText;
    /* access modifiers changed from: private */
    public SmartHomeDeviceManager.IClientDeviceListener m = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            SmartHomeDeviceManager.a().c(MihomeOAuthUI.this.m);
            Device b = SmartHomeDeviceManager.a().b(MihomeOAuthUI.this.b);
            if (b != null && b.isOwner()) {
                boolean unused = MihomeOAuthUI.this.l = true;
            }
            boolean unused2 = MihomeOAuthUI.this.j = true;
            if (MihomeOAuthUI.this.h == 0) {
                MihomeOAuthUI.this.a(true);
            } else if (MihomeOAuthUI.this.h != 9999) {
                MihomeOAuthUI.this.a(false);
            }
        }

        public void b(int i) {
            SmartHomeDeviceManager.a().c(MihomeOAuthUI.this.m);
            boolean unused = MihomeOAuthUI.this.j = true;
            if (MihomeOAuthUI.this.h == 0) {
                MihomeOAuthUI.this.a(true);
            } else {
                MihomeOAuthUI.this.a(false);
            }
        }
    };
    @BindView(2131427772)
    View oneBtn;
    @BindView(2131432616)
    View rightBtn;
    @BindView(2131428102)
    View twoBtns;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f13832a = getIntent().getStringExtra("transaction");
        try {
            JSONObject jSONObject = new JSONObject(getIntent().getStringExtra("message"));
            if (jSONObject.has("type")) {
                finish();
                return;
            }
            if (jSONObject.has("did")) {
                this.b = jSONObject.optString("did");
            }
            if (jSONObject.has("sharekey")) {
                this.g = jSONObject.optString("sharekey");
            }
            if (jSONObject.has("unionid")) {
                this.c = jSONObject.optString("unionid");
            }
            if (jSONObject.has("name")) {
                this.d = jSONObject.optString("name");
            }
            if (jSONObject.has("userId")) {
                this.e = jSONObject.optString("userId");
            }
            if (jSONObject.has("icon")) {
                this.f = jSONObject.optString("icon");
            }
            setContentView(R.layout.activity_mihome_oauth);
            ButterKnife.bind((Activity) this);
            if (TextUtils.isEmpty(this.b)) {
                a(false);
            } else if (TextUtils.isEmpty(this.g)) {
                a(false);
            } else if (TextUtils.isEmpty(this.c)) {
                a(false);
            } else {
                ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.share_oauth_title);
                this.imageIcon.setImageURI(CommonUtils.c((int) R.drawable.accept_loading));
                this.rightBtn.setOnClickListener(this);
                this.leftBtn.setOnClickListener(this);
                this.oneBtn.setOnClickListener(this);
                findViewById(R.id.module_a_3_return_btn).setOnClickListener(this);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (!CoreApi.a().q()) {
                    if (MihomeOAuthUI.this.k) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("accept_code", MihomeOAuthUI.this.h);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        WXTextObject wXTextObject = new WXTextObject();
                        wXTextObject.text = jSONObject.toString();
                        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXTextObject);
                        GetMessageFromWX.Resp resp = new GetMessageFromWX.Resp();
                        resp.transaction = MihomeOAuthUI.this.f13832a;
                        resp.message = wXMediaMessage;
                        SHApplication.getIWXAPI2().sendResp(resp);
                        MihomeOAuthUI.this.finish();
                        return;
                    }
                    LoginApi.a().a((Context) MihomeOAuthUI.this, 1, (LoginApi.LoginCallback) null);
                    boolean unused = MihomeOAuthUI.this.k = true;
                } else if (!MihomeOAuthUI.this.i) {
                    if (CoreApi.a().D()) {
                        if (TextUtils.isEmpty(MihomeOAuthUI.this.f)) {
                            MihomeOAuthUI.this.imageIcon.setImageURI(CommonUtils.c((int) R.drawable.accept_device_defualt));
                        } else {
                            MihomeOAuthUI.this.imageIcon.setImageURI(MihomeOAuthUI.this.f);
                        }
                        MihomeOAuthUI.this.deviceNameText.setText(TextUtils.isEmpty(MihomeOAuthUI.this.d) ? "" : MihomeOAuthUI.this.d);
                        MihomeOAuthUI.this.acceptStatusText.setVisibility(0);
                        MihomeOAuthUI.this.acceptStatusText.setText(R.string.share_accept_fail_not_support);
                        MihomeOAuthUI.this.acceptStatusText.setTextColor(MihomeOAuthUI.this.getResources().getColor(R.color.accept_fail_tip));
                        MihomeOAuthUI.this.oneBtn.setVisibility(0);
                        boolean unused2 = MihomeOAuthUI.this.i = true;
                        return;
                    }
                    MihomeOAuthUI.this.a();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (!CoreApi.a().q()) {
                    LoginApi.a().a((Context) MihomeOAuthUI.this, 1, (LoginApi.LoginCallback) null);
                    return;
                }
                MihomeOAuthUI.this.deviceNameText.setText(R.string.share_accept_ing);
                MiniProgramManager.a().a(MihomeOAuthUI.this.b, MihomeOAuthUI.this.g, MihomeOAuthUI.this.c, new AsyncCallback<WXDeviceShareLinkResult, Error>() {
                    /* renamed from: a */
                    public void onSuccess(WXDeviceShareLinkResult wXDeviceShareLinkResult) {
                        if (wXDeviceShareLinkResult != null) {
                            int unused = MihomeOAuthUI.this.h = wXDeviceShareLinkResult.b;
                            if (wXDeviceShareLinkResult.b == 0) {
                                MihomeOAuthUI.this.a(true);
                            }
                        }
                        MihomeOAuthUI.this.a(false);
                        SmartHomeDeviceManager.a().a(MihomeOAuthUI.this.m);
                        SmartHomeDeviceManager.a().p();
                    }

                    public void onFailure(Error error) {
                        MihomeOAuthUI.this.a(false);
                        SmartHomeDeviceManager.a().a(MihomeOAuthUI.this.m);
                        SmartHomeDeviceManager.a().p();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        String str;
        if (this.j) {
            this.i = true;
            this.loadingText.setVisibility(8);
            if (TextUtils.isEmpty(this.f)) {
                this.imageIcon.setImageURI(CommonUtils.c((int) R.drawable.accept_device_defualt));
            } else {
                this.imageIcon.setImageURI(this.f);
            }
            this.deviceNameText.setText(TextUtils.isEmpty(this.d) ? "" : this.d);
            this.acceptStatusText.setVisibility(0);
            if (z) {
                this.twoBtns.setVisibility(0);
                this.oneBtn.setVisibility(8);
                this.acceptStatusText.setTextColor(getResources().getColor(R.color.message_icon_color_green));
                if (TextUtils.isEmpty(this.e)) {
                    str = "";
                } else if (this.e.length() > 6) {
                    str = this.e.substring(0, 3) + "..." + this.e.substring(this.e.length() - 3, this.e.length());
                } else {
                    str = this.e;
                }
                TextView textView = this.acceptStatusText;
                Object[] objArr = new Object[1];
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                objArr[0] = str;
                textView.setText(getString(R.string.share_accept_success, objArr));
                return;
            }
            this.acceptStatusText.setTextColor(getResources().getColor(R.color.accept_fail_tip));
            if (this.h == 7) {
                if (this.l) {
                    this.acceptStatusText.setText(R.string.share_accept_fail_owner);
                } else {
                    this.acceptStatusText.setText(R.string.share_accept_fail_accept);
                }
            } else if (this.h == 2) {
                this.acceptStatusText.setText(R.string.share_accept_fail_accept);
            } else {
                this.acceptStatusText.setText(R.string.share_accept_fail_common);
            }
            this.oneBtn.setVisibility(0);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_wechat1:
            case R.id.back_wechat2:
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("accept_code", this.h);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                WXTextObject wXTextObject = new WXTextObject();
                wXTextObject.text = jSONObject.toString();
                WXMediaMessage wXMediaMessage = new WXMediaMessage(wXTextObject);
                GetMessageFromWX.Resp resp = new GetMessageFromWX.Resp();
                resp.transaction = this.f13832a;
                resp.message = wXMediaMessage;
                SHApplication.getIWXAPI2().sendResp(resp);
                finish();
                return;
            case R.id.module_a_3_return_btn:
                finish();
                return;
            case R.id.stay_mijia:
                Intent intent = new Intent(this, SmartHomeMainActivity.class);
                intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                intent.putExtra("source", 14);
                intent.putExtra("device_id", this.b);
                startActivity(intent);
                finish();
                return;
            default:
                return;
        }
    }
}
