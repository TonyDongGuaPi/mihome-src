package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;

public class FullBox extends Box {
    protected int g;
    protected byte[] h;

    public FullBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(box);
        this.g = sequentialReader.e();
        this.h = sequentialReader.a(3);
    }
}
