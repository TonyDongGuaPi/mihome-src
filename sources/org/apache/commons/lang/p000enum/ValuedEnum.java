package org.apache.commons.lang.p000enum;

import com.taobao.weex.el.parse.Operators;
import org.apache.commons.lang.ClassUtils;

/* renamed from: org.apache.commons.lang.enum.ValuedEnum  reason: invalid package */
public abstract class ValuedEnum extends Enum {
    private static final long serialVersionUID = -7129650521543789085L;
    private final int iValue;

    protected ValuedEnum(String str, int i) {
        super(str);
        this.iValue = i;
    }

    protected static Enum getEnum(Class cls, int i) {
        if (cls != null) {
            for (ValuedEnum valuedEnum : Enum.getEnumList(cls)) {
                if (valuedEnum.getValue() == i) {
                    return valuedEnum;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("The Enum Class must not be null");
    }

    public final int getValue() {
        return this.iValue;
    }

    public int compareTo(Object obj) {
        return this.iValue - ((ValuedEnum) obj).iValue;
    }

    public String toString() {
        if (this.iToString == null) {
            String a2 = ClassUtils.a(getEnumClass());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a2);
            stringBuffer.append(Operators.ARRAY_START_STR);
            stringBuffer.append(getName());
            stringBuffer.append("=");
            stringBuffer.append(getValue());
            stringBuffer.append(Operators.ARRAY_END_STR);
            this.iToString = stringBuffer.toString();
        }
        return this.iToString;
    }
}
