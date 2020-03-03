package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001^B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u000fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0012J\u001b\u0010)\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0012J\u001b\u00100\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u000fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0012J\u001b\u00109\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0012J\u001b\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020\u0003H\b¢\u0006\u0004\bD\u0010\u0005J\u0010\u0010E\u001a\u00020\rH\b¢\u0006\u0004\bF\u0010GJ\u0010\u0010H\u001a\u00020IH\b¢\u0006\u0004\bJ\u0010KJ\u0010\u0010L\u001a\u00020MH\b¢\u0006\u0004\bN\u0010OJ\u000f\u0010P\u001a\u00020QH\u0016¢\u0006\u0004\bR\u0010SJ\u0013\u0010T\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\bU\u0010\u0005J\u0013\u0010V\u001a\u00020\u0010H\bø\u0001\u0000¢\u0006\u0004\bW\u0010GJ\u0013\u0010X\u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\bY\u0010KJ\u0013\u0010Z\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\b[\u0010OJ\u001b\u0010\\\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b]\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006_"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "data$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class UByte implements Comparable<UByte> {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f2680a = 0;
    public static final byte b = -1;
    public static final int c = 1;
    public static final int d = 8;
    public static final Companion e = new Companion((DefaultConstructorMarker) null);
    private final byte f;

    @PublishedApi
    public static /* synthetic */ void a() {
    }

    public static final boolean a(byte b2, byte b3) {
        throw null;
    }

    public static boolean a(byte b2, @Nullable Object obj) {
        if (obj instanceof UByte) {
            if (b2 == ((UByte) obj).b()) {
                return true;
            }
        }
        return false;
    }

    @PublishedApi
    public static byte b(byte b2) {
        return b2;
    }

    @NotNull
    public static final /* synthetic */ UByte c(byte b2) {
        return new UByte(b2);
    }

    public static int d(byte b2) {
        return b2;
    }

    @InlineOnly
    private int e(byte b2) {
        return b(this.f, b2);
    }

    @InlineOnly
    private static final byte i(byte b2) {
        return b2;
    }

    @InlineOnly
    private static final short j(byte b2) {
        return (short) (((short) b2) & 255);
    }

    @InlineOnly
    private static final int k(byte b2) {
        return b2 & 255;
    }

    @InlineOnly
    private static final long l(byte b2) {
        return ((long) b2) & 255;
    }

    @InlineOnly
    private static final byte m(byte b2) {
        return b2;
    }

    public final /* synthetic */ byte b() {
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
    private /* synthetic */ UByte(byte b2) {
        this.f = b2;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return e(((UByte) obj).b());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @InlineOnly
    private static int b(byte b2, byte b3) {
        return Intrinsics.a((int) b2 & 255, (int) b3 & 255);
    }

    @InlineOnly
    private static final int a(byte b2, short s) {
        return Intrinsics.a((int) b2 & 255, (int) s & 65535);
    }

    @InlineOnly
    private static final int a(byte b2, int i) {
        return UnsignedKt.a(UInt.b(b2 & 255), i);
    }

    @InlineOnly
    private static final int a(byte b2, long j) {
        return UnsignedKt.a(ULong.b(((long) b2) & 255), j);
    }

    @InlineOnly
    private static final int c(byte b2, byte b3) {
        return UInt.b(UInt.b(b2 & 255) + UInt.b(b3 & 255));
    }

    @InlineOnly
    private static final int b(byte b2, short s) {
        return UInt.b(UInt.b(b2 & 255) + UInt.b(s & 65535));
    }

    @InlineOnly
    private static final int b(byte b2, int i) {
        return UInt.b(UInt.b(b2 & 255) + i);
    }

    @InlineOnly
    private static final long b(byte b2, long j) {
        return ULong.b(ULong.b(((long) b2) & 255) + j);
    }

    @InlineOnly
    private static final int d(byte b2, byte b3) {
        return UInt.b(UInt.b(b2 & 255) - UInt.b(b3 & 255));
    }

    @InlineOnly
    private static final int c(byte b2, short s) {
        return UInt.b(UInt.b(b2 & 255) - UInt.b(s & 65535));
    }

    @InlineOnly
    private static final int c(byte b2, int i) {
        return UInt.b(UInt.b(b2 & 255) - i);
    }

    @InlineOnly
    private static final long c(byte b2, long j) {
        return ULong.b(ULong.b(((long) b2) & 255) - j);
    }

    @InlineOnly
    private static final int e(byte b2, byte b3) {
        return UInt.b(UInt.b(b2 & 255) * UInt.b(b3 & 255));
    }

    @InlineOnly
    private static final int d(byte b2, short s) {
        return UInt.b(UInt.b(b2 & 255) * UInt.b(s & 65535));
    }

    @InlineOnly
    private static final int d(byte b2, int i) {
        return UInt.b(UInt.b(b2 & 255) * i);
    }

    @InlineOnly
    private static final long d(byte b2, long j) {
        return ULong.b(ULong.b(((long) b2) & 255) * j);
    }

    @InlineOnly
    private static final int f(byte b2, byte b3) {
        return UnsignedKt.b(UInt.b(b2 & 255), UInt.b(b3 & 255));
    }

    @InlineOnly
    private static final int e(byte b2, short s) {
        return UnsignedKt.b(UInt.b(b2 & 255), UInt.b(s & 65535));
    }

    @InlineOnly
    private static final int e(byte b2, int i) {
        return UnsignedKt.b(UInt.b(b2 & 255), i);
    }

    @InlineOnly
    private static final long e(byte b2, long j) {
        return UnsignedKt.b(ULong.b(((long) b2) & 255), j);
    }

    @InlineOnly
    private static final int g(byte b2, byte b3) {
        return UnsignedKt.c(UInt.b(b2 & 255), UInt.b(b3 & 255));
    }

    @InlineOnly
    private static final int f(byte b2, short s) {
        return UnsignedKt.c(UInt.b(b2 & 255), UInt.b(s & 65535));
    }

    @InlineOnly
    private static final int f(byte b2, int i) {
        return UnsignedKt.c(UInt.b(b2 & 255), i);
    }

    @InlineOnly
    private static final long f(byte b2, long j) {
        return UnsignedKt.c(ULong.b(((long) b2) & 255), j);
    }

    @InlineOnly
    private static final byte f(byte b2) {
        return b((byte) (b2 + 1));
    }

    @InlineOnly
    private static final byte g(byte b2) {
        return b((byte) (b2 - 1));
    }

    @InlineOnly
    private static final UIntRange h(byte b2, byte b3) {
        return new UIntRange(UInt.b(b2 & 255), UInt.b(b3 & 255), (DefaultConstructorMarker) null);
    }

    @InlineOnly
    private static final byte i(byte b2, byte b3) {
        return b((byte) (b2 & b3));
    }

    @InlineOnly
    private static final byte j(byte b2, byte b3) {
        return b((byte) (b2 | b3));
    }

    @InlineOnly
    private static final byte k(byte b2, byte b3) {
        return b((byte) (b2 ^ b3));
    }

    @InlineOnly
    private static final byte h(byte b2) {
        return b((byte) (b2 ^ -1));
    }

    @InlineOnly
    private static final short n(byte b2) {
        return UShort.b((short) (((short) b2) & 255));
    }

    @InlineOnly
    private static final int o(byte b2) {
        return UInt.b(b2 & 255);
    }

    @InlineOnly
    private static final long p(byte b2) {
        return ULong.b(((long) b2) & 255);
    }

    @NotNull
    public static String a(byte b2) {
        return String.valueOf(b2 & 255);
    }
}
