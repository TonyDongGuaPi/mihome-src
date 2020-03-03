package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\n\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000bH\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000eH\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\f\u001a\u00020\rH\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u000fH\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u0010H\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\b\u001a\r\u0010\u0011\u001a\u00020\u0001*\u00020\u0001H\n¨\u0006\u0012"}, d2 = {"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "mod", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/MathKt")
class MathKt__BigDecimalsKt {
    @InlineOnly
    private static final BigDecimal a(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal add = bigDecimal.add(bigDecimal2);
        Intrinsics.b(add, "this.add(other)");
        return add;
    }

    @InlineOnly
    private static final BigDecimal b(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal subtract = bigDecimal.subtract(bigDecimal2);
        Intrinsics.b(subtract, "this.subtract(other)");
        return subtract;
    }

    @InlineOnly
    private static final BigDecimal c(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal multiply = bigDecimal.multiply(bigDecimal2);
        Intrinsics.b(multiply, "this.multiply(other)");
        return multiply;
    }

    @InlineOnly
    private static final BigDecimal d(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal divide = bigDecimal.divide(bigDecimal2, RoundingMode.HALF_EVEN);
        Intrinsics.b(divide, "this.divide(other, RoundingMode.HALF_EVEN)");
        return divide;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use rem(other) instead", replaceWith = @ReplaceWith(expression = "rem(other)", imports = {}))
    @InlineOnly
    private static final BigDecimal e(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal remainder = bigDecimal.remainder(bigDecimal2);
        Intrinsics.b(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigDecimal f(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal remainder = bigDecimal.remainder(bigDecimal2);
        Intrinsics.b(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigDecimal a(@NotNull BigDecimal bigDecimal) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal negate = bigDecimal.negate();
        Intrinsics.b(negate, "this.negate()");
        return negate;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal b(@NotNull BigDecimal bigDecimal) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal add = bigDecimal.add(BigDecimal.ONE);
        Intrinsics.b(add, "this.add(BigDecimal.ONE)");
        return add;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal c(@NotNull BigDecimal bigDecimal) {
        Intrinsics.f(bigDecimal, "receiver$0");
        BigDecimal subtract = bigDecimal.subtract(BigDecimal.ONE);
        Intrinsics.b(subtract, "this.subtract(BigDecimal.ONE)");
        return subtract;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal a(int i) {
        BigDecimal valueOf = BigDecimal.valueOf((long) i);
        Intrinsics.b(valueOf, "BigDecimal.valueOf(this.toLong())");
        return valueOf;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal a(int i, MathContext mathContext) {
        return new BigDecimal(i, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal a(long j) {
        BigDecimal valueOf = BigDecimal.valueOf(j);
        Intrinsics.b(valueOf, "BigDecimal.valueOf(this)");
        return valueOf;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal a(long j, MathContext mathContext) {
        return new BigDecimal(j, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal a(float f) {
        return new BigDecimal(String.valueOf(f));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal a(float f, MathContext mathContext) {
        return new BigDecimal(String.valueOf(f), mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal a(double d) {
        return new BigDecimal(String.valueOf(d));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal a(double d, MathContext mathContext) {
        return new BigDecimal(String.valueOf(d), mathContext);
    }
}
