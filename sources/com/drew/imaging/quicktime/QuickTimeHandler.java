package com.drew.imaging.quicktime;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;

public abstract class QuickTimeHandler<T extends QuickTimeDirectory> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    protected Metadata f5189a;
    @NotNull
    protected T b = a();

    /* access modifiers changed from: protected */
    public abstract QuickTimeHandler a(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException;

    /* access modifiers changed from: protected */
    @NotNull
    public abstract T a();

    /* access modifiers changed from: protected */
    public abstract boolean a(@NotNull Atom atom);

    /* access modifiers changed from: protected */
    public abstract boolean b(@NotNull Atom atom);

    public QuickTimeHandler(@NotNull Metadata metadata) {
        this.f5189a = metadata;
        metadata.a(this.b);
    }

    /* access modifiers changed from: protected */
    public QuickTimeHandler c(@NotNull Atom atom) throws IOException {
        return a(atom, (byte[]) null);
    }

    public void a(@NotNull String str) {
        this.b.a(str);
    }
}
