package com.unionpay.tsmservice.mi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.unionpay.tsmservice.mi.ITsmActivityCallback;

public final class a extends ITsmActivityCallback.Stub {

    /* renamed from: a  reason: collision with root package name */
    private Context f9841a;

    public a(Context context) {
        this.f9841a = context;
    }

    public final void startActivity(String str, String str2, int i, Bundle bundle) {
        ComponentName componentName = new ComponentName(str, str2);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        if (i != -1) {
            intent.setFlags(i);
        }
        intent.setComponent(componentName);
        this.f9841a.startActivity(intent);
    }
}
