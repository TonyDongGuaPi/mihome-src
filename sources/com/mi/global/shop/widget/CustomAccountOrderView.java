package com.mi.global.shop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.util.fresco.FrescoUtils;

public class CustomAccountOrderView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private Context f7153a;
    private View b;
    private LayoutInflater c;
    private SimpleDraweeView d;
    private ImageView e;
    private CustomTextView f;
    private CustomTextView g;
    private RelativeLayout h;

    public CustomAccountOrderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public CustomAccountOrderView(Context context) {
        super(context);
        a(context);
    }

    public CustomAccountOrderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.f7153a = context;
        this.c = LayoutInflater.from(context);
        this.b = this.c.inflate(R.layout.shop_custom_account_order_layout, (ViewGroup) null);
        this.d = (SimpleDraweeView) this.b.findViewById(R.id.iv_icon);
        this.e = (ImageView) this.b.findViewById(R.id.iv_beta);
        this.f = (CustomTextView) this.b.findViewById(R.id.tv_count);
        this.g = (CustomTextView) this.b.findViewById(R.id.tv_title);
        this.h = (RelativeLayout) this.b.findViewById(R.id.rl_item);
        addView(this.b);
    }

    public CustomTextView getTvCount() {
        return this.f;
    }

    public void setTvCount(String str) {
        this.f.setText(str);
    }

    public SimpleDraweeView getIvIcon() {
        return this.d;
    }

    public void setIvIcon(int i) {
        FrescoUtils.a(i, this.d);
    }

    public CustomTextView getTvTitle() {
        return this.g;
    }

    public void setTvTitle(String str) {
        this.g.setText(str);
    }

    public void setCountVisible(int i) {
        this.f.setVisibility(i);
    }

    public void setIvBeta(int i) {
        this.e.setImageResource(i);
    }
}
