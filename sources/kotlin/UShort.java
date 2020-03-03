package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001^B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u0010J\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0013J\u001b\u0010)\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u0010J\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0013J\u001b\u00100\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u0010J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0013J\u001b\u00109\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u0010J\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0013J\u001b\u0010>\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020DH\b¢\u0006\u0004\bE\u0010FJ\u0010\u0010G\u001a\u00020\rH\b¢\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020\u0003H\b¢\u0006\u0004\bO\u0010\u0005J\u000f\u0010P\u001a\u00020QH\u0016¢\u0006\u0004\bR\u0010SJ\u0013\u0010T\u001a\u00020\u000eH\bø\u0001\u0000¢\u0006\u0004\bU\u0010FJ\u0013\u0010V\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bW\u0010IJ\u0013\u0010X\u001a\u00020\u0014H\bø\u0001\u0000¢\u0006\u0004\bY\u0010MJ\u0013\u0010Z\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b[\u0010\u0005J\u001b\u0010\\\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b]\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006_"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "data$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toInt", "toInt-impl", "(S)I", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class UShort implements Comparable<UShort> {

    /* renamed from: a  reason: collision with root package name */
    public static final short f2690a = 0;
    public static final short b = -1;
    public static final int c = 2;
    public static final int d = 16;
    public static final Companion e = new Companion((DefaultConstructorMarker) null);
    private final short f;

    @PublishedApi
    public static /* synthetic */ void a() {
    }

    public static boolean a(short s, @Nullable Object obj) {
        if (obj instanceof UShort) {
            if (s == ((UShort) obj).b()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean a(short s, short s2) {
        throw null;
    }

    @PublishedApi
    public static short b(short s) {
        return s;
    }

    @NotNull
    public static final /* synthetic */ UShort c(short s) {
        return new UShort(s);
    }

    public static int d(short s) {
        return s;
    }

    @InlineOnly
    private int e(short s) {
        return b(this.f, s);
    }

    @InlineOnly
    private static final byte i(short s) {
        return (byte) s;
    }

    @InlineOnly
    private static final short j(short s) {
        return s;
    }

    @InlineOnly
    private static final int k(short s) {
        return s & 65535;
    }

    @InlineOnly
    private static final long l(short s) {
        return ((long) s) & 65535;
    }

    @InlineOnly
    private static final short n(short s) {
        return s;
    }

    public final /* synthetic */ short b() {
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
    private /* synthetic */ UShort(short s) {
        this.f = s;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return e(((UShort) obj).b());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/UShort$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UShort;", "S", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @InlineOnly
    private static final int a(short s, byte b2) {
        return Intrinsics.a((int) s & 65535, (int) b2 & 255);
    }

    @InlineOnly
    private static int b(short s, short s2) {
        return Intrinsics.a((int) s & 65535, (int) s2 & 65535);
    }

    @InlineOnly
    private static final int a(short s, int i) {
        return UnsignedKt.a(UInt.b(s & 65535), i);
    }

    @InlineOnly
    private static final int a(short s, long j) {
        return UnsignedKt.a(ULong.b(((long) s) & 65535), j);
    }

    @InlineOnly
    private static final int b(short s, byte b2) {
        return UInt.b(UInt.b(s & 65535) + UInt.b(b2 & 255));
    }

    @InlineOnly
    private static final int c(short s, short s2) {
        return UInt.b(UInt.b(s & 65535) + UInt.b(s2 & 65535));
    }

    @InlineOnly
    private static final int b(short s, int i) {
        return UInt.b(UInt.b(s & 65535) + i);
    }

    @InlineOnly
    private static final long b(short s, long j) {
        return ULong.b(ULong.b(((long) s) & 65535) + j);
    }

    @InlineOnly
    private static final int c(short s, byte b2) {
        return UInt.b(UInt.b(s & 65535) - UInt.b(b2 & 255));
    }

    @InlineOnly
    private static final int d(short s, short s2) {
        return UInt.b(UInt.b(s & 65535) - UInt.b(s2 & 65535));
    }

    @InlineOnly
    private static final int c(short s, int i) {
        return UInt.b(UInt.b(s & 65535) - i);
    }

    @InlineOnly
    private static final long c(short s, long j) {
        return ULong.b(ULong.b(((long) s) & 65535) - j);
    }

    @InlineOnly
    private static final int d(short s, byte b2) {
        return UInt.b(UInt.b(s & 65535) * UInt.b(b2 & 255));
    }

    @InlineOnly
    private static final int e(short s, short s2) {
        return UInt.b(UInt.b(s & 65535) * UInt.b(s2 & 65535));
    }

    @InlineOnly
    private static final int d(short s, int i) {
        return UInt.b(UInt.b(s & 65535) * i);
    }

    @InlineOnly
    private static final long d(short s, long j) {
        return ULong.b(ULong.b(((long) s) & 65535) * j);
    }

    @InlineOnly
    private static final int e(short s, byte b2) {
        return UnsignedKt.b(UInt.b(s & 65535), UInt.b(b2 & 255));
    }

    @InlineOnly
    private static final int f(short s, short s2) {
        return UnsignedKt.b(UInt.b(s & 65535), UInt.b(s2 & 65535));
    }

    @InlineOnly
    private static final int e(short s, int i) {
        return UnsignedKt.b(UInt.b(s & 65535), i);
    }

    @InlineOnly
    private static final long e(short s, long j) {
        return UnsignedKt.b(ULong.b(((long) s) & 65535), j);
    }

    @InlineOnly
    private static final int f(short s, byte b2) {
        return UnsignedKt.c(UInt.b(s & 65535), UInt.b(b2 & 255));
    }

    @InlineOnly
    private static final int g(short s, short s2) {
        return UnsignedKt.c(UInt.b(s & 65535), UInt.b(s2 & 65535));
    }

    @InlineOnly
    private static final int f(short s, int i) {
        return UnsignedKt.c(UInt.b(s & 65535), i);
    }

    @InlineOnly
    private static final long f(short s, long j) {
        return UnsignedKt.c(ULong.b(((long) s) & 65535), j);
    }

    @InlineOnly
    private static final short f(short s) {
        return b((short) (s + 1));
    }

    @InlineOnly
    private static final short g(short s) {
        return b((short) (s - 1));
    }

    @InlineOnly
    private static final UIntRange h(short s, short s2) {
        return new UIntRange(UInt.b(s & 65535), UInt.b(s2 & 65535), (DefaultConstructorMarker) null);
    }

    @InlineOnly
    private static final short i(short s, short s2) {
        return b((short) (s & s2));
    }

    @InlineOnly
    private static final short j(short s, short s2) {
        return b((short) (s | s2));
    }

    @InlineOnly
    private static final short k(short s, short s2) {
        return b((short) (s ^ s2));
    }

    @InlineOnly
    private static final short h(short s) {
        return b((short) (s ^ -1));
    }

    @InlineOnly
    private static final byte m(short s) {
        return UByte.b((byte) s);
    }

    @InlineOnly
    private static final int o(short s) {
        return UInt.b(s & 65535);
    }

    @InlineOnly
    private static final long p(short s) {
        return ULong.b(((long) s) & 65535);
    }

    @NotNull
    public static String a(short s) {
        return String.valueOf(s & 65535);
    }
}
