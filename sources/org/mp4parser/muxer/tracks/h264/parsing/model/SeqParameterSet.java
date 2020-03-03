package org.mp4parser.muxer.tracks.h264.parsing.model;

import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.mp4parser.muxer.tracks.h264.parsing.model.VUIParameters;
import org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader;
import org.mp4parser.muxer.tracks.h264.parsing.write.CAVLCWriter;

public class SeqParameterSet extends BitstreamElement {
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
    public int f4043a;
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

    public static SeqParameterSet a(byte[] bArr) throws IOException {
        return a((InputStream) new ByteArrayInputStream(bArr));
    }

    public static SeqParameterSet a(InputStream inputStream) throws IOException {
        CAVLCReader cAVLCReader = new CAVLCReader(inputStream);
        SeqParameterSet seqParameterSet = new SeqParameterSet();
        seqParameterSet.q = (int) cAVLCReader.a(8, "SPS: profile_idc");
        seqParameterSet.s = cAVLCReader.c("SPS: constraint_set_0_flag");
        seqParameterSet.t = cAVLCReader.c("SPS: constraint_set_1_flag");
        seqParameterSet.u = cAVLCReader.c("SPS: constraint_set_2_flag");
        seqParameterSet.v = cAVLCReader.c("SPS: constraint_set_3_flag");
        seqParameterSet.w = cAVLCReader.c("SPS: constraint_set_4_flag");
        seqParameterSet.x = cAVLCReader.c("SPS: constraint_set_5_flag");
        seqParameterSet.r = cAVLCReader.a(2, "SPS: reserved_zero_2bits");
        seqParameterSet.y = (int) cAVLCReader.a(8, "SPS: level_idc");
        seqParameterSet.z = cAVLCReader.a("SPS: seq_parameter_set_id");
        if (seqParameterSet.q == 100 || seqParameterSet.q == 110 || seqParameterSet.q == 122 || seqParameterSet.q == 144) {
            seqParameterSet.i = ChromaFormat.a(cAVLCReader.a("SPS: chroma_format_idc"));
            if (seqParameterSet.i == ChromaFormat.d) {
                seqParameterSet.A = cAVLCReader.c("SPS: residual_color_transform_flag");
            }
            seqParameterSet.n = cAVLCReader.a("SPS: bit_depth_luma_minus8");
            seqParameterSet.o = cAVLCReader.a("SPS: bit_depth_chroma_minus8");
            seqParameterSet.p = cAVLCReader.c("SPS: qpprime_y_zero_transform_bypass_flag");
            if (cAVLCReader.c("SPS: seq_scaling_matrix_present_lag")) {
                a(cAVLCReader, seqParameterSet);
            }
        } else {
            seqParameterSet.i = ChromaFormat.b;
        }
        seqParameterSet.j = cAVLCReader.a("SPS: log2_max_frame_num_minus4");
        seqParameterSet.f4043a = cAVLCReader.a("SPS: pic_order_cnt_type");
        if (seqParameterSet.f4043a == 0) {
            seqParameterSet.k = cAVLCReader.a("SPS: log2_max_pic_order_cnt_lsb_minus4");
        } else if (seqParameterSet.f4043a == 1) {
            seqParameterSet.c = cAVLCReader.c("SPS: delta_pic_order_always_zero_flag");
            seqParameterSet.B = cAVLCReader.b("SPS: offset_for_non_ref_pic");
            seqParameterSet.C = cAVLCReader.b("SPS: offset_for_top_to_bottom_field");
            seqParameterSet.O = cAVLCReader.a("SPS: num_ref_frames_in_pic_order_cnt_cycle");
            seqParameterSet.L = new int[seqParameterSet.O];
            for (int i2 = 0; i2 < seqParameterSet.O; i2++) {
                int[] iArr = seqParameterSet.L;
                iArr[i2] = cAVLCReader.b("SPS: offsetForRefFrame [" + i2 + Operators.ARRAY_END_STR);
            }
        }
        seqParameterSet.D = cAVLCReader.a("SPS: num_ref_frames");
        seqParameterSet.E = cAVLCReader.c("SPS: gaps_in_frame_num_value_allowed_flag");
        seqParameterSet.m = cAVLCReader.a("SPS: pic_width_in_mbs_minus1");
        seqParameterSet.l = cAVLCReader.a("SPS: pic_height_in_map_units_minus1");
        seqParameterSet.F = cAVLCReader.c("SPS: frame_mbs_only_flag");
        if (!seqParameterSet.F) {
            seqParameterSet.g = cAVLCReader.c("SPS: mb_adaptive_frame_field_flag");
        }
        seqParameterSet.h = cAVLCReader.c("SPS: direct_8x8_inference_flag");
        seqParameterSet.G = cAVLCReader.c("SPS: frame_cropping_flag");
        if (seqParameterSet.G) {
            seqParameterSet.H = cAVLCReader.a("SPS: frame_crop_left_offset");
            seqParameterSet.I = cAVLCReader.a("SPS: frame_crop_right_offset");
            seqParameterSet.J = cAVLCReader.a("SPS: frame_crop_top_offset");
            seqParameterSet.K = cAVLCReader.a("SPS: frame_crop_bottom_offset");
        }
        if (cAVLCReader.c("SPS: vui_parameters_present_flag")) {
            seqParameterSet.M = a(cAVLCReader);
        }
        cAVLCReader.l();
        return seqParameterSet;
    }

