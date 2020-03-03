package org.mp4parser.streaming;

import java.io.Closeable;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.streaming.output.SampleSink;

public interface StreamingTrack extends Closeable {
    long a();

    <T extends TrackExtension> T a(Class<T> cls);

    void a(TrackExtension trackExtension);

    void a(SampleSink sampleSink);

    String b();

    void b(Class<? extends TrackExtension> cls);

    String c();

    SampleDescriptionBox d();
}
