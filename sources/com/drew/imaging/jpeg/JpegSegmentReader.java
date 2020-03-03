package com.drew.imaging.jpeg;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.io.IOException;
import java.util.HashSet;

public class JpegSegmentReader {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f5181a = (!JpegSegmentReader.class.desiredAssertionStatus());
    private static final byte b = -1;
    private static final byte c = -38;
    private static final byte d = -39;

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0019  */
    @com.drew.lang.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.drew.imaging.jpeg.JpegSegmentData a(@com.drew.lang.annotations.NotNull java.io.File r2, @com.drew.lang.annotations.Nullable java.lang.Iterable<com.drew.imaging.jpeg.JpegSegmentType> r3) throws com.drew.imaging.jpeg.JpegProcessingException, java.io.IOException {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0015 }
            r1.<init>(r2)     // Catch:{ all -> 0x0015 }
            com.drew.lang.StreamReader r2 = new com.drew.lang.StreamReader     // Catch:{ all -> 0x0013 }
            r2.<init>(r1)     // Catch:{ all -> 0x0013 }
            com.drew.imaging.jpeg.JpegSegmentData r2 = a((com.drew.lang.SequentialReader) r2, (java.lang.Iterable<com.drew.imaging.jpeg.JpegSegmentType>) r3)     // Catch:{ all -> 0x0013 }
            r1.close()
            return r2
        L_0x0013:
            r2 = move-exception
            goto L_0x0017
        L_0x0015:
            r2 = move-exception
            r1 = r0
        L_0x0017:
            if (r1 == 0) goto L_0x001c
            r1.close()
        L_0x001c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.imaging.jpeg.JpegSegmentReader.a(java.io.File, java.lang.Iterable):com.drew.imaging.jpeg.JpegSegmentData");
    }

    @NotNull
    public static JpegSegmentData a(@NotNull SequentialReader sequentialReader, @Nullable Iterable<JpegSegmentType> iterable) throws JpegProcessingException, IOException {
        if (f5181a || sequentialReader.d()) {
            int g = sequentialReader.g();
            if (g == 65496) {
                HashSet hashSet = null;
                if (iterable != null) {
                    hashSet = new HashSet();
                    for (JpegSegmentType jpegSegmentType : iterable) {
                        hashSet.add(Byte.valueOf(jpegSegmentType.byteValue));
                    }
                }
                HashSet hashSet2 = hashSet;
                JpegSegmentData jpegSegmentData = new JpegSegmentData();
                while (true) {
                    byte f = sequentialReader.f();
                    byte f2 = sequentialReader.f();
                    while (true) {
                        if (f == -1 && f2 != -1 && f2 != 0) {
                            break;
                        }
                        byte b2 = f2;
                        f2 = sequentialReader.f();
                        f = b2;
                    }
                    if (f2 == -38 || f2 == -39) {
                        return jpegSegmentData;
                    }
                    int g2 = sequentialReader.g() - 2;
                    if (g2 < 0) {
                        throw new JpegProcessingException("JPEG segment size would be less than zero");
                    } else if (hashSet2 == null || hashSet2.contains(Byte.valueOf(f2))) {
                        byte[] a2 = sequentialReader.a(g2);
                        if (f5181a || g2 == a2.length) {
                            jpegSegmentData.a(f2, a2);
                        } else {
                            throw new AssertionError();
                        }
                    } else if (!sequentialReader.b((long) g2)) {
                        return jpegSegmentData;
                    }
                }
            } else {
                throw new JpegProcessingException("JPEG data is expected to begin with 0xFFD8 (ÿØ) not 0x" + Integer.toHexString(g));
            }
        } else {
            throw new AssertionError();
        }
    }

    private JpegSegmentReader() throws Exception {
        throw new Exception("Not intended for instantiation.");
    }
}