    private static void a(CAVLCReader cAVLCReader, SeqParameterSet seqParameterSet) throws IOException {
        seqParameterSet.N = new ScalingMatrix();
        for (int i2 = 0; i2 < 8; i2++) {
            if (cAVLCReader.c("SPS: seqScalingListPresentFlag")) {
                seqParameterSet.N.f4042a = new ScalingList[8];
                seqParameterSet.N.b = new ScalingList[8];
                if (i2 < 6) {
                    seqParameterSet.N.f4042a[i2] = ScalingList.a(cAVLCReader, 16);
                } else {
                    seqParameterSet.N.b[i2 - 6] = ScalingList.a(cAVLCReader, 64);
                }
            }
        }
    }

    private static VUIParameters a(CAVLCReader cAVLCReader) throws IOException {
        VUIParameters vUIParameters = new VUIParameters();
        vUIParameters.f4044a = cAVLCReader.c("VUI: aspect_ratio_info_present_flag");
        if (vUIParameters.f4044a) {
            vUIParameters.y = AspectRatio.a((int) cAVLCReader.a(8, "VUI: aspect_ratio"));
            if (vUIParameters.y == AspectRatio.f4036a) {
                vUIParameters.b = (int) cAVLCReader.a(16, "VUI: sar_width");
                vUIParameters.c = (int) cAVLCReader.a(16, "VUI: sar_height");
            }
        }
        vUIParameters.d = cAVLCReader.c("VUI: overscan_info_present_flag");
        if (vUIParameters.d) {
            vUIParameters.e = cAVLCReader.c("VUI: overscan_appropriate_flag");
        }
        vUIParameters.f = cAVLCReader.c("VUI: video_signal_type_present_flag");
        if (vUIParameters.f) {
            vUIParameters.g = (int) cAVLCReader.a(3, "VUI: video_format");
            vUIParameters.h = cAVLCReader.c("VUI: video_full_range_flag");
            vUIParameters.i = cAVLCReader.c("VUI: colour_description_present_flag");
            if (vUIParameters.i) {
                vUIParameters.j = (int) cAVLCReader.a(8, "VUI: colour_primaries");
                vUIParameters.k = (int) cAVLCReader.a(8, "VUI: transfer_characteristics");
                vUIParameters.l = (int) cAVLCReader.a(8, "VUI: matrix_coefficients");
            }
        }
        vUIParameters.m = cAVLCReader.c("VUI: chroma_loc_info_present_flag");
        if (vUIParameters.m) {
            vUIParameters.n = cAVLCReader.a("VUI chroma_sample_loc_type_top_field");
            vUIParameters.o = cAVLCReader.a("VUI chroma_sample_loc_type_bottom_field");
        }
        vUIParameters.p = cAVLCReader.c("VUI: timing_info_present_flag");
        if (vUIParameters.p) {
            vUIParameters.q = (int) cAVLCReader.a(32, "VUI: num_units_in_tick");
            vUIParameters.r = (int) cAVLCReader.a(32, "VUI: time_scale");
            vUIParameters.s = cAVLCReader.c("VUI: fixed_frame_rate_flag");
        }
        boolean c2 = cAVLCReader.c("VUI: nal_hrd_parameters_present_flag");
        if (c2) {
            vUIParameters.v = b(cAVLCReader);
        }
        boolean c3 = cAVLCReader.c("VUI: vcl_hrd_parameters_present_flag");
        if (c3) {
            vUIParameters.w = b(cAVLCReader);
        }
        if (c2 || c3) {
            vUIParameters.t = cAVLCReader.c("VUI: low_delay_hrd_flag");
        }
        vUIParameters.u = cAVLCReader.c("VUI: pic_struct_present_flag");
        if (cAVLCReader.c("VUI: bitstream_restriction_flag")) {
            vUIParameters.x = new VUIParameters.BitstreamRestriction();
            vUIParameters.x.f4045a = cAVLCReader.c("VUI: motion_vectors_over_pic_boundaries_flag");
            vUIParameters.x.b = cAVLCReader.a("VUI max_bytes_per_pic_denom");
            vUIParameters.x.c = cAVLCReader.a("VUI max_bits_per_mb_denom");
            vUIParameters.x.d = cAVLCReader.a("VUI log2_max_mv_length_horizontal");
            vUIParameters.x.e = cAVLCReader.a("VUI log2_max_mv_length_vertical");
            vUIParameters.x.f = cAVLCReader.a("VUI num_reorder_frames");
            vUIParameters.x.g = cAVLCReader.a("VUI max_dec_frame_buffering");
        }
        return vUIParameters;
    }

