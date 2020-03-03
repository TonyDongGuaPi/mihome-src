package com.iheartradio.m3u8.data;

import com.taobao.weex.el.parse.Operators;
import java.util.List;
import java.util.Objects;

public class MediaPlaylist {

    /* renamed from: a  reason: collision with root package name */
    private final List<TrackData> f6045a;
    private final List<String> b;
    private final int c;
    private final int d;
    private final boolean e;
    private final boolean f;
    private final PlaylistType g;
    private final StartData h;

    private MediaPlaylist(List<TrackData> list, List<String> list2, int i, StartData startData, int i2, boolean z, boolean z2, PlaylistType playlistType) {
        this.f6045a = DataUtil.a(list);
        this.b = DataUtil.a(list2);
        this.c = i;
        this.d = i2;
        this.e = z;
        this.f = z2;
        this.h = startData;
        this.g = playlistType;
    }

    public boolean a() {
        return !this.f6045a.isEmpty();
    }

    public List<TrackData> b() {
        return this.f6045a;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return !this.b.isEmpty();
    }

    public List<String> h() {
        return this.b;
    }

    public StartData i() {
        return this.h;
    }

    public boolean j() {
        return this.h != null;
    }

    public PlaylistType k() {
        return this.g;
    }

    public boolean l() {
        return this.g != null;
    }

    public int a(int i) {
        if (i < 0 || i >= this.f6045a.size()) {
            throw new IndexOutOfBoundsException();
        }
        int i2 = 0;
        for (int i3 = 0; i3 <= i; i3++) {
            if (this.f6045a.get(i3).h()) {
                i2++;
            }
        }
        return i2;
    }

    public Builder m() {
        return new Builder(this.f6045a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.f6045a, this.b, Integer.valueOf(this.c), Integer.valueOf(this.d), Boolean.valueOf(this.e), Boolean.valueOf(this.f), this.g, this.h});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaPlaylist)) {
            return false;
        }
        MediaPlaylist mediaPlaylist = (MediaPlaylist) obj;
        if (!Objects.equals(this.f6045a, mediaPlaylist.f6045a) || !Objects.equals(this.b, mediaPlaylist.b) || this.c != mediaPlaylist.c || this.d != mediaPlaylist.d || this.e != mediaPlaylist.e || this.f != mediaPlaylist.f || !Objects.equals(this.g, mediaPlaylist.g) || !Objects.equals(this.h, mediaPlaylist.h)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "(MediaPlaylist" + " mTracks=" + this.f6045a + " mUnknownTags=" + this.b + " mTargetDuration=" + this.c + " mMediaSequenceNumber=" + this.d + " mIsIframesOnly=" + this.e + " mIsOngoing=" + this.f + " mPlaylistType=" + this.g + " mStartData=" + this.h + Operators.BRACKET_END_STR;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private List<TrackData> f6046a;
        private List<String> b;
        private int c;
        private int d;
        private boolean e;
        private boolean f;
        private PlaylistType g;
        private StartData h;

        public Builder() {
        }

        private Builder(List<TrackData> list, List<String> list2, int i, int i2, boolean z, boolean z2, PlaylistType playlistType, StartData startData) {
            this.f6046a = list;
            this.b = list2;
            this.c = i;
            this.d = i2;
            this.e = z;
            this.f = z2;
            this.g = playlistType;
            this.h = startData;
        }

        public Builder a(List<TrackData> list) {
            this.f6046a = list;
            return this;
        }

        public Builder b(List<String> list) {
            this.b = list;
            return this;
        }

        public Builder a(int i) {
            this.c = i;
            return this;
        }

        public Builder a(StartData startData) {
            this.h = startData;
            return this;
        }

        public Builder b(int i) {
            this.d = i;
            return this;
        }

        public Builder a(boolean z) {
            this.e = z;
            return this;
        }

        public Builder b(boolean z) {
            this.f = z;
            return this;
        }

        public Builder a(PlaylistType playlistType) {
            this.g = playlistType;
            return this;
        }

        public MediaPlaylist a() {
            return new MediaPlaylist(this.f6046a, this.b, this.c, this.h, this.d, this.e, this.f, this.g);
        }
    }
}
