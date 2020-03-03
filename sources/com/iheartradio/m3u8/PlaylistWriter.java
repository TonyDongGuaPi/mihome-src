package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;
import java.io.OutputStream;

public class PlaylistWriter {

    /* renamed from: a  reason: collision with root package name */
    private final Writer f6032a;
    private final OutputStream b;
    private final boolean c;
    private boolean d;

    public PlaylistWriter(OutputStream outputStream, Format format, Encoding encoding) {
        this(outputStream, format, encoding, false);
    }

    private PlaylistWriter(OutputStream outputStream, Format format, Encoding encoding, boolean z) {
        boolean z2 = true;
        this.d = true;
        if (outputStream == null) {
            throw new IllegalArgumentException("outputStream is null");
        } else if (format == null) {
            throw new IllegalArgumentException("format is null");
        } else if (encoding != null) {
            this.b = outputStream;
            this.c = (!encoding.supportsByteOrderMark || !z) ? false : z2;
            switch (format) {
                case M3U:
                    this.f6032a = new M3uWriter(outputStream, encoding);
                    return;
                case EXT_M3U:
                    this.f6032a = new ExtendedM3uWriter(outputStream, encoding);
                    return;
                default:
                    throw new RuntimeException("unsupported format detected, this should be impossible: " + format);
            }
        } else {
            throw new IllegalArgumentException("encoding is null");
        }
    }

    public void a(Playlist playlist) throws IOException, ParseException, PlaylistException {
        PlaylistValidation a2 = PlaylistValidation.a(playlist);
        if (a2.a()) {
            a();
            this.f6032a.b(playlist);
            this.d = false;
            return;
        }
        throw new PlaylistException("", a2.b());
    }

    private void a() throws IOException {
        if (this.c && this.d) {
            for (int write : Constants.ar) {
                this.b.write(write);
            }
        }
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private OutputStream f6034a;
        private Format b;
        private Encoding c;
        private boolean d;

        public Builder a(OutputStream outputStream) {
            this.f6034a = outputStream;
            return this;
        }

        public Builder a(Format format) {
            this.b = format;
            return this;
        }

        public Builder a(Encoding encoding) {
            this.c = encoding;
            return this;
        }

        public Builder a() {
            this.d = true;
            return this;
        }

        public PlaylistWriter b() {
            return new PlaylistWriter(this.f6034a, this.b, this.c, this.d);
        }
    }
}
