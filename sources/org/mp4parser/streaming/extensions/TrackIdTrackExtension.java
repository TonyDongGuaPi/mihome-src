package org.mp4parser.streaming.extensions;

import org.mp4parser.streaming.TrackExtension;

public class TrackIdTrackExtension implements TrackExtension {

    /* renamed from: a  reason: collision with root package name */
    private long f4067a = 1;

    public TrackIdTrackExtension(long j) {
        this.f4067a = j;
    }

    public long a() {
        return this.f4067a;
    }
}
