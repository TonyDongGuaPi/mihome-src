package kotlin.io;

import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000eH\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0011H\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0012H\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0013H\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0014H\b\u001a\t\u0010\u0015\u001a\u00020\nH\b\u001a\u0013\u0010\u0015\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000eH\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0011H\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0012H\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0013H\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0014H\b\u001a\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u001a\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\f\u0010\u001a\u001a\u00020\r*\u00020\u001bH\u0002\u001a\f\u0010\u001c\u001a\u00020\u000f*\u00020\u001bH\u0002\u001a\f\u0010\u001d\u001a\u00020\n*\u00020\u001eH\u0002\u001a$\u0010\u001f\u001a\u00020\r*\u00020\u00042\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\rH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006$"}, d2 = {"BUFFER_SIZE", "", "LINE_SEPARATOR_MAX_LENGTH", "decoder", "Ljava/nio/charset/CharsetDecoder;", "getDecoder", "()Ljava/nio/charset/CharsetDecoder;", "decoder$delegate", "Lkotlin/Lazy;", "print", "", "message", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "inputStream", "Ljava/io/InputStream;", "containsLineSeparator", "Ljava/nio/CharBuffer;", "dequeue", "flipBack", "Ljava/nio/Buffer;", "tryDecode", "byteBuffer", "Ljava/nio/ByteBuffer;", "charBuffer", "isEndOfStream", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "ConsoleKt")
public final class ConsoleKt {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ KProperty[] f2788a = {Reflection.a((PropertyReference0) new PropertyReference0Impl(Reflection.b(ConsoleKt.class, "kotlin-stdlib"), "decoder", "getDecoder()Ljava/nio/charset/CharsetDecoder;"))};
    private static final int b = 32;
    private static final int c = 2;
    private static final Lazy d = LazyKt.a(ConsoleKt$decoder$2.INSTANCE);

    private static final CharsetDecoder c() {
        Lazy lazy = d;
        KProperty kProperty = f2788a[0];
        return (CharsetDecoder) lazy.getValue();
    }

    @InlineOnly
    private static final void a(Object obj) {
        System.out.print(obj);
    }

    @InlineOnly
    private static final void a(int i) {
        System.out.print(i);
    }

    @InlineOnly
    private static final void a(long j) {
        System.out.print(j);
    }

    @InlineOnly
    private static final void a(byte b2) {
        System.out.print(Byte.valueOf(b2));
    }

    @InlineOnly
    private static final void a(short s) {
        System.out.print(Short.valueOf(s));
    }

    @InlineOnly
    private static final void a(char c2) {
        System.out.print(c2);
    }

    @InlineOnly
    private static final void a(boolean z) {
        System.out.print(z);
    }

    @InlineOnly
    private static final void a(float f) {
        System.out.print(f);
    }

    @InlineOnly
    private static final void a(double d2) {
        System.out.print(d2);
    }

    @InlineOnly
    private static final void a(char[] cArr) {
        System.out.print(cArr);
    }

    @InlineOnly
    private static final void b(Object obj) {
        System.out.println(obj);
    }

    @InlineOnly
    private static final void b(int i) {
        System.out.println(i);
    }

    @InlineOnly
    private static final void b(long j) {
        System.out.println(j);
    }

    @InlineOnly
    private static final void b(byte b2) {
        System.out.println(Byte.valueOf(b2));
    }

    @InlineOnly
    private static final void b(short s) {
        System.out.println(Short.valueOf(s));
    }

    @InlineOnly
    private static final void b(char c2) {
        System.out.println(c2);
    }

    @InlineOnly
    private static final void b(boolean z) {
        System.out.println(z);
    }

    @InlineOnly
    private static final void b(float f) {
        System.out.println(f);
    }

    @InlineOnly
    private static final void b(double d2) {
        System.out.println(d2);
    }

    @InlineOnly
    private static final void b(char[] cArr) {
        System.out.println(cArr);
    }

    @InlineOnly
    private static final void b() {
        System.out.println();
    }

    @Nullable
    public static final String a() {
        InputStream inputStream = System.in;
        Intrinsics.b(inputStream, "System.`in`");
        return a(inputStream, c());
    }

    @Nullable
    public static final String a(@NotNull InputStream inputStream, @NotNull CharsetDecoder charsetDecoder) {
        Intrinsics.f(inputStream, "inputStream");
        Intrinsics.f(charsetDecoder, "decoder");
        if (charsetDecoder.maxCharsPerByte() <= ((float) 1)) {
            ByteBuffer allocate = ByteBuffer.allocate(32);
            CharBuffer allocate2 = CharBuffer.allocate(2);
            StringBuilder sb = new StringBuilder();
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            do {
                allocate.put((byte) read);
                Intrinsics.b(allocate, "byteBuffer");
                Intrinsics.b(allocate2, "charBuffer");
                if (a(charsetDecoder, allocate, allocate2, false)) {
                    if (a(allocate2)) {
                        break;
                    } else if (!allocate2.hasRemaining()) {
                        sb.append(b(allocate2));
                    }
                }
                read = inputStream.read();
            } while (read != -1);
            a(charsetDecoder, allocate, allocate2, true);
            charsetDecoder.reset();
            int position = allocate2.position();
            char c2 = allocate2.get(0);
            char c3 = allocate2.get(1);
            switch (position) {
                case 1:
                    if (c2 != 10) {
                        sb.append(c2);
                        break;
                    }
                    break;
                case 2:
                    if (!(c2 == 13 && c3 == 10)) {
                        sb.append(c2);
                    }
                    if (c3 != 10) {
                        sb.append(c3);
                        break;
                    }
                    break;
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Encodings with multiple chars per byte are not supported".toString());
    }

    private static final boolean a(@NotNull CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer, boolean z) {
        int position = charBuffer.position();
        byteBuffer.flip();
        CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, z);
        if (decode.isError()) {
            decode.throwException();
        }
        boolean z2 = charBuffer.position() > position;
        if (z2) {
            byteBuffer.clear();
        } else {
            a((Buffer) byteBuffer);
        }
        return z2;
    }

    private static final boolean a(@NotNull CharBuffer charBuffer) {
        return charBuffer.get(1) == 10 || charBuffer.get(0) == 10;
    }

    private static final void a(@NotNull Buffer buffer) {
        buffer.position(buffer.limit());
        buffer.limit(buffer.capacity());
    }

    private static final char b(@NotNull CharBuffer charBuffer) {
        charBuffer.flip();
        char c2 = charBuffer.get();
        charBuffer.compact();
        return c2;
    }
}
