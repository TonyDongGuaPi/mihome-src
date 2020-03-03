package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.mp4parser.tools.Hex;
import org.mp4parser.tools.IsoTypeWriter;

@Descriptor(tags = {5})
public class DecoderSpecificInfo extends BaseDescriptor {

    /* renamed from: a  reason: collision with root package name */
    byte[] f3824a;

    public DecoderSpecificInfo() {
        this.Z = 5;
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        this.f3824a = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.f3824a);
    }

    public void a(byte[] bArr) {
        this.f3824a = bArr;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f3824a.length;
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(l());
        IsoTypeWriter.d(allocate, this.Z);
        a(allocate, a());
        allocate.put(this.f3824a);
        return (ByteBuffer) allocate.rewind();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DecoderSpecificInfo");
        sb.append("{bytes=");
        sb.append(this.f3824a == null ? "null" : Hex.a(this.f3824a));
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Arrays.equals(this.f3824a, ((DecoderSpecificInfo) obj).f3824a);
    }

    public int hashCode() {
        if (this.f3824a != null) {
            return Arrays.hashCode(this.f3824a);
        }
        return 0;
    }
}