    private static HRDParameters b(CAVLCReader cAVLCReader) throws IOException {
        HRDParameters hRDParameters = new HRDParameters();
        hRDParameters.f4038a = cAVLCReader.a("SPS: cpb_cnt_minus1");
        hRDParameters.b = (int) cAVLCReader.a(4, "HRD: bit_rate_scale");
        hRDParameters.c = (int) cAVLCReader.a(4, "HRD: cpb_size_scale");
        hRDParameters.d = new int[(hRDParameters.f4038a + 1)];
        hRDParameters.e = new int[(hRDParameters.f4038a + 1)];
        hRDParameters.f = new boolean[(hRDParameters.f4038a + 1)];
        for (int i2 = 0; i2 <= hRDParameters.f4038a; i2++) {
            hRDParameters.d[i2] = cAVLCReader.a("HRD: bit_rate_value_minus1");
            hRDParameters.e[i2] = cAVLCReader.a("HRD: cpb_size_value_minus1");
            hRDParameters.f[i2] = cAVLCReader.c("HRD: cbr_flag");
        }
        hRDParameters.g = (int) cAVLCReader.a(5, "HRD: initial_cpb_removal_delay_length_minus1");
        hRDParameters.h = (int) cAVLCReader.a(5, "HRD: cpb_removal_delay_length_minus1");
        hRDParameters.i = (int) cAVLCReader.a(5, "HRD: dpb_output_delay_length_minus1");
        hRDParameters.j = (int) cAVLCReader.a(5, "HRD: time_offset_length");
        return hRDParameters;
    }

