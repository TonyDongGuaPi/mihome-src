package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;
import java.io.OutputStream;

class M3uWriter extends Writer {
    M3uWriter(OutputStream outputStream, Encoding encoding) {
        super(outputStream, encoding);
    }

    /* access modifiers changed from: package-private */
    public void a(Playlist playlist) throws IOException {
        throw new UnsupportedOperationException();
    }
}
