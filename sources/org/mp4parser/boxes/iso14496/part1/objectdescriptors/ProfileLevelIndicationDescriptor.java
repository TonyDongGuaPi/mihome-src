package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

@Descriptor(tags = {20})
public class ProfileLevelIndicationDescriptor extends BaseDescriptor {

    /* renamed from: a  reason: collision with root package name */
    int f3830a;

    public int a() {
        return 1;
    }

    public ProfileLevelIndicationDescriptor() {
        this.Z = 20;
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        this.f3830a = IsoTypeReader.f(byteBuffer);
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(l());
        IsoTypeWriter.d(allocate, 20);
        a(allocate, a());
        IsoTypeWriter.d(allocate, this.f3830a);
        return allocate;
    }

    public String toString() {
        return "ProfileLevelIndicationDescriptor" + "{profileLevelIndicationIndex=" + Integer.toHexString(this.f3830a) + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f3830a == ((ProfileLevelIndicationDescriptor) obj).f3830a;
    }

    public int hashCode() {
        return this.f3830a;
    }
}
