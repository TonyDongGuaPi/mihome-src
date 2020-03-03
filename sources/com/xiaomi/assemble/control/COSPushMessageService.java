package com.xiaomi.assemble.control;

import android.content.Context;
import android.util.Log;
import com.coloros.mcssdk.PushService;
import com.coloros.mcssdk.mode.AppMessage;
import com.coloros.mcssdk.mode.SptDataMessage;
import com.xiaomi.mipush.sdk.COSPushHelper;

public class COSPushMessageService extends PushService {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9996a = "COS_PUSH";

    public void processMessage(Context context, AppMessage appMessage) {
        super.processMessage(context, appMessage);
        String content = appMessage.getContent();
        Log.v(f9996a, "pt  " + content + "");
    }

    public void processMessage(Context context, SptDataMessage sptDataMessage) {
        super.processMessage(context, sptDataMessage);
        String content = sptDataMessage.getContent();
        Log.v(f9996a, "tc   " + content + "");
        COSPushHelper.c(context, content);
    }
}
