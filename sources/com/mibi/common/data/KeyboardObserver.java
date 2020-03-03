package com.mibi.common.data;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class KeyboardObserver {

    /* renamed from: a  reason: collision with root package name */
    private Activity f7524a;
    private ViewTreeObserver.OnGlobalLayoutListener b;

    public interface KeyboardVisibilityListener {
        void a(boolean z);
    }

    public KeyboardObserver(Activity activity) {
        this.f7524a = activity;
    }

    public final void a(final KeyboardVisibilityListener keyboardVisibilityListener) {
        if (this.f7524a != null) {
            final View findViewById = this.f7524a.findViewById(16908290);
            if (this.b != null) {
                a();
            }
            this.b = new ViewTreeObserver.OnGlobalLayoutListener() {
                private boolean d;
                private final Rect e = new Rect();

                public void onGlobalLayout() {
                    findViewById.getWindowVisibleDisplayFrame(this.e);
                    boolean z = findViewById.getRootView().getHeight() - this.e.height() > 100;
                    if (z != this.d) {
                        this.d = z;
                        if (keyboardVisibilityListener != null) {
                            keyboardVisibilityListener.a(z);
                        }
                    }
                }
            };
            findViewById.getViewTreeObserver().addOnGlobalLayoutListener(this.b);
        }
    }

    public final void a() {
        if (this.f7524a != null && this.b != null) {
            ((ViewGroup) this.f7524a.findViewById(16908290)).getViewTreeObserver().removeOnGlobalLayoutListener(this.b);
            this.b = null;
        }
    }
}
