package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.IFrameStreamInfo;
import com.iheartradio.m3u8.data.MasterPlaylist;
import com.iheartradio.m3u8.data.MediaData;
import com.iheartradio.m3u8.data.MediaPlaylist;
import com.iheartradio.m3u8.data.MediaType;
import com.iheartradio.m3u8.data.Playlist;
import com.iheartradio.m3u8.data.PlaylistData;
import com.iheartradio.m3u8.data.StartData;
import com.iheartradio.m3u8.data.TrackData;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PlaylistValidation {

    /* renamed from: a  reason: collision with root package name */
    private final Set<PlaylistError> f6031a;

    private PlaylistValidation(Set<PlaylistError> set) {
        this.f6031a = Collections.unmodifiableSet(set);
    }

    public String toString() {
        return "(PlaylistValidation" + " valid=" + a() + " errors=" + this.f6031a + Operators.BRACKET_END_STR;
    }

    public boolean a() {
        return this.f6031a.isEmpty();
    }

    public Set<PlaylistError> b() {
        return this.f6031a;
    }

    public static PlaylistValidation a(Playlist playlist) {
        return a(playlist, ParsingMode.f6027a);
    }

    public static PlaylistValidation a(Playlist playlist, ParsingMode parsingMode) {
        HashSet hashSet = new HashSet();
        if (playlist == null) {
            hashSet.add(PlaylistError.NO_PLAYLIST);
            return new PlaylistValidation(hashSet);
        }
        if (playlist.f() < 1) {
            hashSet.add(PlaylistError.COMPATIBILITY_TOO_LOW);
        }
        if (b(playlist)) {
            hashSet.add(PlaylistError.NO_MASTER_OR_MEDIA);
        } else if (c(playlist)) {
            hashSet.add(PlaylistError.BOTH_MASTER_AND_MEDIA);
        }
        if (playlist.a()) {
            if (!playlist.e()) {
                hashSet.add(PlaylistError.MASTER_NOT_EXTENDED);
            }
            a(playlist.c(), (Set<PlaylistError>) hashSet);
        }
        if (playlist.b()) {
            a(playlist.d(), (Set<PlaylistError>) hashSet, playlist.e(), parsingMode);
        }
        return new PlaylistValidation(hashSet);
    }

    private static boolean b(Playlist playlist) {
        return !playlist.a() && !playlist.b();
    }

    private static boolean c(Playlist playlist) {
        return playlist.a() && playlist.b();
    }

    private static void a(MasterPlaylist masterPlaylist, Set<PlaylistError> set) {
        for (PlaylistData a2 : masterPlaylist.a()) {
            a(a2, set);
        }
        for (IFrameStreamInfo a3 : masterPlaylist.b()) {
            a(a3, set);
        }
        for (MediaData a4 : masterPlaylist.c()) {
            a(a4, set);
        }
    }

    private static void a(MediaPlaylist mediaPlaylist, Set<PlaylistError> set, boolean z, ParsingMode parsingMode) {
        if (z && mediaPlaylist.j()) {
            a(mediaPlaylist.i(), set);
        }
        for (TrackData a2 : mediaPlaylist.b()) {
            a(a2, set, z, parsingMode);
        }
    }

    private static void a(StartData startData, Set<PlaylistError> set) {
        if (Float.isNaN(startData.a())) {
            set.add(PlaylistError.START_DATA_WITHOUT_TIME_OFFSET);
        }
    }

    private static void a(PlaylistData playlistData, Set<PlaylistError> set) {
        if (playlistData.a() == null || playlistData.a().isEmpty()) {
            set.add(PlaylistError.PLAYLIST_DATA_WITHOUT_URI);
        }
        if (playlistData.b()) {
            if (playlistData.c().a() == -1) {
                set.add(PlaylistError.STREAM_INFO_WITH_NO_BANDWIDTH);
            }
            if (playlistData.c().c() < -1) {
                set.add(PlaylistError.STREAM_INFO_WITH_INVALID_AVERAGE_BANDWIDTH);
            }
        }
    }

    private static void a(IFrameStreamInfo iFrameStreamInfo, Set<PlaylistError> set) {
        if (iFrameStreamInfo.l() == null || iFrameStreamInfo.l().isEmpty()) {
            set.add(PlaylistError.I_FRAME_STREAM_WITHOUT_URI);
        }
        if (iFrameStreamInfo.a() == -1) {
            set.add(PlaylistError.I_FRAME_STREAM_WITH_NO_BANDWIDTH);
        }
        if (iFrameStreamInfo.c() < -1) {
            set.add(PlaylistError.I_FRAME_STREAM_WITH_INVALID_AVERAGE_BANDWIDTH);
        }
    }

    private static void a(MediaData mediaData, Set<PlaylistError> set) {
        if (mediaData.a() == null) {
            set.add(PlaylistError.MEDIA_DATA_WITHOUT_TYPE);
        }
        if (mediaData.d() == null) {
            set.add(PlaylistError.MEDIA_DATA_WITHOUT_GROUP_ID);
        }
        if (mediaData.i() == null) {
            set.add(PlaylistError.MEDIA_DATA_WITHOUT_NAME);
        }
        if (mediaData.a() == MediaType.CLOSED_CAPTIONS) {
            if (mediaData.b()) {
                set.add(PlaylistError.CLOSE_CAPTIONS_WITH_URI);
            }
            if (mediaData.n() == null) {
                set.add(PlaylistError.CLOSE_CAPTIONS_WITHOUT_IN_STREAM_ID);
            }
        } else if (!(mediaData.a() == MediaType.CLOSED_CAPTIONS || mediaData.n() == null)) {
            set.add(PlaylistError.IN_STREAM_ID_WITHOUT_CLOSE_CAPTIONS);
        }
        if (mediaData.j() && !mediaData.k()) {
            set.add(PlaylistError.DEFAULT_WITHOUT_AUTO_SELECT);
        }
        if (mediaData.a() != MediaType.SUBTITLES && mediaData.l()) {
            set.add(PlaylistError.FORCED_WITHOUT_SUBTITLES);
        }
    }

    private static void a(TrackData trackData, Set<PlaylistError> set, boolean z, ParsingMode parsingMode) {
        if (trackData.a() == null || trackData.a().isEmpty()) {
            set.add(PlaylistError.TRACK_DATA_WITHOUT_URI);
        }
        if (z && !trackData.b()) {
            set.add(PlaylistError.EXTENDED_TRACK_DATA_WITHOUT_TRACK_INFO);
        }
        if (trackData.d() && trackData.i().a() == null) {
            set.add(PlaylistError.ENCRYPTION_DATA_WITHOUT_METHOD);
        }
        if (trackData.b() && !parsingMode.d && trackData.c().f6058a < 0.0f) {
            set.add(PlaylistError.TRACK_INFO_WITH_NEGATIVE_DURATION);
        }
    }
}
