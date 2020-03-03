package com.xiaomi.miot.store.ucashier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.mipay.sdk.IMipayAccountProvider;
import com.mipay.ucashier.UCashier;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.IPayProvider;
import java.util.HashMap;

public class UCashierProvider implements IPayProvider {

    /* renamed from: a  reason: collision with root package name */
    private IMipayAccountProvider f11410a;
    private ICallback b;

    public String name() {
        return "ucashier";
    }

    public UCashierProvider(IMipayAccountProvider iMipayAccountProvider) {
        this.f11410a = iMipayAccountProvider;
    }

    public void pay(Activity activity, String str, ICallback iCallback) {
        this.b = iCallback;
        UCashier.get(this.f11410a).pay(activity, str, (Bundle) null);
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i != 810 || this.b == null) {
            return false;
        }
        int i3 = -10000;
        String str = "";
        String str2 = "fail";
        if (intent != null) {
            i3 = intent.getIntExtra("code", -1);
            str = intent.getStringExtra("message");
            str2 = intent.getStringExtra("result");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("code", Integer.valueOf(i3));
        hashMap.put("message", str);
        hashMap.put("result", str2);
        this.b.callback(hashMap);
        return true;
    }

    public void clear() {
        this.f11410a = null;
        this.b = null;
    }
}
