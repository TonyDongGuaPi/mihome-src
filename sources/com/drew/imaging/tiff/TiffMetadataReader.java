package com.drew.imaging.tiff;

import com.drew.lang.RandomAccessFileReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifTiffHandler;
import com.drew.metadata.file.FileSystemMetadataReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class TiffMetadataReader {
    /* JADX INFO: finally extract failed */
    @NotNull
    public static Metadata a(@NotNull File file) throws IOException, TiffProcessingException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            Metadata a2 = a((RandomAccessReader) new RandomAccessFileReader(randomAccessFile));
            randomAccessFile.close();
            new FileSystemMetadataReader().a(file, a2);
            return a2;
        } catch (Throwable th) {
            randomAccessFile.close();
            throw th;
        }
    }

    @NotNull
    public static Metadata a(@NotNull InputStream inputStream) throws IOException, TiffProcessingException {
        return a((RandomAccessReader) new RandomAccessStreamReader(inputStream));
    }

    @NotNull
    public static Metadata a(@NotNull RandomAccessReader randomAccessReader) throws IOException, TiffProcessingException {
        Metadata metadata = new Metadata();
        new TiffReader().a(randomAccessReader, new ExifTiffHandler(metadata, (Directory) null), 0);
        return metadata;
    }
}
