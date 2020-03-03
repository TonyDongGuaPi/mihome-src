package miuipub.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;

public class CheckedTextView extends android.widget.CheckedTextView {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int CHECK_MARK_PADDING = 0;
    private int mBasePadding;
    private Drawable mCheckMarkDrawable;

    public CheckedTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    public Drawable getCheckMarkDrawable() {
        return this.mCheckMarkDrawable;
    }

    public void setCheckMarkDrawable(Drawable drawable) {
        if (this.mCheckMarkDrawable != null) {
            this.mCheckMarkDrawable.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.mCheckMarkDrawable);
        }
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setVisible(getVisibility() == 0, false);
            drawable.setState(CHECKED_STATE_SET);
            setMinHeight(drawable.getIntrinsicHeight());
            drawable.setState(getDrawableState());
        }
        this.mCheckMarkDrawable = drawable;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mBasePadding = i;
        if (getCheckMarkDrawable() != null) {
            i = getCheckMarkDrawable().getIntrinsicWidth() + i + 0;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        this.mBasePadding = i;
        if (getCheckMarkDrawable() != null) {
            i = getCheckMarkDrawable().getIntrinsicWidth() + i + 0;
        }
        super.setPaddingRelative(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Drawable checkMarkDrawable = getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            int i = this.mBasePadding;
            int paddingTop = getPaddingTop();
            int intrinsicHeight = checkMarkDrawable.getIntrinsicHeight();
            if (checkMarkDrawable.getCurrent() instanceof NinePatchDrawable) {
                intrinsicHeight = (getHeight() - paddingTop) - getPaddingBottom();
            } else {
                int gravity = getGravity() & 112;
                if (gravity == 16) {
                    paddingTop = (getHeight() - intrinsicHeight) / 2;
                } else if (gravity == 80) {
                    paddingTop = getHeight() - intrinsicHeight;
                }
            }
            checkMarkDrawable.setBounds(i, paddingTop, checkMarkDrawable.getCurrent().getIntrinsicWidth() + i, intrinsicHeight + paddingTop);
            checkMarkDrawable.draw(canvas);
        }
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mCheckMarkDrawable != null) {
            this.mCheckMarkDrawable.setState(getDrawableState());
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mCheckMarkDrawable;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.mCheckMarkDrawable != null) {
            this.mCheckMarkDrawable.jumpToCurrentState();
        }
    }
}
