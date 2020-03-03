package com.mi.global.shop.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.Request;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.adapter.OrderProgressAdapter;
import com.mi.global.shop.buy.ConfirmActivity;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.newmodel.NewSimpleResult;
import com.mi.global.shop.newmodel.order.NewDeliversData;
import com.mi.global.shop.newmodel.order.NewOrderViewData;
import com.mi.global.shop.newmodel.order.NewOrderViewResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.user.OrderViewItemListViewAdapter;
import com.mi.global.shop.user.OrderViewSuborderListViewAdapter;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.CountDownUtil;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.global.shop.widget.dialog.CustomCancelDialog;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import java.util.ArrayList;

public class OrderViewActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5405a = "OrderViewActivity";
    private CustomTextView A;
    private CustomTextView B;
    private CustomTextView C;
    private CustomTextView D;
    private CustomTextView E;
    private CustomTextView F;
    private CustomTextView G;
    private CustomTextView H;
    private CustomTextView I;
    private CustomTextView J;
    private CustomTextView K;
    private CustomButtonView L;
    private CustomButtonView M;
    private CustomButtonView N;
    private OrderViewItemListViewAdapter O;
    private OrderViewSuborderListViewAdapter P;
    private NoScrollListView Q;
    private NoScrollListView R;
    private RecyclerView S;
    private OrderProgressAdapter T;
    /* access modifiers changed from: private */
    public String U;
    private CountDownUtil V;
    private CustomTextView W;
    private View b;
    private String c;
    /* access modifiers changed from: private */
    public NewOrderViewData d;
    /* access modifiers changed from: private */
    public ArrayList<NewDeliversData> e;
    @BindView(2131493797)
    LinearLayout exchangeCouponGroup;
    @BindView(2131493796)
    CustomTextView exchangeCouponView;
    private boolean f;
    private boolean g;
    private boolean h;
    /* access modifiers changed from: private */
    public boolean i;
    /* access modifiers changed from: private */
    public boolean j;
    /* access modifiers changed from: private */
    public boolean k;
    private boolean l;
    @BindView(2131493674)
    LinearLayout llNotice;
    private LinearLayout m;
    public ProgressDialog mProgressDialog;
    private LinearLayout n;
    private LinearLayout o;
    private LinearLayout p;
    private LinearLayout q;
    private LinearLayout r;
    private LinearLayout s;
    @BindView(2131494020)
    CustomTextView showTag;
    @BindView(2131494021)
    CustomTextView showTips;
    private CustomTextView t;
    @BindView(2131494128)
    LinearLayout tipsShadow;
    private CustomTextView u;
    private CustomTextView v;
    private CustomTextView w;
    private CustomTextView x;
    private CustomTextView y;
    private CustomTextView z;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            LogUtil.b(f5405a, "onCreate, savedInstanceState:" + bundle.toString());
            this.d = (NewOrderViewData) bundle.getParcelable("orderViewModel");
        }
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_orderview_activity);
        ButterKnife.bind((Activity) this);
        setTitle(R.string.orderview_title);
        this.c = getIntent().getStringExtra("orderview_orderid");
        if (TextUtils.isEmpty(this.c)) {
            LogUtil.b(f5405a, "OrderId is empty");
            finish();
            return;
        }
        this.f = this.c.length() > 16;
        this.mCartView.setVisibility(4);
        this.b = findViewById(R.id.title_bar_home);
        this.b.setVisibility(0);
        this.b.setOnClickListener(this);
        a();
        c();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("orderViewModel", this.d);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        if (this.V != null) {
            this.V.a();
            this.V = null;
        }
        super.onDestroy();
    }

    private void a() {
        this.q = (LinearLayout) findViewById(R.id.orderview_promoteLL);
        this.r = (LinearLayout) findViewById(R.id.orderview_suborderLL);
        this.m = (LinearLayout) findViewById(R.id.orderview_subtotalLL);
        this.n = (LinearLayout) findViewById(R.id.orderview_shippingLL);
        this.o = (LinearLayout) findViewById(R.id.orderview_totalLL);
        this.p = (LinearLayout) findViewById(R.id.orderview_discountLL);
        this.s = (LinearLayout) findViewById(R.id.orderview_itemlistrLL);
        this.t = (CustomTextView) findViewById(R.id.orderview_status);
        this.u = (CustomTextView) findViewById(R.id.orderview_subtotal);
        this.w = (CustomTextView) findViewById(R.id.orderview_promote);
        this.v = (CustomTextView) findViewById(R.id.orderview_shipping);
        this.x = (CustomTextView) findViewById(R.id.orderview_total);
        this.y = (CustomTextView) findViewById(R.id.orderview_discount);
        this.z = (CustomTextView) findViewById(R.id.orderview_total_bottom);
        this.A = (CustomTextView) findViewById(R.id.orderview_paywarningSpan);
        this.B = (CustomTextView) findViewById(R.id.orderview_paywarning);
        this.I = (CustomTextView) findViewById(R.id.orderview_transportnum);
        this.J = (CustomTextView) findViewById(R.id.orderview_deliveryid);
        this.K = (CustomTextView) findViewById(R.id.orderview_transportcompany);
        this.Q = (NoScrollListView) findViewById(R.id.orderview_itemlist);
        this.R = (NoScrollListView) findViewById(R.id.orderview_suborderlist);
        this.C = (CustomTextView) findViewById(R.id.orderview_addline1);
        this.D = (CustomTextView) findViewById(R.id.orderview_addline2);
        this.E = (CustomTextView) findViewById(R.id.orderview_addline3);
        this.F = (CustomTextView) findViewById(R.id.orderview_email);
        this.G = (CustomTextView) findViewById(R.id.orderview_orderid);
        this.H = (CustomTextView) findViewById(R.id.orderview_time);
        this.L = (CustomButtonView) findViewById(R.id.orderview_payBtn);
        this.M = (CustomButtonView) findViewById(R.id.orderview_cancelBtn);
        this.N = (CustomButtonView) findViewById(R.id.orderview_tracebtn);
        this.S = (RecyclerView) findViewById(R.id.order_progress_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.S.setLayoutManager(linearLayoutManager);
        this.T = new OrderProgressAdapter(this, this);
        this.S.setAdapter(this.T);
        this.W = (CustomTextView) findViewById(R.id.tv_deliver_time);
    }

    /* access modifiers changed from: private */
    public void a(NewOrderViewData newOrderViewData) {
        if (newOrderViewData != null) {
            this.l = false;
            this.k = false;
            this.j = false;
            this.i = false;
            this.h = false;
            this.g = false;
            if (this.e != null) {
                if (this.e.size() == 1 && !TextUtils.isEmpty(this.e.get(0).express_sn) && !this.e.get(0).express_sn.equals("0")) {
                    this.h = true;
                }
                if (this.e.size() > 1) {
                    this.g = true;
                }
            }
            if (!(newOrderViewData.orderInfo == null || newOrderViewData.orderInfo.order_status_info == null || newOrderViewData.orderInfo.order_status_info.next == null)) {
                for (int i2 = 0; i2 < newOrderViewData.orderInfo.order_status_info.next.size(); i2++) {
                    String str = newOrderViewData.orderInfo.order_status_info.next.get(i2);
                    if (Tags.Order.ORDER_NEXT_PAY.equalsIgnoreCase(str)) {
                        this.i = true;
                    }
                    if (Tags.Order.ORDER_NEXT_CANCEL.equalsIgnoreCase(str)) {
                        this.j = true;
                    }
                    if ("REFOUND_APPLY".equalsIgnoreCase(str)) {
                        this.k = true;
                    }
                }
            }
            if (newOrderViewData.orderInfo != null && !TextUtils.isEmpty(newOrderViewData.orderInfo.reduce_price_txt) && Integer.parseInt(newOrderViewData.orderInfo.reduce_price_txt.replace(",", "")) != 0) {
                this.l = true;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0183  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0192  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(com.mi.global.shop.newmodel.order.NewOrderViewData r10) {
        /*
            r9 = this;
            if (r10 != 0) goto L_0x0003
            return
        L_0x0003:
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewOrderStatusInfo r0 = r0.order_status_info
            if (r0 == 0) goto L_0x0014
            com.mi.global.shop.widget.CustomTextView r0 = r9.t
            com.mi.global.shop.newmodel.order.NewOrderInfo r1 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewOrderStatusInfo r1 = r1.order_status_info
            java.lang.String r1 = r1.info
            r0.setText(r1)
        L_0x0014:
            com.mi.global.shop.newmodel.order.NewOrderViewData$ExchangeCoupon r0 = r10.exchange_coupon
            r1 = 8
            r2 = 0
            if (r0 == 0) goto L_0x005d
            com.mi.global.shop.newmodel.order.NewOrderViewData$ExchangeCoupon r0 = r10.exchange_coupon
            int r0 = r0.amount
            if (r0 == 0) goto L_0x005d
            android.widget.LinearLayout r0 = r9.exchangeCouponGroup
            r0.setVisibility(r2)
            com.mi.global.shop.widget.CustomTextView r0 = r9.exchangeCouponView
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "-"
            r3.append(r4)
            java.lang.String r4 = com.mi.global.shop.locale.LocaleHelper.e()
            r3.append(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            com.mi.global.shop.newmodel.order.NewOrderViewData$ExchangeCoupon r5 = r10.exchange_coupon
            int r5 = r5.amount
            r4.append(r5)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = com.mi.global.shop.buy.payu.PayUtil.a(r4)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.setText(r3)
            goto L_0x0062
        L_0x005d:
            android.widget.LinearLayout r0 = r9.exchangeCouponGroup
            r0.setVisibility(r1)
        L_0x0062:
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            if (r0 == 0) goto L_0x0113
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            java.lang.String r0 = r0.shipment_expense_txt
            java.lang.String r0 = r0.trim()
            java.lang.String r3 = "0"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0082
            com.mi.global.shop.widget.CustomTextView r0 = r9.v
            int r3 = com.mi.global.shop.R.string.orderview_freedelivery
            java.lang.String r3 = r9.getString(r3)
            r0.setText(r3)
            goto L_0x009e
        L_0x0082:
            com.mi.global.shop.widget.CustomTextView r0 = r9.v
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = com.mi.global.shop.locale.LocaleHelper.e()
            r3.append(r4)
            com.mi.global.shop.newmodel.order.NewOrderInfo r4 = r10.orderInfo
            java.lang.String r4 = r4.shipment_expense_txt
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.setText(r3)
        L_0x009e:
            com.mi.global.shop.widget.CustomTextView r0 = r9.x
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = com.mi.global.shop.locale.LocaleHelper.e()
            r3.append(r4)
            com.mi.global.shop.newmodel.order.NewOrderInfo r4 = r10.orderInfo
            java.lang.String r4 = r4.goods_amount_txt
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.setText(r3)
            com.mi.global.shop.widget.CustomTextView r0 = r9.y
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "-"
            r3.append(r4)
            java.lang.String r4 = com.mi.global.shop.locale.LocaleHelper.e()
            r3.append(r4)
            com.mi.global.shop.newmodel.order.NewOrderInfo r4 = r10.orderInfo
            java.lang.String r4 = r4.reduce_price_txt
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.setText(r3)
            com.mi.global.shop.widget.CustomTextView r0 = r9.z
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = com.mi.global.shop.locale.LocaleHelper.e()
            r3.append(r4)
            com.mi.global.shop.newmodel.order.NewOrderInfo r4 = r10.orderInfo
            java.lang.String r4 = r4.goods_amount_txt
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.setText(r3)
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            java.lang.String r0 = r0.arrival_time
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0113
            com.mi.global.shop.widget.CustomTextView r0 = r9.W
            r0.setVisibility(r2)
            com.mi.global.shop.widget.CustomTextView r0 = r9.W
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            java.lang.String r3 = r3.arrival_time
            android.text.Spanned r3 = android.text.Html.fromHtml(r3)
            r0.setText(r3)
        L_0x0113:
            com.mi.global.shop.widget.CustomTextView r0 = r9.u
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = com.mi.global.shop.locale.LocaleHelper.e()
            r3.append(r4)
            java.lang.String r4 = r10.goodsAmt
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.setText(r3)
            boolean r0 = r9.f
            if (r0 == 0) goto L_0x014a
            android.widget.LinearLayout r0 = r9.m
            r0.setVisibility(r1)
            android.widget.LinearLayout r0 = r9.n
            r0.setVisibility(r1)
            android.widget.LinearLayout r0 = r9.q
            r0.setVisibility(r1)
            android.widget.LinearLayout r0 = r9.o
            r0.setVisibility(r1)
            android.widget.LinearLayout r0 = r9.p
            r0.setVisibility(r1)
        L_0x014a:
            boolean r0 = r9.i
            if (r0 == 0) goto L_0x01b5
            com.mi.global.shop.widget.CustomButtonView r0 = r9.L
            r0.setVisibility(r2)
            com.mi.global.shop.widget.CustomButtonView r0 = r9.L
            r0.setOnClickListener(r9)
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            if (r0 == 0) goto L_0x01ba
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            java.lang.String r0 = r0.ttl
            if (r0 == 0) goto L_0x01ba
            java.lang.String r0 = r9.U
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x01ba
            r3 = 0
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo     // Catch:{ Exception -> 0x017b }
            java.lang.String r0 = r0.ttl     // Catch:{ Exception -> 0x017b }
            long r5 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x017b }
            java.lang.String r0 = r9.U     // Catch:{ Exception -> 0x017c }
            long r7 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x017c }
            goto L_0x017d
        L_0x017b:
            r5 = r3
        L_0x017c:
            r7 = r3
        L_0x017d:
            r0 = 0
            long r5 = r5 - r7
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 > 0) goto L_0x0192
            int r10 = com.mi.global.shop.R.string.expired_order
            java.lang.String r10 = r9.getString(r10)
            r0 = 3000(0xbb8, float:4.204E-42)
            com.mi.util.MiToast.a((android.content.Context) r9, (java.lang.CharSequence) r10, (int) r0)
            r9.finish()
            return
        L_0x0192:
            com.mi.global.shop.widget.CustomTextView r0 = r9.A
            r0.setVisibility(r2)
            com.mi.global.shop.widget.CustomTextView r0 = r9.B
            r0.setVisibility(r2)
            com.mi.global.shop.util.CountDownUtil r0 = r9.V
            if (r0 != 0) goto L_0x01ba
            com.mi.global.shop.util.CountDownUtil r0 = new com.mi.global.shop.util.CountDownUtil
            r0.<init>(r9, r5)
            r9.V = r0
            com.mi.global.shop.util.CountDownUtil r0 = r9.V
            com.mi.global.shop.widget.CustomTextView r3 = r9.B
            int r4 = com.mi.global.shop.R.string.expired_order
            java.lang.String r4 = r9.getString(r4)
            r0.a((com.mi.global.shop.widget.CustomTextView) r3, (java.lang.String) r4)
            goto L_0x01ba
        L_0x01b5:
            com.mi.global.shop.widget.CustomButtonView r0 = r9.L
            r0.setVisibility(r1)
        L_0x01ba:
            boolean r0 = r9.j
            if (r0 != 0) goto L_0x01c9
            boolean r0 = r9.k
            if (r0 == 0) goto L_0x01c3
            goto L_0x01c9
        L_0x01c3:
            com.mi.global.shop.widget.CustomButtonView r0 = r9.M
            r0.setVisibility(r1)
            goto L_0x01d3
        L_0x01c9:
            com.mi.global.shop.widget.CustomButtonView r0 = r9.M
            r0.setVisibility(r2)
            com.mi.global.shop.widget.CustomButtonView r0 = r9.M
            r0.setOnClickListener(r9)
        L_0x01d3:
            boolean r0 = r9.h
            if (r0 == 0) goto L_0x0246
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r0 = r9.e
            if (r0 == 0) goto L_0x0246
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r0 = r9.e
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0246
            com.mi.global.shop.widget.CustomButtonView r0 = r9.N
            r0.setVisibility(r2)
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r0 = r9.e
            java.lang.Object r0 = r0.get(r2)
            com.mi.global.shop.newmodel.order.NewDeliversData r0 = (com.mi.global.shop.newmodel.order.NewDeliversData) r0
            com.mi.global.shop.newmodel.order.NewExpress r0 = r0.express
            if (r0 == 0) goto L_0x0209
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r0 = r9.e
            java.lang.Object r0 = r0.get(r2)
            com.mi.global.shop.newmodel.order.NewDeliversData r0 = (com.mi.global.shop.newmodel.order.NewDeliversData) r0
            java.lang.String r0 = r0.express_sn
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0209
            com.mi.global.shop.widget.CustomButtonView r0 = r9.N
            r0.setOnClickListener(r9)
        L_0x0209:
            com.mi.global.shop.widget.CustomTextView r0 = r9.I
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r3 = r9.e
            java.lang.Object r3 = r3.get(r2)
            com.mi.global.shop.newmodel.order.NewDeliversData r3 = (com.mi.global.shop.newmodel.order.NewDeliversData) r3
            java.lang.String r3 = r3.deliver_id
            r0.setText(r3)
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r0 = r9.e
            java.lang.Object r0 = r0.get(r2)
            com.mi.global.shop.newmodel.order.NewDeliversData r0 = (com.mi.global.shop.newmodel.order.NewDeliversData) r0
            com.mi.global.shop.newmodel.order.NewExpress r0 = r0.express
            if (r0 == 0) goto L_0x0246
            com.mi.global.shop.widget.CustomTextView r0 = r9.J
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r3 = r9.e
            java.lang.Object r3 = r3.get(r2)
            com.mi.global.shop.newmodel.order.NewDeliversData r3 = (com.mi.global.shop.newmodel.order.NewDeliversData) r3
            com.mi.global.shop.newmodel.order.NewExpress r3 = r3.express
            java.lang.String r3 = r3.express_sn
            r0.setText(r3)
            com.mi.global.shop.widget.CustomTextView r0 = r9.K
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r3 = r9.e
            java.lang.Object r3 = r3.get(r2)
            com.mi.global.shop.newmodel.order.NewDeliversData r3 = (com.mi.global.shop.newmodel.order.NewDeliversData) r3
            com.mi.global.shop.newmodel.order.NewExpress r3 = r3.express
            java.lang.String r3 = r3.express_name
            r0.setText(r3)
        L_0x0246:
            boolean r0 = r9.g
            if (r0 == 0) goto L_0x0274
            android.widget.LinearLayout r0 = r9.r
            r0.setVisibility(r2)
            com.mi.global.shop.user.OrderViewSuborderListViewAdapter r0 = new com.mi.global.shop.user.OrderViewSuborderListViewAdapter
            r0.<init>(r9)
            r9.P = r0
            com.mi.global.shop.user.OrderViewSuborderListViewAdapter r0 = r9.P
            r0.c()
            com.mi.global.shop.user.OrderViewSuborderListViewAdapter r0 = r9.P
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewDeliversData> r3 = r9.e
            r0.a(r3)
            com.mi.global.shop.widget.NoScrollListView r0 = r9.R
            com.mi.global.shop.user.OrderViewSuborderListViewAdapter r3 = r9.P
            r0.setAdapter(r3)
            android.support.v7.widget.RecyclerView r0 = r9.S
            r0.setVisibility(r1)
            android.widget.LinearLayout r0 = r9.s
            r0.setVisibility(r1)
            goto L_0x0283
        L_0x0274:
            android.widget.LinearLayout r0 = r9.r
            r0.setVisibility(r1)
            android.support.v7.widget.RecyclerView r0 = r9.S
            r0.setVisibility(r2)
            android.widget.LinearLayout r0 = r9.s
            r0.setVisibility(r2)
        L_0x0283:
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            if (r0 == 0) goto L_0x038c
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewListProduct> r0 = r0.product
            if (r0 == 0) goto L_0x038c
            com.mi.global.shop.user.OrderViewItemListViewAdapter r0 = new com.mi.global.shop.user.OrderViewItemListViewAdapter
            r0.<init>(r9)
            r9.O = r0
            com.mi.global.shop.user.OrderViewItemListViewAdapter r0 = r9.O
            r0.c()
            com.mi.global.shop.user.OrderViewItemListViewAdapter r0 = r9.O
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewListProduct> r3 = r3.product
            r0.a(r3)
            com.mi.global.shop.widget.NoScrollListView r0 = r9.Q
            com.mi.global.shop.user.OrderViewItemListViewAdapter r3 = r9.O
            r0.setAdapter(r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            java.lang.String r3 = r3.consignee
            r0.append(r3)
            java.lang.String r3 = " "
            r0.append(r3)
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            java.lang.String r3 = r3.tel
            r0.append(r3)
            r0.toString()
            com.mi.global.shop.widget.CustomTextView r0 = r9.C
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            com.mi.global.shop.newmodel.order.NewOrderInfo r4 = r10.orderInfo
            java.lang.String r4 = r4.consignee
            android.text.Spanned r4 = android.text.Html.fromHtml(r4)
            java.lang.String r4 = r4.toString()
            r3.append(r4)
            java.lang.String r4 = "    "
            r3.append(r4)
            com.mi.global.shop.newmodel.order.NewOrderInfo r4 = r10.orderInfo
            java.lang.String r4 = r4.tel
            android.text.Spanned r4 = android.text.Html.fromHtml(r4)
            java.lang.String r4 = r4.toString()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.setText(r3)
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewAddrIndia r0 = r0.addr_india
            if (r0 == 0) goto L_0x0361
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewAddrIndia r3 = r3.addr_india
            java.lang.String r3 = r3.addr
            r0.append(r3)
            java.lang.String r3 = " "
            r0.append(r3)
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewAddrIndia r3 = r3.addr_india
            java.lang.String r3 = r3.landmark
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.mi.global.shop.widget.CustomTextView r3 = r9.D
            android.text.Spanned r0 = android.text.Html.fromHtml(r0)
            java.lang.String r0 = r0.toString()
            r3.setText(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            java.lang.String r3 = r3.zipcode
            r0.append(r3)
            java.lang.String r3 = " "
            r0.append(r3)
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewAddrIndia r3 = r3.addr_india
            java.lang.String r3 = r3.city
            r0.append(r3)
            java.lang.String r3 = " "
            r0.append(r3)
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewAddrSimple r3 = r3.city
            java.lang.String r3 = r3.name
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.mi.global.shop.widget.CustomTextView r3 = r9.E
            android.text.Spanned r0 = android.text.Html.fromHtml(r0)
            java.lang.String r0 = r0.toString()
            r3.setText(r0)
        L_0x0361:
            com.mi.global.shop.widget.CustomTextView r0 = r9.F
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            java.lang.String r3 = r3.email
            r0.setText(r3)
            com.mi.global.shop.widget.CustomTextView r0 = r9.G
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            java.lang.String r3 = r3.order_id
            r0.setText(r3)
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            java.lang.String r0 = r0.add_time
            long r3 = java.lang.Long.parseLong(r0)
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r5
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            java.lang.String r0 = com.mi.global.shop.locale.LocaleHelper.a((java.lang.Long) r0)
            com.mi.global.shop.widget.CustomTextView r3 = r9.H
            r3.setText(r0)
        L_0x038c:
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewOrderStatusInfo r0 = r0.order_status_info
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewTraceItem> r0 = r0.trace
            if (r0 == 0) goto L_0x039f
            com.mi.global.shop.adapter.OrderProgressAdapter r0 = r9.T
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            com.mi.global.shop.newmodel.order.NewOrderStatusInfo r3 = r3.order_status_info
            java.util.ArrayList<com.mi.global.shop.newmodel.order.NewTraceItem> r3 = r3.trace
            r0.a(r3)
        L_0x039f:
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            java.lang.String r0 = r0.show_tag
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x03b8
            com.mi.global.shop.widget.CustomTextView r0 = r9.showTag
            com.mi.global.shop.newmodel.order.NewOrderInfo r3 = r10.orderInfo
            java.lang.String r3 = r3.show_tag
            r0.setText(r3)
            com.mi.global.shop.widget.CustomTextView r0 = r9.showTag
            r0.setVisibility(r2)
            goto L_0x03bd
        L_0x03b8:
            com.mi.global.shop.widget.CustomTextView r0 = r9.showTag
            r0.setVisibility(r1)
        L_0x03bd:
            com.mi.global.shop.newmodel.order.NewOrderInfo r0 = r10.orderInfo
            java.lang.String r0 = r0.show_tips
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x03db
            com.mi.global.shop.widget.CustomTextView r0 = r9.showTips
            com.mi.global.shop.newmodel.order.NewOrderInfo r10 = r10.orderInfo
            java.lang.String r10 = r10.show_tips
            r0.setText(r10)
            android.widget.LinearLayout r10 = r9.llNotice
            r10.setVisibility(r2)
            android.widget.LinearLayout r10 = r9.tipsShadow
            r10.setVisibility(r2)
            goto L_0x03e5
        L_0x03db:
            android.widget.LinearLayout r10 = r9.llNotice
            r10.setVisibility(r1)
            android.widget.LinearLayout r10 = r9.tipsShadow
            r10.setVisibility(r1)
        L_0x03e5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.activity.OrderViewActivity.b(com.mi.global.shop.newmodel.order.NewOrderViewData):void");
    }

    private String b() {
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.ac()).buildUpon();
        buildUpon.appendQueryParameter("order_id", this.c);
        if (Setting.a()) {
            buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
        }
        return buildUpon.toString();
    }

    private void c() {
        Request request;
        LogUtil.b(f5405a, "getOrderViewInfo url:" + b());
        String b2 = b();
        AnonymousClass1 r1 = new SimpleCallback<NewOrderViewResult>() {
            public void a(NewOrderViewResult newOrderViewResult) {
                OrderViewActivity.this.hideLoading();
                NewOrderViewData unused = OrderViewActivity.this.d = newOrderViewResult.data;
                String unused2 = OrderViewActivity.this.U = OrderViewActivity.this.d.currentTime;
                ArrayList unused3 = OrderViewActivity.this.e = OrderViewActivity.this.d.delivers;
                OrderViewActivity.this.a(OrderViewActivity.this.d);
                OrderViewActivity.this.b(OrderViewActivity.this.d);
            }

            public void a(String str) {
                super.a(str);
                LogUtil.b(OrderViewActivity.f5405a, "getOrderViewInfo Exception:" + str);
                OrderViewActivity.this.a(str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(b2, NewOrderViewResult.class, r1);
        } else {
            request = new SimpleJsonRequest(b2, NewOrderViewResult.class, r1);
        }
        request.setTag(f5405a);
        RequestQueueUtil.a().add(request);
        showLoading();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            MiToast.a((Context) this, R.string.shop_error_network, 0);
        } else {
            MiToast.a((Context) this, (CharSequence) str, 0);
        }
        hideLoading();
        setResult(0);
        finish();
        LogUtil.b(f5405a, "JSON parse error");
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            MiToast.a((Context) this, R.string.shop_error_network, 0);
        } else {
            MiToast.a((Context) this, (CharSequence) str, 0);
        }
        hideLoading();
        LogUtil.b(f5405a, "JSON parse error");
    }

    private void d() {
        CustomCancelDialog.Builder builder = new CustomCancelDialog.Builder(this);
        builder.a(getString(R.string.orderview_delpromote)).a((Boolean) true).a(getString(R.string.orderview_confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                OrderViewActivity.this.g();
            }
        }).b(getString(R.string.orderview_no), (DialogInterface.OnClickListener) null);
        builder.a().show();
    }

    private String[] e() {
        String[] o2 = ConnectionHelper.o();
        String[] strArr = new String[o2.length];
        for (int i2 = 0; i2 < o2.length; i2++) {
            LogUtil.b(f5405a, "getPaymentInfo");
            Uri.Builder buildUpon = Uri.parse(o2[i2]).buildUpon();
            buildUpon.appendQueryParameter("order_id", this.c);
            buildUpon.appendQueryParameter("security", "true");
            buildUpon.appendQueryParameter("jsontag", "true");
            LogUtil.b(f5405a, "payment url:" + buildUpon.toString());
            strArr[i2] = buildUpon.toString();
        }
        return strArr;
    }

    private String f() {
        String s2 = ConnectionHelper.s();
        LogUtil.b(f5405a, "getPaymentInfo");
        Uri.Builder buildUpon = Uri.parse(s2).buildUpon();
        buildUpon.appendQueryParameter("order_id", this.c);
        LogUtil.b(f5405a, "payment url:" + buildUpon.toString());
        return buildUpon.toString();
    }

    /* access modifiers changed from: private */
    public void g() {
        Request request;
        LogUtil.b(f5405a, "delUnpaidOrder url:" + b());
        String f2 = f();
        AnonymousClass3 r1 = new SimpleCallback<NewSimpleResult>() {
            public void a(NewSimpleResult newSimpleResult) {
                OrderViewActivity.this.hideLoading();
                boolean unused = OrderViewActivity.this.i = false;
                boolean unused2 = OrderViewActivity.this.j = false;
                boolean unused3 = OrderViewActivity.this.k = false;
                OrderViewActivity.this.a(ShopApp.g().getString(R.string.orderview_closed), (Intent) null);
                OrderViewActivity.this.b(OrderViewActivity.this.d);
            }

            public void a(String str) {
                super.a(str);
                LogUtil.b(OrderViewActivity.f5405a, "delUnpaidOrder Exception:" + str);
                OrderViewActivity.this.b(str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(f2, NewSimpleResult.class, r1);
        } else {
            request = new SimpleJsonRequest(f2, NewSimpleResult.class, r1);
        }
        request.setTag(f5405a);
        RequestQueueUtil.a().add(request);
        showLoading();
    }

    /* access modifiers changed from: private */
    public void a(String str, Intent intent) {
        this.d.orderInfo.order_status_info.info = str;
        if (intent == null) {
            intent = new Intent();
        }
        if (TextUtils.isEmpty(intent.getStringExtra("order_status"))) {
            intent.putExtra("order_status", str);
            intent.putExtra("order_haspay", this.i);
            intent.putExtra("order_hascancel", this.j);
            intent.putExtra("order_hastrace", this.h);
            intent.putExtra("order_hasrefund", this.k);
        }
        setResult(-1, intent);
    }

    public void onClick(View view) {
        if (view == this.L) {
            Intent intent = new Intent(this, ConfirmActivity.class);
            intent.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", this.c);
            startActivityForResult(intent, 17);
        } else if (view == this.M) {
            if (this.i) {
                d();
                return;
            }
            Intent intent2 = new Intent(this, CancelOrderAcitvity.class);
            intent2.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", this.c);
            intent2.putExtra("cancel_reason", "[{\"id\":\"101\",\"desc\":\"Not interested any more\"},{\"id\":\"102\",\"desc\":\"Mistakenly ordered a product twice\"},{\"id\":\"103\",\"desc\":\"Order delivery is delayed\"},{\"id\":\"104\",\"desc\":\"Ordered an incorrect product\"},{\"id\":\"105\",\"desc\":\"Other reasons\"}]");
            startActivityForResult(intent2, 18);
        } else if (view == this.N) {
            Intent intent3 = new Intent(this, TrackAcitvity.class);
            intent3.putExtra("expresssn", this.e.get(0).deliver_id);
            if (this.d.orderInfo.order_status_info.trace != null && this.d.orderInfo.order_status_info.trace.size() > 1) {
                intent3.putExtra("order_placed", this.d.orderInfo.order_status_info.trace.get(0).time);
                intent3.putExtra("order_paid", this.d.orderInfo.order_status_info.trace.get(1).time);
            }
            startActivity(intent3);
        } else if (view == this.b) {
            onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 18) {
            LogUtil.b(f5405a, "get ordercancel result:" + i3);
            if (i3 == -1) {
                this.i = false;
                this.j = false;
                this.k = false;
                if (intent != null) {
                    try {
                        if (!TextUtils.isEmpty(intent.getStringExtra("order_status"))) {
                            a(intent.getStringExtra("order_status"), intent);
                            b(this.d);
                        }
                    } catch (Exception unused) {
                    }
                }
                a(ShopApp.g().getString(R.string.orderview_paymentcancel), intent);
                b(this.d);
            } else {
                return;
            }
        }
        if (i2 == 17) {
            LogUtil.b(f5405a, "get confirm result:" + i3);
            if (i3 == -1) {
                Boolean bool = false;
                if (intent != null) {
                    bool = Boolean.valueOf(intent.getExtras().getBoolean("cod"));
                }
                if (bool.booleanValue()) {
                    a(ShopApp.g().getString(R.string.orderview_codconfirmed), intent);
                } else {
                    a(ShopApp.g().getString(R.string.orderview_paymentreceived), intent);
                }
                this.i = false;
                b(this.d);
            }
        }
    }
}
