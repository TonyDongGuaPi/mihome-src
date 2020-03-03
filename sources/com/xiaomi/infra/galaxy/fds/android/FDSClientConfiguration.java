package com.xiaomi.infra.galaxy.fds.android;

import com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential;
import com.xiaomi.infra.galaxy.fds.android.util.Args;

public class FDSClientConfiguration {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10130a = 50000;
    public static final int b = 3;
    public static final int c = 4096;
    public static final int d = 50000;
    public static final int e = 4;
    public static final int f = 10;
    public static final int g = 30;
    public static final int h = 10240;
    private static final String i = "http://";
    private static final String j = "https://";
    private static final String k = "cdn";
    private static final String l = "fds.api.xiaomi.com";
    private static final String m = "fds.api.mi-img.com";
    private boolean A = false;
    private boolean B = true;
    private boolean C = false;
    private String D = "";
    private String E;
    private int n = 50000;
    private int o = 50000;
    private int p = 0;
    private int q = 0;
    private int r = 3;
    private int s = 4096;
    private int t = 4;
    private int u = 10;
    private int v = 30;
    private int w = 10240;
    private GalaxyFDSCredential x;
    private String y = "cnbj0";
    private boolean z = true;

    @Deprecated
    public void b(String str) {
    }

    @Deprecated
    public FDSClientConfiguration c(String str) {
        return this;
    }

    @Deprecated
    public void d(String str) {
    }

    @Deprecated
    public FDSClientConfiguration e(String str) {
        return this;
    }

    public int a() {
        return this.r;
    }

    public void a(int i2) {
        Args.b(i2, "max retry times");
        this.r = i2;
    }

    public FDSClientConfiguration b(int i2) {
        a(i2);
        return this;
    }

    public int b() {
        return this.n;
    }

    public void c(int i2) {
        this.n = i2;
    }

    public FDSClientConfiguration d(int i2) {
        c(i2);
        return this;
    }

    public int c() {
        return this.o;
    }

    public void e(int i2) {
        this.o = i2;
    }

    public FDSClientConfiguration f(int i2) {
        e(i2);
        return this;
    }

    public int[] d() {
        return new int[]{this.p, this.q};
    }

    public void a(int i2, int i3) {
        this.p = i2;
        this.q = i3;
    }

    public FDSClientConfiguration b(int i2, int i3) {
        a(i2, i3);
        return this;
    }

    public int e() {
        return this.s;
    }

    public void g(int i2) {
        Args.a(i2, "upload part size");
        this.s = i2;
    }

    public FDSClientConfiguration h(int i2) {
        g(i2);
        return this;
    }

    public int f() {
        return this.t;
    }

    public void i(int i2) {
        this.t = i2;
    }

    public FDSClientConfiguration j(int i2) {
        i(i2);
        return this;
    }

    public int g() {
        return this.u;
    }

    public void k(int i2) {
        this.u = i2;
    }

    public FDSClientConfiguration l(int i2) {
        k(i2);
        return this;
    }

    public int h() {
        return this.v;
    }

    public void m(int i2) {
        this.v = i2;
    }

    public FDSClientConfiguration n(int i2) {
        m(i2);
        return this;
    }

    public int i() {
        return this.w;
    }

    public void o(int i2) {
        this.w = i2;
    }

    public FDSClientConfiguration p(int i2) {
        o(i2);
        return this;
    }

    public GalaxyFDSCredential j() {
        return this.x;
    }

    public void a(GalaxyFDSCredential galaxyFDSCredential) {
        Args.a(galaxyFDSCredential, "credential");
        this.x = galaxyFDSCredential;
    }

    public FDSClientConfiguration b(GalaxyFDSCredential galaxyFDSCredential) {
        a(galaxyFDSCredential);
        return this;
    }

    public String k() {
        return this.E;
    }

    public void a(String str) {
        this.E = str;
    }

    @Deprecated
    public String l() {
        return t();
    }

    @Deprecated
    public String m() {
        return u();
    }

    public String n() {
        return this.y;
    }

    public void f(String str) {
        this.y = str;
    }

    public FDSClientConfiguration g(String str) {
        f(str);
        return this;
    }

    public boolean o() {
        return this.z;
    }

    public void a(boolean z2) {
        this.z = z2;
    }

    public FDSClientConfiguration b(boolean z2) {
        a(z2);
        return this;
    }

    public boolean p() {
        return this.A;
    }

    public void c(boolean z2) {
        this.A = z2;
    }

    public FDSClientConfiguration d(boolean z2) {
        c(z2);
        return this;
    }

    public boolean q() {
        return this.B;
    }

    public void e(boolean z2) {
        this.B = z2;
    }

    public FDSClientConfiguration f(boolean z2) {
        e(z2);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean r() {
        return this.C;
    }

    /* access modifiers changed from: package-private */
    public void g(boolean z2) {
        this.C = z2;
    }

    /* access modifiers changed from: package-private */
    public FDSClientConfiguration h(boolean z2) {
        g(z2);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String s() {
        return this.D;
    }

    /* access modifiers changed from: package-private */
    public void h(String str) {
        this.D = str;
    }

    /* access modifiers changed from: package-private */
    public FDSClientConfiguration i(String str) {
        h(str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String t() {
        return i(false);
    }

    /* access modifiers changed from: package-private */
    public String u() {
        return i(true);
    }

    /* access modifiers changed from: package-private */
    public String v() {
        return i(this.B);
    }

    /* access modifiers changed from: package-private */
    public String w() {
        return i(this.A);
    }

    /* access modifiers changed from: package-private */
    public String i(boolean z2) {
        if (this.C) {
            return this.D;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.z ? "https://" : "http://");
        if (this.E != null && !this.E.isEmpty()) {
            sb.append(this.E);
        } else if (z2) {
            sb.append("cdn." + this.y + "." + m);
        } else {
            sb.append(this.y + "." + l);
        }
        return sb.toString();
    }
}
