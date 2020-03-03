package org.apache.commons.lang.math;

import com.taobao.weex.el.parse.Operators;
import org.apache.commons.lang.text.StrBuilder;

public abstract class Range {
    public abstract boolean containsNumber(Number number);

    public abstract Number getMaximumNumber();

    public abstract Number getMinimumNumber();

    public long getMinimumLong() {
        return getMinimumNumber().longValue();
    }

    public int getMinimumInteger() {
        return getMinimumNumber().intValue();
    }

    public double getMinimumDouble() {
        return getMinimumNumber().doubleValue();
    }

    public float getMinimumFloat() {
        return getMinimumNumber().floatValue();
    }

    public long getMaximumLong() {
        return getMaximumNumber().longValue();
    }

    public int getMaximumInteger() {
        return getMaximumNumber().intValue();
    }

    public double getMaximumDouble() {
        return getMaximumNumber().doubleValue();
    }

    public float getMaximumFloat() {
        return getMaximumNumber().floatValue();
    }

    public boolean containsLong(Number number) {
        if (number == null) {
            return false;
        }
        return containsLong(number.longValue());
    }

    public boolean containsLong(long j) {
        return j >= getMinimumLong() && j <= getMaximumLong();
    }

    public boolean containsInteger(Number number) {
        if (number == null) {
            return false;
        }
        return containsInteger(number.intValue());
    }

    public boolean containsInteger(int i) {
        return i >= getMinimumInteger() && i <= getMaximumInteger();
    }

    public boolean containsDouble(Number number) {
        if (number == null) {
            return false;
        }
        return containsDouble(number.doubleValue());
    }

    public boolean containsDouble(double d) {
        return NumberUtils.a(getMinimumDouble(), d) <= 0 && NumberUtils.a(getMaximumDouble(), d) >= 0;
    }

    public boolean containsFloat(Number number) {
        if (number == null) {
            return false;
        }
        return containsFloat(number.floatValue());
    }

    public boolean containsFloat(float f) {
        return NumberUtils.a(getMinimumFloat(), f) <= 0 && NumberUtils.a(getMaximumFloat(), f) >= 0;
    }

    public boolean containsRange(Range range) {
        return range != null && containsNumber(range.getMinimumNumber()) && containsNumber(range.getMaximumNumber());
    }

    public boolean overlapsRange(Range range) {
        if (range == null) {
            return false;
        }
        return range.containsNumber(getMinimumNumber()) || range.containsNumber(getMaximumNumber()) || containsNumber(range.getMinimumNumber());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Range range = (Range) obj;
        if (!getMinimumNumber().equals(range.getMinimumNumber()) || !getMaximumNumber().equals(range.getMaximumNumber())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((629 + getClass().hashCode()) * 37) + getMinimumNumber().hashCode()) * 37) + getMaximumNumber().hashCode();
    }

    public String toString() {
        StrBuilder strBuilder = new StrBuilder(32);
        strBuilder.c("Range[");
        strBuilder.a((Object) getMinimumNumber());
        strBuilder.a(',');
        strBuilder.a((Object) getMaximumNumber());
        strBuilder.a((char) Operators.ARRAY_END);
        return strBuilder.toString();
    }
}
