package org.apache.commons.lang.math;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import org.apache.commons.lang.text.StrBuilder;

public final class LongRange extends Range implements Serializable {
    private static final long serialVersionUID = 71849363892720L;

    /* renamed from: a  reason: collision with root package name */
    private transient Long f3393a = null;
    private transient Long b = null;
    private transient int c = 0;
    private transient String d = null;
    private final long max;
    private final long min;

    public LongRange(long j) {
        this.min = j;
        this.max = j;
    }

    public LongRange(Number number) {
        if (number != null) {
            this.min = number.longValue();
            this.max = number.longValue();
            if (number instanceof Long) {
                Long l = (Long) number;
                this.f3393a = l;
                this.b = l;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The number must not be null");
    }

    public LongRange(long j, long j2) {
        if (j2 < j) {
            this.min = j2;
            this.max = j;
            return;
        }
        this.min = j;
        this.max = j2;
    }

    public LongRange(Number number, Number number2) {
        if (number == null || number2 == null) {
            throw new IllegalArgumentException("The numbers must not be null");
        }
        long longValue = number.longValue();
        long longValue2 = number2.longValue();
        if (longValue2 < longValue) {
            this.min = longValue2;
            this.max = longValue;
            if (number2 instanceof Long) {
                this.f3393a = (Long) number2;
            }
            if (number instanceof Long) {
                this.b = (Long) number;
                return;
            }
            return;
        }
        this.min = longValue;
        this.max = longValue2;
        if (number instanceof Long) {
            this.f3393a = (Long) number;
        }
        if (number2 instanceof Long) {
            this.b = (Long) number2;
        }
    }

    public Number getMinimumNumber() {
        if (this.f3393a == null) {
            this.f3393a = new Long(this.min);
        }
        return this.f3393a;
    }

    public long getMinimumLong() {
        return this.min;
    }

    public int getMinimumInteger() {
        return (int) this.min;
    }

    public double getMinimumDouble() {
        return (double) this.min;
    }

    public float getMinimumFloat() {
        return (float) this.min;
    }

    public Number getMaximumNumber() {
        if (this.b == null) {
            this.b = new Long(this.max);
        }
        return this.b;
    }

    public long getMaximumLong() {
        return this.max;
    }

    public int getMaximumInteger() {
        return (int) this.max;
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
        return containsLong(number.longValue());
    }

    public boolean containsLong(long j) {
        return j >= this.min && j <= this.max;
    }

    public boolean containsRange(Range range) {
        return range != null && containsLong(range.getMinimumLong()) && containsLong(range.getMaximumLong());
    }

    public boolean overlapsRange(Range range) {
        if (range == null) {
            return false;
        }
        return range.containsLong(this.min) || range.containsLong(this.max) || containsLong(range.getMinimumLong());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LongRange)) {
            return false;
        }
        LongRange longRange = (LongRange) obj;
        if (this.min == longRange.min && this.max == longRange.max) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = 17;
            this.c = (this.c * 37) + getClass().hashCode();
            this.c = (this.c * 37) + ((int) (this.min ^ (this.min >> 32)));
            this.c = (this.c * 37) + ((int) (this.max ^ (this.max >> 32)));
        }
        return this.c;
    }

    public String toString() {
        if (this.d == null) {
            StrBuilder strBuilder = new StrBuilder(32);
            strBuilder.c("Range[");
            strBuilder.a(this.min);
            strBuilder.a(',');
            strBuilder.a(this.max);
            strBuilder.a((char) Operators.ARRAY_END);
            this.d = strBuilder.toString();
        }
        return this.d;
    }

    public long[] toArray() {
        long[] jArr = new long[((int) ((this.max - this.min) + 1))];
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = this.min + ((long) i);
        }
        return jArr;
    }
}
