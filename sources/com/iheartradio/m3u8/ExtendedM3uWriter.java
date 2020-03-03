package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class ExtendedM3uWriter extends Writer {
    private List<IExtTagWriter> b = new ArrayList();

    public ExtendedM3uWriter(OutputStream outputStream, Encoding encoding) {
        super(outputStream, encoding);
        a(ExtTagWriter.f5942a, ExtTagWriter.c, MediaPlaylistTagWriter.f, MediaPlaylistTagWriter.l, MediaPlaylistTagWriter.h, MediaPlaylistTagWriter.g, MediaPlaylistTagWriter.i, MediaPlaylistTagWriter.e, MasterPlaylistTagWriter.d, MediaPlaylistTagWriter.j, MasterPlaylistTagWriter.f, MasterPlaylistTagWriter.e, MediaPlaylistTagWriter.k, MediaPlaylistTagWriter.d);
    }

    private void a(IExtTagWriter... iExtTagWriterArr) {
        if (iExtTagWriterArr != null) {
            for (IExtTagWriter add : iExtTagWriterArr) {
                this.b.add(add);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Playlist playlist) throws IOException, ParseException, PlaylistException {
        for (IExtTagWriter a2 : this.b) {
            a2.a(this.f6036a, playlist);
        }
    }
}
