package com.xiaomi.phonenum;

import android.os.RemoteException;
import com.xiaomi.phonenum.PhoneNumKeeper;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.obtain.PhoneException;
import com.xiaomi.phonenum.obtain.PhoneLevel;
import java.io.IOException;

class MiuiPhoneNumServiceAdapter implements PhoneNumGetter {

    /* renamed from: a  reason: collision with root package name */
    private MiuiPhoneNumServiceProxy f12546a;
    private PhoneNumStore b;

    MiuiPhoneNumServiceAdapter(MiuiPhoneNumServiceProxy miuiPhoneNumServiceProxy, PhoneNumStore phoneNumStore) {
        this.f12546a = miuiPhoneNumServiceProxy;
        this.b = phoneNumStore;
    }

    public void a(PhoneNumKeeper.SetupFinishedListener setupFinishedListener) {
        this.f12546a.a(setupFinishedListener);
    }

    public void a() {
        this.f12546a.a();
    }

    public PhoneNum a(int i, PhoneLevel phoneLevel) {
        try {
            return this.b.a(i, phoneLevel);
        } catch (PhoneException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean a(int i, PhoneNum phoneNum) {
        this.b.a(phoneNum);
        try {
            return this.f12546a.a(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public PhoneNum b(int i, PhoneLevel phoneLevel) throws IOException {
        if (phoneLevel.value >= PhoneLevel.SMS_VERIFY.value) {
            try {
                return this.f12546a.a(i, true);
            } catch (RemoteException unused) {
                return Error.UNKNOW.result("RemoteException");
            }
        } else {
            try {
                return this.b.b(i, phoneLevel);
            } catch (PhoneException e) {
                e.printStackTrace();
                return e.error.result();
            }
        }
    }
}
