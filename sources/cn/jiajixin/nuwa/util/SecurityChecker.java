package cn.jiajixin.nuwa.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.security.MessageDigest;

public class SecurityChecker {
    private static final String b = "SecurityChecker";

    /* renamed from: a  reason: collision with root package name */
    String f663a;

    public SecurityChecker(Context context) {
        a(context);
    }

    public boolean a(Context context, File file) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(file.getAbsolutePath(), 64);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            Log.e(b, "verifyApk packageInfo==null");
            return true;
        }
        String a2 = a(packageInfo.signatures);
        if (!TextUtils.isEmpty(a2)) {
            return a2.equals(this.f663a);
        }
        Log.e(b, "verifyApk generateApkSignatureMD5 null");
        return false;
    }

    private void a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo == null) {
                this.f663a = "";
            } else {
                this.f663a = a(packageInfo.signatures);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(b, "init", e);
        }
    }

    private static String a(Signature[] signatureArr) {
        if (signatureArr == null) {
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (signatureArr != null) {
                for (Signature byteArray : signatureArr) {
                    instance.update(byteArray.toByteArray());
                }
            }
            return ByteUtils.a(instance.digest());
        } catch (Exception unused) {
            return "";
        }
    }
}
