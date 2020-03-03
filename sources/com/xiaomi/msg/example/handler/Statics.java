package com.xiaomi.msg.example.handler;

import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.thread.XMDSendThread;

public class Statics {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12108a = "Statics";
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private XMDTransceiver x;

    public void a(XMDTransceiver xMDTransceiver) {
        this.x = xMDTransceiver;
    }

    public int a() {
        return this.w;
    }

    public void a(int i2) {
        this.w++;
        if (i2 > 5000) {
            this.v++;
        } else if (i2 > 4000) {
            this.u++;
        } else if (i2 > 3000) {
            this.t++;
        } else if (i2 > 2000) {
            this.s++;
        } else if (i2 > 1000) {
            this.r++;
        } else if (i2 > 900) {
            this.q++;
        } else if (i2 > 800) {
            this.p++;
        } else if (i2 > 700) {
            this.o++;
        } else if (i2 > 600) {
            this.n++;
        } else if (i2 > 500) {
            this.m++;
        } else if (i2 > 400) {
            this.l++;
        } else if (i2 > 300) {
            this.k++;
        } else if (i2 > 200) {
            this.j++;
        } else if (i2 > 180) {
            this.i++;
        } else if (i2 > 150) {
            this.h++;
        } else if (i2 > 120) {
            this.g++;
        } else if (i2 > 100) {
            this.f++;
        } else if (i2 > 80) {
            this.e++;
        } else if (i2 > 50) {
            this.d++;
        } else if (i2 > 20) {
            this.c++;
        } else if (i2 > 0) {
            this.b++;
        }
    }

    public void b() {
        MIMCLog.b(f12108a, String.format("TIME_RANGE [0-20]=%d", new Object[]{Integer.valueOf(this.b)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [20-50]=%d", new Object[]{Integer.valueOf(this.c)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [50-80]=%d", new Object[]{Integer.valueOf(this.d)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [80-100]=%d", new Object[]{Integer.valueOf(this.e)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [100-120]=%d", new Object[]{Integer.valueOf(this.f)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [120-150]=%d", new Object[]{Integer.valueOf(this.g)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [150-180]=%d", new Object[]{Integer.valueOf(this.h)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [180-200]=%d", new Object[]{Integer.valueOf(this.i)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [200-300]=%d", new Object[]{Integer.valueOf(this.j)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [300-400]=%d", new Object[]{Integer.valueOf(this.k)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [400-500]=%d", new Object[]{Integer.valueOf(this.l)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [500-600]=%d", new Object[]{Integer.valueOf(this.m)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [600-700]=%d", new Object[]{Integer.valueOf(this.n)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [700-800]=%d", new Object[]{Integer.valueOf(this.o)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [800-900]=%d", new Object[]{Integer.valueOf(this.p)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [900-1000]=%d", new Object[]{Integer.valueOf(this.q)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [1000-2000]=%d", new Object[]{Integer.valueOf(this.r)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [2000-3000]=%d", new Object[]{Integer.valueOf(this.s)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [3000-4000]=%d", new Object[]{Integer.valueOf(this.t)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [4000-5000]=%d", new Object[]{Integer.valueOf(this.u)}));
        MIMCLog.b(f12108a, String.format("TIME_RANGE [5000+]=%d", new Object[]{Integer.valueOf(this.v)}));
        MIMCLog.b(f12108a, String.format("sum: %d", new Object[]{Integer.valueOf(this.w)}));
        MIMCLog.b(f12108a, String.format("SendCnt=%d", new Object[]{Long.valueOf(XMDSendThread.f12119a)}));
        MIMCLog.b(f12108a, String.format("commandQueueDatasSize = %d", new Object[]{Integer.valueOf(this.x.j())}));
        MIMCLog.b(f12108a, String.format("priorityQueueDatasSize = %d", new Object[]{Integer.valueOf(this.x.k())}));
        MIMCLog.b(f12108a, String.format("resendQueueDatasSize = %d", new Object[]{Integer.valueOf(this.x.l())}));
        MIMCLog.b(f12108a, String.format("waitAckSize = %d", new Object[]{Integer.valueOf(this.x.m())}));
        MIMCLog.b(f12108a, String.format("groupQueueDatasSize = %d", new Object[]{Integer.valueOf(this.x.n())}));
        MIMCLog.b(f12108a, String.format("curElementSizeInRecvBuffer = %d", new Object[]{Integer.valueOf(this.x.o())}));
        c();
    }

    public void c() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
    }
}
