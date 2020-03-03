package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.TextSampleDescriptionAtom;
import java.io.IOException;

public class QuickTimeTextHandler extends QuickTimeMediaHandler<QuickTimeTextDirectory> {
    /* access modifiers changed from: protected */
    public String b() {
        return "gmhd";
    }

    /* access modifiers changed from: protected */
    public void b(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void c(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
    }

    public QuickTimeTextHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public QuickTimeTextDirectory a() {
        return new QuickTimeTextDirectory();
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new TextSampleDescriptionAtom(sequentialReader, atom).a((QuickTimeTextDirectory) this.b);
    }
}
