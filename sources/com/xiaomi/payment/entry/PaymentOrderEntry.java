package com.xiaomi.payment.entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.payment.PaymentOrderEntryActivity;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.IEntry;

public class PaymentOrderEntry implements IEntry {
    public String a() {
        return "mibi.pay";
    }

    public boolean a(Context context) {
        return true;
    }

    public void a(IEntry.ContextEnterInterface contextEnterInterface, Bundle bundle, int i) {
        Intent intent = new Intent();
        intent.setClass(contextEnterInterface.a(), PaymentOrderEntryActivity.class);
        intent.putExtra(MibiConstants.cS, a(bundle));
        contextEnterInterface.a(intent, i);
    }

    private Bundle a(Bundle bundle) {
        String string = bundle.getString("order", "");
        boolean z = bundle.getBoolean("payment_is_no_account", false);
        boolean z2 = bundle.getBoolean(MibiConstants.gK, false);
        Bundle bundle2 = new Bundle();
        bundle2.putString(MibiConstants.cT, string);
        bundle2.putBoolean("payment_is_no_account", z);
        bundle2.putBoolean(MibiConstants.gK, z2);
        return bundle2;
    }
}
