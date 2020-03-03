package cn.com.xm.bt.profile.a;

import cn.com.xm.bt.c.c;
import com.taobao.weex.el.parse.Operators;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private int f576a;
    private int b;
    private byte[] c;

    public int a() {
        return this.f576a;
    }

    public void a(int i) {
        this.f576a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public byte[] c() {
        return this.c;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public String toString() {
        return Operators.BLOCK_START_STR + "version=" + this.f576a + ", algorithm=" + this.b + ", publicKeyHash=" + c.a(this.c) + Operators.BLOCK_END;
    }
}
