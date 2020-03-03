package org.mp4parser.muxer.tracks.h264.parsing.model;

import com.taobao.weex.el.parse.Operators;

public class VUIParameters {

    /* renamed from: a  reason: collision with root package name */
    public boolean f4044a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;
    public boolean f;
    public int g;
    public boolean h;
    public boolean i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    public int n;
    public int o;
    public boolean p;
    public int q;
    public int r;
    public boolean s;
    public boolean t;
    public boolean u;
    public HRDParameters v;
    public HRDParameters w;
    public BitstreamRestriction x;
    public AspectRatio y;

    public String toString() {
        return "VUIParameters{\naspect_ratio_info_present_flag=" + this.f4044a + "\n" + ", sar_width=" + this.b + "\n" + ", sar_height=" + this.c + "\n" + ", overscan_info_present_flag=" + this.d + "\n" + ", overscan_appropriate_flag=" + this.e + "\n" + ", video_signal_type_present_flag=" + this.f + "\n" + ", video_format=" + this.g + "\n" + ", video_full_range_flag=" + this.h + "\n" + ", colour_description_present_flag=" + this.i + "\n" + ", colour_primaries=" + this.j + "\n" + ", transfer_characteristics=" + this.k + "\n" + ", matrix_coefficients=" + this.l + "\n" + ", chroma_loc_info_present_flag=" + this.m + "\n" + ", chroma_sample_loc_type_top_field=" + this.n + "\n" + ", chroma_sample_loc_type_bottom_field=" + this.o + "\n" + ", timing_info_present_flag=" + this.p + "\n" + ", num_units_in_tick=" + this.q + "\n" + ", time_scale=" + this.r + "\n" + ", fixed_frame_rate_flag=" + this.s + "\n" + ", low_delay_hrd_flag=" + this.t + "\n" + ", pic_struct_present_flag=" + this.u + "\n" + ", nalHRDParams=" + this.v + "\n" + ", vclHRDParams=" + this.w + "\n" + ", bitstreamRestriction=" + this.x + "\n" + ", aspect_ratio=" + this.y + "\n" + Operators.BLOCK_END;
    }

    public static class BitstreamRestriction {

        /* renamed from: a  reason: collision with root package name */
        public boolean f4045a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;

        public String toString() {
            return "BitstreamRestriction{" + "motion_vectors_over_pic_boundaries_flag=" + this.f4045a + ", max_bytes_per_pic_denom=" + this.b + ", max_bits_per_mb_denom=" + this.c + ", log2_max_mv_length_horizontal=" + this.d + ", log2_max_mv_length_vertical=" + this.e + ", num_reorder_frames=" + this.f + ", max_dec_frame_buffering=" + this.g + Operators.BLOCK_END;
        }
    }
}
