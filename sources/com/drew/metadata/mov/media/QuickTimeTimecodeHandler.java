package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.TimecodeInformationMediaAtom;
import com.drew.metadata.mov.atoms.TimecodeSampleDescriptionAtom;
import java.io.IOException;

public class QuickTimeTimecodeHandler extends QuickTimeMediaHandler<QuickTimeTimecodeDirectory> {
    /* access modifiers changed from: protected */
    public String b() {
        return QuickTimeAtomTypes.f;
    }

    /* access modifiers changed from: protected */
    public void c(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
    }

    public QuickTimeTimecodeHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public QuickTimeTimecodeDirectory a() {
        return new QuickTimeTimecodeDirectory();
    }

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new TimecodeSampleDescriptionAtom(sequentialReader, atom).a((QuickTimeTimecodeDirectory) this.b);
    }

    public void b(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new TimecodeInformationMediaAtom(sequentialReader, atom).a((QuickTimeTimecodeDirectory) this.b);
    }
}
