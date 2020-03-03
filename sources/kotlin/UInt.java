package kotlin;

import cn.com.fmsh.communication.core.MessageHead;
import kotlin.internal.InlineOnly;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001bB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u0013\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\u0013\u0010#\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b$\u0010\u0005J\u0013\u0010%\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u000fJ\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b)\u0010\u000bJ\u001b\u0010'\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u001dJ\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0016J\u001b\u0010,\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b-\u0010\u000bJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b/\u0010\u000fJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000bJ\u001b\u0010.\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u001dJ\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0016J\u001b\u00103\u001a\u0002042\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\b8\u0010\u000fJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b9\u0010\u000bJ\u001b\u00107\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u001dJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0016J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0003H\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u0003H\fø\u0001\u0000¢\u0006\u0004\b@\u0010\u000bJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u000fJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u000bJ\u001b\u0010A\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001dJ\u001b\u0010A\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u0016J\u0010\u0010F\u001a\u00020GH\b¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020\u0003H\b¢\u0006\u0004\bK\u0010\u0005J\u0010\u0010L\u001a\u00020MH\b¢\u0006\u0004\bN\u0010OJ\u0010\u0010P\u001a\u00020QH\b¢\u0006\u0004\bR\u0010SJ\u000f\u0010T\u001a\u00020UH\u0016¢\u0006\u0004\bV\u0010WJ\u0013\u0010X\u001a\u00020\rH\bø\u0001\u0000¢\u0006\u0004\bY\u0010IJ\u0013\u0010Z\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b[\u0010\u0005J\u0013\u0010\\\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\b]\u0010OJ\u0013\u0010^\u001a\u00020\u0014H\bø\u0001\u0000¢\u0006\u0004\b_\u0010SJ\u001b\u0010`\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\ba\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006c"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "data$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class UInt implements Comparable<UInt> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2683a = 0;
    public static final int b = -1;
    public static final int c = 4;
    public static final int d = 32;
    public static final Companion e = new Companion((DefaultConstructorMarker) null);
    private final int f;

    @PublishedApi
    public static /* synthetic */ void a() {
    }

    public static final boolean a(int i, int i2) {
        throw null;
    }

    public static boolean a(int i, @Nullable Object obj) {
        if (obj instanceof UInt) {
            if (i == ((UInt) obj).b()) {
                return true;
            }
        }
        return false;
    }

    @PublishedApi
    public static int b(int i) {
        return i;
    }

    @NotNull
    public static final /* synthetic */ UInt c(int i) {
        return new UInt(i);
    }

    public static int d(int i) {
        return i;
    }

    @InlineOnly
    private int e(int i) {
        return b(this.f, i);
    }

    @InlineOnly
    private static final byte i(int i) {
        return (byte) i;
    }

    @InlineOnly
    private static final short j(int i) {
        return (short) i;
    }

    @InlineOnly
    private static final int k(int i) {
        return i;
    }

    @InlineOnly
    private static final long l(int i) {
        return ((long) i) & MessageHead.SERIAL_MAK;
    }

    @InlineOnly
    private static final int o(int i) {
        return i;
    }

    public final /* synthetic */ int b() {
        return this.f;
    }

    public boolean equals(Object obj) {
        return a(this.f, obj);
    }

    public int hashCode() {
        return d(this.f);
    }

    @NotNull
    public String toString() {
        return a(this.f);
    }

    @PublishedApi
    private /* synthetic */ UInt(int i) {
        this.f = i;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return e(((UInt) obj).b());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @InlineOnly
    private static final int a(int i, byte b2) {
        return UnsignedKt.a(i, b(b2 & 255));
    }

    @InlineOnly
    private static final int a(int i, short s) {
        return UnsignedKt.a(i, b(s & 65535));
    }

    @InlineOnly
    private static int b(int i, int i2) {
        return UnsignedKt.a(i, i2);
    }

    @InlineOnly
    private static final int a(int i, long j) {
        return UnsignedKt.a(ULong.b(((long) i) & MessageHead.SERIAL_MAK), j);
    }

    @InlineOnly
    private static final int b(int i, byte b2) {
        return b(i + b(b2 & 255));
    }

    @InlineOnly
    private static final int b(int i, short s) {
        return b(i + b(s & 65535));
    }

    @InlineOnly
    private static final int c(int i, int i2) {
        return b(i + i2);
    }

    @InlineOnly
    private static final long b(int i, long j) {
        return ULong.b(ULong.b(((long) i) & MessageHead.SERIAL_MAK) + j);
    }

    @InlineOnly
    private static final int c(int i, byte b2) {
        return b(i - b(b2 & 255));
    }

    @InlineOnly
    private static final int c(int i, short s) {
        return b(i - b(s & 65535));
    }

    @InlineOnly
    private static final int d(int i, int i2) {
        return b(i - i2);
    }

    @InlineOnly
    private static final long c(int i, long j) {
        return ULong.b(ULong.b(((long) i) & MessageHead.SERIAL_MAK) - j);
    }

    @InlineOnly
    private static final int d(int i, byte b2) {
        return b(i * b(b2 & 255));
    }

    @InlineOnly
    private static final int d(int i, short s) {
        return b(i * b(s & 65535));
    }

    @InlineOnly
    private static final int e(int i, int i2) {
        return b(i * i2);
    }

    @InlineOnly
    private static final long d(int i, long j) {
        return ULong.b(ULong.b(((long) i) & MessageHead.SERIAL_MAK) * j);
    }

    @InlineOnly
    private static final int e(int i, byte b2) {
        return UnsignedKt.b(i, b(b2 & 255));
    }

    @InlineOnly
    private static final int e(int i, short s) {
        return UnsignedKt.b(i, b(s & 65535));
    }

    @InlineOnly
    private static final int f(int i, int i2) {
        return UnsignedKt.b(i, i2);
    }

    @InlineOnly
    private static final long e(int i, long j) {
        return UnsignedKt.b(ULong.b(((long) i) & MessageHead.SERIAL_MAK), j);
    }

    @InlineOnly
    private static final int f(int i, byte b2) {
        return UnsignedKt.c(i, b(b2 & 255));
    }

    @InlineOnly
    private static final int f(int i, short s) {
        return UnsignedKt.c(i, b(s & 65535));
    }

    @InlineOnly
    private static final int g(int i, int i2) {
        return UnsignedKt.c(i, i2);
    }

    @InlineOnly
    private static final long f(int i, long j) {
        return UnsignedKt.c(ULong.b(((long) i) & MessageHead.SERIAL_MAK), j);
    }

    @InlineOnly
    private static final int f(int i) {
        return b(i + 1);
    }

    @InlineOnly
    private static final int g(int i) {
        return b(i - 1);
    }

    @InlineOnly
    private static final UIntRange h(int i, int i2) {
        return new UIntRange(i, i2, (DefaultConstructorMarker) null);
    }

    @InlineOnly
    private static final int i(int i, int i2) {
        return b(i << i2);
    }

    @InlineOnly
    private static final int j(int i, int i2) {
        return b(i >>> i2);
    }

    @InlineOnly
    private static final int k(int i, int i2) {
        return b(i & i2);
    }

    @InlineOnly
    private static final int l(int i, int i2) {
        return b(i | i2);
    }

    @InlineOnly
    private static final int m(int i, int i2) {
        return b(i ^ i2);
    }

    @InlineOnly
    private static final int h(int i) {
        return b(i ^ -1);
    }

    @InlineOnly
    private static final byte m(int i) {
        return UByte.b((byte) i);
    }

    @InlineOnly
    private static final short n(int i) {
        return UShort.b((short) i);
    }

    @InlineOnly
    private static final long p(int i) {
        return ULong.b(((long) i) & MessageHead.SERIAL_MAK);
    }

    @NotNull
    public static String a(int i) {
        return String.valueOf(((long) i) & MessageHead.SERIAL_MAK);
    }
}
