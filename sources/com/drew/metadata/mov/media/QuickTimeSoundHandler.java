package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeHandlerFactory;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.SoundInformationMediaHeaderAtom;
import com.drew.metadata.mov.atoms.SoundSampleDescriptionAtom;
import java.io.IOException;

public class QuickTimeSoundHandler extends QuickTimeMediaHandler<QuickTimeSoundDirectory> {
    /* access modifiers changed from: protected */
    public String b() {
        return "smhd";
    }

    public QuickTimeSoundHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public QuickTimeSoundDirectory a() {
        return new QuickTimeSoundDirectory();
    }

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new SoundSampleDescriptionAtom(sequentialReader, atom).a((QuickTimeSoundDirectory) this.b);
    }

    public void b(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new SoundInformationMediaHeaderAtom(sequentialReader, atom).a((QuickTimeSoundDirectory) this.b);
    }

    /* access modifiers changed from: protected */
    public void c(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        ((QuickTimeSoundDirectory) this.b).a(772, (double) QuickTimeHandlerFactory.f5237a.longValue());
    }
}
