package com.mi.global.shop.buy;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.buy.OrderdetailFragment;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.SlidingButton;

public class PaymentListViewAdapter extends ArrayAdapter<OrderdetailFragment.PaymentMethod> implements SlidingButton.OnCheckedChangedListener {
    private static final String b = "PaymentListViewAdapter";

    /* renamed from: a  reason: collision with root package name */
    Context f6852a;

    public void a(SlidingButton slidingButton, boolean z) {
    }

    public PaymentListViewAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, OrderdetailFragment.PaymentMethod paymentMethod, ViewGroup viewGroup) {
        if (paymentMethod == null) {
            return null;
        }
        return LayoutInflater.from(this.d).inflate(R.layout.shop_buy_confirm_payment_method_item, (ViewGroup) null, false);
    }

    public void a(View view, int i, OrderdetailFragment.PaymentMethod paymentMethod) {
        if (paymentMethod != null) {
            CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.paymentmethod_name);
            ImageView imageView = (ImageView) view.findViewById(R.id.paymentmethod_image);
            CustomTextView customTextView2 = (CustomTextView) view.findViewById(R.id.tv_paymentmethod_long_hint);
            CustomTextView customTextView3 = (CustomTextView) view.findViewById(R.id.paymentmethod_hint);
            CustomTextView customTextView4 = (CustomTextView) view.findViewById(R.id.paymentmethod_discount);
            if (TextUtils.isEmpty(paymentMethod.b)) {
                customTextView4.setVisibility(8);
            } else {
                customTextView4.setText(paymentMethod.b);
                customTextView4.setVisibility(0);
            }
            customTextView.setText(paymentMethod.f6851a);
            if (!ShopApp.g().getString(R.string.buy_confirm_PaymentKey_Cardless_EMI).equals(paymentMethod.f) || TextUtils.isEmpty(paymentMethod.c)) {
                customTextView3.setVisibility(0);
                customTextView2.setVisibility(8);
                customTextView3.setText(paymentMethod.c);
            } else {
                customTextView2.setVisibility(0);
                customTextView3.setVisibility(4);
                customTextView2.setText(paymentMethod.c);
            }
            if (paymentMethod.g) {
                customTextView.setTextColor(Color.rgb(46, 39, 39));
            } else {
                customTextView.setTextColor(Color.rgb(176, 176, 176));
            }
        }
    }
}
