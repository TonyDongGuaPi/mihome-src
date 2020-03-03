package com.drew.metadata.mov.metadata;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMetadataHandler;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;

public class QuickTimeDirectoryHandler extends QuickTimeMetadataHandler {
    private String c;

    /* access modifiers changed from: protected */
    public void a(@NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
    }

    public QuickTimeDirectoryHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    public boolean a(@NotNull Atom atom) {
        return atom.b.equals("data");
    }

    /* access modifiers changed from: protected */
    public boolean b(@NotNull Atom atom) {
        return QuickTimeMetadataDirectory.x.containsKey(atom.b) || atom.b.equals("ilst");
    }

    /* access modifiers changed from: protected */
    public QuickTimeHandler a(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (!atom.b.equals("data") || this.c == null) {
                this.c = new String(sequentialByteArrayReader.a(4));
            } else {
                a(bArr, sequentialByteArrayReader);
            }
        } else if (QuickTimeMetadataDirectory.x.containsKey(atom.b)) {
            this.c = atom.b;
        } else {
            this.c = null;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull byte[] bArr, @NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        sequentialByteArrayReader.a(8);
        this.b.a(QuickTimeMetadataDirectory.x.get(this.c).intValue(), new String(sequentialByteArrayReader.a(bArr.length - 8)));
    }
}
