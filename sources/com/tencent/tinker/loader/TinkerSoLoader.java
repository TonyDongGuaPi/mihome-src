package com.tencent.tinker.loader;

import android.content.Intent;
import com.tencent.tinker.loader.shareutil.ShareBsDiffPatchInfo;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TinkerSoLoader {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f9234a = "assets/so_meta.txt";
    protected static final String b = "lib";
    private static final String c = "Tinker.TinkerSoLoader";

    public static boolean a(String str, ShareSecurityCheck shareSecurityCheck, Intent intent) {
        String str2 = shareSecurityCheck.a().get("assets/so_meta.txt");
        if (str2 == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        ShareBsDiffPatchInfo.a(str2, arrayList);
        if (arrayList.isEmpty()) {
            return true;
        }
        String str3 = str + "/" + "lib" + "/";
        HashMap hashMap = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ShareBsDiffPatchInfo shareBsDiffPatchInfo = (ShareBsDiffPatchInfo) it.next();
            if (!ShareBsDiffPatchInfo.a(shareBsDiffPatchInfo)) {
                intent.putExtra(ShareIntentUtil.m, -4);
                ShareIntentUtil.a(intent, -8);
                return false;
            }
            hashMap.put(shareBsDiffPatchInfo.e + "/" + shareBsDiffPatchInfo.f9250a, shareBsDiffPatchInfo.b);
        }
        File file = new File(str3);
        if (!file.exists() || !file.isDirectory()) {
            ShareIntentUtil.a(intent, -17);
            return false;
        }
        for (String str4 : hashMap.keySet()) {
            File file2 = new File(str3 + str4);
            if (!SharePatchFileUtil.a(file2)) {
                ShareIntentUtil.a(intent, -18);
                intent.putExtra(ShareIntentUtil.i, file2.getAbsolutePath());
                return false;
            }
        }
        intent.putExtra(ShareIntentUtil.j, hashMap);
        return true;
    }
}
