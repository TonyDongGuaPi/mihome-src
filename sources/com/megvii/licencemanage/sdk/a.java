package com.megvii.licencemanage.sdk;

import android.content.Context;
import com.megvii.licencemanage.sdk.jni.LicenceApi;
import java.util.regex.Pattern;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private int f6667a = 0;
    private Context b;

    public a(Context context) {
        this.b = context;
    }

    public final String a(String str, int i, long[] jArr) {
        this.f6667a = 0;
        if (this.b == null) {
            this.f6667a = 1;
            return null;
        }
        String nativeGetLicense = LicenceApi.nativeGetLicense(this.b, str, 30, jArr);
        if (!Pattern.compile("[0-9]+").matcher(nativeGetLicense).matches()) {
            return nativeGetLicense;
        }
        this.f6667a = Integer.parseInt(nativeGetLicense);
        return null;
    }

    public final boolean a(String str) {
        this.f6667a = 0;
        if (this.b == null || str == null) {
            this.f6667a = 1;
            return false;
        }
        this.f6667a = LicenceApi.nativeSetLicense(this.b, str);
        if (this.f6667a == 0) {
            return true;
        }
        return false;
    }

    public final String a() {
        int i = this.f6667a;
        switch (i) {
            case -1:
                return "MG_RETCODE_FAILED";
            case 0:
                return "MG_RETCODE_OK";
            case 1:
                return "MG_RETCODE_INVALID_ARGUMENT";
            case 2:
                return "MG_RETCODE_INVALID_HANDLE";
            case 3:
                return "MG_RETCODE_INDEX_OUT_OF_RANGE";
            default:
                switch (i) {
                    case 101:
                        return "MG_RETCODE_EXPIRE";
                    case 102:
                        return "MG_RETCODE_INVALID_BUNDLEID";
                    case 103:
                        return "MG_RETCODE_INVALID_LICENSE";
                    case 104:
                        return "MG_RETCODE_INVALID_MODEL";
                    default:
                        return null;
                }
        }
    }
}
