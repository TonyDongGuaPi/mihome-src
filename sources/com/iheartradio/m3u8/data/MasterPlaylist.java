package com.iheartradio.m3u8.data;

import com.taobao.weex.el.parse.Operators;
import java.util.List;
import java.util.Objects;

public class MasterPlaylist {

    /* renamed from: a  reason: collision with root package name */
    private final List<PlaylistData> f6041a;
    private final List<IFrameStreamInfo> b;
    private final List<MediaData> c;
    private final List<String> d;

    private MasterPlaylist(List<PlaylistData> list, List<IFrameStreamInfo> list2, List<MediaData> list3, List<String> list4) {
        this.f6041a = DataUtil.a(list);
        this.b = DataUtil.a(list2);
        this.c = DataUtil.a(list3);
        this.d = DataUtil.a(list4);
    }

    public List<PlaylistData> a() {
        return this.f6041a;
    }

    public List<IFrameStreamInfo> b() {
        return this.b;
    }

    public List<MediaData> c() {
        return this.c;
    }

    public boolean d() {
        return this.d.size() > 0;
    }

    public List<String> e() {
        return this.d;
    }

    public Builder f() {
        return new Builder(this.f6041a, this.c);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.c, this.f6041a, this.b, this.d});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MasterPlaylist)) {
            return false;
        }
        MasterPlaylist masterPlaylist = (MasterPlaylist) obj;
        if (!Objects.equals(this.c, masterPlaylist.c) || !Objects.equals(this.f6041a, masterPlaylist.f6041a) || !Objects.equals(this.b, masterPlaylist.b) || !Objects.equals(this.d, masterPlaylist.d)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "(MasterPlaylist" + " mPlaylists=" + this.f6041a.toString() + " mIFramePlaylists=" + this.b.toString() + " mMediaData=" + this.c.toString() + " mUnknownTags=" + this.d.toString() + Operators.BRACKET_END_STR;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private List<PlaylistData> f6042a;
        private List<IFrameStreamInfo> b;
        private List<MediaData> c;
        private List<String> d;

        public Builder() {
        }

        private Builder(List<PlaylistData> list, List<MediaData> list2) {
            this.f6042a = list;
            this.c = list2;
        }

        public Builder a(List<PlaylistData> list) {
            this.f6042a = list;
            return this;
        }

        public Builder b(List<IFrameStreamInfo> list) {
            this.b = list;
            return this;
        }

        public Builder c(List<MediaData> list) {
            this.c = list;
            return this;
        }

        public Builder d(List<String> list) {
            this.d = list;
            return this;
        }

        public MasterPlaylist a() {
            return new MasterPlaylist(this.f6042a, this.b, this.c, this.d);
        }
    }
}
