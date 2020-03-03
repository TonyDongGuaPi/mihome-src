package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import javax.annotation.Nullable;

class GlyphContext {
    private double[] A = {0.0d};
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G = -1;
    private int H = -1;
    private int I = -1;
    private int J = -1;
    private int K = -1;
    private int L;
    private final float M;
    private final float N;
    private final float O;

    /* renamed from: a  reason: collision with root package name */
    final ArrayList<FontData> f5809a = new ArrayList<>();
    private final ArrayList<SVGLength[]> b = new ArrayList<>();
    private final ArrayList<SVGLength[]> c = new ArrayList<>();
    private final ArrayList<SVGLength[]> d = new ArrayList<>();
    private final ArrayList<SVGLength[]> e = new ArrayList<>();
    private final ArrayList<double[]> f = new ArrayList<>();
    private final ArrayList<Integer> g = new ArrayList<>();
    private final ArrayList<Integer> h = new ArrayList<>();
    private final ArrayList<Integer> i = new ArrayList<>();
    private final ArrayList<Integer> j = new ArrayList<>();
    private final ArrayList<Integer> k = new ArrayList<>();
    private final ArrayList<Integer> l = new ArrayList<>();
    private final ArrayList<Integer> m = new ArrayList<>();
    private final ArrayList<Integer> n = new ArrayList<>();
    private final ArrayList<Integer> o = new ArrayList<>();
    private final ArrayList<Integer> p = new ArrayList<>();
    private double q = 12.0d;
    private FontData r = FontData.n;
    private double s;
    private double t;
    private double u;
    private double v;
    private SVGLength[] w = new SVGLength[0];
    private SVGLength[] x = new SVGLength[0];
    private SVGLength[] y = new SVGLength[0];
    private SVGLength[] z = new SVGLength[0];

    private void j() {
        this.l.add(Integer.valueOf(this.B));
        this.m.add(Integer.valueOf(this.C));
        this.n.add(Integer.valueOf(this.D));
        this.o.add(Integer.valueOf(this.E));
        this.p.add(Integer.valueOf(this.F));
    }

    GlyphContext(float f2, float f3, float f4) {
        this.M = f2;
        this.N = f3;
        this.O = f4;
        this.b.add(this.w);
        this.c.add(this.x);
        this.d.add(this.y);
        this.e.add(this.z);
        this.f.add(this.A);
        this.g.add(Integer.valueOf(this.G));
        this.h.add(Integer.valueOf(this.H));
        this.i.add(Integer.valueOf(this.I));
        this.j.add(Integer.valueOf(this.J));
        this.k.add(Integer.valueOf(this.K));
        this.f5809a.add(this.r);
        j();
    }

    private void k() {
        this.F = 0;
        this.E = 0;
        this.D = 0;
        this.C = 0;
        this.B = 0;
        this.K = -1;
        this.J = -1;
        this.I = -1;
        this.H = -1;
        this.G = -1;
        this.v = 0.0d;
        this.u = 0.0d;
        this.t = 0.0d;
        this.s = 0.0d;
    }

    /* access modifiers changed from: package-private */
    public FontData a() {
        return this.r;
    }

    private FontData a(GroupView groupView) {
        if (this.L > 0) {
            return this.r;
        }
        for (GroupView parentTextRoot = groupView.getParentTextRoot(); parentTextRoot != null; parentTextRoot = parentTextRoot.getParentTextRoot()) {
            FontData a2 = parentTextRoot.a().a();
            if (a2 != FontData.n) {
                return a2;
            }
        }
        return FontData.n;
    }

    private void b(GroupView groupView, @Nullable ReadableMap readableMap) {
        FontData a2 = a(groupView);
        this.L++;
        if (readableMap == null) {
            this.f5809a.add(a2);
            return;
        }
        FontData fontData = new FontData(readableMap, a2, (double) this.M);
        this.q = fontData.b;
        this.f5809a.add(fontData);
        this.r = fontData;
    }

    /* access modifiers changed from: package-private */
    public void a(GroupView groupView, @Nullable ReadableMap readableMap) {
        b(groupView, readableMap);
        j();
    }

    private SVGLength[] a(ArrayList<SVGLength> arrayList) {
        int size = arrayList.size();
        SVGLength[] sVGLengthArr = new SVGLength[size];
        for (int i2 = 0; i2 < size; i2++) {
            sVGLengthArr[i2] = arrayList.get(i2);
        }
        return sVGLengthArr;
    }

