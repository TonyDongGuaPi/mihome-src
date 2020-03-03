package com.jstun.core.header;

public interface MessageHeaderInterface {
    public static final int d = 1;
    public static final int e = 257;
    public static final int f = 273;
    public static final int g = 2;
    public static final int h = 258;
    public static final int i = 274;

    public enum MessageHeaderType {
        BindingRequest,
        BindingResponse,
        BindingErrorResponse,
        SharedSecretRequest,
        SharedSecretResponse,
        SharedSecretErrorResponse
    }
}
