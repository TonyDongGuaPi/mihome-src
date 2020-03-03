package com.iheartradio.m3u8.data;

import com.taobao.weex.el.parse.Operators;
import java.util.Objects;

public class Playlist {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6047a = 1;
    private final MasterPlaylist b;
    private final MediaPlaylist c;
    private final boolean d;
    private final int e;

    private Playlist(MasterPlaylist masterPlaylist, MediaPlaylist mediaPlaylist, boolean z, int i) {
        this.b = masterPlaylist;
        this.c = mediaPlaylist;
        this.d = z;
        this.e = i;
    }

    public boolean a() {
        return this.b != null;
    }

    public boolean b() {
        return this.c != null;
    }

    public MasterPlaylist c() {
        return this.b;
    }

    public MediaPlaylist d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public int f() {
        return this.e;
    }

    public Builder g() {
        return new Builder(this.b, this.c, this.d, this.e);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.e), Boolean.valueOf(this.d), this.b, this.c});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Playlist)) {
            return false;
        }
        Playlist playlist = (Playlist) obj;
        if (!Objects.equals(this.b, playlist.b) || !Objects.equals(this.c, playlist.c) || this.d != playlist.d || this.e != playlist.e) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "(Playlist" + " mMasterPlaylist=" + this.b + " mMediaPlaylist=" + this.c + " mIsExtended=" + this.d + " mCompatibilityVersion=" + this.e + Operators.BRACKET_END_STR;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private MasterPlaylist f6048a;
        private MediaPlaylist b;
        private boolean c;
        private int d;

        public Builder() {
            this.d = 1;
        }

        private Builder(MasterPlaylist masterPlaylist, MediaPlaylist mediaPlaylist, boolean z, int i) {
            this.d = 1;
            this.f6048a = masterPlaylist;
            this.b = mediaPlaylist;
            this.c = z;
            this.d = i;
        }

        public Builder a(MasterPlaylist masterPlaylist) {
            this.f6048a = masterPlaylist;
            return a(true);
        }

        public Builder a(MediaPlaylist mediaPlaylist) {
            this.b = mediaPlaylist;
            return this;
        }

        public Builder a(boolean z) {
            this.c = z;
            return this;
        }

        public Builder a(int i) {
            this.d = i;
            return this;
        }

        public Playlist a() {
            return new Playlist(this.f6048a, this.b, this.c, this.d);
        }
    }
}
