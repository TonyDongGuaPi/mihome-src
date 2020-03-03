package com.unionpay.mobile.android.pro.views;

import android.content.Intent;
import android.view.View;
import com.miui.tsmclient.util.Constants;
import com.unionpay.mobile.android.plugin.BaseActivity;

final class q implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k f9696a;

    q(k kVar) {
        this.f9696a = kVar;
    }

    public final void onClick(View view) {
        ((BaseActivity) this.f9696a.d).startActivityForResult(new Intent(Constants.INTENT_ACTION_NFC_SETTINGS), 0);
    }
}
