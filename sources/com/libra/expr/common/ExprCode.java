package com.libra.expr.common;

import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class ExprCode {
    private static final String d = "ExprCode_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    public byte[] f6240a;
    public int b;
    public int c;

    public ExprCode() {
        this.f6240a = null;
        this.b = 0;
        this.c = 0;
    }

    public ExprCode(byte[] bArr, int i, int i2) {
        this.f6240a = bArr;
        this.b = i;
        this.c = this.b + i2;
    }

    public ExprCode(List<Byte> list) {
        if (list != null) {
            int size = list.size();
            this.f6240a = new byte[size];
            for (int i = 0; i < size; i++) {
                this.f6240a[i] = list.get(i).byteValue();
            }
            this.b = 0;
            this.c = size;
        }
    }

    /* renamed from: a */
    public ExprCode clone() {
        if (this.f6240a == null) {
            return null;
        }
        int b2 = b();
        ExprCode exprCode = new ExprCode();
        exprCode.f6240a = new byte[b2];
        exprCode.b = 0;
        exprCode.c = b2;
        for (int i = 0; i < b2; i++) {
            exprCode.f6240a[i] = this.f6240a[i];
        }
        return exprCode;
    }

    public int b() {
        return this.c - this.b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("start pos:" + this.b + "  endPos:" + this.c + "  [");
        for (int i = this.b; i < this.c; i++) {
            sb.append(this.f6240a[i] + ",");
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }
}
