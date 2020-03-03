package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class HandlerBox extends FullBox {

    /* renamed from: a  reason: collision with root package name */
    String f5252a;
    String b;

    public String a() {
        return this.f5252a;
    }

    public HandlerBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        sequentialReader.a(4);
        this.f5252a = sequentialReader.b(4);
        sequentialReader.a(12);
        this.b = sequentialReader.c(((int) this.d) - 32, Charset.defaultCharset());
    }
}
