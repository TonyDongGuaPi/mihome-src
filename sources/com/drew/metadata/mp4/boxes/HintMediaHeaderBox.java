package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4HintDirectory;
import java.io.IOException;

public class HintMediaHeaderBox extends FullBox {

    /* renamed from: a  reason: collision with root package name */
    int f5253a;
    int b;
    long c;
    long i;

    public HintMediaHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.f5253a = sequentialReader.g();
        this.b = sequentialReader.g();
        this.c = sequentialReader.i();
        this.i = sequentialReader.i();
    }

    public void a(Mp4HintDirectory mp4HintDirectory) {
        mp4HintDirectory.a(101, this.f5253a);
        mp4HintDirectory.a(102, this.b);
        mp4HintDirectory.a(103, this.c);
        mp4HintDirectory.a(104, this.i);
    }
}
