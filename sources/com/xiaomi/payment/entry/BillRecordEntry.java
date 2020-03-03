package com.xiaomi.payment.entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.IEntry;
import com.xiaomi.payment.ui.fragment.BillRecordFragment;

public class BillRecordEntry implements IEntry {
    public String a() {
        return MibiConstants.eU;
    }

    public boolean a(Context context) {
        return true;
    }

    public void a(IEntry.ContextEnterInterface contextEnterInterface, Bundle bundle, int i) {
        Intent intent = new Intent();
        if (Utils.b()) {
            intent.setClass(contextEnterInterface.a(), PadDialogActivity.class);
        } else {
            intent.setClass(contextEnterInterface.a(), PhoneCommonActivity.class);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtra("payment_fragment_arguments", bundle);
        intent.putExtra("fragment", BillRecordFragment.class.getName());
        contextEnterInterface.a(intent, i);
    }
}
