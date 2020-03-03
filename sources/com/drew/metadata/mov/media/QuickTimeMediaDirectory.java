package com.drew.metadata.mov.media;

import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

public abstract class QuickTimeMediaDirectory extends QuickTimeDirectory {
    public static final int w = 20481;
    public static final int x = 20482;
    public static final int y = 20483;

    protected static void a(HashMap<Integer, String> hashMap) {
        hashMap.put(Integer.valueOf(w), "Creation Time");
        hashMap.put(Integer.valueOf(x), "Modification Time");
        hashMap.put(Integer.valueOf(y), "Duration");
    }
}
