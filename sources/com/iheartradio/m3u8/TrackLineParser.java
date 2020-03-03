package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.TrackData;

class TrackLineParser implements LineParser {
    TrackLineParser() {
    }

    public void a(String str, ParseState parseState) throws ParseException {
        TrackData.Builder builder = new TrackData.Builder();
        MediaParseState f = parseState.f();
        if (!parseState.h() || f.g != null) {
            f.f5992a.add(builder.a(str).a(f.g).a(f.h).b(f.j).a(f.l).a());
            f.g = null;
            f.j = null;
            f.l = false;
            return;
        }
        throw ParseException.create(ParseExceptionType.MISSING_TRACK_INFO, str);
    }
}
