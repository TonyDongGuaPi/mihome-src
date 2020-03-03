package org.xutils.common.util;

import com.taobao.weex.el.parse.Operators;

public class KeyValue {

    /* renamed from: a  reason: collision with root package name */
    public final String f4233a;
    public final Object b;

    public KeyValue(String str, Object obj) {
        this.f4233a = str;
        this.b = obj;
    }

    public String a() {
        if (this.b == null) {
            return null;
        }
        return this.b.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        KeyValue keyValue = (KeyValue) obj;
        if (this.f4233a != null) {
            return this.f4233a.equals(keyValue.f4233a);
        }
        if (keyValue.f4233a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.f4233a != null) {
            return this.f4233a.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "KeyValue{key='" + this.f4233a + Operators.SINGLE_QUOTE + ", value=" + this.b + Operators.BLOCK_END;
    }
}
