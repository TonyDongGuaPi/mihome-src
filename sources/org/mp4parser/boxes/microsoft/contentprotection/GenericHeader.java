package org.mp4parser.boxes.microsoft.contentprotection;

import java.nio.ByteBuffer;
import java.util.UUID;
import org.mp4parser.boxes.microsoft.ProtectionSpecificHeader;

public class GenericHeader extends ProtectionSpecificHeader {
    public static UUID b = UUID.fromString("00000000-0000-0000-0000-000000000000");
    ByteBuffer c;

    static {
        ProtectionSpecificHeader.f3938a.put(b, GenericHeader.class);
    }

    public UUID a() {
        return b;
    }

    public void a(ByteBuffer byteBuffer) {
        this.c = byteBuffer;
    }

    public ByteBuffer b() {
        return this.c;
    }
}
