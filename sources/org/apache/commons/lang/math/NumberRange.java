package org.apache.commons.lang.math;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import org.apache.commons.lang.text.StrBuilder;

public final class NumberRange extends Range implements Serializable {
    private static final long serialVersionUID = 71849363892710L;

    /* renamed from: a  reason: collision with root package name */
    private transient int f3394a = 0;
    private transient String b = null;
    private final Number max;
    private final Number min;

    public NumberRange(Number number) {
        if (number == null) {
            throw new IllegalArgumentException("The number must not be null");
        } else if (!(number instanceof Comparable)) {
            throw new IllegalArgumentException("The number must implement Comparable");
        } else if ((number instanceof Double) && ((Double) number).isNaN()) {
            throw new IllegalArgumentException("The number must not be NaN");
        } else if (!(number instanceof Float) || !((Float) number).isNaN()) {
            this.min = number;
            this.max = number;
        } else {
            throw new IllegalArgumentException("The number must not be NaN");
        }
    }

    public NumberRange(Number number, Number number2) {
        if (number == null || number2 == null) {
            throw new IllegalArgumentException("The numbers must not be null");
        } else if (number.getClass() != number2.getClass()) {
            throw new IllegalArgumentException("The numbers must be of the same type");
        } else if (number instanceof Comparable) {
            if (number instanceof Double) {
                if (((Double) number).isNaN() || ((Double) number2).isNaN()) {
                    throw new IllegalArgumentException("The number must not be NaN");
                }
            } else if ((number instanceof Float) && (((Float) number).isNaN() || ((Float) number2).isNaN())) {
                throw new IllegalArgumentException("The number must not be NaN");
            }
            int compareTo = ((Comparable) number).compareTo(number2);
            if (compareTo == 0) {
                this.min = number;
                this.max = number;
            } else if (compareTo > 0) {
                this.min = number2;
                this.max = number;
            } else {
                this.min = number;
                this.max = number2;
            }
        } else {
            throw new IllegalArgumentException("The numbers must implement Comparable");
        }
    }

    public Number getMinimumNumber() {
        return this.min;
    }

    public Number getMaximumNumber() {
        return this.max;
    }

    public boolean containsNumber(Number number) {
        if (number == null) {
            return false;
        }
        if (number.getClass() == this.min.getClass()) {
            int compareTo = ((Comparable) this.min).compareTo(number);
            int compareTo2 = ((Comparable) this.max).compareTo(number);
            if (compareTo > 0 || compareTo2 < 0) {
                return false;
            }
            return true;
        }
        throw new IllegalArgumentException("The number must be of the same type as the range numbers");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberRange)) {
            return false;
        }
        NumberRange numberRange = (NumberRange) obj;
        if (!this.min.equals(numberRange.min) || !this.max.equals(numberRange.max)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.f3394a == 0) {
            this.f3394a = 17;
            this.f3394a = (this.f3394a * 37) + getClass().hashCode();
            this.f3394a = (this.f3394a * 37) + this.min.hashCode();
            this.f3394a = (this.f3394a * 37) + this.max.hashCode();
        }
        return this.f3394a;
    }

    public String toString() {
        if (this.b == null) {
            StrBuilder strBuilder = new StrBuilder(32);
            strBuilder.c("Range[");
            strBuilder.a((Object) this.min);
            strBuilder.a(',');
            strBuilder.a((Object) this.max);
            strBuilder.a((char) Operators.ARRAY_END);
            this.b = strBuilder.toString();
        }
        return this.b;
    }
}
