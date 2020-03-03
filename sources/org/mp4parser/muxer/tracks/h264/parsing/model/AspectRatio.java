package org.mp4parser.muxer.tracks.h264.parsing.model;

import com.taobao.weex.el.parse.Operators;

public class AspectRatio {

    /* renamed from: a  reason: collision with root package name */
    public static final AspectRatio f4036a = new AspectRatio(255);
    private int b;

    private AspectRatio(int i) {
        this.b = i;
    }

    public static AspectRatio a(int i) {
        if (i == f4036a.b) {
            return f4036a;
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
