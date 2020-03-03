package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4HandlerFactory;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import java.io.IOException;
import java.util.ArrayList;

public class TimeToSampleBox extends FullBox {

    /* renamed from: a  reason: collision with root package name */
    long f5257a;
    ArrayList<EntryCount> b = new ArrayList<>();

    public TimeToSampleBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.f5257a = sequentialReader.i();
        for (int i = 0; ((long) i) < this.f5257a; i++) {
            this.b.add(new EntryCount(sequentialReader.i(), sequentialReader.i()));
        }
    }

    public void a(Mp4VideoDirectory mp4VideoDirectory) {
        mp4VideoDirectory.a(114, ((float) Mp4HandlerFactory.f5249a.longValue()) / ((float) this.b.get(0).b));
    }

    public void a(Mp4SoundDirectory mp4SoundDirectory) {
        mp4SoundDirectory.a(104, (double) Mp4HandlerFactory.f5249a.longValue());
    }

    class EntryCount {

        /* renamed from: a  reason: collision with root package name */
        long f5258a;
        long b;

        public EntryCount(long j, long j2) {
            this.f5258a = j;
            this.b = j2;
        }
    }
}
