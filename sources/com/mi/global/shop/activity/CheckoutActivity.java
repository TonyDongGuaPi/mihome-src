package com.mi.global.shop.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.adobe.xmp.XMPConst;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.google.gson.Gson;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.adapter.checkout.CheckoutListAdapter;
import com.mi.global.shop.buy.ConfirmActivity;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.model.SyncModel;
import com.mi.global.shop.newmodel.checkout.NewChangeAddressData;
import com.mi.global.shop.newmodel.checkout.NewChangeAddressResult;
import com.mi.global.shop.newmodel.checkout.NewCheckoutCartItem;
import com.mi.global.shop.newmodel.checkout.NewCheckoutData;
import com.mi.global.shop.newmodel.checkout.NewCheckoutResult;
import com.mi.global.shop.newmodel.checkout.NewSubmitData;
import com.mi.global.shop.newmodel.checkout.NewSubmitResult;
import com.mi.global.shop.newmodel.user.address.FourDeliveryData;
import com.mi.global.shop.newmodel.user.address.SmartBoxData;
import com.mi.global.shop.newmodel.user.address.SmartDetailItemData;
import com.mi.global.shop.newmodel.user.coupon.NewCouponData;
import com.mi.global.shop.newmodel.user.coupon.NewCouponItem;
import com.mi.global.shop.newmodel.user.coupon.NewPaymentCouponResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.user.AddressListActivity;
import com.mi.global.shop.user.CouponActivity;
import com.mi.global.shop.user.ExchangeCouponActivity;
import com.mi.global.shop.util.BaseTypeConvertUtil;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.global.shop.widget.dialog.CustomCloseDialog;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.util.HashMap;
import java.util.Iterator;

