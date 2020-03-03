package com.drew.metadata.mp4;

import com.drew.imaging.mp4.Mp4Handler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.media.Mp4MediaDirectory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public abstract class Mp4MediaHandler<T extends Mp4MediaDirectory> extends Mp4Handler<T> {
    /* access modifiers changed from: protected */
    public abstract void a(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException;

    /* access modifiers changed from: protected */
    public abstract String b();

    /* access modifiers changed from: protected */
    public abstract void b(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void c(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException;

    public Mp4MediaHandler(Metadata metadata) {
        super(metadata);
        if (Mp4HandlerFactory.b != null && Mp4HandlerFactory.c != null) {
            Calendar instance = Calendar.getInstance();
            instance.set(1904, 0, 1, 0, 0, 0);
            long time = instance.getTime().getTime();
            String date = new Date((Mp4HandlerFactory.b.longValue() * 1000) + time).toString();
            String date2 = new Date((Mp4HandlerFactory.c.longValue() * 1000) + time).toString();
            String str = Mp4HandlerFactory.e;
            ((Mp4MediaDirectory) this.b).a(101, date);
            ((Mp4MediaDirectory) this.b).a(102, date2);
            ((Mp4MediaDirectory) this.b).a(104, str);
        }
    }

    public boolean a(@NotNull Box box) {
        return box.e.equals(b()) || box.e.equals("stsd") || box.e.equals("stts");
    }

    public boolean b(@NotNull Box box) {
        return box.e.equals("stbl") || box.e.equals("minf");
    }

    public Mp4Handler a(@NotNull Box box, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (box.e.equals(b())) {
                b(sequentialByteArrayReader, box);
            } else if (box.e.equals("stsd")) {
                a((SequentialReader) sequentialByteArrayReader, box);
            } else if (box.e.equals("stts")) {
                c(sequentialByteArrayReader, box);
            }
        }
        return this;
    }
}
