package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.mp4parser.tools.Hex;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

@Descriptor(tags = {4})
public class DecoderConfigDescriptor extends BaseDescriptor {
    private static Logger k = Logger.getLogger(DecoderConfigDescriptor.class.getName());

    /* renamed from: a  reason: collision with root package name */
    int f3823a;
    int b;
    int c;
    int d;
    long e;
    long f;
    DecoderSpecificInfo g;
    AudioSpecificConfig h;
    List<ProfileLevelIndicationDescriptor> i = new ArrayList();
    byte[] j;

    public DecoderConfigDescriptor() {
        this.Z = 4;
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        int l;
        this.f3823a = IsoTypeReader.f(byteBuffer);
        int f2 = IsoTypeReader.f(byteBuffer);
        this.b = f2 >>> 2;
        this.c = (f2 >> 1) & 1;
        this.d = IsoTypeReader.c(byteBuffer);
        this.e = IsoTypeReader.b(byteBuffer);
        this.f = IsoTypeReader.b(byteBuffer);
        while (byteBuffer.remaining() > 2) {
            int position = byteBuffer.position();
            BaseDescriptor a2 = ObjectDescriptorFactory.a(this.f3823a, byteBuffer);
            int position2 = byteBuffer.position() - position;
            Logger logger = k;
            StringBuilder sb = new StringBuilder();
            sb.append(a2);
            sb.append(" - DecoderConfigDescr1 read: ");
            sb.append(position2);
            sb.append(", size: ");
            sb.append(a2 != null ? Integer.valueOf(a2.l()) : null);
            logger.finer(sb.toString());
            if (a2 != null && position2 < (l = a2.l())) {
                this.j = new byte[(l - position2)];
                byteBuffer.get(this.j);
            }
            if (a2 instanceof DecoderSpecificInfo) {
                this.g = (DecoderSpecificInfo) a2;
            } else if (a2 instanceof AudioSpecificConfig) {
                this.h = (AudioSpecificConfig) a2;
            } else if (a2 instanceof ProfileLevelIndicationDescriptor) {
                this.i.add((ProfileLevelIndicationDescriptor) a2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int a() {
        int i2 = 0;
        int l = (this.h == null ? 0 : this.h.l()) + 13;
        if (this.g != null) {
            i2 = this.g.l();
        }
        int i3 = l + i2;
        for (ProfileLevelIndicationDescriptor l2 : this.i) {
            i3 += l2.l();
        }
        return i3;
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(l());
        IsoTypeWriter.d(allocate, this.Z);
        a(allocate, a());
        IsoTypeWriter.d(allocate, this.f3823a);
        IsoTypeWriter.d(allocate, (this.b << 2) | (this.c << 1) | 1);
        IsoTypeWriter.a(allocate, this.d);
        IsoTypeWriter.b(allocate, this.e);
        IsoTypeWriter.b(allocate, this.f);
        if (this.g != null) {
            allocate.put(this.g.b());
        }
        if (this.h != null) {
            allocate.put(this.h.b());
        }
        for (ProfileLevelIndicationDescriptor b2 : this.i) {
            allocate.put(b2.b());
        }
        return (ByteBuffer) allocate.rewind();
    }

    public DecoderSpecificInfo c() {
        return this.g;
    }

    public void a(DecoderSpecificInfo decoderSpecificInfo) {
        this.g = decoderSpecificInfo;
    }

    public AudioSpecificConfig d() {
        return this.h;
    }

    public void a(AudioSpecificConfig audioSpecificConfig) {
        this.h = audioSpecificConfig;
    }

    public List<ProfileLevelIndicationDescriptor> e() {
        return this.i;
    }

    public int f() {
        return this.f3823a;
    }

    public void a(int i2) {
        this.f3823a = i2;
    }

    public int g() {
        return this.b;
    }

    public void b(int i2) {
        this.b = i2;
    }

    public int h() {
        return this.c;
    }

    public void c(int i2) {
        this.c = i2;
    }

    public int i() {
        return this.d;
    }

    public void d(int i2) {
        this.d = i2;
    }

    public long m() {
        return this.e;
    }

    public void a(long j2) {
        this.e = j2;
    }

    public long n() {
        return this.f;
    }

    public void b(long j2) {
        this.f = j2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DecoderConfigDescriptor");
        sb.append("{objectTypeIndication=");
        sb.append(this.f3823a);
        sb.append(", streamType=");
        sb.append(this.b);
        sb.append(", upStream=");
        sb.append(this.c);
        sb.append(", bufferSizeDB=");
        sb.append(this.d);
        sb.append(", maxBitRate=");
        sb.append(this.e);
        sb.append(", avgBitRate=");
        sb.append(this.f);
        sb.append(", decoderSpecificInfo=");
        sb.append(this.g);
        sb.append(", audioSpecificInfo=");
        sb.append(this.h);
        sb.append(", configDescriptorDeadBytes=");
        sb.append(Hex.a(this.j != null ? this.j : new byte[0]));
        sb.append(", profileLevelIndicationDescriptors=");
        sb.append(this.i == null ? "null" : Arrays.asList(new List[]{this.i}).toString());
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
