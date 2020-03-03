package com.ximalaya.ting.android.sdkdownloader.model;

import com.ximalaya.ting.android.opensdk.model.track.Track;
import java.util.List;

public class XmDownloadAlbumHaveTracks {

    /* renamed from: a  reason: collision with root package name */
    private XmDownloadAlbum f2366a;
    private List<Track> b;

    public XmDownloadAlbumHaveTracks(XmDownloadAlbum xmDownloadAlbum, List<Track> list) {
        this.f2366a = xmDownloadAlbum;
        this.b = list;
    }

    public XmDownloadAlbum a() {
        return this.f2366a;
    }

    public void a(XmDownloadAlbum xmDownloadAlbum) {
        this.f2366a = xmDownloadAlbum;
    }

    public List<Track> b() {
        return this.b;
    }

    public void a(List<Track> list) {
        this.b = list;
    }
}
