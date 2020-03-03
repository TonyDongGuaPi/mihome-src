package org.mp4parser.muxer.tracks.h264.parsing.model;

import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader;
import org.mp4parser.muxer.tracks.h264.parsing.write.CAVLCWriter;

public class PictureParameterSet extends BitstreamElement {

    /* renamed from: a  reason: collision with root package name */
    public boolean f4039a;
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

    public static PictureParameterSet a(byte[] bArr) throws IOException {
        return a((InputStream) new ByteArrayInputStream(bArr));
    }

    public static PictureParameterSet a(InputStream inputStream) throws IOException {
        CAVLCReader cAVLCReader = new CAVLCReader(inputStream);
        PictureParameterSet pictureParameterSet = new PictureParameterSet();
        pictureParameterSet.e = cAVLCReader.a("PPS: pic_parameter_set_id");
        pictureParameterSet.f = cAVLCReader.a("PPS: seq_parameter_set_id");
        pictureParameterSet.f4039a = cAVLCReader.c("PPS: entropy_coding_mode_flag");
        pictureParameterSet.g = cAVLCReader.c("PPS: pic_order_present_flag");
        pictureParameterSet.h = cAVLCReader.a("PPS: num_slice_groups_minus1");
        if (pictureParameterSet.h > 0) {
            pictureParameterSet.i = cAVLCReader.a("PPS: slice_group_map_type");
            int i2 = 1;
            pictureParameterSet.r = new int[(pictureParameterSet.h + 1)];
            pictureParameterSet.s = new int[(pictureParameterSet.h + 1)];
            pictureParameterSet.t = new int[(pictureParameterSet.h + 1)];
            if (pictureParameterSet.i == 0) {
                for (int i3 = 0; i3 <= pictureParameterSet.h; i3++) {
                    pictureParameterSet.t[i3] = cAVLCReader.a("PPS: run_length_minus1");
                }
            } else if (pictureParameterSet.i == 2) {
                for (int i4 = 0; i4 < pictureParameterSet.h; i4++) {
                    pictureParameterSet.r[i4] = cAVLCReader.a("PPS: top_left");
                    pictureParameterSet.s[i4] = cAVLCReader.a("PPS: bottom_right");
                }
            } else if (pictureParameterSet.i == 3 || pictureParameterSet.i == 4 || pictureParameterSet.i == 5) {
                pictureParameterSet.u = cAVLCReader.c("PPS: slice_group_change_direction_flag");
                pictureParameterSet.d = cAVLCReader.a("PPS: slice_group_change_rate_minus1");
            } else if (pictureParameterSet.i == 6) {
                if (pictureParameterSet.h + 1 > 4) {
                    i2 = 3;
                } else if (pictureParameterSet.h + 1 > 2) {
                    i2 = 2;
                }
                int a2 = cAVLCReader.a("PPS: pic_size_in_map_units_minus1");
                pictureParameterSet.v = new int[(a2 + 1)];
                for (int i5 = 0; i5 <= a2; i5++) {
                    int[] iArr = pictureParameterSet.v;
                    iArr[i5] = cAVLCReader.b(i2, "PPS: slice_group_id [" + i5 + "]f");
                }
            }
        }
        pictureParameterSet.b = cAVLCReader.a("PPS: num_ref_idx_l0_active_minus1");
        pictureParameterSet.c = cAVLCReader.a("PPS: num_ref_idx_l1_active_minus1");
        pictureParameterSet.j = cAVLCReader.c("PPS: weighted_pred_flag");
        pictureParameterSet.k = (int) cAVLCReader.a(2, "PPS: weighted_bipred_idc");
        pictureParameterSet.l = cAVLCReader.b("PPS: pic_init_qp_minus26");
        pictureParameterSet.m = cAVLCReader.b("PPS: pic_init_qs_minus26");
        pictureParameterSet.n = cAVLCReader.b("PPS: chroma_qp_index_offset");
        pictureParameterSet.o = cAVLCReader.c("PPS: deblocking_filter_control_present_flag");
        pictureParameterSet.p = cAVLCReader.c("PPS: constrained_intra_pred_flag");
        pictureParameterSet.q = cAVLCReader.c("PPS: redundant_pic_cnt_present_flag");
        if (cAVLCReader.d()) {
            pictureParameterSet.w = new PPSExt();
            pictureParameterSet.w.f4040a = cAVLCReader.c("PPS: transform_8x8_mode_flag");
            if (cAVLCReader.c("PPS: pic_scaling_matrix_present_flag")) {
                for (int i6 = 0; i6 < ((pictureParameterSet.w.f4040a ? 1 : 0) * true) + 6; i6++) {
                    if (cAVLCReader.c("PPS: pic_scaling_list_present_flag")) {
                        pictureParameterSet.w.b.f4042a = new ScalingList[8];
                        pictureParameterSet.w.b.b = new ScalingList[8];
                        if (i6 < 6) {
                            pictureParameterSet.w.b.f4042a[i6] = ScalingList.a(cAVLCReader, 16);
                        } else {
                            pictureParameterSet.w.b.b[i6 - 6] = ScalingList.a(cAVLCReader, 64);
                        }
                    }
                }
            }
            pictureParameterSet.w.c = cAVLCReader.b("PPS: second_chroma_qp_index_offset");
        }
        cAVLCReader.l();
        return pictureParameterSet;
    }

