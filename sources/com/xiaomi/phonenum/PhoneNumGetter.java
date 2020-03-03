package com.xiaomi.phonenum;

import com.xiaomi.phonenum.PhoneNumKeeper;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.obtain.PhoneLevel;
import java.io.IOException;

interface PhoneNumGetter {
    PhoneNum a(int i, PhoneLevel phoneLevel);

    void a();

    void a(PhoneNumKeeper.SetupFinishedListener setupFinishedListener);

    boolean a(int i, PhoneNum phoneNum);

    PhoneNum b(int i, PhoneLevel phoneLevel) throws IOException;
}
