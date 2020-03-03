package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

@Descriptor(tags = {6})
public class SLConfigDescriptor extends BaseDescriptor {

    /* renamed from: a  reason: collision with root package name */
    int f3831a;

    /* access modifiers changed from: package-private */
    public int a() {
        return 1;
    }

    public SLConfigDescriptor() {
        this.Z = 6;
    }

    public int c() {
        return this.f3831a;
    }

    public void a(int i) {
        this.f3831a = i;
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        this.f3831a = IsoTypeReader.f(byteBuffer);
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(l());
        IsoTypeWriter.d(allocate, 6);
        a(allocate, a());
        IsoTypeWriter.d(allocate, this.f3831a);
        return allocate;
    }

    public String toString() {
        return "SLConfigDescriptor" + "{predefined=" + this.f3831a + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f3831a == ((SLConfigDescriptor) obj).f3831a;
    }

    public int hashCode() {
        return this.f3831a;
    }
}
