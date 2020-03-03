package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.MusicSampleDescriptionAtom;
import java.io.IOException;

public class QuickTimeMusicHandler extends QuickTimeMediaHandler<QuickTimeMusicDirectory> {
    /* access modifiers changed from: protected */
    public String b() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void b(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void c(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
    }

    public QuickTimeMusicHandler(Metadata metadata) {
        super(metadata);
    }

    /* access modifiers changed from: protected */
    @NotNull
    /* renamed from: c */
    public QuickTimeMusicDirectory a() {
        return (QuickTimeMusicDirectory) this.b;
    }

    /* access modifiers changed from: protected */
    public void a(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new MusicSampleDescriptionAtom(sequentialReader, atom).a((QuickTimeMusicDirectory) this.b);
    }
}
