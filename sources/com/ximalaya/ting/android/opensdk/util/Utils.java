package com.ximalaya.ting.android.opensdk.util;

import android.content.Context;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;

public class Utils {
    public static Context a() {
        if (XmPlayerService.getPlayerSrvice() != null) {
            return XmPlayerService.getPlayerSrvice();
        }
        return CommonRequest.a().c();
    }
}
