package org.mp4parser.boxes.iso14496.part15;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.boxes.samplegrouping.GroupEntry;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class SyncSampleEntry extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3923a = "sync";
    int b;
    int c;

    public String a() {
        return "sync";
    }

    public void a(ByteBuffer byteBuffer) {
        int f = IsoTypeReader.f(byteBuffer);
        this.b = (f & 192) >> 6;
        this.c = f & 63;
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(1);
        IsoTypeWriter.d(allocate, this.c + (this.b << 6));
        return (ByteBuffer) allocate.rewind();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SyncSampleEntry syncSampleEntry = (SyncSampleEntry) obj;
        return this.c == syncSampleEntry.c && this.b == syncSampleEntry.b;
    }

    public int hashCode() {
        return (this.b * 31) + this.c;
    }

    public int c() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int d() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String toString() {
        return "SyncSampleEntry{reserved=" + this.b + ", nalUnitType=" + this.c + Operators.BLOCK_END;
    }
}
