package com.mi.global.shop.buy;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.adapter.PromotionListAdapter;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.buy.model.BuyOrderInfo;
import com.mi.global.shop.buy.model.BuyOrderItem;
import com.mi.global.shop.buy.model.CreditCardItem;
import com.mi.global.shop.buy.model.PromotionHintModel;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.buy.payu.PayUtil;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.model.basestruct.PageMessage;
import com.mi.global.shop.model.common.PayOption;
import com.mi.global.shop.newmodel.NewPageMessage;
import com.mi.global.shop.newmodel.pay.payinfo.NewPayOption;
import com.mi.global.shop.util.CountDownUtil;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.global.shop.widget.dialog.CustomTextDialog;
import com.mi.log.LogUtil;
import com.squareup.wire.Wire;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderdetailFragment extends MiFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6849a = "OrderdetailFragment";
    private LinearLayout b;
    private ImageView c;
    private CustomTextView d;
    private View e;
    private NoScrollListView f;
    private PaymentListViewAdapter g;
    private ArrayList<PaymentMethod> h = new ArrayList<>();
    private ArrayList<CreditCardItem> i;
    private View j;
    private View k;
    private View l;
    private LinearLayout m = null;
    private BuyOrderInfo n;
    private NoScrollListView o;
    private PromotionListAdapter p;
    private LinearLayout q;
    private CustomTextDialog r;
    private CustomTextView s;
    private LinearLayout t;
    private PageMessage u;

    public void onCreate(Bundle bundle) {
        LogUtil.b(f6849a, "onCreate");
        if (bundle != null) {
            LogUtil.b(f6849a, "onCreate, savedInstanceState != null");
        }
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (bundle != null) {
            LogUtil.b(f6849a, "onCreateView resume from savedInstanceState");
        }
        if (this.e == null) {
            LogUtil.b(f6849a, "onCreateView self == null");
            View inflate = layoutInflater.inflate(R.layout.shop_buy_confirm_orderdetail, viewGroup, false);
            ButterKnife.bind(inflate);
            this.m = (LinearLayout) inflate.findViewById(R.id.orderdetail_tip_payfail);
            this.b = (LinearLayout) inflate.findViewById(R.id.orderdetail_detailist);
            this.o = (NoScrollListView) inflate.findViewById(R.id.promoteListView);
            this.p = new PromotionListAdapter(getActivity());
            this.o.setAdapter(this.p);
            this.q = (LinearLayout) inflate.findViewById(R.id.promotion_bottom_divider);
            this.q.setVisibility(8);
            this.c = (ImageView) inflate.findViewById(R.id.orderdetail_toggleicon);
            this.d = (CustomTextView) inflate.findViewById(R.id.orderdetail_toggletext);
            this.j = inflate.findViewById(R.id.card_list_view_separator);
            this.k = inflate.findViewById(R.id.expandable_list_view_top_separator);
            this.l = inflate.findViewById(R.id.expandable_list_view_bottom_separator);
            this.s = (CustomTextView) inflate.findViewById(R.id.orderdetail_confirm_exchange_coupon);
            this.t = (LinearLayout) inflate.findViewById(R.id.layout_exchange_coupon_container);
            this.d.setOnClickListener(this);
            this.c.setOnClickListener(this);
            this.e = inflate;
            a(((ConfirmActivity) getActivity()).getconfirmOrder(), ((ConfirmActivity) getActivity()).getMention());
        } else {
            LogUtil.b(f6849a, "onCreateView self != null");
            ViewGroup viewGroup2 = (ViewGroup) this.e.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.e);
                LogUtil.b(f6849a, "onCreateView remove from parent");
            }
        }
        ButterKnife.bind((Object) this, this.e);
        return this.e;
    }

    public void a(Boolean bool) {
        if (this.m != null) {
            if (bool.booleanValue()) {
                this.m.setVisibility(0);
            } else {
                this.m.setVisibility(8);
            }
        }
    }

    public void a(NewPageMessage newPageMessage) {
        ConfirmActivity confirmActivity;
        if (newPageMessage != null && BaseActivity.isActivityAlive(getActivity()) && (confirmActivity = (ConfirmActivity) getActivity()) != null) {
            confirmActivity.showPageNotice(newPageMessage);
        }
    }

    public void a(BuyOrderInfo buyOrderInfo, String str) {
        long j2;
        CustomTextView customTextView;
        LinearLayout linearLayout;
        BuyOrderInfo buyOrderInfo2 = buyOrderInfo;
        if (this.e == null) {
            LogUtil.b(f6849a, "update mOrderdetailFragment selfView ==null, return");
        } else if (buyOrderInfo2 == null) {
            LogUtil.b(f6849a, "update mOrderdetailFragment confirmOrder ==null, return");
        } else {
            LogUtil.b(f6849a, "update mOrderdetailFragment, confirmOrder=" + buyOrderInfo.toString());
            this.n = buyOrderInfo2;
            LinearLayout linearLayout2 = (LinearLayout) this.e.findViewById(R.id.orderdetail_detailist);
            OrderItemListAdapter orderItemListAdapter = new OrderItemListAdapter(getActivity());
            orderItemListAdapter.a(buyOrderInfo2.l);
            ((NoScrollListView) this.e.findViewById(R.id.buy_confirm_itemlist)).setAdapter(orderItemListAdapter);
            ((CustomTextView) this.e.findViewById(R.id.orderdetail_total)).setText(LocaleHelper.e() + PayUtil.a(buyOrderInfo2.g));
            CustomTextView customTextView2 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_orderid);
            CustomTextView customTextView3 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_subtotal);
            CustomTextView customTextView4 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_subtotalamount);
            CustomTextView customTextView5 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_shipping);
            CustomTextView customTextView6 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_promote);
            CustomTextView customTextView7 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_total);
            CustomTextView customTextView8 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_consignee);
            CustomTextView customTextView9 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_address);
            CustomTextView customTextView10 = (CustomTextView) this.e.findViewById(R.id.orderdetail_confirm_phone);
            CustomTextView customTextView11 = (CustomTextView) this.e.findViewById(R.id.confirm_ttl_tv);
            int i2 = 0;
            int i3 = 0;
            double d2 = 0.0d;
            while (i3 < buyOrderInfo2.l.size()) {
                try {
                    i2 += Integer.parseInt(buyOrderInfo2.l.get(i3).b);
                    d2 += Double.parseDouble(buyOrderInfo2.l.get(i3).c);
                    linearLayout = linearLayout2;
                    customTextView = customTextView11;
                } catch (NumberFormatException e2) {
                    linearLayout = linearLayout2;
                    StringBuilder sb = new StringBuilder();
                    customTextView = customTextView11;
                    sb.append("NumberFormatException:");
                    sb.append(e2.toString());
                    LogUtil.b(f6849a, sb.toString());
                    i2 = i2;
                }
                i3++;
                linearLayout2 = linearLayout;
                customTextView11 = customTextView;
            }
            LinearLayout linearLayout3 = linearLayout2;
            CustomTextView customTextView12 = customTextView11;
            customTextView4.setText(" X" + i2);
            customTextView5.setText(LocaleHelper.e() + PayUtil.a(buyOrderInfo2.f));
            if (buyOrderInfo2.e.compareTo("0") == 0) {
                ((LinearLayout) this.e.findViewById(R.id.buy_confirm_promotelayout)).setVisibility(8);
            }
            customTextView6.setText("-" + LocaleHelper.e() + PayUtil.a(buyOrderInfo2.e));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(LocaleHelper.e());
            sb2.append(PayUtil.a(String.valueOf(d2)));
            customTextView3.setText(sb2.toString());
            customTextView2.setText(buyOrderInfo2.f6881a);
            customTextView7.setText(LocaleHelper.e() + PayUtil.a(buyOrderInfo2.g));
            customTextView8.setText(buyOrderInfo2.c);
            customTextView9.setText(buyOrderInfo2.d);
            customTextView10.setText(buyOrderInfo2.b);
            if (TextUtils.isEmpty(buyOrderInfo2.k)) {
                this.t.setVisibility(8);
            } else {
                this.t.setVisibility(0);
                CustomTextView customTextView13 = this.s;
                customTextView13.setText("-" + LocaleHelper.e() + PayUtil.a(buyOrderInfo2.k));
            }
            try {
                j2 = Long.parseLong(buyOrderInfo2.j);
            } catch (Exception unused) {
                j2 = 0;
            }
            CustomTextView customTextView14 = customTextView12;
            customTextView14.setText(buyOrderInfo2.j);
            if (((ConfirmActivity) getActivity()).getCountDownUtil() == null) {
                ((ConfirmActivity) getActivity()).setCountDownUtil(new CountDownUtil(getActivity(), j2));
                ((ConfirmActivity) getActivity()).getCountDownUtil().a(customTextView14, getString(R.string.expired_order));
            }
            linearLayout3.setVisibility(8);
            if (this.o == null || TextUtils.isEmpty(str)) {
                this.q.setVisibility(8);
            } else {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    if (jSONArray.length() > 0) {
                        ArrayList arrayList = new ArrayList();
                        for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i4);
                            PromotionHintModel promotionHintModel = new PromotionHintModel();
                            promotionHintModel.b = jSONObject.optString("icon");
                            promotionHintModel.c = jSONObject.optString("desc");
                            promotionHintModel.f6889a = jSONObject.optString("type");
                            promotionHintModel.d = jSONObject.optString("wap_url");
                            arrayList.add(promotionHintModel);
                        }
                        this.p.a(arrayList);
                        this.q.setVisibility(0);
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
            this.h = PayU.ae;
            this.f = (NoScrollListView) this.e.findViewById(R.id.BaseListView);
            this.g = new PaymentListViewAdapter(getActivity());
            this.g.c();
            this.g.a(this.h);
            this.f.setAdapter(this.g);
            if (this.h != null && this.h.size() > 0) {
                this.l.setVisibility(0);
            }
            this.f.setOnItemClickListener(this);
            this.n = buyOrderInfo2;
            LogUtil.b(f6849a, "update mOrderdetailFragment finish all");
        }
    }

    class OrderItemListAdapter extends ArrayAdapter<BuyOrderItem> {

        /* renamed from: a  reason: collision with root package name */
        CustomTextView f6850a;
        CustomTextView b;
        CustomTextView c;

        public void a(View view, int i, BuyOrderItem buyOrderItem) {
        }

        public OrderItemListAdapter(Context context) {
            super(context);
        }

        public View a(Context context, int i, BuyOrderItem buyOrderItem, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.shop_buy_confirm_payment_orderitem, (ViewGroup) null);
            this.f6850a = (CustomTextView) inflate.findViewById(R.id.orderdetail_confirm_itemname);
            this.b = (CustomTextView) inflate.findViewById(R.id.orderdetail_confirm_itemamount);
            this.c = (CustomTextView) inflate.findViewById(R.id.orderdetail_confirm_itemsubtotal);
            if (!TextUtils.isEmpty(buyOrderItem.f6882a)) {
                this.f6850a.setText(buyOrderItem.f6882a);
            }
            if (!TextUtils.isEmpty(buyOrderItem.b)) {
                CustomTextView customTextView = this.b;
                customTextView.setText(" X" + buyOrderItem.b);
            }
            if (!TextUtils.isEmpty(buyOrderItem.c)) {
                CustomTextView customTextView2 = this.c;
                customTextView2.setText(LocaleHelper.e() + PayUtil.a(buyOrderItem.c));
            }
            return inflate;
        }
    }

    public void onClick(View view) {
        Resources resources = ShopApp.g().getResources();
        if (this.b.getVisibility() == 8) {
            this.b.setVisibility(0);
            this.d.setText(R.string.buy_confirm_hidedetail);
            this.c.setImageDrawable(resources.getDrawable(R.drawable.shop_arrow_up));
            return;
        }
        this.b.setVisibility(8);
        this.d.setText(R.string.buy_confirm_showdetail);
        this.c.setImageDrawable(resources.getDrawable(R.drawable.shop_arrow_down));
    }

    public static class PaymentMethod {

        /* renamed from: a  reason: collision with root package name */
        public String f6851a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public boolean g;
        public boolean h = false;
        public String i;
        public ArrayList<PaymentMethod> j = new ArrayList<>();
        public int k;

        public static PaymentMethod a(NewPayOption newPayOption) {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.f6851a = (String) Wire.get(newPayOption.title, "");
            paymentMethod.b = (String) Wire.get(newPayOption.subtitle, "");
            paymentMethod.g = ((Boolean) Wire.get(Boolean.valueOf(newPayOption.enable), PayOption.DEFAULT_ENABLE)).booleanValue();
            paymentMethod.c = (String) Wire.get(newPayOption.infotitle, "");
            paymentMethod.d = (String) Wire.get(newPayOption.info, "");
            paymentMethod.e = (String) Wire.get(newPayOption.image, "");
            paymentMethod.f = (String) Wire.get(newPayOption.key, "");
            paymentMethod.i = (String) Wire.get(newPayOption.upioptions, "");
            paymentMethod.k = ((Integer) Wire.get(Integer.valueOf(newPayOption.upitype), 1)).intValue();
            return paymentMethod;
        }

        public static ArrayList<PaymentMethod> a(List<NewPayOption> list) {
            ArrayList<PaymentMethod> arrayList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                for (NewPayOption next : list) {
                    PaymentMethod a2 = a(next);
                    ArrayList<PaymentMethod> arrayList2 = new ArrayList<>();
                    if (next.subOptions != null) {
                        Iterator<NewPayOption> it = next.subOptions.iterator();
                        while (it.hasNext()) {
                            new PaymentMethod();
                            arrayList2.add(a(it.next()));
                        }
                    }
                    a2.j = arrayList2;
                    arrayList.add(a2);
                }
            }
            return arrayList;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        if (adapterView == this.f) {
            Object itemAtPosition = adapterView.getItemAtPosition(i2);
            if (itemAtPosition instanceof PaymentMethod) {
                PaymentMethod paymentMethod = (PaymentMethod) itemAtPosition;
                if (!paymentMethod.g) {
                    CustomTextDialog.Builder builder = new CustomTextDialog.Builder(getActivity());
                    if (!TextUtils.isEmpty(paymentMethod.d)) {
                        builder.a(paymentMethod.d);
                        this.r = builder.a();
                        this.r.show();
                    }
                } else {
                    LogUtil.b(f6849a, "clicked:" + paymentMethod.f6851a);
                    ConfirmActivity confirmActivity = (ConfirmActivity) getActivity();
                    if (confirmActivity != null) {
                        confirmActivity.onPaymentOptionSelected(paymentMethod.f);
                    }
                }
                MiShopStatInterface.a(String.format("pay_method(%s)", new Object[]{paymentMethod.f}), ConfirmActivity.class.getSimpleName());
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void a() {
        a(((ConfirmActivity) getActivity()).getconfirmOrder(), ((ConfirmActivity) getActivity()).getMention());
    }
}
