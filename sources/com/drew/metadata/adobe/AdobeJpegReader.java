package com.drew.metadata.adobe;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Collections;

public class AdobeJpegReader implements JpegSegmentMetadataReader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5212a = "Adobe";

    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.APPE);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] next : iterable) {
            if (next.length == 12 && f5212a.equalsIgnoreCase(new String(next, 0, f5212a.length()))) {
                a(new SequentialByteArrayReader(next), metadata);
            }
        }
    }

    public void a(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        AdobeJpegDirectory adobeJpegDirectory = new AdobeJpegDirectory();
        metadata.a(adobeJpegDirectory);
        try {
            sequentialReader.a(false);
            if (!sequentialReader.b(f5212a.length()).equals(f5212a)) {
                adobeJpegDirectory.a("Invalid Adobe JPEG data header.");
                return;
            }
            adobeJpegDirectory.a(0, sequentialReader.g());
            adobeJpegDirectory.a(1, sequentialReader.g());
            adobeJpegDirectory.a(2, sequentialReader.g());
            adobeJpegDirectory.a(3, (int) sequentialReader.f());
        } catch (IOException e) {
            adobeJpegDirectory.a("IO exception processing data: " + e.getMessage());
        }
    }
}
