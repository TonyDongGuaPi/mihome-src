package org.mp4parser.streaming.extensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.mp4parser.streaming.TrackExtension;

public class DefaultSampleFlagsTrackExtension implements TrackExtension {

    /* renamed from: a  reason: collision with root package name */
    public static Map<Long, SampleFlagsSampleExtension> f4063a = Collections.synchronizedMap(new HashMap());
    private byte b;
    private byte c;
    private byte d;
    private byte e;
    private byte f;
    private boolean g;
    private int h;

    public static DefaultSampleFlagsTrackExtension a(byte b2, byte b3, byte b4, byte b5, byte b6, boolean z, int i) {
        DefaultSampleFlagsTrackExtension defaultSampleFlagsTrackExtension = new DefaultSampleFlagsTrackExtension();
        defaultSampleFlagsTrackExtension.b = b2;
        defaultSampleFlagsTrackExtension.c = b3;
        defaultSampleFlagsTrackExtension.d = b4;
        defaultSampleFlagsTrackExtension.e = b5;
        defaultSampleFlagsTrackExtension.f = b6;
        defaultSampleFlagsTrackExtension.g = z;
        defaultSampleFlagsTrackExtension.h = i;
        return defaultSampleFlagsTrackExtension;
    }

    public byte a() {
        return this.b;
    }

    public void a(int i) {
        this.b = (byte) i;
    }

    public byte b() {
        return this.c;
    }

    public void b(int i) {
        this.c = (byte) i;
    }

    public byte c() {
        return this.d;
    }

    public void c(int i) {
        this.d = (byte) i;
    }

    public byte d() {
        return this.e;
    }

    public void d(int i) {
        this.e = (byte) i;
    }

    public byte e() {
        return this.f;
    }

    public void a(byte b2) {
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

    public void e(int i) {
        this.h = i;
    }
}
