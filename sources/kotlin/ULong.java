package kotlin;

import cn.com.fmsh.communication.core.MessageHead;
import kotlin.internal.InlineOnly;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001eB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\rHÖ\u0001J\u0013\u0010'\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u0013\u0010)\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0005J\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001dJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u001fJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u000bJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b/\u0010\"J\u001b\u00100\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001dJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u001fJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b6\u0010\"J\u001b\u00107\u001a\u0002082\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001dJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u001fJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\"J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010C\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u001dJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bI\u0010\"J\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020\rH\b¢\u0006\u0004\bO\u0010PJ\u0010\u0010Q\u001a\u00020\u0003H\b¢\u0006\u0004\bR\u0010\u0005J\u0010\u0010S\u001a\u00020TH\b¢\u0006\u0004\bU\u0010VJ\u000f\u0010W\u001a\u00020XH\u0016¢\u0006\u0004\bY\u0010ZJ\u0013\u0010[\u001a\u00020\u000eH\bø\u0001\u0000¢\u0006\u0004\b\\\u0010MJ\u0013\u0010]\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\b^\u0010PJ\u0013\u0010_\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b`\u0010\u0005J\u0013\u0010a\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\bb\u0010VJ\u001b\u0010c\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\bd\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006f"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "data$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-impl", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class ULong implements Comparable<ULong> {

    /* renamed from: a  reason: collision with root package name */
    public static final long f2686a = 0;
    public static final long b = -1;
    public static final int c = 8;
    public static final int d = 64;
    public static final Companion e = new Companion((DefaultConstructorMarker) null);
    private final long f;

    @PublishedApi
    public static /* synthetic */ void a() {
    }

    public static final boolean a(long j, long j2) {
        throw null;
    }

    public static boolean a(long j, @Nullable Object obj) {
        if (obj instanceof ULong) {
            if (j == ((ULong) obj).b()) {
                return true;
            }
        }
        return false;
    }

    @PublishedApi
    public static long b(long j) {
        return j;
    }

    @NotNull
    public static final /* synthetic */ ULong c(long j) {
        return new ULong(j);
    }

    public static int d(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @InlineOnly
    private int e(long j) {
        return b(this.f, j);
    }

    @InlineOnly
    private static final byte i(long j) {
        return (byte) ((int) j);
    }

    @InlineOnly
    private static final short j(long j) {
        return (short) ((int) j);
    }

    @InlineOnly
    private static final int k(long j) {
        return (int) j;
    }

    @InlineOnly
    private static final long l(long j) {
        return j;
    }

    @InlineOnly
    private static final long p(long j) {
        return j;
    }

    public final /* synthetic */ long b() {
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
    private /* synthetic */ ULong(long j) {
        this.f = j;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return e(((ULong) obj).b());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @InlineOnly
    private static final int a(long j, byte b2) {
        return UnsignedKt.a(j, b(((long) b2) & 255));
    }

    @InlineOnly
    private static final int a(long j, short s) {
        return UnsignedKt.a(j, b(((long) s) & 65535));
    }

    @InlineOnly
    private static final int a(long j, int i) {
        return UnsignedKt.a(j, b(((long) i) & MessageHead.SERIAL_MAK));
    }

    @InlineOnly
    private static int b(long j, long j2) {
        return UnsignedKt.a(j, j2);
    }

    @InlineOnly
    private static final long b(long j, byte b2) {
        return b(j + b(((long) b2) & 255));
    }

    @InlineOnly
    private static final long b(long j, short s) {
        return b(j + b(((long) s) & 65535));
    }

    @InlineOnly
    private static final long b(long j, int i) {
        return b(j + b(((long) i) & MessageHead.SERIAL_MAK));
    }

    @InlineOnly
    private static final long c(long j, long j2) {
        return b(j + j2);
    }

    @InlineOnly
    private static final long c(long j, byte b2) {
        return b(j - b(((long) b2) & 255));
    }

    @InlineOnly
    private static final long c(long j, short s) {
        return b(j - b(((long) s) & 65535));
    }

    @InlineOnly
    private static final long c(long j, int i) {
        return b(j - b(((long) i) & MessageHead.SERIAL_MAK));
    }

    @InlineOnly
    private static final long d(long j, long j2) {
        return b(j - j2);
    }

    @InlineOnly
    private static final long d(long j, byte b2) {
        return b(j * b(((long) b2) & 255));
    }

    @InlineOnly
    private static final long d(long j, short s) {
        return b(j * b(((long) s) & 65535));
    }

    @InlineOnly
    private static final long d(long j, int i) {
        return b(j * b(((long) i) & MessageHead.SERIAL_MAK));
    }

    @InlineOnly
    private static final long e(long j, long j2) {
        return b(j * j2);
    }

    @InlineOnly
    private static final long e(long j, byte b2) {
        return UnsignedKt.b(j, b(((long) b2) & 255));
    }

    @InlineOnly
    private static final long e(long j, short s) {
        return UnsignedKt.b(j, b(((long) s) & 65535));
    }

    @InlineOnly
    private static final long e(long j, int i) {
        return UnsignedKt.b(j, b(((long) i) & MessageHead.SERIAL_MAK));
    }

    @InlineOnly
    private static final long f(long j, long j2) {
        return UnsignedKt.b(j, j2);
    }

    @InlineOnly
    private static final long f(long j, byte b2) {
        return UnsignedKt.c(j, b(((long) b2) & 255));
    }

    @InlineOnly
    private static final long f(long j, short s) {
        return UnsignedKt.c(j, b(((long) s) & 65535));
    }

    @InlineOnly
    private static final long f(long j, int i) {
        return UnsignedKt.c(j, b(((long) i) & MessageHead.SERIAL_MAK));
    }

    @InlineOnly
    private static final long g(long j, long j2) {
        return UnsignedKt.c(j, j2);
    }

    @InlineOnly
    private static final long f(long j) {
        return b(j + 1);
    }

    @InlineOnly
    private static final long g(long j) {
        return b(j - 1);
    }

    @InlineOnly
    private static final ULongRange h(long j, long j2) {
        return new ULongRange(j, j2, (DefaultConstructorMarker) null);
    }

    @InlineOnly
    private static final long g(long j, int i) {
        return b(j << i);
    }

    @InlineOnly
    private static final long h(long j, int i) {
        return b(j >>> i);
    }

    @InlineOnly
    private static final long i(long j, long j2) {
        return b(j & j2);
    }

    @InlineOnly
    private static final long j(long j, long j2) {
        return b(j | j2);
    }

    @InlineOnly
    private static final long k(long j, long j2) {
        return b(j ^ j2);
    }

    @InlineOnly
    private static final long h(long j) {
        return b(j ^ -1);
    }

    @InlineOnly
    private static final byte m(long j) {
        return UByte.b((byte) ((int) j));
    }

    @InlineOnly
    private static final short n(long j) {
        return UShort.b((short) ((int) j));
    }

    @InlineOnly
    private static final int o(long j) {
        return UInt.b((int) j);
    }

    @NotNull
    public static String a(long j) {
        return UnsignedKt.a(j);
    }
}
