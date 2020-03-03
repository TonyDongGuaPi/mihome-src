package com.drew.metadata.file;

import com.drew.imaging.FileType;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class FileTypeDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    @NotNull
    protected static final HashMap<Integer, String> i = new HashMap<>();

    @NotNull
    public String a() {
        return "File Type";
    }

    static {
        i.put(1, "Detected File Type Name");
        i.put(2, "Detected File Type Long Name");
        i.put(3, "Detected MIME Type");
        i.put(4, "Expected File Name Extension");
    }

    public FileTypeDirectory(FileType fileType) {
        a((TagDescriptor) new FileTypeDescriptor(this));
        a(1, fileType.getName());
        a(2, fileType.getLongName());
        if (fileType.getMimeType() != null) {
            a(3, fileType.getMimeType());
        }
        if (fileType.getCommonExtension() != null) {
            a(4, fileType.getCommonExtension());
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return i;
    }
}
