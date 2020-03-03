package com.xiaomi.shopviews.adapter.adapter;

import android.support.v4.util.SparseArrayCompat;
import com.drew.metadata.iptc.IptcDirectory;
import com.tencent.smtt.sdk.TbsListener;
import com.tiqiaa.constant.KeyType;
import com.xiaomi.base.utils.ScreenInfo;
import com.ximalaya.ting.android.xmpayordersdk.IXmPayOrderListener;

public class UIAdapter {
    public static final int A;
    public static final int B;
    public static final int C;
    private static UIAdapter E = null;
    private static int F = 1;

    /* renamed from: a  reason: collision with root package name */
    public static final int f13069a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    public static final int f;
    public static final int g;
    public static final int h;
    public static final int i;
    public static final int j;
    public static final int k;
    public static final int l;
    public static final int m;
    public static final int n;
    public static final int o;
    public static final int p;
    public static final int q;
    public static final int r;
    public static final int s;
    public static final int t;
    public static final int u;
    public static final int v;
    public static final int w;
    public static final int x;
    public static final int y;
    public static final int z;
    public SparseArrayCompat<Float> D;
    private float G;
    private float H;
    private float I;
    private float J;
    private float K = 3.0f;
    private int L = 480;
    private float M = 1080.0f;
    private float N = (this.M / this.K);

    static {
        int i2 = F;
        F = i2 + 1;
        B = i2;
        int i3 = F;
        F = i3 + 1;
        C = i3;
        int i4 = F;
        F = i4 + 1;
        z = i4;
        int i5 = F;
        F = i5 + 1;
        u = i5;
        int i6 = F;
        F = i6 + 1;
        w = i6;
        int i7 = F;
        F = i7 + 1;
        v = i7;
        int i8 = F;
        F = i8 + 1;
        y = i8;
        int i9 = F;
        F = i9 + 1;
        x = i9;
        int i10 = F;
        F = i10 + 1;
        t = i10;
        int i11 = F;
        F = i11 + 1;
        o = i11;
        int i12 = F;
        F = i12 + 1;
        q = i12;
        int i13 = F;
        F = i13 + 1;
        p = i13;
        int i14 = F;
        F = i14 + 1;
        s = i14;
        int i15 = F;
        F = i15 + 1;
        r = i15;
        int i16 = F;
        F = i16 + 1;
        h = i16;
        int i17 = F;
        F = i17 + 1;
        g = i17;
        int i18 = F;
        F = i18 + 1;
        d = i18;
        int i19 = F;
        F = i19 + 1;
        c = i19;
        int i20 = F;
        F = i20 + 1;
        f = i20;
        int i21 = F;
        F = i21 + 1;
        e = i21;
        int i22 = F;
        F = i22 + 1;
        A = i22;
        int i23 = F;
        F = i23 + 1;
        n = i23;
        int i24 = F;
        F = i24 + 1;
        k = i24;
        int i25 = F;
        F = i25 + 1;
        m = i25;
        int i26 = F;
        F = i26 + 1;
        l = i26;
        int i27 = F;
        F = i27 + 1;
        f13069a = i27;
        int i28 = F;
        F = i28 + 1;
        b = i28;
        int i29 = F;
        F = i29 + 1;
        j = i29;
        int i30 = F;
        F = i30 + 1;
        i = i30;
    }

    private UIAdapter() {
    }

    public static UIAdapter a() {
        if (E == null) {
            UIAdapter uIAdapter = new UIAdapter();
            E = uIAdapter;
            uIAdapter.b();
        }
        return E;
    }

    private float b(int i2) {
        return (((float) i2) / this.M) * this.I;
    }

    private void b() {
        this.H = ScreenInfo.a().b();
        this.G = (float) ScreenInfo.a().c();
        this.I = (float) ScreenInfo.a().e();
        this.J = this.I / this.H;
        c();
    }

    private void c() {
        if (this.D == null) {
            this.D = new SparseArrayCompat<>();
        }
        this.D.put(B, Float.valueOf(b(340)));
        this.D.put(C, Float.valueOf(b(TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_MINIQBSUCCESS)));
        this.D.put(z, Float.valueOf(b(340)));
        this.D.put(u, Float.valueOf(b(IXmPayOrderListener.y)));
        this.D.put(w, Float.valueOf(b(360)));
        this.D.put(v, Float.valueOf(b(TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR)));
        this.D.put(y, Float.valueOf(b(192)));
        this.D.put(x, Float.valueOf(b(192)));
        this.D.put(t, Float.valueOf(b(504)));
        this.D.put(o, Float.valueOf(b(IptcDirectory.T)));
        this.D.put(q, Float.valueOf(b(540)));
        this.D.put(p, Float.valueOf(b(IXmPayOrderListener.A)));
        this.D.put(s, Float.valueOf(b(TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR)));
        this.D.put(r, Float.valueOf(b(TbsListener.ErrorCode.ERROR_TBSCORE_SHARE_DIR)));
        this.D.put(h, Float.valueOf(b(1080)));
        this.D.put(g, Float.valueOf(b(600)));
        this.D.put(c, Float.valueOf(b(1589)));
        this.D.put(d, Float.valueOf(b(1080)));
        this.D.put(e, Float.valueOf(b(768)));
        this.D.put(A, Float.valueOf(b(540)));
        this.D.put(f, Float.valueOf(b(1080)));
        this.D.put(k, Float.valueOf(b(1040)));
        this.D.put(n, Float.valueOf(b(KeyType.TITLE)));
        this.D.put(l, Float.valueOf(b(570)));
        this.D.put(m, Float.valueOf(b(KeyType.TITLE)));
        this.D.put(f13069a, Float.valueOf(b(480)));
        this.D.put(b, Float.valueOf(b(370)));
        this.D.put(j, Float.valueOf(b(1008)));
        this.D.put(i, Float.valueOf(b(IptcDirectory.ai)));
    }

    public int a(int i2) {
        if (this.D != null) {
            return (int) (this.D.get(i2).floatValue() + 0.5f);
        }
        return 0;
    }
}
