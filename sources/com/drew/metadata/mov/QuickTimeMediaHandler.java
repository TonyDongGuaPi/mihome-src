package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.media.QuickTimeMediaDirectory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.mp4parser.boxes.apple.TimeCodeBox;

public abstract class QuickTimeMediaHandler<T extends QuickTimeDirectory> extends QuickTimeHandler<T> {
    /* access modifiers changed from: protected */
    public abstract void a(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException;

    /* access modifiers changed from: protected */
    public abstract String b();

    /* access modifiers changed from: protected */
    public abstract void b(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void c(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException;

    public QuickTimeMediaHandler(Metadata metadata) {
        super(metadata);
        if (QuickTimeHandlerFactory.b != null && QuickTimeHandlerFactory.c != null) {
            Calendar instance = Calendar.getInstance();
            instance.set(1904, 0, 1, 0, 0, 0);
            long time = instance.getTime().getTime();
            String date = new Date((QuickTimeHandlerFactory.b.longValue() * 1000) + time).toString();
            String date2 = new Date((QuickTimeHandlerFactory.c.longValue() * 1000) + time).toString();
            this.b.a((int) QuickTimeMediaDirectory.w, date);
            this.b.a((int) QuickTimeMediaDirectory.x, date2);
        }
    }

    public boolean a(@NotNull Atom atom) {
        return atom.b.equals(b()) || atom.b.equals("stsd") || atom.b.equals("stts");
    }

    public boolean b(@NotNull Atom atom) {
        return atom.b.equals("stbl") || atom.b.equals("minf") || atom.b.equals("gmhd") || atom.b.equals(TimeCodeBox.f3800a);
    }

    /* renamed from: b */
    public QuickTimeMediaHandler a(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.b.equals(b())) {
                b((SequentialReader) sequentialByteArrayReader, atom);
            } else if (atom.b.equals("stsd")) {
                a((SequentialReader) sequentialByteArrayReader, atom);
            } else if (atom.b.equals("stts")) {
                c(sequentialByteArrayReader, atom);
            }
        }
        return this;
    }
}
