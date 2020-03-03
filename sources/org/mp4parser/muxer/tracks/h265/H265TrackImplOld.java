package org.mp4parser.muxer.tracks.h265;

import com.taobao.weex.el.parse.Operators;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.mp4parser.boxes.iso14496.part15.HevcDecoderConfigurationRecord;
import org.mp4parser.muxer.DataSource;
import org.mp4parser.muxer.FileDataSourceImpl;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader;
import org.mp4parser.tools.ByteBufferByteChannel;
import org.mp4parser.tools.IsoTypeReader;

public class H265TrackImplOld {
    private static final int A = 4;
    private static final int B = 5;
    private static final int C = 6;
    private static final int D = 7;
    private static final int E = 8;
    private static final int F = 9;
    private static final int G = 16;
    private static final int H = 17;
    private static final int I = 18;
    private static final int J = 19;
    private static final int K = 20;
    private static final int L = 21;
    private static final long M = 1048576;

    /* renamed from: a  reason: collision with root package name */
    public static final int f4050a = 32;
    public static final int b = 33;
    public static final int c = 34;
    public static final int d = 35;
    public static final int e = 39;
    public static final int f = 41;
    public static final int g = 42;
    public static final int h = 43;
    public static final int i = 44;
    public static final int j = 48;
    public static final int k = 49;
    public static final int l = 50;
    public static final int m = 51;
    public static final int n = 52;
    public static final int o = 53;
    public static final int p = 54;
    public static final int q = 55;
    private static final int w = 0;
    private static final int x = 1;
    private static final int y = 2;
    private static final int z = 3;
    LinkedHashMap<Long, ByteBuffer> r = new LinkedHashMap<>();
    LinkedHashMap<Long, ByteBuffer> s = new LinkedHashMap<>();
    LinkedHashMap<Long, ByteBuffer> t = new LinkedHashMap<>();
    List<Long> u = new ArrayList();
    List<Sample> v = new ArrayList();

    public static class NalUnitHeader {

        /* renamed from: a  reason: collision with root package name */
        int f4052a;
        int b;
        int c;
        int d;
    }

    public enum PARSE_STATE {
        AUD_SEI_SLICE,
        SEI_SLICE,
        SLICE_OES_EOB
    }

    public H265TrackImplOld(DataSource dataSource) throws IOException {
        LookAhead lookAhead = new LookAhead(dataSource);
        ArrayList<ByteBuffer> arrayList = new ArrayList<>();
        long j2 = 1;
        int i2 = 0;
        while (true) {
            ByteBuffer a2 = a(lookAhead);
            if (a2 != null) {
                NalUnitHeader b2 = b(a2);
                switch (b2.b) {
                    case 32:
                        this.r.put(Long.valueOf(j2), a2);
                        break;
                    case 33:
                        this.s.put(Long.valueOf(j2), a2);
                        break;
                    case 34:
                        this.t.put(Long.valueOf(j2), a2);
                        break;
                }
                i2 = b2.b < 32 ? b2.b : i2;
                if (a(b2.b, a2, (List<ByteBuffer>) arrayList) && !arrayList.isEmpty()) {
                    System.err.println("##########################");
                    for (ByteBuffer byteBuffer : arrayList) {
                        NalUnitHeader b3 = b(byteBuffer);
                        System.err.println(String.format("type: %3d - layer: %3d - tempId: %3d - size: %3d", new Object[]{Integer.valueOf(b3.b), Integer.valueOf(b3.c), Integer.valueOf(b3.d), Integer.valueOf(byteBuffer.limit())}));
                    }
                    System.err.println("                          ##########################");
                    this.v.add(a((List<ByteBuffer>) arrayList));
                    arrayList.clear();
                    j2++;
                }
                arrayList.add(a2);
                if (i2 >= 16 && i2 <= 21) {
                    this.u.add(Long.valueOf(j2));
                }
            } else {
                System.err.println("");
                HevcDecoderConfigurationRecord hevcDecoderConfigurationRecord = new HevcDecoderConfigurationRecord();
                hevcDecoderConfigurationRecord.a(a());
                hevcDecoderConfigurationRecord.j(0);
                return;
            }
        }
    }

    public static void a(String[] strArr) throws IOException {
        new H265TrackImplOld(new FileDataSourceImpl("c:\\content\\test-UHD-HEVC_01_FMV_Med_track1.hvc"));
    }

