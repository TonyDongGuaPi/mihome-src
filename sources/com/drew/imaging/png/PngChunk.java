package com.drew.imaging.png;

import com.drew.lang.annotations.NotNull;

public class PngChunk {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final PngChunkType f5184a;
    @NotNull
    private final byte[] b;

    public PngChunk(@NotNull PngChunkType pngChunkType, @NotNull byte[] bArr) {
        this.f5184a = pngChunkType;
        this.b = bArr;
    }

    @NotNull
    public PngChunkType a() {
        return this.f5184a;
    }

    @NotNull
    public byte[] b() {
        return this.b;
    }
}
