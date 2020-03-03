package com.mobikwik.sdk;

import android.content.Intent;
import com.mobikwik.sdk.lib.Constants;

class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Intent f8367a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ PGWebView d;

    i(PGWebView pGWebView, Intent intent, String str, String str2) {
        this.d = pGWebView;
        this.f8367a = intent;
        this.b = str;
        this.c = str2;
    }

    public void run() {
        Intent intent = new Intent();
        if (this.f8367a != null) {
            intent = new Intent(this.f8367a);
        } else {
            intent.putExtra(Constants.STATUS_CODE, this.b);
            intent.putExtra(Constants.STATUS_MSG, this.c);
        }
        this.d.setResult(1, intent);
        this.d.finish();
    }
}
