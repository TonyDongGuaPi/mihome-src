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
import com.google.gson.Gson;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.adapter.user.ExchangeCouponListAdapter;
import com.mi.global.shop.newmodel.user.coupon.NewPaymentCouponResult;
import com.mi.global.shop.newmodel.user.exchangecoupon.coupon.NewExchangeCouponData;
import com.mi.global.shop.newmodel.user.exchangecoupon.coupon.NewExchangeCouponItem;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.util.ArrayList;
import java.util.HashMap;

public class ExchangeCouponActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7031a = "ExchangeCouponActivity";
    private View b;
    private ListView c;
    private ExchangeCouponListAdapter d;
    private NewExchangeCouponData e;
    private ArrayList<NewExchangeCouponItem> f;
    private String g;
    private String h;
    private String i;
    private View j;
    private String k;
    public ProgressDialog mProgressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_activity_coupon_list);
        setTitle(R.string.user_exchange_coupon_title);
        this.mCartView.setVisibility(4);
        this.mBackView.setVisibility(0);
        this.mBackView.setOnClickListener(this);
        Intent intent = getIntent();
        this.g = intent.getStringExtra("com.mi.global.shop.extra_user_coupon_type");
        if (TextUtils.isEmpty(this.g)) {
            LogUtil.b(f7031a, "mAccessType is null");
            finish();
            return;
        }
        this.h = intent.getStringExtra("address_id");
        this.i = intent.getStringExtra("coupon_id");
        this.k = intent.getStringExtra("city_id");
        this.f = new ArrayList<>();
        this.b = findViewById(R.id.user_no_coupon);
        this.c = (ListView) findViewById(R.id.user_coupon_list);
        this.d = new ExchangeCouponListAdapter(this, this.g);
        if ("coupon_choose".equalsIgnoreCase(this.g)) {
            a(intent.getStringExtra("coupon_list"));
            this.j = LayoutInflater.from(this).inflate(R.layout.shop_unuse_coupon_item, this.c, false);
            this.c.addHeaderView(this.j);
            this.j.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ExchangeCouponActivity.this.a((NewExchangeCouponItem) null);
                }
            });
        }
        this.c.setAdapter(this.d);
    }

    private void a(String str) {
        try {
            this.e = (NewExchangeCouponData) new Gson().fromJson("{\"exchange_coupon_list\":" + str + "}", NewExchangeCouponData.class);
            a(this.e);
        } catch (Exception e2) {
            LogUtil.b(f7031a, "JSON parse error");
            e2.printStackTrace();
            CrashReport.postCrash(Thread.currentThread(), (Throwable) e2);
            a();
        }
    }

    private void a() {
        MiToast.a((Context) this, R.string.shop_error_network, 0);
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        setResult(0);
        finish();
        LogUtil.b(f7031a, "JSON parse error");
    }

    private void a(NewExchangeCouponData newExchangeCouponData) {
        if (newExchangeCouponData == null || newExchangeCouponData.exchange_coupon_list == null) {
            a();
            return;
        }
        this.f = newExchangeCouponData.exchange_coupon_list;
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
            if (itemAtPosition instanceof NewExchangeCouponItem) {
                NewExchangeCouponItem newExchangeCouponItem = (NewExchangeCouponItem) itemAtPosition;
                if (!TextUtils.isEmpty(newExchangeCouponItem.id)) {
                    a(newExchangeCouponItem);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final NewExchangeCouponItem newExchangeCouponItem) {
        String str;
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.k);
        hashMap.put("address_id", this.h);
        hashMap.put(UrlConstants.payment, "55");
        hashMap.put("cardtype", "no");
        if (!TextUtils.isEmpty(this.i)) {
            hashMap.put("value", this.i);
        }
        if (newExchangeCouponItem == null) {
            str = "0";
        } else {
            str = newExchangeCouponItem.id;
        }
        hashMap.put("exchange_coupon_id", str);
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.bo(), NewPaymentCouponResult.class, hashMap, new SimpleCallback<NewPaymentCouponResult>() {
            public void a(NewPaymentCouponResult newPaymentCouponResult) {
                if (ExchangeCouponActivity.this.mProgressDialog != null) {
                    ExchangeCouponActivity.this.mProgressDialog.dismiss();
                }
                if (!(newPaymentCouponResult == null || newPaymentCouponResult.data == null)) {
                    if (newExchangeCouponItem != null && newPaymentCouponResult.data.exchange_coupon != null && newPaymentCouponResult.data.exchange_coupon.allow == 0) {
                        if (!TextUtils.isEmpty(newPaymentCouponResult.data.exchange_coupon.deny_reason)) {
                            MiToast.a((Context) ExchangeCouponActivity.this, (CharSequence) newPaymentCouponResult.data.exchange_coupon.deny_reason, 0);
                        }
                        ExchangeCouponActivity.this.finish();
                        return;
                    } else if (newPaymentCouponResult.data.checkout != null) {
                        Intent intent = new Intent();
                        if (newExchangeCouponItem != null && !TextUtils.isEmpty(newExchangeCouponItem.id)) {
                            intent.putExtra("coupon_id", newExchangeCouponItem.id);
                        }
                        if (newExchangeCouponItem != null && !TextUtils.isEmpty(newExchangeCouponItem.amount)) {
                            intent.putExtra("amount", newExchangeCouponItem.amount);
                        }
                        if (!TextUtils.isEmpty(newPaymentCouponResult.data.checkout.exchange_coupon_amount)) {
                            intent.putExtra("couponDiscountMoney", newPaymentCouponResult.data.checkout.exchange_coupon_amount);
                        }
                        if (!TextUtils.isEmpty(newPaymentCouponResult.data.checkout.shipment)) {
                            intent.putExtra("shipment", newPaymentCouponResult.data.checkout.shipment);
                        }
                        if (!TextUtils.isEmpty(newPaymentCouponResult.data.checkout.need_pay_amount)) {
                            intent.putExtra("amount", newPaymentCouponResult.data.checkout.need_pay_amount);
                        }
                        ExchangeCouponActivity.this.setResult(-1, intent);
                    }
                }
                ExchangeCouponActivity.this.finish();
            }

            public void a(String str) {
                super.a(str);
                if (ExchangeCouponActivity.this.mProgressDialog != null) {
                    ExchangeCouponActivity.this.mProgressDialog.dismiss();
                }
                ExchangeCouponActivity.this.finish();
            }
        });
        simpleProtobufRequest.setTag(f7031a);
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
