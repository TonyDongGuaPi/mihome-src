package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;

interface IExtTagWriter {
    void a(TagWriter tagWriter, Playlist playlist) throws IOException, ParseException;

    String b();
}
