package com.mipay.ucashier.pay;

import android.content.Intent;
import com.mipay.common.base.l;

public interface IPayEntry {
    void handleActivityResult(int i, int i2, Intent intent);

    void pay(l lVar, String str);
}
