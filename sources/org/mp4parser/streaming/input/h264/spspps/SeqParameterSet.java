package org.mp4parser.streaming.input.h264.spspps;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.mp4parser.streaming.input.h264.spspps.VUIParameters;

public class SeqParameterSet {
    public boolean A;
    public int B;
    public int C;
    public int D;
    public boolean E;
    public boolean F;
    public boolean G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int[] L;
    public VUIParameters M;
    public ScalingMatrix N;
    public int O;

    /* renamed from: a  reason: collision with root package name */
    public int f4084a;
    public boolean b;
    public boolean c;
    public boolean d;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public ChromaFormat i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public int q;
    public long r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public int y;
    public int z;

    public static SeqParameterSet a(ByteBuffer byteBuffer) throws IOException {
        ByteBufferBitreader byteBufferBitreader = new ByteBufferBitreader(byteBuffer);
        SeqParameterSet seqParameterSet = new SeqParameterSet();
        seqParameterSet.q = (int) byteBufferBitreader.a(8);
        seqParameterSet.s = byteBufferBitreader.d();
        seqParameterSet.t = byteBufferBitreader.d();
        seqParameterSet.u = byteBufferBitreader.d();
        seqParameterSet.v = byteBufferBitreader.d();
        seqParameterSet.w = byteBufferBitreader.d();
        seqParameterSet.x = byteBufferBitreader.d();
        seqParameterSet.r = byteBufferBitreader.a(2);
        seqParameterSet.y = (int) byteBufferBitreader.a(8);
        seqParameterSet.z = byteBufferBitreader.c();
        if (seqParameterSet.q == 100 || seqParameterSet.q == 110 || seqParameterSet.q == 122 || seqParameterSet.q == 144) {
            seqParameterSet.i = ChromaFormat.a(byteBufferBitreader.c());
            if (seqParameterSet.i == ChromaFormat.d) {
                seqParameterSet.A = byteBufferBitreader.d();
            }
            seqParameterSet.n = byteBufferBitreader.c();
            seqParameterSet.o = byteBufferBitreader.c();
            seqParameterSet.p = byteBufferBitreader.d();
            if (byteBufferBitreader.d()) {
                a(byteBufferBitreader, seqParameterSet);
            }
        } else {
            seqParameterSet.i = ChromaFormat.b;
        }
        seqParameterSet.j = byteBufferBitreader.c();
        seqParameterSet.f4084a = byteBufferBitreader.c();
        if (seqParameterSet.f4084a == 0) {
            seqParameterSet.k = byteBufferBitreader.c();
        } else if (seqParameterSet.f4084a == 1) {
            seqParameterSet.c = byteBufferBitreader.d();
            seqParameterSet.B = byteBufferBitreader.e();
            seqParameterSet.C = byteBufferBitreader.e();
            seqParameterSet.O = byteBufferBitreader.c();
            seqParameterSet.L = new int[seqParameterSet.O];
            for (int i2 = 0; i2 < seqParameterSet.O; i2++) {
                seqParameterSet.L[i2] = byteBufferBitreader.e();
            }
        }
        seqParameterSet.D = byteBufferBitreader.c();
        seqParameterSet.E = byteBufferBitreader.d();
        seqParameterSet.m = byteBufferBitreader.c();
        seqParameterSet.l = byteBufferBitreader.c();
        seqParameterSet.F = byteBufferBitreader.d();
        if (!seqParameterSet.F) {
            seqParameterSet.g = byteBufferBitreader.d();
        }
        seqParameterSet.h = byteBufferBitreader.d();
        seqParameterSet.G = byteBufferBitreader.d();
        if (seqParameterSet.G) {
            seqParameterSet.H = byteBufferBitreader.c();
            seqParameterSet.I = byteBufferBitreader.c();
            seqParameterSet.J = byteBufferBitreader.c();
            seqParameterSet.K = byteBufferBitreader.c();
        }
        if (byteBufferBitreader.d()) {
            seqParameterSet.M = a(byteBufferBitreader);
        }
        return seqParameterSet;
    }

    private static void a(ByteBufferBitreader byteBufferBitreader, SeqParameterSet seqParameterSet) throws IOException {
        seqParameterSet.N = new ScalingMatrix();
        for (int i2 = 0; i2 < 8; i2++) {
            if (byteBufferBitreader.d()) {
                seqParameterSet.N.f4083a = new ScalingList[8];
                seqParameterSet.N.b = new ScalingList[8];
                if (i2 < 6) {
                    seqParameterSet.N.f4083a[i2] = ScalingList.a(byteBufferBitreader, 16);
                } else {
                    seqParameterSet.N.b[i2 - 6] = ScalingList.a(byteBufferBitreader, 64);
                }
            }
        }
    }

