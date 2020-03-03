package org.mp4parser.boxes.samplegrouping;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

public class TemporalLevelEntry extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3963a = "tele";
    private boolean b;
    private short c;

    public String a() {
        return f3963a;
    }

    public boolean c() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = (byteBuffer.get() & 128) == 128;
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(1);
        allocate.put((byte) (this.b ? 128 : 0));
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
        TemporalLevelEntry temporalLevelEntry = (TemporalLevelEntry) obj;
        return this.b == temporalLevelEntry.b && this.c == temporalLevelEntry.c;
    }

    public int hashCode() {
        return ((this.b ? 1 : 0) * true) + this.c;
    }

    public String toString() {
        return "TemporalLevelEntry" + "{levelIndependentlyDecodable=" + this.b + Operators.BLOCK_END;
    }
}
