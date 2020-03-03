package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

class ExtendedM3uParser extends BaseM3uParser {
    private final ParsingMode c;
    private final Map<String, IExtTagParser> d = new HashMap();

    ExtendedM3uParser(InputStream inputStream, Encoding encoding, ParsingMode parsingMode) {
        super(inputStream, encoding);
        this.c = parsingMode;
        a(ExtLineParser.f5940a, ExtLineParser.c, MediaPlaylistLineParser.c, MediaPlaylistLineParser.d, MediaPlaylistLineParser.k, MediaPlaylistLineParser.f, MediaPlaylistLineParser.e, MediaPlaylistLineParser.g, MediaPlaylistLineParser.b, MasterPlaylistLineParser.f5945a, MediaPlaylistLineParser.h, MasterPlaylistLineParser.c, MasterPlaylistLineParser.b, MediaPlaylistLineParser.i, MediaPlaylistLineParser.f5993a, MediaPlaylistLineParser.j);
    }

    public Playlist c() throws IOException, ParseException, PlaylistException {
        b();
        ParseState parseState = new ParseState(this.b);
        PlaylistLineParser playlistLineParser = new PlaylistLineParser();
        TrackLineParser trackLineParser = new TrackLineParser();
        while (true) {
            try {
                if (!this.f5938a.b()) {
                    break;
                }
                String c2 = this.f5938a.c();
                a(c2);
                if (c2.length() != 0) {
                    if (!b(c2)) {
                        if (c(c2)) {
                            String d2 = d(c2);
                            IExtTagParser iExtTagParser = this.d.get(d2);
                            if (iExtTagParser == null) {
                                if (this.c.c) {
                                    iExtTagParser = ExtLineParser.b;
                                } else {
                                    throw ParseException.create(ParseExceptionType.UNSUPPORTED_EXT_TAG_DETECTED, d2, c2);
                                }
                            }
                            iExtTagParser.a(c2, parseState);
                            if (parseState.e() && parseState.f().k) {
                                break;
                            }
                        } else if (parseState.b()) {
                            playlistLineParser.a(c2, parseState);
                        } else if (parseState.e()) {
                            trackLineParser.a(c2, parseState);
                        } else {
                            throw ParseException.create(ParseExceptionType.UNKNOWN_PLAYLIST_TYPE, c2);
                        }
                    }
                }
            } catch (ParseException e) {
                e.setInput(this.f5938a.a());
                throw e;
            }
        }
        Playlist m = parseState.a();
        PlaylistValidation a2 = PlaylistValidation.a(m, this.c);
        if (a2.a()) {
            return m;
        }
        throw new PlaylistException(this.f5938a.a(), a2.b());
    }

    private void a(IExtTagParser... iExtTagParserArr) {
        if (iExtTagParserArr != null) {
            for (IExtTagParser iExtTagParser : iExtTagParserArr) {
                this.d.put(iExtTagParser.a(), iExtTagParser);
            }
        }
    }

    private void a(String str) throws ParseException {
        if (!b(str) && str.length() != str.trim().length()) {
            throw ParseException.create(ParseExceptionType.WHITESPACE_IN_TRACK, str);
        }
    }

    private boolean b(String str) {
        return str.startsWith("#") && !c(str);
    }

    private boolean c(String str) {
        return str.startsWith(Constants.i);
    }

    private String d(String str) {
        int indexOf = str.indexOf(":");
        if (indexOf == -1) {
            return str.substring(1);
        }
        return str.substring(1, indexOf);
    }
}
