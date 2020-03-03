package com.mi.global.shop.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.android.volley.Request;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.newmodel.ordercancel.NewRefundApplyResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.RequestUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CancelOrderAcitvity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5343a = "CancelOrderAcitvity";
    private CustomEditTextView b;
    private CustomEditTextView c;
    private Spinner d;
    /* access modifiers changed from: private */
    public SimpleDraweeView e;
    private CustomTextView f;
    private ArrayAdapter<CancelReason> g;
    private String h;
    private ProgressDialog i;
    /* access modifiers changed from: private */
    public CancelReason j;
    protected ArrayList<CancelReason> reasons;

    public static class CancelReason {
        @SerializedName("id")

        /* renamed from: a  reason: collision with root package name */
        public String f5350a;
        @SerializedName("desc")
        public String b;

        public String toString() {
            return this.b;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.h = getIntent().getStringExtra("com.mi.global.shop.extra_buy_confirm_orderid");
        this.reasons = (ArrayList) new Gson().fromJson(getIntent().getStringExtra("cancel_reason"), new TypeToken<ArrayList<CancelReason>>() {
        }.getType());
        if (this.reasons == null) {
            this.reasons = new ArrayList<>();
        }
        if (TextUtils.isEmpty(this.h)) {
            MiToast.a((Context) this, (CharSequence) "orderId is null", 0);
            setResult(0, (Intent) null);
            finish();
        }
        setCustomContentView(R.layout.shop_cancel_order);
        setTitle((CharSequence) getString(R.string.order_cancellation));
        this.mBackView.setVisibility(0);
        findViewById(R.id.title_bar_cart_view).setVisibility(4);
        this.d = (Spinner) findViewById(R.id.reason_spinner);
        this.b = (CustomEditTextView) findViewById(R.id.reason_description);
        this.c = (CustomEditTextView) findViewById(R.id.vcode);
        this.e = (SimpleDraweeView) findViewById(R.id.vcode_image);
        this.f = (CustomTextView) findViewById(R.id.change_vcode_image);
        String k = ConnectionHelper.k();
        FrescoUtils.a(k, this.e);
        String str = f5343a;
        LogUtil.b(str, "vcodeImgUrl " + k);
        updateUi();
        b();
    }

    /* access modifiers changed from: private */
    public void a() {
        Request request;
        HashMap hashMap = new HashMap();
        hashMap.put("orderId", this.h);
        hashMap.put(Tags.CheckCode.COOKIE_AUTHCODE, this.c.getText().toString());
        hashMap.put("reason", this.j.f5350a);
        hashMap.put("description", this.b.getText().toString());
        AnonymousClass2 r1 = new SimpleCallback<NewRefundApplyResult>() {
            public void a(NewRefundApplyResult newRefundApplyResult) {
                CancelOrderAcitvity.this.hideLoading();
                if (newRefundApplyResult.data != null) {
                    Intent intent = new Intent();
                    intent.putExtra("order_status", newRefundApplyResult.data.order_status_info);
                    CancelOrderAcitvity.this.setResult(-1, intent);
                    CancelOrderAcitvity.this.finish();
                    return;
                }
                CancelOrderAcitvity.this.setResult(0, new Intent());
            }

            public void a(String str) {
                super.a(str);
                CancelOrderAcitvity.this.hideLoading();
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(ConnectionHelper.l(), NewRefundApplyResult.class, RequestUtil.a((Map<String, String>) hashMap, true), r1);
        } else {
            request = new SimpleJsonRequest(ConnectionHelper.l(), NewRefundApplyResult.class, RequestUtil.a((Map<String, String>) hashMap, true), r1);
        }
        request.setTag(f5343a);
        RequestQueueUtil.a().add(request);
        showLoading();
    }

    private void b() {
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FrescoUtils.a(ConnectionHelper.k(), CancelOrderAcitvity.this.e);
            }
        });
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CancelOrderAcitvity.this.setResult(0, (Intent) null);
                CancelOrderAcitvity.this.finish();
            }
        });
        findViewById(R.id.confirm_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CancelOrderAcitvity.this.a();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void updateUi() {
        this.g = new ArrayAdapter<>(this, R.layout.shop_cancel_order_spinneritem, this.reasons);
        this.d.setAdapter(this.g);
        this.d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                CancelReason unused = CancelOrderAcitvity.this.j = CancelOrderAcitvity.this.reasons.get(i);
            }
        });
        this.d.setFocusable(true);
        this.d.setFocusableInTouchMode(true);
        this.d.requestFocus();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
