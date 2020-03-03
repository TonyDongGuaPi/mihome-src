package org.mp4parser.boxes.sampleentry;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.support.AbstractContainerBox;

public abstract class AbstractSampleEntry extends AbstractContainerBox implements SampleEntry {
    protected int r = 1;

    public abstract void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException;

    public abstract void b(WritableByteChannel writableByteChannel) throws IOException;

    protected AbstractSampleEntry(String str) {
        super(str);
    }

    public int d() {
        return this.r;
    }

    public void a(int i) {
        this.r = i;
    }
}
