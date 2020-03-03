package miuipub.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.View;
import miuipub.stateposition.ViewStatePosition;
import miuipub.stateposition.ViewStatePositionProxy;

public class RadioButton extends android.widget.RadioButton implements ViewStatePosition {
    private Drawable mButtonDrawable;
    private ViewStatePositionProxy mProxy;

    public RadioButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public RadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842878);
    }

    public RadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.mProxy = new ViewStatePositionProxy(this, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mProxy.a();
    }

    public void setPosition(int i) {
        this.mProxy.setPosition(i);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (this.mProxy == null) {
            return super.onCreateDrawableState(i);
        }
        return this.mProxy.a(i);
    }

    public int[] onSuperCreateDrawableState(int i) {
        return super.onCreateDrawableState(i);
    }

    public void setButtonDrawable(Drawable drawable) {
        this.mButtonDrawable = drawable;
        super.setButtonDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!onDrawMiuipub(canvas)) {
            super.onDraw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Drawable drawable = this.mButtonDrawable;
        if (drawable != null) {
            int mode = View.MeasureSpec.getMode(i);
            int measuredWidth = getMeasuredWidth();
            if (mode != 1073741824) {
                measuredWidth += drawable.getIntrinsicWidth();
            }
            setMeasuredDimension(measuredWidth, getMeasuredHeight());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onDrawMiuipub(Canvas canvas) {
        if (this.mButtonDrawable == null) {
            return false;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int intrinsicHeight = this.mButtonDrawable.getIntrinsicHeight();
        if (this.mButtonDrawable.getCurrent() instanceof NinePatchDrawable) {
            intrinsicHeight = (getHeight() - paddingTop) - getPaddingBottom();
        } else {
            int gravity = getGravity() & 112;
            if (gravity == 16) {
                paddingTop = (getHeight() - intrinsicHeight) / 2;
            } else if (gravity == 80) {
                paddingTop = getHeight() - intrinsicHeight;
            }
        }
        this.mButtonDrawable.setBounds(paddingLeft, paddingTop, this.mButtonDrawable.getIntrinsicWidth() + paddingLeft, intrinsicHeight + paddingTop);
        this.mButtonDrawable.draw(canvas);
        int paddingLeft2 = getPaddingLeft();
        setPadding(this.mButtonDrawable.getIntrinsicWidth() + paddingLeft2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        super.onDraw(canvas);
        setPadding(paddingLeft2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        return true;
    }
}
