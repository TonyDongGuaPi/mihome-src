package com.ximalaya.ting.android.sdkdownloader.http;

import com.taobao.weex.el.parse.Operators;

public class KeyValue {
    public final String b;
    public final Object c;

    public KeyValue(String str, Object obj) {
        this.b = str;
        this.c = obj;
    }

    public String a() {
        if (this.c == null) {
            return null;
        }
        return this.c.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        KeyValue keyValue = (KeyValue) obj;
        if (this.b != null) {
            return this.b.equals(keyValue.b);
        }
        if (keyValue.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.b != null) {
            return this.b.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "KeyValue{key='" + this.b + Operators.SINGLE_QUOTE + ", value=" + this.c + Operators.BLOCK_END;
    }
}
