package org.mp4parser.muxer.tracks.h265;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader;
import org.mp4parser.tools.ByteBufferByteChannel;

public class VideoParameterSet {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f4053a;
    int b;

    public VideoParameterSet(ByteBuffer byteBuffer) throws IOException {
        this.f4053a = byteBuffer;
        CAVLCReader cAVLCReader = new CAVLCReader(Channels.newInputStream(new ByteBufferByteChannel((ByteBuffer) byteBuffer.position(0))));
        this.b = cAVLCReader.b(4, "vps_parameter_set_id");
        cAVLCReader.b(2, "vps_reserved_three_2bits");
        cAVLCReader.b(6, "vps_max_layers_minus1");
        int b2 = cAVLCReader.b(3, "vps_max_sub_layers_minus1");
        cAVLCReader.c("vps_temporal_id_nesting_flag");
        cAVLCReader.b(16, "vps_reserved_0xffff_16bits");
        a(b2, cAVLCReader);
        boolean c = cAVLCReader.c("vps_sub_layer_ordering_info_present_flag");
        int[] iArr = new int[(c ? 1 : b2 + 1)];
        int[] iArr2 = new int[(c ? 1 : b2 + 1)];
        int[] iArr3 = new int[(c ? 1 : b2 + 1)];
        for (int i = c ? 0 : b2; i <= b2; i++) {
            iArr[i] = cAVLCReader.a("vps_max_dec_pic_buffering_minus1[" + i + Operators.ARRAY_END_STR);
            iArr2[i] = cAVLCReader.a("vps_max_dec_pic_buffering_minus1[" + i + Operators.ARRAY_END_STR);
            iArr3[i] = cAVLCReader.a("vps_max_dec_pic_buffering_minus1[" + i + Operators.ARRAY_END_STR);
        }
        int b3 = cAVLCReader.b(6, "vps_max_layer_id");
        int a2 = cAVLCReader.a("vps_num_layer_sets_minus1");
        boolean[][] zArr = (boolean[][]) Array.newInstance(boolean.class, new int[]{a2, b3});
        for (int i2 = 1; i2 <= a2; i2++) {
            for (int i3 = 0; i3 <= b3; i3++) {
                boolean[] zArr2 = zArr[i2];
                zArr2[i3] = cAVLCReader.c("layer_id_included_flag[" + i2 + "][" + i3 + Operators.ARRAY_END_STR);
            }
        }
        if (cAVLCReader.c("vps_timing_info_present_flag")) {
            cAVLCReader.b(32, "vps_num_units_in_tick");
            cAVLCReader.b(32, "vps_time_scale");
            if (cAVLCReader.c("vps_poc_proportional_to_timing_flag")) {
                cAVLCReader.a("vps_num_ticks_poc_diff_one_minus1");
            }
            int a3 = cAVLCReader.a("vps_num_hrd_parameters");
            int[] iArr4 = new int[a3];
            boolean[] zArr3 = new boolean[a3];
            for (int i4 = 0; i4 < a3; i4++) {
                iArr4[i4] = cAVLCReader.a("hrd_layer_set_idx[" + i4 + Operators.ARRAY_END_STR);
                if (i4 > 0) {
                    zArr3[i4] = cAVLCReader.c("cprms_present_flag[" + i4 + Operators.ARRAY_END_STR);
                } else {
                    zArr3[0] = true;
                }
                a(zArr3[i4], b2, cAVLCReader);
            }
        }
        if (cAVLCReader.c("vps_extension_flag")) {
            while (cAVLCReader.d()) {
                cAVLCReader.c("vps_extension_data_flag");
            }
        }
        cAVLCReader.l();
    }

