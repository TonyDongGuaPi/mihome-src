package com.xiaomi.assemble.control;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.huawei.hms.support.api.push.PushReceiver;
import com.xiaomi.mipush.sdk.HWPushHelper;

public class HmsPushReceiver extends PushReceiver {
    public static final String TAG = "HmsPushReceiver";

    public boolean onPushMsg(Context context, byte[] bArr, Bundle bundle) {
        try {
            String str = new String(bArr, "UTF-8");
            Log.i(TAG, "Hms get pass though message! ");
            HWPushHelper.b(context, str);
            return true;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return true;
        }
    }

    public void onEvent(Context context, PushReceiver.Event event, Bundle bundle) {
        Log.i(TAG, "Hms get notification clicked !");
        HWPushHelper.a(context, bundle.getString(PushReceiver.BOUND_KEY.pushMsgKey));
    }

    public void onToken(Context context, String str, Bundle bundle) {
        Log.i(TAG, "Hms token get success ! token = " + str);
        HWPushHelper.c(context, str);
    }
}
