package com.drew.metadata.file;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class FileSystemDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    @NotNull
    protected static final HashMap<Integer, String> h = new HashMap<>();

    @NotNull
    public String a() {
        return "File";
    }

    static {
        h.put(1, "File Name");
        h.put(2, "File Size");
        h.put(3, "File Modified Date");
    }

    public FileSystemDirectory() {
        a((TagDescriptor) new FileSystemDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return h;
    }
}
