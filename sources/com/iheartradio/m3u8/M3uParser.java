package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.MediaPlaylist;
import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;
import java.io.InputStream;

class M3uParser extends BaseM3uParser {
    M3uParser(InputStream inputStream, Encoding encoding) {
        super(inputStream, encoding);
    }

    public Playlist c() throws IOException, ParseException, PlaylistException {
        b();
        ParseState parseState = new ParseState(this.b);
        TrackLineParser trackLineParser = new TrackLineParser();
        try {
            parseState.g();
            while (this.f5938a.b()) {
                String c = this.f5938a.c();
                a(c);
                if (c.length() != 0) {
                    if (!b(c)) {
                        trackLineParser.a(c, parseState);
                    }
                }
            }
            Playlist a2 = new Playlist.Builder().a(new MediaPlaylist.Builder().a(parseState.f().f5992a).a()).a();
            PlaylistValidation a3 = PlaylistValidation.a(a2);
            if (a3.a()) {
                return a2;
            }
            throw new PlaylistException(this.f5938a.a(), a3.b());
        } catch (ParseException e) {
            e.setInput(this.f5938a.a());
            throw e;
        }
    }

    private void a(String str) throws ParseException {
        if (!b(str) && str.length() != str.trim().length()) {
            ParseExceptionType parseExceptionType = ParseExceptionType.WHITESPACE_IN_TRACK;
            throw ParseException.create(parseExceptionType, str, "" + str.length());
        }
    }

    private boolean b(String str) {
        return str.indexOf("#") == 0;
    }
}
