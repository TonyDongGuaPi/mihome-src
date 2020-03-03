package org.apache.commons.lang.math;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import org.apache.commons.lang.text.StrBuilder;

public final class IntRange extends Range implements Serializable {
    private static final long serialVersionUID = 71849363892730L;

    /* renamed from: a  reason: collision with root package name */
    private transient Integer f3391a = null;
    private transient Integer b = null;
    private transient int c = 0;
    private transient String d = null;
    private final int max;
    private final int min;

    public IntRange(int i) {
        this.min = i;
        this.max = i;
    }

    public IntRange(Number number) {
        if (number != null) {
            this.min = number.intValue();
            this.max = number.intValue();
            if (number instanceof Integer) {
                Integer num = (Integer) number;
                this.f3391a = num;
                this.b = num;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The number must not be null");
    }

    public IntRange(int i, int i2) {
        if (i2 < i) {
            this.min = i2;
            this.max = i;
            return;
        }
        this.min = i;
        this.max = i2;
    }

    public IntRange(Number number, Number number2) {
        if (number == null || number2 == null) {
            throw new IllegalArgumentException("The numbers must not be null");
        }
        int intValue = number.intValue();
        int intValue2 = number2.intValue();
        if (intValue2 < intValue) {
            this.min = intValue2;
            this.max = intValue;
            if (number2 instanceof Integer) {
                this.f3391a = (Integer) number2;
            }
            if (number instanceof Integer) {
                this.b = (Integer) number;
                return;
            }
            return;
        }
        this.min = intValue;
        this.max = intValue2;
        if (number instanceof Integer) {
            this.f3391a = (Integer) number;
        }
        if (number2 instanceof Integer) {
            this.b = (Integer) number2;
        }
    }

    public Number getMinimumNumber() {
        if (this.f3391a == null) {
            this.f3391a = new Integer(this.min);
        }
        return this.f3391a;
    }

    public long getMinimumLong() {
        return (long) this.min;
    }

    public int getMinimumInteger() {
        return this.min;
    }

    public double getMinimumDouble() {
        return (double) this.min;
    }

    public float getMinimumFloat() {
        return (float) this.min;
    }

    public Number getMaximumNumber() {
        if (this.b == null) {
            this.b = new Integer(this.max);
        }
        return this.b;
    }

    public long getMaximumLong() {
        return (long) this.max;
    }

    public int getMaximumInteger() {
        return this.max;
    }

    public double getMaximumDouble() {
        return (double) this.max;
    }

    public float getMaximumFloat() {
        return (float) this.max;
    }

    public boolean containsNumber(Number number) {
        if (number == null) {
            return false;
        }
        return containsInteger(number.intValue());
    }

    public boolean containsInteger(int i) {
        return i >= this.min && i <= this.max;
    }

    public boolean containsRange(Range range) {
        return range != null && containsInteger(range.getMinimumInteger()) && containsInteger(range.getMaximumInteger());
    }

    public boolean overlapsRange(Range range) {
        if (range == null) {
            return false;
        }
        return range.containsInteger(this.min) || range.containsInteger(this.max) || containsInteger(range.getMinimumInteger());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntRange)) {
            return false;
        }
        IntRange intRange = (IntRange) obj;
        if (this.min == intRange.min && this.max == intRange.max) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = 17;
            this.c = (this.c * 37) + getClass().hashCode();
            this.c = (this.c * 37) + this.min;
            this.c = (this.c * 37) + this.max;
        }
        return this.c;
    }

    public String toString() {
        if (this.d == null) {
            StrBuilder strBuilder = new StrBuilder(32);
            strBuilder.c("Range[");
            strBuilder.e(this.min);
            strBuilder.a(',');
            strBuilder.e(this.max);
            strBuilder.a((char) Operators.ARRAY_END);
            this.d = strBuilder.toString();
        }
        return this.d;
    }

    public int[] toArray() {
        int[] iArr = new int[((this.max - this.min) + 1)];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = this.min + i;
        }
        return iArr;
    }
}
