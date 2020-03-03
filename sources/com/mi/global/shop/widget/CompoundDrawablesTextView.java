package com.mi.global.shop.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CompoundDrawablesTextView extends CustomTextView implements View.OnClickListener {
    private Drawable mBottomDrawable;
    private DrawableClickListener mDrawableClickListener;
    private boolean mIsAllDrawableTouchedResponse = true;
    private boolean mIsAlwaysClick = true;
    private boolean mIsBottomTouched;
    private boolean mIsLeftTouched;
    private boolean mIsRightTouched;
    private boolean mIsTopTouched;
    private int mLazyX = 0;
    private int mLazyY = 0;
    private Drawable mLeftDrawable;
    private View.OnClickListener mOnClickListener;
    private Drawable mRightDrawable;
    private Drawable mTopDrawable;

    public interface DrawableClickListener {

        public enum DrawablePosition {
            LEFT,
            TOP,
            RIGHT,
            BOTTOM
        }

        void onClick(DrawablePosition drawablePosition);
    }

    public CompoundDrawablesTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public CompoundDrawablesTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CompoundDrawablesTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        super.setOnClickListener(this);
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        this.mLeftDrawable = drawable;
        this.mTopDrawable = drawable2;
        this.mRightDrawable = drawable3;
        this.mBottomDrawable = drawable4;
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            resetTouchStatus();
            if (this.mDrawableClickListener != null) {
                this.mIsLeftTouched = touchLeftDrawable(motionEvent);
                this.mIsTopTouched = touchTopDrawable(motionEvent);
                this.mIsRightTouched = touchRightDrawable(motionEvent);
                this.mIsBottomTouched = touchBottomDrawable(motionEvent);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onClick(View view) {
        if (this.mDrawableClickListener != null) {
            if (this.mIsLeftTouched) {
                this.mDrawableClickListener.onClick(DrawableClickListener.DrawablePosition.LEFT);
            }
            boolean z = false;
            boolean z2 = this.mIsAllDrawableTouchedResponse || !this.mIsLeftTouched;
            if (z2 && this.mIsTopTouched) {
                this.mDrawableClickListener.onClick(DrawableClickListener.DrawablePosition.TOP);
            }
            boolean z3 = this.mIsAllDrawableTouchedResponse || (z2 && !this.mIsTopTouched);
            if (z3 && this.mIsRightTouched) {
                this.mDrawableClickListener.onClick(DrawableClickListener.DrawablePosition.RIGHT);
            }
            if (this.mIsAllDrawableTouchedResponse || (z3 && !this.mIsRightTouched)) {
                z = true;
            }
            if (z && this.mIsBottomTouched) {
                this.mDrawableClickListener.onClick(DrawableClickListener.DrawablePosition.BOTTOM);
            }
        }
        if (this.mOnClickListener == null) {
            return;
        }
        if (this.mIsAlwaysClick || (!this.mIsLeftTouched && !this.mIsTopTouched && !this.mIsRightTouched && !this.mIsBottomTouched)) {
            this.mOnClickListener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.mRightDrawable = null;
        this.mBottomDrawable = null;
        this.mLeftDrawable = null;
        this.mTopDrawable = null;
        super.finalize();
    }

    private void resetTouchStatus() {
        this.mIsLeftTouched = false;
        this.mIsTopTouched = false;
        this.mIsRightTouched = false;
        this.mIsBottomTouched = false;
    }

    private boolean touchLeftDrawable(MotionEvent motionEvent) {
        int i = 0;
        if (this.mLeftDrawable == null) {
            return false;
        }
        int intrinsicHeight = this.mLeftDrawable.getIntrinsicHeight();
        int intrinsicWidth = this.mLeftDrawable.getIntrinsicWidth();
        int intrinsicHeight2 = this.mTopDrawable == null ? 0 : this.mTopDrawable.getIntrinsicHeight();
        if (this.mBottomDrawable != null) {
            i = this.mBottomDrawable.getIntrinsicHeight();
        }
        double height = (double) (getHeight() + (intrinsicHeight2 - i));
        Double.isNaN(height);
        double d = height * 0.5d;
        int compoundDrawablePadding = getCompoundDrawablePadding() - this.mLazyX;
        double d2 = (double) intrinsicHeight;
        Double.isNaN(d2);
        double d3 = d2 * 0.5d;
        double d4 = (double) this.mLazyY;
        Double.isNaN(d4);
        int i2 = (int) ((d - d3) - d4);
        int compoundDrawablePadding2 = getCompoundDrawablePadding() + intrinsicWidth + this.mLazyX;
        double d5 = d + d3;
        double d6 = (double) this.mLazyY;
        Double.isNaN(d6);
        return new Rect(compoundDrawablePadding, i2, compoundDrawablePadding2, (int) (d5 + d6)).contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    private boolean touchTopDrawable(MotionEvent motionEvent) {
        int i = 0;
        if (this.mTopDrawable == null) {
            return false;
        }
        int intrinsicHeight = this.mTopDrawable.getIntrinsicHeight();
        int intrinsicWidth = this.mTopDrawable.getIntrinsicWidth();
        int intrinsicWidth2 = this.mLeftDrawable == null ? 0 : this.mLeftDrawable.getIntrinsicWidth();
        if (this.mRightDrawable != null) {
            i = this.mRightDrawable.getIntrinsicWidth();
        }
        double width = (double) (getWidth() + (intrinsicWidth2 - i));
        Double.isNaN(width);
        double d = width * 0.5d;
        double d2 = (double) intrinsicWidth;
        Double.isNaN(d2);
        double d3 = d2 * 0.5d;
        double d4 = (double) this.mLazyX;
        Double.isNaN(d4);
        int i2 = (int) ((d - d3) - d4);
        int compoundDrawablePadding = getCompoundDrawablePadding() - this.mLazyY;
        double d5 = (double) this.mLazyX;
        Double.isNaN(d5);
        return new Rect(i2, compoundDrawablePadding, (int) (d + d3 + d5), getCompoundDrawablePadding() + intrinsicHeight + this.mLazyY).contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    private boolean touchRightDrawable(MotionEvent motionEvent) {
        int i = 0;
        if (this.mRightDrawable == null) {
            return false;
        }
        int intrinsicHeight = this.mRightDrawable.getIntrinsicHeight();
        int intrinsicWidth = this.mRightDrawable.getIntrinsicWidth();
        int intrinsicHeight2 = this.mTopDrawable == null ? 0 : this.mTopDrawable.getIntrinsicHeight();
        if (this.mBottomDrawable != null) {
            i = this.mBottomDrawable.getIntrinsicHeight();
        }
        double height = (double) (getHeight() + (intrinsicHeight2 - i));
        Double.isNaN(height);
        double d = height * 0.5d;
        int width = ((getWidth() - getCompoundDrawablePadding()) - intrinsicWidth) - this.mLazyX;
        double d2 = (double) intrinsicHeight;
        Double.isNaN(d2);
        double d3 = d2 * 0.5d;
        double d4 = (double) this.mLazyY;
        Double.isNaN(d4);
        int i2 = (int) ((d - d3) - d4);
        int width2 = (getWidth() - getCompoundDrawablePadding()) + this.mLazyX;
        double d5 = (double) this.mLazyY;
        Double.isNaN(d5);
        return new Rect(width, i2, width2, (int) (d + d3 + d5)).contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    private boolean touchBottomDrawable(MotionEvent motionEvent) {
        int i = 0;
        if (this.mBottomDrawable == null) {
            return false;
        }
        int intrinsicHeight = this.mBottomDrawable.getIntrinsicHeight();
        int intrinsicWidth = this.mBottomDrawable.getIntrinsicWidth();
        int intrinsicWidth2 = this.mLeftDrawable == null ? 0 : this.mLeftDrawable.getIntrinsicWidth();
        if (this.mRightDrawable != null) {
            i = this.mRightDrawable.getIntrinsicWidth();
        }
        double width = (double) (getWidth() + (intrinsicWidth2 - i));
        Double.isNaN(width);
        double d = width * 0.5d;
        double d2 = (double) intrinsicWidth;
        Double.isNaN(d2);
        double d3 = d2 * 0.5d;
        double d4 = (double) this.mLazyX;
        Double.isNaN(d4);
        int i2 = (int) ((d - d3) - d4);
        int height = ((getHeight() - getCompoundDrawablePadding()) - intrinsicHeight) - this.mLazyY;
        double d5 = (double) this.mLazyX;
        Double.isNaN(d5);
        return new Rect(i2, height, (int) (d + d3 + d5), (getHeight() - getCompoundDrawablePadding()) + this.mLazyY).contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    public boolean isAllDrawableTouchedResponse() {
        return this.mIsAllDrawableTouchedResponse;
    }

    public void setAllDrawableTouchedResponse(boolean z) {
        this.mIsAllDrawableTouchedResponse = z;
    }

    public boolean isAlwaysClick() {
        return this.mIsAlwaysClick;
    }

    public void setAlwaysClick(boolean z) {
        this.mIsAlwaysClick = z;
    }

    public int getLazyX() {
        return this.mLazyX;
    }

    public void setLazyX(int i) {
        this.mLazyX = i;
    }

    public int getLazyY() {
        return this.mLazyY;
    }

    public void setLazyY(int i) {
        this.mLazyY = i;
    }

    public void setLazy(int i, int i2) {
        this.mLazyX = i;
        this.mLazyY = i2;
    }

    public void setDrawableClickListener(DrawableClickListener drawableClickListener) {
        this.mDrawableClickListener = drawableClickListener;
    }
}
