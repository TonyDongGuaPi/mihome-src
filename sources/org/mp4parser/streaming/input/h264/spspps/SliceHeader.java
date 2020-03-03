package org.mp4parser.streaming.input.h264.spspps;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class SliceHeader {

    /* renamed from: a  reason: collision with root package name */
    public int f4085a;
    public SliceType b;
    public int c;
    public int d;
    public int e;
    public boolean f = false;
    public boolean g = false;
    public int h = -1;
    public int i;
    public int j;
    public int k;
    public int l;
    public PictureParameterSet m;
    public SeqParameterSet n;

    public enum SliceType {
        P,
        B,
        I,
        SP,
        SI
    }

    public SliceHeader(ByteBuffer byteBuffer, Map<Integer, SeqParameterSet> map, Map<Integer, PictureParameterSet> map2, boolean z) {
        try {
            byteBuffer.position(1);
            ByteBufferBitreader byteBufferBitreader = new ByteBufferBitreader(byteBuffer);
            this.f4085a = byteBufferBitreader.c();
            switch (byteBufferBitreader.c()) {
                case 0:
                case 5:
                    this.b = SliceType.P;
                    break;
                case 1:
                case 6:
                    this.b = SliceType.B;
                    break;
                case 2:
                case 7:
                    this.b = SliceType.I;
                    break;
                case 3:
                case 8:
                    this.b = SliceType.SP;
                    break;
                case 4:
                case 9:
                    this.b = SliceType.SI;
                    break;
            }
            this.c = byteBufferBitreader.c();
            this.m = map2.get(Integer.valueOf(this.c));
            if (this.m == null) {
                String str = "";
                for (Integer num : map2.keySet()) {
                    str = str + num + ", ";
                }
                throw new RuntimeException("PPS with ids " + str + " available but not " + this.c);
            }
            this.n = map.get(Integer.valueOf(this.m.f));
            if (this.n == null) {
                String str2 = "";
                for (Integer num2 : map.keySet()) {
                    str2 = str2 + num2 + ", ";
                }
                throw new RuntimeException("SPS with ids " + str2 + " available but not " + this.m.f);
            }
            if (this.n.A) {
                this.d = (int) byteBufferBitreader.a(2);
            }
            this.e = (int) byteBufferBitreader.a(this.n.j + 4);
            if (!this.n.F) {
                this.f = byteBufferBitreader.d();
                if (this.f) {
                    this.g = byteBufferBitreader.d();
                }
            }
            if (z) {
                this.h = byteBufferBitreader.c();
            }
            if (this.n.f4084a == 0) {
                this.i = (int) byteBufferBitreader.a(this.n.k + 4);
                if (this.m.g && !this.f) {
                    this.j = byteBufferBitreader.e();
                }
            }
            if (this.n.f4084a == 1 && !this.n.c) {
                this.k = byteBufferBitreader.e();
                if (this.m.g && !this.f) {
                    this.l = byteBufferBitreader.e();
                }
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public String toString() {
        return "SliceHeader{first_mb_in_slice=" + this.f4085a + ", slice_type=" + this.b + ", pic_parameter_set_id=" + this.c + ", colour_plane_id=" + this.d + ", frame_num=" + this.e + ", field_pic_flag=" + this.f + ", bottom_field_flag=" + this.g + ", idr_pic_id=" + this.h + ", pic_order_cnt_lsb=" + this.i + ", delta_pic_order_cnt_bottom=" + this.j + Operators.BLOCK_END;
    }
}
