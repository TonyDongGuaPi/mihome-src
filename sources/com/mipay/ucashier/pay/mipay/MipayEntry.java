package com.mipay.ucashier.pay.mipay;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.mipay.common.base.l;
import com.mipay.sdk.MipayFactory;
import com.mipay.ucashier.UCashier;
import com.mipay.ucashier.pay.IPayEntry;
import com.mipay.ucashier.ui.BaseUCashierFragment;

public class MipayEntry implements IPayEntry {

    /* renamed from: a  reason: collision with root package name */
    private l f8186a;

    private int a(int i) {
        if (i == 0) {
            return 0;
        }
        return i == 2 ? 2 : 1;
    }

    private Bundle a(int i, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("errcode", a(i));
        bundle.putString("errDesc", str);
        bundle.putString("result", str2);
        return bundle;
    }

    private int b(int i) {
        return i == 0 ? BaseUCashierFragment.RESULT_OK : i == 2 ? BaseUCashierFragment.RESULT_CANCELED : BaseUCashierFragment.RESULT_ERROR;
    }

    public void handleActivityResult(int i, int i2, Intent intent) {
        int i3;
        MipayFactory.release();
        String str = "";
        String str2 = "";
        if (i != 424 || (i2 != -1 && intent == null)) {
            i3 = 1;
        } else {
            i3 = intent.getIntExtra("code", -1);
            str = intent.getStringExtra("message");
            str2 = intent.getStringExtra("result");
        }
        this.f8186a.setResult(b(i3), a(i3, str, str2));
        if (i3 == 2) {
            this.f8186a.finish();
        } else {
            this.f8186a.finish("trade", false);
        }
    }

    public void pay(l lVar, String str) {
        this.f8186a = lVar;
        MipayFactory.get(lVar.getActivity(), UCashier.getAccountProvider() != null ? UCashier.getAccountProvider() : new AccountProvider(lVar.getActivity())).pay((Fragment) lVar, str, (Bundle) null);
    }
}
