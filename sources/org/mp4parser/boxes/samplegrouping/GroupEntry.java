package org.mp4parser.boxes.samplegrouping;

import java.nio.ByteBuffer;

public abstract class GroupEntry {
    public abstract String a();

    public abstract void a(ByteBuffer byteBuffer);

    public abstract ByteBuffer b();

    public int n() {
        return b().limit();
    }
}
