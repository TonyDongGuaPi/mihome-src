package org.mp4parser.boxes.iso14496.part15;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class TemporalLayerSampleGroup extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3924a = "tscl";
    int b;
    int c;
    boolean d;
    int e;
    long f;
    long g;
    int h;
    int i;
    int j;
    int k;
    int l;

    public String a() {
        return f3924a;
    }

    public int n() {
        return 20;
    }

    public int c() {
        return this.b;
    }

    public void a(int i2) {
        this.b = i2;
    }

    public int d() {
        return this.c;
    }

    public void b(int i2) {
        this.c = i2;
    }

    public boolean e() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public int f() {
        return this.e;
    }

    public void c(int i2) {
        this.e = i2;
    }

    public long g() {
        return this.f;
    }

    public void a(long j2) {
        this.f = j2;
    }

    public long h() {
        return this.g;
    }

    public void b(long j2) {
        this.g = j2;
    }

    public int i() {
        return this.h;
    }

    public void d(int i2) {
        this.h = i2;
    }

    public int j() {
        return this.i;
    }

    public void e(int i2) {
        this.i = i2;
    }

    public int k() {
        return this.j;
    }

    public void f(int i2) {
        this.j = i2;
    }

    public int l() {
        return this.k;
    }

    public void g(int i2) {
        this.k = i2;
    }

    public int m() {
        return this.l;
    }

    public void h(int i2) {
        this.l = i2;
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = IsoTypeReader.f(byteBuffer);
        int f2 = IsoTypeReader.f(byteBuffer);
        this.c = (f2 & 192) >> 6;
        this.d = (f2 & 32) > 0;
        this.e = f2 & 31;
        this.f = IsoTypeReader.b(byteBuffer);
        this.g = IsoTypeReader.n(byteBuffer);
        this.h = IsoTypeReader.f(byteBuffer);
        this.i = IsoTypeReader.d(byteBuffer);
        this.j = IsoTypeReader.d(byteBuffer);
        this.k = IsoTypeReader.f(byteBuffer);
        this.l = IsoTypeReader.d(byteBuffer);
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(20);
        IsoTypeWriter.d(allocate, this.b);
        IsoTypeWriter.d(allocate, (this.c << 6) + (this.d ? 32 : 0) + this.e);
        IsoTypeWriter.b(allocate, this.f);
        IsoTypeWriter.d(allocate, this.g);
        IsoTypeWriter.d(allocate, this.h);
        IsoTypeWriter.b(allocate, this.i);
        IsoTypeWriter.b(allocate, this.j);
        IsoTypeWriter.d(allocate, this.k);
        IsoTypeWriter.b(allocate, this.l);
        return (ByteBuffer) allocate.rewind();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TemporalLayerSampleGroup temporalLayerSampleGroup = (TemporalLayerSampleGroup) obj;
        return this.b == temporalLayerSampleGroup.b && this.j == temporalLayerSampleGroup.j && this.l == temporalLayerSampleGroup.l && this.k == temporalLayerSampleGroup.k && this.i == temporalLayerSampleGroup.i && this.g == temporalLayerSampleGroup.g && this.h == temporalLayerSampleGroup.h && this.f == temporalLayerSampleGroup.f && this.e == temporalLayerSampleGroup.e && this.c == temporalLayerSampleGroup.c && this.d == temporalLayerSampleGroup.d;
    }

    public int hashCode() {
        return (((((((((((((((((((this.b * 31) + this.c) * 31) + (this.d ? 1 : 0)) * 31) + this.e) * 31) + ((int) (this.f ^ (this.f >>> 32)))) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + this.k) * 31) + this.l;
    }

    public String toString() {
        return "TemporalLayerSampleGroup{temporalLayerId=" + this.b + ", tlprofile_space=" + this.c + ", tltier_flag=" + this.d + ", tlprofile_idc=" + this.e + ", tlprofile_compatibility_flags=" + this.f + ", tlconstraint_indicator_flags=" + this.g + ", tllevel_idc=" + this.h + ", tlMaxBitRate=" + this.i + ", tlAvgBitRate=" + this.j + ", tlConstantFrameRate=" + this.k + ", tlAvgFrameRate=" + this.l + Operators.BLOCK_END;
    }
}
