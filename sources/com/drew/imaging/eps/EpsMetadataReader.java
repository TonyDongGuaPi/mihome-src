package com.drew.imaging.eps;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.eps.EpsReader;
import com.drew.metadata.file.FileSystemMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EpsMetadataReader {
    @NotNull
    public static Metadata a(@NotNull File file) throws IOException {
        Metadata metadata = new Metadata();
        new EpsReader().a((InputStream) new FileInputStream(file), metadata);
        new FileSystemMetadataReader().a(file, metadata);
        return metadata;
    }

    @NotNull
    public static Metadata a(@NotNull InputStream inputStream) throws IOException {
        Metadata metadata = new Metadata();
        new EpsReader().a(inputStream, metadata);
        return metadata;
    }
}
