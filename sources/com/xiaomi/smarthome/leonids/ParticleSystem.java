package com.xiaomi.smarthome.leonids;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.xiaomi.smarthome.leonids.initializers.AccelerationInitializer;
import com.xiaomi.smarthome.leonids.initializers.ParticleInitializer;
import com.xiaomi.smarthome.leonids.initializers.RotationInitiazer;
import com.xiaomi.smarthome.leonids.initializers.RotationSpeedInitializer;
import com.xiaomi.smarthome.leonids.initializers.ScaleInitializer;
import com.xiaomi.smarthome.leonids.initializers.SpeeddByComponentsInitializer;
import com.xiaomi.smarthome.leonids.initializers.SpeeddModuleAndRangeInitializer;
import com.xiaomi.smarthome.leonids.modifiers.AlphaModifier;
import com.xiaomi.smarthome.leonids.modifiers.ParticleModifier;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ParticleSystem {

    /* renamed from: a  reason: collision with root package name */
    private static final long f18431a = 50;
    private int A;
    private int B;
    private int C;
    private int D;
    private float E;
    private float F;
    private boolean G;
    private float H;
    private int I;
    private ViewGroup b;
    private int c;
    private Random d;
    private ParticleField e;
    private ArrayList<Particle> f;
    private final ArrayList<Particle> g;
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
    private final ParticleTimerTask q;
    private float r;
    private int[] s;
    private int t;
    private int u;
    private int v;
    private int w;
    private ParticleSystemType x;
    private int y;
    private int z;

    public enum ParticleSystemType {
        Normal,
        Sweeper,
        AirPurifier
    }

    private boolean f(int i2, int i3) {
        return (i2 & i3) == i3;
    }

    private static class ParticleTimerTask extends TimerTask {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<ParticleSystem> f18434a;

        public ParticleTimerTask(ParticleSystem particleSystem) {
            this.f18434a = new WeakReference<>(particleSystem);
        }

        public void run() {
            if (this.f18434a.get() != null) {
                ParticleSystem particleSystem = (ParticleSystem) this.f18434a.get();
                particleSystem.f(particleSystem.i);
                long unused = particleSystem.i = particleSystem.i + ParticleSystem.f18431a;
            }
        }
    }

    private ParticleSystem(ViewGroup viewGroup, int i2, long j2) {
        this.g = new ArrayList<>();
        this.i = 0;
        this.q = new ParticleTimerTask(this);
        this.x = ParticleSystemType.Normal;
        this.E = 1.0f;
        this.F = 1.0f;
        this.G = false;
        this.H = 1.0f;
        this.d = new Random();
        this.s = new int[2];
        a(viewGroup);
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.c = i2;
        this.f = new ArrayList<>();
        this.h = j2;
        this.r = viewGroup.getContext().getResources().getDisplayMetrics().xdpi / 160.0f;
    }

    public ParticleSystem(ViewGroup viewGroup, int i2, Drawable drawable, long j2) {
        this(viewGroup, i2, j2);
        Bitmap bitmap;
        this.I = i2;
        int i3 = 0;
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            while (i3 < this.c) {
                this.f.add(new AnimatedParticle(animationDrawable));
                i3++;
            }
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        while (i3 < this.c) {
            this.f.add(new Particle(bitmap));
            i3++;
        }
    }

    public ParticleSystem(Activity activity, int i2, int i3, long j2) {
        this(activity, i2, activity.getResources().getDrawable(i3), j2, 16908290);
    }

    public ParticleSystem(Activity activity, int i2, int i3, long j2, int i4) {
        this(activity, i2, activity.getResources().getDrawable(i3), j2, i4);
    }

    public ParticleSystem(Activity activity, int i2, Drawable drawable, long j2) {
        this(activity, i2, drawable, j2, 16908290);
    }

    public ParticleSystem(Activity activity, int i2, Drawable drawable, long j2, int i3) {
        this((ViewGroup) activity.findViewById(i3), i2, drawable, j2);
    }

    public float a(float f2) {
        return f2 * this.r;
    }

    public ParticleSystem(Activity activity, int i2, Bitmap bitmap, long j2) {
        this(activity, i2, bitmap, j2, 16908290);
    }

    public ParticleSystem(Activity activity, int i2, Bitmap bitmap, long j2, int i3) {
        this((ViewGroup) activity.findViewById(i3), i2, j2);
        for (int i4 = 0; i4 < this.c; i4++) {
            this.f.add(new Particle(bitmap));
        }
    }

    public ParticleSystem(Activity activity, int i2, AnimationDrawable animationDrawable, long j2) {
        this(activity, i2, animationDrawable, j2, 16908290);
    }

    public ParticleSystem(Activity activity, int i2, AnimationDrawable animationDrawable, long j2, int i3) {
        this((ViewGroup) activity.findViewById(i3), i2, j2);
        for (int i4 = 0; i4 < this.c; i4++) {
            this.f.add(new AnimatedParticle(animationDrawable));
        }
    }

    public ParticleSystem a(ParticleModifier particleModifier) {
        this.m.add(particleModifier);
        return this;
    }

    public ParticleSystem a(float f2, float f3) {
        this.n.add(new SpeeddModuleAndRangeInitializer(a(f2), a(f3), 0, 360));
        return this;
    }

    public ParticleSystem a(float f2, float f3, int i2, int i3) {
        while (i3 < i2) {
            i3 += 360;
        }
        this.n.add(new SpeeddModuleAndRangeInitializer(a(f2), a(f3), i2, i3));
        return this;
    }

    public ParticleSystem a(float f2, float f3, float f4, float f5) {
        this.n.add(new SpeeddByComponentsInitializer(a(f2), a(f3), a(f4), a(f5)));
        return this;
    }

    public ParticleSystem a(int i2, int i3) {
        this.n.add(new RotationInitiazer(i2, i3));
        return this;
    }

    public ParticleSystem b(float f2, float f3) {
        this.n.add(new ScaleInitializer(f2, f3));
        return this;
    }

    public ParticleSystem a(ParticleSystemType particleSystemType) {
        this.x = particleSystemType;
        return this;
    }

    public ParticleSystem a(int i2) {
        this.D = i2;
        return this;
    }

    public void b(float f2) {
        this.F = f2;
    }

    public ParticleSystem c(float f2) {
        this.n.add(new RotationSpeedInitializer(f2, f2));
        return this;
    }

    public ParticleSystem c(float f2, float f3) {
        this.n.add(new RotationSpeedInitializer(f2, f3));
        return this;
    }

    public ParticleSystem b(float f2, float f3, int i2, int i3) {
        this.n.add(new AccelerationInitializer(a(f2), a(f3), i2, i3));
        return this;
    }

    public ParticleSystem a(float f2, int i2) {
        this.n.add(new AccelerationInitializer(f2, f2, i2, i2));
        return this;
    }

    public ParticleSystem d(float f2) {
        this.n.add(new AccelerationInitializer(a(f2), a(f2)));
        return this;
    }

    public ParticleSystem a(ViewGroup viewGroup) {
        this.b = viewGroup;
        if (this.b != null) {
            this.b.getLocationInWindow(this.s);
        }
        return this;
    }

    public ParticleSystem a(long j2) {
        this.i = j2;
        return this;
    }

    public ParticleSystem a(long j2, Interpolator interpolator) {
        this.m.add(new AlphaModifier(255, 0, this.h - j2, this.h, interpolator));
        return this;
    }

    public ParticleSystem b(long j2) {
        return a(j2, (Interpolator) new LinearInterpolator());
    }

    public void a(View view, int i2, int i3, int i4) {
        d(view, i2);
        d(i3, i4);
    }

    public void a(View view, int i2, int i3) {
        a(view, 17, i2, i3);
    }

    public void a(View view, int i2) {
        b(view, 17, i2);
    }

    public void b(View view, int i2, int i3) {
        d(view, i2);
        b(i3);
    }

    private void b(int i2) {
        this.k = 0;
        this.j = ((float) i2) / 1000.0f;
        this.e = new ParticleField(this.b.getContext());
        this.b.addView(this.e);
        this.l = -1;
        this.e.a(this.g);
        c(i2);
        this.p = new Timer();
        this.p.schedule(this.q, 0, f18431a);
    }

    public void a(int i2, int i3, int i4, int i5) {
        c(i2, i3);
        d(i4, i5);
    }

    private void c(int i2, int i3) {
        this.t = i2 - this.s[0];
        this.u = this.t;
        this.v = i3 - this.s[1];
        this.w = this.v;
    }

    private void d(int i2, int i3) {
        this.k = 0;
        this.j = ((float) i2) / 1000.0f;
        this.e = new ParticleField(this.b.getContext());
        this.b.addView(this.e);
        this.e.a(this.g);
        c(i2);
        long j2 = (long) i3;
        this.l = j2;
        a((Interpolator) new LinearInterpolator(), j2 + this.h);
    }

    public void a(int i2, int i3, int i4) {
        c(i2, i3);
        b(i4);
    }

    public void b(int i2, int i3, int i4) {
        e(i2, i3);
        b(i4);
    }

    private void e(int i2, int i3) {
        if (this.b != null) {
            this.A = this.b.getWidth() / 2;
            this.B = this.b.getHeight() / 2;
            this.y = i2;
            this.z = i3;
        }
    }

    public void b(int i2, int i3) {
        c(i2, i3);
    }

    public void b(View view, int i2) {
        d(view, i2);
    }

    public void c(View view, int i2) {
        a(view, i2, (Interpolator) new LinearInterpolator());
    }

    public void a(View view, int i2, Interpolator interpolator) {
        d(view, 17);
        int i3 = 0;
        this.k = 0;
        this.l = this.h;
        while (i3 < i2 && i3 < this.c) {
            c(0);
            i3++;
        }
        this.e = new ParticleField(this.b.getContext());
        this.b.addView(this.e);
        this.e.a(this.g);
        a(interpolator, this.h);
    }

    private void a(Interpolator interpolator, long j2) {
        this.o = ValueAnimator.ofInt(new int[]{0, (int) j2});
        this.o.setDuration(j2);
        this.o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ParticleSystem.this.f((long) ((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        this.o.addListener(new Animator.AnimatorListener() {
            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                ParticleSystem.this.e();
            }

            public void onAnimationCancel(Animator animator) {
                ParticleSystem.this.e();
            }
        });
        this.o.setInterpolator(interpolator);
        this.o.start();
    }

    private void d(View view, int i2) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        if (f(i2, 3)) {
            this.t = iArr[0] - this.s[0];
            this.u = this.t;
        } else if (f(i2, 5)) {
            this.t = (iArr[0] + view.getWidth()) - this.s[0];
            this.u = this.t;
        } else if (f(i2, 1)) {
            this.t = (iArr[0] + (view.getWidth() / 2)) - this.s[0];
            this.u = this.t;
        } else {
            this.t = iArr[0] - this.s[0];
            this.u = (iArr[0] + view.getWidth()) - this.s[0];
        }
        if (f(i2, 48)) {
            this.v = iArr[1] - this.s[1];
            this.w = this.v;
        } else if (f(i2, 80)) {
            this.v = (iArr[1] + view.getHeight()) - this.s[1];
            this.w = this.v;
        } else if (f(i2, 16)) {
            this.v = (iArr[1] + (view.getHeight() / 2)) - this.s[1];
            this.w = this.v;
        } else {
            this.v = iArr[1] - this.s[1];
            this.w = (iArr[1] + view.getHeight()) - this.s[1];
        }
    }

    private void c(long j2) {
        Particle remove = this.f.remove(0);
        remove.a();
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            this.n.get(i2).a(remove, this.d);
        }
        remove.a(this.h, (float) g(this.t, this.u), (float) g(this.v, this.w));
        remove.a(j2, this.m);
        this.g.add(remove);
        this.k++;
    }

    private void d(long j2) {
        Particle remove = this.f.remove(0);
        remove.a();
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            this.n.get(i2).a(remove, this.d);
        }
        int[] d2 = d();
        remove.a(this.h, (float) d2[0], (float) d2[1], d2[2], ParticleSystemType.Sweeper);
        remove.a(j2, this.m);
        this.g.add(remove);
        this.k++;
    }

    private void e(long j2) {
        int i2 = 0;
        if (this.G) {
            Particle remove = this.f.remove(0);
            remove.a();
            for (int i3 = 0; i3 < this.n.size(); i3++) {
                this.n.get(i3).a(remove, this.d);
            }
            int g2 = g(this.t, this.u);
            int height = this.v == 0 ? 0 : this.b.getHeight();
            if (this.v != 0) {
                i2 = this.b.getHeight();
            }
            remove.a(this.h, (float) g2, (float) g(height, i2), 0, ParticleSystemType.AirPurifier);
            remove.a(j2, this.m);
            this.g.add(remove);
            this.k++;
            return;
        }
        for (int i4 = 0; i4 < 10 && this.f.size() != 0; i4++) {
            Particle remove2 = this.f.remove(0);
            remove2.a();
            for (int i5 = 0; i5 < this.n.size(); i5++) {
                this.n.get(i5).a(remove2, this.d);
            }
            remove2.a((long) (this.d.nextInt(Math.abs((int) this.h)) + 5000), (float) this.d.nextInt(Math.abs(this.b.getWidth())), (float) this.d.nextInt(Math.abs(this.b.getHeight())), 0, ParticleSystemType.AirPurifier);
            remove2.a(j2, this.m);
            this.g.add(remove2);
            this.k++;
        }
        this.G = true;
    }

    public void e(float f2) {
        this.H = f2;
    }

    private int[] d() {
        int nextInt = this.d.nextInt(360);
        double d2 = (double) nextInt;
        Double.isNaN(d2);
        int nextInt2 = this.d.nextInt(this.z - this.y) + this.y;
        double d3 = (double) this.A;
        double d4 = (double) nextInt2;
        double d5 = (double) ((float) ((d2 * 3.141592653589793d) / 180.0d));
        double sin = Math.sin(d5);
        Double.isNaN(d4);
        Double.isNaN(d3);
        double d6 = (double) this.B;
        double cos = Math.cos(d5);
        Double.isNaN(d4);
        Double.isNaN(d6);
        return new int[]{(int) (d3 + (sin * d4)), (int) (d6 - (d4 * cos)), nextInt};
    }

    private int g(int i2, int i3) {
        if (i2 == i3) {
            return i2;
        }
        if (i2 < i3) {
            return this.d.nextInt(i3 - i2) + i2;
        }
        return this.d.nextInt(i2 - i3) + i3;
    }

    /* access modifiers changed from: private */
    public void f(long j2) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (((this.l > 0 && j2 < this.l) || this.l == -1) && !this.f.isEmpty() && ((float) this.k) < this.j * ((float) j2)) {
                if (this.x != ParticleSystemType.Normal) {
                    if (this.x != ParticleSystemType.Sweeper) {
                        if (this.x == ParticleSystemType.AirPurifier) {
                            if (((float) this.g.size()) >= ((float) this.I) * this.H) {
                                break;
                            }
                            e(j2);
                            i3++;
                            if (((float) i3) > this.j * 50.0f) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        d(j2);
                    }
                } else {
                    c(j2);
                }
            } else {
                break;
            }
        }
        synchronized (this.g) {
            if (this.D != this.C) {
                Iterator<Particle> it = this.f.iterator();
                while (it.hasNext()) {
                    Particle next = it.next();
                    next.a(a(this.D, next.b()));
                }
                Iterator<Particle> it2 = this.g.iterator();
                while (it2.hasNext()) {
                    Particle next2 = it2.next();
                    next2.a(a(this.D, next2.b()));
                }
                this.C = this.D;
            }
            if (this.F != this.E) {
                Iterator<Particle> it3 = this.f.iterator();
                while (it3.hasNext()) {
                    it3.next().a(this.F);
                }
                Iterator<Particle> it4 = this.g.iterator();
                while (it4.hasNext()) {
                    it4.next().a(this.F);
                }
                this.E = this.F;
            }
            while (i2 < this.g.size()) {
                if (!this.g.get(i2).a(j2)) {
                    i2--;
                    this.f.add(this.g.remove(i2));
                    this.k--;
                }
                i2++;
            }
        }
        if (this.e != null) {
            this.e.postInvalidate();
        }
    }

    public Bitmap a(int i2, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    /* access modifiers changed from: private */
    public void e() {
        this.b.removeView(this.e);
        this.e = null;
        this.b.postInvalidate();
        this.f.addAll(this.g);
    }

    public void a() {
        this.l = this.i;
    }

    public void b() {
        if (this.o != null && this.o.isRunning()) {
            this.o.cancel();
        }
        if (this.p != null) {
            this.p.cancel();
            this.p.purge();
            e();
        }
    }

    private void c(int i2) {
        if (i2 != 0) {
            long j2 = (this.i / 1000) / ((long) i2);
            if (j2 != 0) {
                long j3 = this.i / j2;
                int i3 = 1;
                while (true) {
                    long j4 = (long) i3;
                    if (j4 <= j2) {
                        f((j4 * j3) + 1);
                        i3++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public List<Particle> c() {
        return this.f;
    }
}
