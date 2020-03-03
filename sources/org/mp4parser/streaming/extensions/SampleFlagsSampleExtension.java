package org.mp4parser.streaming.extensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.mp4parser.streaming.SampleExtension;

public class SampleFlagsSampleExtension implements SampleExtension {

    /* renamed from: a  reason: collision with root package name */
    public static Map<Long, SampleFlagsSampleExtension> f4066a = Collections.synchronizedMap(new HashMap());
    private byte b;
    private byte c;
    private byte d;
    private byte e;
    private byte f;
    private boolean g;
    private int h;

    public static SampleFlagsSampleExtension a(byte b2, byte b3, byte b4, byte b5, byte b6, boolean z, int i) {
        long j = ((long) ((b3 << 2) + b2 + (b4 << 4) + (b5 << 6))) + ((long) (b6 << 8)) + ((long) (i << 11)) + ((long) ((z ? 1 : 0) << true));
        SampleFlagsSampleExtension sampleFlagsSampleExtension = f4066a.get(Long.valueOf(j));
        if (sampleFlagsSampleExtension != null) {
            return sampleFlagsSampleExtension;
        }
        SampleFlagsSampleExtension sampleFlagsSampleExtension2 = new SampleFlagsSampleExtension();
        sampleFlagsSampleExtension2.b = b2;
        sampleFlagsSampleExtension2.c = b3;
        sampleFlagsSampleExtension2.d = b4;
        sampleFlagsSampleExtension2.e = b5;
        sampleFlagsSampleExtension2.f = b6;
        sampleFlagsSampleExtension2.g = z;
        sampleFlagsSampleExtension2.h = i;
        f4066a.put(Long.valueOf(j), sampleFlagsSampleExtension2);
        return sampleFlagsSampleExtension2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("isLeading=");
        sb.append(this.b);
        sb.append(", dependsOn=");
        sb.append(this.c);
        sb.append(", isDependedOn=");
        sb.append(this.d);
        sb.append(", hasRedundancy=");
        sb.append(this.e);
        sb.append(", paddingValue=");
        sb.append(this.f);
        sb.append(", isSyncSample=");
        sb.append(!this.g);
        sb.append(", sampleDegradationPriority=");
        sb.append(this.h);
        return sb.toString();
    }

    public byte a() {
        return this.b;
    }

    public void a(byte b2) {
        this.b = b2;
    }

    public byte b() {
        return this.c;
    }

    public void a(int i) {
        this.c = (byte) i;
    }

    public byte c() {
        return this.d;
    }

    public void b(int i) {
        this.d = (byte) i;
    }

    public byte d() {
        return this.e;
    }

    public void b(byte b2) {
        this.e = b2;
    }

    public byte e() {
        return this.f;
    }

    public void c(byte b2) {
        this.f = b2;
    }

    public boolean f() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean g() {
        return !this.g;
    }

    public int h() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }
}
