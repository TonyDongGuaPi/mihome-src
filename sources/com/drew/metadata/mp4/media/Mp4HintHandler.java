package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.boxes.HintMediaHeaderBox;
import java.io.IOException;

public class Mp4HintHandler extends Mp4MediaHandler<Mp4HintDirectory> {
    /* access modifiers changed from: protected */
    public void a(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }

    /* access modifiers changed from: protected */
    public String b() {
        return "hmhd";
    }

    /* access modifiers changed from: protected */
    public void c(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }

    public Mp4HintHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public Mp4HintDirectory a() {
        return new Mp4HintDirectory();
    }

    /* access modifiers changed from: protected */
    public void b(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new HintMediaHeaderBox(sequentialReader, box).a((Mp4HintDirectory) this.b);
    }
}
