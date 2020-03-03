package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.AudioSampleEntry;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.boxes.SoundMediaHeaderBox;
import com.drew.metadata.mp4.boxes.TimeToSampleBox;
import java.io.IOException;

public class Mp4SoundHandler extends Mp4MediaHandler<Mp4SoundDirectory> {
    /* access modifiers changed from: protected */
    public String b() {
        return "smhd";
    }

    public Mp4SoundHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public Mp4SoundDirectory a() {
        return new Mp4SoundDirectory();
    }

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new AudioSampleEntry(sequentialReader, box).a((Mp4SoundDirectory) this.b);
    }

    public void b(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new SoundMediaHeaderBox(sequentialReader, box).a((Mp4SoundDirectory) this.b);
    }

    /* access modifiers changed from: protected */
    public void c(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new TimeToSampleBox(sequentialReader, box).a((Mp4SoundDirectory) this.b);
    }
}
