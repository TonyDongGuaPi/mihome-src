package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class OlympusEquipmentMakernoteDirectory extends Directory {
    public static final int A = 4097;
    public static final int B = 4098;
    public static final int C = 4099;
    @NotNull
    protected static final HashMap<Integer, String> D = new HashMap<>();
    public static final int e = 0;
    public static final int f = 256;
    public static final int g = 257;
    public static final int h = 258;
    public static final int i = 259;
    public static final int j = 260;
    public static final int k = 513;
    public static final int l = 514;
    public static final int m = 515;
    public static final int n = 516;
    public static final int o = 517;
    public static final int p = 518;
    public static final int q = 519;
    public static final int r = 520;
    public static final int s = 522;
    public static final int t = 523;
    public static final int u = 769;
    public static final int v = 770;
    public static final int w = 771;
    public static final int x = 772;
    public static final int y = 1027;
    public static final int z = 4096;

    @NotNull
    public String a() {
        return "Olympus Equipment";
    }

    static {
        D.put(0, "Equipment Version");
        D.put(256, "Camera Type 2");
        D.put(257, "Serial Number");
        D.put(258, "Internal Serial Number");
        D.put(259, "Focal Plane Diagonal");
        D.put(260, "Body Firmware Version");
        D.put(513, "Lens Type");
        D.put(514, "Lens Serial Number");
        D.put(515, "Lens Model");
        D.put(516, "Lens Firmware Version");
        D.put(517, "Max Aperture At Min Focal");
        D.put(518, "Max Aperture At Max Focal");
        D.put(519, "Min Focal Length");
        D.put(520, "Max Focal Length");
        D.put(522, "Max Aperture");
        D.put(523, "Lens Properties");
        D.put(769, "Extender");
        D.put(770, "Extender Serial Number");
        D.put(771, "Extender Model");
        D.put(772, "Extender Firmware Version");
        D.put(1027, "Conversion Lens");
        D.put(4096, "Flash Type");
        D.put(4097, "Flash Model");
        D.put(4098, "Flash Firmware Version");
        D.put(4099, "Flash Serial Number");
    }

    public OlympusEquipmentMakernoteDirectory() {
        a((TagDescriptor) new OlympusEquipmentMakernoteDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return D;
    }
}