    public void a(OutputStream outputStream) throws IOException {
        CAVLCWriter cAVLCWriter = new CAVLCWriter(outputStream);
        cAVLCWriter.a(this.e, "PPS: pic_parameter_set_id");
        cAVLCWriter.a(this.f, "PPS: seq_parameter_set_id");
        cAVLCWriter.a(this.f4039a, "PPS: entropy_coding_mode_flag");
        cAVLCWriter.a(this.g, "PPS: pic_order_present_flag");
        cAVLCWriter.a(this.h, "PPS: num_slice_groups_minus1");
        if (this.h > 0) {
            cAVLCWriter.a(this.i, "PPS: slice_group_map_type");
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            int[] iArr3 = new int[1];
            if (this.i == 0) {
                for (int i2 = 0; i2 <= this.h; i2++) {
                    cAVLCWriter.a(iArr3[i2], "PPS: ");
                }
            } else if (this.i == 2) {
                for (int i3 = 0; i3 < this.h; i3++) {
                    cAVLCWriter.a(iArr[i3], "PPS: ");
                    cAVLCWriter.a(iArr2[i3], "PPS: ");
                }
            } else {
                int i4 = 3;
                if (this.i == 3 || this.i == 4 || this.i == 5) {
                    cAVLCWriter.a(this.u, "PPS: slice_group_change_direction_flag");
                    cAVLCWriter.a(this.d, "PPS: slice_group_change_rate_minus1");
                } else if (this.i == 6) {
                    if (this.h + 1 <= 4) {
                        i4 = this.h + 1 > 2 ? 2 : 1;
                    }
                    cAVLCWriter.a(this.v.length, "PPS: ");
                    for (int i5 = 0; i5 <= this.v.length; i5++) {
                        cAVLCWriter.a(this.v[i5], i4);
                    }
                }
            }
        }
        cAVLCWriter.a(this.b, "PPS: num_ref_idx_l0_active_minus1");
        cAVLCWriter.a(this.c, "PPS: num_ref_idx_l1_active_minus1");
        cAVLCWriter.a(this.j, "PPS: weighted_pred_flag");
        cAVLCWriter.a((long) this.k, 2, "PPS: weighted_bipred_idc");
        cAVLCWriter.b(this.l, "PPS: pic_init_qp_minus26");
        cAVLCWriter.b(this.m, "PPS: pic_init_qs_minus26");
        cAVLCWriter.b(this.n, "PPS: chroma_qp_index_offset");
        cAVLCWriter.a(this.o, "PPS: deblocking_filter_control_present_flag");
        cAVLCWriter.a(this.p, "PPS: constrained_intra_pred_flag");
        cAVLCWriter.a(this.q, "PPS: redundant_pic_cnt_present_flag");
        if (this.w != null) {
            cAVLCWriter.a(this.w.f4040a, "PPS: transform_8x8_mode_flag");
            cAVLCWriter.a(this.w.b != null, "PPS: scalindMatrix");
            if (this.w.b != null) {
                for (int i6 = 0; i6 < ((this.w.f4040a ? 1 : 0) * true) + 6; i6++) {
                    if (i6 < 6) {
                        cAVLCWriter.a(this.w.b.f4042a[i6] != null, "PPS: ");
                        if (this.w.b.f4042a[i6] != null) {
                            this.w.b.f4042a[i6].a(cAVLCWriter);
                        }
                    } else {
                        int i7 = i6 - 6;
                        cAVLCWriter.a(this.w.b.b[i7] != null, "PPS: ");
                        if (this.w.b.b[i7] != null) {
                            this.w.b.b[i7].a(cAVLCWriter);
                        }
                    }
                }
            }
            cAVLCWriter.b(this.w.c, "PPS: ");
        }
        cAVLCWriter.c();
    }

