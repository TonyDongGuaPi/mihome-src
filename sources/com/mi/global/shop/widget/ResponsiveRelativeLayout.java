package com.mi.global.shop.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.mi.global.shop.R;

public class ResponsiveRelativeLayout extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private Drawable f7168a;

    public ResponsiveRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    public ResponsiveRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public ResponsiveRelativeLayout(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    /* access modifiers changed from: protected */
    public Drawable getForegroundDrawable() {
        return getResources().getDrawable(R.drawable.shop_selector_home_staritem);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attributeSet, int i) {
        this.f7168a = getForegroundDrawable();
        this.f7168a.setCallback(this);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f7168a.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.f7168a.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f7168a;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.f7168a.setState(getDrawableState());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f7168a.setBounds(0, 0, i, i2);
    }
}
