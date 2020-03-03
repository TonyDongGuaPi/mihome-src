package com.drew.metadata.jfxx;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import java.io.IOException;
import java.util.Collections;

public class JfxxReader implements JpegSegmentMetadataReader, MetadataReader {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5232a = "JFXX";

    @NotNull
    public Iterable<JpegSegmentType> a() {
        return Collections.singletonList(JpegSegmentType.APP0);
    }

    public void a(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] next : iterable) {
            if (next.length >= f5232a.length() && f5232a.equals(new String(next, 0, f5232a.length()))) {
                a(new ByteArrayReader(next), metadata);
            }
        }
    }

    public void a(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata) {
        JfxxDirectory jfxxDirectory = new JfxxDirectory();
        metadata.a(jfxxDirectory);
        try {
            jfxxDirectory.a(5, (int) randomAccessReader.d(5));
        } catch (IOException e) {
            jfxxDirectory.a(e.getMessage());
        }
    }
}
