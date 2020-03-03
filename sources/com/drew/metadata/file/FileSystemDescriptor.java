package com.drew.metadata.file;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class FileSystemDescriptor extends TagDescriptor<FileSystemDirectory> {
    public FileSystemDescriptor(@NotNull FileSystemDirectory fileSystemDirectory) {
        super(fileSystemDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i != 2) {
            return super.a(i);
        }
        return a();
    }

    @Nullable
    private String a() {
        Long m = ((FileSystemDirectory) this.f5211a).m(2);
        if (m == null) {
            return null;
        }
        return Long.toString(m.longValue()) + " bytes";
    }
}
