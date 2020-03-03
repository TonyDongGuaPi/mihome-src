package com.xiaomi.phonenum;

import com.xiaomi.phonenum.PhoneNumKeeper;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.obtain.PhoneException;
import com.xiaomi.phonenum.obtain.PhoneLevel;
import com.xiaomi.phonenum.utils.LoggerManager;
import java.io.IOException;

class PhoneNumStoreAdapter implements PhoneNumGetter {

    /* renamed from: a  reason: collision with root package name */
    private String f12552a = "PhoneNumStoreAdapter";
    private PhoneNumStore b;

    public void a() {
    }

    PhoneNumStoreAdapter(PhoneNumStore phoneNumStore) {
        this.b = phoneNumStore;
    }

    public void a(PhoneNumKeeper.SetupFinishedListener setupFinishedListener) {
        setupFinishedListener.onSetupFinished(Error.NONE);
    }

    public PhoneNum a(int i, PhoneLevel phoneLevel) {
        try {
            return this.b.a(i, phoneLevel);
        } catch (PhoneException e) {
            LoggerManager.a().a(this.f12552a, e.toString());
            return null;
        }
    }

    public boolean a(int i, PhoneNum phoneNum) {
        return this.b.a(phoneNum);
    }

    public PhoneNum b(int i, PhoneLevel phoneLevel) throws IOException {
        try {
            return this.b.b(i, phoneLevel);
        } catch (PhoneException e) {
            LoggerManager.a().a(this.f12552a, e.toString());
            return e.error.result();
        }
    }
}
