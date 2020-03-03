package org.mp4parser.boxes.iso14496.part12;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SampleFlags {

    /* renamed from: a  reason: collision with root package name */
    private byte f3881a;
    private byte b;
    private byte c;
    private byte d;
    private byte e;
    private byte f;
    private boolean g;
    private int h;

    public SampleFlags() {
    }

    public SampleFlags(ByteBuffer byteBuffer) {
        long b2 = IsoTypeReader.b(byteBuffer);
        this.f3881a = (byte) ((int) ((-268435456 & b2) >> 28));
        this.b = (byte) ((int) ((201326592 & b2) >> 26));
        this.c = (byte) ((int) ((50331648 & b2) >> 24));
        this.d = (byte) ((int) ((12582912 & b2) >> 22));
        this.e = (byte) ((int) ((3145728 & b2) >> 20));
        this.f = (byte) ((int) ((917504 & b2) >> 17));
        this.g = ((65536 & b2) >> 16) > 0;
        this.h = (int) (b2 & 65535);
    }

    public void a(ByteBuffer byteBuffer) {
        IsoTypeWriter.b(byteBuffer, ((long) (this.f3881a << 28)) | 0 | ((long) (this.b << 26)) | ((long) (this.c << 24)) | ((long) (this.d << 22)) | ((long) (this.e << 20)) | ((long) (this.f << 17)) | ((long) ((this.g ? 1 : 0) << true)) | ((long) this.h));
    }

    public int a() {
        return this.f3881a;
    }

    public void a(int i) {
        this.f3881a = (byte) i;
    }

    public byte b() {
        return this.b;
    }

    public void a(byte b2) {
        this.b = b2;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = (byte) i;
    }

    public int d() {
        return this.d;
    }

    public void c(int i) {
        this.d = (byte) i;
    }

    public int e() {
        return this.e;
    }

    public void d(int i) {
        this.e = (byte) i;
    }

    public int f() {
        return this.f;
    }

    public void e(int i) {
        this.f = (byte) i;
    }

    public boolean g() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public int h() {
        return this.h;
    }

    public void f(int i) {
        this.h = i;
    }

    public String toString() {
        return "SampleFlags{reserved=" + this.f3881a + ", isLeading=" + this.b + ", depOn=" + this.c + ", isDepOn=" + this.d + ", hasRedundancy=" + this.e + ", padValue=" + this.f + ", isDiffSample=" + this.g + ", degradPrio=" + this.h + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SampleFlags sampleFlags = (SampleFlags) obj;
        return this.b == sampleFlags.b && this.f3881a == sampleFlags.f3881a && this.h == sampleFlags.h && this.c == sampleFlags.c && this.e == sampleFlags.e && this.d == sampleFlags.d && this.g == sampleFlags.g && this.f == sampleFlags.f;
    }

    public int hashCode() {
        return (((((((((((((this.f3881a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + (this.g ? 1 : 0)) * 31) + this.h;
    }
}
