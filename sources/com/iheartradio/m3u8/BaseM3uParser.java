package com.iheartradio.m3u8;

import java.io.EOFException;
import java.io.InputStream;

abstract class BaseM3uParser implements IPlaylistParser {

    /* renamed from: a  reason: collision with root package name */
    protected final M3uScanner f5938a;
    protected final Encoding b;

    BaseM3uParser(InputStream inputStream, Encoding encoding) {
        this.f5938a = new M3uScanner(inputStream, encoding);
        this.b = encoding;
    }

    public boolean a() {
        return this.f5938a.b();
    }

    /* access modifiers changed from: package-private */
    public final void b() throws EOFException {
        if (!a()) {
            throw new EOFException();
        }
    }
}
