package org.mp4parser.streaming.input;

import java.util.HashMap;
import org.mp4parser.boxes.iso14496.part12.TrackHeaderBox;
import org.mp4parser.streaming.StreamingTrack;
import org.mp4parser.streaming.TrackExtension;
import org.mp4parser.streaming.output.SampleSink;

public abstract class AbstractStreamingTrack implements StreamingTrack {

    /* renamed from: a  reason: collision with root package name */
    protected TrackHeaderBox f4068a = new TrackHeaderBox();
    protected HashMap<Class<? extends TrackExtension>, TrackExtension> b = new HashMap<>();
    protected SampleSink c;

    public AbstractStreamingTrack() {
        this.f4068a.a(1);
    }

    public void a(SampleSink sampleSink) {
        this.c = sampleSink;
    }

    public <T extends TrackExtension> T a(Class<T> cls) {
        return (TrackExtension) this.b.get(cls);
    }

    public void a(TrackExtension trackExtension) {
        this.b.put(trackExtension.getClass(), trackExtension);
    }

    public void b(Class<? extends TrackExtension> cls) {
        this.b.remove(cls);
    }
}
