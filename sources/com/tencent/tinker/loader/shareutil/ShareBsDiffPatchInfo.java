package com.tencent.tinker.loader.shareutil;

import java.util.ArrayList;

public class ShareBsDiffPatchInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f9250a;
    public String b;
    public String c;
    public String d;
    public String e;

    public ShareBsDiffPatchInfo(String str, String str2, String str3, String str4, String str5) {
        this.f9250a = str;
        this.b = str2;
        this.c = str4;
        this.d = str5;
        this.e = str3;
    }

    public static void a(String str, ArrayList<ShareBsDiffPatchInfo> arrayList) {
        String[] split;
        if (str != null && str.length() != 0) {
            for (String str2 : str.split("\n")) {
                if (str2 != null && str2.length() > 0 && (split = str2.split(",", 5)) != null && split.length >= 5) {
                    arrayList.add(new ShareBsDiffPatchInfo(split[0].trim(), split[2].trim(), split[1].trim(), split[3].trim(), split[4].trim()));
                }
            }
        }
    }

    public static boolean a(ShareBsDiffPatchInfo shareBsDiffPatchInfo) {
        if (shareBsDiffPatchInfo == null) {
            return false;
        }
        String str = shareBsDiffPatchInfo.f9250a;
        String str2 = shareBsDiffPatchInfo.b;
        if (str == null || str.length() <= 0 || str2 == null || str2.length() != 32) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f9250a);
        stringBuffer.append(",");
        stringBuffer.append(this.e);
        stringBuffer.append(",");
        stringBuffer.append(this.b);
        stringBuffer.append(",");
        stringBuffer.append(this.c);
        stringBuffer.append(",");
        stringBuffer.append(this.d);
        return stringBuffer.toString();
    }
}
