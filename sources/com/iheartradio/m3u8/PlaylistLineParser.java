package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.PlaylistData;

class PlaylistLineParser implements LineParser {
    PlaylistLineParser() {
    }

    public void a(String str, ParseState parseState) {
        PlaylistData.Builder builder = new PlaylistData.Builder();
        MasterParseState c = parseState.c();
        c.f5944a.add(builder.b(str).a(c.e).a());
        c.e = null;
    }
}
