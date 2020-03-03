package com.drew.imaging.mp4;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4Directory;
import com.drew.metadata.mp4.boxes.Box;
import java.io.IOException;

public abstract class Mp4Handler<T extends Mp4Directory> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    protected Metadata f5182a;
    @NotNull
    protected T b = a();

    /* access modifiers changed from: protected */
    public abstract Mp4Handler a(@NotNull Box box, @Nullable byte[] bArr) throws IOException;

    /* access modifiers changed from: protected */
    @NotNull
    public abstract T a();

    /* access modifiers changed from: protected */
    public abstract boolean a(@NotNull Box box);

    /* access modifiers changed from: protected */
    public abstract boolean b(@NotNull Box box);

    public Mp4Handler(@NotNull Metadata metadata) {
        this.f5182a = metadata;
        metadata.a(this.b);
    }

    /* access modifiers changed from: protected */
    public Mp4Handler c(@NotNull Box box) throws IOException {
        return a(box, (byte[]) null);
    }

    public void a(@NotNull String str) {
        this.b.a(str);
    }
}
