package com.xiaomi.smarthome.camera.view.timeline;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

public class ImageDrawable {
    Drawable mNormalDrawable;
    OnTouchListener mOnTouchListener;
    boolean mPressed;
    Drawable mPressedDrawable;
    Rect mRect = new Rect();

    public interface OnTouchListener {
        void onActionDown();

        void onActionUp();
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    public ImageDrawable(Drawable drawable, Drawable drawable2) {
        this.mNormalDrawable = drawable;
        this.mPressedDrawable = drawable2;
    }

    public void setRect(int i, int i2, int i3, int i4) {
        this.mRect.set(i, i2, i3, i4);
    }

    public void actionCancel() {
        if (this.mPressed) {
            this.mPressed = false;
            if (this.mOnTouchListener != null) {
                this.mOnTouchListener.onActionUp();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mRect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (motionEvent.getAction() == 0) {
                this.mPressed = true;
                if (this.mOnTouchListener != null) {
                    this.mOnTouchListener.onActionDown();
                }
                return true;
            } else if ((motionEvent.getAction() != 1 && motionEvent.getAction() != 3) || !this.mPressed) {
                return this.mPressed;
            } else {
                actionCancel();
                return true;
            }
        } else if (!this.mPressed) {
            return false;
        } else {
            actionCancel();
            return true;
        }
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.mNormalDrawable;
        if (this.mPressed && this.mPressedDrawable != null) {
            drawable = this.mPressedDrawable;
        }
        if (drawable != null) {
            drawable.setBounds(this.mRect);
            drawable.draw(canvas);
        }
    }
}
