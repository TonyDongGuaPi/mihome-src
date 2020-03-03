package com.drew.metadata.file;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileSystemMetadataReader {
    public void a(@NotNull File file, @NotNull Metadata metadata) throws IOException {
        if (!file.isFile()) {
            throw new IOException("File object must reference a file");
        } else if (!file.exists()) {
            throw new IOException("File does not exist");
        } else if (file.canRead()) {
            FileSystemDirectory fileSystemDirectory = (FileSystemDirectory) metadata.b(FileSystemDirectory.class);
            if (fileSystemDirectory == null) {
                fileSystemDirectory = new FileSystemDirectory();
                metadata.a(fileSystemDirectory);
            }
            fileSystemDirectory.a(1, file.getName());
            fileSystemDirectory.a(2, file.length());
            fileSystemDirectory.a(3, new Date(file.lastModified()));
        } else {
            throw new IOException("File is not readable");
        }
    }
}
