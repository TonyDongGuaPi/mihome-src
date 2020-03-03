package com.drew.imaging.mp4;

import com.drew.imaging.ImageProcessingException;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.mp4.Mp4BoxHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Mp4MetadataReader {
    /* JADX INFO: finally extract failed */
    @NotNull
    public static Metadata a(@NotNull File file) throws ImageProcessingException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata a2 = a((InputStream) fileInputStream);
            fileInputStream.close();
            new FileSystemMetadataReader().a(file, a2);
            return a2;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    @NotNull
    public static Metadata a(@NotNull InputStream inputStream) throws IOException {
        Metadata metadata = new Metadata();
        Mp4Reader.a(inputStream, new Mp4BoxHandler(metadata));
        return metadata;
    }
}
