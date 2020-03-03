package org.mp4parser.streaming.input.h264.spspps;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class PictureParameterSet {

    /* renamed from: a  reason: collision with root package name */
    public boolean f4080a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public int h;
    public int i;
    public boolean j;
    public int k;
    public int l;
    public int m;
    public int n;
    public boolean o;
    public boolean p;
    public boolean q;
    public int[] r;
    public int[] s;
    public int[] t;
    public boolean u;
    public int[] v;
    public PPSExt w;

    public static PictureParameterSet a(ByteBuffer byteBuffer) throws IOException {
        ByteBufferBitreader byteBufferBitreader = new ByteBufferBitreader(byteBuffer);
        PictureParameterSet pictureParameterSet = new PictureParameterSet();
        pictureParameterSet.e = byteBufferBitreader.c();
        pictureParameterSet.f = byteBufferBitreader.c();
        pictureParameterSet.f4080a = byteBufferBitreader.d();
        pictureParameterSet.g = byteBufferBitreader.d();
        pictureParameterSet.h = byteBufferBitreader.c();
        if (pictureParameterSet.h > 0) {
            pictureParameterSet.i = byteBufferBitreader.c();
            int i2 = 1;
            pictureParameterSet.r = new int[(pictureParameterSet.h + 1)];
            pictureParameterSet.s = new int[(pictureParameterSet.h + 1)];
            pictureParameterSet.t = new int[(pictureParameterSet.h + 1)];
            if (pictureParameterSet.i == 0) {
                for (int i3 = 0; i3 <= pictureParameterSet.h; i3++) {
                    pictureParameterSet.t[i3] = byteBufferBitreader.c();
                }
            } else if (pictureParameterSet.i == 2) {
                for (int i4 = 0; i4 < pictureParameterSet.h; i4++) {
                    pictureParameterSet.r[i4] = byteBufferBitreader.c();
                    pictureParameterSet.s[i4] = byteBufferBitreader.c();
                }
            } else if (pictureParameterSet.i == 3 || pictureParameterSet.i == 4 || pictureParameterSet.i == 5) {
                pictureParameterSet.u = byteBufferBitreader.d();
                pictureParameterSet.d = byteBufferBitreader.c();
            } else if (pictureParameterSet.i == 6) {
                if (pictureParameterSet.h + 1 > 4) {
                    i2 = 3;
                } else if (pictureParameterSet.h + 1 > 2) {
                    i2 = 2;
                }
                int c2 = byteBufferBitreader.c();
                pictureParameterSet.v = new int[(c2 + 1)];
                for (int i5 = 0; i5 <= c2; i5++) {
                    pictureParameterSet.v[i5] = (int) byteBufferBitreader.a(i2);
                }
            }
        }
        pictureParameterSet.b = byteBufferBitreader.c();
        pictureParameterSet.c = byteBufferBitreader.c();
        pictureParameterSet.j = byteBufferBitreader.d();
        pictureParameterSet.k = (int) byteBufferBitreader.a(2);
        pictureParameterSet.l = byteBufferBitreader.e();
        pictureParameterSet.m = byteBufferBitreader.e();
        pictureParameterSet.n = byteBufferBitreader.e();
        pictureParameterSet.o = byteBufferBitreader.d();
        pictureParameterSet.p = byteBufferBitreader.d();
        pictureParameterSet.q = byteBufferBitreader.d();
        if (byteBufferBitreader.f()) {
            pictureParameterSet.w = new PPSExt();
            pictureParameterSet.w.f4081a = byteBufferBitreader.d();
            if (byteBufferBitreader.d()) {
                for (int i6 = 0; i6 < ((pictureParameterSet.w.f4081a ? 1 : 0) * true) + 6; i6++) {
                    if (byteBufferBitreader.d()) {
                        pictureParameterSet.w.b.f4083a = new ScalingList[8];
                        pictureParameterSet.w.b.b = new ScalingList[8];
                        if (i6 < 6) {
                            pictureParameterSet.w.b.f4083a[i6] = ScalingList.a(byteBufferBitreader, 16);
                        } else {
                            pictureParameterSet.w.b.b[i6 - 6] = ScalingList.a(byteBufferBitreader, 64);
                        }
                    }
                }
            }
            pictureParameterSet.w.c = byteBufferBitreader.e();
        }
        return pictureParameterSet;
    }

    public int hashCode() {
        int i2;
        int i3 = 1237;
        int hashCode = (((((((((Arrays.hashCode(this.s) + 31) * 31) + this.n) * 31) + (this.p ? 1231 : 1237)) * 31) + (this.o ? 1231 : 1237)) * 31) + (this.f4080a ? 1231 : 1237)) * 31;
        if (this.w == null) {
            i2 = 0;
        } else {
            i2 = this.w.hashCode();
        }
        int hashCode2 = (((((((((((((((((((((((((((((((((hashCode + i2) * 31) + this.b) * 31) + this.c) * 31) + this.h) * 31) + this.l) * 31) + this.m) * 31) + (this.g ? 1231 : 1237)) * 31) + this.e) * 31) + (this.q ? 1231 : 1237)) * 31) + Arrays.hashCode(this.t)) * 31) + this.f) * 31) + (this.u ? 1231 : 1237)) * 31) + this.d) * 31) + Arrays.hashCode(this.v)) * 31) + this.i) * 31) + Arrays.hashCode(this.r)) * 31) + this.k) * 31;
        if (this.j) {
            i3 = 1231;
        }
        return hashCode2 + i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PictureParameterSet pictureParameterSet = (PictureParameterSet) obj;
        if (!Arrays.equals(this.s, pictureParameterSet.s) || this.n != pictureParameterSet.n || this.p != pictureParameterSet.p || this.o != pictureParameterSet.o || this.f4080a != pictureParameterSet.f4080a) {
            return false;
        }
        if (this.w == null) {
            if (pictureParameterSet.w != null) {
                return false;
            }
        } else if (!this.w.equals(pictureParameterSet.w)) {
            return false;
        }
        return this.b == pictureParameterSet.b && this.c == pictureParameterSet.c && this.h == pictureParameterSet.h && this.l == pictureParameterSet.l && this.m == pictureParameterSet.m && this.g == pictureParameterSet.g && this.e == pictureParameterSet.e && this.q == pictureParameterSet.q && Arrays.equals(this.t, pictureParameterSet.t) && this.f == pictureParameterSet.f && this.u == pictureParameterSet.u && this.d == pictureParameterSet.d && Arrays.equals(this.v, pictureParameterSet.v) && this.i == pictureParameterSet.i && Arrays.equals(this.r, pictureParameterSet.r) && this.k == pictureParameterSet.k && this.j == pictureParameterSet.j;
    }

    public String toString() {
        return "PictureParameterSet{\n       entropy_coding_mode_flag=" + this.f4080a + ",\n       num_ref_idx_l0_active_minus1=" + this.b + ",\n       num_ref_idx_l1_active_minus1=" + this.c + ",\n       slice_group_change_rate_minus1=" + this.d + ",\n       pic_parameter_set_id=" + this.e + ",\n       seq_parameter_set_id=" + this.f + ",\n       pic_order_present_flag=" + this.g + ",\n       num_slice_groups_minus1=" + this.h + ",\n       slice_group_map_type=" + this.i + ",\n       weighted_pred_flag=" + this.j + ",\n       weighted_bipred_idc=" + this.k + ",\n       pic_init_qp_minus26=" + this.l + ",\n       pic_init_qs_minus26=" + this.m + ",\n       chroma_qp_index_offset=" + this.n + ",\n       deblocking_filter_control_present_flag=" + this.o + ",\n       constrained_intra_pred_flag=" + this.p + ",\n       redundant_pic_cnt_present_flag=" + this.q + ",\n       top_left=" + this.r + ",\n       bottom_right=" + this.s + ",\n       run_length_minus1=" + this.t + ",\n       slice_group_change_direction_flag=" + this.u + ",\n       slice_group_id=" + this.v + ",\n       extended=" + this.w + Operators.BLOCK_END;
    }

    public static class PPSExt {

        /* renamed from: a  reason: collision with root package name */
        public boolean f4081a;
        public ScalingMatrix b = new ScalingMatrix();
        public int c;
        public boolean[] d;

        public String toString() {
            return "PPSExt{transform_8x8_mode_flag=" + this.f4081a + ", scalindMatrix=" + this.b + ", second_chroma_qp_index_offset=" + this.c + ", pic_scaling_list_present_flag=" + this.d + Operators.BLOCK_END;
        }
    }
}
