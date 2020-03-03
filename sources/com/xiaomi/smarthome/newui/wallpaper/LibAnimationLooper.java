package com.xiaomi.smarthome.newui.wallpaper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;

public abstract class LibAnimationLooper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ValueAnimator f20803a = null;
    /* access modifiers changed from: private */
    public ValueAnimator b = null;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = false;

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public void a(int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    public void c() {
        if (Build.VERSION.SDK_INT >= 19 && !this.f) {
            this.f = true;
            if (this.f20803a != null) {
                this.f20803a.pause();
            }
            if (this.b != null) {
                this.b.pause();
            }
        }
    }

    public void d() {
        if (Build.VERSION.SDK_INT >= 19 && this.f) {
            this.f = false;
            if (this.f20803a != null) {
                this.f20803a.resume();
            }
            if (this.b != null) {
                this.b.resume();
            }
        }
    }

    public void e() {
        this.f = true;
        if (this.f20803a != null) {
            this.f20803a.cancel();
        }
        this.f20803a = null;
        if (this.b != null) {
            this.b.cancel();
        }
        this.b = null;
        this.g = true;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    protected LibAnimationLooper() {
    }

    public LibAnimationLooper(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, int i3, int i4) {
        if (i > 0) {
            this.f20803a = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.f20803a.setDuration((long) i);
            this.f20803a.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    ValueAnimator unused = LibAnimationLooper.this.f20803a = null;
                    LibAnimationLooper.this.i();
                }
            });
        }
        if (i3 > 0) {
            this.b = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.b.setDuration((long) i3);
            this.b.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    ValueAnimator unused = LibAnimationLooper.this.b = null;
                    boolean unused2 = LibAnimationLooper.this.g = true;
                    LibAnimationLooper.this.a();
                }
            });
        }
        if (i2 > 0) {
            this.c = i2;
        }
        if (i4 > 0) {
            this.d = i4;
        } else {
            this.c = 0;
        }
        if (this.f20803a != null) {
            this.f20803a.start();
        } else {
            i();
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.g) {
            a();
        } else if (this.c >= 0) {
            int i = this.c;
            this.c = i - 1;
            if (i >= 1) {
                this.e = 0;
                b();
            } else if (this.b != null) {
                this.b.start();
            } else {
                this.g = true;
                a();
            }
        }
    }

    public final void h() {
        if (this.e < this.d) {
            synchronized (this) {
                a(this.e, this.d);
                this.e++;
                if (this.e >= this.d) {
                    i();
                }
            }
        }
    }
}
