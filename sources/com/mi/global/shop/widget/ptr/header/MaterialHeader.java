package com.mi.global.shop.widget.ptr.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.mi.global.shop.widget.ptr.PtrFrameLayout;
import com.mi.global.shop.widget.ptr.PtrUIHandler;
import com.mi.global.shop.widget.ptr.PtrUIHandlerHook;

public class MaterialHeader extends View implements PtrUIHandler {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public MaterialProgressDrawable f7254a;
    /* access modifiers changed from: private */
    public float b = 1.0f;
    private PtrFrameLayout c;
    /* access modifiers changed from: private */
    public Animation d = new Animation() {
        public void applyTransformation(float f, Transformation transformation) {
            float unused = MaterialHeader.this.b = 1.0f - f;
            MaterialHeader.this.f7254a.setAlpha((int) (MaterialHeader.this.b * 255.0f));
            MaterialHeader.this.invalidate();
        }
    };

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
    }

    public MaterialHeader(Context context) {
        super(context);
        a();
    }

    public MaterialHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public MaterialHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void setPtrFrameLayout(PtrFrameLayout ptrFrameLayout) {
        final AnonymousClass2 r0 = new PtrUIHandlerHook() {
            public void run() {
                MaterialHeader.this.startAnimation(MaterialHeader.this.d);
            }
        };
        this.d.setDuration(200);
        this.d.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                r0.c();
            }
        });
        this.c = ptrFrameLayout;
        this.c.setRefreshCompleteHook(r0);
    }

    private void a() {
        this.f7254a = new MaterialProgressDrawable(getContext(), this);
        this.f7254a.b(-1);
        this.f7254a.setCallback(this);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.f7254a) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public void setColorSchemeColors(int[] iArr) {
        this.f7254a.a(iArr);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.f7254a.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom(), 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int intrinsicHeight = this.f7254a.getIntrinsicHeight();
        this.f7254a.setBounds(0, 0, intrinsicHeight, intrinsicHeight);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = this.f7254a.getBounds();
        canvas.translate((float) (getPaddingLeft() + ((getMeasuredWidth() - this.f7254a.getIntrinsicWidth()) / 2)), (float) getPaddingTop());
        canvas.scale(this.b, this.b, bounds.exactCenterX(), bounds.exactCenterY());
        this.f7254a.draw(canvas);
        canvas.restoreToCount(save);
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        this.b = 1.0f;
        this.f7254a.stop();
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.f7254a.setAlpha(255);
        this.f7254a.start();
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        this.f7254a.stop();
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b2, int i, int i2, float f, float f2) {
        float min = Math.min(1.0f, f2);
        if (b2 == 2) {
            this.f7254a.setAlpha((int) (255.0f * min));
            this.f7254a.a(true);
            this.f7254a.a(0.0f, Math.min(0.8f, min * 0.8f));
            this.f7254a.a(Math.min(1.0f, min));
            this.f7254a.b((((0.4f * min) - 16.0f) + (min * 2.0f)) * 0.5f);
            invalidate();
        }
    }
}
