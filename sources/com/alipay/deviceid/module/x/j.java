package com.alipay.deviceid.module.x;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public final class j {

    /* renamed from: a  reason: collision with root package name */
    private static j f928a = new j();

    private j() {
    }

    public static j a() {
        return f928a;
    }

    private static byte[] b(Context context, String str) {
        try {
            for (PackageInfo next : context.getPackageManager().getInstalledPackages(64)) {
                if (next.packageName.equals(str)) {
                    return next.signatures[0].toByteArray();
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] a(Context context, String str) {
        try {
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(b(context, str)))).getPublicKey().getEncoded();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception unused) {
            return "0.0.0";
        }
    }
}
