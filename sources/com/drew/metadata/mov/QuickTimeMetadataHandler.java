package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.metadata.QuickTimeMetadataDirectory;
import java.io.IOException;

public abstract class QuickTimeMetadataHandler extends QuickTimeHandler {
    /* access modifiers changed from: protected */
    public abstract void a(@NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void a(@NotNull byte[] bArr, @NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException;

    public QuickTimeMetadataHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public QuickTimeDirectory a() {
        return new QuickTimeMetadataDirectory();
    }

    /* access modifiers changed from: protected */
    public boolean a(@NotNull Atom atom) {
        return atom.b.equals("hdlr") || atom.b.equals(QuickTimeAtomTypes.h) || atom.b.equals("data");
    }

    /* access modifiers changed from: protected */
    public boolean b(@NotNull Atom atom) {
        return atom.b.equals("ilst");
    }

    /* access modifiers changed from: protected */
    public QuickTimeHandler a(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.b.equals(QuickTimeAtomTypes.h)) {
                a(sequentialByteArrayReader);
            } else if (atom.b.equals("data")) {
                a(bArr, sequentialByteArrayReader);
            }
        }
        return this;
    }
}