    private double[] b(ArrayList<SVGLength> arrayList) {
        int size = arrayList.size();
        double[] dArr = new double[size];
        for (int i2 = 0; i2 < size; i2++) {
            dArr[i2] = arrayList.get(i2).f5825a;
        }
        return dArr;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2, TextView textView, @Nullable ReadableMap readableMap, @Nullable ArrayList<SVGLength> arrayList, @Nullable ArrayList<SVGLength> arrayList2, @Nullable ArrayList<SVGLength> arrayList3, @Nullable ArrayList<SVGLength> arrayList4, @Nullable ArrayList<SVGLength> arrayList5) {
        if (z2) {
            k();
        }
        b(textView, readableMap);
        if (!(arrayList == null || arrayList.size() == 0)) {
            this.B++;
            this.G = -1;
            this.g.add(Integer.valueOf(this.G));
            this.w = a(arrayList);
            this.b.add(this.w);
        }
        if (!(arrayList2 == null || arrayList2.size() == 0)) {
            this.C++;
            this.H = -1;
            this.h.add(Integer.valueOf(this.H));
            this.x = a(arrayList2);
            this.c.add(this.x);
        }
        if (!(arrayList3 == null || arrayList3.size() == 0)) {
            this.D++;
            this.I = -1;
            this.i.add(Integer.valueOf(this.I));
            this.y = a(arrayList3);
            this.d.add(this.y);
        }
        if (!(arrayList4 == null || arrayList4.size() == 0)) {
            this.E++;
            this.J = -1;
            this.j.add(Integer.valueOf(this.J));
            this.z = a(arrayList4);
            this.e.add(this.z);
        }
        if (!(arrayList5 == null || arrayList5.size() == 0)) {
            this.F++;
            this.K = -1;
            this.k.add(Integer.valueOf(this.K));
            this.A = b(arrayList5);
            this.f.add(this.A);
        }
        j();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.f5809a.remove(this.L);
        this.l.remove(this.L);
        this.m.remove(this.L);
        this.n.remove(this.L);
        this.o.remove(this.L);
        this.p.remove(this.L);
        this.L--;
        int i2 = this.B;
        int i3 = this.C;
        int i4 = this.D;
        int i5 = this.E;
        int i6 = this.F;
        this.r = this.f5809a.get(this.L);
        this.B = this.l.get(this.L).intValue();
        this.C = this.m.get(this.L).intValue();
        this.D = this.n.get(this.L).intValue();
        this.E = this.o.get(this.L).intValue();
        this.F = this.p.get(this.L).intValue();
        if (i2 != this.B) {
            this.b.remove(i2);
            this.w = this.b.get(this.B);
            this.G = this.g.get(this.B).intValue();
        }
        if (i3 != this.C) {
            this.c.remove(i3);
            this.x = this.c.get(this.C);
            this.H = this.h.get(this.C).intValue();
        }
        if (i4 != this.D) {
            this.d.remove(i4);
            this.y = this.d.get(this.D);
            this.I = this.i.get(this.D).intValue();
        }
        if (i5 != this.E) {
            this.e.remove(i5);
            this.z = this.e.get(this.E);
            this.J = this.j.get(this.E).intValue();
        }
        if (i6 != this.F) {
            this.f.remove(i6);
            this.A = this.f.get(this.F);
            this.K = this.k.get(this.F).intValue();
        }
    }

    private static void a(ArrayList<Integer> arrayList, int i2) {
        while (i2 >= 0) {
            arrayList.set(i2, Integer.valueOf(arrayList.get(i2).intValue() + 1));
            i2--;
        }
    }

    /* access modifiers changed from: package-private */
    public double c() {
        return this.q;
    }

    /* access modifiers changed from: package-private */
    public double a(double d2) {
        a(this.g, this.B);
        int i2 = this.G + 1;
        if (i2 < this.w.length) {
            this.u = 0.0d;
            this.G = i2;
            this.s = PropHelper.a(this.w[i2], (double) this.N, 0.0d, (double) this.M, this.q);
        }
        this.s += d2;
        return this.s;
    }

    /* access modifiers changed from: package-private */
    public double d() {
        a(this.h, this.C);
        int i2 = this.H + 1;
        if (i2 < this.x.length) {
            this.v = 0.0d;
            this.H = i2;
            this.t = PropHelper.a(this.x[i2], (double) this.O, 0.0d, (double) this.M, this.q);
        }
        return this.t;
    }

    /* access modifiers changed from: package-private */
    public double e() {
        a(this.i, this.D);
        int i2 = this.I + 1;
        if (i2 < this.y.length) {
            this.I = i2;
            this.u += PropHelper.a(this.y[i2], (double) this.N, 0.0d, (double) this.M, this.q);
        }
        return this.u;
    }

    /* access modifiers changed from: package-private */
    public double f() {
        a(this.j, this.E);
        int i2 = this.J + 1;
        if (i2 < this.z.length) {
            this.J = i2;
            this.v += PropHelper.a(this.z[i2], (double) this.O, 0.0d, (double) this.M, this.q);
        }
        return this.v;
    }

    /* access modifiers changed from: package-private */
    public double g() {
        a(this.k, this.F);
        this.K = Math.min(this.K + 1, this.A.length - 1);
        return this.A[this.K];
    }

    /* access modifiers changed from: package-private */
    public float h() {
        return this.N;
    }

    /* access modifiers changed from: package-private */
    public float i() {
        return this.O;
    }
}
