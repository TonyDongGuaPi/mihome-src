package com.drew.metadata.iptc;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public final class Iso2022Converter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5230a = "ISO-8859-1";
    private static final String b = "UTF-8";
    private static final byte c = 65;
    private static final int d = 14844066;
    private static final byte e = 71;
    private static final byte f = 37;
    private static final byte g = 27;

    @Nullable
    public static String a(@NotNull byte[] bArr) {
        if (bArr.length > 2 && bArr[0] == 27 && bArr[1] == 37 && bArr[2] == 71) {
            return "UTF-8";
        }
        if (bArr.length > 3 && bArr[0] == 27 && ((bArr[3] & 255) | ((bArr[2] & 255) << 8) | ((bArr[1] & 255) << 16)) == d && bArr[4] == 65) {
            return "ISO-8859-1";
        }
        return null;
    }

    @Nullable
    static Charset b(@NotNull byte[] bArr) {
        int i = 0;
        String[] strArr = {"UTF-8", System.getProperty("file.encoding"), "ISO-8859-1"};
        int length = strArr.length;
        while (i < length) {
            Charset forName = Charset.forName(strArr[i]);
            try {
                forName.newDecoder().decode(ByteBuffer.wrap(bArr));
                return forName;
            } catch (CharacterCodingException unused) {
                i++;
            }
        }
        return null;
    }

    private Iso2022Converter() {
    }
}
