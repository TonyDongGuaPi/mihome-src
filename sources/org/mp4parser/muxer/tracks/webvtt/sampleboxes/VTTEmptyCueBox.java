package org.mp4parser.muxer.tracks.webvtt.sampleboxes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.Box;
import org.mp4parser.IsoFile;
import org.mp4parser.tools.IsoTypeWriter;

public class VTTEmptyCueBox implements Box {
    public String ae_() {
        return "vtte";
    }

    public long c() {
        return 8;
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        IsoTypeWriter.b(allocate, c());
        allocate.put(IsoFile.a(ae_()));
        writableByteChannel.write((ByteBuffer) allocate.rewind());
    }
}
