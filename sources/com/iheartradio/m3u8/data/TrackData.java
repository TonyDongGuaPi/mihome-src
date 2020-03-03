package com.iheartradio.m3u8.data;

import com.taobao.weex.el.parse.Operators;
import java.util.Objects;

public class TrackData {

    /* renamed from: a  reason: collision with root package name */
    private final String f6056a;
    private final TrackInfo b;
    private final EncryptionData c;
    private final String d;
    private final boolean e;

    private TrackData(String str, TrackInfo trackInfo, EncryptionData encryptionData, String str2, boolean z) {
        this.f6056a = str;
        this.b = trackInfo;
        this.c = encryptionData;
        this.d = str2;
        this.e = z;
    }

    public String a() {
        return this.f6056a;
    }

    public boolean b() {
        return this.b != null;
    }

    public TrackInfo c() {
        return this.b;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return (!d() || this.c.a() == null || this.c.a() == EncryptionMethod.NONE) ? false : true;
    }

    public boolean f() {
        return this.d != null && this.d.length() > 0;
    }

    public String g() {
        return this.d;
    }

    public boolean h() {
        return this.e;
    }

    public EncryptionData i() {
        return this.c;
    }

    public Builder j() {
        return new Builder(a(), this.b, this.c, this.e);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.f6056a, this.c, this.b, Boolean.valueOf(this.e)});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackData)) {
            return false;
        }
        TrackData trackData = (TrackData) obj;
        if (!Objects.equals(this.f6056a, trackData.f6056a) || !Objects.equals(this.b, trackData.b) || !Objects.equals(this.c, trackData.c) || !Objects.equals(this.d, trackData.d) || !Objects.equals(Boolean.valueOf(this.e), Boolean.valueOf(trackData.e))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "(TrackData" + " mUri=" + this.f6056a + " mTrackInfo=" + this.b + " mEncryptionData=" + this.c + " mProgramDateTime=" + this.d + " mHasDiscontinuity=" + this.e + Operators.BRACKET_END_STR;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private String f6057a;
        private TrackInfo b;
        private EncryptionData c;
        private String d;
        private boolean e;

        public Builder() {
        }

        private Builder(String str, TrackInfo trackInfo, EncryptionData encryptionData, boolean z) {
            this.f6057a = str;
            this.b = trackInfo;
            this.c = encryptionData;
            this.e = z;
        }

        public Builder a(String str) {
            this.f6057a = str;
            return this;
        }

        public Builder a(TrackInfo trackInfo) {
            this.b = trackInfo;
            return this;
        }

        public Builder a(EncryptionData encryptionData) {
            this.c = encryptionData;
            return this;
        }

        public Builder b(String str) {
            this.d = str;
            return this;
        }

        public Builder a(boolean z) {
            this.e = z;
            return this;
        }

        public TrackData a() {
            return new TrackData(this.f6057a, this.b, this.c, this.d, this.e);
        }
    }
}
