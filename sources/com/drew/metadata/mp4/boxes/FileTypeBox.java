package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4Directory;
import java.io.IOException;
import java.util.ArrayList;

public class FileTypeBox extends Box {

    /* renamed from: a  reason: collision with root package name */
    String f5251a;
    long b;
    ArrayList<String> c = new ArrayList<>();

    public FileTypeBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(box);
        this.f5251a = sequentialReader.b(4);
        this.b = sequentialReader.i();
        for (int i = 16; ((long) i) < this.d; i += 4) {
            this.c.add(sequentialReader.b(4));
        }
    }

    public void a(Mp4Directory mp4Directory) {
        mp4Directory.a(1, this.f5251a);
        mp4Directory.a(2, this.b);
        mp4Directory.a(3, (String[]) this.c.toArray(new String[this.c.size()]));
    }
}
