package com.xiaomi.jr.mipay.codepay.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xiaomi.jr.mipay.codepay.R;

public class ProgressButton extends TextView {
    private CharSequence mCharSequence;
    private Drawable mProgressDrawable;
    private boolean mShowProgress;

    public ProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProgressDrawable = context.getResources().getDrawable(R.drawable.miuisupport_progressbar_indeterminate_light);
        this.mProgressDrawable.setBounds(0, 0, this.mProgressDrawable.getIntrinsicWidth(), this.mProgressDrawable.getIntrinsicHeight());
        this.mProgressDrawable.setCallback(this);
    }

    public void startProgress() {
        if (!this.mShowProgress) {
            setEnabled(false);
            this.mCharSequence = getText();
            setText("");
            this.mShowProgress = true;
            if (Animatable.class.isInstance(this.mProgressDrawable)) {
                ((Animatable) this.mProgressDrawable).start();
            }
        }
    }

    public void stopProgress() {
        if (this.mShowProgress) {
            if (Animatable.class.isInstance(this.mProgressDrawable)) {
                ((Animatable) this.mProgressDrawable).stop();
            }
            this.mShowProgress = false;
            setText(this.mCharSequence);
            setEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mProgressDrawable.isStateful()) {
            this.mProgressDrawable.setState(getDrawableState());
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
        if (verifyDrawable(drawable) && drawable == this.mProgressDrawable) {
            Rect bounds = drawable.getBounds();
            int compoundPaddingLeft = getCompoundPaddingLeft();
            int compoundPaddingTop = getCompoundPaddingTop();
            int compoundPaddingRight = getCompoundPaddingRight();
            int compoundPaddingBottom = getCompoundPaddingBottom();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int right = scrollX + compoundPaddingLeft + (((((getRight() - getLeft()) - compoundPaddingRight) - compoundPaddingLeft) - this.mProgressDrawable.getIntrinsicWidth()) / 2);
            int bottom = scrollY + compoundPaddingTop + (((((getBottom() - getTop()) - compoundPaddingBottom) - compoundPaddingTop) - this.mProgressDrawable.getIntrinsicHeight()) / 2);
            invalidate(bounds.left + right, bounds.top + bottom, bounds.right + right, bounds.bottom + bottom);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        boolean verifyDrawable = super.verifyDrawable(drawable);
        if (!verifyDrawable) {
            return drawable == this.mProgressDrawable;
        }
        return verifyDrawable;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.mProgressDrawable.jumpToCurrentState();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mShowProgress) {
            int compoundPaddingLeft = getCompoundPaddingLeft();
            int compoundPaddingTop = getCompoundPaddingTop();
            int compoundPaddingRight = getCompoundPaddingRight();
            int compoundPaddingBottom = getCompoundPaddingBottom();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int right = getRight();
            int left = getLeft();
            canvas.save();
            canvas.translate((float) (scrollX + compoundPaddingLeft + (((((right - left) - compoundPaddingRight) - compoundPaddingLeft) - this.mProgressDrawable.getIntrinsicWidth()) / 2)), (float) (scrollY + compoundPaddingTop + (((((getBottom() - getTop()) - compoundPaddingBottom) - compoundPaddingTop) - this.mProgressDrawable.getIntrinsicHeight()) / 2)));
            this.mProgressDrawable.draw(canvas);
            canvas.restore();
        }
    }
}
