package com.google.common.io;

import com.coloros.mcssdk.c.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.io.GwtWorkarounds;
import com.google.common.math.IntMath;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
@Beta
public abstract class BaseEncoding {
    private static final BaseEncoding BASE16 = new StandardBaseEncoding("base16()", a.f, (Character) null);
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final BaseEncoding BASE64 = new StandardBaseEncoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final BaseEncoding BASE64_URL = new StandardBaseEncoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');

    /* access modifiers changed from: package-private */
    public abstract GwtWorkarounds.ByteInput decodingStream(GwtWorkarounds.CharInput charInput);

    /* access modifiers changed from: package-private */
    public abstract GwtWorkarounds.ByteOutput encodingStream(GwtWorkarounds.CharOutput charOutput);

    @CheckReturnValue
    public abstract BaseEncoding lowerCase();

    /* access modifiers changed from: package-private */
    public abstract int maxDecodedSize(int i);

    /* access modifiers changed from: package-private */
    public abstract int maxEncodedSize(int i);

    @CheckReturnValue
    public abstract BaseEncoding omitPadding();

    /* access modifiers changed from: package-private */
    public abstract CharMatcher padding();

    @CheckReturnValue
    public abstract BaseEncoding upperCase();

    @CheckReturnValue
    public abstract BaseEncoding withPadChar(char c);

    @CheckReturnValue
    public abstract BaseEncoding withSeparator(String str, int i);

    BaseEncoding() {
    }

    public static final class DecodingException extends IOException {
        DecodingException(String str) {
            super(str);
        }

        DecodingException(Throwable th) {
            super(th);
        }
    }

    public String encode(byte[] bArr) {
        return encode((byte[]) Preconditions.checkNotNull(bArr), 0, bArr.length);
    }

