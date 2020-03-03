package com.ximalaya.ting.android.opensdk.model.customized;

import android.support.annotation.Nullable;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import java.util.ArrayList;
import java.util.List;

public class XmCustomizedModelUtil {
    @Nullable
    public static Track a(ColumnItems columnItems) {
        if (columnItems == null) {
            return null;
        }
        Track track = new Track();
        track.a("track");
        track.a((long) columnItems.a());
        track.w(columnItems.d());
        track.h(columnItems.e());
        track.j(columnItems.g());
        track.k(columnItems.A());
        track.l(columnItems.j());
        track.m(columnItems.i());
        track.q(columnItems.k());
        track.u((int) columnItems.n());
        track.v((int) columnItems.o());
        track.q(columnItems.p() + "");
        track.s(columnItems.q() + "");
        track.j(columnItems.r());
        track.n((long) columnItems.s());
        track.m(columnItems.t());
        track.l(columnItems.u());
        track.n(columnItems.v());
        track.o(columnItems.w());
        track.p(columnItems.x());
        track.r(columnItems.y());
        track.t(columnItems.z());
        track.p(columnItems.B());
        track.a(columnItems.C());
        return track;
    }

    @Nullable
    public static List<Track> a(List<ColumnItems> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ColumnItems a2 : list) {
            arrayList.add(a(a2));
        }
        return arrayList;
    }

    @Nullable
    public static Album a(ColumnAlbumItem columnAlbumItem) {
        if (columnAlbumItem == null) {
            return null;
        }
        Album album = new Album();
        album.a((long) columnAlbumItem.a());
        album.a(columnAlbumItem.e());
        album.b(columnAlbumItem.f());
        album.c(columnAlbumItem.g());
        album.f(columnAlbumItem.k());
        album.d(columnAlbumItem.i());
        album.e(columnAlbumItem.j());
        album.b((long) columnAlbumItem.l());
        album.d((long) columnAlbumItem.n());
        album.a(columnAlbumItem.o());
        album.f(columnAlbumItem.p());
        album.e(columnAlbumItem.q());
        return album;
    }

    public static List<Album> b(List<ColumnAlbumItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ColumnAlbumItem a2 : list) {
            arrayList.add(a(a2));
        }
        return arrayList;
    }
}
