package com.iheartradio.m3u8.data;

import com.taobao.weex.el.parse.Operators;
import java.util.Objects;

public class PlaylistData {

    /* renamed from: a  reason: collision with root package name */
    private final String f6049a;
    private final StreamInfo b;

    private PlaylistData(String str, StreamInfo streamInfo) {
        this.f6049a = str;
        this.b = streamInfo;
    }

    public String a() {
        return this.f6049a;
    }

    public boolean b() {
        return this.b != null;
    }

    public StreamInfo c() {
        return this.b;
    }

    public Builder d() {
        return new Builder(this.f6049a, this.b);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(super.hashCode()), this.b});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaylistData)) {
            return false;
        }
        PlaylistData playlistData = (PlaylistData) obj;
        if (!super.equals(playlistData) || !Objects.equals(this.b, playlistData.b)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "PlaylistData [mStreamInfo=" + this.b + ", mUri=" + this.f6049a + Operators.ARRAY_END_STR;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private String f6050a;
        private StreamInfo b;

        public Builder() {
        }

        private Builder(String str, StreamInfo streamInfo) {
            this.f6050a = str;
            this.b = streamInfo;
        }

        public Builder a(String str) {
            this.f6050a = str;
            return this;
        }

        public Builder b(String str) {
            this.f6050a = str;
            return this;
        }

        public Builder a(StreamInfo streamInfo) {
            this.b = streamInfo;
            return this;
        }

        public PlaylistData a() {
            return new PlaylistData(this.f6050a, this.b);
        }
    }
}
