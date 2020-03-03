package org.mp4parser.streaming.extensions;

import org.mp4parser.streaming.TrackExtension;

public class NameTrackExtension implements TrackExtension {

    /* renamed from: a  reason: collision with root package name */
    private String f4065a;

    public static NameTrackExtension a(String str) {
        NameTrackExtension nameTrackExtension = new NameTrackExtension();
        nameTrackExtension.f4065a = str;
        return nameTrackExtension;
    }

    public String a() {
        return this.f4065a;
    }
}
