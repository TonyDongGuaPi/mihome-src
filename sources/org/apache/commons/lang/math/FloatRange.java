package org.apache.commons.lang.math;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

public final class FloatRange extends Range implements Serializable {
    private static final long serialVersionUID = 71849363892750L;

    /* renamed from: a  reason: collision with root package name */
    private transient Float f3389a = null;
    private transient Float b = null;
    private transient int c = 0;
    private transient String d = null;
    private final float max;
    private final float min;

    public FloatRange(float f) {
        if (!Float.isNaN(f)) {
            this.min = f;
            this.max = f;
            return;
        }
        throw new IllegalArgumentException("The number must not be NaN");
    }

    public FloatRange(Number number) {
        if (number != null) {
            this.min = number.floatValue();
            this.max = number.floatValue();
            if (Float.isNaN(this.min) || Float.isNaN(this.max)) {
                throw new IllegalArgumentException("The number must not be NaN");
            } else if (number instanceof Float) {
                Float f = (Float) number;
                this.f3389a = f;
                this.b = f;
            }
        } else {
            throw new IllegalArgumentException("The number must not be null");
        }
    }

    public FloatRange(float f, float f2) {
        if (Float.isNaN(f) || Float.isNaN(f2)) {
            throw new IllegalArgumentException("The numbers must not be NaN");
        } else if (f2 < f) {
            this.min = f2;
            this.max = f;
        } else {
            this.min = f;
            this.max = f2;
        }
    }

    public FloatRange(Number number, Number number2) {
        if (number == null || number2 == null) {
            throw new IllegalArgumentException("The numbers must not be null");
        }
        float floatValue = number.floatValue();
        float floatValue2 = number2.floatValue();
        if (Float.isNaN(floatValue) || Float.isNaN(floatValue2)) {
            throw new IllegalArgumentException("The numbers must not be NaN");
        } else if (floatValue2 < floatValue) {
            this.min = floatValue2;
            this.max = floatValue;
            if (number2 instanceof Float) {
                this.f3389a = (Float) number2;
            }
            if (number instanceof Float) {
                this.b = (Float) number;
            }
        } else {
            this.min = floatValue;
            this.max = floatValue2;
            if (number instanceof Float) {
                this.f3389a = (Float) number;
            }
            if (number2 instanceof Float) {
                this.b = (Float) number2;
            }
        }
    }

    public Number getMinimumNumber() {
        if (this.f3389a == null) {
            this.f3389a = new Float(this.min);
        }
        return this.f3389a;
    }

    public long getMinimumLong() {
        return (long) this.min;
    }

    public int getMinimumInteger() {
        return (int) this.min;
    }

    public double getMinimumDouble() {
        return (double) this.min;
    }

    public float getMinimumFloat() {
        return this.min;
    }

    public Number getMaximumNumber() {
        if (this.b == null) {
            this.b = new Float(this.max);
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
        return (double) this.max;
    }

    public float getMaximumFloat() {
        return this.max;
    }

    public boolean containsNumber(Number number) {
        if (number == null) {
            return false;
        }
        return containsFloat(number.floatValue());
    }

    public boolean containsFloat(float f) {
        return f >= this.min && f <= this.max;
    }

    public boolean containsRange(Range range) {
        return range != null && containsFloat(range.getMinimumFloat()) && containsFloat(range.getMaximumFloat());
    }

    public boolean overlapsRange(Range range) {
        if (range == null) {
            return false;
        }
        return range.containsFloat(this.min) || range.containsFloat(this.max) || containsFloat(range.getMinimumFloat());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FloatRange)) {
            return false;
        }
        FloatRange floatRange = (FloatRange) obj;
        if (Float.floatToIntBits(this.min) == Float.floatToIntBits(floatRange.min) && Float.floatToIntBits(this.max) == Float.floatToIntBits(floatRange.max)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = 17;
            this.c = (this.c * 37) + getClass().hashCode();
            this.c = (this.c * 37) + Float.floatToIntBits(this.min);
            this.c = (this.c * 37) + Float.floatToIntBits(this.max);
        }
        return this.c;
    }

    public String toString() {
        if (this.d == null) {
            StringBuffer stringBuffer = new StringBuffer(32);
            stringBuffer.append("Range[");
            stringBuffer.append(this.min);
            stringBuffer.append(',');
            stringBuffer.append(this.max);
            stringBuffer.append(Operators.ARRAY_END);
            this.d = stringBuffer.toString();
        }
        return this.d;
    }
}
