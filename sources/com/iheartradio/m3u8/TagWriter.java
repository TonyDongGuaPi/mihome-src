package com.iheartradio.m3u8;

import java.io.IOException;
import java.io.OutputStreamWriter;

class TagWriter {

    /* renamed from: a  reason: collision with root package name */
    private final OutputStreamWriter f6035a;

    public TagWriter(OutputStreamWriter outputStreamWriter) {
        this.f6035a = outputStreamWriter;
    }

    public void a(String str) throws IOException {
        this.f6035a.write(str);
    }

    public void b(String str) throws IOException {
        a(str + "\n");
    }

    public void c(String str) throws IOException {
        b("#" + str);
    }

    public void a(String str, String str2) throws IOException {
        b("#" + str + ":" + str2);
    }

    public void a() throws IOException {
        this.f6035a.flush();
    }
}
