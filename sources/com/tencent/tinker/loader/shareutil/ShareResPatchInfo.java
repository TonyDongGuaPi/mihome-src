package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

public class ShareResPatchInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f9260a = null;
    public String b = null;
    public ArrayList<String> c = new ArrayList<>();
    public ArrayList<String> d = new ArrayList<>();
    public ArrayList<String> e = new ArrayList<>();
    public HashMap<String, File> f = new HashMap<>();
    public ArrayList<String> g = new ArrayList<>();
    public HashMap<String, LargeModeInfo> h = new HashMap<>();
    public HashSet<Pattern> i = new HashSet<>();

    public static class LargeModeInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f9261a = null;
        public long b;
        public File c = null;
    }

    public static void a(String str, ShareResPatchInfo shareResPatchInfo) {
        if (str != null && str.length() != 0) {
            String[] split = str.split("\n");
            int i2 = 0;
            while (i2 < split.length) {
                String str2 = split[i2];
                if (str2 != null && str2.length() > 0) {
                    if (str2.startsWith(ShareConstants.L)) {
                        String[] split2 = str2.split(",", 3);
                        shareResPatchInfo.f9260a = split2[1];
                        shareResPatchInfo.b = split2[2];
                    } else if (str2.startsWith(ShareConstants.S)) {
                        for (int parseInt = Integer.parseInt(str2.split(":", 2)[1]); parseInt > 0; parseInt--) {
                            i2++;
                            shareResPatchInfo.i.add(a(split[i2]));
                        }
                    } else if (str2.startsWith(ShareConstants.O)) {
                        for (int parseInt2 = Integer.parseInt(str2.split(":", 2)[1]); parseInt2 > 0; parseInt2--) {
                            i2++;
                            shareResPatchInfo.c.add(split[i2]);
                        }
                    } else if (str2.startsWith(ShareConstants.P)) {
                        for (int parseInt3 = Integer.parseInt(str2.split(":", 2)[1]); parseInt3 > 0; parseInt3--) {
                            i2++;
                            shareResPatchInfo.e.add(split[i2]);
                        }
                    } else if (str2.startsWith(ShareConstants.Q)) {
                        for (int parseInt4 = Integer.parseInt(str2.split(":", 2)[1]); parseInt4 > 0; parseInt4--) {
                            i2++;
                            String[] split3 = split[i2].split(",", 3);
                            String str3 = split3[0];
                            LargeModeInfo largeModeInfo = new LargeModeInfo();
                            largeModeInfo.f9261a = split3[1];
                            largeModeInfo.b = Long.parseLong(split3[2]);
                            shareResPatchInfo.g.add(str3);
                            shareResPatchInfo.h.put(str3, largeModeInfo);
                        }
                    } else if (str2.startsWith(ShareConstants.R)) {
                        for (int parseInt5 = Integer.parseInt(str2.split(":", 2)[1]); parseInt5 > 0; parseInt5--) {
                            i2++;
                            shareResPatchInfo.d.add(split[i2]);
                        }
                    } else if (str2.startsWith(ShareConstants.T)) {
                        for (int parseInt6 = Integer.parseInt(str2.split(":", 2)[1]); parseInt6 > 0; parseInt6--) {
                            i2++;
                            shareResPatchInfo.f.put(split[i2], (Object) null);
                        }
                    }
                }
                i2++;
            }
        }
    }

    public static boolean a(HashSet<Pattern> hashSet, String str) {
        if (hashSet.isEmpty()) {
            return false;
        }
        Iterator<Pattern> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(ShareResPatchInfo shareResPatchInfo) {
        String str;
        if (shareResPatchInfo == null || (str = shareResPatchInfo.b) == null || str.length() != 32) {
            return false;
        }
        return true;
    }

    private static Pattern a(String str) {
        if (str.contains(".")) {
            str = str.replaceAll("\\.", "\\\\.");
        }
        if (str.contains("?")) {
            str = str.replaceAll("\\?", "\\.");
        }
        if (str.contains("*")) {
            str = str.replace("*", ".*");
        }
        return Pattern.compile(str);
    }

    public static void b(String str, ShareResPatchInfo shareResPatchInfo) {
        if (str != null && str.length() != 0) {
            String str2 = str.split("\n")[0];
            if (str2 == null || str2.length() <= 0) {
                throw new TinkerRuntimeException("res meta Corrupted:" + str);
            }
            String[] split = str2.split(",", 3);
            shareResPatchInfo.f9260a = split[1];
            shareResPatchInfo.b = split[2];
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("resArscMd5:" + this.b + "\n");
        stringBuffer.append("arscBaseCrc:" + this.f9260a + "\n");
        Iterator<Pattern> it = this.i.iterator();
        while (it.hasNext()) {
            stringBuffer.append(ShareConstants.S + it.next() + "\n");
        }
        Iterator<String> it2 = this.c.iterator();
        while (it2.hasNext()) {
            stringBuffer.append("addedSet:" + it2.next() + "\n");
        }
        Iterator<String> it3 = this.e.iterator();
        while (it3.hasNext()) {
            stringBuffer.append("modifiedSet:" + it3.next() + "\n");
        }
        Iterator<String> it4 = this.g.iterator();
        while (it4.hasNext()) {
            stringBuffer.append("largeModifiedSet:" + it4.next() + "\n");
        }
        Iterator<String> it5 = this.d.iterator();
        while (it5.hasNext()) {
            stringBuffer.append("deletedSet:" + it5.next() + "\n");
        }
        for (String str : this.f.keySet()) {
            stringBuffer.append("storeSet:" + str + "\n");
        }
        return stringBuffer.toString();
    }
}
