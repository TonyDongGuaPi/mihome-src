package com.drew.metadata.photoshop;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.Charsets;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Collections;

public class DuckyReader implements JpegSegmentMetadataReader {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private static final String f5261a = "Ducky";

    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.APPC);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        int length = f5261a.length();
        for (byte[] next : iterable) {
            if (next.length >= length && f5261a.equals(new String(next, 0, length))) {
                a(new SequentialByteArrayReader(next, length), metadata);
            }
        }
    }

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        DuckyDirectory duckyDirectory = new DuckyDirectory();
        metadata.a(duckyDirectory);
        while (true) {
            try {
                int g = sequentialReader.g();
                if (g != 0) {
                    int g2 = sequentialReader.g();
                    switch (g) {
                        case 1:
                            if (g2 == 4) {
                                duckyDirectory.a(g, sequentialReader.j());
                                break;
                            } else {
                                duckyDirectory.a("Unexpected length for the quality tag");
                                return;
                            }
                        case 2:
                        case 3:
                            sequentialReader.a(4);
                            duckyDirectory.a(g, sequentialReader.b(g2 - 4, Charsets.e));
                            break;
                        default:
                            duckyDirectory.a(g, sequentialReader.a(g2));
                            break;
                    }
                } else {
                    return;
                }
            } catch (IOException e) {
                duckyDirectory.a(e.getMessage());
                return;
            }
        }
    }
}
