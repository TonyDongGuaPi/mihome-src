package com.xiaomi.smarthome.newui.wallpaper;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import com.google.code.microlog4android.format.PatternFormatter;
import com.xiaomi.smarthome.newui.wallpaper.LibAnimationConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibAnimation {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, TimeInterpolator> f20788a = new HashMap();
    /* access modifiers changed from: private */
    public LibAnimationComposition b;
    private LibAnimationConfig.Animations c;
    /* access modifiers changed from: private */
    public List<ValueAnimator> d = null;
    /* access modifiers changed from: private */
    public ValueAnimator e = null;
    /* access modifiers changed from: private */
    public LibAnimationLayer f;
    private boolean g = false;
    private boolean h = false;

    public static final void a(String str) {
    }

    private static void f() {
        if (f20788a.isEmpty()) {
            f20788a.put("accelerate_decelerate", new AccelerateDecelerateInterpolator());
            f20788a.put("accelerate", new AccelerateInterpolator());
            f20788a.put("decelerate", new DecelerateInterpolator());
            f20788a.put("anticipate", new AnticipateInterpolator());
            f20788a.put("anticipate_overshoot", new AnticipateOvershootInterpolator());
            f20788a.put("bounce", new BounceInterpolator());
            f20788a.put("fast_out", new FastOutLinearInInterpolator());
            f20788a.put("fast_out_slow", new FastOutSlowInInterpolator());
            f20788a.put("overshoot", new OvershootInterpolator());
        }
    }

    public LibAnimation(LibAnimationComposition libAnimationComposition, LibAnimationLayer libAnimationLayer, LibAnimationConfig.Animations animations) {
        this.b = libAnimationComposition;
        this.c = animations;
        this.f = libAnimationLayer;
    }

    public boolean a() {
        return this.g;
    }

    public boolean b() {
        return this.h;
    }

    /* access modifiers changed from: private */
    public void b(LibAnimationLooper libAnimationLooper) {
        this.g = false;
        this.h = false;
        if (libAnimationLooper != null) {
            libAnimationLooper.h();
        }
    }

    public void c() {
        this.g = false;
        this.h = false;
        if (this.d != null) {
            for (ValueAnimator cancel : this.d) {
                cancel.cancel();
            }
        }
        this.d = null;
        if (this.e != null) {
            this.e.cancel();
        }
        this.e = null;
    }

    public void d() {
        if (this.g && !this.h) {
            this.h = true;
            if (Build.VERSION.SDK_INT < 19) {
                return;
            }
            if (this.d != null) {
                for (ValueAnimator pause : this.d) {
                    pause.pause();
                }
            } else if (this.e != null) {
                this.e.pause();
            }
        }
    }

    public void e() {
        if (this.g && this.h) {
            this.h = true;
            if (Build.VERSION.SDK_INT < 19) {
                return;
            }
            if (this.d != null) {
                for (ValueAnimator resume : this.d) {
                    resume.resume();
                }
            } else if (this.e != null) {
                this.e.resume();
            }
        }
    }

    public void a(final LibAnimationLooper libAnimationLooper) {
        if (!this.g) {
            this.g = true;
            this.h = false;
            this.d = new ArrayList();
            a('x', this.c.f, this.c.q);
            a('y', this.c.g, this.c.r);
            a('w', this.c.h, this.c.s);
            a('h', this.c.i, this.c.t);
            a('a', this.c.j, this.c.u);
            a('X', this.c.k, this.c.v);
            a('Y', this.c.l, this.c.w);
            a('Z', this.c.m, this.c.x);
            a(PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR, this.c.n, this.c.y);
            a('1', this.c.o, this.c.z);
            a('2', this.c.p, this.c.A);
            if (this.c.d > 0) {
                this.e = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                this.e.setDuration((long) this.c.d);
                this.e.addListener(new Animator.AnimatorListener() {
                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }

                    public void onAnimationStart(Animator animator) {
                        LibAnimation.a("real waiter start");
                    }

                    public void onAnimationEnd(Animator animator) {
                        LibAnimation.a("real waiter end");
                        if (LibAnimation.this.e != null) {
                            ValueAnimator unused = LibAnimation.this.e = null;
                            LibAnimation.this.b(libAnimationLooper);
                        }
                    }
                });
            }
            if (!this.d.isEmpty()) {
                this.d.iterator().next().addListener(new Animator.AnimatorListener() {
                    public void onAnimationRepeat(Animator animator) {
                    }

                    public void onAnimationStart(Animator animator) {
                        LibAnimation.a("real start");
                    }

                    public void onAnimationEnd(Animator animator) {
                        LibAnimation.a("real end");
                        List unused = LibAnimation.this.d = null;
                        if (LibAnimation.this.e != null) {
                            LibAnimation.this.e.start();
                        } else {
                            LibAnimation.this.b(libAnimationLooper);
                        }
                    }

                    public void onAnimationCancel(Animator animator) {
                        ValueAnimator unused = LibAnimation.this.e = null;
                        onAnimationEnd(animator);
                    }
                });
                for (ValueAnimator start : this.d) {
                    a("real begin");
                    start.start();
                }
            } else if (this.e != null) {
                this.e.start();
            } else {
                b(libAnimationLooper);
            }
        }
    }

    private void a(final char c2, String str, float[] fArr) {
        if (fArr != null && fArr.length >= 1) {
            final ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
            ofFloat.setDuration((long) this.c.b);
            if (this.c.f20798a > 1) {
                ofFloat.setRepeatCount(this.c.f20798a - 1);
            }
            if (this.c.c > 0) {
                ofFloat.setStartDelay((long) this.c.c);
            }
            f();
            TimeInterpolator timeInterpolator = f20788a.get(str);
            if (timeInterpolator == null) {
                timeInterpolator = f20788a.get("linear");
            }
            if (timeInterpolator == null) {
                timeInterpolator = new LinearInterpolator();
            }
            ofFloat.setInterpolator(timeInterpolator);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    View b2 = LibAnimation.this.f.b();
                    if (b2 == null) {
                        ofFloat.cancel();
                        return;
                    }
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    switch (c2) {
                        case '1':
                            b2.setRotationX(floatValue);
                            return;
                        case '2':
                            b2.setRotationY(floatValue);
                            return;
                        case 'X':
                            b2.setTranslationX(floatValue);
                            return;
                        case 'Y':
                            b2.setTranslationY(floatValue);
                            return;
                        case 'a':
                            b2.setAlpha(1.0f - floatValue);
                            return;
                        case 'h':
                            b2.setScaleY(floatValue);
                            return;
                        case 'r':
                            b2.setRotation(floatValue);
                            return;
                        case 'w':
                            b2.setScaleX(floatValue);
                            return;
                        case 'x':
                            b2.setX(LibAnimation.this.b.b() * (floatValue + LibAnimation.this.f.a().d));
                            return;
                        case 'y':
                            b2.setY(LibAnimation.this.b.c() * (floatValue + LibAnimation.this.f.a().e));
                            return;
                        default:
                            return;
                    }
                }
            });
            this.d.add(ofFloat);
        }
    }
}
