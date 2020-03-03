package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

public class QuickTimeSoundDirectory extends QuickTimeDirectory {
    public static final int A = 773;
    @NotNull
    protected static final HashMap<Integer, String> B = new HashMap<>();
    public static final int w = 769;
    public static final int x = 770;
    public static final int y = 771;
    public static final int z = 772;

    @NotNull
    public String a() {
        return "QuickTime Sound";
    }

    public QuickTimeSoundDirectory() {
        a((TagDescriptor) new QuickTimeSoundDescriptor(this));
    }

    static {
        QuickTimeMediaDirectory.a(B);
        B.put(769, "Format");
        B.put(770, "Number of Channels");
        B.put(771, "Sample Size");
        B.put(772, "Sample Rate");
        B.put(773, "Balance");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return B;
    }
}
