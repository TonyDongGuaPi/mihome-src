package org.mp4parser.boxes.iso14496.part30;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.BoxParser;
import org.mp4parser.boxes.sampleentry.AbstractSampleEntry;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Path;

public class WebVTTSampleEntry extends AbstractSampleEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3929a = "wvtt";

    public WebVTTSampleEntry() {
        super(f3929a);
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        a(readableByteChannel, j, boxParser);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        writableByteChannel.write(s());
        a(writableByteChannel);
    }

    public WebVTTConfigurationBox e() {
        return (WebVTTConfigurationBox) Path.a((AbstractContainerBox) this, WebVTTConfigurationBox.f3928a);
    }

    public WebVTTSourceLabelBox f() {
        return (WebVTTSourceLabelBox) Path.a((AbstractContainerBox) this, WebVTTSourceLabelBox.f3930a);
    }
}
