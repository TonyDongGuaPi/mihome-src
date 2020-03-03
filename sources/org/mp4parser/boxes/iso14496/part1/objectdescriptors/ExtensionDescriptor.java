package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import org.mp4parser.tools.Hex;
import org.mp4parser.tools.IsoTypeWriter;

@Descriptor(tags = {19, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253})
public class ExtensionDescriptor extends BaseDescriptor {
    private static Logger b = Logger.getLogger(ExtensionDescriptor.class.getName());

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f3826a;

    static int[] c() {
        int[] iArr = new int[148];
        for (int i = 106; i < 254; i++) {
            int i2 = i - 106;
            b.finest("pos:" + i2);
            iArr[i2] = i;
        }
        return iArr;
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        this.f3826a = byteBuffer.slice();
        byteBuffer.position(byteBuffer.position() + this.f3826a.remaining());
    }

    public String toString() {
        return "ExtensionDescriptor" + "tag=" + this.Z + ",bytes=" + Hex.a(this.f3826a.array()) + Operators.BLOCK_END;
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(l());
        IsoTypeWriter.d(allocate, this.Z);
        a(allocate, a());
        allocate.put(this.f3826a.duplicate());
        return allocate;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f3826a.remaining();
    }
}
