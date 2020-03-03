package com.nostra13.universalimageloader.core.assist;

public class FailReason {

    /* renamed from: a  reason: collision with root package name */
    private final FailType f8474a;
    private final Throwable b;

    public enum FailType {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN
    }

    public FailReason(FailType failType, Throwable th) {
        this.f8474a = failType;
        this.b = th;
    }

    public FailType a() {
        return this.f8474a;
    }

    public Throwable b() {
        return this.b;
    }
}
