package org.mp4parser.boxes.samplegrouping;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

public class VisualRandomAccessEntry extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3965a = "rap ";
    private boolean b;
    private short c;

    public String a() {
        return f3965a;
    }

    public boolean c() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public short d() {
        return this.c;
    }

    public void a(short s) {
        this.c = s;
    }

    public void a(ByteBuffer byteBuffer) {
        byte b2 = byteBuffer.get();
        this.b = (b2 & 128) == 128;
        this.c = (short) (b2 & Byte.MAX_VALUE);
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(1);
        allocate.put((byte) ((this.b ? (short) 128 : 0) | (this.c & 127)));
        allocate.rewind();
        return allocate;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VisualRandomAccessEntry visualRandomAccessEntry = (VisualRandomAccessEntry) obj;
        return this.c == visualRandomAccessEntry.c && this.b == visualRandomAccessEntry.b;
    }

    public int hashCode() {
        return ((this.b ? 1 : 0) * true) + this.c;
    }

    public String toString() {
        return "VisualRandomAccessEntry" + "{numLeadingSamplesKnown=" + this.b + ", numLeadingSamples=" + this.c + Operators.BLOCK_END;
    }
}
