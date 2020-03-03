package org.mp4parser.boxes.iso14496.part15;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitWriterBuffer;
import org.mp4parser.tools.Hex;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class AvcDecoderConfigurationRecord {

    /* renamed from: a  reason: collision with root package name */
    public int f3917a;
    public int b;
    public int c;
    public int d;
    public int e;
    public List<ByteBuffer> f;
    public List<ByteBuffer> g;
    public boolean h;
    public int i;
    public int j;
    public int k;
    public List<ByteBuffer> l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;

    public AvcDecoderConfigurationRecord() {
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = true;
        this.i = 1;
        this.j = 0;
        this.k = 0;
        this.l = new ArrayList();
        this.m = 63;
        this.n = 7;
        this.o = 31;
        this.p = 31;
        this.q = 31;
    }

    public AvcDecoderConfigurationRecord(ByteBuffer byteBuffer) {
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = true;
        this.i = 1;
        this.j = 0;
        this.k = 0;
        this.l = new ArrayList();
        this.m = 63;
        this.n = 7;
        this.o = 31;
        this.p = 31;
        this.q = 31;
        this.f3917a = IsoTypeReader.f(byteBuffer);
        this.b = IsoTypeReader.f(byteBuffer);
        this.c = IsoTypeReader.f(byteBuffer);
        this.d = IsoTypeReader.f(byteBuffer);
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        this.m = bitReaderBuffer.a(6);
        this.e = bitReaderBuffer.a(2);
        this.n = bitReaderBuffer.a(3);
        int a2 = bitReaderBuffer.a(5);
        for (int i2 = 0; i2 < a2; i2++) {
            byte[] bArr = new byte[IsoTypeReader.d(byteBuffer)];
            byteBuffer.get(bArr);
            this.f.add(ByteBuffer.wrap(bArr));
        }
        long f2 = (long) IsoTypeReader.f(byteBuffer);
        for (int i3 = 0; ((long) i3) < f2; i3++) {
            byte[] bArr2 = new byte[IsoTypeReader.d(byteBuffer)];
            byteBuffer.get(bArr2);
            this.g.add(ByteBuffer.wrap(bArr2));
        }
        if (byteBuffer.remaining() < 4) {
            this.h = false;
        }
        if (!this.h || !(this.b == 100 || this.b == 110 || this.b == 122 || this.b == 144)) {
            this.i = -1;
            this.j = -1;
            this.k = -1;
            return;
        }
        BitReaderBuffer bitReaderBuffer2 = new BitReaderBuffer(byteBuffer);
        this.o = bitReaderBuffer2.a(6);
        this.i = bitReaderBuffer2.a(2);
        this.p = bitReaderBuffer2.a(5);
        this.j = bitReaderBuffer2.a(3);
        this.q = bitReaderBuffer2.a(5);
        this.k = bitReaderBuffer2.a(3);
        long f3 = (long) IsoTypeReader.f(byteBuffer);
        for (int i4 = 0; ((long) i4) < f3; i4++) {
            byte[] bArr3 = new byte[IsoTypeReader.d(byteBuffer)];
            byteBuffer.get(bArr3);
            this.l.add(ByteBuffer.wrap(bArr3));
        }
    }

    public void a(ByteBuffer byteBuffer) {
        IsoTypeWriter.d(byteBuffer, this.f3917a);
        IsoTypeWriter.d(byteBuffer, this.b);
        IsoTypeWriter.d(byteBuffer, this.c);
        IsoTypeWriter.d(byteBuffer, this.d);
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
        bitWriterBuffer.a(this.m, 6);
        bitWriterBuffer.a(this.e, 2);
        bitWriterBuffer.a(this.n, 3);
        bitWriterBuffer.a(this.g.size(), 5);
        for (ByteBuffer next : this.f) {
            IsoTypeWriter.b(byteBuffer, next.limit());
            byteBuffer.put((ByteBuffer) next.rewind());
        }
        IsoTypeWriter.d(byteBuffer, this.g.size());
        for (ByteBuffer next2 : this.g) {
            IsoTypeWriter.b(byteBuffer, next2.limit());
            byteBuffer.put((ByteBuffer) next2.rewind());
        }
        if (!this.h) {
            return;
        }
        if (this.b == 100 || this.b == 110 || this.b == 122 || this.b == 144) {
            BitWriterBuffer bitWriterBuffer2 = new BitWriterBuffer(byteBuffer);
            bitWriterBuffer2.a(this.o, 6);
            bitWriterBuffer2.a(this.i, 2);
            bitWriterBuffer2.a(this.p, 5);
            bitWriterBuffer2.a(this.j, 3);
            bitWriterBuffer2.a(this.q, 5);
            bitWriterBuffer2.a(this.k, 3);
            for (ByteBuffer next3 : this.l) {
                IsoTypeWriter.b(byteBuffer, next3.limit());
                byteBuffer.put((ByteBuffer) next3.reset());
            }
        }
    }

    public long a() {
        long j2;
        long j3 = 6;
        for (ByteBuffer limit : this.f) {
            j3 = j3 + 2 + ((long) limit.limit());
        }
        long j4 = j3 + 1;
        for (ByteBuffer limit2 : this.g) {
            j4 = j2 + 2 + ((long) limit2.limit());
        }
        if (this.h && (this.b == 100 || this.b == 110 || this.b == 122 || this.b == 144)) {
            j2 += 4;
            for (ByteBuffer limit3 : this.l) {
                j2 = j2 + 2 + ((long) limit3.limit());
            }
        }
        return j2;
    }

    public List<String> b() {
        ArrayList arrayList = new ArrayList(this.f.size());
        for (ByteBuffer a2 : this.f) {
            arrayList.add(Hex.a(a2));
        }
        return arrayList;
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList(this.l.size());
        for (ByteBuffer a2 : this.l) {
            arrayList.add(Hex.a(a2));
        }
        return arrayList;
    }

    public List<String> d() {
        ArrayList arrayList = new ArrayList(this.g.size());
        for (ByteBuffer a2 : this.g) {
            arrayList.add(Hex.a(a2));
        }
        return arrayList;
    }

    public String toString() {
        return "AvcDecoderConfigurationRecord{configurationVersion=" + this.f3917a + ", avcProfileIndication=" + this.b + ", profileCompatibility=" + this.c + ", avcLevelIndication=" + this.d + ", lengthSizeMinusOne=" + this.e + ", hasExts=" + this.h + ", chromaFormat=" + this.i + ", bitDepthLumaMinus8=" + this.j + ", bitDepthChromaMinus8=" + this.k + ", lengthSizeMinusOnePaddingBits=" + this.m + ", numberOfSequenceParameterSetsPaddingBits=" + this.n + ", chromaFormatPaddingBits=" + this.o + ", bitDepthLumaMinus8PaddingBits=" + this.p + ", bitDepthChromaMinus8PaddingBits=" + this.q + Operators.BLOCK_END;
    }
}
