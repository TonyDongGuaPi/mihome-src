package com.xiaomi.smarthome.newui.wallpaper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class LibAnimationView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private LibAnimationComposition f20806a;

    public LibAnimationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LibAnimationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LibAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f20806a = null;
    }

    public void load(LibAnimationConfig libAnimationConfig, LibAnimationBitmapLoader libAnimationBitmapLoader) {
        if (this.f20806a == null) {
            this.f20806a = new LibAnimationComposition(libAnimationConfig, this, libAnimationBitmapLoader);
        }
    }

    public boolean isLoop() {
        if (this.f20806a == null) {
            return false;
        }
        return this.f20806a.d();
    }

    public void setLoop(boolean z) {
        if (this.f20806a != null) {
            this.f20806a.a(z);
        }
    }

    public void playAnimation() {
        if (this.f20806a != null) {
            if (this.f20806a.f()) {
                this.f20806a.h();
            } else {
                this.f20806a.j();
            }
        }
    }

    public void pauseAnimation() {
        if (this.f20806a != null) {
            this.f20806a.g();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f20806a != null) {
            this.f20806a.a(this);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f20806a != null) {
            this.f20806a.a((LibAnimationView) null);
        }
    }

    public void clearAnimation() {
        if (this.f20806a != null) {
            this.f20806a.i();
            this.f20806a = null;
        }
    }

    public boolean isRunning() {
        if (this.f20806a == null) {
            return false;
        }
        return this.f20806a.e();
    }

    public boolean isPaused() {
        if (this.f20806a == null) {
            return false;
        }
        return this.f20806a.f();
    }
}
