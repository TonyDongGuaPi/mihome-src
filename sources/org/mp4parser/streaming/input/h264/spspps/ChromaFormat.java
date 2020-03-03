package org.mp4parser.streaming.input.h264.spspps;

import com.taobao.weex.el.parse.Operators;

public class ChromaFormat {

    /* renamed from: a  reason: collision with root package name */
    public static ChromaFormat f4078a = new ChromaFormat(0, 0, 0);
    public static ChromaFormat b = new ChromaFormat(1, 2, 2);
    public static ChromaFormat c = new ChromaFormat(2, 2, 1);
    public static ChromaFormat d = new ChromaFormat(3, 1, 1);
    private int e;
    private int f;
    private int g;

    public ChromaFormat(int i, int i2, int i3) {
        this.e = i;
        this.f = i2;
        this.g = i3;
    }

    public static ChromaFormat a(int i) {
        if (i == f4078a.e) {
            return f4078a;
        }
        if (i == b.e) {
            return b;
        }
        if (i == c.e) {
            return c;
        }
        if (i == d.e) {
            return d;
        }
        return null;
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public int c() {
        return this.g;
    }

    public String toString() {
        return "ChromaFormat{\nid=" + this.e + ",\n" + " subWidth=" + this.f + ",\n" + " subHeight=" + this.g + Operators.BLOCK_END;
    }
}
