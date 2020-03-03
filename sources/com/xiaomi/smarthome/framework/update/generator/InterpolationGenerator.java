package com.xiaomi.smarthome.framework.update.generator;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.xiaomi.smarthome.frame.HostSetting;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class InterpolationGenerator extends Generator {
    private static boolean b = (HostSetting.g || HostSetting.i);
    private static final String c = "InterpolationGenerator";
    private static final int d = 1000;
    private static final int g = 80;
    /* access modifiers changed from: private */
    public long e = 150;
    /* access modifiers changed from: private */
    public long f = 225;
    /* access modifiers changed from: private */
    public int h = 7;
    /* access modifiers changed from: private */
    public long i = this.e;
    /* access modifiers changed from: private */
    public long j = this.f;
    /* access modifiers changed from: private */
    public final Queue<Integer> k = new PriorityQueue();
    /* access modifiers changed from: private */
    public boolean l = false;
    private String m;
    private boolean n;
    private boolean o;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler p = new Handler() {
        private Random b = new Random();

        public void handleMessage(Message message) {
            if (message.what == 1000 && InterpolationGenerator.this.k.size() > 0) {
                Integer num = (Integer) InterpolationGenerator.this.k.poll();
                Integer num2 = (Integer) InterpolationGenerator.this.k.peek();
                if (num.intValue() >= 100) {
                    InterpolationGenerator.this.c(100);
                    InterpolationGenerator.this.k.clear();
                    return;
                }
                InterpolationGenerator.this.c(num.intValue());
                Integer valueOf = Integer.valueOf(num.intValue() + 1);
                if (num2 != null) {
                    long unused = InterpolationGenerator.this.i = InterpolationGenerator.this.e;
                    long unused2 = InterpolationGenerator.this.j = InterpolationGenerator.this.f;
                    if (valueOf.intValue() != num2.intValue()) {
                        InterpolationGenerator.this.k.offer(valueOf);
                    }
                } else if (valueOf.intValue() > 92) {
                    boolean unused3 = InterpolationGenerator.this.l = false;
                    InterpolationGenerator.this.k.offer(valueOf);
                    InterpolationGenerator.this.p.removeMessages(1000);
                    return;
                } else {
                    long unused4 = InterpolationGenerator.this.i = InterpolationGenerator.this.j = InterpolationGenerator.this.j + 80;
                    if ((InterpolationGenerator.this.i - InterpolationGenerator.this.f) / 80 > ((long) (this.b.nextInt(InterpolationGenerator.this.h) + 1))) {
                        boolean unused5 = InterpolationGenerator.this.l = false;
                        InterpolationGenerator.this.p.removeMessages(1000);
                        long unused6 = InterpolationGenerator.this.i = InterpolationGenerator.this.e;
                        InterpolationGenerator.this.k.offer(valueOf);
                        return;
                    }
                    InterpolationGenerator.this.k.offer(valueOf);
                }
                if (InterpolationGenerator.this.k.size() > 0) {
                    InterpolationGenerator.this.p.sendEmptyMessageDelayed(1000, InterpolationGenerator.this.i);
                    return;
                }
                boolean unused7 = InterpolationGenerator.this.l = false;
                InterpolationGenerator.this.p.removeMessages(1000);
            }
        }
    };
    private long q = 0;
    private int r = 0;
    private long s = 0;
    private int t = 0;

    public InterpolationGenerator(String str, boolean z) {
        this.m = str;
        this.n = z;
    }

    public void a(int i2) {
        if (this.k.size() <= 0 || i2 > this.k.peek().intValue()) {
            d(i2);
            if (!this.o) {
                this.o = true;
                if (this.k.size() == 0) {
                    if (this.n) {
                        this.k.add(Integer.valueOf(Math.max(0, i2 - 1)));
                    } else {
                        this.k.add(0);
                    }
                }
            }
            this.k.add(Integer.valueOf(i2));
            if (!this.l) {
                this.l = true;
                this.p.sendEmptyMessageDelayed(1000, this.i);
            }
        }
    }

    private void d(int i2) {
        if (i2 == 100) {
            this.j = 120;
            this.i = 120;
        } else if (i2 == 0) {
        } else {
            if (this.q == 0) {
                this.q = System.currentTimeMillis();
                this.r = i2;
            } else if (this.s == 0 && i2 != this.r) {
                this.s = System.currentTimeMillis();
                this.t = i2;
                long j2 = (this.s - this.q) / ((long) (this.t - this.r));
                if (b) {
                    Log.e(c, this.m + "; interval adjust to : " + j2);
                }
                this.e = j2;
                this.i = j2;
                double d2 = (double) j2;
                Double.isNaN(d2);
                long j3 = (long) (d2 * 1.5d);
                this.f = j3;
                this.j = j3;
                if (this.t - this.r <= 3) {
                    this.h = 2;
                    if (b) {
                        Log.e(c, this.m + "; over_extend adjust to : " + this.h);
                    }
                } else if (this.t - this.r >= 15) {
                    this.h = 12;
                    if (b) {
                        Log.e(c, this.m + "; over_extend adjust to : " + this.h);
                    }
                } else {
                    this.h = 7;
                    if (b) {
                        Log.e(c, this.m + "; over_extend is default : " + this.h);
                    }
                }
                this.q = 0;
                this.s = 0;
            }
        }
    }

    public void b(int i2) {
        this.k.clear();
        this.k.offer(Integer.valueOf(i2));
        this.p.sendEmptyMessage(1000);
    }

    /* access modifiers changed from: protected */
    public void c(int i2) {
        super.c(i2);
    }

    public void a() {
        this.p.removeCallbacksAndMessages((Object) null);
        this.p = null;
    }
}
