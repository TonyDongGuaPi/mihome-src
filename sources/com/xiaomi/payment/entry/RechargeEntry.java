package com.xiaomi.payment.entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.data.AnalyticsConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.IEntry;
import com.xiaomi.payment.ui.PadFixedWidthActivity;
import com.xiaomi.payment.ui.PhoneRechargeDialogActivity;
import com.xiaomi.payment.ui.fragment.recharge.RechargeGridFragment;
import java.util.HashMap;
import java.util.Map;

public class RechargeEntry implements IEntry {
    public String a() {
        return MibiConstants.eX;
    }

    public boolean a(Context context) {
        return true;
    }

    public void a(IEntry.ContextEnterInterface contextEnterInterface, Bundle bundle, int i) {
        Intent intent = new Intent();
        intent.setClass(contextEnterInterface.a(), b());
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtra("fragment", RechargeGridFragment.class.getName());
        intent.putExtra("payment_fragment_arguments", bundle);
        contextEnterInterface.a(intent, i);
        String string = bundle.getString("miref", "");
        if (!TextUtils.isEmpty(string)) {
            HashMap hashMap = new HashMap();
            hashMap.put("miref", string);
            hashMap.put(AnalyticsConstants.bH, "recharge_entry");
            MistatisticUtils.a(AnalyticsConstants.bE, AnalyticsConstants.bF, (Map<String, String>) hashMap);
        }
    }

    private Class<? extends BaseActivity> b() {
        return Utils.b() ? PadFixedWidthActivity.class : PhoneRechargeDialogActivity.class;
    }
}
