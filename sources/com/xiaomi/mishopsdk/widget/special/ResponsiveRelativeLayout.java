package com.xiaomi.mishopsdk.widget.special;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.xiaomi.mishopsdk.R;

public class ResponsiveRelativeLayout extends RelativeLayout {
    private Drawable mForegroundDrawable;

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
        return getResources().getDrawable(R.drawable.mishopsdk_selector_home_staritem);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attributeSet, int i) {
        this.mForegroundDrawable = getForegroundDrawable();
        this.mForegroundDrawable.setCallback(this);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mForegroundDrawable.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.mForegroundDrawable.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mForegroundDrawable;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.mForegroundDrawable.setState(getDrawableState());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mForegroundDrawable.setBounds(0, 0, i, i2);
    }
}
