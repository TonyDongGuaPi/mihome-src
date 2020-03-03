package com.megvii.idcardquality;

import android.content.Context;
import com.megvii.idcard.sdk.jni.IDCardApi;
import com.megvii.licencemanage.sdk.a;
import com.megvii.licensemanager.ILicenseManager;
import java.security.InvalidParameterException;

public class IDCardQualityLicenseManager implements ILicenseManager {

    /* renamed from: a  reason: collision with root package name */
    private Context f6664a;
    private a b;

    public IDCardQualityLicenseManager(Context context) {
        if (context != null) {
            this.f6664a = context.getApplicationContext();
            this.b = new a(this.f6664a);
            return;
        }
        throw new InvalidParameterException("mContext cannot be null");
    }

    public String getContext(String str) {
        return this.b.a(str, 30, new long[]{IDCardApi.nativeGetApiName()});
    }

    public long setLicense(String str) {
        if (this.b.a(str)) {
            return checkCachedLicense();
        }
        return 0;
    }

    public long checkCachedLicense() {
        return IDCardApi.nativeGetApiExpication(this.f6664a);
    }

    public String getVersion() {
        return IDCardQualityAssessment.a();
    }

    public String a() {
        if (this.b == null) {
            return null;
        }
        return this.b.a();
    }
}
