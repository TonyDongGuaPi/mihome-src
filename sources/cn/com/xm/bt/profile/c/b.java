package cn.com.xm.bt.profile.c;

import com.taobao.weex.el.parse.Operators;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private int f584a = 1;
    private int b = 1;
    private int c = 0;

    public b(int i, int i2, int i3) {
        this.f584a = i;
        this.b = i2;
        this.c = i3;
    }

    public int a() {
        return this.c;
    }

    public boolean b() {
        return this.f584a == 1;
    }

    public boolean c() {
        return this.b == 1;
    }

    public String toString() {
        return "PieceResult{pieceState=" + this.f584a + ", profileState=" + this.b + ", timeout=" + this.c + Operators.BLOCK_END;
    }
}
