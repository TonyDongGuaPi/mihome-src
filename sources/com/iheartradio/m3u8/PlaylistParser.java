package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;
import java.io.InputStream;

public class PlaylistParser implements IPlaylistParser {

    /* renamed from: a  reason: collision with root package name */
    private final IPlaylistParser f6029a;

    public PlaylistParser(InputStream inputStream, Format format, String str) {
        this(inputStream, format, a(str), ParsingMode.f6027a);
    }

    public PlaylistParser(InputStream inputStream, Format format, String str, ParsingMode parsingMode) {
        this(inputStream, format, a(str), parsingMode);
    }

    public PlaylistParser(InputStream inputStream, Format format, Extension extension) {
        this(inputStream, format, extension.encoding, ParsingMode.f6027a);
    }

    public PlaylistParser(InputStream inputStream, Format format, Extension extension, ParsingMode parsingMode) {
        this(inputStream, format, extension.encoding, parsingMode);
    }

    public PlaylistParser(InputStream inputStream, Format format, Encoding encoding) {
        this(inputStream, format, encoding, ParsingMode.f6027a);
    }

    public PlaylistParser(InputStream inputStream, Format format, Encoding encoding, ParsingMode parsingMode) {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream is null");
        } else if (format == null) {
            throw new IllegalArgumentException("format is null");
        } else if (encoding == null) {
            throw new IllegalArgumentException("encoding is null");
        } else if (parsingMode != null || format == Format.M3U) {
            switch (format) {
                case M3U:
                    this.f6029a = new M3uParser(inputStream, encoding);
                    return;
                case EXT_M3U:
                    this.f6029a = new ExtendedM3uParser(inputStream, encoding, parsingMode);
                    return;
                default:
                    throw new RuntimeException("unsupported format detected, this should be impossible: " + format);
            }
        } else {
            throw new IllegalArgumentException("parsingMode is null");
        }
    }

    public Playlist c() throws IOException, ParseException, PlaylistException {
        return this.f6029a.c();
    }

    public boolean a() {
        return this.f6029a.a();
    }

    private static Extension a(String str) {
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf != -1) {
                String substring = str.substring(lastIndexOf + 1);
                if (Extension.M3U.value.equalsIgnoreCase(substring)) {
                    return Extension.M3U;
                }
                if (Extension.M3U8.value.equalsIgnoreCase(substring)) {
                    return Extension.M3U8;
                }
                throw new IllegalArgumentException("filename extension should be .m3u or .m3u8: " + str);
            }
            throw new IllegalArgumentException("filename has no extension: " + str);
        }
        throw new IllegalArgumentException("filename is null");
    }
}