public class CheckoutActivity extends BaseActivity {
    public static String GST_CODE_S = "gst_code_s";
    public static final String ONE_CLICK_EXTRA = "one_click_extra";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f5351a = "CheckoutActivity";
    /* access modifiers changed from: private */
    public int A;
    /* access modifiers changed from: private */
    public boolean B;
    private DefaultRetryPolicy C = new DefaultRetryPolicy(15000, 1, 1.0f);
    @BindView(2131492930)
    View addAddressView;
    @BindView(2131492934)
    View addressContent;
    @BindView(2131492935)
    CustomTextView addressErrorTextView;
    @BindView(2131492940)
    View addressMarkView;
    @BindView(2131492932)
    CustomTextView addressTextView;
    @BindView(2131492943)
    View addressTipLayout;
    @BindView(2131492936)
    View addressView;
    /* access modifiers changed from: private */
    public NewCheckoutData b;
    @BindView(2131492998)
    CustomTextView bottomTotalTextView;
    /* access modifiers changed from: private */
    public String c;
    @BindView(2131492871)
    CustomTextView codNotAvailableTextView;
    @BindView(2131493199)
    CustomTextView consigneeTextView;
    @BindView(2131493164)
    CustomTextView couponTextView;
    @BindView(2131493216)
    View couponView;
    /* access modifiers changed from: private */
    public String d;
    @BindView(2131494206)
    TextView deliveryNameTextView;
    @BindView(2131493260)
    CustomTextView deliveryNotice;
    @BindView(2131494340)
    View dividerView;
    private String e = "";
    @BindView(2131493165)
    CustomTextView exchangeCouponTextView;
    @BindView(2131493330)
    View exchangeCouponView;
    private String f = "";
    @BindView(2131494010)
    View fourDeliveryRelativeLayout;
    private String g = "";
    @BindView(2131493964)
    RelativeLayout gstRelativeLayout;
    @BindView(2131493229)
    CustomTextView gstTextView;
    private String h = "0";
    private boolean i = true;
    private boolean j = true;
    private float k = Float.MAX_VALUE;
    private float l = Float.MAX_VALUE;
    @BindView(2131493691)
    View loadingView;
    private float m;
    public CustomCloseDialog mExchangeInvalidDialog;
    public ProgressDialog mProgressDialog;
    /* access modifiers changed from: private */
    public String n;
    @BindView(2131493746)
    View noticeContainer;
    /* access modifiers changed from: private */
    public String o;
    protected String orderId;
    /* access modifiers changed from: private */
    public String p;
    @BindView(2131493863)
    CustomTextView phoneTextView;
    @BindView(2131493871)
    CustomButtonView placeOrderButton;
    @BindView(2131493166)
    CustomTextView promoteTextView;
    @BindView(2131493907)
    View promoteView;
    private String q;
    private String r;
    private boolean s = false;
    @BindView(2131493214)
    CustomTextView selectCouponTextView;
    @BindView(2131494005)
    View selectCouponView;
    @BindView(2131494007)
    CustomTextView selectExchangeCouponTextView;
    @BindView(2131494009)
    CustomTextView selectExchangeCouponTitleView;
    @BindView(2131494008)
    View selectExchangeCouponView;
    @BindView(2131493167)
    CustomTextView shipTextView;
    @BindView(2131493168)
    CustomTextView subtotalTextView;
    private int t = 0;
    @BindView(2131493169)
    CustomTextView totalTextView;
    /* access modifiers changed from: private */
    public String u = "";
    /* access modifiers changed from: private */
    public FourDeliveryData v;
    /* access modifiers changed from: private */
    public SmartBoxData w;
    private final String x = "54";
    /* access modifiers changed from: private */
    public String y = "";
    private String z = "";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_activity_checkout);
        ButterKnife.bind((Activity) this);
        setTitle((CharSequence) getString(R.string.checkout_title));
        this.mBackView.setVisibility(0);
        this.mCartView.setVisibility(4);
        this.addressView.setFocusable(true);
        this.addressView.setFocusableInTouchMode(true);
        a();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: private */
    public void a(NewCheckoutResult newCheckoutResult) {
        this.b = newCheckoutResult.data;
        this.g = newCheckoutResult.data.checkoutInfo.address.zipcode;
        updateUi();
        if (this.b != null) {
            showPageNotice(this.b.pagemsg);
            if (!TextUtils.isEmpty(this.b.addressList)) {
                this.q = this.b.addressList;
            }
            this.r = new Gson().toJson((Object) this.b.region);
            if (TextUtils.isEmpty(this.b.addressList) || this.b.addressList.equalsIgnoreCase(XMPConst.ai)) {
                d();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (this.b.exchange_coupon_count >= 0) {
            CustomCloseDialog.Builder builder = new CustomCloseDialog.Builder(this);
            builder.b(str).a((Boolean) true);
            this.mExchangeInvalidDialog = builder.a();
            if (!this.mExchangeInvalidDialog.isShowing()) {
                this.mExchangeInvalidDialog.show();
            }
        }
    }

    private void a() {
        this.t = getIntent().getIntExtra(ONE_CLICK_EXTRA, 0);
        this.loadingView.setVisibility(0);
        final String[] b2 = b();
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(b2[0], NewCheckoutResult.class, new SimpleCallback<NewCheckoutResult>() {
            public void a(NewCheckoutResult newCheckoutResult) {
                CheckoutActivity.this.a(newCheckoutResult);
            }

            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError.networkResponse == null || volleyError.networkResponse.statusCode != 302 || b2.length <= 1) {
                    super.onErrorResponse(volleyError);
                    return;
                }
                SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(b2[1], NewCheckoutResult.class, new SimpleCallback<NewCheckoutResult>() {
                    public void a(NewCheckoutResult newCheckoutResult) {
                        CheckoutActivity.this.a(newCheckoutResult);
                        if (b2[1].startsWith("https")) {
                            SyncModel.useHttps = true;
                        } else {
                            SyncModel.useHttps = false;
                        }
                    }

                    public void a(String str) {
                        super.a(str);
                        CheckoutActivity.this.finish();
                    }
                });
                simpleProtobufRequest.setTag(CheckoutActivity.f5351a);
                RequestQueueUtil.a().add(simpleProtobufRequest);
            }

            public void a(String str) {
                super.a(str);
                CheckoutActivity.this.finish();
            }
        });
        simpleProtobufRequest.setTag(f5351a);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getString(R.string.shop_error_network);
        }
        MiToast.a((Context) this, (CharSequence) str, 0);
        setResult(0, new Intent());
        finish();
    }

    private void a(float f2, String str) {
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aI()).buildUpon();
        buildUpon.appendQueryParameter("totalpay", f2 + "");
        buildUpon.appendQueryParameter("addressid", str);
        if (this.t == 1) {
            buildUpon.appendQueryParameter("oneclick", "1");
        }
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewChangeAddressResult.class, new SimpleCallback<NewChangeAddressResult>() {
            public void a(String str) {
            }

            public void a(NewChangeAddressResult newChangeAddressResult) {
                CheckoutActivity.this.updateAddressView(newChangeAddressResult.data);
            }
        });
        simpleProtobufRequest.setTag(f5351a);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    private String[] b() {
        String[] p2 = ConnectionHelper.p();
        String[] strArr = new String[p2.length];
        for (int i2 = 0; i2 < p2.length; i2++) {
            Uri.Builder buildUpon = Uri.parse(p2[i2]).buildUpon();
            buildUpon.appendQueryParameter("jsontag", "true");
            buildUpon.appendQueryParameter("security", "true");
            if (this.t == 1) {
                buildUpon.appendQueryParameter("oneclick", "1");
            }
            if (Setting.a()) {
                buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
            }
            appendOriginalUrl(buildUpon, this);
            if (ShopApp.n()) {
                buildUpon.appendQueryParameter("ot", "5");
            }
            strArr[i2] = buildUpon.toString();
        }
        return strArr;
    }

    private String[] c() {
        String[] q2 = ConnectionHelper.q();
        String[] strArr = new String[q2.length];
        for (int i2 = 0; i2 < q2.length; i2++) {
            Uri.Builder buildUpon = Uri.parse(q2[i2]).buildUpon();
            buildUpon.appendQueryParameter("jsontag", "true");
            buildUpon.appendQueryParameter("security", "true");
            if (this.t == 1) {
                buildUpon.appendQueryParameter("oneclick", "1");
            }
            if (ShopApp.n()) {
                buildUpon.appendQueryParameter("ot", "5");
            }
            strArr[i2] = buildUpon.toString();
        }
        return strArr;
    }

    /* access modifiers changed from: protected */
    public void updateUi() {
        this.loadingView.setVisibility(8);
        this.m = BaseTypeConvertUtil.a(this.b.totalpay, 0.0f);
        if (this.b.checkoutInfo == null || this.b.checkoutInfo.address == null || TextUtils.isEmpty(this.b.checkoutInfo.address.address_id)) {
            this.s = true;
            this.addAddressView.setVisibility(0);
            this.addressContent.setVisibility(8);
            this.addressMarkView.setVisibility(8);
            this.placeOrderButton.setEnabled(false);
        } else {
            this.c = this.b.checkoutInfo.address.address_id;
            if (this.b.checkoutInfo.address.city != null) {
                this.d = this.b.checkoutInfo.address.city.id;
            }
            a(this.m, this.c);
            this.consigneeTextView.setText(this.b.checkoutInfo.address.consignee);
            CustomTextView customTextView = this.phoneTextView;
            customTextView.setText(getString(R.string.buy_confirm_COD_phonezone) + " " + this.b.checkoutInfo.address.tel);
            this.addressTextView.setText(a(this.b.checkoutInfo.address.address, this.b.checkoutInfo.address.zipcode, this.b.checkoutInfo.address.city != null ? this.b.checkoutInfo.address.city.name : ""));
            this.i = this.b.checkoutInfo.address.is_invalid == 0;
            this.j = this.b.checkoutInfo.address.can_cod == 1;
            if (LocaleHelper.g()) {
                this.k = BaseTypeConvertUtil.a(this.b.checkoutInfo.address.limit, Float.MAX_VALUE);
                this.l = BaseTypeConvertUtil.a(this.b.checkoutInfo.address.limit_cod, Float.MAX_VALUE);
            }
            e();
        }
        if (this.b.exchange_coupon_count <= 0) {
            this.selectExchangeCouponView.setVisibility(8);
        } else {
            this.selectExchangeCouponTextView.setText(getString(R.string.select_from_n_coupons));
        }
        CheckoutListAdapter checkoutListAdapter = new CheckoutListAdapter(this);
        ((NoScrollListView) findViewById(R.id.cartItemList)).setAdapter(checkoutListAdapter);
        if (this.b.cartInfo != null) {
            checkoutListAdapter.a(this.b.cartInfo.items);
            CustomTextView customTextView2 = this.subtotalTextView;
            customTextView2.setText(this.b.currency + this.b.cartInfo.productMoney);
        }
        if (this.b.couponsCount <= 0) {
            this.selectCouponView.setVisibility(8);
        } else {
            NewCouponData c2 = c(this.b.couponList);
            if (!(c2 == null || c2.coupons == null || c2.coupons.size() <= 0)) {
                NewCouponItem newCouponItem = c2.coupons.get(0);
                this.b.totalPayTxt = newCouponItem.useinfo.amount;
                this.b.shipmentExpense = newCouponItem.useinfo.shipment;
                this.selectCouponTextView.setText(newCouponItem.couponName);
                this.couponView.setVisibility(0);
                CustomTextView customTextView3 = this.couponTextView;
                customTextView3.setText("-" + this.b.currency + newCouponItem.useinfo.couponDiscountMoney);
                this.n = newCouponItem.couponId;
                this.o = newCouponItem.type;
            }
        }
        if (this.b.couponsCount <= 0 && this.b.exchange_coupon_count <= 0) {
            this.dividerView.setVisibility(8);
        }
        if (BaseTypeConvertUtil.a(this.b.shipmentExpense, 0.0f) <= 0.0f) {
            this.shipTextView.setText(R.string.free);
        } else {
            CustomTextView customTextView4 = this.shipTextView;
            customTextView4.setText(this.b.currency + this.b.shipmentExpenseTxt);
        }
        if (this.b.checkoutInfo != null && BaseTypeConvertUtil.a(this.b.checkoutInfo.activityDiscountMoney, 0.0f) > 0.0f) {
            this.promoteView.setVisibility(0);
            CustomTextView customTextView5 = this.promoteTextView;
            customTextView5.setText("-" + this.b.currency + this.b.checkoutInfo.activityDiscountMoneyTxt);
        }
        CustomTextView customTextView6 = this.totalTextView;
        customTextView6.setText(this.b.currency + this.b.totalPayTxt);
        CustomTextView customTextView7 = this.bottomTotalTextView;
        customTextView7.setText("Total  " + this.b.currency + this.b.totalPayTxt);
        this.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CheckoutActivity.this.placeOrder();
            }
        });
        this.addressView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CheckoutActivity.this.d();
            }
        });
        this.selectCouponView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this, CouponActivity.class);
                intent.putExtra("com.mi.global.shop.extra_user_coupon_type", "coupon_choose");
                if (!TextUtils.isEmpty(CheckoutActivity.this.b.couponList)) {
                    intent.putExtra("coupon_list", CheckoutActivity.this.b.couponList);
                }
                if (!TextUtils.isEmpty(CheckoutActivity.this.c)) {
                    intent.putExtra("address_id", CheckoutActivity.this.c);
                }
                if (!TextUtils.isEmpty(CheckoutActivity.this.d)) {
                    intent.putExtra("city_id", CheckoutActivity.this.d);
                }
                if (!TextUtils.isEmpty(CheckoutActivity.this.p)) {
                    intent.putExtra("coupon_id", CheckoutActivity.this.p);
                }
                CheckoutActivity.this.startActivityForResult(intent, 8);
            }
        });
        this.selectExchangeCouponView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CheckoutActivity.this.b != null && !TextUtils.isEmpty(CheckoutActivity.this.b.exchange_deny_reason)) {
                    CheckoutActivity.this.a(CheckoutActivity.this.b.exchange_deny_reason);
                } else if (!CheckoutActivity.this.B) {
                    CheckoutActivity.this.a(CheckoutActivity.this.getString(R.string.exchange_coupon_invalid_by_pin_code_dialog_title));
                } else if (CheckoutActivity.this.A == 1) {
                    CheckoutActivity.this.a(CheckoutActivity.this.getString(R.string.exchange_coupon_invalid_by_delivery_dialog_title));
                } else if (CheckoutActivity.this.A == 2) {
                    CheckoutActivity.this.a(CheckoutActivity.this.getString(R.string.exchange_coupon_invalid_by_smart_box_dialog_title));
                } else {
                    Intent intent = new Intent(CheckoutActivity.this, ExchangeCouponActivity.class);
                    intent.putExtra("com.mi.global.shop.extra_user_coupon_type", "coupon_choose");
                    if (!TextUtils.isEmpty(CheckoutActivity.this.b.exchange_coupon_list)) {
                        intent.putExtra("coupon_list", CheckoutActivity.this.b.exchange_coupon_list);
                    }
                    if (!TextUtils.isEmpty(CheckoutActivity.this.c)) {
                        intent.putExtra("address_id", CheckoutActivity.this.c);
                    }
                    if (!TextUtils.isEmpty(CheckoutActivity.this.d)) {
                        intent.putExtra("city_id", CheckoutActivity.this.d);
                    }
                    if (!TextUtils.isEmpty(CheckoutActivity.this.n)) {
                        intent.putExtra("coupon_id", CheckoutActivity.this.n);
                    }
                    if (!TextUtils.isEmpty(CheckoutActivity.this.o)) {
                        intent.putExtra("type", CheckoutActivity.this.o);
                    }
                    CheckoutActivity.this.startActivityForResult(intent, 26);
                }
            }
        });
        if (this.b.checkoutInfo != null && this.b.checkoutInfo.shipmentlist != null) {
            int i2 = 0;
            while (true) {
                if (i2 < this.b.checkoutInfo.shipmentlist.size()) {
                    if (!TextUtils.isEmpty(this.b.checkoutInfo.shipmentlist.get(i2).shipment_id) && "54".equals(this.b.checkoutInfo.shipmentlist.get(i2).shipment_id)) {
                        this.v = this.b.checkoutInfo.shipmentlist.get(i2);
                        break;
                    }
                    if (i2 == this.b.checkoutInfo.shipmentlist.size() - 1) {
                        this.v = null;
                    }
                    i2++;
                } else {
                    break;
                }
            }
        }
        this.fourDeliveryRelativeLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this, DeliveryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("mode", CheckoutActivity.this.A);
                if (CheckoutActivity.this.w != null) {
                    bundle.putSerializable("delivery_smart", CheckoutActivity.this.w);
                }
                if (CheckoutActivity.this.v != null) {
                    bundle.putSerializable("delivery_fourhour", CheckoutActivity.this.v);
                    bundle.putString("currency", CheckoutActivity.this.b.currency);
                }
                if (!TextUtils.isEmpty(CheckoutActivity.this.y)) {
                    bundle.putString("smartbox_id", CheckoutActivity.this.y);
                }
                if (!TextUtils.isEmpty(CheckoutActivity.this.p)) {
                    bundle.putString("coupon_id", CheckoutActivity.this.p);
                }
                intent.putExtras(bundle);
                CheckoutActivity.this.startActivityForResult(intent, 25);
            }
        });
        if (this.b.gstin) {
            this.gstRelativeLayout.setVisibility(0);
            this.gstTextView.setText(this.u);
        } else {
            this.gstRelativeLayout.setVisibility(8);
        }
        if (this.gstRelativeLayout.getVisibility() == 0) {
            this.gstRelativeLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(CheckoutActivity.this, GSTActivity.class);
                    intent.putExtra(CheckoutActivity.GST_CODE_S, CheckoutActivity.this.u);
                    CheckoutActivity.this.startActivityForResult(intent, 24);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        Intent intent = new Intent(this, AddressListActivity.class);
        intent.putExtra("com.mi.global.shop.extra_user_address_type", "address_choose");
        if (!TextUtils.isEmpty(this.c)) {
            intent.putExtra("address_id", this.c);
        }
        if (!TextUtils.isEmpty(this.q)) {
            intent.putExtra("address_list", this.q);
        }
        if (!TextUtils.isEmpty(this.r)) {
            intent.putExtra("region_list", this.r);
        }
        startActivityForResult(intent, 1);
    }

    private void e() {
        this.addressTipLayout.setVisibility(8);
        if (!this.j) {
            this.addressTipLayout.setVisibility(0);
            this.codNotAvailableTextView.setVisibility(0);
        } else {
            this.codNotAvailableTextView.setVisibility(8);
        }
        if (!this.i) {
            this.addressTipLayout.setVisibility(0);
            this.addressMarkView.setVisibility(0);
            this.addressErrorTextView.setVisibility(0);
            this.addressErrorTextView.setText(R.string.invalid_address);
            this.placeOrderButton.setEnabled(false);
        } else if (this.m > this.k && this.m <= this.l) {
            this.addressTipLayout.setVisibility(0);
            this.addressMarkView.setVisibility(8);
            this.addressErrorTextView.setVisibility(0);
            CustomTextView customTextView = this.addressErrorTextView;
            int i2 = R.string.no_prepayment_address;
            customTextView.setText(getString(i2, new Object[]{this.b.currency + this.k}));
            this.placeOrderButton.setEnabled(true);
        } else if (this.m > this.l && this.m <= this.k) {
            this.addressTipLayout.setVisibility(0);
            this.addressMarkView.setVisibility(8);
            this.addressErrorTextView.setVisibility(0);
            CustomTextView customTextView2 = this.addressErrorTextView;
            int i3 = R.string.no_COD_limit_address;
            customTextView2.setText(getString(i3, new Object[]{this.b.currency + this.l}));
            this.placeOrderButton.setEnabled(true);
        } else if (this.m <= this.k || this.m <= this.l) {
            this.addressMarkView.setVisibility(8);
            this.addressErrorTextView.setVisibility(8);
            this.placeOrderButton.setEnabled(true);
        } else {
            this.addressTipLayout.setVisibility(0);
            this.addressMarkView.setVisibility(0);
            this.addressErrorTextView.setVisibility(0);
            CustomTextView customTextView3 = this.addressErrorTextView;
            int i4 = R.string.no_devivery_address;
            customTextView3.setText(getString(i4, new Object[]{this.b.currency + Math.max(this.k, this.l)}));
            this.placeOrderButton.setEnabled(false);
        }
    }

    public void updateAddressView(NewChangeAddressData newChangeAddressData) {
        if (newChangeAddressData != null) {
            if (newChangeAddressData.valid) {
                this.addressMarkView.setVisibility(8);
                if (newChangeAddressData.isCos) {
                    this.placeOrderButton.setEnabled(false);
                } else {
                    this.placeOrderButton.setEnabled(true);
                }
            } else {
                this.addressMarkView.setVisibility(0);
                this.placeOrderButton.setEnabled(false);
            }
            this.addressTipLayout.setVisibility(8);
            if (TextUtils.isEmpty(newChangeAddressData.codtext)) {
                this.codNotAvailableTextView.setVisibility(8);
            } else {
                this.addressTipLayout.setVisibility(0);
                this.codNotAvailableTextView.setVisibility(0);
                this.codNotAvailableTextView.setText(newChangeAddressData.codtext);
            }
            if (TextUtils.isEmpty(newChangeAddressData.producttext)) {
                this.addressErrorTextView.setVisibility(8);
            } else {
                this.addressTipLayout.setVisibility(0);
                this.addressErrorTextView.setVisibility(0);
                this.addressErrorTextView.setText(newChangeAddressData.producttext);
            }
            if (newChangeAddressData.shipmentlist != null) {
                int i2 = 0;
                while (true) {
                    if (i2 < newChangeAddressData.shipmentlist.size()) {
                        if (!TextUtils.isEmpty(newChangeAddressData.shipmentlist.get(i2).shipment_id) && "54".equals(newChangeAddressData.shipmentlist.get(i2).shipment_id)) {
                            this.v = newChangeAddressData.shipmentlist.get(i2);
                            break;
                        }
                        if (i2 == newChangeAddressData.shipmentlist.size() - 1) {
                            this.v = null;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            } else {
                this.v = null;
            }
            if (!TextUtils.isEmpty(newChangeAddressData.hint)) {
                this.deliveryNotice.setText(newChangeAddressData.hint);
                this.noticeContainer.setVisibility(0);
                this.w = newChangeAddressData.smartboxdata;
            } else {
                this.noticeContainer.setVisibility(8);
                this.w = null;
            }
            this.B = newChangeAddressData.can_exchange;
            if (!this.B) {
                h();
            }
        }
    }

    private String a(String str, String str2, String str3) {
        String[] split = Html.fromHtml(str).toString().split("\\[\\-addr\\-\\]");
        if (split.length >= 3) {
            return split[1] + " " + split[2] + "\n" + str2 + " " + split[0] + " " + str3;
        } else if (split.length == 2) {
            return split[1] + "\n" + str2 + " " + split[0] + " " + str3;
        } else {
            return str2 + " " + split[0] + " " + str3;
        }
    }

    private String a(String str, String str2, String str3, String str4, String str5) {
        if (!TextUtils.isEmpty(str4)) {
            str = str + "  " + str4;
        }
        if (!TextUtils.isEmpty(str3)) {
            str2 = str2 + "  " + str3;
        }
        if (!TextUtils.isEmpty(str5)) {
            str2 = str2 + "  " + str5;
        }
        return str + "\n" + str2;
    }

    private Boolean f() {
        if (!TextUtils.isEmpty(this.c)) {
            return true;
        }
        MiToast.a((Context) this, R.string.user_address_empty, 0);
        return false;
    }

    private void g() {
        LogUtil.b(f5351a, "recordCheckoutEvent start");
        AppEventsLogger newLogger = AppEventsLogger.newLogger(this);
        try {
            if (this.b != null && this.b.cartInfo != null && this.b.cartInfo.items != null) {
                Iterator<NewCheckoutCartItem> it = this.b.cartInfo.items.iterator();
                while (it.hasNext()) {
                    NewCheckoutCartItem next = it.next();
                    Bundle bundle = new Bundle();
                    bundle.putString(AppEventsConstants.EVENT_PARAM_NUM_ITEMS, String.valueOf(next.num));
                    bundle.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, next.goodsId);
                    bundle.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, next.price);
                    newLogger.logEvent(AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT, Double.parseDouble(this.b.totalpay), bundle);
                    LogUtil.b(f5351a, "recordCheckoutEvent finished");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void submitSuccess(NewSubmitResult newSubmitResult) {
        this.u = "";
        NewSubmitData newSubmitData = newSubmitResult.data;
        if (newSubmitData != null) {
            g();
            this.orderId = newSubmitData.order_id;
            MiShopStatInterface.b("order", f5351a, "", "order_id", this.orderId);
            String str = newSubmitData.link;
            boolean z2 = newSubmitData.is_zero_order;
            String replaceAll = str.replaceAll("\\/", "/");
            String substring = replaceAll.substring(replaceAll.indexOf("/in/buy/confirm?id=") + "/in/buy/confirm?id=".length());
            if (substring.indexOf(38) >= 0) {
                substring = substring.substring(0, substring.indexOf(38));
            }
            if (LocaleHelper.g()) {
                if (z2) {
                    Intent intent = new Intent(this, OrderViewActivity.class);
                    intent.putExtra("orderview_orderid", this.orderId);
                    startActivity(intent);
                    setResult(-1, new Intent());
                    finish();
                } else {
                    this.orderId = substring;
                    Intent intent2 = new Intent(this, ConfirmActivity.class);
                    intent2.putExtra(ConfirmActivity.IS_FROM_CHECKOUT, true);
                    intent2.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", this.orderId);
                    startActivity(intent2);
                    setResult(-1, new Intent());
                    finish();
                }
                updateShoppingCart(0);
                return;
            }
            Intent intent3 = new Intent();
            intent3.putExtra("url", replaceAll);
            setResult(-1, intent3);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void placeOrder() {
        if (f().booleanValue()) {
            final HashMap hashMap = new HashMap();
            hashMap.put("Checkout[best_time]", "");
            hashMap.put("Checkout[invoice_type]", "");
            hashMap.put("Checkout[invoice_title]", "");
            hashMap.put("Checkout[email]", "");
            hashMap.put("Checkout[is_donate]", "");
            hashMap.put("Checkout[couponsValue]", TextUtils.isEmpty(this.n) ? "" : this.n);
            hashMap.put("Checkout[couponsType]", TextUtils.isEmpty(this.n) ? "0" : "2");
            hashMap.put("Checkout[address_id]", this.c);
            hashMap.put("Checkout[authcode]", "");
            hashMap.put("Checkout[shipment_id]", this.h);
            hashMap.put("Checkout[invoice_company_code]", this.u);
            hashMap.put("Checkout[smartbox_id]", this.y);
            hashMap.put("Checkout[address]", this.z);
            hashMap.put("Checkout[zipcode]", this.g);
            hashMap.put("Checkout[city]", this.e);
            hashMap.put("Checkout[landmark]", this.f);
            hashMap.put("Checkout[exchange_voucher_id]", !TextUtils.isEmpty(this.p) ? this.p : "");
            final String[] c2 = c();
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(c2[0], NewSubmitResult.class, hashMap, new SimpleCallback<NewSubmitResult>() {
                public void a(NewSubmitResult newSubmitResult) {
                    CheckoutActivity.this.submitSuccess(newSubmitResult);
                }

                public void onErrorResponse(VolleyError volleyError) {
                    if (volleyError.networkResponse == null || volleyError.networkResponse.statusCode != 302 || c2.length <= 1) {
                        super.onErrorResponse(volleyError);
                        return;
                    }
                    SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(c2[1], NewSubmitResult.class, hashMap, new SimpleCallback<NewSubmitResult>() {
                        public void a(NewSubmitResult newSubmitResult) {
                            CheckoutActivity.this.submitSuccess(newSubmitResult);
                            if (c2[1].startsWith("https")) {
                                SyncModel.useHttps = true;
                            } else {
                                SyncModel.useHttps = false;
                            }
                        }

                        public void a(String str) {
                            super.a(str);
                            if (CheckoutActivity.this.mProgressDialog != null) {
                                CheckoutActivity.this.mProgressDialog.dismiss();
                            }
                            CheckoutActivity.this.finish();
                        }
                    });
                    simpleProtobufRequest.setTag(CheckoutActivity.f5351a);
                    RequestQueueUtil.a().add(simpleProtobufRequest);
                }

                public void a(String str) {
                    super.a(str);
                    if (CheckoutActivity.this.mProgressDialog != null) {
                        CheckoutActivity.this.mProgressDialog.dismiss();
                    }
                    CheckoutActivity.this.finish();
                }
            });
            simpleProtobufRequest.setTag(f5351a);
            simpleProtobufRequest.setRetryPolicy(this.C);
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

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 1) {
            String stringPref = Utils.Preference.getStringPref(ShopApp.g(), "pref_address", "");
            if (!TextUtils.isEmpty(stringPref)) {
                this.q = stringPref;
            }
            if (i3 == -1) {
                if (this.s) {
                    this.s = false;
                    this.addAddressView.setVisibility(8);
                    this.addressContent.setVisibility(0);
                }
                String str = this.c;
                this.c = intent.getStringExtra("address_id");
                this.d = intent.getStringExtra("city_id");
                if (TextUtils.isEmpty(this.c) || !this.c.equals(str)) {
                    this.noticeContainer.setVisibility(8);
                    this.w = null;
                    this.v = null;
                    this.deliveryNameTextView.setText("Home Delivery");
                    this.h = "0";
                    this.y = "";
                    this.z = "";
                    this.e = "";
                    this.f = "";
                    this.A = 0;
                }
                a(this.m, this.c);
                this.g = intent.getStringExtra("zipcode");
                this.consigneeTextView.setText(intent.getStringExtra("name"));
                CustomTextView customTextView = this.phoneTextView;
                customTextView.setText(getString(R.string.buy_confirm_COD_phonezone) + " " + intent.getStringExtra("tel"));
                this.addressTextView.setText(a(intent.getStringExtra("address"), intent.getStringExtra("zipcode"), intent.getStringExtra("city"), intent.getStringExtra("landmark"), intent.getStringExtra("state")));
                this.i = "0".equals(intent.getStringExtra("is_invalid"));
                this.j = "1".equals(intent.getStringExtra("can_cod"));
                this.k = BaseTypeConvertUtil.a(intent.getStringExtra("limit"), Float.MAX_VALUE);
                this.l = BaseTypeConvertUtil.a(intent.getStringExtra("limit_cod"), Float.MAX_VALUE);
                e();
            }
        } else if (i2 != 8) {
            switch (i2) {
                case 24:
                    if (i3 == -1) {
                        this.u = intent.getStringExtra(GST_CODE_S);
                        if (this.u == null) {
                            this.u = "";
                        }
                        if (this.gstTextView != null) {
                            this.gstTextView.setText(this.u);
                            return;
                        }
                        return;
                    }
                    return;
                case 25:
                    if (i3 == -1) {
                        this.y = "";
                        this.z = "";
                        this.e = "";
                        this.f = "";
                        this.A = intent.getIntExtra("mode", 0);
                        if (this.A == 1) {
                            this.deliveryNameTextView.setText("Express Delivery");
                            this.h = "54";
                            h();
                            return;
                        } else if (this.A == 2) {
                            SmartDetailItemData smartDetailItemData = (SmartDetailItemData) intent.getSerializableExtra("smart_selected");
                            this.deliveryNameTextView.setText(smartDetailItemData.shortName);
                            this.h = "6";
                            this.y = smartDetailItemData.id;
                            this.z = smartDetailItemData.address;
                            this.e = smartDetailItemData.city;
                            this.f = smartDetailItemData.shortName;
                            h();
                            return;
                        } else {
                            this.deliveryNameTextView.setText("Home Delivery");
                            this.h = "0";
                            return;
                        }
                    } else {
                        return;
                    }
                case 26:
                    if (i3 == -1 && intent != null) {
                        this.p = intent.getStringExtra("coupon_id");
                        if (!TextUtils.isEmpty(this.p)) {
                            CustomTextView customTextView2 = this.selectExchangeCouponTextView;
                            customTextView2.setText(getString(R.string.user_exchange_coupon_item_title) + " " + this.b.currency + intent.getStringExtra("couponDiscountMoney"));
                            this.exchangeCouponView.setVisibility(0);
                            CustomTextView customTextView3 = this.exchangeCouponTextView;
                            customTextView3.setText("-" + this.b.currency + intent.getStringExtra("couponDiscountMoney"));
                        } else {
                            this.selectExchangeCouponTextView.setText(getString(R.string.select_from_n_coupons));
                            this.exchangeCouponView.setVisibility(8);
                        }
                        String stringExtra = intent.getStringExtra("shipment");
                        if (BaseTypeConvertUtil.a(stringExtra, 0.0f) <= 0.0f) {
                            this.shipTextView.setText(R.string.free);
                        } else if (this.b != null) {
                            CustomTextView customTextView4 = this.shipTextView;
                            customTextView4.setText(this.b.currency + stringExtra);
                        } else {
                            return;
                        }
                        CustomTextView customTextView5 = this.totalTextView;
                        customTextView5.setText(this.b.currency + intent.getStringExtra("amount"));
                        CustomTextView customTextView6 = this.bottomTotalTextView;
                        customTextView6.setText(this.b.currency + intent.getStringExtra("amount"));
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (i3 == -1 && intent != null) {
            this.n = intent.getStringExtra("coupon_id");
            this.o = intent.getStringExtra("type");
            if (!TextUtils.isEmpty(this.n)) {
                this.couponView.setVisibility(0);
                this.selectCouponTextView.setText(intent.getStringExtra("name"));
            } else {
                this.selectCouponTextView.setText(getString(R.string.select_from_n_coupons));
                this.couponView.setVisibility(8);
            }
            String stringExtra2 = intent.getStringExtra("shipment");
            if (BaseTypeConvertUtil.a(stringExtra2, 0.0f) <= 0.0f) {
                this.shipTextView.setText(R.string.free);
            } else if (this.b != null) {
                CustomTextView customTextView7 = this.shipTextView;
                customTextView7.setText(this.b.currency + stringExtra2);
            } else {
                return;
            }
            CustomTextView customTextView8 = this.couponTextView;
            customTextView8.setText("-" + this.b.currency + intent.getStringExtra("couponDiscountMoney"));
            CustomTextView customTextView9 = this.totalTextView;
            customTextView9.setText(this.b.currency + intent.getStringExtra("amount"));
            CustomTextView customTextView10 = this.bottomTotalTextView;
            customTextView10.setText(this.b.currency + intent.getStringExtra("amount"));
        }
    }

    private void h() {
        if (!TextUtils.isEmpty(this.p)) {
            HashMap hashMap = new HashMap(6);
            hashMap.put("id", this.d);
            hashMap.put("address_id", this.c);
            hashMap.put(UrlConstants.payment, "55");
            hashMap.put("cardtype", "no");
            if (!TextUtils.isEmpty(this.n)) {
                hashMap.put("value", this.n);
            }
            hashMap.put("exchange_coupon_id", "0");
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.bo(), NewPaymentCouponResult.class, hashMap, new SimpleCallback<NewPaymentCouponResult>() {
                public void a(NewPaymentCouponResult newPaymentCouponResult) {
                    if (newPaymentCouponResult != null && newPaymentCouponResult.data != null && newPaymentCouponResult.data.checkout != null) {
                        String unused = CheckoutActivity.this.p = null;
                        CheckoutActivity.this.selectExchangeCouponTextView.setText(CheckoutActivity.this.getString(R.string.select_from_n_coupons));
                        CheckoutActivity.this.exchangeCouponView.setVisibility(8);
                        String str = newPaymentCouponResult.data.checkout.shipment;
                        if (BaseTypeConvertUtil.a(newPaymentCouponResult.data.checkout.shipment, 0.0f) <= 0.0f) {
                            CheckoutActivity.this.shipTextView.setText(R.string.free);
                        } else if (CheckoutActivity.this.b != null) {
                            CustomTextView customTextView = CheckoutActivity.this.shipTextView;
                            customTextView.setText(CheckoutActivity.this.b.currency + str);
                        }
                        CustomTextView customTextView2 = CheckoutActivity.this.totalTextView;
                        customTextView2.setText(CheckoutActivity.this.b.currency + newPaymentCouponResult.data.checkout.need_pay_amount);
                        CustomTextView customTextView3 = CheckoutActivity.this.bottomTotalTextView;
                        customTextView3.setText(CheckoutActivity.this.b.currency + newPaymentCouponResult.data.checkout.need_pay_amount);
                    }
                }

                public void a(String str) {
                    super.a(str);
                }
            });
            simpleProtobufRequest.setTag(f5351a);
            RequestQueueUtil.a().add(simpleProtobufRequest);
        }
    }

    private NewCouponData c(String str) {
        try {
            return (NewCouponData) new Gson().fromJson("{\"coupons\":" + str + "}", NewCouponData.class);
        } catch (Exception e2) {
            LogUtil.b(f5351a, "JSON parse error");
            e2.printStackTrace();
            CrashReport.postCrash(Thread.currentThread(), (Throwable) e2);
            return null;
        }
    }
}
