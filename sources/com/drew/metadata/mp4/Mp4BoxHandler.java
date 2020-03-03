package com.drew.metadata.mp4;

import com.drew.imaging.mp4.Mp4Handler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.boxes.FileTypeBox;
import com.drew.metadata.mp4.boxes.HandlerBox;
import com.drew.metadata.mp4.boxes.MediaHeaderBox;
import com.drew.metadata.mp4.boxes.MovieHeaderBox;
import java.io.IOException;

public class Mp4BoxHandler extends Mp4Handler<Mp4Directory> {
    private Mp4HandlerFactory c = new Mp4HandlerFactory(this);

    public Mp4BoxHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public Mp4Directory a() {
        return new Mp4Directory();
    }

    public boolean a(@NotNull Box box) {
        return box.e.equals("ftyp") || box.e.equals("mvhd") || box.e.equals("hdlr") || box.e.equals("mdhd");
    }

    public boolean b(@NotNull Box box) {
        return box.e.equals("trak") || box.e.equals("meta") || box.e.equals("moov") || box.e.equals("mdia");
    }

    public Mp4Handler a(@NotNull Box box, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (box.e.equals("mvhd")) {
                b(sequentialByteArrayReader, box);
            } else if (box.e.equals("ftyp")) {
                a((SequentialReader) sequentialByteArrayReader, box);
            } else if (box.e.equals("hdlr")) {
                return this.c.a(new HandlerBox(sequentialByteArrayReader, box), this.f5182a);
            } else if (box.e.equals("mdhd")) {
                c(sequentialByteArrayReader, box);
            }
        } else if (box.e.equals("cmov")) {
            this.b.a("Compressed MP4 movies not supported");
        }
        return this;
    }

    private void a(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new FileTypeBox(sequentialReader, box).a(this.b);
    }

    private void b(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new MovieHeaderBox(sequentialReader, box).a(this.b);
    }

    private void c(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new MediaHeaderBox(sequentialReader, box);
    }
}
