package org.mp4parser.streaming.input.h264.spspps;

import com.taobao.weex.el.parse.Operators;

public class AspectRatio {

    /* renamed from: a  reason: collision with root package name */
    public static final AspectRatio f4076a = new AspectRatio(255);
    private int b;

    private AspectRatio(int i) {
        this.b = i;
    }

    public static AspectRatio a(int i) {
        if (i == f4076a.b) {
            return f4076a;
        }
        return new AspectRatio(i);
    }

    public int a() {
        return this.b;
    }

    public String toString() {
        return "AspectRatio{" + "value=" + this.b + Operators.BLOCK_END;
    }
}