    public void a(OutputStream outputStream) throws IOException {
        CAVLCWriter cAVLCWriter = new CAVLCWriter(outputStream);
        cAVLCWriter.a((long) this.q, 8, "SPS: profile_idc");
        cAVLCWriter.a(this.s, "SPS: constraint_set_0_flag");
        cAVLCWriter.a(this.t, "SPS: constraint_set_1_flag");
        cAVLCWriter.a(this.u, "SPS: constraint_set_2_flag");
        cAVLCWriter.a(this.v, "SPS: constraint_set_3_flag");
        cAVLCWriter.a(0, 4, "SPS: reserved");
        cAVLCWriter.a((long) this.y, 8, "SPS: level_idc");
        cAVLCWriter.a(this.z, "SPS: seq_parameter_set_id");
        boolean z2 = false;
        if (this.q == 100 || this.q == 110 || this.q == 122 || this.q == 144) {
            cAVLCWriter.a(this.i.a(), "SPS: chroma_format_idc");
            if (this.i == ChromaFormat.d) {
                cAVLCWriter.a(this.A, "SPS: residual_color_transform_flag");
            }
            cAVLCWriter.a(this.n, "SPS: ");
            cAVLCWriter.a(this.o, "SPS: ");
            cAVLCWriter.a(this.p, "SPS: qpprime_y_zero_transform_bypass_flag");
            cAVLCWriter.a(this.N != null, "SPS: ");
            if (this.N != null) {
                for (int i2 = 0; i2 < 8; i2++) {
                    if (i2 < 6) {
                        cAVLCWriter.a(this.N.f4042a[i2] != null, "SPS: ");
                        if (this.N.f4042a[i2] != null) {
                            this.N.f4042a[i2].a(cAVLCWriter);
                        }
                    } else {
                        int i3 = i2 - 6;
                        cAVLCWriter.a(this.N.b[i3] != null, "SPS: ");
                        if (this.N.b[i3] != null) {
                            this.N.b[i3].a(cAVLCWriter);
                        }
                    }
                }
            }
        }
        cAVLCWriter.a(this.j, "SPS: log2_max_frame_num_minus4");
        cAVLCWriter.a(this.f4043a, "SPS: pic_order_cnt_type");
        if (this.f4043a == 0) {
            cAVLCWriter.a(this.k, "SPS: log2_max_pic_order_cnt_lsb_minus4");
        } else if (this.f4043a == 1) {
            cAVLCWriter.a(this.c, "SPS: delta_pic_order_always_zero_flag");
            cAVLCWriter.b(this.B, "SPS: offset_for_non_ref_pic");
            cAVLCWriter.b(this.C, "SPS: offset_for_top_to_bottom_field");
            cAVLCWriter.a(this.L.length, "SPS: ");
            for (int b2 : this.L) {
                cAVLCWriter.b(b2, "SPS: ");
            }
        }
        cAVLCWriter.a(this.D, "SPS: num_ref_frames");
        cAVLCWriter.a(this.E, "SPS: gaps_in_frame_num_value_allowed_flag");
        cAVLCWriter.a(this.m, "SPS: pic_width_in_mbs_minus1");
        cAVLCWriter.a(this.l, "SPS: pic_height_in_map_units_minus1");
        cAVLCWriter.a(this.F, "SPS: frame_mbs_only_flag");
        if (!this.F) {
            cAVLCWriter.a(this.g, "SPS: mb_adaptive_frame_field_flag");
        }
        cAVLCWriter.a(this.h, "SPS: direct_8x8_inference_flag");
        cAVLCWriter.a(this.G, "SPS: frame_cropping_flag");
        if (this.G) {
            cAVLCWriter.a(this.H, "SPS: frame_crop_left_offset");
            cAVLCWriter.a(this.I, "SPS: frame_crop_right_offset");
            cAVLCWriter.a(this.J, "SPS: frame_crop_top_offset");
            cAVLCWriter.a(this.K, "SPS: frame_crop_bottom_offset");
        }
        if (this.M != null) {
            z2 = true;
        }
        cAVLCWriter.a(z2, "SPS: ");
        if (this.M != null) {
            a(this.M, cAVLCWriter);
        }
        cAVLCWriter.c();
    }

