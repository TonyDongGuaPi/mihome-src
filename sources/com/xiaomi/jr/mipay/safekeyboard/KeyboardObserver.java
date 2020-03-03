package com.xiaomi.jr.mipay.safekeyboard;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

public class KeyboardObserver {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10975a = 300;
    /* access modifiers changed from: private */
    public View b;
    private ViewTreeObserver.OnGlobalLayoutListener c;

    public interface KeyboardVisibilityListener {
        void a(boolean z);
    }

    public KeyboardObserver(View view) {
        this.b = ((Activity) view.getContext()).findViewById(16908290);
    }

    public final void a(final KeyboardVisibilityListener keyboardVisibilityListener) {
        if (this.b != null) {
            if (this.c != null) {
                a();
            }
            this.c = new ViewTreeObserver.OnGlobalLayoutListener() {
                private boolean c;
                private final Rect d = new Rect();

                public void onGlobalLayout() {
                    KeyboardObserver.this.b.getWindowVisibleDisplayFrame(this.d);
                    boolean z = KeyboardObserver.this.b.getRootView().getHeight() - this.d.height() > 300;
                    if (z != this.c) {
                        this.c = z;
                        if (keyboardVisibilityListener != null) {
                            keyboardVisibilityListener.a(z);
                        }
                    }
                }
            };
            this.b.getViewTreeObserver().addOnGlobalLayoutListener(this.c);
        }
    }

    public final void a() {
        if (this.b != null && this.c != null) {
            this.b.getViewTreeObserver().removeOnGlobalLayoutListener(this.c);
            this.c = null;
        }
    }
}
