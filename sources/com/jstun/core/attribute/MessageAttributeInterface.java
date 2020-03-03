package com.jstun.core.attribute;

public interface MessageAttributeInterface {
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 5;
    public static final int i = 6;
    public static final int j = 7;
    public static final int k = 8;
    public static final int l = 9;
    public static final int m = 10;
    public static final int n = 11;
    public static final int o = 0;

    public enum MessageAttributeType {
        MappedAddress,
        ResponseAddress,
        ChangeRequest,
        SourceAddress,
        ChangedAddress,
        Username,
        Password,
        MessageIntegrity,
        ErrorCode,
        UnknownAttribute,
        ReflectedFrom,
        Dummy
    }
}
