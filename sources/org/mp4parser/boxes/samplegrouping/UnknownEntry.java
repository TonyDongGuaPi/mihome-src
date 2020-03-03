package org.mp4parser.boxes.samplegrouping;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import org.mp4parser.tools.Hex;

public class UnknownEntry extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    private ByteBuffer f3964a;
    private String b;

    public UnknownEntry(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public ByteBuffer c() {
        return this.f3964a;
    }

    public void b(ByteBuffer byteBuffer) {
        this.f3964a = (ByteBuffer) byteBuffer.duplicate().rewind();
    }

    public void a(ByteBuffer byteBuffer) {
        this.f3964a = (ByteBuffer) byteBuffer.duplicate().rewind();
    }

    public ByteBuffer b() {
        return this.f3964a.duplicate();
    }

    public String toString() {
        ByteBuffer duplicate = this.f3964a.duplicate();
        duplicate.rewind();
        byte[] bArr = new byte[duplicate.limit()];
        duplicate.get(bArr);
        return "UnknownEntry{content=" + Hex.a(bArr) + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnknownEntry unknownEntry = (UnknownEntry) obj;
        return this.f3964a == null ? unknownEntry.f3964a == null : this.f3964a.equals(unknownEntry.f3964a);
    }

    public int hashCode() {
        if (this.f3964a != null) {
            return this.f3964a.hashCode();
        }
        return 0;
    }
}
