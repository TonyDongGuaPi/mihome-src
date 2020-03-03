package com.megvii.idcardquality.bean;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.megvii.idcard.sdk.a;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public class IDCardAttr {
    public static Bitmap s;
    public static Bitmap t;

    /* renamed from: a  reason: collision with root package name */
    public float f6666a = 0.0f;
    public Point[] b;
    public Point[] c;
    public float[] d;
    public boolean e;
    public boolean f;
    public a.g[] g;
    public a.b[] h;
    public a.C0061a[] i;
    public IDCardType j;
    public float k;
    public float l;
    public int m;
    public int n;
    public IDCardSide o;
    public float p;
    public Bitmap q;
    public Bitmap r;

    public enum IDCardSide {
        IDCARD_SIDE_FRONT,
        IDCARD_SIDE_BACK
    }

    public enum IDCardType {
        NORMAL,
        MONGOL
    }

    public String toString() {
        return "IDCardAttr{lowQuality=" + this.f6666a + ", cornerPoints=" + Arrays.toString(this.b) + ", portraitPoints=" + Arrays.toString(this.c) + ", angles=" + Arrays.toString(this.d) + ", hasSpecularHighlight=" + this.e + ", side=" + this.o + ", brightness=" + this.p + ", inBound=" + this.k + ", isIdcard=" + this.l + ", shadowCount=" + this.m + ", specularHightlightCount=" + this.n + Operators.BLOCK_END;
    }
}
