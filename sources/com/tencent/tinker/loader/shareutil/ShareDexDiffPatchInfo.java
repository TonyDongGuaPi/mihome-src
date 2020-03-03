package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.TinkerRuntimeException;
import java.util.ArrayList;

public class ShareDexDiffPatchInfo {

    /* renamed from: a  reason: collision with root package name */
    public final String f1358a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final boolean i;
    public final String j;

    public ShareDexDiffPatchInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.f1358a = str;
        this.g = str2;
        this.b = str3;
        this.c = str4;
        this.f = str5;
        this.d = str6;
        this.e = str7;
        this.h = str8;
        if (str8.equals("jar")) {
            this.i = true;
            if (SharePatchFileUtil.g(str)) {
                this.j = str + ".jar";
                return;
            }
            this.j = str;
        } else if (str8.equals(ShareConstants.V)) {
            this.i = false;
            this.j = str;
        } else {
            throw new TinkerRuntimeException("can't recognize dex mode:" + str8);
        }
    }

    public static void a(String str, ArrayList<ShareDexDiffPatchInfo> arrayList) {
        String[] split;
        if (str != null && str.length() != 0) {
            for (String str2 : str.split("\n")) {
                if (str2 != null && str2.length() > 0 && (split = str2.split(",", 8)) != null && split.length >= 8) {
                    arrayList.add(new ShareDexDiffPatchInfo(split[0].trim(), split[1].trim(), split[2].trim(), split[3].trim(), split[4].trim(), split[5].trim(), split[6].trim(), split[7].trim()));
                }
            }
        }
    }

    public static boolean a(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        if (shareDexDiffPatchInfo == null) {
            return false;
        }
        String str = shareDexDiffPatchInfo.f1358a;
        String str2 = ShareTinkerInternals.a() ? shareDexDiffPatchInfo.c : shareDexDiffPatchInfo.b;
        if (str == null || str.length() <= 0 || str2 == null || str2.length() != 32) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f1358a);
        stringBuffer.append(",");
        stringBuffer.append(this.g);
        stringBuffer.append(",");
        stringBuffer.append(this.b);
        stringBuffer.append(",");
        stringBuffer.append(this.c);
        stringBuffer.append(",");
        stringBuffer.append(this.d);
        stringBuffer.append(",");
        stringBuffer.append(this.e);
        stringBuffer.append(",");
        stringBuffer.append(this.f);
        stringBuffer.append(",");
        stringBuffer.append(this.h);
        return stringBuffer.toString();
    }
}