    private ByteBuffer a(LookAhead lookAhead) throws IOException {
        while (!lookAhead.b()) {
            try {
                lookAhead.d();
            } catch (EOFException unused) {
                return null;
            }
        }
        lookAhead.e();
        while (!lookAhead.c()) {
            lookAhead.d();
        }
        return lookAhead.f();
    }

    public void a(int i2, CAVLCReader cAVLCReader) throws IOException {
        int i3 = i2;
        CAVLCReader cAVLCReader2 = cAVLCReader;
        cAVLCReader2.b(2, "general_profile_space ");
        cAVLCReader2.c("general_tier_flag");
        cAVLCReader2.b(5, "general_profile_idc");
        boolean[] zArr = new boolean[32];
        for (int i4 = 0; i4 < 32; i4++) {
            zArr[i4] = cAVLCReader2.c("general_profile_compatibility_flag[" + i4 + Operators.ARRAY_END_STR);
        }
        cAVLCReader2.c("general_progressive_source_flag");
        cAVLCReader2.c("general_interlaced_source_flag");
        cAVLCReader2.c("general_non_packed_constraint_flag");
        cAVLCReader2.c("general_frame_only_constraint_flag");
        cAVLCReader2.b(44, "general_reserved_zero_44bits");
        cAVLCReader2.b(8, "general_level_idc");
        boolean[] zArr2 = new boolean[i3];
        boolean[] zArr3 = new boolean[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            zArr2[i5] = cAVLCReader2.c("sub_layer_profile_present_flag[" + i5 + Operators.ARRAY_END_STR);
            zArr3[i5] = cAVLCReader2.c("sub_layer_level_present_flag[" + i5 + Operators.ARRAY_END_STR);
        }
        if (i3 > 0) {
            for (int i6 = i3; i6 < 8; i6++) {
                cAVLCReader2.b(2, "reserved_zero_2bits");
            }
        }
        int[] iArr = new int[i3];
        boolean[] zArr4 = new boolean[i3];
        int[] iArr2 = new int[i3];
        boolean[][] zArr5 = (boolean[][]) Array.newInstance(boolean.class, new int[]{i3, 32});
        boolean[] zArr6 = new boolean[i3];
        boolean[] zArr7 = new boolean[i3];
        boolean[] zArr8 = new boolean[i3];
        boolean[] zArr9 = new boolean[i3];
        int[] iArr3 = new int[i3];
        int i7 = 0;
        while (i7 < i3) {
            if (zArr2[i7]) {
                iArr[i7] = cAVLCReader2.b(2, "sub_layer_profile_space[" + i7 + Operators.ARRAY_END_STR);
                zArr4[i7] = cAVLCReader2.c("sub_layer_tier_flag[" + i7 + Operators.ARRAY_END_STR);
                iArr2[i7] = cAVLCReader2.b(5, "sub_layer_profile_idc[" + i7 + Operators.ARRAY_END_STR);
                int i8 = 0;
                while (i8 < 32) {
                    boolean[] zArr10 = zArr5[i7];
                    zArr10[i8] = cAVLCReader2.c("sub_layer_profile_compatibility_flag[" + i7 + "][" + i8 + Operators.ARRAY_END_STR);
                    i8++;
                    int i9 = i2;
                }
                zArr6[i7] = cAVLCReader2.c("sub_layer_progressive_source_flag[" + i7 + Operators.ARRAY_END_STR);
                zArr7[i7] = cAVLCReader2.c("sub_layer_interlaced_source_flag[" + i7 + Operators.ARRAY_END_STR);
                zArr8[i7] = cAVLCReader2.c("sub_layer_non_packed_constraint_flag[" + i7 + Operators.ARRAY_END_STR);
                zArr9[i7] = cAVLCReader2.c("sub_layer_frame_only_constraint_flag[" + i7 + Operators.ARRAY_END_STR);
                cAVLCReader2.a(44, "reserved");
            }
            if (zArr3[i7]) {
                iArr3[i7] = cAVLCReader2.b(8, "sub_layer_level_idc");
            }
            i7++;
            i3 = i2;
        }
    }

