package com.xiaomi.zxing.oned.rss;

import com.taobao.weex.el.parse.Operators;

public class DataCharacter {

    /* renamed from: a  reason: collision with root package name */
    private final int f1712a;
    private final int b;

    public DataCharacter(int i, int i2) {
        this.f1712a = i;
        this.b = i2;
    }

    public final int a() {
        return this.f1712a;
    }

    public final int b() {
        return this.b;
    }

    public final String toString() {
        return this.f1712a + Operators.BRACKET_START_STR + this.b + Operators.BRACKET_END;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof DataCharacter)) {
            return false;
        }
        DataCharacter dataCharacter = (DataCharacter) obj;
        if (this.f1712a == dataCharacter.f1712a && this.b == dataCharacter.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f1712a ^ this.b;
    }
}
