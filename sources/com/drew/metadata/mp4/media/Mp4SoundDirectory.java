package com.drew.metadata.mp4.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class Mp4SoundDirectory extends Mp4MediaDirectory {
    public static final int A = 104;
    public static final int B = 105;
    @NotNull
    protected static final HashMap<Integer, String> G = new HashMap<>();
    public static final int x = 101;
    public static final int y = 102;
    public static final int z = 103;

    @NotNull
    public String a() {
        return "MP4 Sound";
    }

    public Mp4SoundDirectory() {
        a((TagDescriptor) new Mp4SoundDescriptor(this));
    }

    static {
        Mp4MediaDirectory.a(G);
        G.put(101, "Format");
        G.put(102, "Number of Channels");
        G.put(103, "Sample Size");
        G.put(104, "Sample Rate");
        G.put(105, "Balance");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return G;
    }
}
