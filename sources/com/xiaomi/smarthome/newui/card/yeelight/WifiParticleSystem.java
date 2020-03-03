package com.xiaomi.smarthome.newui.card.yeelight;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.newui.card.yeelight.initializers.AccelerationInitializer;
import com.xiaomi.smarthome.newui.card.yeelight.initializers.AlphaInitializer;
import com.xiaomi.smarthome.newui.card.yeelight.initializers.ParticleInitializer;
import com.xiaomi.smarthome.newui.card.yeelight.initializers.RotationInitiazer;
import com.xiaomi.smarthome.newui.card.yeelight.initializers.RotationSpeedInitializer;
import com.xiaomi.smarthome.newui.card.yeelight.initializers.ScaleInitializer;
import com.xiaomi.smarthome.newui.card.yeelight.initializers.SpeeddByComponentsInitializer;
import com.xiaomi.smarthome.newui.card.yeelight.initializers.SpeeddModuleAndRangeInitializer;
import com.xiaomi.smarthome.newui.card.yeelight.modifiers.AlphaModifier;
import com.xiaomi.smarthome.newui.card.yeelight.modifiers.ParticleModifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WifiParticleSystem {

    /* renamed from: a  reason: collision with root package name */
    private static final long f20614a = 50;
    private ViewGroup b;
    private int c;
    private Random d;
    private ParticleField e;
    private ArrayList<Particle> f;
    private ArrayList<Particle> g;
    private long h;
    /* access modifiers changed from: private */
    public long i;
    private float j;
    private int k;
    private long l;
    private List<ParticleModifier> m;
    private List<ParticleInitializer> n;
    private ValueAnimator o;
    private Timer p;
    private float q;
    private int[] r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;

    private boolean f(int i2, int i3) {
        return (i2 & i3) == i3;
    }

    private WifiParticleSystem(Activity activity, int i2, long j2, int i3) {
        this.i = 0;
        this.d = new Random();
        this.b = (ViewGroup) activity.findViewById(i3);
        if (this.b == null) {
            Log.e("WifiParticleSystem", "!!!parentView==null parentId:" + i3);
            return;
        }
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.c = i2;
        this.g = new ArrayList<>();
        this.f = new ArrayList<>();
        this.h = j2;
        this.r = new int[2];
        this.b.getLocationInWindow(this.r);
        this.q = activity.getResources().getDisplayMetrics().xdpi / 160.0f;
    }

    public WifiParticleSystem(Activity activity, int i2, int i3, long j2) {
        this(activity, i2, activity.getResources().getDrawable(i3), j2, 16908290);
    }

    public WifiParticleSystem(Activity activity, int i2, int i3, long j2, int i4) {
        this(activity, i2, activity.getResources().getDrawable(i3), j2, i4);
    }

    public WifiParticleSystem(Activity activity, int i2, Drawable drawable, long j2) {
        this(activity, i2, drawable, j2, 16908290);
    }

    public WifiParticleSystem(Activity activity, int i2, Drawable drawable, long j2, int i3) {
        this(activity, i2, j2, i3);
        float f2 = ((float) activity.getResources().getDisplayMetrics().widthPixels) / 1920.0f;
        int i4 = 0;
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            while (i4 < this.c) {
                this.f.add(new Particle(bitmap, f2));
                i4++;
            }
        } else if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            while (i4 < this.c) {
                this.f.add(new AnimatedParticle(animationDrawable));
                i4++;
            }
        }
    }

    public float a(float f2) {
        return f2 * this.q;
    }

    public WifiParticleSystem(Activity activity, int i2, Bitmap bitmap, long j2) {
        this(activity, i2, bitmap, j2, 16908290);
    }

    public WifiParticleSystem(Activity activity, int i2, Bitmap bitmap, long j2, int i3) {
        this(activity, i2, j2, i3);
        float f2 = ((float) activity.getResources().getDisplayMetrics().widthPixels) / 1920.0f;
        for (int i4 = 0; i4 < this.c; i4++) {
            this.f.add(new Particle(bitmap, f2));
        }
    }

    public WifiParticleSystem(Activity activity, int i2, AnimationDrawable animationDrawable, long j2) {
        this(activity, i2, animationDrawable, j2, 16908290);
    }

    public WifiParticleSystem(Activity activity, int i2, AnimationDrawable animationDrawable, long j2, int i3) {
        this(activity, i2, j2, i3);
        for (int i4 = 0; i4 < this.c; i4++) {
            this.f.add(new AnimatedParticle(animationDrawable));
        }
    }

    public WifiParticleSystem a(ParticleModifier particleModifier) {
        this.m.add(particleModifier);
        return this;
    }

    public WifiParticleSystem a(float f2, float f3) {
        this.n.add(new SpeeddModuleAndRangeInitializer(a(f2), a(f3), 0, 360));
        return this;
    }

    public WifiParticleSystem a(float f2, float f3, int i2, int i3) {
        this.n.add(new SpeeddModuleAndRangeInitializer(a(f2), a(f3), i2, i3));
        return this;
    }

    public WifiParticleSystem a(float f2, float f3, int i2, int i3, boolean z) {
        this.n.add(new SpeeddModuleAndRangeInitializer(a(f2), a(f3), i2, i3, z));
        return this;
    }

    public WifiParticleSystem a(float f2, float f3, float f4, float f5) {
        this.n.add(new SpeeddByComponentsInitializer(a(f2), a(f3), a(f4), a(f5)));
        return this;
    }

    public WifiParticleSystem a(int i2, int i3) {
        this.n.add(new RotationInitiazer(i2, i3));
        return this;
    }

    public WifiParticleSystem b(float f2, float f3) {
        this.n.add(new ScaleInitializer(f2, f3));
        return this;
    }

    public WifiParticleSystem b(float f2) {
        this.n.add(new RotationSpeedInitializer(f2, f2));
        return this;
    }

    public WifiParticleSystem b(int i2, int i3) {
        this.n.add(new AlphaInitializer(i2, i3));
        return this;
    }

    public WifiParticleSystem c(float f2, float f3) {
        this.n.add(new RotationSpeedInitializer(f2, f3));
        return this;
    }

    public WifiParticleSystem b(float f2, float f3, int i2, int i3) {
        this.n.add(new AccelerationInitializer(a(f2), a(f3), i2, i3));
        return this;
    }

    public WifiParticleSystem a(float f2, int i2) {
        this.n.add(new AccelerationInitializer(f2, f2, i2, i2));
        return this;
    }

    public WifiParticleSystem a(ViewGroup viewGroup) {
        this.b = viewGroup;
        return this;
    }

    public WifiParticleSystem a(int i2) {
        this.i = (long) i2;
        return this;
    }

    public WifiParticleSystem a(long j2, Interpolator interpolator) {
        this.m.add(new AlphaModifier(255, 0, this.h - j2, this.h, interpolator));
        return this;
    }

    public WifiParticleSystem a(long j2) {
        return a(j2, (Interpolator) new LinearInterpolator());
    }

    public void a(View view, int i2, int i3, int i4) {
        c(view, i2);
        e(i3, i4);
    }

    public void a(View view, int i2, int i3) {
        a(view, 17, i2, i3);
    }

    public void a(View view, int i2) {
        b(view, 17, i2);
    }

    public void b(View view, int i2, int i3) {
        c(view, i2);
        c(i3);
    }

    private void c(int i2) {
        this.k = 0;
        this.j = ((float) i2) / 1000.0f;
        this.e = new ParticleField(this.b.getContext());
        this.b.addView(this.e);
        this.l = -1;
        this.e.a(this.g);
        d(i2);
        this.p = new Timer();
        this.p.schedule(new TimerTask() {
            public void run() {
                WifiParticleSystem.this.c(WifiParticleSystem.this.i);
                long unused = WifiParticleSystem.this.i = WifiParticleSystem.this.i + WifiParticleSystem.f20614a;
            }
        }, 0, f20614a);
    }

    public void a(int i2, int i3, int i4, int i5) {
        d(i2, i3);
        e(i4, i5);
    }

    private void d(int i2, int i3) {
        this.s = i2 - this.r[0];
        this.t = this.s;
        this.u = i3 - this.r[1];
        this.v = this.u;
    }

    private void e(int i2, int i3) {
        this.k = 0;
        this.j = ((float) i2) / 1000.0f;
        this.e = new ParticleField(this.b.getContext());
        this.b.addView(this.e);
        this.e.a(this.g);
        d(i2);
        long j2 = (long) i3;
        this.l = j2;
        a((Interpolator) new LinearInterpolator(), j2 + this.h);
    }

    public void a(int i2, int i3, int i4) {
        d(i2, i3);
        c(i4);
    }

    public void c(int i2, int i3) {
        d(i2, i3);
    }

    public void b(View view, int i2) {
        a(view, i2, (Interpolator) new LinearInterpolator());
    }

    public void a(View view, int i2, Interpolator interpolator) {
        c(view, 17);
        int i3 = 0;
        this.k = 0;
        this.l = this.h;
        while (i3 < i2 && i3 < this.c) {
            b(0);
            i3++;
        }
        this.e = new ParticleField(this.b.getContext());
        this.b.addView(this.e);
        this.e.a(this.g);
        a(interpolator, this.h);
    }

    @SuppressLint({"NewApi"})
    private void a(Interpolator interpolator, long j2) {
        this.o = ValueAnimator.ofInt(new int[]{0, (int) j2});
        this.o.setDuration(j2);
        this.o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WifiParticleSystem.this.c((long) ((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        this.o.addListener(new Animator.AnimatorListener() {
            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                WifiParticleSystem.this.d();
            }

            public void onAnimationCancel(Animator animator) {
                WifiParticleSystem.this.d();
            }
        });
        this.o.setInterpolator(interpolator);
        this.o.start();
    }

    private void c(View view, int i2) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        if (f(i2, 3)) {
            this.s = iArr[0] - this.r[0];
            this.t = this.s;
        } else if (f(i2, 5)) {
            this.s = (iArr[0] + view.getWidth()) - this.r[0];
            this.t = this.s;
        } else if (f(i2, 1)) {
            this.s = (iArr[0] + (view.getWidth() / 2)) - this.r[0];
            this.t = this.s;
        } else {
            this.s = iArr[0] - this.r[0];
            this.t = (iArr[0] + view.getWidth()) - this.r[0];
        }
        if (f(i2, 48)) {
            this.u = iArr[1] - this.r[1];
            this.v = this.u;
        } else if (f(i2, 80)) {
            this.u = (iArr[1] + view.getHeight()) - this.r[1];
            this.v = this.u;
        } else if (f(i2, 16)) {
            this.u = (iArr[1] + (view.getHeight() / 2)) - this.r[1];
            this.v = this.u;
        } else {
            this.u = iArr[1] - this.r[1];
            this.v = (iArr[1] + view.getHeight()) - this.r[1];
        }
    }

    private void b(long j2) {
        Particle remove = this.f.remove(0);
        remove.a();
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            this.n.get(i2).a(remove, this.d);
        }
        int g2 = g(this.s, this.t);
        int g3 = g(this.u, this.v);
        remove.a(this.w);
        remove.a(this.h, (float) g2, (float) g3);
        remove.a(j2, this.m);
        this.g.add(remove);
        this.k++;
    }

    private int g(int i2, int i3) {
        if (i2 == i3) {
            return i2;
        }
        try {
            return this.d.nextInt(i3 - i2) + i2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }

    /* access modifiers changed from: private */
    public void c(long j2) {
        while (true) {
            if (((this.l <= 0 || j2 >= this.l) && this.l != -1) || this.f.isEmpty() || ((float) this.k) >= this.j * ((float) j2)) {
                int i2 = 0;
            } else {
                b(j2);
            }
        }
        int i22 = 0;
        while (i22 < this.g.size()) {
            Particle particle = this.g.get(i22);
            particle.a(this.w);
            if (!particle.a(j2)) {
                i22--;
                this.f.add(this.g.remove(i22));
            }
            i22++;
        }
        if (this.e != null) {
            this.e.postInvalidate();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.b.removeView(this.e);
        this.e = null;
        this.b.postInvalidate();
        this.f.addAll(this.g);
    }

    public void a() {
        this.l = this.i;
    }

    @SuppressLint({"NewApi"})
    public void b() {
        if (this.o != null && this.o.isRunning()) {
            this.o.cancel();
        }
        if (this.p != null) {
            this.p.cancel();
            this.p.purge();
            d();
        }
    }

    private void d(int i2) {
        if (i2 != 0) {
            long j2 = (this.i / 1000) / ((long) i2);
            if (j2 != 0) {
                long j3 = this.i / j2;
                int i3 = 1;
                while (true) {
                    long j4 = (long) i3;
                    if (j4 <= j2) {
                        c((j4 * j3) + 1);
                        i3++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public int c() {
        return this.w;
    }

    public WifiParticleSystem b(int i2) {
        this.w = i2;
        return this;
    }
}
