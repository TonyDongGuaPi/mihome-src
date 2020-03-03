package org.mp4parser.muxer.tracks.webvtt.sampleboxes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.mp4parser.Box;
import org.mp4parser.IsoFile;
import org.mp4parser.tools.IsoTypeWriter;

public class VTTCueBox implements Box {

    /* renamed from: a  reason: collision with root package name */
    CueSourceIDBox f4061a;
    CueIDBox b;
    CueTimeBox c;
    CueSettingsBox d;
    CuePayloadBox e;

    public String ae_() {
        return "vtcc";
    }

    public long c() {
        long j = 0;
        long c2 = (this.f4061a != null ? this.f4061a.c() : 0) + 8 + (this.b != null ? this.b.c() : 0) + (this.c != null ? this.c.c() : 0) + (this.d != null ? this.d.c() : 0);
        if (this.e != null) {
            j = this.e.c();
        }
        return c2 + j;
    }

    public void b(WritableByteChannel writableByteChannel) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        IsoTypeWriter.b(allocate, c());
        allocate.put(IsoFile.a(ae_()));
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        if (this.f4061a != null) {
            this.f4061a.b(writableByteChannel);
        }
        if (this.b != null) {
            this.b.b(writableByteChannel);
        }
        if (this.c != null) {
            this.c.b(writableByteChannel);
        }
        if (this.d != null) {
            this.d.b(writableByteChannel);
        }
        if (this.e != null) {
            this.e.b(writableByteChannel);
        }
    }

    public CueSourceIDBox a() {
        return this.f4061a;
    }

    public void a(CueSourceIDBox cueSourceIDBox) {
        this.f4061a = cueSourceIDBox;
    }

    public CueIDBox d() {
        return this.b;
    }

    public void a(CueIDBox cueIDBox) {
        this.b = cueIDBox;
    }

    public CueTimeBox e() {
        return this.c;
    }

    public void a(CueTimeBox cueTimeBox) {
        this.c = cueTimeBox;
    }

    public CueSettingsBox f() {
        return this.d;
    }

    public void a(CueSettingsBox cueSettingsBox) {
        this.d = cueSettingsBox;
    }

    public CuePayloadBox g() {
        return this.e;
    }

    public void a(CuePayloadBox cuePayloadBox) {
        this.e = cuePayloadBox;
    }
}
