package org.mp4parser.boxes.iso14496.part15;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.compress.archivers.zip.UnixStat;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class HevcDecoderConfigurationRecord {
    boolean A;

    /* renamed from: a  reason: collision with root package name */
    int f3919a;
    int b;
    boolean c;
    int d;
    long e;
    long f;
    int g;
    int h = 15;
    int i;
    int j = 63;
    int k;
    int l = 63;
    int m;
    int n = 31;
    int o;
    int p = 31;
    int q;
    int r;
    int s;
    int t;
    boolean u;
    int v;
    List<Array> w = new ArrayList();
    boolean x;
    boolean y;
    boolean z;

    public void a(ByteBuffer byteBuffer) {
        this.f3919a = IsoTypeReader.f(byteBuffer);
        int f2 = IsoTypeReader.f(byteBuffer);
        this.b = (f2 & 192) >> 6;
        this.c = (f2 & 32) > 0;
        this.d = f2 & 31;
        this.e = IsoTypeReader.b(byteBuffer);
        this.f = IsoTypeReader.n(byteBuffer);
        this.x = ((this.f >> 44) & 8) > 0;
        this.y = ((this.f >> 44) & 4) > 0;
        this.z = ((this.f >> 44) & 2) > 0;
        this.A = ((this.f >> 44) & 1) > 0;
        this.f &= 140737488355327L;
        this.g = IsoTypeReader.f(byteBuffer);
        int d2 = IsoTypeReader.d(byteBuffer);
        this.h = (61440 & d2) >> 12;
        this.i = d2 & UnixStat.f3272a;
        int f3 = IsoTypeReader.f(byteBuffer);
        this.j = (f3 & 252) >> 2;
        this.k = f3 & 3;
        int f4 = IsoTypeReader.f(byteBuffer);
        this.l = (f4 & 252) >> 2;
        this.m = f4 & 3;
        int f5 = IsoTypeReader.f(byteBuffer);
        this.n = (f5 & 248) >> 3;
        this.o = f5 & 7;
        int f6 = IsoTypeReader.f(byteBuffer);
        this.p = (f6 & 248) >> 3;
        this.q = f6 & 7;
        this.r = IsoTypeReader.d(byteBuffer);
        int f7 = IsoTypeReader.f(byteBuffer);
        this.s = (f7 & 192) >> 6;
        this.t = (f7 & 56) >> 3;
        this.u = (f7 & 4) > 0;
        this.v = f7 & 3;
        int f8 = IsoTypeReader.f(byteBuffer);
        this.w = new ArrayList();
        for (int i2 = 0; i2 < f8; i2++) {
            Array array = new Array();
            int f9 = IsoTypeReader.f(byteBuffer);
            array.f3920a = (f9 & 128) > 0;
            array.b = (f9 & 64) > 0;
            array.c = f9 & 63;
            int d3 = IsoTypeReader.d(byteBuffer);
            array.d = new ArrayList();
            for (int i3 = 0; i3 < d3; i3++) {
                byte[] bArr = new byte[IsoTypeReader.d(byteBuffer)];
                byteBuffer.get(bArr);
                array.d.add(bArr);
            }
            this.w.add(array);
        }
    }

    public void b(ByteBuffer byteBuffer) {
        IsoTypeWriter.d(byteBuffer, this.f3919a);
        IsoTypeWriter.d(byteBuffer, (this.b << 6) + (this.c ? 32 : 0) + this.d);
        IsoTypeWriter.b(byteBuffer, this.e);
        long j2 = this.f;
        if (this.x) {
            j2 |= 140737488355328L;
        }
        if (this.y) {
            j2 |= 70368744177664L;
        }
        if (this.z) {
            j2 |= 35184372088832L;
        }
        if (this.A) {
            j2 |= 17592186044416L;
        }
        IsoTypeWriter.d(byteBuffer, j2);
        IsoTypeWriter.d(byteBuffer, this.g);
        IsoTypeWriter.b(byteBuffer, (this.h << 12) + this.i);
        IsoTypeWriter.d(byteBuffer, (this.j << 2) + this.k);
        IsoTypeWriter.d(byteBuffer, (this.l << 2) + this.m);
        IsoTypeWriter.d(byteBuffer, (this.n << 3) + this.o);
        IsoTypeWriter.d(byteBuffer, (this.p << 3) + this.q);
        IsoTypeWriter.b(byteBuffer, this.r);
        IsoTypeWriter.d(byteBuffer, (this.s << 6) + (this.t << 3) + (this.u ? 4 : 0) + this.v);
        IsoTypeWriter.d(byteBuffer, this.w.size());
        for (Array next : this.w) {
            IsoTypeWriter.d(byteBuffer, (next.f3920a ? 128 : 0) + (next.b ? 64 : 0) + next.c);
            IsoTypeWriter.b(byteBuffer, next.d.size());
            for (byte[] next2 : next.d) {
                IsoTypeWriter.b(byteBuffer, next2.length);
                byteBuffer.put(next2);
            }
        }
    }

    public int a() {
        int i2 = 23;
        for (Array array : this.w) {
            i2 += 3;
            for (byte[] length : array.d) {
                i2 = i2 + 2 + length.length;
            }
        }
        return i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HevcDecoderConfigurationRecord hevcDecoderConfigurationRecord = (HevcDecoderConfigurationRecord) obj;
        if (this.r == hevcDecoderConfigurationRecord.r && this.q == hevcDecoderConfigurationRecord.q && this.o == hevcDecoderConfigurationRecord.o && this.m == hevcDecoderConfigurationRecord.m && this.f3919a == hevcDecoderConfigurationRecord.f3919a && this.s == hevcDecoderConfigurationRecord.s && this.f == hevcDecoderConfigurationRecord.f && this.g == hevcDecoderConfigurationRecord.g && this.e == hevcDecoderConfigurationRecord.e && this.d == hevcDecoderConfigurationRecord.d && this.b == hevcDecoderConfigurationRecord.b && this.c == hevcDecoderConfigurationRecord.c && this.v == hevcDecoderConfigurationRecord.v && this.i == hevcDecoderConfigurationRecord.i && this.t == hevcDecoderConfigurationRecord.t && this.k == hevcDecoderConfigurationRecord.k && this.h == hevcDecoderConfigurationRecord.h && this.j == hevcDecoderConfigurationRecord.j && this.l == hevcDecoderConfigurationRecord.l && this.n == hevcDecoderConfigurationRecord.n && this.p == hevcDecoderConfigurationRecord.p && this.u == hevcDecoderConfigurationRecord.u) {
            return this.w == null ? hevcDecoderConfigurationRecord.w == null : this.w.equals(hevcDecoderConfigurationRecord.w);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((this.f3919a * 31) + this.b) * 31) + (this.c ? 1 : 0)) * 31) + this.d) * 31) + ((int) (this.e ^ (this.e >>> 32)))) * 31) + ((int) (this.f ^ (this.f >>> 32)))) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + this.k) * 31) + this.l) * 31) + this.m) * 31) + this.n) * 31) + this.o) * 31) + this.p) * 31) + this.q) * 31) + this.r) * 31) + this.s) * 31) + this.t) * 31) + (this.u ? 1 : 0)) * 31) + this.v) * 31) + (this.w != null ? this.w.hashCode() : 0);
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        StringBuilder sb = new StringBuilder("HEVCDecoderConfigurationRecord{configurationVersion=");
        sb.append(this.f3919a);
        sb.append(", general_profile_space=");
        sb.append(this.b);
        sb.append(", general_tier_flag=");
        sb.append(this.c);
        sb.append(", general_profile_idc=");
        sb.append(this.d);
        sb.append(", general_profile_compatibility_flags=");
        sb.append(this.e);
        sb.append(", general_constraint_indicator_flags=");
        sb.append(this.f);
        sb.append(", general_level_idc=");
        sb.append(this.g);
        if (this.h != 15) {
            str = ", reserved1=" + this.h;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(", min_spatial_segmentation_idc=");
        sb.append(this.i);
        if (this.j != 63) {
            str2 = ", reserved2=" + this.j;
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append(", parallelismType=");
        sb.append(this.k);
        if (this.l != 63) {
            str3 = ", reserved3=" + this.l;
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(", chromaFormat=");
        sb.append(this.m);
        if (this.n != 31) {
            str4 = ", reserved4=" + this.n;
        } else {
            str4 = "";
        }
        sb.append(str4);
        sb.append(", bitDepthLumaMinus8=");
        sb.append(this.o);
        if (this.p != 31) {
            str5 = ", reserved5=" + this.p;
        } else {
            str5 = "";
        }
        sb.append(str5);
        sb.append(", bitDepthChromaMinus8=");
        sb.append(this.q);
        sb.append(", avgFrameRate=");
        sb.append(this.r);
        sb.append(", constantFrameRate=");
        sb.append(this.s);
        sb.append(", numTemporalLayers=");
        sb.append(this.t);
        sb.append(", temporalIdNested=");
        sb.append(this.u);
        sb.append(", lengthSizeMinusOne=");
        sb.append(this.v);
        sb.append(", arrays=");
        sb.append(this.w);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public int b() {
        return this.f3919a;
    }

    public void a(int i2) {
        this.f3919a = i2;
    }

    public int c() {
        return this.b;
    }

    public void b(int i2) {
        this.b = i2;
    }

    public boolean d() {
        return this.c;
    }

    public void a(boolean z2) {
        this.c = z2;
    }

    public int e() {
        return this.d;
    }

    public void c(int i2) {
        this.d = i2;
    }

    public long f() {
        return this.e;
    }

    public void a(long j2) {
        this.e = j2;
    }

    public long g() {
        return this.f;
    }

    public void b(long j2) {
        this.f = j2;
    }

    public int h() {
        return this.g;
    }

    public void d(int i2) {
        this.g = i2;
    }

    public int i() {
        return this.i;
    }

    public void e(int i2) {
        this.i = i2;
    }

    public int j() {
        return this.k;
    }

    public void f(int i2) {
        this.k = i2;
    }

    public int k() {
        return this.m;
    }

    public void g(int i2) {
        this.m = i2;
    }

    public int l() {
        return this.o;
    }

    public void h(int i2) {
        this.o = i2;
    }

    public int m() {
        return this.q;
    }

    public void i(int i2) {
        this.q = i2;
    }

    public int n() {
        return this.r;
    }

    public void j(int i2) {
        this.r = i2;
    }

    public int o() {
        return this.t;
    }

    public void k(int i2) {
        this.t = i2;
    }

    public int p() {
        return this.v;
    }

    public void l(int i2) {
        this.v = i2;
    }

    public boolean q() {
        return this.u;
    }

    public void b(boolean z2) {
        this.u = z2;
    }

    public int r() {
        return this.s;
    }

    public void m(int i2) {
        this.s = i2;
    }

    public List<Array> s() {
        return this.w;
    }

    public void a(List<Array> list) {
        this.w = list;
    }

    public boolean t() {
        return this.x;
    }

    public void c(boolean z2) {
        this.x = z2;
    }

    public boolean u() {
        return this.y;
    }

    public void d(boolean z2) {
        this.y = z2;
    }

    public boolean v() {
        return this.z;
    }

    public void e(boolean z2) {
        this.z = z2;
    }

    public boolean w() {
        return this.A;
    }

    public void f(boolean z2) {
        this.A = z2;
    }

    public static class Array {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3920a;
        public boolean b;
        public int c;
        public List<byte[]> d;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Array array = (Array) obj;
            if (this.f3920a != array.f3920a || this.c != array.c || this.b != array.b) {
                return false;
            }
            ListIterator<byte[]> listIterator = this.d.listIterator();
            ListIterator<byte[]> listIterator2 = array.d.listIterator();
            while (listIterator.hasNext() && listIterator2.hasNext()) {
                byte[] next = listIterator.next();
                byte[] next2 = listIterator2.next();
                if (next == null) {
                    if (next2 != null) {
                    }
                } else if (!Arrays.equals(next, next2)) {
                }
                return false;
            }
            if (listIterator.hasNext() || listIterator2.hasNext()) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((((((this.f3920a ? 1 : 0) * true) + (this.b ? 1 : 0)) * 31) + this.c) * 31) + (this.d != null ? this.d.hashCode() : 0);
        }

        public String toString() {
            return "Array{nal_unit_type=" + this.c + ", reserved=" + this.b + ", array_completeness=" + this.f3920a + ", num_nals=" + this.d.size() + Operators.BLOCK_END;
        }
    }
}
