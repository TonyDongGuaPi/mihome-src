package com.drew.metadata.jpeg;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Arrays;

public class JpegReader implements JpegSegmentMetadataReader {
    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Arrays.asList(new JpegSegmentType[]{JpegSegmentType.SOF0, JpegSegmentType.SOF1, JpegSegmentType.SOF2, JpegSegmentType.SOF3, JpegSegmentType.SOF5, JpegSegmentType.SOF6, JpegSegmentType.SOF7, JpegSegmentType.SOF9, JpegSegmentType.SOF10, JpegSegmentType.SOF11, JpegSegmentType.SOF13, JpegSegmentType.SOF14, JpegSegmentType.SOF15});
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] a2 : iterable) {
            a(a2, metadata, jpegSegmentType);
        }
    }

    public void a(byte[] bArr, Metadata metadata, JpegSegmentType jpegSegmentType) {
        JpegDirectory jpegDirectory = new JpegDirectory();
        metadata.a(jpegDirectory);
        jpegDirectory.a(-3, jpegSegmentType.byteValue - JpegSegmentType.SOF0.byteValue);
        SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
        try {
            jpegDirectory.a(0, (int) sequentialByteArrayReader.e());
            jpegDirectory.a(1, sequentialByteArrayReader.g());
            jpegDirectory.a(3, sequentialByteArrayReader.g());
            short e = sequentialByteArrayReader.e();
            jpegDirectory.a(5, (int) e);
            for (int i = 0; i < e; i++) {
                jpegDirectory.a(i + 6, (Object) new JpegComponent(sequentialByteArrayReader.e(), sequentialByteArrayReader.e(), sequentialByteArrayReader.e()));
            }
        } catch (IOException e2) {
            jpegDirectory.a(e2.getMessage());
        }
    }
}
