package com.xiaomi.youpin.share.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.xiaomi.youpin.common.util.ConvertUtils;
import com.xiaomi.youpin.common.util.blur.StackBlurManager;
import com.xiaomi.youpin.share.R;

public abstract class NewShareAnimationActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private StackBlurManager f23685a;
    /* access modifiers changed from: private */
    public BitmapDrawable b;
    private boolean c;
    private AnimatorSet d;
    private AnimatorSet e;
    private boolean f = false;
    protected Bitmap mBlurBitmap;

    /* access modifiers changed from: protected */
    public abstract View getBackgroundView();

    /* access modifiers changed from: protected */
    public abstract View getContainerView();

    /* access modifiers changed from: protected */
    public abstract void onActivityDismiss();

    /* access modifiers changed from: protected */
    public abstract void onActivityShow();

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("bitmap");
        if (bitmap != null) {
            if (getIntent().getBooleanExtra("needBlur", false)) {
                this.f23685a = new StackBlurManager(bitmap);
                this.mBlurBitmap = this.f23685a.a(ConvertUtils.a(2.0f));
            } else {
                this.mBlurBitmap = bitmap;
            }
            this.b = new BitmapDrawable(getResources(), this.mBlurBitmap);
        }
    }

    private void a() {
        AnonymousClass1 r0 = new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                if (NewShareAnimationActivity.this.b != null) {
                    NewShareAnimationActivity.this.getContainerView().setBackground(NewShareAnimationActivity.this.b);
                }
                NewShareAnimationActivity.this.onActivityShow();
            }
        };
        this.d = new AnimatorSet();
        ObjectAnimator ofObject = ObjectAnimator.ofObject(getBackgroundView(), "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(ContextCompat.getColor(this.mContext, R.color.black_00_transparent)), Integer.valueOf(ContextCompat.getColor(this.mContext, R.color.black_60_transparent))});
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getContainerView(), "alpha", new float[]{0.0f, 1.0f});
        this.d.setDuration(300);
        this.d.addListener(r0);
        this.d.play(ofObject).with(ofFloat);
    }

    private void b() {
        AnonymousClass2 r0 = new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                NewShareAnimationActivity.this.onActivityDismiss();
            }
        };
        this.e = new AnimatorSet();
        ObjectAnimator ofObject = ObjectAnimator.ofObject(getBackgroundView(), "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(ContextCompat.getColor(this.mContext, R.color.black_60_transparent)), Integer.valueOf(ContextCompat.getColor(this.mContext, R.color.black_00_transparent))});
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getContainerView(), "alpha", new float[]{1.0f, 0.0f});
        this.e.setDuration(300);
        this.e.play(ofObject).with(ofFloat);
        this.e.addListener(r0);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !this.c) {
            showInAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void showInAnimation() {
        if (!this.f) {
            this.f = true;
            if (Build.VERSION.SDK_INT >= 11) {
                a();
                this.d.start();
                return;
            }
            getContainerView().setBackground(this.b);
            getContainerView().setBackgroundColor(getResources().getColor(R.color.black_60_transparent));
            onActivityShow();
        }
    }

    public void onBackPressed() {
        finishPage();
    }

    /* access modifiers changed from: protected */
    public void finishPage() {
        this.c = true;
        if (Build.VERSION.SDK_INT >= 11) {
            b();
            this.e.start();
            return;
        }
        getContainerView().setBackgroundColor(getResources().getColor(R.color.black_00_transparent));
        onActivityDismiss();
    }

    /* access modifiers changed from: protected */
    public void finishFinal() {
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
