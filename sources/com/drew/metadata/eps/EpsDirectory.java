package com.drew.metadata.eps;

import android.support.media.ExifInterface;
import com.alipay.sdk.packet.e;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class EpsDirectory extends Directory {
    public static final int A = 23;
    public static final int B = 24;
    public static final int C = 25;
    public static final int D = 26;
    public static final int E = 27;
    public static final int F = 28;
    public static final int G = 29;
    public static final int H = 30;
    public static final int I = 31;
    public static final int J = 32;
    public static final int K = 33;
    public static final int L = 34;
    public static final int M = 35;
    public static final int N = 36;
    @NotNull
    protected static final HashMap<Integer, String> O = new HashMap<>();
    @NotNull
    protected static final HashMap<String, Integer> P = new HashMap<>();
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 6;
    public static final int k = 7;
    public static final int l = 8;
    public static final int m = 9;
    public static final int n = 10;
    public static final int o = 11;
    public static final int p = 12;
    public static final int q = 13;
    public static final int r = 14;
    public static final int s = 15;
    public static final int t = 16;
    public static final int u = 17;
    public static final int v = 18;
    public static final int w = 19;
    public static final int x = 20;
    public static final int y = 21;
    public static final int z = 22;

    @NotNull
    public String a() {
        return "EPS";
    }

    static {
        P.put("%!PS-Adobe-", 1);
        P.put("%%Author", 2);
        P.put("%%BoundingBox", 3);
        P.put("%%Copyright", 4);
        P.put("%%CreationDate", 5);
        P.put("%%Creator", 6);
        P.put("%%For", 7);
        P.put("%ImageData", 8);
        P.put("%%Keywords", 9);
        P.put("%%ModDate", 10);
        P.put("%%Pages", 11);
        P.put("%%Routing", 12);
        P.put("%%Subject", 13);
        P.put("%%Title", 14);
        P.put("%%Version", 15);
        P.put("%%DocumentData", 16);
        P.put("%%Emulation", 17);
        P.put("%%Extensions", 18);
        P.put("%%LanguageLevel", 19);
        P.put("%%Orientation", 20);
        P.put("%%PageOrder", 21);
        P.put("%%OperatorIntervention", 22);
        P.put("%%OperatorMessage", 23);
        P.put("%%ProofMode", 24);
        P.put("%%Requirements", 25);
        P.put("%%VMlocation", 26);
        P.put("%%VMusage", 27);
        P.put("Image Width", 28);
        P.put("Image Height", 29);
        P.put("Color Type", 30);
        P.put("Ram Size", 31);
        P.put("TIFFPreview", 32);
        P.put("TIFFPreviewOffset", 33);
        P.put("WMFPreview", 34);
        P.put("WMFPreviewOffset", 35);
        P.put("%%+", 36);
        O.put(36, "Line Continuation");
        O.put(3, "Bounding Box");
        O.put(4, ExifInterface.TAG_COPYRIGHT);
        O.put(16, "Document Data");
        O.put(17, "Emulation");
        O.put(18, "Extensions");
        O.put(19, "Language Level");
        O.put(20, ExifInterface.TAG_ORIENTATION);
        O.put(21, "Page Order");
        O.put(15, e.e);
        O.put(8, "Image Data");
        O.put(28, "Image Width");
        O.put(29, "Image Height");
        O.put(30, "Color Type");
        O.put(31, "Ram Size");
        O.put(6, "Creator");
        O.put(5, "Creation Date");
        O.put(7, "For");
        O.put(25, "Requirements");
        O.put(12, "Routing");
        O.put(14, "Title");
        O.put(1, "DSC Version");
        O.put(11, "Pages");
        O.put(22, "Operator Intervention");
        O.put(23, "Operator Message");
        O.put(24, "Proof Mode");
        O.put(26, "VM Location");
        O.put(27, "VM Usage");
        O.put(2, "Author");
        O.put(9, "Keywords");
        O.put(10, "Modify Date");
        O.put(13, "Subject");
        O.put(32, "TIFF Preview Size");
        O.put(33, "TIFF Preview Offset");
        O.put(34, "WMF Preview Size");
        O.put(35, "WMF Preview Offset");
    }

    public EpsDirectory() {
        a((TagDescriptor) new EpsDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return O;
    }
}
