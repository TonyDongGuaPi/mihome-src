package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.mp4parser.tools.IsoTypeReader;

@Descriptor(tags = {0})
public abstract class BaseDescriptor {
    static final /* synthetic */ boolean ac = (!BaseDescriptor.class.desiredAssertionStatus());
    int Z;
    int aa;
    int ab;

    /* access modifiers changed from: package-private */
    public abstract int a();

    public abstract void a(ByteBuffer byteBuffer) throws IOException;

    public abstract ByteBuffer b();

    public int j() {
        return this.Z;
    }

    public void a(ByteBuffer byteBuffer, int i) {
        int position = byteBuffer.position();
        int i2 = 0;
        while (true) {
            if (i > 0 || i2 < this.ab) {
                i2++;
                if (i > 0) {
                    byteBuffer.put((k() + position) - i2, (byte) (i & 127));
                } else {
                    byteBuffer.put((k() + position) - i2, Byte.MIN_VALUE);
                }
                i >>>= 7;
            } else {
                byteBuffer.position(position + k());
                return;
            }
        }
    }

    public int k() {
        int a2 = a();
        int i = 0;
        while (true) {
            if (a2 <= 0 && i >= this.ab) {
                return i;
            }
            a2 >>>= 7;
            i++;
        }
    }

    public int l() {
        return a() + k() + 1;
    }

    public final void a(int i, ByteBuffer byteBuffer) throws IOException {
        this.Z = i;
        int f = IsoTypeReader.f(byteBuffer);
        this.aa = f & 127;
        int i2 = 1;
        while ((f >>> 7) == 1) {
            f = IsoTypeReader.f(byteBuffer);
            i2++;
            this.aa = (this.aa << 7) | (f & 127);
        }
        this.ab = i2;
        ByteBuffer slice = byteBuffer.slice();
        slice.limit(this.aa);
        a(slice);
        if (ac || slice.remaining() == 0) {
            byteBuffer.position(byteBuffer.position() + this.aa);
            return;
        }
        throw new AssertionError(String.valueOf(getClass().getSimpleName()) + " has not been fully parsed");
    }

    public String toString() {
        return "BaseDescriptor" + "{tag=" + this.Z + ", sizeOfInstance=" + this.aa + Operators.BLOCK_END;
    }
}
