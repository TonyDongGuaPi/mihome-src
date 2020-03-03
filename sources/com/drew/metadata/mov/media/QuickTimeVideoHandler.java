package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.TimeToSampleAtom;
import com.drew.metadata.mov.atoms.VideoInformationMediaHeaderAtom;
import com.drew.metadata.mov.atoms.VideoSampleDescriptionAtom;
import java.io.IOException;

public class QuickTimeVideoHandler extends QuickTimeMediaHandler<QuickTimeVideoDirectory> {
    /* access modifiers changed from: protected */
    public String b() {
        return "vmhd";
    }

    public QuickTimeVideoHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public QuickTimeVideoDirectory a() {
        return new QuickTimeVideoDirectory();
    }

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new VideoSampleDescriptionAtom(sequentialReader, atom).a((QuickTimeVideoDirectory) this.b);
    }

    public void b(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new VideoInformationMediaHeaderAtom(sequentialReader, atom).a((QuickTimeVideoDirectory) this.b);
    }

    public void c(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new TimeToSampleAtom(sequentialReader, atom).a((QuickTimeVideoDirectory) this.b);
    }
}
