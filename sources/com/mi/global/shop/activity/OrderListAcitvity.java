package com.mi.global.shop.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.volley.Request;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.adapter.OrderListAdapter;
import com.mi.global.shop.adapter.util.AutoLoadArrayAdapter;
import com.mi.global.shop.buy.ConfirmActivity;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.newmodel.NewPageMessage;
import com.mi.global.shop.newmodel.NewSimpleResult;
import com.mi.global.shop.newmodel.orderlist.NewOrderListData;
import com.mi.global.shop.newmodel.orderlist.NewOrderListItem;
import com.mi.global.shop.newmodel.orderlist.NewOrderListResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.dialog.CustomCancelDialog;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderListAcitvity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5398a = "OrderListAcitvity";
    private int b;
    private List<NewOrderListItem> c = new ArrayList();
    /* access modifiers changed from: private */
    public OrderListAdapter d;
    private ListView e;
    private CustomTextView f;
    /* access modifiers changed from: private */
    public int g = 1;
    private View h;
    public ProgressDialog mProgressDialog;
    protected NewOrderListItem selectedOrderItem;
    protected int totalPages = Integer.MAX_VALUE;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_order_list);
        this.mCartView.setVisibility(8);
        this.mBackView.setVisibility(0);
        this.mBackView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OrderListAcitvity.this.onBackPressed();
            }
        });
        this.b = getIntent().getIntExtra("type", 1);
        if (this.b == 0) {
            setTitle((CharSequence) getString(R.string.account_all_orders));
            this.mForgetPwd.setText(R.string.closed_orders);
            this.mForgetPwd.setVisibility(0);
            this.mForgetPwd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(OrderListAcitvity.this, OrderListAcitvity.class);
                    intent.putExtra("type", 2);
                    OrderListAcitvity.this.startActivity(intent);
                }
            });
        } else if (this.b == 1) {
            setTitle((CharSequence) getString(R.string.account_my_openoder_default));
        } else if (this.b == 2) {
            setTitle((CharSequence) getString(R.string.closed_orders));
        } else if (this.b == 3) {
            setTitle((CharSequence) getString(R.string.returns));
        } else if (this.b == 4) {
            setTitle((CharSequence) getString(R.string.account_awaiting_payment_orders));
        } else if (this.b == 5) {
            setTitle((CharSequence) getString(R.string.account_shipping_orders));
        }
        this.f = (CustomTextView) findViewById(R.id.no_orders_txt);
        this.e = (ListView) findViewById(R.id.orderItemList);
        this.d = new OrderListAdapter(this);
        this.e.setAdapter(this.d);
        this.d.a((AutoLoadArrayAdapter.LoadMoreCallback) new AutoLoadArrayAdapter.LoadMoreCallback() {
            public void a() {
                OrderListAcitvity.this.a();
            }
        });
        this.d.a(AutoLoadArrayAdapter.LoadMoreStatus.loading);
        a();
    }

    public void startOrderViewActivity(NewOrderListItem newOrderListItem) {
        if (newOrderListItem != null) {
            Intent intent = new Intent(this, OrderViewActivity.class);
            intent.putExtra("orderview_orderid", newOrderListItem.order_id);
            startActivityForResult(intent, 19);
            this.selectedOrderItem = newOrderListItem;
        }
    }

    public void onBackPressed() {
        if (getIntent().getIntExtra("backToUserCenter", 0) == 1) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasNextPage() {
        return this.g <= this.totalPages;
    }

    /* access modifiers changed from: private */
    public void a(NewOrderListResult newOrderListResult) {
        if (newOrderListResult != null) {
            NewOrderListData newOrderListData = newOrderListResult.data;
            if (!(newOrderListData == null || newOrderListData.order_list == null)) {
                Iterator<NewOrderListItem> it = newOrderListResult.data.order_list.iterator();
                while (it.hasNext()) {
                    NewOrderListItem next = it.next();
                    if (!(next.order_status_info == null || next.order_status_info.next == null)) {
                        Iterator<String> it2 = next.order_status_info.next.iterator();
                        while (it2.hasNext()) {
                            String next2 = it2.next();
                            if (Tags.Order.ORDER_NEXT_PAY.equalsIgnoreCase(next2)) {
                                next.hasPay = true;
                            }
                            if (Tags.Order.ORDER_NEXT_CANCEL.equalsIgnoreCase(next2)) {
                                next.hasCancel = true;
                            }
                            if ("REFOUND_APPLY".equalsIgnoreCase(next2)) {
                                next.hasRefund = true;
                            }
                        }
                    }
                    if (next.delivers != null && next.delivers.size() > 1) {
                        next.hasTrace = true;
                        if (next.delivers.size() > 1) {
                            next.hasSuborder = true;
                        }
                    }
                    this.c.add(next);
                }
                this.g++;
                this.totalPages = newOrderListData.total_pages;
            }
            if (hasNextPage()) {
                this.d.a(AutoLoadArrayAdapter.LoadMoreStatus.idle);
            } else {
                this.d.a(AutoLoadArrayAdapter.LoadMoreStatus.disable);
            }
            updateUi();
        }
    }

    /* access modifiers changed from: private */
    public void a(NewPageMessage newPageMessage) {
        if (newPageMessage != null && !TextUtils.isEmpty(newPageMessage.pagemsg)) {
            this.e.setAdapter((ListAdapter) null);
            if (this.h == null) {
                this.h = LayoutInflater.from(this).inflate(R.layout.shop_notice_layout, this.e, false);
                this.e.addHeaderView(this.h);
            }
            this.h.setVisibility(0);
            ((CustomTextView) this.h.findViewById(R.id.notice_text)).setText(newPageMessage.pagemsg);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) this.h.findViewById(R.id.notice_icon);
            if (TextUtils.isEmpty(newPageMessage.icon)) {
                simpleDraweeView.setVisibility(8);
            } else {
                simpleDraweeView.setVisibility(0);
                FrescoUtils.a(newPageMessage.icon, simpleDraweeView);
            }
            this.e.setAdapter(this.d);
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        Request request;
        String c2 = c();
        AnonymousClass4 r1 = new SimpleCallback<NewOrderListResult>() {
            public void a(NewOrderListResult newOrderListResult) {
                if (OrderListAcitvity.this.g == 1 && newOrderListResult.data != null) {
                    OrderListAcitvity.this.a(newOrderListResult.data.pagemsg);
                }
                OrderListAcitvity.this.a(newOrderListResult);
            }

            public void a(String str) {
                super.a(str);
                MiToast.a((Context) OrderListAcitvity.this, (CharSequence) str, 0);
                OrderListAcitvity.this.d.a(AutoLoadArrayAdapter.LoadMoreStatus.error);
                if (OrderListAcitvity.this.g == 1) {
                    OrderListAcitvity.this.setResult(0, new Intent());
                    OrderListAcitvity.this.finish();
                }
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(c2, NewOrderListResult.class, r1);
        } else {
            request = new SimpleJsonRequest(c2, NewOrderListResult.class, r1);
        }
        request.setTag(f5398a);
        RequestQueueUtil.a().add(request);
    }

    /* access modifiers changed from: protected */
    public void updateUi() {
        if (this.c.size() == 0) {
            this.f.setVisibility(0);
            if (getString(R.string.account_all_orders).equals(getTitle())) {
                this.f.setText(getString(R.string.no_all_orders));
            } else if (getString(R.string.closed_orders).equals(getTitle())) {
                this.f.setText(getString(R.string.no_closed_orders));
            } else {
                this.f.setText(getString(R.string.no_orders, new Object[]{getTitle().toString().toLowerCase()}));
            }
        } else {
            this.f.setVisibility(8);
            this.d.a(this.c);
        }
    }

    private String[] b() {
        String[] a2 = ConnectionHelper.a(this.b);
        String[] strArr = new String[a2.length];
        for (int i = 0; i < a2.length; i++) {
            Uri.Builder buildUpon = Uri.parse(a2[i]).buildUpon();
            buildUpon.appendQueryParameter("r", System.currentTimeMillis() + "");
            buildUpon.appendQueryParameter("jsontag", "true");
            buildUpon.appendQueryParameter("security", "true");
            buildUpon.appendQueryParameter("page", this.g + "");
            if (Setting.a()) {
                buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
            }
            strArr[i] = buildUpon.toString();
        }
        return strArr;
    }

    private String c() {
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.b(this.b)).buildUpon();
        buildUpon.appendQueryParameter("r", System.currentTimeMillis() + "");
        buildUpon.appendQueryParameter("page", this.g + "");
        if (Setting.a()) {
            buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
        }
        return buildUpon.toString();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private String a(String str) {
        String s = ConnectionHelper.s();
        LogUtil.b(f5398a, "getPaymentInfo");
        Uri.Builder buildUpon = Uri.parse(s).buildUpon();
        buildUpon.appendQueryParameter("order_id", str);
        String str2 = f5398a;
        LogUtil.b(str2, "payment url:" + buildUpon.toString());
        return buildUpon.toString();
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        Request request;
        String a2 = a(str);
        AnonymousClass5 r0 = new SimpleCallback<NewSimpleResult>() {
            public void a(NewSimpleResult newSimpleResult) {
                OrderListAcitvity.this.hideLoading();
                if (OrderListAcitvity.this.selectedOrderItem != null) {
                    OrderListAcitvity.this.selectedOrderItem.order_status_info.info = OrderListAcitvity.this.getString(R.string.orderview_closed);
                    OrderListAcitvity.this.selectedOrderItem.hasPay = false;
                    OrderListAcitvity.this.selectedOrderItem.hasCancel = false;
                    OrderListAcitvity.this.selectedOrderItem.hasRefund = false;
                    OrderListAcitvity.this.d.notifyDataSetChanged();
                }
            }

            public void a(String str) {
                super.a(str);
                OrderListAcitvity.this.hideLoading();
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(a2, NewSimpleResult.class, r0);
        } else {
            request = new SimpleJsonRequest(a2, NewSimpleResult.class, r0);
        }
        request.setTag(f5398a);
        RequestQueueUtil.a().add(request);
        showLoading();
    }

    public void cancelOrder(final NewOrderListItem newOrderListItem) {
        if (newOrderListItem != null) {
            this.selectedOrderItem = newOrderListItem;
            if (newOrderListItem.hasPay) {
                CustomCancelDialog.Builder builder = new CustomCancelDialog.Builder(this);
                builder.a(getString(R.string.orderview_delpromote)).a((Boolean) true).a(getString(R.string.orderview_confirm), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OrderListAcitvity.this.b(newOrderListItem.order_id);
                    }
                }).b(getString(R.string.orderview_no), (DialogInterface.OnClickListener) null);
                builder.a().show();
                return;
            }
            Intent intent = new Intent(this, CancelOrderAcitvity.class);
            intent.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", newOrderListItem.order_id);
            intent.putExtra("cancel_reason", "[{\"id\":\"101\",\"desc\":\"Not interested any more\"},{\"id\":\"102\",\"desc\":\"Mistakenly ordered a product twice\"},{\"id\":\"103\",\"desc\":\"Order delivery is delayed\"},{\"id\":\"104\",\"desc\":\"Ordered an incorrect product\"},{\"id\":\"105\",\"desc\":\"Other reasons\"}]");
            startActivityForResult(intent, 18);
        }
    }

    public void payOrder(NewOrderListItem newOrderListItem) {
        if (newOrderListItem != null) {
            Intent intent = new Intent(this, ConfirmActivity.class);
            intent.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", newOrderListItem.order_id);
            startActivityForResult(intent, 17);
            this.selectedOrderItem = newOrderListItem;
        }
    }

    public void traceOrder(NewOrderListItem newOrderListItem) {
        if (newOrderListItem != null && newOrderListItem.delivers != null && newOrderListItem.delivers.size() != 0) {
            if (newOrderListItem.delivers.size() > 1) {
                startOrderViewActivity(newOrderListItem);
                return;
            }
            Intent intent = new Intent(this, TrackAcitvity.class);
            intent.putExtra("expresssn", newOrderListItem.delivers.get(0).deliver_id);
            if (newOrderListItem.order_status_info.trace != null && newOrderListItem.order_status_info.trace.size() > 1) {
                intent.putExtra("order_placed", newOrderListItem.order_status_info.trace.get(0).time);
                intent.putExtra("order_paid", newOrderListItem.order_status_info.trace.get(1).time);
            }
            startActivity(intent);
            this.selectedOrderItem = newOrderListItem;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = false;
        switch (i) {
            case 17:
                if (i2 == -1 && this.selectedOrderItem != null) {
                    this.selectedOrderItem.hasPay = false;
                    if (intent != null) {
                        z = intent.getExtras().getBoolean("cod");
                    }
                    if (z) {
                        this.selectedOrderItem.order_status_info.info = getString(R.string.orderview_codconfirmed);
                    } else {
                        this.selectedOrderItem.order_status_info.info = getString(R.string.orderview_paymentreceived);
                    }
                    this.d.notifyDataSetChanged();
                    return;
                }
                return;
            case 18:
                if (i2 == -1 && this.selectedOrderItem != null) {
                    this.selectedOrderItem.hasPay = false;
                    this.selectedOrderItem.hasCancel = false;
                    this.selectedOrderItem.hasRefund = false;
                    String str = null;
                    if (intent != null) {
                        str = intent.getStringExtra("order_status");
                    }
                    if (TextUtils.isEmpty(str)) {
                        str = getString(R.string.orderview_paymentcancel);
                    }
                    this.selectedOrderItem.order_status_info.info = str;
                    this.d.notifyDataSetChanged();
                    return;
                }
                return;
            case 19:
                if (i2 == -1 && this.selectedOrderItem != null && intent != null && !TextUtils.isEmpty(intent.getStringExtra("order_status"))) {
                    this.selectedOrderItem.order_status_info.info = intent.getStringExtra("order_status");
                    this.selectedOrderItem.hasPay = intent.getBooleanExtra("order_haspay", false);
                    this.selectedOrderItem.hasCancel = intent.getBooleanExtra("order_hascancel", false);
                    this.selectedOrderItem.hasRefund = intent.getBooleanExtra("order_hasrefund", false);
                    this.selectedOrderItem.hasTrace = intent.getBooleanExtra("order_hastrace", false);
                    this.d.notifyDataSetChanged();
                    return;
                }
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }
}
