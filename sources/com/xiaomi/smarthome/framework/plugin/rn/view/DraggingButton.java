package com.xiaomi.smarthome.framework.plugin.rn.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

public class DraggingButton extends AppCompatButton {
    private int beginX;
    private int beginY;
    private boolean isFirstInit;
    private boolean isFromDragLayout;
    private int lastLayoutBottom;
    private int lastLayoutLeft;
    private int lastLayoutRight;
    private int lastLayoutTop;
    private int lastX;
    private int lastY;
    private int screenHeight;
    private int screenWidth;

    public DraggingButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public DraggingButton(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DraggingButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastX = 0;
        this.lastY = 0;
        this.beginX = 0;
        this.beginY = 0;
        this.screenWidth = 720;
        this.screenHeight = 1280;
        this.isFirstInit = true;
        this.isFromDragLayout = false;
        initData(context);
    }

    private void initData(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.screenWidth = displayMetrics.widthPixels;
        this.screenHeight = displayMetrics.heightPixels;
        this.isFirstInit = true;
        this.isFromDragLayout = false;
    }

    public void layout(int i, int i2, int i3, int i4) {
        if (this.isFirstInit) {
            super.layout(i, i2, i3, i4);
        } else if (this.isFromDragLayout) {
            super.layout(i, i2, i3, i4);
        } else {
            super.layout(this.lastLayoutLeft, this.lastLayoutTop, this.lastLayoutRight, this.lastLayoutBottom);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        switch (motionEvent.getAction()) {
            case 0:
                this.lastX = (int) motionEvent.getRawX();
                this.lastY = (int) motionEvent.getRawY();
                this.beginX = this.lastX;
                this.beginY = this.lastY;
                this.isFromDragLayout = true;
                break;
            case 1:
                this.isFromDragLayout = false;
                if (Math.abs(this.lastX - this.beginX) < 10 && Math.abs(this.lastY - this.beginY) < 10) {
                    return super.onTouchEvent(motionEvent);
                }
                setPressed(false);
                return true;
            case 2:
                int rawX = ((int) motionEvent.getRawX()) - this.lastX;
                int rawY = ((int) motionEvent.getRawY()) - this.lastY;
                int left = getLeft() + rawX;
                int top = getTop() + rawY;
                int right = getRight() + rawX;
                int bottom = getBottom() + rawY;
                if (left < 0) {
                    right = getWidth() + 0;
                    left = 0;
                }
                if (right > this.screenWidth) {
                    right = this.screenWidth;
                    left = right - getWidth();
                }
                if (top < 0) {
                    bottom = getHeight() + 0;
                } else {
                    i = top;
                }
                if (bottom > this.screenHeight) {
                    bottom = this.screenHeight;
                    i = bottom - getHeight();
                }
                updateLayoutData(left, i, right, bottom);
                layout(left, i, right, bottom);
                this.lastX = (int) motionEvent.getRawX();
                this.lastY = (int) motionEvent.getRawY();
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void updateLayoutData(int i, int i2, int i3, int i4) {
        this.isFirstInit = false;
        this.lastLayoutLeft = i;
        this.lastLayoutTop = i2;
        this.lastLayoutRight = i3;
        this.lastLayoutBottom = i4;
    }
}
