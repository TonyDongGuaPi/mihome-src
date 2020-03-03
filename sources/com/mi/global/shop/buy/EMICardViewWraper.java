package com.mi.global.shop.buy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.mi.global.shop.R;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.buy.payu.PayUtil;
import com.mi.global.shop.newmodel.GetOtpResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.util.RequestQueueUtil;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;
import com.taobao.weex.annotation.JSMethod;
import java.util.Map;

public class EMICardViewWraper extends AbstractCardViewWraper {
    public static final String m = "EMICardViewWraper";
    private String n;
    private String o = "";
    private String p = "";
    private String q = "";
    private String r = "";
    private BFLVerifyOTPFragment s = null;
    /* access modifiers changed from: private */
    public GetOtpResult.GetOtpData t = null;
    private DefaultRetryPolicy u = new DefaultRetryPolicy(30000, 0, 1.0f);

    public EMICardViewWraper(Context context, View view, boolean z) {
        super(context, view, EMIfragment.f6830a, z);
        view.findViewById(R.id.store_card_container).setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
    }

    /* access modifiers changed from: protected */
    public void c() {
        MiShopStatInterface.a("pay_click", EMIfragment.f6830a, "key", this.o + JSMethod.NOT_SET + this.q);
        ((ConfirmActivity) this.f6776a).showLoading();
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.bg()).buildUpon();
        buildUpon.appendQueryParameter("id", ((ConfirmActivity) this.f6776a).getconfirmOrder().f6881a);
        buildUpon.appendQueryParameter(BFLVerifyOTPFragment.d, this.r);
        buildUpon.appendQueryParameter("cardcode", this.h.getText().toString().replace(" ", ""));
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), GetOtpResult.class, (Map<String, String>) null, new SimpleCallback<GetOtpResult>() {
            public void a(GetOtpResult getOtpResult) {
                ((ConfirmActivity) EMICardViewWraper.this.f6776a).hideLoading();
                if (getOtpResult != null && getOtpResult.data != null) {
                    GetOtpResult.GetOtpData unused = EMICardViewWraper.this.t = getOtpResult.data;
                    EMICardViewWraper.this.i();
                }
            }

            public void onErrorResponse(VolleyError volleyError) {
                super.onErrorResponse(volleyError);
            }

            public void a(String str) {
                super.a(str);
                ((ConfirmActivity) EMICardViewWraper.this.f6776a).hideLoading();
            }
        });
        simpleProtobufRequest.setTag(m);
        simpleProtobufRequest.setRetryPolicy(this.u);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.s == null) {
            this.s = new BFLVerifyOTPFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString(BFLVerifyOTPFragment.f6789a, this.h.getText().toString().replace(" ", ""));
        bundle.putString(BFLVerifyOTPFragment.b, this.n);
        bundle.putString(BFLVerifyOTPFragment.c, this.p);
        bundle.putString(BFLVerifyOTPFragment.d, this.r);
        bundle.putString(BFLVerifyOTPFragment.e, this.t.mobileNumber);
        bundle.putString(BFLVerifyOTPFragment.f, this.t.transactionCode);
        this.s.setArguments(bundle);
        ((ConfirmActivity) this.f6776a).getSupportFragmentManager().beginTransaction().replace(R.id.confirm_fragment_top_container, this.s).addToBackStack((String) null).commitAllowingStateLoss();
    }

    public String e() {
        return this.o;
    }

    public void a(String str) {
        this.o = str;
    }

    public String f() {
        return this.r;
    }

    public void b(String str) {
        this.r = str;
    }

    public String g() {
        return this.q;
    }

    public void c(String str) {
        this.q = str;
    }

    public String h() {
        return this.p;
    }

    public void d(String str) {
        this.p = str;
    }

    /* access modifiers changed from: protected */
    public void d() {
        Params params = new Params();
        params.put(PayU.l, this.h.getText().toString().replace(" ", ""));
        params.put(PayU.m, String.valueOf(this.d));
        params.put(PayU.n, String.valueOf(this.c));
        params.put(PayU.o, this.g.getText().toString());
        params.put(PayU.p, this.f.getText().toString());
        params.put(PayU.j, this.n);
        PayUtil.a(((ConfirmActivity) this.f6776a).getconfirmOrder().f6881a, Constants.PAY_BANK_PAYU, Constants.PAYTYPE_EMI, (ConfirmActivity) this.f6776a, PayU.PaymentMode.EMI, params, this.h.getText().toString().replace(" ", ""), this.o, this.p, "", "");
    }

    public void e(String str) {
        this.n = str;
    }
}
