package org.mp4parser.muxer.builder;

import java.util.Map;
import org.mp4parser.muxer.Track;

public class StaticFragmentIntersectionFinderImpl implements Fragmenter {

    /* renamed from: a  reason: collision with root package name */
    Map<Track, long[]> f3998a;

    public StaticFragmentIntersectionFinderImpl(Map<Track, long[]> map) {
        this.f3998a = map;
    }

    public long[] a(Track track) {
        return this.f3998a.get(track);
    }
}
