package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.IFrameStreamInfo;
import com.iheartradio.m3u8.data.MasterPlaylist;
import com.iheartradio.m3u8.data.MediaData;
import com.iheartradio.m3u8.data.PlaylistData;
import com.iheartradio.m3u8.data.StreamInfo;
import java.util.ArrayList;
import java.util.List;

class MasterParseState implements IParseState<MasterPlaylist> {

    /* renamed from: a  reason: collision with root package name */
    public final List<PlaylistData> f5944a = new ArrayList();
    public final List<IFrameStreamInfo> b = new ArrayList();
    public final List<MediaData> c = new ArrayList();
    public final List<String> d = new ArrayList();
    public StreamInfo e;
    public boolean f;
    public boolean g;

    MasterParseState() {
    }

    public void b() {
        this.f = false;
        this.g = false;
    }

    /* renamed from: c */
    public MasterPlaylist a() throws ParseException {
        return new MasterPlaylist.Builder().a(this.f5944a).b(this.b).c(this.c).d(this.d).a();
    }
}
