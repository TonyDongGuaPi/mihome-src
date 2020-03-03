package com.drew.lang;

import com.drew.lang.annotations.NotNull;

public class ByteConvert {
    public static int a(@NotNull byte[] bArr) {
        return (bArr[3] & 255) | ((bArr[0] << 24) & -16777216) | ((bArr[1] << 16) & 16711680) | ((bArr[2] << 8) & 65280);
    }

    public static int b(@NotNull byte[] bArr) {
        return ((bArr[3] << 24) & -16777216) | (bArr[0] & 255) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 16) & 16711680);
    }
}
