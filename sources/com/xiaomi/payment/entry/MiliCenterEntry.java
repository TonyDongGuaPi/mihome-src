package com.xiaomi.payment.entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.IEntry;

public class MiliCenterEntry implements IEntry {
    private static final String c = "com.xiaomi.action.VIEW_MILI_CENTER";

    public String a() {
        return MibiConstants.eW;
    }

    public boolean a(Context context) {
        return true;
    }

    public void a(IEntry.ContextEnterInterface contextEnterInterface, Bundle bundle, int i) {
        Intent intent = new Intent(c);
        intent.setPackage(contextEnterInterface.a().getPackageName());
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        contextEnterInterface.a(intent, i);
    }
}
