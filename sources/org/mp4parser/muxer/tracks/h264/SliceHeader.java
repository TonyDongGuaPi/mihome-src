package org.mp4parser.muxer.tracks.h264;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.mp4parser.muxer.tracks.h264.parsing.model.PictureParameterSet;
import org.mp4parser.muxer.tracks.h264.parsing.model.SeqParameterSet;
import org.mp4parser.muxer.tracks.h264.parsing.read.CAVLCReader;

public class SliceHeader {

    /* renamed from: a  reason: collision with root package name */
    public int f4032a;
    public SliceType b;
    public int c;
    public int d;
    public int e;
    public boolean f = false;
    public boolean g = false;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public PictureParameterSet m;
    public SeqParameterSet n;

    public enum SliceType {
        P,
        B,
        I,
        SP,
        SI
    }

    public SliceHeader(InputStream inputStream, Map<Integer, SeqParameterSet> map, Map<Integer, PictureParameterSet> map2, boolean z) {
        try {
            inputStream.read();
            CAVLCReader cAVLCReader = new CAVLCReader(inputStream);
            this.f4032a = cAVLCReader.a("SliceHeader: first_mb_in_slice");
            switch (cAVLCReader.a("SliceHeader: slice_type")) {
                case 0:
                case 5:
                    this.b = SliceType.P;
                    break;
                case 1:
                case 6:
                    this.b = SliceType.B;
                    break;
                case 2:
                case 7:
                    this.b = SliceType.I;
                    break;
                case 3:
                case 8:
                    this.b = SliceType.SP;
                    break;
                case 4:
                case 9:
                    this.b = SliceType.SI;
                    break;
            }
            this.c = cAVLCReader.a("SliceHeader: pic_parameter_set_id");
            this.m = map2.get(Integer.valueOf(this.c));
            if (this.m == null) {
                String str = "";
                for (Integer num : map.keySet()) {
                    str = str + num + ", ";
                }
                throw new RuntimeException("PPS with ids " + str + " available but not " + this.c);
            }
            this.n = map.get(Integer.valueOf(this.m.f));
            if (this.n.A) {
                this.d = cAVLCReader.b(2, "SliceHeader: colour_plane_id");
            }
            this.e = cAVLCReader.b(this.n.j + 4, "SliceHeader: frame_num");
            if (!this.n.F) {
                this.f = cAVLCReader.c("SliceHeader: field_pic_flag");
                if (this.f) {
                    this.g = cAVLCReader.c("SliceHeader: bottom_field_flag");
                }
            }
            if (z) {
                this.h = cAVLCReader.a("SliceHeader: idr_pic_id");
            }
            if (this.n.f4043a == 0) {
                this.i = cAVLCReader.b(this.n.k + 4, "SliceHeader: pic_order_cnt_lsb");
                if (this.m.g && !this.f) {
                    this.j = cAVLCReader.b("SliceHeader: delta_pic_order_cnt_bottom");
                }
            }
            if (this.n.f4043a == 1 && !this.n.c) {
                this.k = cAVLCReader.b("delta_pic_order_cnt_0");
                if (this.m.g && !this.f) {
                    this.l = cAVLCReader.b("delta_pic_order_cnt_1");
                }
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public String toString() {
        return "SliceHeader{first_mb_in_slice=" + this.f4032a + ", slice_type=" + this.b + ", pic_parameter_set_id=" + this.c + ", colour_plane_id=" + this.d + ", frame_num=" + this.e + ", field_pic_flag=" + this.f + ", bottom_field_flag=" + this.g + ", idr_pic_id=" + this.h + ", pic_order_cnt_lsb=" + this.i + ", delta_pic_order_cnt_bottom=" + this.j + Operators.BLOCK_END;
    }
}
