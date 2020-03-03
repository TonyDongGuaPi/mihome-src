package cn.com.xm.bt.profile.a;

import com.taobao.weex.el.parse.Operators;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private String f575a = null;
    private boolean b = false;
    private boolean c = true;
    private byte d = 20;

    public String a() {
        return this.f575a;
    }

    public void a(String str) {
        this.f575a = str;
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public byte c() {
        return this.d;
    }

    public String toString() {
        return "AuthInfo{, mKey='" + this.f575a + Operators.SINGLE_QUOTE + ", mIsPair=" + this.b + ", mNeedAuth=" + this.c + Operators.BLOCK_END;
    }
}
