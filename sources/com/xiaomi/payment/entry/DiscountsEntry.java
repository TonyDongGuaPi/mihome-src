package com.xiaomi.payment.entry;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.IEntry;
import com.xiaomi.payment.hybrid.MibiHybridUtils;

public class DiscountsEntry implements IEntry {
    public String a() {
        return MibiConstants.eT;
    }

    public boolean a(Context context) {
        return true;
    }

    public void a(IEntry.ContextEnterInterface contextEnterInterface, Bundle bundle, int i) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        String string = bundle.getString("webUrl");
        if (TextUtils.isEmpty(string)) {
            string = MibiConstants.b("activity");
        }
        if (contextEnterInterface.a() instanceof Activity) {
            MibiHybridUtils.a(string, (Activity) contextEnterInterface.a(), i);
        }
    }
}