    public void a(int i, CAVLCReader cAVLCReader) throws IOException {
        int i2 = i;
        CAVLCReader cAVLCReader2 = cAVLCReader;
        cAVLCReader2.b(2, "general_profile_space ");
        cAVLCReader2.c("general_tier_flag");
        cAVLCReader2.b(5, "general_profile_idc");
        boolean[] zArr = new boolean[32];
        for (int i3 = 0; i3 < 32; i3++) {
            zArr[i3] = cAVLCReader2.c("general_profile_compatibility_flag[" + i3 + Operators.ARRAY_END_STR);
        }
        cAVLCReader2.c("general_progressive_source_flag");
        cAVLCReader2.c("general_interlaced_source_flag");
        cAVLCReader2.c("general_non_packed_constraint_flag");
        cAVLCReader2.c("general_frame_only_constraint_flag");
        cAVLCReader2.b(44, "general_reserved_zero_44bits");
        cAVLCReader2.b(8, "general_level_idc");
        boolean[] zArr2 = new boolean[i2];
        boolean[] zArr3 = new boolean[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            zArr2[i4] = cAVLCReader2.c("sub_layer_profile_present_flag[" + i4 + Operators.ARRAY_END_STR);
            zArr3[i4] = cAVLCReader2.c("sub_layer_level_present_flag[" + i4 + Operators.ARRAY_END_STR);
        }
        if (i2 > 0) {
            for (int i5 = i2; i5 < 8; i5++) {
                cAVLCReader2.b(2, "reserved_zero_2bits");
            }
        }
        int[] iArr = new int[i2];
        boolean[] zArr4 = new boolean[i2];
        int[] iArr2 = new int[i2];
        boolean[][] zArr5 = (boolean[][]) Array.newInstance(boolean.class, new int[]{i2, 32});
        boolean[] zArr6 = new boolean[i2];
        boolean[] zArr7 = new boolean[i2];
        boolean[] zArr8 = new boolean[i2];
        boolean[] zArr9 = new boolean[i2];
        int[] iArr3 = new int[i2];
        int i6 = 0;
        while (i6 < i2) {
            if (zArr2[i6]) {
                iArr[i6] = cAVLCReader2.b(2, "sub_layer_profile_space[" + i6 + Operators.ARRAY_END_STR);
                zArr4[i6] = cAVLCReader2.c("sub_layer_tier_flag[" + i6 + Operators.ARRAY_END_STR);
                iArr2[i6] = cAVLCReader2.b(5, "sub_layer_profile_idc[" + i6 + Operators.ARRAY_END_STR);
                int i7 = 0;
                while (i7 < 32) {
                    boolean[] zArr10 = zArr5[i6];
                    zArr10[i7] = cAVLCReader2.c("sub_layer_profile_compatibility_flag[" + i6 + "][" + i7 + Operators.ARRAY_END_STR);
                    i7++;
                    int i8 = i;
                }
                zArr6[i6] = cAVLCReader2.c("sub_layer_progressive_source_flag[" + i6 + Operators.ARRAY_END_STR);
                zArr7[i6] = cAVLCReader2.c("sub_layer_interlaced_source_flag[" + i6 + Operators.ARRAY_END_STR);
                zArr8[i6] = cAVLCReader2.c("sub_layer_non_packed_constraint_flag[" + i6 + Operators.ARRAY_END_STR);
                zArr9[i6] = cAVLCReader2.c("sub_layer_frame_only_constraint_flag[" + i6 + Operators.ARRAY_END_STR);
                cAVLCReader2.a(44, "reserved");
            }
            if (zArr3[i6]) {
                iArr3[i6] = cAVLCReader2.b(8, "sub_layer_level_idc");
            }
            i6++;
            i2 = i;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r11, int r12, org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader r13) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
            if (r11 == 0) goto L_0x0054
            java.lang.String r11 = "nal_hrd_parameters_present_flag"
            boolean r11 = r13.c((java.lang.String) r11)
            java.lang.String r1 = "vcl_hrd_parameters_present_flag"
            boolean r1 = r13.c((java.lang.String) r1)
            if (r11 != 0) goto L_0x0013
            if (r1 == 0) goto L_0x0056
        L_0x0013:
            java.lang.String r2 = "sub_pic_hrd_params_present_flag"
            boolean r2 = r13.c((java.lang.String) r2)
            r3 = 5
            if (r2 == 0) goto L_0x0032
            r4 = 8
            java.lang.String r5 = "tick_divisor_minus2"
            r13.b(r4, r5)
            java.lang.String r4 = "du_cpb_removal_delay_increment_length_minus1"
            r13.b(r3, r4)
            java.lang.String r4 = "sub_pic_cpb_params_in_pic_timing_sei_flag"
            r13.c((java.lang.String) r4)
            java.lang.String r4 = "dpb_output_delay_du_length_minus1"
            r13.b(r3, r4)
        L_0x0032:
            java.lang.String r4 = "bit_rate_scale"
            r5 = 4
            r13.b(r5, r4)
            java.lang.String r4 = "cpb_size_scale"
            r13.b(r5, r4)
            if (r2 == 0) goto L_0x0044
            java.lang.String r4 = "cpb_size_du_scale"
            r13.b(r5, r4)
        L_0x0044:
            java.lang.String r4 = "initial_cpb_removal_delay_length_minus1"
            r13.b(r3, r4)
            java.lang.String r4 = "au_cpb_removal_delay_length_minus1"
            r13.b(r3, r4)
            java.lang.String r4 = "dpb_output_delay_length_minus1"
            r13.b(r3, r4)
            goto L_0x0057
        L_0x0054:
            r11 = 0
            r1 = 0
        L_0x0056:
            r2 = 0
        L_0x0057:
            boolean[] r3 = new boolean[r12]
            boolean[] r4 = new boolean[r12]
            boolean[] r5 = new boolean[r12]
            int[] r6 = new int[r12]
            int[] r7 = new int[r12]
        L_0x0061:
            if (r0 > r12) goto L_0x010e
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "fixed_pic_rate_general_flag["
            r8.append(r9)
            r8.append(r0)
            java.lang.String r9 = "]"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            boolean r8 = r13.c((java.lang.String) r8)
            r3[r0] = r8
            boolean r8 = r3[r0]
            if (r8 != 0) goto L_0x009f
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "fixed_pic_rate_within_cvs_flag["
            r8.append(r9)
            r8.append(r0)
            java.lang.String r9 = "]"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            boolean r8 = r13.c((java.lang.String) r8)
            r4[r0] = r8
        L_0x009f:
            boolean r8 = r4[r0]
            if (r8 == 0) goto L_0x00c0
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "elemental_duration_in_tc_minus1["
            r8.append(r9)
            r8.append(r0)
            java.lang.String r9 = "]"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            int r8 = r13.a(r8)
            r7[r0] = r8
            goto L_0x00dc
        L_0x00c0:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "low_delay_hrd_flag["
            r8.append(r9)
            r8.append(r0)
            java.lang.String r9 = "]"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            boolean r8 = r13.c((java.lang.String) r8)
            r5[r0] = r8
        L_0x00dc:
            boolean r8 = r5[r0]
            if (r8 != 0) goto L_0x00fc
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "cpb_cnt_minus1["
            r8.append(r9)
            r8.append(r0)
            java.lang.String r9 = "]"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            int r8 = r13.a(r8)
            r6[r0] = r8
        L_0x00fc:
            if (r11 == 0) goto L_0x0103
            r8 = r6[r0]
            r10.a(r0, r8, r2, r13)
        L_0x0103:
            if (r1 == 0) goto L_0x010a
            r8 = r6[r0]
            r10.a(r0, r8, r2, r13)
        L_0x010a:
            int r0 = r0 + 1
            goto L_0x0061
        L_0x010e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.muxer.tracks.h265.VideoParameterSet.a(boolean, int, org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader):void");
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2, boolean z, CAVLCReader cAVLCReader) throws IOException {
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        int[] iArr4 = new int[i2];
        boolean[] zArr = new boolean[i2];
        for (int i3 = 0; i3 <= i2; i3++) {
            iArr[i3] = cAVLCReader.a("bit_rate_value_minus1[" + i3 + Operators.ARRAY_END_STR);
            iArr2[i3] = cAVLCReader.a("cpb_size_value_minus1[" + i3 + Operators.ARRAY_END_STR);
            if (z) {
                iArr3[i3] = cAVLCReader.a("cpb_size_du_value_minus1[" + i3 + Operators.ARRAY_END_STR);
                iArr4[i3] = cAVLCReader.a("bit_rate_du_value_minus1[" + i3 + Operators.ARRAY_END_STR);
            }
            zArr[i3] = cAVLCReader.c("cbr_flag[" + i3 + Operators.ARRAY_END_STR);
        }
    }

    public ByteBuffer a() {
        return this.f4053a;
    }
}
