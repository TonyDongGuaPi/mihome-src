package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

@Descriptor(tags = {3})
public class ESDescriptor extends BaseDescriptor {
    private static Logger n = Logger.getLogger(ESDescriptor.class.getName());

    /* renamed from: a  reason: collision with root package name */
    int f3825a;
    int b;
    int c;
    int d;
    int e;
    int f = 0;
    String g;
    int h;
    int i;
    int j;
    DecoderConfigDescriptor k;
    SLConfigDescriptor l;
    List<BaseDescriptor> m = new ArrayList();

    public ESDescriptor() {
        this.Z = 3;
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        this.f3825a = IsoTypeReader.d(byteBuffer);
        int f2 = IsoTypeReader.f(byteBuffer);
        this.b = f2 >>> 7;
        this.c = (f2 >>> 6) & 1;
        this.d = (f2 >>> 5) & 1;
        this.e = f2 & 31;
        if (this.b == 1) {
            this.i = IsoTypeReader.d(byteBuffer);
        }
        if (this.c == 1) {
            this.f = IsoTypeReader.f(byteBuffer);
            this.g = IsoTypeReader.a(byteBuffer, this.f);
        }
        if (this.d == 1) {
            this.j = IsoTypeReader.d(byteBuffer);
        }
        while (byteBuffer.remaining() > 1) {
            BaseDescriptor a2 = ObjectDescriptorFactory.a(-1, byteBuffer);
            if (a2 instanceof DecoderConfigDescriptor) {
                this.k = (DecoderConfigDescriptor) a2;
            } else if (a2 instanceof SLConfigDescriptor) {
                this.l = (SLConfigDescriptor) a2;
            } else {
                this.m.add(a2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int a() {
        int i2 = this.b > 0 ? 5 : 3;
        if (this.c > 0) {
            i2 += this.f + 1;
        }
        if (this.d > 0) {
            i2 += 2;
        }
        int l2 = i2 + this.k.l() + this.l.l();
        if (this.m.size() <= 0) {
            return l2;
        }
        throw new RuntimeException(" Doesn't handle other descriptors yet");
    }

    public ByteBuffer b() {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[l()]);
        IsoTypeWriter.d(wrap, 3);
        a(wrap, a());
        IsoTypeWriter.b(wrap, this.f3825a);
        IsoTypeWriter.d(wrap, (this.b << 7) | (this.c << 6) | (this.d << 5) | (this.e & 31));
        if (this.b > 0) {
            IsoTypeWriter.b(wrap, this.i);
        }
        if (this.c > 0) {
            IsoTypeWriter.d(wrap, this.f);
            IsoTypeWriter.d(wrap, this.g);
        }
        if (this.d > 0) {
            IsoTypeWriter.b(wrap, this.j);
        }
        ByteBuffer b2 = this.k.b();
        ByteBuffer b3 = this.l.b();
        wrap.put(b2.array());
        wrap.put(b3.array());
        return wrap;
    }

    public DecoderConfigDescriptor c() {
        return this.k;
    }

    public void a(DecoderConfigDescriptor decoderConfigDescriptor) {
        this.k = decoderConfigDescriptor;
    }

    public SLConfigDescriptor d() {
        return this.l;
    }

    public void a(SLConfigDescriptor sLConfigDescriptor) {
        this.l = sLConfigDescriptor;
    }

    public List<BaseDescriptor> e() {
        return this.m;
    }

    public int f() {
        return this.j;
    }

    public void a(int i2) {
        this.j = i2;
    }

    public int g() {
        return this.f3825a;
    }

    public void b(int i2) {
        this.f3825a = i2;
    }

    public int h() {
        return this.b;
    }

    public void c(int i2) {
        this.b = i2;
    }

    public int i() {
        return this.c;
    }

    public void d(int i2) {
        this.c = i2;
    }

    public int m() {
        return this.d;
    }

    public void e(int i2) {
        this.d = i2;
    }

    public int n() {
        return this.e;
    }

    public void f(int i2) {
        this.e = i2;
    }

    public int o() {
        return this.f;
    }

    public void g(int i2) {
        this.f = i2;
    }

    public String p() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public int q() {
        return this.h;
    }

    public void h(int i2) {
        this.h = i2;
    }

    public int r() {
        return this.i;
    }

    public void i(int i2) {
        this.i = i2;
    }

    public String toString() {
        return "ESDescriptor" + "{esId=" + this.f3825a + ", streamDependenceFlag=" + this.b + ", URLFlag=" + this.c + ", oCRstreamFlag=" + this.d + ", streamPriority=" + this.e + ", URLLength=" + this.f + ", URLString='" + this.g + Operators.SINGLE_QUOTE + ", remoteODFlag=" + this.h + ", dependsOnEsId=" + this.i + ", oCREsId=" + this.j + ", decoderConfigDescriptor=" + this.k + ", slConfigDescriptor=" + this.l + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ESDescriptor eSDescriptor = (ESDescriptor) obj;
        if (this.c != eSDescriptor.c || this.f != eSDescriptor.f || this.i != eSDescriptor.i || this.f3825a != eSDescriptor.f3825a || this.j != eSDescriptor.j || this.d != eSDescriptor.d || this.h != eSDescriptor.h || this.b != eSDescriptor.b || this.e != eSDescriptor.e) {
            return false;
        }
        if (this.g == null ? eSDescriptor.g != null : !this.g.equals(eSDescriptor.g)) {
            return false;
        }
        if (this.k == null ? eSDescriptor.k != null : !this.k.equals(eSDescriptor.k)) {
            return false;
        }
        if (this.m == null ? eSDescriptor.m == null : this.m.equals(eSDescriptor.m)) {
            return this.l == null ? eSDescriptor.l == null : this.l.equals(eSDescriptor.l);
        }
        return false;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = ((((((((((((((((((((((this.f3825a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + (this.g != null ? this.g.hashCode() : 0)) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + (this.k != null ? this.k.hashCode() : 0)) * 31) + (this.l != null ? this.l.hashCode() : 0)) * 31;
        if (this.m != null) {
            i2 = this.m.hashCode();
        }
        return hashCode + i2;
    }
}
