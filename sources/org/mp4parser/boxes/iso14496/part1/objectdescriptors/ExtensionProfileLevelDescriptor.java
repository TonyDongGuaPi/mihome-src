package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.mp4parser.tools.Hex;

@Descriptor(tags = {19})
public class ExtensionProfileLevelDescriptor extends BaseDescriptor {

    /* renamed from: a  reason: collision with root package name */
    byte[] f3827a;

    public ExtensionProfileLevelDescriptor() {
        this.Z = 19;
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        if (l() > 0) {
            this.f3827a = new byte[l()];
            byteBuffer.get(this.f3827a);
        }
    }

    public ByteBuffer b() {
        throw new RuntimeException("Not Implemented");
    }

    /* access modifiers changed from: package-private */
    public int a() {
        throw new RuntimeException("Not Implemented");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExtensionDescriptor");
        sb.append("{bytes=");
        sb.append(this.f3827a == null ? "null" : Hex.a(this.f3827a));
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
