package cn.com.xm.bt.d;

import com.taobao.weex.el.parse.Operators;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private int f568a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private boolean g = false;

    public e() {
    }

    public e(int i, int i2) {
        this.f568a = i;
        this.f = i2;
    }

    public int a() {
        return this.f568a;
    }

    public void a(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public void c(int i) {
        this.b = i;
    }

    public void d(int i) {
        this.c = i;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public String toString() {
        return "[,totalStep:" + this.f568a + ",totalDis:" + this.b + ",totalCal:" + this.c + ",running:" + this.d + ",walking:" + this.e + ",front:" + this.f + Operators.ARRAY_END_STR;
    }
}
