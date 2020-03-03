package org.mp4parser.streaming.input.h264.spspps;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;

public class ScalingList {

    /* renamed from: a  reason: collision with root package name */
    public int[] f4082a;
    public boolean b;

    public static ScalingList a(ByteBufferBitreader byteBufferBitreader, int i) throws IOException {
        ScalingList scalingList = new ScalingList();
        scalingList.f4082a = new int[i];
        int i2 = 0;
        int i3 = 8;
        int i4 = 8;
        while (i2 < i) {
            if (i3 != 0) {
                i3 = ((byteBufferBitreader.e() + i4) + 256) % 256;
                scalingList.b = i2 == 0 && i3 == 0;
            }
            int[] iArr = scalingList.f4082a;
            if (i3 != 0) {
                i4 = i3;
            }
            iArr[i2] = i4;
            i4 = scalingList.f4082a[i2];
            i2++;
        }
        return scalingList;
    }

    public String toString() {
        return "ScalingList{scalingList=" + this.f4082a + ", useDefaultScalingMatrixFlag=" + this.b + Operators.BLOCK_END;
    }
}
