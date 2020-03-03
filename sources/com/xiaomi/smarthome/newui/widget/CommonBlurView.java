package com.xiaomi.smarthome.newui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.module.blur.StackBlurManager;

public class CommonBlurView extends ImageView {
    private boolean isAnimate;
    /* access modifiers changed from: private */
    public Bitmap mAfterBitmap;
    /* access modifiers changed from: private */
    public Bitmap mBeforeBitmap;
    /* access modifiers changed from: private */
    public Bitmap mScaledBitmap;
    private View mView;
    /* access modifiers changed from: private */
    public StackBlurManager manager = new StackBlurManager();

    public CommonBlurView(Context context) {
        super(context);
    }

    public CommonBlurView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setBlurView(View view, boolean z) {
        this.mView = view;
        this.isAnimate = z;
        processBlurView(this.mView, this.isAnimate, true);
    }

    public void setBlurViewGone() {
        if (getVisibility() == 0 && getAlpha() > 0.0f) {
            if (DarkModeCompat.a(getContext())) {
                setVisibility(8);
            } else {
                animateGone();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void processBlurView(View view, final boolean z, final boolean z2) {
        if (view != null) {
            if (DarkModeCompat.a(getContext())) {
                DarkModeCompat.a((View) this, false);
                setImageDrawable(new ColorDrawable(-16777216));
                setAlpha(0.5f);
                setVisibility(0);
                return;
            }
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                final Bitmap createBitmap = Bitmap.createBitmap(drawingCache);
                view.setDrawingCacheEnabled(false);
                SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                    public void run() {
                        System.currentTimeMillis();
                        Bitmap unused = CommonBlurView.this.mScaledBitmap = Bitmap.createScaledBitmap(createBitmap, createBitmap.getWidth() / 5, createBitmap.getHeight() / 5, false);
                        CommonBlurView.this.manager.a(CommonBlurView.this.mScaledBitmap, 5);
                        CommonBlurView.this.post(new Runnable() {
                            public void run() {
                                Bitmap unused = CommonBlurView.this.mBeforeBitmap = createBitmap;
                                Bitmap unused2 = CommonBlurView.this.mAfterBitmap = CommonBlurView.this.mScaledBitmap;
                                CommonBlurView.this.setImageBitmap(CommonBlurView.this.mAfterBitmap);
                                if (z2) {
                                    CommonBlurView.this.setVisibility(0);
                                }
                                if (z) {
                                    CommonBlurView.this.animateStart();
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void animateStart() {
        Log.i("zc", "animateStart");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", new float[]{0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    private void animateGone() {
        Log.i("zc", "animateGone");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f, 0.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception unused) {
        }
        if (getVisibility() == 0) {
            canvas.drawColor(getResources().getColor(R.color.black_50_transparent));
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mView != null) {
        }
    }
}
