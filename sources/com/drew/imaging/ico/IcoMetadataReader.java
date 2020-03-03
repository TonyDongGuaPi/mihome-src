package com.drew.imaging.ico;

import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.ico.IcoReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IcoMetadataReader {
    /* JADX INFO: finally extract failed */
    @NotNull
    public static Metadata a(@NotNull File file) throws IOException {
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
    public static Metadata a(@NotNull InputStream inputStream) {
        Metadata metadata = new Metadata();
        new IcoReader().a(new StreamReader(inputStream), metadata);
        return metadata;
    }
}
