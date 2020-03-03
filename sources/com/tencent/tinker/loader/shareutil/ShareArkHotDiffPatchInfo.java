package com.tencent.tinker.loader.shareutil;

import java.util.ArrayList;

public class ShareArkHotDiffPatchInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f9249a;
    public String b;
    public String c;

    public ShareArkHotDiffPatchInfo(String str, String str2, String str3) {
        this.b = str2;
        this.c = str3;
        this.f9249a = str;
    }

    public static void a(String str, ArrayList<ShareArkHotDiffPatchInfo> arrayList) {
        String[] split;
        if (str != null && arrayList != null) {
            for (String str2 : str.split("\n")) {
                if (str2 != null && str2.length() > 0 && (split = str2.split(",", 4)) != null && split.length >= 3) {
                    arrayList.add(new ShareArkHotDiffPatchInfo(split[1].trim(), split[0].trim(), split[2].trim()));
                }
            }
        }
    }

    public static boolean a(ShareArkHotDiffPatchInfo shareArkHotDiffPatchInfo) {
        if (shareArkHotDiffPatchInfo == null) {
            return false;
        }
        String str = shareArkHotDiffPatchInfo.b;
        String str2 = shareArkHotDiffPatchInfo.c;
        if (str == null || str.length() <= 0 || str2 == null || str2.length() != 32) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.b);
        stringBuffer.append(",");
        stringBuffer.append(this.f9249a);
        stringBuffer.append(",");
        stringBuffer.append(this.c);
        return stringBuffer.toString();
    }
}
