package com.drew.metadata.mp4.media;

import com.drew.metadata.mp4.Mp4Directory;
import java.util.HashMap;

public abstract class Mp4MediaDirectory extends Mp4Directory {
    public static final int C = 101;
    public static final int D = 102;
    public static final int E = 103;
    public static final int F = 104;

    protected static void a(HashMap<Integer, String> hashMap) {
        hashMap.put(101, "Creation Time");
        hashMap.put(102, "Modification Time");
        hashMap.put(103, "Duration");
        hashMap.put(104, "ISO 639-2 Language Code");
    }
}
