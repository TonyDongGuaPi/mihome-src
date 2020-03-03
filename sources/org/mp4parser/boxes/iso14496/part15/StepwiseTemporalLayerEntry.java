package org.mp4parser.boxes.iso14496.part15;

import java.nio.ByteBuffer;
import org.mp4parser.boxes.samplegrouping.GroupEntry;

public class StepwiseTemporalLayerEntry extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3922a = "stsa";

    public String a() {
        return f3922a;
    }

    public void a(ByteBuffer byteBuffer) {
    }

    public int hashCode() {
        return 37;
    }

    public ByteBuffer b() {
        return ByteBuffer.allocate(0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }
}
