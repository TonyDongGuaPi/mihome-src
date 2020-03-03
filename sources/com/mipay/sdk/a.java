package com.mipay.sdk;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

class a implements IMipay {

    /* renamed from: a  reason: collision with root package name */
    private final Mipay f8152a;

    a(Context context) {
        this.f8152a = Mipay.get(context);
    }

    public void pay(Activity activity, String str, Bundle bundle) {
        this.f8152a.pay(activity, str, bundle);
    }

    public void pay(Fragment fragment, String str, Bundle bundle) {
        this.f8152a.pay(fragment, str, bundle);
    }
}
