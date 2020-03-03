package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.boxes.TimeToSampleBox;
import com.drew.metadata.mp4.boxes.VideoMediaHeaderBox;
import com.drew.metadata.mp4.boxes.VisualSampleEntry;
import java.io.IOException;

public class Mp4VideoHandler extends Mp4MediaHandler<Mp4VideoDirectory> {
    /* access modifiers changed from: protected */
    public String b() {
        return "vmhd";
    }

    public Mp4VideoHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public Mp4VideoDirectory a() {
        return new Mp4VideoDirectory();
    }

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new VisualSampleEntry(sequentialReader, box).a((Mp4VideoDirectory) this.b);
    }

    public void b(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new VideoMediaHeaderBox(sequentialReader, box).a((Mp4VideoDirectory) this.b);
    }

    public void c(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new TimeToSampleBox(sequentialReader, box).a((Mp4VideoDirectory) this.b);
    }
}
