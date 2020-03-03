package org.mp4parser.streaming;

import java.nio.ByteBuffer;

public interface StreamingSample {
    ByteBuffer a();

    <T extends SampleExtension> T a(Class<T> cls);

    void a(SampleExtension sampleExtension);

    long b();

    <T extends SampleExtension> T b(Class<T> cls);
}
