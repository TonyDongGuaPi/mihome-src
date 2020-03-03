package org.apache.commons.lang.math;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.math.BigInteger;
import org.apache.commons.lang.text.StrBuilder;

public final class Fraction extends Number implements Comparable {
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ZERO = new Fraction(0, 1);
    private static final long serialVersionUID = 65382027393090L;

    /* renamed from: a  reason: collision with root package name */
    private transient int f3390a = 0;
    private transient String b = null;
    private transient String c = null;
    private final int denominator;
    private final int numerator;

    private Fraction(int i, int i2) {
        this.numerator = i;
        this.denominator = i2;
    }

    public static Fraction getFraction(int i, int i2) {
        if (i2 != 0) {
            if (i2 < 0) {
                if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                    throw new ArithmeticException("overflow: can't negate");
                }
                i = -i;
                i2 = -i2;
            }
            return new Fraction(i, i2);
        }
        throw new ArithmeticException("The denominator must not be zero");
    }

    public static Fraction getFraction(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        } else if (i3 < 0) {
            throw new ArithmeticException("The denominator must not be negative");
        } else if (i2 >= 0) {
            long j = i < 0 ? (((long) i) * ((long) i3)) - ((long) i2) : (((long) i) * ((long) i3)) + ((long) i2);
            if (j >= -2147483648L && j <= 2147483647L) {
                return new Fraction((int) j, i3);
            }
            throw new ArithmeticException("Numerator too large to represent as an Integer.");
        } else {
            throw new ArithmeticException("The numerator must not be negative");
        }
    }

    public static Fraction getReducedFraction(int i, int i2) {
        if (i2 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        } else if (i == 0) {
            return ZERO;
        } else {
            if (i2 == Integer.MIN_VALUE && (i & 1) == 0) {
                i /= 2;
                i2 /= 2;
            }
            if (i2 < 0) {
                if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                    throw new ArithmeticException("overflow: can't negate");
                }
                i = -i;
                i2 = -i2;
            }
            int a2 = a(i, i2);
            return new Fraction(i / a2, i2 / a2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.commons.lang.math.Fraction getFraction(double r24) {
        /*
            r0 = 0
            int r4 = (r24 > r0 ? 1 : (r24 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x0008
            r1 = -1
            goto L_0x0009
        L_0x0008:
            r1 = 1
        L_0x0009:
            double r2 = java.lang.Math.abs(r24)
            r4 = 4746794007244308480(0x41dfffffffc00000, double:2.147483647E9)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x0093
            boolean r4 = java.lang.Double.isNaN(r2)
            if (r4 != 0) goto L_0x0093
            int r4 = (int) r2
            double r5 = (double) r4
            java.lang.Double.isNaN(r5)
            double r2 = r2 - r5
            int r5 = (int) r2
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r8 = (double) r5
            java.lang.Double.isNaN(r8)
            double r8 = r2 - r8
            r10 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            r12 = 0
            r17 = r1
            r12 = 1
            r13 = 0
            r14 = 0
            r15 = 1
            r16 = 1
        L_0x0039:
            double r0 = r6 / r8
            int r0 = (int) r0
            r18 = r10
            double r10 = (double) r0
            java.lang.Double.isNaN(r10)
            double r10 = r10 * r8
            double r6 = r6 - r10
            int r1 = r5 * r12
            int r1 = r1 + r13
            int r5 = r5 * r14
            int r5 = r5 + r15
            double r10 = (double) r1
            r20 = r0
            r21 = r1
            double r0 = (double) r5
            java.lang.Double.isNaN(r10)
            java.lang.Double.isNaN(r0)
            double r10 = r10 / r0
            double r0 = r2 - r10
            double r10 = java.lang.Math.abs(r0)
            r0 = 1
            int r1 = r16 + 1
            int r13 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1))
            r15 = 25
            if (r13 <= 0) goto L_0x007f
            r13 = 10000(0x2710, float:1.4013E-41)
            if (r5 > r13) goto L_0x007f
            if (r5 <= 0) goto L_0x007f
            if (r1 < r15) goto L_0x0070
            goto L_0x007f
        L_0x0070:
            r16 = r1
            r13 = r12
            r15 = r14
            r12 = r21
            r14 = r5
            r5 = r20
            r22 = r6
            r6 = r8
            r8 = r22
            goto L_0x0039
        L_0x007f:
            if (r1 == r15) goto L_0x008b
            int r4 = r4 * r14
            int r12 = r12 + r4
            int r12 = r12 * r17
            org.apache.commons.lang.math.Fraction r0 = getReducedFraction(r12, r14)
            return r0
        L_0x008b:
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.String r1 = "Unable to convert double to fraction"
            r0.<init>(r1)
            throw r0
        L_0x0093:
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.String r1 = "The value must not be greater than Integer.MAX_VALUE or NaN"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.math.Fraction.getFraction(double):org.apache.commons.lang.math.Fraction");
    }

    public static Fraction getFraction(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The string must not be null");
        } else if (str.indexOf(46) >= 0) {
            return getFraction(Double.parseDouble(str));
        } else {
            int indexOf = str.indexOf(32);
            if (indexOf > 0) {
                int parseInt = Integer.parseInt(str.substring(0, indexOf));
                String substring = str.substring(indexOf + 1);
                int indexOf2 = substring.indexOf(47);
                if (indexOf2 >= 0) {
                    return getFraction(parseInt, Integer.parseInt(substring.substring(0, indexOf2)), Integer.parseInt(substring.substring(indexOf2 + 1)));
                }
                throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
            }
            int indexOf3 = str.indexOf(47);
            if (indexOf3 < 0) {
                return getFraction(Integer.parseInt(str), 1);
            }
            return getFraction(Integer.parseInt(str.substring(0, indexOf3)), Integer.parseInt(str.substring(indexOf3 + 1)));
        }
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getProperNumerator() {
        return Math.abs(this.numerator % this.denominator);
    }

    public int getProperWhole() {
        return this.numerator / this.denominator;
    }

    public int intValue() {
        return this.numerator / this.denominator;
    }

    public long longValue() {
        return ((long) this.numerator) / ((long) this.denominator);
    }

    public float floatValue() {
        return ((float) this.numerator) / ((float) this.denominator);
    }

    public double doubleValue() {
        double d = (double) this.numerator;
        double d2 = (double) this.denominator;
        Double.isNaN(d);
        Double.isNaN(d2);
        return d / d2;
    }

    public Fraction reduce() {
        if (this.numerator == 0) {
            return equals(ZERO) ? this : ZERO;
        }
        int a2 = a(Math.abs(this.numerator), this.denominator);
        if (a2 == 1) {
            return this;
        }
        return getFraction(this.numerator / a2, this.denominator / a2);
    }

    public Fraction invert() {
        if (this.numerator == 0) {
            throw new ArithmeticException("Unable to invert zero.");
        } else if (this.numerator == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow: can't negate numerator");
        } else if (this.numerator < 0) {
            return new Fraction(-this.denominator, -this.numerator);
        } else {
            return new Fraction(this.denominator, this.numerator);
        }
    }

    public Fraction negate() {
        if (this.numerator != Integer.MIN_VALUE) {
            return new Fraction(-this.numerator, this.denominator);
        }
        throw new ArithmeticException("overflow: too large to negate");
    }

    public Fraction abs() {
        if (this.numerator >= 0) {
            return this;
        }
        return negate();
    }

    public Fraction pow(int i) {
        if (i == 1) {
            return this;
        }
        if (i == 0) {
            return ONE;
        }
        if (i >= 0) {
            Fraction multiplyBy = multiplyBy(this);
            if (i % 2 == 0) {
                return multiplyBy.pow(i / 2);
            }
            return multiplyBy.pow(i / 2).multiplyBy(this);
        } else if (i == Integer.MIN_VALUE) {
            return invert().pow(2).pow(-(i / 2));
        } else {
            return invert().pow(-i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(int r5, int r6) {
        /*
            int r0 = java.lang.Math.abs(r5)
            r1 = 1
            if (r0 <= r1) goto L_0x0051
            int r0 = java.lang.Math.abs(r6)
            if (r0 > r1) goto L_0x000e
            goto L_0x0051
        L_0x000e:
            if (r5 <= 0) goto L_0x0011
            int r5 = -r5
        L_0x0011:
            if (r6 <= 0) goto L_0x0014
            int r6 = -r6
        L_0x0014:
            r0 = 0
        L_0x0015:
            r2 = r5 & 1
            r3 = 31
            if (r2 != 0) goto L_0x0028
            r4 = r6 & 1
            if (r4 != 0) goto L_0x0028
            if (r0 >= r3) goto L_0x0028
            int r5 = r5 / 2
            int r6 = r6 / 2
            int r0 = r0 + 1
            goto L_0x0015
        L_0x0028:
            if (r0 == r3) goto L_0x0049
            if (r2 != r1) goto L_0x002e
            r2 = r6
            goto L_0x0031
        L_0x002e:
            int r2 = r5 / 2
            int r2 = -r2
        L_0x0031:
            r3 = r2 & 1
            if (r3 != 0) goto L_0x0038
            int r2 = r2 / 2
            goto L_0x0031
        L_0x0038:
            if (r2 <= 0) goto L_0x003c
            int r5 = -r2
            goto L_0x003d
        L_0x003c:
            r6 = r2
        L_0x003d:
            int r2 = r6 - r5
            int r2 = r2 / 2
            if (r2 != 0) goto L_0x0031
            int r5 = -r5
            int r6 = r1 << r0
            int r5 = r5 * r6
            return r5
        L_0x0049:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "overflow: gcd is 2^31"
            r5.<init>(r6)
            throw r5
        L_0x0051:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.math.Fraction.a(int, int):int");
    }

    private static int b(int i, int i2) {
        long j = ((long) i) * ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: mul");
    }

    private static int c(int i, int i2) {
        long j = ((long) i) * ((long) i2);
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: mulPos");
    }

    private static int d(int i, int i2) {
        long j = ((long) i) + ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: add");
    }

    private static int e(int i, int i2) {
        long j = ((long) i) - ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: add");
    }

    public Fraction add(Fraction fraction) {
        return a(fraction, true);
    }

    public Fraction subtract(Fraction fraction) {
        return a(fraction, false);
    }

    private Fraction a(Fraction fraction, boolean z) {
        int i;
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        } else if (this.numerator == 0) {
            return z ? fraction : fraction.negate();
        } else {
            if (fraction.numerator == 0) {
                return this;
            }
            int a2 = a(this.denominator, fraction.denominator);
            if (a2 == 1) {
                int b2 = b(this.numerator, fraction.denominator);
                int b3 = b(fraction.numerator, this.denominator);
                return new Fraction(z ? d(b2, b3) : e(b2, b3), c(this.denominator, fraction.denominator));
            }
            BigInteger multiply = BigInteger.valueOf((long) this.numerator).multiply(BigInteger.valueOf((long) (fraction.denominator / a2)));
            BigInteger multiply2 = BigInteger.valueOf((long) fraction.numerator).multiply(BigInteger.valueOf((long) (this.denominator / a2)));
            BigInteger add = z ? multiply.add(multiply2) : multiply.subtract(multiply2);
            int intValue = add.mod(BigInteger.valueOf((long) a2)).intValue();
            if (intValue == 0) {
                i = a2;
            } else {
                i = a(intValue, a2);
            }
            BigInteger divide = add.divide(BigInteger.valueOf((long) i));
            if (divide.bitLength() <= 31) {
                return new Fraction(divide.intValue(), c(this.denominator / a2, fraction.denominator / i));
            }
            throw new ArithmeticException("overflow: numerator too large after multiply");
        }
    }

    public Fraction multiplyBy(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        } else if (this.numerator == 0 || fraction.numerator == 0) {
            return ZERO;
        } else {
            int a2 = a(this.numerator, fraction.denominator);
            int a3 = a(fraction.numerator, this.denominator);
            return getReducedFraction(b(this.numerator / a2, fraction.numerator / a3), c(this.denominator / a3, fraction.denominator / a2));
        }
    }

    public Fraction divideBy(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        } else if (fraction.numerator != 0) {
            return multiplyBy(fraction.invert());
        } else {
            throw new ArithmeticException("The fraction to divide by must not be zero");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) obj;
        if (getNumerator() == fraction.getNumerator() && getDenominator() == fraction.getDenominator()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.f3390a == 0) {
            this.f3390a = ((getNumerator() + 629) * 37) + getDenominator();
        }
        return this.f3390a;
    }

    public int compareTo(Object obj) {
        Fraction fraction = (Fraction) obj;
        if (this == fraction) {
            return 0;
        }
        if (this.numerator == fraction.numerator && this.denominator == fraction.denominator) {
            return 0;
        }
        long j = ((long) this.numerator) * ((long) fraction.denominator);
        long j2 = ((long) fraction.numerator) * ((long) this.denominator);
        if (j == j2) {
            return 0;
        }
        return j < j2 ? -1 : 1;
    }

    public String toString() {
        if (this.b == null) {
            this.b = new StrBuilder(32).e(getNumerator()).a((char) IOUtils.f15883a).e(getDenominator()).toString();
        }
        return this.b;
    }

    public String toProperString() {
        if (this.c == null) {
            if (this.numerator == 0) {
                this.c = "0";
            } else if (this.numerator == this.denominator) {
                this.c = "1";
            } else if (this.numerator == this.denominator * -1) {
                this.c = "-1";
            } else {
                if ((this.numerator > 0 ? -this.numerator : this.numerator) < (-this.denominator)) {
                    int properNumerator = getProperNumerator();
                    if (properNumerator == 0) {
                        this.c = Integer.toString(getProperWhole());
                    } else {
                        this.c = new StrBuilder(32).e(getProperWhole()).a(' ').e(properNumerator).a((char) IOUtils.f15883a).e(getDenominator()).toString();
                    }
                } else {
                    this.c = new StrBuilder(32).e(getNumerator()).a((char) IOUtils.f15883a).e(getDenominator()).toString();
                }
            }
        }
        return this.c;
    }
}
