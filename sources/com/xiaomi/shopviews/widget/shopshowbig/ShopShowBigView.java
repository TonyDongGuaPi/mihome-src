package com.xiaomi.shopviews.widget.shopshowbig;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.shopviews.widget.R;

public class ShopShowBigView extends ConstraintLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f13344a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;

    public ShopShowBigView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public ShopShowBigView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShopShowBigView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.shopview_shop_show_big, this);
        b();
    }

    private void b() {
        this.f13344a = (ImageView) findViewById(R.id.iv);
        this.b = (TextView) findViewById(R.id.tv_discount);
        this.c = (TextView) findViewById(R.id.tv_title);
        this.d = (TextView) findViewById(R.id.tv_description);
        this.f = (TextView) findViewById(R.id.tv_currency);
        this.e = (TextView) findViewById(R.id.tv_price);
        this.g = (TextView) findViewById(R.id.tv_price_origin);
        this.h = (TextView) findViewById(R.id.tv_buy);
    }

    public void setImage(String str) {
        ImageLoader.a().a(str, this.f13344a);
    }

    public void setImage(int i) {
        ImageLoader.a().a(i, this.f13344a);
    }

    public void setOffText(String str) {
        this.b.setText(str);
    }

    public void setOffText(int i) {
        this.b.setText(i);
    }

    public void setTitle(String str) {
        this.c.setText(str);
    }

    public void setTitle(int i) {
        this.c.setText(i);
    }

    public void setDescription(String str) {
        this.d.setText(str);
    }

    public void setDescription(int i) {
        this.d.setText(i);
    }

    public void setCurrency(String str) {
        this.f.setText(str);
    }

    public void setCurrency(int i) {
        this.f.setText(i);
    }

    public void setPrice(String str) {
        this.e.setText(str);
    }

    public void setPrice(int i) {
        this.e.setText(i);
    }

    public void setPriceOrigin(String str) {
        this.g.setText(str);
    }

    public void setPriceOrigin(int i) {
        this.g.setText(i);
    }

    public void setBuy(String str) {
        this.h.setText(str);
    }

    public void setBuy(int i) {
        this.h.setText(i);
    }
}
