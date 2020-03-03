package org.apache.commons.lang.math;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import org.apache.commons.lang.text.StrBuilder;

public final class DoubleRange extends Range implements Serializable {
    private static final long serialVersionUID = 71849363892740L;

    /* renamed from: a  reason: collision with root package name */
    private transient Double f3388a = null;
    private transient Double b = null;
    private transient int c = 0;
    private transient String d = null;
    private final double max;
    private final double min;

    public DoubleRange(double d2) {
        if (!Double.isNaN(d2)) {
            this.min = d2;
            this.max = d2;
            return;
        }
        throw new IllegalArgumentException("The number must not be NaN");
    }

    public DoubleRange(Number number) {
        if (number != null) {
            this.min = number.doubleValue();
            this.max = number.doubleValue();
            if (Double.isNaN(this.min) || Double.isNaN(this.max)) {
                throw new IllegalArgumentException("The number must not be NaN");
            } else if (number instanceof Double) {
                Double d2 = (Double) number;
                this.f3388a = d2;
                this.b = d2;
            }
        } else {
            throw new IllegalArgumentException("The number must not be null");
        }
    }

    public DoubleRange(double d2, double d3) {
        if (Double.isNaN(d2) || Double.isNaN(d3)) {
            throw new IllegalArgumentException("The numbers must not be NaN");
        } else if (d3 < d2) {
            this.min = d3;
            this.max = d2;
        } else {
            this.min = d2;
            this.max = d3;
        }
    }

    public DoubleRange(Number number, Number number2) {
        if (number == null || number2 == null) {
            throw new IllegalArgumentException("The numbers must not be null");
        }
        double doubleValue = number.doubleValue();
        double doubleValue2 = number2.doubleValue();
        if (Double.isNaN(doubleValue) || Double.isNaN(doubleValue2)) {
            throw new IllegalArgumentException("The numbers must not be NaN");
        } else if (doubleValue2 < doubleValue) {
            this.min = doubleValue2;
            this.max = doubleValue;
            if (number2 instanceof Double) {
                this.f3388a = (Double) number2;
            }
            if (number instanceof Double) {
                this.b = (Double) number;
            }
        } else {
            this.min = doubleValue;
            this.max = doubleValue2;
            if (number instanceof Double) {
                this.f3388a = (Double) number;
            }
            if (number2 instanceof Double) {
                this.b = (Double) number2;
            }
        }
    }

    public Number getMinimumNumber() {
        if (this.f3388a == null) {
            this.f3388a = new Double(this.min);
        }
        return this.f3388a;
    }

    public long getMinimumLong() {
        return (long) this.min;
    }

    public int getMinimumInteger() {
        return (int) this.min;
    }

    public double getMinimumDouble() {
        return this.min;
    }

    public float getMinimumFloat() {
        return (float) this.min;
    }

    public Number getMaximumNumber() {
        if (this.b == null) {
            this.b = new Double(this.max);
        }
        return this.b;
    }

    public long getMaximumLong() {
        return (long) this.max;
    }

    public int getMaximumInteger() {
        return (int) this.max;
    }

    public double getMaximumDouble() {
        return this.max;
    }

    public float getMaximumFloat() {
        return (float) this.max;
    }

    public boolean containsNumber(Number number) {
        if (number == null) {
            return false;
        }
        return containsDouble(number.doubleValue());
    }

    public boolean containsDouble(double d2) {
        return d2 >= this.min && d2 <= this.max;
    }

    public boolean containsRange(Range range) {
        return range != null && containsDouble(range.getMinimumDouble()) && containsDouble(range.getMaximumDouble());
    }

    public boolean overlapsRange(Range range) {
        if (range == null) {
            return false;
        }
        return range.containsDouble(this.min) || range.containsDouble(this.max) || containsDouble(range.getMinimumDouble());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DoubleRange)) {
            return false;
        }
        DoubleRange doubleRange = (DoubleRange) obj;
        if (Double.doubleToLongBits(this.min) == Double.doubleToLongBits(doubleRange.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(doubleRange.max)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = 17;
            this.c = (this.c * 37) + getClass().hashCode();
            long doubleToLongBits = Double.doubleToLongBits(this.min);
            this.c = (this.c * 37) + ((int) (doubleToLongBits ^ (doubleToLongBits >> 32)));
            long doubleToLongBits2 = Double.doubleToLongBits(this.max);
            this.c = (this.c * 37) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >> 32)));
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
}
