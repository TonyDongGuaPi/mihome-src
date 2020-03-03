package com.imi.fastjson.serializer;

import com.imi.fastjson.JSONException;
import com.imi.fastjson.util.ThreadLocalCache;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

public class SerialWriterStringEncoder {

    /* renamed from: a  reason: collision with root package name */
    private final CharsetEncoder f6178a;

    public SerialWriterStringEncoder(Charset charset) {
        this(charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE));
    }

    public SerialWriterStringEncoder(CharsetEncoder charsetEncoder) {
        this.f6178a = charsetEncoder;
    }

    public byte[] a(char[] cArr, int i, int i2) {
        if (i2 == 0) {
            return new byte[0];
        }
        this.f6178a.reset();
        return a(cArr, i, i2, ThreadLocalCache.b(a(i2, this.f6178a.maxBytesPerChar())));
    }

    public CharsetEncoder a() {
        return this.f6178a;
    }

    public byte[] a(char[] cArr, int i, int i2, byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        try {
            CoderResult encode = this.f6178a.encode(CharBuffer.wrap(cArr, i, i2), wrap, true);
            if (!encode.isUnderflow()) {
                encode.throwException();
            }
            CoderResult flush = this.f6178a.flush(wrap);
            if (!flush.isUnderflow()) {
                flush.throwException();
            }
            int position = wrap.position();
            byte[] bArr2 = new byte[position];
            System.arraycopy(bArr, 0, bArr2, 0, position);
            return bArr2;
        } catch (CharacterCodingException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    private static int a(int i, float f) {
        double d = (double) i;
        double d2 = (double) f;
        Double.isNaN(d);
        Double.isNaN(d2);
        return (int) (d * d2);
    }
}
