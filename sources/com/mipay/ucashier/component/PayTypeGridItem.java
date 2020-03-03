package com.mipay.ucashier.component;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mipay.common.data.g;
import com.mipay.ucashier.R;
import com.mipay.ucashier.data.PayType;
import com.squareup.picasso.Picasso;

public class PayTypeGridItem extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f8178a;
    private TextView b;
    private TextView c;
    private PayType d;

    public PayTypeGridItem(Context context) {
        this(context, (AttributeSet) null);
    }

    public PayTypeGridItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PayType getPayType() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f8178a = (ImageView) findViewById(R.id.pay_image);
        this.b = (TextView) findViewById(R.id.pay_title);
        this.c = (TextView) findViewById(R.id.pay_tip);
    }

    public void setItemData(PayType payType) {
        this.d = payType;
        this.b.setText(payType.mPayTitle);
        if (!TextUtils.isEmpty(payType.mPayTip)) {
            this.c.setText(payType.mPayTip);
        }
        if (!TextUtils.isEmpty(payType.mPayIconUrl)) {
            g a2 = g.a(payType.mPayIconUrl);
            a2.a(g.b.a(getResources().getDimensionPixelSize(R.dimen.ucashier_pay_image_width), getResources().getDimensionPixelSize(R.dimen.ucashier_pay_image_height), 2));
            String b2 = a2.b();
            if (!TextUtils.isEmpty(b2)) {
                Picasso.with(getContext()).load(b2).placeholder(R.drawable.ucashier_pay_type_default).into(this.f8178a);
            }
        }
    }
}