    private void a(VUIParameters vUIParameters, CAVLCWriter cAVLCWriter) throws IOException {
        cAVLCWriter.a(vUIParameters.f4044a, "VUI: aspect_ratio_info_present_flag");
        if (vUIParameters.f4044a) {
            cAVLCWriter.a((long) vUIParameters.y.a(), 8, "VUI: aspect_ratio");
            if (vUIParameters.y == AspectRatio.f4036a) {
                cAVLCWriter.a((long) vUIParameters.b, 16, "VUI: sar_width");
                cAVLCWriter.a((long) vUIParameters.c, 16, "VUI: sar_height");
            }
        }
        cAVLCWriter.a(vUIParameters.d, "VUI: overscan_info_present_flag");
        if (vUIParameters.d) {
            cAVLCWriter.a(vUIParameters.e, "VUI: overscan_appropriate_flag");
        }
        cAVLCWriter.a(vUIParameters.f, "VUI: video_signal_type_present_flag");
        if (vUIParameters.f) {
            cAVLCWriter.a((long) vUIParameters.g, 3, "VUI: video_format");
            cAVLCWriter.a(vUIParameters.h, "VUI: video_full_range_flag");
            cAVLCWriter.a(vUIParameters.i, "VUI: colour_description_present_flag");
            if (vUIParameters.i) {
                cAVLCWriter.a((long) vUIParameters.j, 8, "VUI: colour_primaries");
                cAVLCWriter.a((long) vUIParameters.k, 8, "VUI: transfer_characteristics");
                cAVLCWriter.a((long) vUIParameters.l, 8, "VUI: matrix_coefficients");
            }
        }
        cAVLCWriter.a(vUIParameters.m, "VUI: chroma_loc_info_present_flag");
        if (vUIParameters.m) {
            cAVLCWriter.a(vUIParameters.n, "VUI: chroma_sample_loc_type_top_field");
            cAVLCWriter.a(vUIParameters.o, "VUI: chroma_sample_loc_type_bottom_field");
        }
        cAVLCWriter.a(vUIParameters.p, "VUI: timing_info_present_flag");
        if (vUIParameters.p) {
            cAVLCWriter.a((long) vUIParameters.q, 32, "VUI: num_units_in_tick");
            cAVLCWriter.a((long) vUIParameters.r, 32, "VUI: time_scale");
            cAVLCWriter.a(vUIParameters.s, "VUI: fixed_frame_rate_flag");
        }
        boolean z2 = false;
        cAVLCWriter.a(vUIParameters.v != null, "VUI: ");
        if (vUIParameters.v != null) {
            a(vUIParameters.v, cAVLCWriter);
        }
        cAVLCWriter.a(vUIParameters.w != null, "VUI: ");
        if (vUIParameters.w != null) {
            a(vUIParameters.w, cAVLCWriter);
        }
        if (!(vUIParameters.v == null && vUIParameters.w == null)) {
            cAVLCWriter.a(vUIParameters.t, "VUI: low_delay_hrd_flag");
        }
        cAVLCWriter.a(vUIParameters.u, "VUI: pic_struct_present_flag");
        if (vUIParameters.x != null) {
            z2 = true;
        }
        cAVLCWriter.a(z2, "VUI: ");
        if (vUIParameters.x != null) {
            cAVLCWriter.a(vUIParameters.x.f4045a, "VUI: motion_vectors_over_pic_boundaries_flag");
            cAVLCWriter.a(vUIParameters.x.b, "VUI: max_bytes_per_pic_denom");
            cAVLCWriter.a(vUIParameters.x.c, "VUI: max_bits_per_mb_denom");
            cAVLCWriter.a(vUIParameters.x.d, "VUI: log2_max_mv_length_horizontal");
            cAVLCWriter.a(vUIParameters.x.e, "VUI: log2_max_mv_length_vertical");
            cAVLCWriter.a(vUIParameters.x.f, "VUI: num_reorder_frames");
            cAVLCWriter.a(vUIParameters.x.g, "VUI: max_dec_frame_buffering");
        }
    }

