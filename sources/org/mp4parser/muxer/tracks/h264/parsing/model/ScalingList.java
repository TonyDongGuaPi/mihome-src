package org.mp4parser.muxer.tracks.h264.parsing.model;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader;
import org.mp4parser.muxer.tracks.h264.parsing.write.CAVLCWriter;

public class ScalingList {

    /* renamed from: a  reason: collision with root package name */
    public int[] f4041a;
    public boolean b;

    public static ScalingList a(CAVLCReader cAVLCReader, int i) throws IOException {
        ScalingList scalingList = new ScalingList();
        scalingList.f4041a = new int[i];
        int i2 = 0;
        int i3 = 8;
        int i4 = 8;
        while (i2 < i) {
            if (i3 != 0) {
                i3 = ((cAVLCReader.b("deltaScale") + i4) + 256) % 256;
                scalingList.b = i2 == 0 && i3 == 0;
            }
            int[] iArr = scalingList.f4041a;
            if (i3 != 0) {
                i4 = i3;
            }
            iArr[i2] = i4;
            i4 = scalingList.f4041a[i2];
            i2++;
        }
        return scalingList;
    }

    public void a(CAVLCWriter cAVLCWriter) throws IOException {
        if (this.b) {
            cAVLCWriter.b(0, "SPS: ");
            return;
        }
        int i = 8;
        for (int i2 = 0; i2 < this.f4041a.length; i2++) {
            cAVLCWriter.b((this.f4041a[i2] - i) - 256, "SPS: ");
            i = this.f4041a[i2];
        }
    }

    public String toString() {
        return "ScalingList{scalingList=" + this.f4041a + ", useDefaultScalingMatrixFlag=" + this.b + Operators.BLOCK_END;
    }
}