    private static VUIParameters a(ByteBufferBitreader byteBufferBitreader) throws IOException {
        VUIParameters vUIParameters = new VUIParameters();
        vUIParameters.f4086a = byteBufferBitreader.d();
        if (vUIParameters.f4086a) {
            vUIParameters.y = AspectRatio.a((int) byteBufferBitreader.a(8));
            if (vUIParameters.y == AspectRatio.f4076a) {
                vUIParameters.b = (int) byteBufferBitreader.a(16);
                vUIParameters.c = (int) byteBufferBitreader.a(16);
            }
        }
        vUIParameters.d = byteBufferBitreader.d();
        if (vUIParameters.d) {
            vUIParameters.e = byteBufferBitreader.d();
        }
        vUIParameters.f = byteBufferBitreader.d();
        if (vUIParameters.f) {
            vUIParameters.g = (int) byteBufferBitreader.a(3);
            vUIParameters.h = byteBufferBitreader.d();
            vUIParameters.i = byteBufferBitreader.d();
            if (vUIParameters.i) {
                vUIParameters.j = (int) byteBufferBitreader.a(8);
                vUIParameters.k = (int) byteBufferBitreader.a(8);
                vUIParameters.l = (int) byteBufferBitreader.a(8);
            }
        }
        vUIParameters.m = byteBufferBitreader.d();
        if (vUIParameters.m) {
            vUIParameters.n = byteBufferBitreader.c();
            vUIParameters.o = byteBufferBitreader.c();
        }
        vUIParameters.p = byteBufferBitreader.d();
        if (vUIParameters.p) {
            vUIParameters.q = (int) byteBufferBitreader.a(32);
            vUIParameters.r = (int) byteBufferBitreader.a(32);
            vUIParameters.s = byteBufferBitreader.d();
        }
        boolean d2 = byteBufferBitreader.d();
        if (d2) {
            vUIParameters.v = b(byteBufferBitreader);
        }
        boolean d3 = byteBufferBitreader.d();
        if (d3) {
            vUIParameters.w = b(byteBufferBitreader);
        }
        if (d2 || d3) {
            vUIParameters.t = byteBufferBitreader.d();
        }
        vUIParameters.u = byteBufferBitreader.d();
        if (byteBufferBitreader.d()) {
            vUIParameters.x = new VUIParameters.BitstreamRestriction();
            vUIParameters.x.f4087a = byteBufferBitreader.d();
            vUIParameters.x.b = byteBufferBitreader.c();
            vUIParameters.x.c = byteBufferBitreader.c();
            vUIParameters.x.d = byteBufferBitreader.c();
            vUIParameters.x.e = byteBufferBitreader.c();
            vUIParameters.x.f = byteBufferBitreader.c();
            vUIParameters.x.g = byteBufferBitreader.c();
        }
        return vUIParameters;
    }

    private static HRDParameters b(ByteBufferBitreader byteBufferBitreader) throws IOException {
        HRDParameters hRDParameters = new HRDParameters();
        hRDParameters.f4079a = byteBufferBitreader.c();
        hRDParameters.b = (int) byteBufferBitreader.a(4);
        hRDParameters.c = (int) byteBufferBitreader.a(4);
        hRDParameters.d = new int[(hRDParameters.f4079a + 1)];
        hRDParameters.e = new int[(hRDParameters.f4079a + 1)];
        hRDParameters.f = new boolean[(hRDParameters.f4079a + 1)];
        for (int i2 = 0; i2 <= hRDParameters.f4079a; i2++) {
            hRDParameters.d[i2] = byteBufferBitreader.c();
            hRDParameters.e[i2] = byteBufferBitreader.c();
            hRDParameters.f[i2] = byteBufferBitreader.d();
        }
        hRDParameters.g = (int) byteBufferBitreader.a(5);
        hRDParameters.h = (int) byteBufferBitreader.a(5);
        hRDParameters.i = (int) byteBufferBitreader.a(5);
        hRDParameters.j = (int) byteBufferBitreader.a(5);
        return hRDParameters;
    }

    public String toString() {
        return "SeqParameterSet{ \n        pic_order_cnt_type=" + this.f4084a + ", \n        field_pic_flag=" + this.b + ", \n        delta_pic_order_always_zero_flag=" + this.c + ", \n        weighted_pred_flag=" + this.d + ", \n        weighted_bipred_idc=" + this.e + ", \n        entropy_coding_mode_flag=" + this.f + ", \n        mb_adaptive_frame_field_flag=" + this.g + ", \n        direct_8x8_inference_flag=" + this.h + ", \n        chroma_format_idc=" + this.i + ", \n        log2_max_frame_num_minus4=" + this.j + ", \n        log2_max_pic_order_cnt_lsb_minus4=" + this.k + ", \n        pic_height_in_map_units_minus1=" + this.l + ", \n        pic_width_in_mbs_minus1=" + this.m + ", \n        bit_depth_luma_minus8=" + this.n + ", \n        bit_depth_chroma_minus8=" + this.o + ", \n        qpprime_y_zero_transform_bypass_flag=" + this.p + ", \n        profile_idc=" + this.q + ", \n        constraint_set_0_flag=" + this.s + ", \n        constraint_set_1_flag=" + this.t + ", \n        constraint_set_2_flag=" + this.u + ", \n        constraint_set_3_flag=" + this.v + ", \n        constraint_set_4_flag=" + this.w + ", \n        constraint_set_5_flag=" + this.x + ", \n        level_idc=" + this.y + ", \n        seq_parameter_set_id=" + this.z + ", \n        residual_color_transform_flag=" + this.A + ", \n        offset_for_non_ref_pic=" + this.B + ", \n        offset_for_top_to_bottom_field=" + this.C + ", \n        num_ref_frames=" + this.D + ", \n        gaps_in_frame_num_value_allowed_flag=" + this.E + ", \n        frame_mbs_only_flag=" + this.F + ", \n        frame_cropping_flag=" + this.G + ", \n        frame_crop_left_offset=" + this.H + ", \n        frame_crop_right_offset=" + this.I + ", \n        frame_crop_top_offset=" + this.J + ", \n        frame_crop_bottom_offset=" + this.K + ", \n        offsetForRefFrame=" + this.L + ", \n        vuiParams=" + this.M + ", \n        scalingMatrix=" + this.N + ", \n        num_ref_frames_in_pic_order_cnt_cycle=" + this.O + Operators.BLOCK_END;
    }
}