    private void a(HRDParameters hRDParameters, CAVLCWriter cAVLCWriter) throws IOException {
        cAVLCWriter.a(hRDParameters.f4038a, "HRD: cpb_cnt_minus1");
        cAVLCWriter.a((long) hRDParameters.b, 4, "HRD: bit_rate_scale");
        cAVLCWriter.a((long) hRDParameters.c, 4, "HRD: cpb_size_scale");
        for (int i2 = 0; i2 <= hRDParameters.f4038a; i2++) {
            cAVLCWriter.a(hRDParameters.d[i2], "HRD: ");
            cAVLCWriter.a(hRDParameters.e[i2], "HRD: ");
            cAVLCWriter.a(hRDParameters.f[i2], "HRD: ");
        }
        cAVLCWriter.a((long) hRDParameters.g, 5, "HRD: initial_cpb_removal_delay_length_minus1");
        cAVLCWriter.a((long) hRDParameters.h, 5, "HRD: cpb_removal_delay_length_minus1");
        cAVLCWriter.a((long) hRDParameters.i, 5, "HRD: dpb_output_delay_length_minus1");
        cAVLCWriter.a((long) hRDParameters.j, 5, "HRD: time_offset_length");
    }

    public String toString() {
        return "SeqParameterSet{ \n        pic_order_cnt_type=" + this.f4043a + ", \n        field_pic_flag=" + this.b + ", \n        delta_pic_order_always_zero_flag=" + this.c + ", \n        weighted_pred_flag=" + this.d + ", \n        weighted_bipred_idc=" + this.e + ", \n        entropy_coding_mode_flag=" + this.f + ", \n        mb_adaptive_frame_field_flag=" + this.g + ", \n        direct_8x8_inference_flag=" + this.h + ", \n        chroma_format_idc=" + this.i + ", \n        log2_max_frame_num_minus4=" + this.j + ", \n        log2_max_pic_order_cnt_lsb_minus4=" + this.k + ", \n        pic_height_in_map_units_minus1=" + this.l + ", \n        pic_width_in_mbs_minus1=" + this.m + ", \n        bit_depth_luma_minus8=" + this.n + ", \n        bit_depth_chroma_minus8=" + this.o + ", \n        qpprime_y_zero_transform_bypass_flag=" + this.p + ", \n        profile_idc=" + this.q + ", \n        constraint_set_0_flag=" + this.s + ", \n        constraint_set_1_flag=" + this.t + ", \n        constraint_set_2_flag=" + this.u + ", \n        constraint_set_3_flag=" + this.v + ", \n        constraint_set_4_flag=" + this.w + ", \n        constraint_set_5_flag=" + this.x + ", \n        level_idc=" + this.y + ", \n        seq_parameter_set_id=" + this.z + ", \n        residual_color_transform_flag=" + this.A + ", \n        offset_for_non_ref_pic=" + this.B + ", \n        offset_for_top_to_bottom_field=" + this.C + ", \n        num_ref_frames=" + this.D + ", \n        gaps_in_frame_num_value_allowed_flag=" + this.E + ", \n        frame_mbs_only_flag=" + this.F + ", \n        frame_cropping_flag=" + this.G + ", \n        frame_crop_left_offset=" + this.H + ", \n        frame_crop_right_offset=" + this.I + ", \n        frame_crop_top_offset=" + this.J + ", \n        frame_crop_bottom_offset=" + this.K + ", \n        offsetForRefFrame=" + this.L + ", \n        vuiParams=" + this.M + ", \n        scalingMatrix=" + this.N + ", \n        num_ref_frames_in_pic_order_cnt_cycle=" + this.O + Operators.BLOCK_END;
    }
}
