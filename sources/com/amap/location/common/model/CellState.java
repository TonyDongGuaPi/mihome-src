package com.amap.location.common.model;

import android.text.TextUtils;
import java.util.Locale;
import kotlin.jvm.internal.ShortCompanionObject;

public class CellState {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4584a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = 0;
    public int k = 0;
    public int l = 0;
    public int m = 0;
    public int n = 99;
    public int o;
    public int p;
    @Deprecated
    public short q = 0;
    @Deprecated
    public long r = 0;
    public long s = 0;
    public boolean t;
    public boolean u = true;
    public short v = ShortCompanionObject.b;

    public CellState(int i2, boolean z) {
        this.f = i2;
        this.t = z;
    }

    public CellState(int i2, boolean z, boolean z2) {
        this.f = i2;
        this.t = z;
        this.u = z2;
    }

    private boolean a(int i2) {
        return i2 >= 0 && i2 <= 65535;
    }

    private boolean b(int i2) {
        return i2 >= 0 && i2 <= 268435455;
    }

    private boolean c(int i2) {
        return i2 > 0 && i2 <= 32767;
    }

    private boolean d(int i2) {
        return i2 >= 0 && i2 <= 65535;
    }

    private boolean e(int i2) {
        return i2 >= 0 && i2 <= 65535;
    }

    public String a() {
        String b2 = b();
        if (TextUtils.isEmpty(b2)) {
            return "";
        }
        boolean z = this.u;
        return (z ? 1 : 0) + "#" + b2;
    }

    public String b() {
        int i2;
        StringBuilder sb;
        switch (this.f) {
            case 1:
            case 3:
            case 4:
                sb = new StringBuilder();
                sb.append(this.f);
                sb.append("#");
                sb.append(this.g);
                sb.append("#");
                sb.append(this.h);
                sb.append("#");
                sb.append(this.i);
                sb.append("#");
                i2 = this.j;
                break;
            case 2:
                sb = new StringBuilder();
                sb.append(this.f);
                sb.append("#");
                sb.append(this.k);
                sb.append("#");
                sb.append(this.l);
                sb.append("#");
                i2 = this.m;
                break;
            default:
                return "";
        }
        sb.append(i2);
        return sb.toString();
    }

    public boolean c() {
        switch (this.f) {
            case 1:
            case 3:
            case 4:
                if (!a(this.i) || !b(this.j)) {
                    return false;
                }
                break;
            case 2:
                if (!c(this.k) || !d(this.l) || !e(this.m)) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    /* renamed from: d */
    public CellState clone() {
        CellState cellState = new CellState(this.f, this.t, this.u);
        cellState.g = this.g;
        cellState.h = this.h;
        cellState.i = this.i;
        cellState.j = this.j;
        cellState.k = this.k;
        cellState.l = this.l;
        cellState.m = this.m;
        cellState.n = this.n;
        cellState.o = this.o;
        cellState.p = this.p;
        cellState.q = this.q;
        cellState.r = this.r;
        cellState.s = this.s;
        cellState.v = this.v;
        return cellState;
    }

    public String toString() {
        Object[] objArr;
        String str;
        Locale locale;
        switch (this.f) {
            case 1:
                locale = Locale.CHINA;
                str = "[type=GSM, mcc=%d, mnc=%d, lac=%d, cid=%d, sig=%d, age=%d, reg=%b, new=%b]";
                objArr = new Object[]{Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.i), Integer.valueOf(this.j), Integer.valueOf(this.n), Short.valueOf(this.q), Boolean.valueOf(this.t), Boolean.valueOf(this.u)};
                break;
            case 2:
                locale = Locale.CHINA;
                str = "[type=CDMA, mcc=%d, mnc=%d, sid=%d, nid=%d, bid=%d, sig=%d, age=%d, reg=%b, new=%b]";
                objArr = new Object[]{Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.k), Integer.valueOf(this.l), Integer.valueOf(this.m), Integer.valueOf(this.n), Short.valueOf(this.q), Boolean.valueOf(this.t), Boolean.valueOf(this.u)};
                break;
            case 3:
                locale = Locale.CHINA;
                str = "[type=LTE, mcc=%d, mnc=%d, lac=%d, cid=%d, sig=%d, age=%d, reg=%b, new=%b, pci=%d]";
                objArr = new Object[]{Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.i), Integer.valueOf(this.j), Integer.valueOf(this.n), Short.valueOf(this.q), Boolean.valueOf(this.t), Boolean.valueOf(this.u), Short.valueOf(this.v)};
                break;
            case 4:
                locale = Locale.CHINA;
                str = "[type=WCDMA, mcc=%d, mnc=%d, lac=%d, cid=%d, sig=%d, age=%d, reg=%b, new=%b, psc=%d]";
                objArr = new Object[]{Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.i), Integer.valueOf(this.j), Integer.valueOf(this.n), Short.valueOf(this.q), Boolean.valueOf(this.t), Boolean.valueOf(this.u), Short.valueOf(this.v)};
                break;
            default:
                return "unknown";
        }
        return String.format(locale, str, objArr);
    }
}
