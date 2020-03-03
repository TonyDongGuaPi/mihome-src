package com.huawei.hms.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private final PackageManager f5868a;

    public enum a {
        ENABLED,
        DISABLED,
        NOT_INSTALLED
    }

    public e(Context context) {
        this.f5868a = context.getPackageManager();
    }

    public a a(String str) {
        try {
            if (this.f5868a.getApplicationInfo(str, 0).enabled) {
                return a.ENABLED;
            }
            return a.DISABLED;
        } catch (PackageManager.NameNotFoundException unused) {
            return a.NOT_INSTALLED;
        }
    }

    public int b(String str) {
        try {
            PackageInfo packageInfo = this.f5868a.getPackageInfo(str, 16);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public String c(String str) {
        try {
            PackageInfo packageInfo = this.f5868a.getPackageInfo(str, 16);
            return (packageInfo == null || packageInfo.versionName == null) ? "" : packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public String d(String str) {
        byte[] e = e(str);
        if (e == null || e.length == 0) {
            return null;
        }
        return b.b(f.a(e), true);
    }

    private byte[] e(String str) {
        InputStream inputStream = null;
        try {
            PackageInfo packageInfo = this.f5868a.getPackageInfo(str, 64);
            if (packageInfo != null && packageInfo.signatures.length > 0) {
                InputStream a2 = c.a(packageInfo.signatures[0].toByteArray());
                try {
                    byte[] encoded = CertificateFactory.getInstance("X.509").generateCertificate(a2).getEncoded();
                    c.a(a2);
                    return encoded;
                } catch (PackageManager.NameNotFoundException | IOException | CertificateException e) {
                    Exception exc = e;
                    inputStream = a2;
                    e = exc;
                    try {
                        com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
                        c.a(inputStream);
                        com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint.");
                        return new byte[0];
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = a2;
                    c.a(inputStream);
                    throw th;
                }
            }
        } catch (PackageManager.NameNotFoundException | IOException | CertificateException e2) {
            e = e2;
            com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
            c.a(inputStream);
            com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint.");
            return new byte[0];
        }
        c.a(inputStream);
        com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint.");
        return new byte[0];
    }

    public boolean a(String str, String str2) {
        try {
            PackageInfo packageInfo = this.f5868a.getPackageInfo(str, 8);
            if (!(packageInfo == null || packageInfo.providers == null)) {
                for (ProviderInfo providerInfo : packageInfo.providers) {
                    if (str2.equals(providerInfo.authority)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public boolean a(String str, String str2, String str3) {
        InputStream inputStream;
        Exception e;
        PackageInfo packageArchiveInfo = this.f5868a.getPackageArchiveInfo(str, 64);
        if (packageArchiveInfo == null || packageArchiveInfo.signatures.length <= 0 || !str2.equals(packageArchiveInfo.packageName)) {
            return false;
        }
        try {
            inputStream = c.a(packageArchiveInfo.signatures[0].toByteArray());
        } catch (IOException | CertificateException e2) {
            e = e2;
            inputStream = null;
            try {
                com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
                c.a(inputStream);
                return false;
            } catch (Throwable th) {
                th = th;
                c.a(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            c.a(inputStream);
            throw th;
        }
        try {
            boolean equalsIgnoreCase = str3.equalsIgnoreCase(b.b(f.a(CertificateFactory.getInstance("X.509").generateCertificate(inputStream).getEncoded()), true));
            c.a(inputStream);
            return equalsIgnoreCase;
        } catch (IOException | CertificateException e3) {
            e = e3;
            com.huawei.hms.support.log.a.d("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
            c.a(inputStream);
            return false;
        }
    }
}
