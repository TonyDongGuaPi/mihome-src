package org.mp4parser.muxer.tracks.h264.parsing.model;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public class HRDParameters {

    /* renamed from: a  reason: collision with root package name */
    public int f4038a;
    public int b;
    public int c;
    public int[] d;
    public int[] e;
    public boolean[] f;
    public int g;
    public int h;
    public int i;
    public int j;

    public String toString() {
        return "HRDParameters{cpb_cnt_minus1=" + this.f4038a + ", bit_rate_scale=" + this.b + ", cpb_size_scale=" + this.c + ", bit_rate_value_minus1=" + Arrays.toString(this.d) + ", cpb_size_value_minus1=" + Arrays.toString(this.e) + ", cbr_flag=" + Arrays.toString(this.f) + ", initial_cpb_removal_delay_length_minus1=" + this.g + ", cpb_removal_delay_length_minus1=" + this.h + ", dpb_output_delay_length_minus1=" + this.i + ", time_offset_length=" + this.j + Operators.BLOCK_END;
    }
}
