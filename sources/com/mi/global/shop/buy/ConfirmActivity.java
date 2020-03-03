package com.mi.global.shop.buy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.activity.OrderListAcitvity;
import com.mi.global.shop.activity.PayResultWebActivity;
import com.mi.global.shop.activity.SuccessAcitvity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.buy.OrderdetailFragment;
import com.mi.global.shop.buy.cod.CODViewHelper;
import com.mi.global.shop.buy.model.BuyOrderInfo;
import com.mi.global.shop.buy.model.BuyOrderItem;
import com.mi.global.shop.buy.model.OrderPaymentInfo;
import com.mi.global.shop.buy.model.PayModelUtil;
import com.mi.global.shop.buy.payu.Cards;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.model.SyncModel;
import com.mi.global.shop.newmodel.pay.payinfo.NewPayInfoData;
import com.mi.global.shop.newmodel.pay.payinfo.NewPayInfoResult;
import com.mi.global.shop.newmodel.pay.payinfo.NewPayOption;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.CountDownUtil;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.MiStatUtil;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.dialog.CustomCancelDialog;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;
import com.xiaomi.payment.data.MibiConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfirmActivity extends BaseActivity implements View.OnClickListener {
    public static final String IS_FROM_CHECKOUT = "is_from_checkout";
    public static final String PAGEID = "pay";
    public static final int START_ACTIVITY_COD_CHANGETEL = 102;

    /* renamed from: a  reason: collision with root package name */
    private static final String f6815a = "ConfirmActivity";
    private String b = "";
    /* access modifiers changed from: private */
    public FragmentManager c;
    public CODViewHelper codViewHelper = null;
    private OrderdetailFragment d;
    private View e;
    private HashMap<String, String> f;
    private double g = -1.0d;
    private JSONObject h = null;
    private BuyOrderInfo i;
    private String j;
    private String k;
    private NBfragment l = null;
    private Cardfragment m = null;
    private EMIfragment n = null;
    private CODfragment o = null;
    protected OrderPaymentInfo orderPaymentInfo = null;
    private CardlessEMIfragment p = null;
    private UPIFragment q = null;
    private Walletfragment r = null;
    private CustomCancelDialog s;
    private OnGetPayParams t;
    private CountDownUtil u;

    public interface OnGetPayParams {
        void a(ConfirmActivity confirmActivity, PayU.PaymentMode paymentMode, Params params);
    }

    private void c() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            LogUtil.b(f6815a, "onCreate, savedInstanceState:" + bundle.toString());
            this.orderPaymentInfo = (OrderPaymentInfo) bundle.getParcelable(OrderPaymentInfo.f6888a);
            this.i = (BuyOrderInfo) bundle.getParcelable("confirmOrder");
        }
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_buy_confirm_activity);
        setTitle(R.string.buy_confirm_title);
        Cards.a(getResources());
        this.b = getIntent().getStringExtra("com.mi.global.shop.extra_buy_confirm_orderid");
        if (TextUtils.isEmpty(this.b)) {
            LogUtil.b(f6815a, "orderId is null");
            finish();
        }
        LogUtil.b(f6815a, "get orderId:" + this.b);
        this.mCartView.setVisibility(4);
        this.e = findViewById(R.id.title_bar_home);
        this.e.setVisibility(0);
        this.e.setOnClickListener(this);
        this.c = getSupportFragmentManager();
        if (bundle != null) {
            this.d = (OrderdetailFragment) this.c.findFragmentByTag(OrderdetailFragment.class.getName());
        }
        FragmentTransaction beginTransaction = this.c.beginTransaction();
        if (this.d == null) {
            this.d = new OrderdetailFragment();
            beginTransaction.add(R.id.confirm_fragment_top_container, this.d, OrderdetailFragment.class.getName());
        }
        beginTransaction.commitAllowingStateLoss();
        a();
        try {
            MiStatUtil.a(this, this.b);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public String getAfterPaySuccessUrl() {
        return this.k;
    }

    public void setAfterPaySuccessUrl(String str) {
        this.k = str;
    }

    private void a() {
        Request request;
        final String[] b2 = b();
        AnonymousClass1 r1 = new SimpleCallback<NewPayInfoResult>() {
            public void a(NewPayInfoResult newPayInfoResult) {
                if (BaseActivity.isActivityAlive(ConfirmActivity.this)) {
                    ConfirmActivity.this.hideLoading();
                    ConfirmActivity.this.a(newPayInfoResult);
                }
            }

            public void onErrorResponse(VolleyError volleyError) {
                if (BaseActivity.isActivityAlive(ConfirmActivity.this)) {
                    if (volleyError.networkResponse == null || volleyError.networkResponse.statusCode != 302 || b2.length <= 1) {
                        super.onErrorResponse(volleyError);
                        return;
                    }
                    SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(b2[1], NewPayInfoResult.class, new SimpleCallback<NewPayInfoResult>() {
                        public void a(NewPayInfoResult newPayInfoResult) {
                            if (BaseActivity.isActivityAlive(ConfirmActivity.this)) {
                                ConfirmActivity.this.hideLoading();
                                ConfirmActivity.this.a(newPayInfoResult);
                                if (b2[1].startsWith("https")) {
                                    SyncModel.useHttps = true;
                                } else {
                                    SyncModel.useHttps = false;
                                }
                            }
                        }

                        public void a(String str) {
                            super.a(str);
                            ConfirmActivity.this.hideLoading();
                            ConfirmActivity.this.finish();
                        }
                    });
                    simpleProtobufRequest.setTag(ConfirmActivity.f6815a);
                    RequestQueueUtil.a().add(simpleProtobufRequest);
                }
            }

            public void a(String str) {
                super.a(str);
                LogUtil.b(ConfirmActivity.f6815a, "getOrderViewInfo Exception:" + str);
                ConfirmActivity.this.hideLoading();
                ConfirmActivity.this.finish();
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(b2[0], NewPayInfoResult.class, r1);
        } else {
            request = new SimpleJsonRequest(b2[0], NewPayInfoResult.class, r1);
        }
        request.setTag(f6815a);
        RequestQueueUtil.a().add(request);
        showLoading();
    }

    /* access modifiers changed from: private */
    public void a(NewPayInfoResult newPayInfoResult) {
        NewPayInfoData newPayInfoData = newPayInfoResult.data;
        if (newPayInfoData == null) {
            MiToast.a((Context) this, (CharSequence) getString(R.string.shop_network_unavaliable), 0);
            finish();
            return;
        }
        try {
            this.h = new JSONObject(newPayInfoData.toJsonString());
            this.orderPaymentInfo = new OrderPaymentInfo(this.h);
            JSONObject jSONObject = (JSONObject) this.h.get("payList");
            JSONObject jSONObject2 = new JSONObject(this.h.getString("payParamString"));
            JSONObject jSONObject3 = (JSONObject) this.h.get(MibiConstants.hk);
            if (jSONObject3 != null) {
                jSONObject3.put("exchange_coupon", this.h.optJSONObject("exchange_coupon"));
            }
            processOrder(jSONObject3);
            processPayList(jSONObject);
            PayU.av = Boolean.parseBoolean(jSONObject2.getString("supportStoreCards"));
            this.j = this.h.optString("mention_ext");
            if (!TextUtils.isEmpty(this.h.optString("bankList"))) {
                PayU.ad = new JSONObject(this.h.optString("bankList"));
            }
            if (newPayInfoData.payOptions != null) {
                PayU.ae = OrderdetailFragment.PaymentMethod.a((List<NewPayOption>) newPayInfoData.payOptions);
            }
            refreshFragment();
            d();
            LogUtil.b(f6815a, "getPaymentInfo Process success");
            if (newPayInfoData.pagemsg != null && this.d != null) {
                this.d.a(newPayInfoData.pagemsg);
            }
        } catch (JSONException e2) {
            MiToast.a((Context) this, R.string.shop_error_network, 0);
            e2.printStackTrace();
            CrashReport.postCrash(Thread.currentThread(), (Throwable) e2);
            finish();
        }
    }

    private String[] b() {
        String[] n2 = ConnectionHelper.n();
        String[] strArr = new String[n2.length];
        for (int i2 = 0; i2 < n2.length; i2++) {
            LogUtil.b(f6815a, "getPaymentInfo");
            Uri.Builder buildUpon = Uri.parse(n2[i2]).buildUpon();
            buildUpon.appendQueryParameter("order_id", this.b);
            buildUpon.appendQueryParameter("bank", Constants.PAY_BANK_PAYU);
            buildUpon.appendQueryParameter("security", "true");
            buildUpon.appendQueryParameter("jsontag", "true");
            buildUpon.appendQueryParameter("payparams", "0");
            if (ShopApp.n()) {
                buildUpon.appendQueryParameter("ot", "5");
            }
            LogUtil.b(f6815a, "payment url:" + buildUpon.toString());
            strArr[i2] = buildUpon.toString();
        }
        return strArr;
    }

    /* access modifiers changed from: protected */
    public void processOrder(JSONObject jSONObject) throws JSONException {
        PayU.U = jSONObject;
        this.i = new BuyOrderInfo(jSONObject);
        try {
            this.g = Double.parseDouble(this.i.g);
        } catch (Exception e2) {
            MiToast.a((Context) this, (CharSequence) getString(R.string.shop_network_unavaliable), 3000);
            CrashReport.postCrash(Thread.currentThread(), (Throwable) e2);
            finish();
        }
    }

    public BuyOrderInfo getconfirmOrder() {
        return this.i;
    }

    public String getMention() {
        return this.j;
    }

    /* access modifiers changed from: protected */
    public void processPayList(JSONObject jSONObject) throws JSONException {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            if (obj.equals("cards")) {
                PayModelUtil.a(jSONObject.getJSONArray("cards"));
            } else if (obj.equals(Constants.PAYTYPE_EMI)) {
                PayU.W = PayModelUtil.a(jSONObject.getJSONArray(Constants.PAYTYPE_EMI), this.g);
            } else if (obj.equals(Constants.PAYTYPE_NETBANK)) {
                PayU.T = jSONObject.getJSONObject(Constants.PAYTYPE_NETBANK);
            } else if (obj.equals("wallet")) {
                PayU.V = jSONObject.getJSONObject("wallet");
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: org.json.JSONArray} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processResult(org.json.JSONObject r7) {
        /*
            r6 = this;
            java.util.Iterator r0 = r7.keys()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r2 = 0
            com.mi.global.shop.buy.payu.PayU.ak = r2
        L_0x000c:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0072
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "hash"
            boolean r4 = r3.equals(r4)     // Catch:{ JSONException -> 0x006d }
            if (r4 == 0) goto L_0x002b
            java.lang.String r4 = "hash"
            java.lang.String r4 = r7.getString(r4)     // Catch:{ JSONException -> 0x006d }
            com.mi.global.shop.buy.payu.PayU.ak = r4     // Catch:{ JSONException -> 0x006d }
            goto L_0x0065
        L_0x002b:
            java.lang.String r4 = "amount"
            boolean r4 = r3.equals(r4)     // Catch:{ JSONException -> 0x006d }
            if (r4 == 0) goto L_0x0040
            java.lang.String r4 = "amount"
            java.lang.String r4 = r7.getString(r4)     // Catch:{ JSONException -> 0x006d }
            double r4 = java.lang.Double.parseDouble(r4)     // Catch:{ JSONException -> 0x006d }
            r6.g = r4     // Catch:{ JSONException -> 0x006d }
            goto L_0x0065
        L_0x0040:
            java.lang.String r4 = "supportStoreCards"
            boolean r4 = r3.equals(r4)     // Catch:{ JSONException -> 0x006d }
            if (r4 == 0) goto L_0x0055
            java.lang.String r4 = "supportStoreCards"
            java.lang.String r4 = r7.getString(r4)     // Catch:{ JSONException -> 0x006d }
            boolean r4 = java.lang.Boolean.parseBoolean(r4)     // Catch:{ JSONException -> 0x006d }
            com.mi.global.shop.buy.payu.PayU.av = r4     // Catch:{ JSONException -> 0x006d }
            goto L_0x0065
        L_0x0055:
            java.lang.String r4 = "key"
            boolean r4 = r3.equals(r4)     // Catch:{ JSONException -> 0x006d }
            if (r4 == 0) goto L_0x0065
            java.lang.String r4 = "key"
            java.lang.String r4 = r7.getString(r4)     // Catch:{ JSONException -> 0x006d }
            com.mi.global.shop.buy.payu.PayU.f6891a = r4     // Catch:{ JSONException -> 0x006d }
        L_0x0065:
            java.lang.String r4 = r7.getString(r3)     // Catch:{ JSONException -> 0x006d }
            r1.put(r3, r4)     // Catch:{ JSONException -> 0x006d }
            goto L_0x000c
        L_0x006d:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x000c
        L_0x0072:
            r6.f = r1
            java.lang.String r0 = "user_cards"
            java.lang.Object r7 = r7.get(r0)     // Catch:{ Exception -> 0x0134 }
            boolean r0 = r7 instanceof org.json.JSONArray     // Catch:{ Exception -> 0x0134 }
            if (r0 == 0) goto L_0x0082
            r2 = r7
            org.json.JSONArray r2 = (org.json.JSONArray) r2     // Catch:{ Exception -> 0x0134 }
        L_0x0082:
            if (r2 == 0) goto L_0x0138
            int r7 = r2.length()     // Catch:{ Exception -> 0x0134 }
            if (r7 <= 0) goto L_0x0138
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x0134 }
            r7.<init>()     // Catch:{ Exception -> 0x0134 }
            r0 = 0
        L_0x0090:
            int r1 = r2.length()     // Catch:{ Exception -> 0x0134 }
            if (r0 >= r1) goto L_0x0138
            com.mi.global.shop.buy.model.CreditCardItem r1 = new com.mi.global.shop.buy.model.CreditCardItem     // Catch:{ Exception -> 0x0134 }
            r1.<init>()     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ Exception -> 0x0134 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "card_type"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0134 }
            r1.b = r3     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ Exception -> 0x0134 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "card_token"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0134 }
            r1.d = r3     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ Exception -> 0x0134 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "card_no"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0134 }
            r1.e = r3     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ Exception -> 0x0134 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "card_mode"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0134 }
            r1.f = r3     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ Exception -> 0x0134 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "expiry_month"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r4 = r2.get(r0)     // Catch:{ Exception -> 0x0134 }
            org.json.JSONObject r4 = (org.json.JSONObject) r4     // Catch:{ Exception -> 0x0134 }
            java.lang.String r5 = "expiry_year"
            java.lang.Object r4 = r4.get(r5)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x0134 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0134 }
            r5.<init>()     // Catch:{ Exception -> 0x0134 }
            r5.append(r3)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = " / "
            r5.append(r3)     // Catch:{ Exception -> 0x0134 }
            r5.append(r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = r5.toString()     // Catch:{ Exception -> 0x0134 }
            r1.f6883a = r3     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ Exception -> 0x0134 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "name_on_card"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0134 }
            r1.g = r3     // Catch:{ Exception -> 0x0134 }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ Exception -> 0x0134 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ Exception -> 0x0134 }
            java.lang.String r4 = "card_brand"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0134 }
            r1.h = r3     // Catch:{ Exception -> 0x0134 }
            r7.add(r1)     // Catch:{ Exception -> 0x0134 }
            int r0 = r0 + 1
            goto L_0x0090
        L_0x0134:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0138:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.buy.ConfirmActivity.processResult(org.json.JSONObject):void");
    }

    public JSONObject getData() {
        return this.h;
    }

    public OrderPaymentInfo getOrderPaymentInfo() {
        return this.orderPaymentInfo;
    }

    public Intent getPaymentIntent() {
        return PayU.a(this).a(this.g, this.f, new PayU.PaymentMode[]{PayU.PaymentMode.CC, PayU.PaymentMode.DC, PayU.PaymentMode.NB, PayU.PaymentMode.EMI, PayU.PaymentMode.STORED_CARDS});
    }

    public HashMap<String, String> getpayParam() {
        return this.f;
    }

    public void setpayParam(HashMap<String, String> hashMap) {
        this.f = hashMap;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        c();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.title_bar_home) {
            onBackPressed();
            MiShopStatInterface.a("title_back", f6815a);
        }
    }

    public void onBackPressed() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        Fragment visibleFragment = getVisibleFragment();
        if (visibleFragment == null || visibleFragment.getTag() == null || !visibleFragment.getTag().equals(OrderdetailFragment.class.getName())) {
            if (visibleFragment != null) {
                String str = "";
                if (visibleFragment instanceof Cardfragment) {
                    str = Cardfragment.f6798a;
                } else if (visibleFragment instanceof NBfragment) {
                    str = NBfragment.f6834a;
                } else if (visibleFragment instanceof EMIfragment) {
                    str = EMIfragment.f6830a;
                } else if (visibleFragment instanceof CODfragment) {
                    str = CODfragment.f6797a;
                } else if (visibleFragment instanceof UPIFragment) {
                    str = "UPI";
                } else if (visibleFragment instanceof Walletfragment) {
                    str = "wallet";
                }
                if (!TextUtils.isEmpty(str)) {
                    MiShopStatInterface.a("return_click", str);
                }
            }
            super.onBackPressed();
            return;
        }
        MiShopStatInterface.a("return_click", "pay");
        redirectBack();
    }

    public void redirectBack() {
        CustomCancelDialog.Builder builder = new CustomCancelDialog.Builder(this);
        builder.a(getString(R.string.confirm_pay_hint_title)).b(getString(R.string.confirm_pay_hint_content)).a(getString(R.string.shop_yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!ConfirmActivity.this.getIntent().getBooleanExtra(ConfirmActivity.IS_FROM_CHECKOUT, false)) {
                    ConfirmActivity.this.finish();
                    return;
                }
                Intent intent = new Intent(ConfirmActivity.this, OrderListAcitvity.class);
                intent.putExtra("type", 4);
                intent.putExtra("backToUserCenter", 1);
                ConfirmActivity.this.startActivity(intent);
                ConfirmActivity.this.finish();
            }
        });
        this.s = builder.a();
        this.s.show();
    }

    public Fragment getVisibleFragment() {
        for (Fragment next : getSupportFragmentManager().getFragments()) {
            if (next != null && next.isVisible()) {
                return next;
            }
        }
        return null;
    }

    public void setPaymentFailedTip(Boolean bool) {
        if (this.d != null) {
            this.d.a(bool);
            new Handler().post(new Runnable() {
                public void run() {
                    if (!(ConfirmActivity.this.c.findFragmentById(R.id.confirm_fragment_top_container) instanceof OrderdetailFragment)) {
                        ConfirmActivity.this.getSupportFragmentManager().popBackStack();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (intent != null && !"".equals(intent)) {
            if (i2 != 100 || intent == null || !intent.hasExtra("result")) {
                if (i2 == 102) {
                    if (i3 == -1) {
                        final String stringExtra = intent.getStringExtra("tel");
                        if (this.orderPaymentInfo != null) {
                            this.orderPaymentInfo.d = stringExtra;
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    ((CustomTextView) ConfirmActivity.this.findViewById(R.id.buy_confirm_cod_phone_number_text)).setText(ShopApp.g().getString(R.string.user_address_phoneareacode) + stringExtra);
                                }
                            });
                            return;
                        }
                        return;
                    } else if (i3 == 0) {
                        return;
                    }
                }
                if (i2 == 101 && intent != null && intent.hasExtra("result")) {
                    try {
                        JSONObject jSONObject = new JSONObject(intent.getStringExtra("result"));
                        if (jSONObject.optBoolean("success")) {
                            e();
                            f();
                            setResult(-1, intent);
                            String optString = jSONObject.optString("url");
                            if (TextUtils.isEmpty(optString)) {
                                Intent intent2 = new Intent(this, SuccessAcitvity.class);
                                intent2.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", this.b);
                                startActivity(intent2);
                            } else {
                                Intent intent3 = new Intent(this, WebActivity.class);
                                intent3.putExtra("url", optString);
                                intent3.putExtra("fromPaySuccess", "1");
                                startActivity(intent3);
                            }
                            finish();
                            return;
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    this.d.a((Boolean) true);
                    getSupportFragmentManager().popBackStack();
                } else if (i2 != 258) {
                    setPaymentFailedTip(true);
                } else if (i3 != -1) {
                    this.d.a((Boolean) true);
                    getSupportFragmentManager().popBackStack();
                } else {
                    Intent intent4 = new Intent(this, PayResultWebActivity.class);
                    if (this.f != null) {
                        intent4.putExtra("url", this.f.get("url"));
                    }
                    startActivityForResult(intent4, 101);
                }
            } else {
                try {
                    JSONObject jSONObject2 = new JSONObject(intent.getStringExtra("result"));
                    if (jSONObject2.optBoolean("success")) {
                        e();
                        f();
                        setResult(-1, intent);
                        String optString2 = jSONObject2.optString("url");
                        if (TextUtils.isEmpty(optString2)) {
                            Intent intent5 = new Intent(this, SuccessAcitvity.class);
                            intent5.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", this.b);
                            startActivity(intent5);
                        } else {
                            Intent intent6 = new Intent(this, WebActivity.class);
                            intent6.putExtra("url", optString2);
                            intent6.putExtra("fromPaySuccess", "1");
                            startActivity(intent6);
                        }
                        finish();
                        return;
                    }
                } catch (JSONException e4) {
                    e4.printStackTrace();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                this.d.a((Boolean) true);
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    private void d() {
        ArrayList arrayList = new ArrayList();
        Iterator<BuyOrderItem> it = this.i.l.iterator();
        while (it.hasNext()) {
            BuyOrderItem next = it.next();
            HashMap hashMap = new HashMap();
            hashMap.put("name", next.f6882a);
            hashMap.put("id", next.e);
            hashMap.put("price", next.d);
            hashMap.put(FirebaseAnalytics.Param.QUANTITY, next.f);
            arrayList.add(hashMap);
        }
    }

    private void e() {
        ArrayList arrayList = new ArrayList();
        Iterator<BuyOrderItem> it = this.i.l.iterator();
        while (it.hasNext()) {
            BuyOrderItem next = it.next();
            HashMap hashMap = new HashMap();
            hashMap.put("name", next.f6882a);
            hashMap.put("id", next.e);
            hashMap.put("price", next.d);
            hashMap.put(FirebaseAnalytics.Param.QUANTITY, next.f);
            hashMap.put("coupon", "");
            arrayList.add(hashMap);
        }
    }

    public double getAmount() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(OrderPaymentInfo.f6888a, this.orderPaymentInfo);
        bundle.putParcelable("confirmOrder", this.i);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.codViewHelper != null) {
            this.codViewHelper.e();
        }
        hideLoading();
        if (this.u != null) {
            this.u.a();
            this.u = null;
        }
        super.onDestroy();
    }

    public void onPaymentOptionSelected(String str) {
        this.d.a((Boolean) false);
        LogUtil.b(f6815a, "onPaymentOptionSelected:" + str);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (str.equals(ShopApp.g().getString(R.string.buy_confirm_PaymentKey_NetBank))) {
            if (this.l == null) {
                this.l = new NBfragment();
            }
            beginTransaction.replace(R.id.confirm_fragment_top_container, this.l);
            beginTransaction.addToBackStack((String) null);
            beginTransaction.commitAllowingStateLoss();
        } else if (str.equals(ShopApp.g().getString(R.string.buy_confirm_PaymentKey_Cards))) {
            if (this.m == null) {
                this.m = new Cardfragment();
            }
            beginTransaction.replace(R.id.confirm_fragment_top_container, this.m);
            beginTransaction.addToBackStack((String) null);
            beginTransaction.commitAllowingStateLoss();
        } else if (str.equals(ShopApp.g().getString(R.string.buy_confirm_PaymentKey_EMI))) {
            if (this.n == null) {
                this.n = new EMIfragment();
            }
            beginTransaction.replace(R.id.confirm_fragment_top_container, this.n);
            beginTransaction.addToBackStack((String) null);
            beginTransaction.commitAllowingStateLoss();
        } else if (str.equals(ShopApp.g().getString(R.string.buy_confirm_PaymentKey_Wallet))) {
            if (this.r == null) {
                this.r = new Walletfragment();
            }
            beginTransaction.replace(R.id.confirm_fragment_top_container, this.r);
            beginTransaction.addToBackStack((String) null);
            beginTransaction.commitAllowingStateLoss();
        } else if (str.equals(ShopApp.g().getString(R.string.buy_confirm_PaymentKey_COD))) {
            this.o = new CODfragment();
            beginTransaction.replace(R.id.confirm_fragment_top_container, this.o);
            beginTransaction.addToBackStack((String) null);
            beginTransaction.commitAllowingStateLoss();
        } else if (str.equals(ShopApp.g().getString(R.string.buy_confirm_PaymentKey_Cardless_EMI))) {
            this.p = new CardlessEMIfragment();
            Bundle bundle = new Bundle();
            bundle.putString(CardlessEMIfragment.b, this.i.g);
            bundle.putString("order_id_extra", this.i.f6881a);
            this.p.setArguments(bundle);
            beginTransaction.replace(R.id.confirm_fragment_top_container, this.p);
            beginTransaction.addToBackStack((String) null);
            beginTransaction.commitAllowingStateLoss();
        } else if (str.equals(ShopApp.g().getString(R.string.buy_confirm_PaymentKey_UPI))) {
            this.q = new UPIFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putString("order_id_extra", this.i.f6881a);
            this.q.setArguments(bundle2);
            beginTransaction.replace(R.id.confirm_fragment_top_container, this.q);
            beginTransaction.addToBackStack((String) null);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    private void f() {
        AppEventsLogger newLogger = AppEventsLogger.newLogger(this);
        try {
            if (this.i != null && this.i.l != null) {
                Iterator<BuyOrderItem> it = this.i.l.iterator();
                while (it.hasNext()) {
                    BuyOrderItem next = it.next();
                    Bundle bundle = new Bundle();
                    bundle.putString(AppEventsConstants.EVENT_PARAM_NUM_ITEMS, next.b);
                    bundle.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, next.g);
                    bundle.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, next.d);
                    newLogger.logEvent(AppEventsConstants.EVENT_NAME_PURCHASED, Double.parseDouble(next.c), bundle);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onPaytmTransactionSuccess(String str) {
        e();
        f();
        if (TextUtils.isEmpty(str)) {
            Intent intent = new Intent(this, SuccessAcitvity.class);
            intent.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", this.b);
            startActivity(intent);
        } else {
            Intent intent2 = new Intent(this, WebActivity.class);
            intent2.putExtra("url", str);
            intent2.putExtra("fromPaySuccess", "1");
            startActivity(intent2);
        }
        finish();
    }

    public CountDownUtil getCountDownUtil() {
        return this.u;
    }

    public void setCountDownUtil(CountDownUtil countDownUtil) {
        this.u = countDownUtil;
    }
}
