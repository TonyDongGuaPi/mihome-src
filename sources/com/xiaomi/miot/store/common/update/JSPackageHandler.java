package com.xiaomi.miot.store.common.update;

import android.text.TextUtils;
import com.xiaomi.miot.store.utils.CompressUtils;
import com.xiaomi.miot.store.utils.Utils;
import com.xiaomi.smarthome.bsdiff.Patcher;
import com.xiaomi.youpin.common.util.crypto.MD5Utils;
import com.xiaomi.youpin.log.LogUtils;
import java.io.File;
import java.util.HashMap;

public class JSPackageHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final int f11399a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private static final String d = "JSPackageHandler";
    private static final String e = "android.tar";

    private boolean a(File file, File file2, File file3, boolean z) {
        boolean z2;
        File file4;
        if (z) {
            File file5 = new File(file, e);
            file4 = new File(file3, e);
            if (!file3.exists()) {
                file3.mkdirs();
            }
            LogUtils.d(d, "old tar exist:" + file5.exists() + ",path:" + file.getPath());
            z2 = Patcher.applyPatch(file5.getPath(), file4.getPath(), file2.getPath()) == 0;
        } else {
            z2 = CompressUtils.a(file2, file3);
            file2.delete();
            File[] listFiles = file3.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                file4 = null;
            } else {
                file4 = listFiles[0];
                file4.renameTo(new File(file3, e));
            }
        }
        if (!z2 || file4 == null) {
            return false;
        }
        return CompressUtils.b(file4, file3);
    }

    public int a(UpdateRequest updateRequest, UpdateResponse updateResponse, boolean z) {
        if (updateResponse == null || TextUtils.isEmpty(updateResponse.f11407a)) {
            if (z) {
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.f11398a, (Object) null);
                updateRequest.d.a(hashMap);
            }
            LogUtils.d(d, "response eTag is null,so return");
            return 2;
        }
        String str = updateResponse.f11407a;
        File file = new File(updateRequest.c, String.format("%sD", new Object[]{updateRequest.f11406a}));
        File file2 = new File(updateRequest.c, String.format("%sD", new Object[]{str}));
        LogUtils.d(d, String.format("old unzip folder: %s", new Object[]{file.getAbsolutePath()}));
        LogUtils.d(d, String.format("new unzip folder: %s", new Object[]{file2.getAbsolutePath()}));
        LogUtils.d(d, String.format("old Etag--: %s", new Object[]{updateRequest.f11406a}));
        LogUtils.d(d, String.format("new Etag--: %s", new Object[]{str}));
        LogUtils.d(d, "response  ctag: %s" + updateResponse.b);
        if (TextUtils.isEmpty(str) || !TextUtils.equals(str, updateRequest.f11406a)) {
            File file3 = new File(updateRequest.c, str);
            String a2 = MD5Utils.a(file3);
            boolean a3 = a(file, file3, file2, updateResponse.c);
            File file4 = new File(file2, e);
            if (updateResponse.c) {
                a2 = MD5Utils.a(file4);
            }
            LogUtils.d(d, "TextUtils.equals(fileMd5, response.cTag)" + TextUtils.equals(a2, updateResponse.b) + "  fileMd5:  " + a2 + " ctag: " + updateResponse.b);
            if (!TextUtils.equals(a2, updateResponse.b)) {
                Utils.a(file2);
                updateRequest.f11406a = null;
                LogUtils.d(d, "the etag is invalid with value:" + a2 + ",expected:" + updateResponse.b + ",so retry to download whole package");
                return 1;
            }
            File file5 = new File(file2, updateRequest.b);
            if (!a3 || !file5.exists()) {
                LogUtils.e(d, "unpack failed!");
                Utils.a(file2);
                return 1;
            }
            String absolutePath = file5.getAbsolutePath();
            String a4 = MD5Utils.a(file5);
            LogUtils.d(d, String.format("New path: %s.", new Object[]{absolutePath}));
            if (z) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put(Constants.f11398a, absolutePath);
                hashMap2.put(Constants.b, str);
                hashMap2.put(Constants.c, a4);
                hashMap2.put(Constants.d, String.valueOf(System.currentTimeMillis()));
                updateRequest.d.a(hashMap2);
            }
            return 0;
        }
        File file6 = new File(file2, updateRequest.b);
        File file7 = new File(file, updateRequest.b);
        LogUtils.d(d, String.format("Not modified. path: %s.", new Object[]{file6.getAbsolutePath()}));
        if (!file6.exists() || !TextUtils.equals(MD5Utils.a(file6), MD5Utils.a(file7))) {
            LogUtils.d(d, "File not exist. Reload js:" + MD5Utils.a(file7) + ",file:" + MD5Utils.a(file6));
            Utils.a(file2);
            updateRequest.f11406a = null;
            return 1;
        }
        if (z) {
            HashMap hashMap3 = new HashMap();
            hashMap3.put(Constants.f11398a, file6.getAbsolutePath());
            hashMap3.put(Constants.b, updateRequest.f11406a);
            hashMap3.put(Constants.d, String.valueOf(System.currentTimeMillis()));
            updateRequest.d.a(hashMap3);
        }
        return 0;
    }
}
