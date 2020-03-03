package com.mi.global.shop.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.adapter.user.CouponListAdapter;
import com.mi.global.shop.buy.payu.Cards;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.newmodel.user.coupon.NewCouponData;
import com.mi.global.shop.newmodel.user.coupon.NewCouponItem;
import com.mi.global.shop.newmodel.user.coupon.NewCouponResult;
import com.mi.global.shop.newmodel.user.coupon.NewPaymentCouponResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.util.ArrayList;
import java.util.HashMap;

public class CouponActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7027a = "CouponActivity";
    private View b;
    private ListView c;
    private CouponListAdapter d;
    private NewCouponData e;
    private ArrayList<NewCouponItem> f;
    private String g;
    private String h;
    private String i;
    private String j;
    private View k;
    public ProgressDialog mProgressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_activity_coupon_list);
        setTitle(R.string.user_coupon_title);
        this.mCartView.setVisibility(4);
        this.mBackView.setVisibility(0);
        this.mBackView.setOnClickListener(this);
        Intent intent = getIntent();
        this.g = intent.getStringExtra("com.mi.global.shop.extra_user_coupon_type");
        if (TextUtils.isEmpty(this.g)) {
            LogUtil.b(f7027a, "accessType is null");
            finish();
            return;
        }
        this.h = intent.getStringExtra("address_id");
        this.i = intent.getStringExtra("city_id");
        this.j = intent.getStringExtra("coupon_id");
        Cards.a(getResources());
        this.f = new ArrayList<>();
        this.b = findViewById(R.id.user_no_coupon);
        this.c = (ListView) findViewById(R.id.user_coupon_list);
        this.d = new CouponListAdapter(this, this.g);
        if ("coupon_manage".equalsIgnoreCase(this.g)) {
            a();
        }
        if ("coupon_choose".equalsIgnoreCase(this.g)) {
            a(intent.getStringExtra("coupon_list"));
            this.k = LayoutInflater.from(this).inflate(R.layout.shop_unuse_coupon_item, this.c, false);
            this.c.addHeaderView(this.k);
            this.k.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CouponActivity.this.a((NewCouponItem) null);
                }
            });
        }
        this.c.setAdapter(this.d);
    }

    private void a(String str) {
        try {
            this.e = (NewCouponData) new Gson().fromJson("{\"coupons\":" + str + "}", NewCouponData.class);
            a(this.e);
        } catch (Exception e2) {
            LogUtil.b(f7027a, "JSON parse error");
            e2.printStackTrace();
            CrashReport.postCrash(Thread.currentThread(), (Throwable) e2);
            b();
        }
    }

    private void a() {
        Request request;
        AnonymousClass2 r0 = new SimpleCallback<NewCouponResult>() {
            public void a(NewCouponResult newCouponResult) {
                if (CouponActivity.this.mProgressDialog != null) {
                    CouponActivity.this.mProgressDialog.dismiss();
                }
                CouponActivity.this.a(newCouponResult.data);
            }

            public void a(String str) {
                super.a(str);
                if (CouponActivity.this.mProgressDialog != null) {
                    CouponActivity.this.mProgressDialog.dismiss();
                }
                CouponActivity.this.finish();
            }
        };
        String ad = ConnectionHelper.ad();
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(ad, NewCouponResult.class, r0);
        } else {
            request = new SimpleJsonRequest(ad, NewCouponResult.class, r0);
        }
        request.setTag(f7027a);
        RequestQueueUtil.a().add(request);
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressDialog(this);
            this.mProgressDialog.setMessage(getString(R.string.please_wait));
            this.mProgressDialog.setIndeterminate(true);
            this.mProgressDialog.setCancelable(false);
        }
        this.mProgressDialog.show();
    }

    private void b() {
        MiToast.a((Context) this, R.string.shop_error_network, 0);
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        setResult(0);
        finish();
        LogUtil.b(f7027a, "JSON parse error");
    }

    /* access modifiers changed from: private */
    public void a(NewCouponData newCouponData) {
        if (newCouponData == null || newCouponData.coupons == null) {
            b();
            return;
        }
        String str = f7027a;
        LogUtil.b(str, "model length:" + newCouponData.coupons.size());
        this.f = newCouponData.coupons;
        if (this.f.size() == 0) {
            this.b.setVisibility(0);
            return;
        }
        this.d.c();
        this.d.a(this.f);
        if (this.g.equalsIgnoreCase("coupon_choose")) {
            this.c.setOnItemClickListener(this);
        }
    }

    public void onClick(View view) {
        try {
            if (view.getId() == R.id.title_bar_back) {
                if (this.mProgressDialog != null) {
                    this.mProgressDialog.dismiss();
                }
                setResult(0);
                finish();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onBackPressed() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        setResult(0);
        finish();
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        if (adapterView == this.c && this.g.equalsIgnoreCase("coupon_choose")) {
            Object itemAtPosition = adapterView.getItemAtPosition(i2);
            if (itemAtPosition instanceof NewCouponItem) {
                NewCouponItem newCouponItem = (NewCouponItem) itemAtPosition;
                if (!TextUtils.isEmpty(newCouponItem.couponId)) {
                    a(newCouponItem);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final NewCouponItem newCouponItem) {
        String str;
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.i);
        hashMap.put("address_id", this.h);
        hashMap.put(UrlConstants.payment, "55");
        hashMap.put("cardtype", "no");
        if (newCouponItem == null) {
            str = "0";
        } else {
            str = newCouponItem.couponId;
        }
        hashMap.put("value", str);
        if (!TextUtils.isEmpty(this.j)) {
            hashMap.put("exchange_coupon_id", this.j);
        }
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.bo(), NewPaymentCouponResult.class, hashMap, new SimpleCallback<NewPaymentCouponResult>() {
            public void a(NewPaymentCouponResult newPaymentCouponResult) {
                if (CouponActivity.this.mProgressDialog != null) {
                    CouponActivity.this.mProgressDialog.dismiss();
                }
                if (!(newPaymentCouponResult == null || newPaymentCouponResult.data == null)) {
                    if (newPaymentCouponResult.data.coupon != null && Tags.Coupon.RESULT_REFUSE.equals(newPaymentCouponResult.data.coupon.result)) {
                        if (!TextUtils.isEmpty(newPaymentCouponResult.data.coupon.reason)) {
                            MiToast.a((Context) CouponActivity.this, (CharSequence) newPaymentCouponResult.data.coupon.reason, 0);
                        }
                        CouponActivity.this.finish();
                        return;
                    } else if (newPaymentCouponResult.data.checkout != null) {
                        Intent intent = new Intent();
                        if (newCouponItem != null && !TextUtils.isEmpty(newCouponItem.couponId)) {
                            intent.putExtra("coupon_id", newCouponItem.couponId);
                        }
                        if (newCouponItem != null && !TextUtils.isEmpty(newCouponItem.type)) {
                            intent.putExtra("type", newCouponItem.type);
                        }
                        if (newCouponItem != null && !TextUtils.isEmpty(newCouponItem.couponName)) {
                            intent.putExtra("name", newCouponItem.couponName);
                        }
                        if (!TextUtils.isEmpty(newPaymentCouponResult.data.checkout.couponDiscountMoney)) {
                            intent.putExtra("couponDiscountMoney", newPaymentCouponResult.data.checkout.couponDiscountMoney);
                        }
                        if (!TextUtils.isEmpty(newPaymentCouponResult.data.checkout.shipment)) {
                            intent.putExtra("shipment", newPaymentCouponResult.data.checkout.shipment);
                        }
                        if (!TextUtils.isEmpty(newPaymentCouponResult.data.checkout.need_pay_amount)) {
                            intent.putExtra("amount", newPaymentCouponResult.data.checkout.need_pay_amount);
                        }
                        CouponActivity.this.setResult(-1, intent);
                    }
                }
                CouponActivity.this.finish();
            }

            public void a(String str) {
                super.a(str);
                if (CouponActivity.this.mProgressDialog != null) {
                    CouponActivity.this.mProgressDialog.dismiss();
                }
                CouponActivity.this.finish();
            }
        });
        simpleProtobufRequest.setTag(f7027a);
        RequestQueueUtil.a().add(simpleProtobufRequest);
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressDialog(this);
            this.mProgressDialog.setMessage(getString(R.string.please_wait));
            this.mProgressDialog.setIndeterminate(true);
            this.mProgressDialog.setCancelable(false);
        }
        this.mProgressDialog.show();
    }
}
