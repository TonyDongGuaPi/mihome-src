package com.drew.imaging.jpeg;

import com.drew.lang.SequentialReader;
import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.adobe.AdobeJpegReader;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.jfif.JfifReader;
import com.drew.metadata.jfxx.JfxxReader;
import com.drew.metadata.jpeg.JpegCommentReader;
import com.drew.metadata.jpeg.JpegDhtReader;
import com.drew.metadata.jpeg.JpegDnlReader;
import com.drew.metadata.jpeg.JpegReader;
import com.drew.metadata.photoshop.DuckyReader;
import com.drew.metadata.photoshop.PhotoshopReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

public class JpegMetadataReader {

    /* renamed from: a  reason: collision with root package name */
    public static final Iterable<JpegSegmentMetadataReader> f5179a = Arrays.asList(new JpegSegmentMetadataReader[]{new JpegReader(), new JpegCommentReader(), new JfifReader(), new JfxxReader(), new ExifReader(), new XmpReader(), new IccReader(), new PhotoshopReader(), new DuckyReader(), new IptcReader(), new AdobeJpegReader(), new JpegDhtReader(), new JpegDnlReader()});

    @NotNull
    public static Metadata a(@NotNull InputStream inputStream, @Nullable Iterable<JpegSegmentMetadataReader> iterable) throws JpegProcessingException, IOException {
        Metadata metadata = new Metadata();
        a(metadata, inputStream, iterable);
        return metadata;
    }

    @NotNull
    public static Metadata a(@NotNull InputStream inputStream) throws JpegProcessingException, IOException {
        return a(inputStream, (Iterable<JpegSegmentMetadataReader>) null);
    }

    /* JADX INFO: finally extract failed */
    @NotNull
    public static Metadata a(@NotNull File file, @Nullable Iterable<JpegSegmentMetadataReader> iterable) throws JpegProcessingException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata a2 = a((InputStream) fileInputStream, iterable);
            fileInputStream.close();
            new FileSystemMetadataReader().a(file, a2);
            return a2;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    @NotNull
    public static Metadata a(@NotNull File file) throws JpegProcessingException, IOException {
        return a(file, (Iterable<JpegSegmentMetadataReader>) null);
    }

    public static void a(@NotNull Metadata metadata, @NotNull InputStream inputStream) throws JpegProcessingException, IOException {
        a(metadata, inputStream, (Iterable<JpegSegmentMetadataReader>) null);
    }

    public static void a(@NotNull Metadata metadata, @NotNull InputStream inputStream, @Nullable Iterable<JpegSegmentMetadataReader> iterable) throws JpegProcessingException, IOException {
        if (iterable == null) {
            iterable = f5179a;
        }
        HashSet hashSet = new HashSet();
        for (JpegSegmentMetadataReader a2 : iterable) {
            for (JpegSegmentType add : a2.a()) {
                hashSet.add(add);
            }
        }
        a(metadata, iterable, JpegSegmentReader.a((SequentialReader) new StreamReader(inputStream), (Iterable<JpegSegmentType>) hashSet));
    }

    public static void a(Metadata metadata, Iterable<JpegSegmentMetadataReader> iterable, JpegSegmentData jpegSegmentData) {
        for (JpegSegmentMetadataReader next : iterable) {
            for (JpegSegmentType next2 : next.a()) {
                next.a(jpegSegmentData.b(next2), metadata, next2);
            }
        }
    }

    private JpegMetadataReader() throws Exception {
        throw new Exception("Not intended for instantiation");
    }
}
