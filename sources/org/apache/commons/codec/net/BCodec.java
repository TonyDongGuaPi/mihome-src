package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;

@Deprecated
public class BCodec extends RFC1522Codec implements StringDecoder, StringEncoder {
    public BCodec() {
        throw new RuntimeException("Stub!");
    }

    public BCodec(String str) {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public String getEncoding() {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public byte[] doEncoding(byte[] bArr) throws EncoderException {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public byte[] doDecoding(byte[] bArr) throws DecoderException {
        throw new RuntimeException("Stub!");
    }

    public String encode(String str, String str2) throws EncoderException {
        throw new RuntimeException("Stub!");
    }

    public String encode(String str) throws EncoderException {
        throw new RuntimeException("Stub!");
    }

    public String decode(String str) throws DecoderException {
        throw new RuntimeException("Stub!");
    }

    public Object encode(Object obj) throws EncoderException {
        throw new RuntimeException("Stub!");
    }

    public Object decode(Object obj) throws DecoderException {
        throw new RuntimeException("Stub!");
    }

    public String getDefaultCharset() {
        throw new RuntimeException("Stub!");
    }
}
