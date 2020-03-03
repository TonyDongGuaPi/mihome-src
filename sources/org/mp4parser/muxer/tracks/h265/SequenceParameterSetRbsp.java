package org.mp4parser.muxer.tracks.h265;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader;

public class SequenceParameterSetRbsp {
    public SequenceParameterSetRbsp(InputStream inputStream) throws IOException {
        CAVLCReader cAVLCReader = new CAVLCReader(inputStream);
        cAVLCReader.a(4, "sps_video_parameter_set_id");
        int a2 = (int) cAVLCReader.a(3, "sps_max_sub_layers_minus1");
        cAVLCReader.c("sps_temporal_id_nesting_flag");
        a(a2, cAVLCReader);
        cAVLCReader.a("sps_seq_parameter_set_id");
        if (cAVLCReader.a("chroma_format_idc") == 3) {
            cAVLCReader.b();
            cAVLCReader.a("pic_width_in_luma_samples");
            cAVLCReader.a("pic_width_in_luma_samples");
            if (cAVLCReader.c("conformance_window_flag")) {
                cAVLCReader.a("conf_win_left_offset");
                cAVLCReader.a("conf_win_right_offset");
                cAVLCReader.a("conf_win_top_offset");
                cAVLCReader.a("conf_win_bottom_offset");
            }
        }
        cAVLCReader.a("bit_depth_luma_minus8");
        cAVLCReader.a("bit_depth_chroma_minus8");
        cAVLCReader.a("log2_max_pic_order_cnt_lsb_minus4");
        boolean c = cAVLCReader.c("sps_sub_layer_ordering_info_present_flag");
        int i = 0;
        int i2 = (a2 - (c ? 0 : a2)) + 1;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        for (i = !c ? a2 : i; i <= a2; i++) {
            iArr[i] = cAVLCReader.a("sps_max_dec_pic_buffering_minus1[" + i + Operators.ARRAY_END_STR);
            iArr2[i] = cAVLCReader.a("sps_max_num_reorder_pics[" + i + Operators.ARRAY_END_STR);
            iArr3[i] = cAVLCReader.a("sps_max_latency_increase_plus1[" + i + Operators.ARRAY_END_STR);
        }
        cAVLCReader.a("log2_min_luma_coding_block_size_minus3");
        cAVLCReader.a("log2_diff_max_min_luma_coding_block_size");
        cAVLCReader.a("log2_min_transform_block_size_minus2");
        cAVLCReader.a("log2_diff_max_min_transform_block_size");
        cAVLCReader.a("max_transform_hierarchy_depth_inter");
        cAVLCReader.a("max_transform_hierarchy_depth_intra");
        if (cAVLCReader.c("scaling_list_enabled_flag") && cAVLCReader.c("sps_scaling_list_data_present_flag")) {
            a(cAVLCReader);
        }
        cAVLCReader.c("amp_enabled_flag");
        cAVLCReader.c("sample_adaptive_offset_enabled_flag");
        if (cAVLCReader.c("pcm_enabled_flag")) {
            cAVLCReader.a(4, "pcm_sample_bit_depth_luma_minus1");
            cAVLCReader.a(4, "pcm_sample_bit_depth_chroma_minus1");
            cAVLCReader.a("log2_min_pcm_luma_coding_block_size_minus3");
        }
    }

    private void a(CAVLCReader cAVLCReader) throws IOException {
        CAVLCReader cAVLCReader2 = cAVLCReader;
        boolean[][] zArr = new boolean[4][];
        int[][] iArr = new int[4][];
        int[][] iArr2 = new int[2][];
        int[][][] iArr3 = new int[4][][];
        int i = 0;
        while (i < 4) {
            int i2 = 0;
            while (true) {
                int i3 = 6;
                if (i2 >= (i == 3 ? 2 : 6)) {
                    break;
                }
                zArr[i] = new boolean[(i == 3 ? 2 : 6)];
                iArr[i] = new int[(i == 3 ? 2 : 6)];
                if (i == 3) {
                    i3 = 2;
                }
                iArr3[i] = new int[i3][];
                zArr[i][i2] = cAVLCReader.a();
                if (!zArr[i][i2]) {
                    iArr[i][i2] = cAVLCReader2.a("scaling_list_pred_matrix_id_delta[" + i + "][" + i2 + Operators.ARRAY_END_STR);
                } else {
                    int min = Math.min(64, 1 << ((i << 1) + 4));
                    int i4 = 8;
                    if (i > 1) {
                        int i5 = i - 2;
                        iArr2[i5][i2] = cAVLCReader2.b("scaling_list_dc_coef_minus8[" + i + "- 2][" + i2 + Operators.ARRAY_END_STR);
                        i4 = 8 + iArr2[i5][i2];
                    }
                    iArr3[i][i2] = new int[min];
                    int i6 = i4;
                    for (int i7 = 0; i7 < min; i7++) {
                        i6 = ((i6 + cAVLCReader2.b("scaling_list_delta_coef ")) + 256) % 256;
                        iArr3[i][i2][i7] = i6;
                    }
                }
                i2++;
            }
            i++;
        }
    }

