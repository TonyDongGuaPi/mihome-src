package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;

public class Box {
    public long d;
    public String e;
    public String f;

    public Box(SequentialReader sequentialReader) throws IOException {
        this.d = sequentialReader.i();
        this.e = sequentialReader.b(4);
        if (this.d == 1) {
            this.d = sequentialReader.k();
        } else if (this.d == 0) {
            this.d = -1;
        }
        if (this.e.equals("uuid")) {
            this.f = sequentialReader.b(16);
        }
    }

    public Box(Box box) {
        this.d = box.d;
        this.e = box.e;
        this.f = box.f;
    }
}