    public int a(ByteBuffer byteBuffer) throws IOException {
        CAVLCReader cAVLCReader = new CAVLCReader(Channels.newInputStream(new ByteBufferByteChannel((ByteBuffer) byteBuffer.position(0))));
        cAVLCReader.b(4, "vps_parameter_set_id");
        cAVLCReader.b(2, "vps_reserved_three_2bits");
        cAVLCReader.b(6, "vps_max_layers_minus1");
        int b2 = cAVLCReader.b(3, "vps_max_sub_layers_minus1");
        cAVLCReader.c("vps_temporal_id_nesting_flag");
        cAVLCReader.b(16, "vps_reserved_0xffff_16bits");
        a(b2, cAVLCReader);
        boolean c2 = cAVLCReader.c("vps_sub_layer_ordering_info_present_flag");
        int[] iArr = new int[(c2 ? 0 : b2)];
        int[] iArr2 = new int[(c2 ? 0 : b2)];
        int[] iArr3 = new int[(c2 ? 0 : b2)];
        for (int i2 = c2 ? 0 : b2; i2 <= b2; i2++) {
            iArr[i2] = cAVLCReader.a("vps_max_dec_pic_buffering_minus1[" + i2 + Operators.ARRAY_END_STR);
            iArr2[i2] = cAVLCReader.a("vps_max_dec_pic_buffering_minus1[" + i2 + Operators.ARRAY_END_STR);
            iArr3[i2] = cAVLCReader.a("vps_max_dec_pic_buffering_minus1[" + i2 + Operators.ARRAY_END_STR);
        }
        int b3 = cAVLCReader.b(6, "vps_max_layer_id");
        int a2 = cAVLCReader.a("vps_num_layer_sets_minus1");
        boolean[][] zArr = (boolean[][]) Array.newInstance(boolean.class, new int[]{a2, b3});
        for (int i3 = 1; i3 <= a2; i3++) {
            for (int i4 = 0; i4 <= b3; i4++) {
                boolean[] zArr2 = zArr[i3];
                zArr2[i4] = cAVLCReader.c("layer_id_included_flag[" + i3 + "][" + i4 + Operators.ARRAY_END_STR);
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
            for (int i5 = 0; i5 < a3; i5++) {
                iArr4[i5] = cAVLCReader.a("hrd_layer_set_idx[" + i5 + Operators.ARRAY_END_STR);
                if (i5 > 0) {
                    zArr3[i5] = cAVLCReader.c("cprms_present_flag[" + i5 + Operators.ARRAY_END_STR);
                } else {
                    zArr3[0] = true;
                }
                a(zArr3[i5], b2, cAVLCReader);
            }
        }
        if (cAVLCReader.c("vps_extension_flag")) {
            while (cAVLCReader.d()) {
                cAVLCReader.c("vps_extension_data_flag");
            }
        }
        cAVLCReader.l();
        return 0;
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
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.muxer.tracks.h265.H265TrackImplOld.a(boolean, int, org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader):void");
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, boolean z2, CAVLCReader cAVLCReader) throws IOException {
        int[] iArr = new int[i3];
        int[] iArr2 = new int[i3];
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        boolean[] zArr = new boolean[i3];
        for (int i4 = 0; i4 <= i3; i4++) {
            iArr[i4] = cAVLCReader.a("bit_rate_value_minus1[" + i4 + Operators.ARRAY_END_STR);
            iArr2[i4] = cAVLCReader.a("cpb_size_value_minus1[" + i4 + Operators.ARRAY_END_STR);
            if (z2) {
                iArr3[i4] = cAVLCReader.a("cpb_size_du_value_minus1[" + i4 + Operators.ARRAY_END_STR);
                iArr4[i4] = cAVLCReader.a("bit_rate_du_value_minus1[" + i4 + Operators.ARRAY_END_STR);
            }
            zArr[i4] = cAVLCReader.c("cbr_flag[" + i4 + Operators.ARRAY_END_STR);
        }
    }

    private List<HevcDecoderConfigurationRecord.Array> a() {
        HevcDecoderConfigurationRecord.Array array = new HevcDecoderConfigurationRecord.Array();
        array.f3920a = true;
        array.c = 32;
        array.d = new ArrayList();
        for (ByteBuffer next : this.r.values()) {
            byte[] bArr = new byte[next.limit()];
            next.position(0);
            next.get(bArr);
            array.d.add(bArr);
        }
        HevcDecoderConfigurationRecord.Array array2 = new HevcDecoderConfigurationRecord.Array();
        array2.f3920a = true;
        array2.c = 33;
        array2.d = new ArrayList();
        for (ByteBuffer next2 : this.s.values()) {
            byte[] bArr2 = new byte[next2.limit()];
            next2.position(0);
            next2.get(bArr2);
            array2.d.add(bArr2);
        }
        HevcDecoderConfigurationRecord.Array array3 = new HevcDecoderConfigurationRecord.Array();
        array3.f3920a = true;
        array3.c = 33;
        array3.d = new ArrayList();
        for (ByteBuffer next3 : this.t.values()) {
            byte[] bArr3 = new byte[next3.limit()];
            next3.position(0);
            next3.get(bArr3);
            array3.d.add(bArr3);
        }
        return Arrays.asList(new HevcDecoderConfigurationRecord.Array[]{array, array2, array3});
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, ByteBuffer byteBuffer, List<ByteBuffer> list) {
        if (list.isEmpty()) {
            return true;
        }
        boolean z2 = b(list.get(list.size() - 1)).b <= 31;
        switch (i2) {
            case 32:
            case 33:
            case 34:
            case 35:
            case 39:
            case 41:
            case 42:
            case 43:
            case 44:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                if (z2) {
                    return true;
                }
                break;
        }
        switch (i2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                break;
            default:
                switch (i2) {
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                        break;
                    default:
                        return false;
                }
        }
        byteBuffer.position(0);
        byteBuffer.get(new byte[50]);
        byteBuffer.position(2);
        int f2 = IsoTypeReader.f(byteBuffer);
        if (!z2 || (f2 & 128) <= 0) {
            return false;
        }
        return true;
    }

    public NalUnitHeader b(ByteBuffer byteBuffer) {
        byteBuffer.position(0);
        int d2 = IsoTypeReader.d(byteBuffer);
        NalUnitHeader nalUnitHeader = new NalUnitHeader();
        nalUnitHeader.f4052a = (32768 & d2) >> 15;
        nalUnitHeader.b = (d2 & 32256) >> 9;
        nalUnitHeader.c = (d2 & 504) >> 3;
        nalUnitHeader.d = d2 & 7;
        return nalUnitHeader;
    }

    /* access modifiers changed from: protected */
    public Sample a(List<ByteBuffer> list) {
        byte[] bArr = new byte[(list.size() * 4)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        for (ByteBuffer remaining : list) {
            wrap.putInt(remaining.remaining());
        }
        ByteBuffer[] byteBufferArr = new ByteBuffer[(list.size() * 2)];
        for (int i2 = 0; i2 < list.size(); i2++) {
            int i3 = i2 * 2;
            byteBufferArr[i3] = ByteBuffer.wrap(bArr, i2 * 4, 4);
            byteBufferArr[i3 + 1] = list.get(i2);
        }
        return new SampleImpl(byteBufferArr);
    }

    class LookAhead {

        /* renamed from: a  reason: collision with root package name */
        long f4051a = 0;
        int b = 0;
        DataSource c;
        ByteBuffer d;
        long e;

        LookAhead(DataSource dataSource) throws IOException {
            this.c = dataSource;
            a();
        }

        public void a() throws IOException {
            this.d = this.c.a(this.f4051a, Math.min(this.c.a() - this.f4051a, 1048576));
        }

        /* access modifiers changed from: package-private */
        public boolean b() throws IOException {
            if (this.d.limit() - this.b >= 3) {
                if (this.d.get(this.b) == 0 && this.d.get(this.b + 1) == 0 && this.d.get(this.b + 2) == 1) {
                    return true;
                }
                return false;
            } else if (this.f4051a + ((long) this.b) == this.c.a()) {
                throw new EOFException();
            } else {
                throw new RuntimeException("buffer repositioning require");
            }
        }

        /* access modifiers changed from: package-private */
        public boolean c() throws IOException {
            if (this.d.limit() - this.b >= 3) {
                if (this.d.get(this.b) != 0 || this.d.get(this.b + 1) != 0) {
                    return false;
                }
                if (this.d.get(this.b + 2) == 0 || this.d.get(this.b + 2) == 1) {
                    return true;
                }
                return false;
            } else if (this.f4051a + ((long) this.b) + 3 <= this.c.a()) {
                this.f4051a = this.e;
                this.b = 0;
                a();
                return c();
            } else if (this.f4051a + ((long) this.b) == this.c.a()) {
                return true;
            } else {
                return false;
            }
        }

        /* access modifiers changed from: package-private */
        public void d() {
            this.b++;
        }

        /* access modifiers changed from: package-private */
        public void e() {
            this.b += 3;
            this.e = this.f4051a + ((long) this.b);
        }

        public ByteBuffer f() {
            if (this.e >= this.f4051a) {
                this.d.position((int) (this.e - this.f4051a));
                ByteBuffer slice = this.d.slice();
                slice.limit((int) (((long) this.b) - (this.e - this.f4051a)));
                return slice;
            }
            throw new RuntimeException("damn! NAL exceeds buffer");
        }
    }
}