    private void a(int i, CAVLCReader cAVLCReader) throws IOException {
        boolean[] zArr;
        int i2 = i;
        CAVLCReader cAVLCReader2 = cAVLCReader;
        cAVLCReader2.b(2, "general_profile_space");
        cAVLCReader2.c("general_tier_flag");
        cAVLCReader2.b(5, "general_profile_idc");
        boolean[] zArr2 = new boolean[32];
        for (int i3 = 0; i3 < 32; i3++) {
            zArr2[i3] = cAVLCReader.a();
        }
        cAVLCReader2.c("general_progressive_source_flag");
        cAVLCReader2.c("general_interlaced_source_flag");
        cAVLCReader2.c("general_non_packed_constraint_flag");
        cAVLCReader2.c("general_frame_only_constraint_flag");
        cAVLCReader2.a(44, "general_reserved_zero_44bits");
        cAVLCReader.c();
        boolean[] zArr3 = new boolean[i2];
        boolean[] zArr4 = new boolean[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            zArr3[i4] = cAVLCReader2.c("sub_layer_profile_present_flag[" + i4 + Operators.ARRAY_END_STR);
            zArr4[i4] = cAVLCReader2.c("sub_layer_level_present_flag[" + i4 + Operators.ARRAY_END_STR);
        }
        if (i2 > 0) {
            int[] iArr = new int[8];
            for (int i5 = i2; i5 < 8; i5++) {
                iArr[i5] = cAVLCReader2.b(2, "reserved_zero_2bits[" + i5 + Operators.ARRAY_END_STR);
            }
        }
        int[] iArr2 = new int[i2];
        boolean[] zArr5 = new boolean[i2];
        int[] iArr3 = new int[i2];
        boolean[][] zArr6 = (boolean[][]) Array.newInstance(boolean.class, new int[]{i2, 32});
        boolean[] zArr7 = new boolean[i2];
        boolean[] zArr8 = new boolean[i2];
        boolean[] zArr9 = new boolean[i2];
        boolean[] zArr10 = new boolean[i2];
        long[] jArr = new long[i2];
        int[] iArr4 = new int[i2];
        int i6 = 0;
        while (i6 < i2) {
            if (zArr3[i6]) {
                iArr2[i6] = cAVLCReader2.b(2, "sub_layer_profile_space[" + i6 + Operators.ARRAY_END_STR);
                zArr5[i6] = cAVLCReader2.c("sub_layer_tier_flag[" + i6 + Operators.ARRAY_END_STR);
                iArr3[i6] = cAVLCReader2.b(5, "sub_layer_profile_idc[" + i6 + Operators.ARRAY_END_STR);
                int i7 = 0;
                while (i7 < 32) {
                    boolean[] zArr11 = zArr6[i6];
                    zArr11[i7] = cAVLCReader2.c("sub_layer_profile_compatibility_flag[" + i6 + "][" + i7 + Operators.ARRAY_END_STR);
                    i7++;
                    zArr3 = zArr3;
                }
                zArr = zArr3;
                zArr7[i6] = cAVLCReader2.c("sub_layer_progressive_source_flag[" + i6 + Operators.ARRAY_END_STR);
                zArr8[i6] = cAVLCReader2.c("sub_layer_interlaced_source_flag[" + i6 + Operators.ARRAY_END_STR);
                zArr9[i6] = cAVLCReader2.c("sub_layer_non_packed_constraint_flag[" + i6 + Operators.ARRAY_END_STR);
                zArr10[i6] = cAVLCReader2.c("sub_layer_frame_only_constraint_flag[" + i6 + Operators.ARRAY_END_STR);
                jArr[i6] = cAVLCReader2.a(44);
            } else {
                zArr = zArr3;
            }
            if (zArr4[i6]) {
                iArr4[i6] = cAVLCReader2.b(8, "sub_layer_level_idc[" + i6 + Operators.ARRAY_END_STR);
            }
            i6++;
            zArr3 = zArr;
            i2 = i;
        }
    }
}
