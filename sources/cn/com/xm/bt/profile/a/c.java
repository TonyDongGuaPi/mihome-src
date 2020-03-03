package cn.com.xm.bt.profile.a;

import com.taobao.weex.el.parse.Operators;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private int f577a = 0;
    private int b = 0;
    private byte[] c = null;
    private int d = 0;

    public c(int i, int i2) {
        this.f577a = i;
        this.b = i2;
    }

    public c(int i, int i2, int i3) {
        this.f577a = i;
        this.b = i2;
        this.d = i3;
    }

    public c(int i) {
        this.f577a = i;
    }

    public c() {
    }

    public int a() {
        return this.f577a;
    }

    public int b() {
        return this.b;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public int c() {
        if (this.c == null || this.c.length < 2) {
            return 0;
        }
        return (this.c[0] & 255) | ((this.c[1] & 15) << 8);
    }

    public String toString() {
        return "HMAuthState{mState=" + this.f577a + ", mError=" + this.b + ", mRandomValue=" + cn.com.xm.bt.c.c.a(this.c) + ", randomSimple=" + c() + ", internalState=" + this.d + Operators.BLOCK_END;
    }
}
