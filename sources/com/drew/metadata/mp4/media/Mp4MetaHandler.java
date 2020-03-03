package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.Box;
import java.io.IOException;

public class Mp4MetaHandler extends Mp4MediaHandler<Mp4MetaDirectory> {
    /* access modifiers changed from: protected */
    public void a(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }

    /* access modifiers changed from: protected */
    public String b() {
        return "nmhd";
    }

    /* access modifiers changed from: protected */
    public void b(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void c(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }

    public Mp4MetaHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public Mp4MetaDirectory a() {
        return (Mp4MetaDirectory) this.b;
    }
}
