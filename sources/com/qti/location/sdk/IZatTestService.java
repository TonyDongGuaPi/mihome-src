package com.qti.location.sdk;

public interface IZatTestService {
    public static final long A = 33554432;
    public static final long B = 67108864;
    public static final long C = 134217728;
    public static final long D = -1;
    public static final long c = 2;
    public static final long d = 4;
    public static final long e = 8;
    public static final long f = 16;
    public static final long g = 32;
    public static final long h = 64;
    public static final long i = 128;
    public static final long j = 256;
    public static final long k = 512;
    public static final long l = 1024;
    public static final long m = 2048;
    public static final long n = 4096;
    public static final long o = 8192;
    public static final long p = 16384;
    public static final long q = 32768;
    public static final long r = 65536;
    public static final long s = 131072;
    public static final long t = 262144;
    public static final long u = 524288;
    public static final long v = 1048576;
    public static final long w = 2097152;
    public static final long x = 4194304;
    public static final long y = 8388608;
    public static final long z = 16777216;

    public interface IFlpMaxPowerAllocatedCallback {
        void a(double d);
    }

    void a(long j2) throws IZatIllegalArgumentException;

    void a(IFlpMaxPowerAllocatedCallback iFlpMaxPowerAllocatedCallback) throws IZatIllegalArgumentException;

    void a(boolean z2);

    void b(IFlpMaxPowerAllocatedCallback iFlpMaxPowerAllocatedCallback) throws IZatIllegalArgumentException;
}
