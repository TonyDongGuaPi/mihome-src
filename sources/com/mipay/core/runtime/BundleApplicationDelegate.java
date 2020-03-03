package com.mipay.core.runtime;

import android.content.Context;
import com.mipay.core.internal.BundleManager;

public class BundleApplicationDelegate {
    private Context mContext;

    public BundleApplicationDelegate(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void onCreate() {
        BundleManager.init(getApplicationContext());
    }

    public Context getApplicationContext() {
        return this.mContext;
    }
}
