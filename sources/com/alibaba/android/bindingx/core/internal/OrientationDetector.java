package com.alibaba.android.bindingx.core.internal;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.alibaba.android.bindingx.core.LogProxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class OrientationDetector implements SensorEventListener {
    private static OrientationDetector i;
    private static final Object j = new Object();
    private static final Set<Integer> k = Utils.a((E[]) new Integer[]{15});
    private static final Set<Integer> l = Utils.a((E[]) new Integer[]{11});
    private static final Set<Integer> m = Utils.a((E[]) new Integer[]{1, 2});
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    SensorManagerProxy f759a;
    private HandlerThread b;
    private Handler c;
    private final Context d;
    private float[] e;
    private float[] f;
    private float[] g;
    private double[] h;
    private final Set<Integer> n = new HashSet();
    private final List<Set<Integer>> o;
    private Set<Integer> p;
    private boolean q;
    private boolean r;
    private boolean s;
    private ArrayList<OnOrientationChangedListener> t = new ArrayList<>();

    interface OnOrientationChangedListener {
        void a(double d, double d2, double d3);
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    private OrientationDetector(@NonNull Context context) {
        this.d = context.getApplicationContext();
        this.o = Utils.b((E[]) new Set[]{k, l, m});
    }

    static OrientationDetector a(Context context) {
        OrientationDetector orientationDetector;
        synchronized (j) {
            if (i == null) {
                i = new OrientationDetector(context);
            }
            orientationDetector = i;
        }
        return orientationDetector;
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull OnOrientationChangedListener onOrientationChangedListener) {
        if (this.t != null && !this.t.contains(onOrientationChangedListener)) {
            this.t.add(onOrientationChangedListener);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(@Nullable OnOrientationChangedListener onOrientationChangedListener) {
        if (this.t == null) {
            return false;
        }
        if (onOrientationChangedListener != null) {
            return this.t.remove(onOrientationChangedListener);
        }
        this.t.clear();
        return true;
    }

    private boolean b(int i2) {
        if (this.s) {
            return false;
        }
        if (this.p != null) {
            String b2 = b();
            LogProxy.c("[OrientationDetector] register sensor:" + b2);
            return a(this.p, i2, true);
        }
        d();
        for (Set<Integer> set : this.o) {
            this.p = set;
            if (a(this.p, i2, true)) {
                String b3 = b();
                LogProxy.c("[OrientationDetector] register sensor:" + b3);
                return true;
            }
        }
        this.s = true;
        this.p = null;
        this.g = null;
        this.h = null;
        return false;
    }

    private String b() {
        if (this.s) {
            return "NOT_AVAILABLE";
        }
        if (this.p == k) {
            return "GAME_ROTATION_VECTOR";
        }
        if (this.p == l) {
            return "ROTATION_VECTOR";
        }
        return this.p == m ? "ACCELEROMETER_MAGNETIC" : "NOT_AVAILABLE";
    }

    public boolean a(int i2) {
        LogProxy.c("[OrientationDetector] sensor started");
        boolean b2 = b(i2);
        if (b2) {
            a(true);
        }
        return b2;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        LogProxy.c("[OrientationDetector] sensor stopped");
        a((Iterable<Integer>) new HashSet(this.n));
        a(false);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        float[] fArr = sensorEvent.values;
        if (type != 11) {
            if (type != 15) {
                switch (type) {
                    case 1:
                        if (this.r) {
                            a(fArr, this.e);
                            return;
                        }
                        return;
                    case 2:
                        if (this.r) {
                            if (this.e == null) {
                                this.e = new float[3];
                            }
                            System.arraycopy(fArr, 0, this.e, 0, this.e.length);
                            return;
                        }
                        return;
                    default:
                        LogProxy.e("unexpected sensor type:" + type);
                        return;
                }
            } else if (this.q) {
                b(fArr, this.h);
                a(this.h[0], this.h[1], this.h[2]);
            }
        } else if (this.q && this.p == l) {
            b(fArr, this.h);
            a(this.h[0], this.h[1], this.h[2]);
        }
    }

    private static double[] a(float[] fArr, double[] dArr) {
        float[] fArr2 = fArr;
        if (fArr2.length != 9) {
            return dArr;
        }
        if (fArr2[8] > 0.0f) {
            dArr[0] = Math.atan2((double) (-fArr2[1]), (double) fArr2[4]);
            dArr[1] = Math.asin((double) fArr2[7]);
            dArr[2] = Math.atan2((double) (-fArr2[6]), (double) fArr2[8]);
        } else {
            double d2 = -3.141592653589793d;
            if (fArr2[8] < 0.0f) {
                dArr[0] = Math.atan2((double) fArr2[1], (double) (-fArr2[4]));
                dArr[1] = -Math.asin((double) fArr2[7]);
                double d3 = dArr[1];
                if (dArr[1] < 0.0d) {
                    d2 = 3.141592653589793d;
                }
                dArr[1] = d3 + d2;
                dArr[2] = Math.atan2((double) fArr2[6], (double) (-fArr2[8]));
            } else {
                double d4 = -1.5707963267948966d;
                if (fArr2[6] > 0.0f) {
                    dArr[0] = Math.atan2((double) (-fArr2[1]), (double) fArr2[4]);
                    dArr[1] = Math.asin((double) fArr2[7]);
                    dArr[2] = -1.5707963267948966d;
                } else if (fArr2[6] < 0.0f) {
                    dArr[0] = Math.atan2((double) fArr2[1], (double) (-fArr2[4]));
                    dArr[1] = -Math.asin((double) fArr2[7]);
                    double d5 = dArr[1];
                    if (dArr[1] < 0.0d) {
                        d2 = 3.141592653589793d;
                    }
                    dArr[1] = d5 + d2;
                    dArr[2] = -1.5707963267948966d;
                } else {
                    dArr[0] = Math.atan2((double) fArr2[3], (double) fArr2[0]);
                    if (fArr2[7] > 0.0f) {
                        d4 = 1.5707963267948966d;
                    }
                    dArr[1] = d4;
                    dArr[2] = 0.0d;
                }
            }
        }
        if (dArr[0] < 0.0d) {
            dArr[0] = dArr[0] + 6.283185307179586d;
        }
        return dArr;
    }

    private void b(float[] fArr, double[] dArr) {
        if (fArr.length > 4) {
            System.arraycopy(fArr, 0, this.f, 0, 4);
            SensorManager.getRotationMatrixFromVector(this.g, this.f);
        } else {
            SensorManager.getRotationMatrixFromVector(this.g, fArr);
        }
        a(this.g, dArr);
        for (int i2 = 0; i2 < 3; i2++) {
            dArr[i2] = Math.toDegrees(dArr[i2]);
        }
    }

    private void a(float[] fArr, float[] fArr2) {
        if (fArr != null && fArr2 != null && SensorManager.getRotationMatrix(this.g, (float[]) null, fArr, fArr2)) {
            a(this.g, this.h);
            a(Math.toDegrees(this.h[0]), Math.toDegrees(this.h[1]), Math.toDegrees(this.h[2]));
        }
    }

    private SensorManagerProxy c() {
        if (this.f759a != null) {
            return this.f759a;
        }
        SensorManager sensorManager = (SensorManager) this.d.getSystemService("sensor");
        if (sensorManager != null) {
            this.f759a = new SensorManagerProxyImpl(sensorManager);
        }
        return this.f759a;
    }

    private void a(boolean z) {
        this.q = z;
        this.r = z && this.p == m;
    }

    private void d() {
        if (this.g == null) {
            this.g = new float[9];
        }
        if (this.h == null) {
            this.h = new double[3];
        }
        if (this.f == null) {
            this.f = new float[4];
        }
    }

    private boolean a(Set<Integer> set, int i2, boolean z) {
        HashSet<Integer> hashSet = new HashSet<>(set);
        hashSet.removeAll(this.n);
        if (hashSet.isEmpty()) {
            return true;
        }
        boolean z2 = false;
        for (Integer num : hashSet) {
            boolean a2 = a(num.intValue(), i2);
            if (!a2 && z) {
                a((Iterable<Integer>) hashSet);
                return false;
            } else if (a2) {
                this.n.add(num);
                z2 = true;
            }
        }
        return z2;
    }

    private void a(Iterable<Integer> iterable) {
        for (Integer next : iterable) {
            if (this.n.contains(next)) {
                c().a(this, next.intValue());
                this.n.remove(next);
            }
        }
    }

    private boolean a(int i2, int i3) {
        SensorManagerProxy c2 = c();
        if (c2 == null) {
            return false;
        }
        return c2.a(this, i2, i3, e());
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(double d2, double d3, double d4) {
        if (this.t != null) {
            try {
                Iterator<OnOrientationChangedListener> it = this.t.iterator();
                while (it.hasNext()) {
                    it.next().a(d2, d3, d4);
                }
            } catch (Throwable th) {
                LogProxy.e("[OrientationDetector] ", th);
            }
        }
    }

    private Handler e() {
        if (this.c == null) {
            this.b = new HandlerThread("DeviceOrientation");
            this.b.start();
            this.c = new Handler(this.b.getLooper());
        }
        return this.c;
    }
}
