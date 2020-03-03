package org.mp4parser.muxer.tracks.webvtt.sampleboxes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.Box;
import org.mp4parser.IsoFile;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.Utf8;

public abstract class AbstractCueBox implements Box {

    /* renamed from: a  reason: collision with root package name */
    String f4060a = "";
    String b;

    public AbstractCueBox(String str) {
        this.b = str;
    }

    public String a() {
        return this.f4060a;
    }

    public void a(String str) {
        this.f4060a = str;
    }

    public long c() {
        return (long) (Utf8.b(this.f4060a) + 8);
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.a(c()));
        IsoTypeWriter.b(allocate, c());
        allocate.put(IsoFile.a(ae_()));
        allocate.put(Utf8.a(this.f4060a));
        writableByteChannel.write((ByteBuffer) allocate.rewind());
    }

    public String ae_() {
        return this.b;
    }
}
