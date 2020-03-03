package org.mp4parser.muxer.builder;

import org.mp4parser.muxer.Track;

public interface Fragmenter {
    long[] a(Track track);
}
