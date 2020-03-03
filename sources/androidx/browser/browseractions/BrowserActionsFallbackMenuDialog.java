package androidx.browser.browseractions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.MotionEvent;
import android.view.View;

class BrowserActionsFallbackMenuDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private static final long f476a = 250;
    private static final long b = 150;
    private final View c;

    BrowserActionsFallbackMenuDialog(Context context, View view) {
        super(context);
        this.c = view;
    }

    public void show() {
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        a(true);
        super.show();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        dismiss();
        return true;
    }

    public void dismiss() {
        a(false);
    }

    private void a(final boolean z) {
        float f = 1.0f;
        float f2 = z ? 0.0f : 1.0f;
        if (!z) {
            f = 0.0f;
        }
        long j = z ? f476a : 150;
        this.c.setScaleX(f2);
        this.c.setScaleY(f2);
        this.c.animate().scaleX(f).scaleY(f).setDuration(j).setInterpolator(new LinearOutSlowInInterpolator()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (!z) {
                    BrowserActionsFallbackMenuDialog.super.dismiss();
                }
            }
        }).start();
    }
}
