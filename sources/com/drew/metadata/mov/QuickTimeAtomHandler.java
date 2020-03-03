package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.FileTypeCompatibilityAtom;
import com.drew.metadata.mov.atoms.HandlerReferenceAtom;
import com.drew.metadata.mov.atoms.MediaHeaderAtom;
import com.drew.metadata.mov.atoms.MovieHeaderAtom;
import java.io.IOException;

public class QuickTimeAtomHandler extends QuickTimeHandler<QuickTimeDirectory> {
    private QuickTimeHandlerFactory c = new QuickTimeHandlerFactory(this);

    public QuickTimeAtomHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public QuickTimeDirectory a() {
        return new QuickTimeDirectory();
    }

    public boolean a(@NotNull Atom atom) {
        return atom.b.equals("ftyp") || atom.b.equals("mvhd") || atom.b.equals("hdlr") || atom.b.equals("mdhd");
    }

    public boolean b(@NotNull Atom atom) {
        return atom.b.equals("trak") || atom.b.equals("udta") || atom.b.equals("meta") || atom.b.equals("moov") || atom.b.equals("mdia");
    }

    public QuickTimeHandler a(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.b.equals("mvhd")) {
                new MovieHeaderAtom(sequentialByteArrayReader, atom).a(this.b);
            } else if (atom.b.equals("ftyp")) {
                new FileTypeCompatibilityAtom(sequentialByteArrayReader, atom).a(this.b);
            } else if (atom.b.equals("hdlr")) {
                return this.c.a(new HandlerReferenceAtom(sequentialByteArrayReader, atom).a(), this.f5189a);
            } else if (atom.b.equals("mdhd")) {
                new MediaHeaderAtom(sequentialByteArrayReader, atom);
            }
        } else if (atom.b.equals("cmov")) {
            this.b.a("Compressed QuickTime movies not supported");
        }
        return this;
    }
}
