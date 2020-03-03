package com.google.android.gms.internal.measurement;

public enum zzabu {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzyw.zzbqx),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzbwn;

    private zzabu(Object obj) {
        this.zzbwn = obj;
    }
}
