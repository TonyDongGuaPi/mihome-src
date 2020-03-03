package org.apache.commons.lang;

import com.taobao.weex.el.parse.Operators;
import org.apache.commons.lang.text.StrBuilder;

public final class NumberRange {

    /* renamed from: a  reason: collision with root package name */
    private final Number f3369a;
    private final Number b;

    public NumberRange(Number number) {
        if (number != null) {
            this.f3369a = number;
            this.b = number;
            return;
        }
        throw new NullPointerException("The number must not be null");
    }

    public NumberRange(Number number, Number number2) {
        if (number == null) {
            throw new NullPointerException("The minimum value must not be null");
        } else if (number2 == null) {
            throw new NullPointerException("The maximum value must not be null");
        } else if (number2.doubleValue() < number.doubleValue()) {
            this.b = number;
            this.f3369a = number;
        } else {
            this.f3369a = number;
            this.b = number2;
        }
    }

    public Number a() {
        return this.f3369a;
    }

    public Number b() {
        return this.b;
    }

    public boolean a(Number number) {
        return number != null && this.f3369a.doubleValue() <= number.doubleValue() && this.b.doubleValue() >= number.doubleValue();
    }

    public boolean a(NumberRange numberRange) {
        return numberRange != null && a(numberRange.f3369a) && a(numberRange.b);
    }

    public boolean b(NumberRange numberRange) {
        if (numberRange == null) {
            return false;
        }
        return numberRange.a(this.f3369a) || numberRange.a(this.b) || a(numberRange);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberRange)) {
            return false;
        }
        NumberRange numberRange = (NumberRange) obj;
        if (!this.f3369a.equals(numberRange.f3369a) || !this.b.equals(numberRange.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((629 + this.f3369a.hashCode()) * 37) + this.b.hashCode();
    }

    public String toString() {
        StrBuilder strBuilder = new StrBuilder();
        if (this.f3369a.doubleValue() < 0.0d) {
            strBuilder.a((char) Operators.BRACKET_START).a((Object) this.f3369a).a((char) Operators.BRACKET_END);
        } else {
            strBuilder.a((Object) this.f3369a);
        }
        strBuilder.a('-');
        if (this.b.doubleValue() < 0.0d) {
            strBuilder.a((char) Operators.BRACKET_START).a((Object) this.b).a((char) Operators.BRACKET_END);
        } else {
            strBuilder.a((Object) this.b);
        }
        return strBuilder.toString();
    }
}
