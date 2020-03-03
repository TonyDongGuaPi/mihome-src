package com.xiaomi.miot.store.ucashier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.mipay.sdk.IMipayAccountProvider;
import com.mipay.sdk.MipayFactory;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.IPayProvider;
import java.util.HashMap;

public class MipayProvider implements IPayProvider {

    /* renamed from: a  reason: collision with root package name */
    private IMipayAccountProvider f11409a;
    private ICallback b;

    public void clear() {
    }

    public String name() {
        return "mipay";
    }

    public MipayProvider(IMipayAccountProvider iMipayAccountProvider) {
        this.f11409a = iMipayAccountProvider;
    }

    public void pay(Activity activity, String str, ICallback iCallback) {
        this.b = iCallback;
        MipayFactory.get(activity, this.f11409a).pay(activity, str, (Bundle) null);
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        MipayFactory.release();
        if (i != 424) {
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
        if (this.b != null) {
            this.b.callback(hashMap);
        }
        this.b = null;
        return true;
    }
}
