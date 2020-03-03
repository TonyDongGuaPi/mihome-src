package com.holtek.libHTBodyfat;

import java.util.Hashtable;

public class HTPeopleGeneral {

    /* renamed from: a  reason: collision with root package name */
    public double f5804a;
    public double b;
    public int c;
    public int d;
    public double e;
    public int f;
    public double g;
    public double h;
    public int i;
    public int j;
    public double k;
    public double l;
    public double m;
    public double n;
    public Hashtable<String, String> o = new Hashtable<>();
    public Hashtable<String, String> p = new Hashtable<>();
    public Hashtable<String, String> q = new Hashtable<>();
    public Hashtable<String, String> r = new Hashtable<>();
    public Hashtable<String, String> s = new Hashtable<>();
    public Hashtable<String, String> t = new Hashtable<>();
    public Hashtable<String, String> u = new Hashtable<>();
    private int v;

    public HTPeopleGeneral(double d2, double d3, int i2, int i3, int i4) {
        this.f5804a = d2;
        this.b = d3;
        this.c = i3;
        this.d = i2;
        this.v = i4;
    }

    public int a(double d2, double d3, int i2, int i3, int i4) {
        this.f5804a = d2;
        this.b = d3;
        this.c = i3;
        this.d = i2;
        this.e = (double) i4;
        return a();
    }

    public int a() {
        int NN = HTBodyfat.NN(this.f5804a, this.b, this.c, this.d, this.v);
        if (NN == 0) {
            this.l = HTBodyfat.CC();
            this.m = HTBodyfat.HH();
            this.k = HTBodyfat.DD();
            this.n = HTBodyfat.FF();
            this.j = (int) HTBodyfat.GG();
            this.i = (int) HTBodyfat.BB();
            this.h = HTBodyfat.EE();
            this.g = HTBodyfat.QQ();
            double[] II = HTBodyfat.II();
            this.o.put(HTDataType.k, String.valueOf(II[0]));
            this.o.put(HTDataType.l, String.valueOf(II[1]));
            this.o.put("偏胖－肥胖", String.valueOf(II[2]));
            double[] JJ = HTBodyfat.JJ();
            this.p = new Hashtable<>();
            this.p.put(HTDataType.f, String.valueOf(JJ[0]));
            double[] KK = HTBodyfat.KK();
            this.s.put(HTDataType.n, String.valueOf(KK[0]));
            this.s.put(HTDataType.o, String.valueOf(KK[1]));
            this.s.put(HTDataType.p, String.valueOf(KK[2]));
            this.s.put("偏胖－肥胖", String.valueOf(KK[3]));
            double[] LL = HTBodyfat.LL();
            this.r.put(HTDataType.g, String.valueOf(LL[0]));
            this.r.put(HTDataType.h, String.valueOf(LL[1]));
            double[] MM = HTBodyfat.MM();
            this.u.put(HTDataType.g, String.valueOf(MM[0]));
            this.u.put(HTDataType.h, String.valueOf(MM[1]));
            double[] OO = HTBodyfat.OO();
            this.q.put(HTDataType.i, String.valueOf(OO[0]));
            this.q.put(HTDataType.j, String.valueOf(OO[1]));
            double[] PP = HTBodyfat.PP();
            this.t.put(HTDataType.g, String.valueOf(PP[0]));
            this.t.put(HTDataType.h, String.valueOf(PP[1]));
        } else {
            if (NN == 4 || NN == 3) {
                this.h = 0.0d;
            } else {
                this.h = HTBodyfat.EE();
                this.g = HTBodyfat.QQ();
            }
            this.j = 0;
            this.l = 0.0d;
            this.m = 0.0d;
            this.n = 0.0d;
            this.k = 0.0d;
            this.i = 0;
        }
        this.e = HTBodyfat.AA();
        return NN;
    }
}
