package com.drew.metadata.mp4.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class Mp4HintDirectory extends Mp4MediaDirectory {
    public static final int A = 104;
    @NotNull
    protected static final HashMap<Integer, String> B = new HashMap<>();
    public static final int x = 101;
    public static final int y = 102;
    public static final int z = 103;

    @NotNull
    public String a() {
        return "MP4 Hint";
    }

    public Mp4HintDirectory() {
        a((TagDescriptor) new Mp4HintDescriptor(this));
    }

    static {
        Mp4MediaDirectory.a(B);
        B.put(101, "Max PDU Size");
        B.put(102, "Average PDU Size");
        B.put(103, "Max Bitrate");
        B.put(104, "Average Bitrate");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return B;
    }
}
