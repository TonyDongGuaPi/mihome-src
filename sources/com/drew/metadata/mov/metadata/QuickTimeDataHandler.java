package com.drew.metadata.mov.metadata;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.ByteUtil;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.drew.metadata.mov.QuickTimeMetadataHandler;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;
import java.util.ArrayList;

public class QuickTimeDataHandler extends QuickTimeMetadataHandler {
    private int c = 0;
    private ArrayList<String> d = new ArrayList<>();

    public QuickTimeDataHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    public boolean a(@NotNull Atom atom) {
        return atom.b.equals("hdlr") || atom.b.equals(QuickTimeAtomTypes.h) || atom.b.equals("data");
    }

    /* access modifiers changed from: protected */
    public boolean b(@NotNull Atom atom) {
        return atom.b.equals("ilst") || ByteUtil.b(atom.b.getBytes(), 0, true) <= this.d.size();
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
        } else {
            int b = ByteUtil.b(atom.b.getBytes(), 0, true);
            if (b > 0 && b < this.d.size() + 1) {
                this.c = b - 1;
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        sequentialByteArrayReader.a(4);
        int j = sequentialByteArrayReader.j();
        for (int i = 0; i < j; i++) {
            int j2 = sequentialByteArrayReader.j();
            sequentialByteArrayReader.a(4);
            this.d.add(new String(sequentialByteArrayReader.a(j2 - 8)));
        }
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull byte[] bArr, @NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        sequentialByteArrayReader.a(8);
        this.b.a(QuickTimeMetadataDirectory.x.get(this.d.get(this.c)).intValue(), new String(sequentialByteArrayReader.a(bArr.length - 8)));
    }
}
