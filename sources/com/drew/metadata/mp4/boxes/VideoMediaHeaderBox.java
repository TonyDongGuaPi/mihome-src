package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import java.io.IOException;

public class VideoMediaHeaderBox extends FullBox {

    /* renamed from: a  reason: collision with root package name */
    int f5259a;
    int[] b;

    public VideoMediaHeaderBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.f5259a = sequentialReader.g();
        this.b = new int[]{sequentialReader.g(), sequentialReader.g(), sequentialReader.g()};
    }

    public void a(Mp4VideoDirectory mp4VideoDirectory) {
        mp4VideoDirectory.a(112, this.b);
        mp4VideoDirectory.a(111, this.f5259a);
    }
}
