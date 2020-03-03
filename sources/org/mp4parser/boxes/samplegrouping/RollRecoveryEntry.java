package org.mp4parser.boxes.samplegrouping;

import java.nio.ByteBuffer;

public class RollRecoveryEntry extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3959a = "roll";
    private short b;

    public String a() {
        return f3959a;
    }

    public short c() {
        return this.b;
    }

    public void a(short s) {
        this.b = s;
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = byteBuffer.getShort();
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.putShort(this.b);
        allocate.rewind();
        return allocate;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.b == ((RollRecoveryEntry) obj).b;
    }

    public int hashCode() {
        return this.b;
    }
}
