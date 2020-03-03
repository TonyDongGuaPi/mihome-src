package org.mp4parser.boxes.iso14496.part12;

import java.nio.ByteBuffer;

public class NullMediaHeaderBox extends AbstractMediaHeaderBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3871a = "nmhd";

    /* access modifiers changed from: protected */
    public long ai_() {
        return 4;
    }

    public NullMediaHeaderBox() {
        super("nmhd");
    }

    public void a(ByteBuffer byteBuffer) {
        e(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public void b(ByteBuffer byteBuffer) {
        f(byteBuffer);
    }
}
