package org.mp4parser.boxes.iso14496.part12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.support.AbstractContainerBox;

public class UserDataBox extends AbstractContainerBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3912a = "udta";

    public UserDataBox() {
        super("udta");
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        super.a(readableByteChannel, byteBuffer, j, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        super.b(writableByteChannel);
    }
}
