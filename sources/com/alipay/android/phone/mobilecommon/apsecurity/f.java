package com.alipay.android.phone.mobilecommon.apsecurity;

import android.content.Context;
import com.alipay.deviceid.DeviceTokenClient;

public class f {
    public static void a(Context context, e eVar) {
        DeviceTokenClient.getInstance(context).initToken("zorro", "elBwppCSr9nB1LIQ", new g(eVar));
    }
}
