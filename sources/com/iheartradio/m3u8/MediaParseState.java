package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.EncryptionData;
import com.iheartradio.m3u8.data.MediaPlaylist;
import com.iheartradio.m3u8.data.PlaylistType;
import com.iheartradio.m3u8.data.StartData;
import com.iheartradio.m3u8.data.TrackData;
import com.iheartradio.m3u8.data.TrackInfo;
import java.util.ArrayList;
import java.util.List;

class MediaParseState implements IParseState<MediaPlaylist> {

    /* renamed from: a  reason: collision with root package name */
    public final List<TrackData> f5992a = new ArrayList();
    public final List<String> b = new ArrayList();
    public Integer c;
    public Integer d;
    public Boolean e;
    public PlaylistType f;
    public TrackInfo g;
    public EncryptionData h;
    public StartData i;
    public String j;
    public boolean k;
    public boolean l;

    MediaParseState() {
    }

    /* renamed from: b */
    public MediaPlaylist a() throws ParseException {
        int i2 = 0;
        MediaPlaylist.Builder a2 = new MediaPlaylist.Builder().a(this.f5992a).b(this.b).a(this.c == null ? a(this.f5992a, 0.0f) : this.c.intValue()).a(this.e != null).b(!this.k).a(this.i);
        if (this.d != null) {
            i2 = this.d.intValue();
        }
        return a2.b(i2).a(this.f).a();
    }

    private static int a(List<TrackData> list, float f2) {
        for (TrackData next : list) {
            if (next.b()) {
                f2 = Math.max(f2, next.c().f6058a);
            }
        }
        return 0;
    }
}
