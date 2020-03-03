package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

abstract class Writer {

    /* renamed from: a  reason: collision with root package name */
    final TagWriter f6036a;

    /* access modifiers changed from: package-private */
    public abstract void a(Playlist playlist) throws IOException, ParseException, PlaylistException;

    Writer(OutputStream outputStream, Encoding encoding) {
        try {
            this.f6036a = new TagWriter(new OutputStreamWriter(outputStream, encoding.getValue()));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) throws IOException {
        b("#" + str);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Object obj) throws IOException {
        b("#" + str + ":" + obj);
    }

    /* access modifiers changed from: package-private */
    public void b(String str) throws IOException {
        this.f6036a.a(str);
        this.f6036a.a("\n");
    }

    /* access modifiers changed from: package-private */
    public final void b(Playlist playlist) throws IOException, ParseException, PlaylistException {
        a(playlist);
        this.f6036a.a();
    }
}
