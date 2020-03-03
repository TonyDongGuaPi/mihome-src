package kotlin.text;

import cn.com.fmsh.communication.core.MessageHead;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "UStringsKt")
public final class UStringsKt {
    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final String a(byte b, int i) {
        String num = Integer.toString(b & 255, CharsKt.a(i));
        Intrinsics.b(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final String a(short s, int i) {
        String num = Integer.toString(s & 65535, CharsKt.a(i));
        Intrinsics.b(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final String a(int i, int i2) {
        String l = Long.toString(((long) i) & MessageHead.SERIAL_MAK, CharsKt.a(i2));
        Intrinsics.b(l, "java.lang.Long.toString(this, checkRadix(radix))");
        return l;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final String a(long j, int i) {
        return UnsignedKt.a(j, CharsKt.a(i));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final byte a(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        UByte e = e(str);
        if (e != null) {
            return e.b();
        }
        StringsKt.j(str);
        throw null;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final byte a(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        UByte e = e(str, i);
        if (e != null) {
            return e.b();
        }
        StringsKt.j(str);
        throw null;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final short b(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        UShort f = f(str);
        if (f != null) {
            return f.b();
        }
        StringsKt.j(str);
        throw null;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final short b(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        UShort f = f(str, i);
        if (f != null) {
            return f.b();
        }
        StringsKt.j(str);
        throw null;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int c(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        UInt g = g(str);
        if (g != null) {
            return g.b();
        }
        StringsKt.j(str);
        throw null;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int c(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        UInt g = g(str, i);
        if (g != null) {
            return g.b();
        }
        StringsKt.j(str);
        throw null;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long d(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        ULong h = h(str);
        if (h != null) {
            return h.b();
        }
        StringsKt.j(str);
        throw null;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long d(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        ULong h = h(str, i);
        if (h != null) {
            return h.b();
        }
        StringsKt.j(str);
        throw null;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @Nullable
    public static final UByte e(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        return e(str, 10);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @Nullable
    public static final UByte e(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        UInt g = g(str, i);
        if (g == null) {
            return null;
        }
        int b = g.b();
        if (UnsignedKt.a(b, UInt.b(255)) > 0) {
            return null;
        }
        return UByte.c(UByte.b((byte) b));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @Nullable
    public static final UShort f(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        return f(str, 10);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @Nullable
    public static final UShort f(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        UInt g = g(str, i);
        if (g == null) {
            return null;
        }
        int b = g.b();
        if (UnsignedKt.a(b, UInt.b(65535)) > 0) {
            return null;
        }
        return UShort.c(UShort.b((short) b));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @Nullable
    public static final UInt g(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        return g(str, 10);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @Nullable
    public static final UInt g(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        CharsKt.a(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        char charAt = str.charAt(0);
        int i3 = 1;
        if (charAt >= '0') {
            i3 = 0;
        } else if (length == 1 || charAt != '+') {
            return null;
        }
        int b = UInt.b(i);
        int b2 = UnsignedKt.b(-1, b);
        while (i3 < length) {
            int a2 = CharsKt.a(str.charAt(i3), i);
            if (a2 < 0 || UnsignedKt.a(i2, b2) > 0) {
                return null;
            }
            int b3 = UInt.b(i2 * b);
            int b4 = UInt.b(UInt.b(a2) + b3);
            if (UnsignedKt.a(b4, b3) < 0) {
                return null;
            }
            i3++;
            i2 = b4;
        }
        return UInt.c(i2);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @Nullable
    public static final ULong h(@NotNull String str) {
        Intrinsics.f(str, "receiver$0");
        return h(str, 10);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @Nullable
    public static final ULong h(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        CharsKt.a(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        char charAt = str.charAt(0);
        if (charAt < '0') {
            if (length == 1 || charAt != '+') {
                return null;
            }
            i2 = 1;
        }
        long b = ((long) UInt.b(i)) & MessageHead.SERIAL_MAK;
        long b2 = UnsignedKt.b(-1, ULong.b(b));
        long j = 0;
        while (i2 < length) {
            int a2 = CharsKt.a(str.charAt(i2), i);
            if (a2 < 0 || UnsignedKt.a(j, b2) > 0) {
                return null;
            }
            long b3 = ULong.b(j * ULong.b(b));
            long b4 = ULong.b(ULong.b(((long) UInt.b(a2)) & MessageHead.SERIAL_MAK) + b3);
            if (UnsignedKt.a(b4, b3) < 0) {
                return null;
            }
            i2++;
            j = b4;
        }
        return ULong.c(j);
    }
}
