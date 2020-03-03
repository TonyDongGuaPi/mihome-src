package com.mi.global.shop.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mi.global.bbs.http.ParamKey;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.request.MiJsonObjectRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.RequestUtil;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.multimonitor.Request;
import com.mi.util.Device;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.mi.util.UserEncryptionUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7034a = "FeedbackActivity";
    @BindView(2131493038)
    CommonButton btnSubmit;
    @BindView(2131493344)
    CustomEditTextView feedbackContent;
    @BindView(2131493345)
    CustomEditTextView feedbackInfo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            LogUtil.b(f7034a, "onCreate, savedInstanceState:" + bundle.toString());
        }
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_user_feedback_edit_fragment);
        ButterKnife.bind((Activity) this);
        setTitle(R.string.user_feedback_title);
        this.mBackView.setVisibility(0);
        this.mCartView.setVisibility(8);
        initView();
    }

    public void initView() {
        this.btnSubmit.setOnClickListener(this);
        this.mForgetPwd.setText(R.string.custom_service);
        this.mForgetPwd.setTextColor(Color.rgb(255, 103, 0));
        this.mForgetPwd.setVisibility(0);
        this.mForgetPwd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String ao = ConnectionHelper.ao();
                Intent intent = new Intent(FeedbackActivity.this, WebActivity.class);
                intent.putExtra("url", ao);
                FeedbackActivity.this.startActivity(intent);
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit) {
            String trim = this.feedbackContent.getText().toString().trim();
            String trim2 = this.feedbackInfo.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                MiToast.a((Context) this, (CharSequence) getString(R.string.feedback_content_cannot_empty), 3000);
            } else if (TextUtils.isEmpty(trim2)) {
                MiToast.a((Context) this, (CharSequence) getString(R.string.feedback_contact_cannot_empty), 3000);
            } else {
                sendFeedback(trim, trim2);
            }
        }
    }

    public void sendFeedback(String str, String str2) {
        this.btnSubmit.setEnabled(false);
        HashMap hashMap = new HashMap();
        hashMap.put("appid", ShopApp.h().f());
        hashMap.put("content", str);
        hashMap.put("contact", str2);
        hashMap.put(ParamKey.appversion, Device.s);
        hashMap.put(ParamKey.mobileversion, Device.e);
        hashMap.put("androidversion", Device.o);
        hashMap.put("deviceid", Device.x);
        hashMap.put("mUserId", UserEncryptionUtil.a(LoginManager.u().c()));
        MiJsonObjectRequest miJsonObjectRequest = new MiJsonObjectRequest(1, ConnectionHelper.ba(), RequestUtil.a(RequestUtil.a((Map<String, String>) hashMap, true)), new Response.Listener<JSONObject>() {
            /* renamed from: a */
            public void onResponse(JSONObject jSONObject) {
                FeedbackActivity.this.btnSubmit.setEnabled(true);
                FeedbackActivity.this.hideLoading();
                if (jSONObject == null) {
                    try {
                        LogUtil.b(FeedbackActivity.f7034a, "get response json null");
                        FeedbackActivity.this.showError("");
                    } catch (Exception e) {
                        LogUtil.b(FeedbackActivity.f7034a, "loadInfo Exception:" + e.toString());
                        e.printStackTrace();
                        FeedbackActivity.this.showError("");
                    }
                } else {
                    LogUtil.b(FeedbackActivity.f7034a, "get response json:" + jSONObject.toString());
                    try {
                        if (!jSONObject.has(Request.RESULT_CODE_KEY) || jSONObject.getInt(Request.RESULT_CODE_KEY) != 0) {
                            FeedbackActivity.this.showError(jSONObject.optString("errmsg"));
                            return;
                        }
                        LogUtil.b(FeedbackActivity.f7034a, "loadInfo errno = 0");
                        MiToast.a((Context) FeedbackActivity.this, (CharSequence) FeedbackActivity.this.getString(R.string.user_feedback_thanks), 3000);
                        FeedbackActivity.this.finish();
                    } catch (Exception e2) {
                        LogUtil.b(FeedbackActivity.f7034a, "loadInfo Exception:" + e2.toString());
                        e2.printStackTrace();
                        FeedbackActivity.this.showError("");
                    }
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FeedbackActivity.this.hideLoading();
                FeedbackActivity.this.btnSubmit.setEnabled(true);
                LogUtil.b(FeedbackActivity.f7034a, "VolleyError error:" + volleyError.getMessage());
                FeedbackActivity.this.showError("");
            }
        });
        showLoading();
        miJsonObjectRequest.setTag(f7034a);
        RequestQueueUtil.a().add(miJsonObjectRequest);
    }

    public void showError(String str) {
        if (TextUtils.isEmpty(str)) {
            MiToast.a((Context) this, (CharSequence) getString(R.string.shop_error_network), 3000);
        } else {
            MiToast.a((Context) this, (CharSequence) str, 3000);
        }
    }
}
