package org.mp4parser.muxer.tracks.h264.parsing.model;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.List;

public class ScalingMatrix {

    /* renamed from: a  reason: collision with root package name */
    public ScalingList[] f4042a;
    public ScalingList[] b;

    public String toString() {
        List list;
        StringBuilder sb = new StringBuilder();
        sb.append("ScalingMatrix{ScalingList4x4=");
        List list2 = null;
        if (this.f4042a == null) {
            list = null;
        } else {
            list = Arrays.asList(this.f4042a);
        }
        sb.append(list);
        sb.append("\n");
        sb.append(", ScalingList8x8=");
        if (this.b != null) {
            list2 = Arrays.asList(this.b);
        }
        sb.append(list2);
        sb.append("\n");
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
