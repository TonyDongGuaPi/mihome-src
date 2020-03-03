package com.drew.imaging.jpeg;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JpegSegmentData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<Byte, List<byte[]>> f5180a = new HashMap<>(10);

    public void a(byte b, @NotNull byte[] bArr) {
        g(b).add(bArr);
    }

    public Iterable<JpegSegmentType> a() {
        HashSet hashSet = new HashSet();
        for (Byte next : this.f5180a.keySet()) {
            JpegSegmentType fromByte = JpegSegmentType.fromByte(next.byteValue());
            if (fromByte != null) {
                hashSet.add(fromByte);
            } else {
                throw new IllegalStateException("Should not have a segmentTypeByte that is not in the enum: " + Integer.toHexString(next.byteValue()));
            }
        }
        return hashSet;
    }

    @Nullable
    public byte[] a(byte b) {
        return a(b, 0);
    }

    @Nullable
    public byte[] a(@NotNull JpegSegmentType jpegSegmentType) {
        return a(jpegSegmentType.byteValue, 0);
    }

    @Nullable
    public byte[] a(@NotNull JpegSegmentType jpegSegmentType, int i) {
        return a(jpegSegmentType.byteValue, i);
    }

    @Nullable
    public byte[] a(byte b, int i) {
        List<byte[]> f = f(b);
        if (f == null || f.size() <= i) {
            return null;
        }
        return f.get(i);
    }

    @NotNull
    public Iterable<byte[]> b(@NotNull JpegSegmentType jpegSegmentType) {
        return b(jpegSegmentType.byteValue);
    }

    @NotNull
    public Iterable<byte[]> b(byte b) {
        List<byte[]> f = f(b);
        return f == null ? new ArrayList() : f;
    }

    @Nullable
    private List<byte[]> f(byte b) {
        return this.f5180a.get(Byte.valueOf(b));
    }

    @NotNull
    private List<byte[]> g(byte b) {
        if (this.f5180a.containsKey(Byte.valueOf(b))) {
            return this.f5180a.get(Byte.valueOf(b));
        }
        ArrayList arrayList = new ArrayList();
        this.f5180a.put(Byte.valueOf(b), arrayList);
        return arrayList;
    }

    public int c(@NotNull JpegSegmentType jpegSegmentType) {
        return c(jpegSegmentType.byteValue);
    }

    public int c(byte b) {
        List<byte[]> f = f(b);
        if (f == null) {
            return 0;
        }
        return f.size();
    }

    public void b(@NotNull JpegSegmentType jpegSegmentType, int i) {
        b(jpegSegmentType.byteValue, i);
    }

    public void b(byte b, int i) {
        this.f5180a.get(Byte.valueOf(b)).remove(i);
    }

    public void d(@NotNull JpegSegmentType jpegSegmentType) {
        d(jpegSegmentType.byteValue);
    }

    public void d(byte b) {
        this.f5180a.remove(Byte.valueOf(b));
    }

    public boolean e(@NotNull JpegSegmentType jpegSegmentType) {
        return e(jpegSegmentType.byteValue);
    }

    public boolean e(byte b) {
        return this.f5180a.containsKey(Byte.valueOf(b));
    }
}
