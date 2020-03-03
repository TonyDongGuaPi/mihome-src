package org.mp4parser.muxer.tracks.h264;

import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.mp4parser.muxer.tracks.h264.parsing.model.SeqParameterSet;
import org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader;

public class SEIMessage {

    /* renamed from: a  reason: collision with root package name */
    int f4031a = 0;
    int b = 0;
    boolean c;
    int d;
    int e;
    boolean f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    SeqParameterSet t;

    public SEIMessage(InputStream inputStream, SeqParameterSet seqParameterSet) throws IOException {
        int i2;
        int i3;
        this.t = seqParameterSet;
        inputStream.read();
        int available = inputStream.available();
        int i4 = 0;
        while (i4 < available) {
            this.f4031a = 0;
            this.b = 0;
            int read = inputStream.read();
            while (true) {
                i4++;
                if (read == 255) {
                    this.f4031a += read;
                    read = inputStream.read();
                } else {
                    this.f4031a += read;
                    int read2 = inputStream.read();
                    int i5 = i4 + 1;
                    while (read2 == 255) {
                        this.b += read2;
                        read2 = inputStream.read();
                        i5++;
                    }
                    this.b += read2;
                    if (available - i5 >= this.b) {
                        if (this.f4031a != 1) {
                            int i6 = i5;
                            for (int i7 = 0; i7 < this.b; i7++) {
                                inputStream.read();
                                i6 = i2 + 1;
                            }
                        } else if (seqParameterSet.M == null || (seqParameterSet.M.v == null && seqParameterSet.M.w == null && !seqParameterSet.M.u)) {
                            i2 = i5;
                            for (int i8 = 0; i8 < this.b; i8++) {
                                inputStream.read();
                                i2++;
                            }
                        } else {
                            byte[] bArr = new byte[this.b];
                            inputStream.read(bArr);
                            i4 = i5 + this.b;
                            CAVLCReader cAVLCReader = new CAVLCReader(new ByteArrayInputStream(bArr));
                            if (seqParameterSet.M.v == null && seqParameterSet.M.w == null) {
                                this.c = false;
                            } else {
                                this.c = true;
                                this.d = cAVLCReader.b(seqParameterSet.M.v.h + 1, "SEI: cpb_removal_delay");
                                this.e = cAVLCReader.b(seqParameterSet.M.v.i + 1, "SEI: dpb_removal_delay");
                            }
                            if (seqParameterSet.M.u) {
                                this.g = cAVLCReader.b(4, "SEI: pic_struct");
                                switch (this.g) {
                                    case 3:
                                    case 4:
                                    case 7:
                                        i3 = 2;
                                        break;
                                    case 5:
                                    case 6:
                                    case 8:
                                        i3 = 3;
                                        break;
                                    default:
                                        i3 = 1;
                                        break;
                                }
                                for (int i9 = 0; i9 < i3; i9++) {
                                    this.f = cAVLCReader.c("pic_timing SEI: clock_timestamp_flag[" + i9 + Operators.ARRAY_END_STR);
                                    if (this.f) {
                                        this.h = cAVLCReader.b(2, "pic_timing SEI: ct_type");
                                        this.i = cAVLCReader.b(1, "pic_timing SEI: nuit_field_based_flag");
                                        this.j = cAVLCReader.b(5, "pic_timing SEI: counting_type");
                                        this.k = cAVLCReader.b(1, "pic_timing SEI: full_timestamp_flag");
                                        this.l = cAVLCReader.b(1, "pic_timing SEI: discontinuity_flag");
                                        this.m = cAVLCReader.b(1, "pic_timing SEI: cnt_dropped_flag");
                                        this.n = cAVLCReader.b(8, "pic_timing SEI: n_frames");
                                        if (this.k == 1) {
                                            this.o = cAVLCReader.b(6, "pic_timing SEI: seconds_value");
                                            this.p = cAVLCReader.b(6, "pic_timing SEI: minutes_value");
                                            this.q = cAVLCReader.b(5, "pic_timing SEI: hours_value");
                                        } else if (cAVLCReader.c("pic_timing SEI: seconds_flag")) {
                                            this.o = cAVLCReader.b(6, "pic_timing SEI: seconds_value");
                                            if (cAVLCReader.c("pic_timing SEI: minutes_flag")) {
                                                this.p = cAVLCReader.b(6, "pic_timing SEI: minutes_value");
                                                if (cAVLCReader.c("pic_timing SEI: hours_flag")) {
                                                    this.q = cAVLCReader.b(5, "pic_timing SEI: hours_value");
                                                }
                                            }
                                        }
                                        if (seqParameterSet.M.v != null) {
                                            this.r = seqParameterSet.M.v.j;
                                        } else if (seqParameterSet.M.w != null) {
                                            this.r = seqParameterSet.M.w.j;
                                        } else {
                                            this.r = 24;
                                        }
                                        this.s = cAVLCReader.b(24, "pic_timing SEI: time_offset");
                                    }
                                }
                            }
                        }
                        i4 = i2;
                    } else {
                        i4 = available;
                    }
                }
            }
        }
    }

    public String toString() {
        String str = "SEIMessage{payloadType=" + this.f4031a + ", payloadSize=" + this.b;
        if (this.f4031a == 1) {
            if (!(this.t.M.v == null && this.t.M.w == null)) {
                str = str + ", cpb_removal_delay=" + this.d + ", dpb_removal_delay=" + this.e;
            }
            if (this.t.M.u) {
                str = str + ", pic_struct=" + this.g;
                if (this.f) {
                    str = str + ", ct_type=" + this.h + ", nuit_field_based_flag=" + this.i + ", counting_type=" + this.j + ", full_timestamp_flag=" + this.k + ", discontinuity_flag=" + this.l + ", cnt_dropped_flag=" + this.m + ", n_frames=" + this.n + ", seconds_value=" + this.o + ", minutes_value=" + this.p + ", hours_value=" + this.q + ", time_offset_length=" + this.r + ", time_offset=" + this.s;
                }
            }
        }
        return str + Operators.BLOCK_END;
    }
}
