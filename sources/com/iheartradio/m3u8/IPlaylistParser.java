package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;

interface IPlaylistParser {
    boolean a();

    Playlist c() throws IOException, ParseException, PlaylistException;
}
