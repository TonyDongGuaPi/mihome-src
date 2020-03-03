package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import java.io.IOException;

public class SoundMediaHeaderBox extends FullBox {

    /* renamed from: a  reason: collision with root package name */
    int f5256a;

    public SoundMediaHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.f5256a = sequentialReader.h();
        sequentialReader.a(2);
    }

    public void a(Mp4SoundDirectory mp4SoundDirectory) {
        double d = (double) (this.f5256a & -65536);
        double d2 = (double) (this.f5256a & 65535);
        double pow = Math.pow(2.0d, 4.0d);
        Double.isNaN(d2);
        Double.isNaN(d);
        mp4SoundDirectory.a(105, d + (d2 / pow));
    }
}
