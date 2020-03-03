package org.apache.commons.lang.enums;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang.ClassUtils;

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
        if (obj == this) {
            return 0;
        }
        if (obj.getClass() == getClass()) {
            return this.iValue - ((ValuedEnum) obj).iValue;
        }
        if (obj.getClass().getName().equals(getClass().getName())) {
            return this.iValue - a(obj);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Different enum class '");
        stringBuffer.append(ClassUtils.a((Class) obj.getClass()));
        stringBuffer.append("'");
        throw new ClassCastException(stringBuffer.toString());
    }

    private int a(Object obj) {
        try {
            return ((Integer) obj.getClass().getMethod("getValue", (Class[]) null).invoke(obj, (Object[]) null)).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            throw new IllegalStateException("This should not happen");
        }
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
