package com.facebook.yoga;

import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;

public class YogaValue {
    static final YogaValue AUTO = new YogaValue(Float.NaN, YogaUnit.AUTO);
    static final YogaValue UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
    static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.POINT);
    public final YogaUnit unit;
    public final float value;

    public YogaValue(float f, YogaUnit yogaUnit) {
        this.value = f;
        this.unit = yogaUnit;
    }

    YogaValue(float f, int i) {
        this(f, YogaUnit.fromInt(i));
    }

    public boolean equals(Object obj) {
        if (obj instanceof YogaValue) {
            YogaValue yogaValue = (YogaValue) obj;
            if (this.unit == yogaValue.unit) {
                if (this.unit == YogaUnit.UNDEFINED || this.unit == YogaUnit.AUTO || Float.compare(this.value, yogaValue.value) == 0) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }

    public String toString() {
        switch (this.unit) {
            case UNDEFINED:
                return Constants.Name.UNDEFINED;
            case POINT:
                return Float.toString(this.value);
            case PERCENT:
                return this.value + Operators.MOD;
            case AUTO:
                return "auto";
            default:
                throw new IllegalStateException();
        }
    }

    public static YogaValue parse(String str) {
        if (str == null) {
            return null;
        }
        if (Constants.Name.UNDEFINED.equals(str)) {
            return UNDEFINED;
        }
        if ("auto".equals(str)) {
            return AUTO;
        }
        if (str.endsWith(Operators.MOD)) {
            return new YogaValue(Float.parseFloat(str.substring(0, str.length() - 1)), YogaUnit.PERCENT);
        }
        return new YogaValue(Float.parseFloat(str), YogaUnit.POINT);
    }
}
