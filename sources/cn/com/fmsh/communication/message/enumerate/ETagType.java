package cn.com.fmsh.communication.message.enumerate;

import miuipub.reflect.Field;

public enum ETagType {
    I(Field.e),
    S("S"),
    B(Field.b),
    C(Field.c),
    N("N"),
    U("U"),
    G("G"),
    H("H");
    
    private String value;

    private ETagType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
