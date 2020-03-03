package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import java.io.IOException;

public class AudioSampleEntry extends SampleEntry {

    /* renamed from: a  reason: collision with root package name */
    int f5250a;
    int b;
    long c;

    public AudioSampleEntry(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        sequentialReader.a(8);
        this.f5250a = sequentialReader.g();
        this.b = sequentialReader.h();
        sequentialReader.a(2);
        sequentialReader.a(2);
        this.c = sequentialReader.i();
    }

    public void a(Mp4SoundDirectory mp4SoundDirectory) {
        mp4SoundDirectory.a(102, this.f5250a);
        mp4SoundDirectory.a(103, this.b);
        mp4SoundDirectory.a(104, this.c);
    }
}
