package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import com.xiaomi.smarthome.R;

public class DragSlideHorizontalView extends HorizontalScrollView {

    /* renamed from: a  reason: collision with root package name */
    private View f18817a;
    private View b;
    private View c;
    private VelocityTracker d;
    private boolean e = false;
    private boolean f;
    private boolean g;

    public DragSlideHorizontalView(Context context) {
        super(context);
    }

    public DragSlideHorizontalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DragSlideHorizontalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setPaddingView(View view) {
        this.c = view;
    }

    public void setAboveView(View view) {
        this.f18817a = view;
    }

    public void setBehindView(View view) {
        this.b = view;
        this.e = false;
    }

    public void disableBehindView(boolean z) {
        this.g = z;
    }

    public boolean isShow() {
        return this.e;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.g) {
            if (motionEvent.getAction() == 1) {
                performClick();
            }
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (this.d == null) {
                    this.d = VelocityTracker.obtain();
                } else {
                    this.d.clear();
                }
                if (this.e && ((float) getWidth()) - motionEvent.getX() <= ((float) this.b.getWidth())) {
                    this.f = true;
                    break;
                }
            case 1:
                if (this.f) {
                    this.f = false;
                    break;
                } else {
                    this.d.addMovement(motionEvent);
                    this.d.computeCurrentVelocity(1000);
                    if (this.d.getXVelocity() > 300.0f) {
                        scrollToAbove();
                    } else if (this.d.getXVelocity() < -300.0f) {
                        scrollToBehind();
                    } else if (this.c != null && computeHorizontalScrollOffset() > this.c.getWidth() / 2 && !this.e) {
                        scrollToBehind();
                    } else if (this.c != null && computeHorizontalScrollOffset() < this.c.getWidth() / 2 && this.e) {
                        scrollToAbove();
                    } else if (this.e) {
                        scrollToAbove();
                    } else {
                        scrollToBehind();
                    }
                    return true;
                }
                break;
            case 2:
                this.d.addMovement(motionEvent);
                break;
            case 3:
                if (this.e) {
                    scrollToAbove();
                }
                this.f = false;
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void scrollToAbove() {
        if (this.e) {
            smoothScrollTo(0, 0);
            this.e = false;
            this.b.setVisibility(4);
            this.b.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right));
        }
    }

    public void reset() {
        scrollTo(0, 0);
        this.e = false;
        this.b.setVisibility(4);
    }

    public void scrollToBehind() {
        if (!this.e && this.c != null) {
            smoothScrollTo(this.c.getWidth(), 0);
            this.e = true;
            this.b.setVisibility(0);
            this.b.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right));
        }
    }

    public void scroll() {
        if (this.c == null || computeHorizontalScrollOffset() <= this.c.getWidth() / 2) {
            scrollToBehind();
        } else {
            scrollToAbove();
        }
    }
}
