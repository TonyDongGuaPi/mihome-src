package org.mp4parser.boxes.iso14496.part12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.BoxParser;
import org.mp4parser.Container;
import org.mp4parser.ParsableBox;
import org.mp4parser.tools.CastUtils;
import org.mp4parser.tools.IsoTypeWriter;

public class FreeBox implements ParsableBox {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3848a = "free";
    ByteBuffer b;
    List<ParsableBox> c;
    private Container d;
    private long e;

    public String ae_() {
        return f3848a;
    }

    public FreeBox() {
        this.c = new LinkedList();
        this.b = ByteBuffer.wrap(new byte[0]);
    }

    public FreeBox(int i) {
        this.c = new LinkedList();
        this.b = ByteBuffer.allocate(i);
    }

    public ByteBuffer a() {
        if (this.b != null) {
            return (ByteBuffer) this.b.duplicate().rewind();
        }
        return null;
    }

    public void a(ByteBuffer byteBuffer) {
        this.b = byteBuffer;
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        for (ParsableBox b2 : this.c) {
            b2.b(writableByteChannel);
        }
        ByteBuffer allocate = ByteBuffer.allocate(8);
        IsoTypeWriter.b(allocate, (long) (this.b.limit() + 8));
        allocate.put(f3848a.getBytes());
        allocate.rewind();
        writableByteChannel.write(allocate);
        allocate.rewind();
        this.b.rewind();
        writableByteChannel.write(this.b);
        this.b.rewind();
    }

    public long c() {
        long j = 8;
        for (ParsableBox c2 : this.c) {
            j += c2.c();
        }
        return j + ((long) this.b.limit());
    }

    public void a(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, BoxParser boxParser) throws IOException {
        this.b = ByteBuffer.allocate(CastUtils.a(j));
        int i = 0;
        do {
            i += readableByteChannel.read(this.b);
        } while (((long) i) < j);
    }

    public void a(ParsableBox parsableBox) {
        this.b.position(CastUtils.a(parsableBox.c()));
        this.b = this.b.slice();
        this.c.add(parsableBox);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FreeBox freeBox = (FreeBox) obj;
        return a() == null ? freeBox.a() == null : a().equals(freeBox.a());
    }

    public int hashCode() {
        if (this.b != null) {
            return this.b.hashCode();
        }
        return 0;
    }
}
