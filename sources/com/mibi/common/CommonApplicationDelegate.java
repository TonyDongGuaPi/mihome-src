package com.mibi.common;

import android.content.Context;
import com.mibi.common.data.Client;
import com.mipay.core.runtime.BundleApplicationDelegate;

public class CommonApplicationDelegate extends BundleApplicationDelegate {

    /* renamed from: a  reason: collision with root package name */
    public static Context f7437a;

    public CommonApplicationDelegate(Context context) {
        super(context);
    }

    public void onCreate() {
        super.onCreate();
        f7437a = getApplicationContext();
        Client.a(f7437a);
    }
}
