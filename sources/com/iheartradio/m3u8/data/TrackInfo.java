package com.iheartradio.m3u8.data;

import java.util.Objects;

public class TrackInfo {

    /* renamed from: a  reason: collision with root package name */
    public final float f6058a;
    public final String b;

    public TrackInfo(float f, String str) {
        this.f6058a = f;
        this.b = str;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Float.valueOf(this.f6058a), this.b});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackInfo)) {
            return false;
        }
        TrackInfo trackInfo = (TrackInfo) obj;
        if (this.f6058a != trackInfo.f6058a || !Objects.equals(this.b, trackInfo.b)) {
            return false;
        }
        return true;
    }
}
