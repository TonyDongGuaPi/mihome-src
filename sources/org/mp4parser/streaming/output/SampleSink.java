package org.mp4parser.streaming.output;

import java.io.Closeable;
import java.io.IOException;
import org.mp4parser.streaming.StreamingSample;
import org.mp4parser.streaming.StreamingTrack;

public interface SampleSink extends Closeable {
    void a(StreamingSample streamingSample, StreamingTrack streamingTrack) throws IOException;

    void close() throws IOException;
}
