package com.miui.tsmclient.pay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.mipay.ucashier.UCashier;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.util.LogUtils;

public class MiPayTool implements IPayTool {
    public int getPayRequestCode() {
        return 810;
    }

    public boolean isSupport(Context context) {
        return true;
    }

    public int pay(Activity activity, String str, Bundle bundle) {
        LogUtils.v("payExtra:" + str + ", bundle:" + bundle);
        UCashier.get(new MipayAccountProvider(activity.getApplicationContext())).pay(activity, str, bundle);
        return 0;
    }

    public int parsePayResult(Context context, CardInfo cardInfo, Bundle bundle) {
        UCashier.release();
        int i = bundle.getInt("code", -1);
        String string = bundle.getString("message");
        LogUtils.d("pay result:" + i + ",msg:" + string);
        return i;
    }
}