    public int hashCode() {
        int i2;
        int i3 = 1237;
        int hashCode = (((((((((Arrays.hashCode(this.s) + 31) * 31) + this.n) * 31) + (this.p ? 1231 : 1237)) * 31) + (this.o ? 1231 : 1237)) * 31) + (this.f4039a ? 1231 : 1237)) * 31;
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
        if (!Arrays.equals(this.s, pictureParameterSet.s) || this.n != pictureParameterSet.n || this.p != pictureParameterSet.p || this.o != pictureParameterSet.o || this.f4039a != pictureParameterSet.f4039a) {
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
        return "PictureParameterSet{\n       entropy_coding_mode_flag=" + this.f4039a + ",\n       num_ref_idx_l0_active_minus1=" + this.b + ",\n       num_ref_idx_l1_active_minus1=" + this.c + ",\n       slice_group_change_rate_minus1=" + this.d + ",\n       pic_parameter_set_id=" + this.e + ",\n       seq_parameter_set_id=" + this.f + ",\n       pic_order_present_flag=" + this.g + ",\n       num_slice_groups_minus1=" + this.h + ",\n       slice_group_map_type=" + this.i + ",\n       weighted_pred_flag=" + this.j + ",\n       weighted_bipred_idc=" + this.k + ",\n       pic_init_qp_minus26=" + this.l + ",\n       pic_init_qs_minus26=" + this.m + ",\n       chroma_qp_index_offset=" + this.n + ",\n       deblocking_filter_control_present_flag=" + this.o + ",\n       constrained_intra_pred_flag=" + this.p + ",\n       redundant_pic_cnt_present_flag=" + this.q + ",\n       top_left=" + this.r + ",\n       bottom_right=" + this.s + ",\n       run_length_minus1=" + this.t + ",\n       slice_group_change_direction_flag=" + this.u + ",\n       slice_group_id=" + this.v + ",\n       extended=" + this.w + Operators.BLOCK_END;
    }

    public static class PPSExt {

        /* renamed from: a  reason: collision with root package name */
        public boolean f4040a;
        public ScalingMatrix b = new ScalingMatrix();
        public int c;
        public boolean[] d;

        public String toString() {
            return "PPSExt{transform_8x8_mode_flag=" + this.f4040a + ", scalindMatrix=" + this.b + ", second_chroma_qp_index_offset=" + this.c + ", pic_scaling_list_present_flag=" + this.d + Operators.BLOCK_END;
        }
    }
}