    public final String encode(byte[] bArr, int i, int i2) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        GwtWorkarounds.CharOutput stringBuilderOutput = GwtWorkarounds.stringBuilderOutput(maxEncodedSize(i2));
        GwtWorkarounds.ByteOutput encodingStream = encodingStream(stringBuilderOutput);
        int i3 = 0;
        while (i3 < i2) {
            try {
                encodingStream.write(bArr[i + i3]);
                i3++;
            } catch (IOException unused) {
                throw new AssertionError("impossible");
            }
        }
        encodingStream.close();
        return stringBuilderOutput.toString();
    }

    @GwtIncompatible("Writer,OutputStream")
    public final OutputStream encodingStream(Writer writer) {
        return GwtWorkarounds.asOutputStream(encodingStream(GwtWorkarounds.asCharOutput(writer)));
    }

    @GwtIncompatible("ByteSink,CharSink")
    public final ByteSink encodingSink(final CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        return new ByteSink() {
            public OutputStream openStream() throws IOException {
                return BaseEncoding.this.encodingStream(charSink.openStream());
            }
        };
    }

    private static byte[] extract(byte[] bArr, int i) {
        if (i == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    public final byte[] decode(CharSequence charSequence) {
        try {
            return decodeChecked(charSequence);
        } catch (DecodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final byte[] decodeChecked(CharSequence charSequence) throws DecodingException {
        String trimTrailingFrom = padding().trimTrailingFrom(charSequence);
        GwtWorkarounds.ByteInput decodingStream = decodingStream(GwtWorkarounds.asCharInput((CharSequence) trimTrailingFrom));
        byte[] bArr = new byte[maxDecodedSize(trimTrailingFrom.length())];
        try {
            int read = decodingStream.read();
            int i = 0;
            while (read != -1) {
                int i2 = i + 1;
                bArr[i] = (byte) read;
                read = decodingStream.read();
                i = i2;
            }
            return extract(bArr, i);
        } catch (DecodingException e) {
            throw e;
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    @GwtIncompatible("Reader,InputStream")
    public final InputStream decodingStream(Reader reader) {
        return GwtWorkarounds.asInputStream(decodingStream(GwtWorkarounds.asCharInput(reader)));
    }

    @GwtIncompatible("ByteSource,CharSource")
    public final ByteSource decodingSource(final CharSource charSource) {
        Preconditions.checkNotNull(charSource);
        return new ByteSource() {
            public InputStream openStream() throws IOException {
                return BaseEncoding.this.decodingStream(charSource.openStream());
            }
        };
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    private static final class Alphabet extends CharMatcher {
        final int bitsPerChar;
        final int bytesPerChunk;
        private final char[] chars;
        final int charsPerChunk;
        private final byte[] decodabet;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        Alphabet(String str, char[] cArr) {
            this.name = (String) Preconditions.checkNotNull(str);
            this.chars = (char[]) Preconditions.checkNotNull(cArr);
            try {
                this.bitsPerChar = IntMath.log2(cArr.length, RoundingMode.UNNECESSARY);
                int min = Math.min(8, Integer.lowestOneBit(this.bitsPerChar));
                this.charsPerChunk = 8 / min;
                this.bytesPerChunk = this.bitsPerChar / min;
                this.mask = cArr.length - 1;
                byte[] bArr = new byte[128];
                Arrays.fill(bArr, (byte) -1);
                for (int i = 0; i < cArr.length; i++) {
                    char c = cArr[i];
                    Preconditions.checkArgument(CharMatcher.ASCII.matches(c), "Non-ASCII character: %s", Character.valueOf(c));
                    Preconditions.checkArgument(bArr[c] == -1, "Duplicate character: %s", Character.valueOf(c));
                    bArr[c] = (byte) i;
                }
                this.decodabet = bArr;
                boolean[] zArr = new boolean[this.charsPerChunk];
                for (int i2 = 0; i2 < this.bytesPerChunk; i2++) {
                    zArr[IntMath.divide(i2 * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
                }
                this.validPadding = zArr;
            } catch (ArithmeticException e) {
                int length = cArr.length;
                StringBuilder sb = new StringBuilder(35);
                sb.append("Illegal alphabet length ");
                sb.append(length);
                throw new IllegalArgumentException(sb.toString(), e);
            }
        }

        /* access modifiers changed from: package-private */
        public char encode(int i) {
            return this.chars[i];
        }

        /* access modifiers changed from: package-private */
        public boolean isValidPaddingStartPosition(int i) {
            return this.validPadding[i % this.charsPerChunk];
        }

        /* access modifiers changed from: package-private */
        public int decode(char c) throws IOException {
            if (c <= 127 && this.decodabet[c] != -1) {
                return this.decodabet[c];
            }
            StringBuilder sb = new StringBuilder(25);
            sb.append("Unrecognized character: ");
            sb.append(c);
            throw new DecodingException(sb.toString());
        }

        private boolean hasLowerCase() {
            for (char isLowerCase : this.chars) {
                if (Ascii.isLowerCase(isLowerCase)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char isUpperCase : this.chars) {
                if (Ascii.isUpperCase(isUpperCase)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public Alphabet upperCase() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            for (int i = 0; i < this.chars.length; i++) {
                cArr[i] = Ascii.toUpperCase(this.chars[i]);
            }
            return new Alphabet(String.valueOf(this.name).concat(".upperCase()"), cArr);
        }

        /* access modifiers changed from: package-private */
        public Alphabet lowerCase() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            for (int i = 0; i < this.chars.length; i++) {
                cArr[i] = Ascii.toLowerCase(this.chars[i]);
            }
            return new Alphabet(String.valueOf(this.name).concat(".lowerCase()"), cArr);
        }

        public boolean matches(char c) {
            return CharMatcher.ASCII.matches(c) && this.decodabet[c] != -1;
        }

        public String toString() {
            return this.name;
        }
    }

    static final class StandardBaseEncoding extends BaseEncoding {
        /* access modifiers changed from: private */
        public final Alphabet alphabet;
        private transient BaseEncoding lowerCase;
        /* access modifiers changed from: private */
        @Nullable
        public final Character paddingChar;
        private transient BaseEncoding upperCase;

        StandardBaseEncoding(String str, String str2, @Nullable Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        StandardBaseEncoding(Alphabet alphabet2, @Nullable Character ch) {
            this.alphabet = (Alphabet) Preconditions.checkNotNull(alphabet2);
            Preconditions.checkArgument(ch == null || !alphabet2.matches(ch.charValue()), "Padding character %s was already in alphabet", ch);
            this.paddingChar = ch;
        }

        /* access modifiers changed from: package-private */
        public CharMatcher padding() {
            return this.paddingChar == null ? CharMatcher.NONE : CharMatcher.is(this.paddingChar.charValue());
        }

        /* access modifiers changed from: package-private */
        public int maxEncodedSize(int i) {
            return this.alphabet.charsPerChunk * IntMath.divide(i, this.alphabet.bytesPerChunk, RoundingMode.CEILING);
        }

        /* access modifiers changed from: package-private */
        public GwtWorkarounds.ByteOutput encodingStream(final GwtWorkarounds.CharOutput charOutput) {
            Preconditions.checkNotNull(charOutput);
            return new GwtWorkarounds.ByteOutput() {
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int writtenChars = 0;

                public void write(byte b) throws IOException {
                    this.bitBuffer <<= 8;
                    this.bitBuffer = (b & 255) | this.bitBuffer;
                    this.bitBufferLength += 8;
                    while (this.bitBufferLength >= StandardBaseEncoding.this.alphabet.bitsPerChar) {
                        charOutput.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer >> (this.bitBufferLength - StandardBaseEncoding.this.alphabet.bitsPerChar)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        this.bitBufferLength -= StandardBaseEncoding.this.alphabet.bitsPerChar;
                    }
                }

                public void flush() throws IOException {
                    charOutput.flush();
                }

                public void close() throws IOException {
                    if (this.bitBufferLength > 0) {
                        charOutput.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer << (StandardBaseEncoding.this.alphabet.bitsPerChar - this.bitBufferLength)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        if (StandardBaseEncoding.this.paddingChar != null) {
                            while (this.writtenChars % StandardBaseEncoding.this.alphabet.charsPerChunk != 0) {
                                charOutput.write(StandardBaseEncoding.this.paddingChar.charValue());
                                this.writtenChars++;
                            }
                        }
                    }
                    charOutput.close();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public int maxDecodedSize(int i) {
            return (int) (((((long) this.alphabet.bitsPerChar) * ((long) i)) + 7) / 8);
        }

        /* access modifiers changed from: package-private */
        public GwtWorkarounds.ByteInput decodingStream(final GwtWorkarounds.CharInput charInput) {
            Preconditions.checkNotNull(charInput);
            return new GwtWorkarounds.ByteInput() {
                int bitBuffer = 0;
                int bitBufferLength = 0;
                boolean hitPadding = false;
                final CharMatcher paddingMatcher = StandardBaseEncoding.this.padding();
                int readChars = 0;

                public int read() throws IOException {
                    while (true) {
                        int read = charInput.read();
                        if (read != -1) {
                            this.readChars++;
                            char c = (char) read;
                            if (this.paddingMatcher.matches(c)) {
                                if (this.hitPadding || (this.readChars != 1 && StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars - 1))) {
                                    this.hitPadding = true;
                                }
                            } else if (!this.hitPadding) {
                                this.bitBuffer <<= StandardBaseEncoding.this.alphabet.bitsPerChar;
                                this.bitBuffer = StandardBaseEncoding.this.alphabet.decode(c) | this.bitBuffer;
                                this.bitBufferLength += StandardBaseEncoding.this.alphabet.bitsPerChar;
                                if (this.bitBufferLength >= 8) {
                                    this.bitBufferLength -= 8;
                                    return (this.bitBuffer >> this.bitBufferLength) & 255;
                                }
                            } else {
                                int i = this.readChars;
                                StringBuilder sb = new StringBuilder(61);
                                sb.append("Expected padding character but found '");
                                sb.append(c);
                                sb.append("' at index ");
                                sb.append(i);
                                throw new DecodingException(sb.toString());
                            }
                        } else if (this.hitPadding || StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars)) {
                            return -1;
                        } else {
                            int i2 = this.readChars;
                            StringBuilder sb2 = new StringBuilder(32);
                            sb2.append("Invalid input length ");
                            sb2.append(i2);
                            throw new DecodingException(sb2.toString());
                        }
                    }
                    int i3 = this.readChars;
                    StringBuilder sb3 = new StringBuilder(41);
                    sb3.append("Padding cannot start at index ");
                    sb3.append(i3);
                    throw new DecodingException(sb3.toString());
                }

                public void close() throws IOException {
                    charInput.close();
                }
            };
        }

        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : new StandardBaseEncoding(this.alphabet, (Character) null);
        }

        public BaseEncoding withPadChar(char c) {
            return (8 % this.alphabet.bitsPerChar == 0 || (this.paddingChar != null && this.paddingChar.charValue() == c)) ? this : new StandardBaseEncoding(this.alphabet, Character.valueOf(c));
        }

        public BaseEncoding withSeparator(String str, int i) {
            Preconditions.checkNotNull(str);
            Preconditions.checkArgument(padding().or(this.alphabet).matchesNoneOf(str), "Separator cannot contain alphabet or padding characters");
            return new SeparatedBaseEncoding(this, str, i);
        }

        public BaseEncoding upperCase() {
            BaseEncoding baseEncoding = this.upperCase;
            if (baseEncoding == null) {
                Alphabet upperCase2 = this.alphabet.upperCase();
                baseEncoding = upperCase2 == this.alphabet ? this : new StandardBaseEncoding(upperCase2, this.paddingChar);
                this.upperCase = baseEncoding;
            }
            return baseEncoding;
        }

        public BaseEncoding lowerCase() {
            BaseEncoding baseEncoding = this.lowerCase;
            if (baseEncoding == null) {
                Alphabet lowerCase2 = this.alphabet.lowerCase();
                baseEncoding = lowerCase2 == this.alphabet ? this : new StandardBaseEncoding(lowerCase2, this.paddingChar);
                this.lowerCase = baseEncoding;
            }
            return baseEncoding;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.alphabet.toString());
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    sb.append(".omitPadding()");
                } else {
                    sb.append(".withPadChar(");
                    sb.append(this.paddingChar);
                    sb.append(Operators.BRACKET_END);
                }
            }
            return sb.toString();
        }
    }

    static GwtWorkarounds.CharInput ignoringInput(final GwtWorkarounds.CharInput charInput, final CharMatcher charMatcher) {
        Preconditions.checkNotNull(charInput);
        Preconditions.checkNotNull(charMatcher);
        return new GwtWorkarounds.CharInput() {
            /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: 
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                */
            public int read() throws java.io.IOException {
                /*
                    r3 = this;
                L_0x0000:
                    com.google.common.io.GwtWorkarounds$CharInput r0 = r1
                    int r0 = r0.read()
                    r1 = -1
                    if (r0 == r1) goto L_0x0012
                    com.google.common.base.CharMatcher r1 = r2
                    char r2 = (char) r0
                    boolean r1 = r1.matches(r2)
                    if (r1 != 0) goto L_0x0000
                L_0x0012:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.AnonymousClass3.read():int");
            }

            public void close() throws IOException {
                charInput.close();
            }
        };
    }

    static GwtWorkarounds.CharOutput separatingOutput(final GwtWorkarounds.CharOutput charOutput, final String str, final int i) {
        Preconditions.checkNotNull(charOutput);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(i > 0);
        return new GwtWorkarounds.CharOutput() {
            int charsUntilSeparator = i;

            public void write(char c) throws IOException {
                if (this.charsUntilSeparator == 0) {
                    for (int i = 0; i < str.length(); i++) {
                        charOutput.write(str.charAt(i));
                    }
                    this.charsUntilSeparator = i;
                }
                charOutput.write(c);
                this.charsUntilSeparator--;
            }

            public void flush() throws IOException {
                charOutput.flush();
            }

            public void close() throws IOException {
                charOutput.close();
            }
        };
    }

    static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;
        private final CharMatcher separatorChars;

        SeparatedBaseEncoding(BaseEncoding baseEncoding, String str, int i) {
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(baseEncoding);
            this.separator = (String) Preconditions.checkNotNull(str);
            this.afterEveryChars = i;
            Preconditions.checkArgument(i > 0, "Cannot add a separator after every %s chars", Integer.valueOf(i));
            this.separatorChars = CharMatcher.anyOf(str).precomputed();
        }

        /* access modifiers changed from: package-private */
        public CharMatcher padding() {
            return this.delegate.padding();
        }

        /* access modifiers changed from: package-private */
        public int maxEncodedSize(int i) {
            int maxEncodedSize = this.delegate.maxEncodedSize(i);
            return maxEncodedSize + (this.separator.length() * IntMath.divide(Math.max(0, maxEncodedSize - 1), this.afterEveryChars, RoundingMode.FLOOR));
        }

        /* access modifiers changed from: package-private */
        public GwtWorkarounds.ByteOutput encodingStream(GwtWorkarounds.CharOutput charOutput) {
            return this.delegate.encodingStream(separatingOutput(charOutput, this.separator, this.afterEveryChars));
        }

        /* access modifiers changed from: package-private */
        public int maxDecodedSize(int i) {
            return this.delegate.maxDecodedSize(i);
        }

        /* access modifiers changed from: package-private */
        public GwtWorkarounds.ByteInput decodingStream(GwtWorkarounds.CharInput charInput) {
            return this.delegate.decodingStream(ignoringInput(charInput, this.separatorChars));
        }

        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withPadChar(char c) {
            return this.delegate.withPadChar(c).withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withSeparator(String str, int i) {
            throw new UnsupportedOperationException("Already have a separator");
        }

        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.delegate.toString()));
            String valueOf2 = String.valueOf(String.valueOf(this.separator));
            int i = this.afterEveryChars;
            StringBuilder sb = new StringBuilder(valueOf.length() + 31 + valueOf2.length());
            sb.append(valueOf);
            sb.append(".withSeparator(\"");
            sb.append(valueOf2);
            sb.append("\", ");
            sb.append(i);
            sb.append(Operators.BRACKET_END_STR);
            return sb.toString();
        }
    }
}
