package com.taobao.weex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.alibaba.fastjson.JSON;
import com.taobao.weex.utils.WXLogUtils;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXGlobalEventReceiver extends BroadcastReceiver {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String EVENT_ACTION = "wx_global_action";
    public static final String EVENT_NAME = "eventName";
    public static final String EVENT_PARAMS = "eventParams";
    public static final String EVENT_WX_INSTANCEID = "wx_instanceid";
    private WXSDKInstance mWXSDKInstance;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3949301347578882467L, "com/taobao/weex/WXGlobalEventReceiver", 9);
        $jacocoData = a2;
        return a2;
    }

    public WXGlobalEventReceiver() {
        $jacocoInit()[0] = true;
    }

    public WXGlobalEventReceiver(WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWXSDKInstance = wXSDKInstance;
        $jacocoInit[1] = true;
    }

    public void onReceive(Context context, Intent intent) {
        boolean[] $jacocoInit = $jacocoInit();
        String stringExtra = intent.getStringExtra(EVENT_NAME);
        $jacocoInit[2] = true;
        String stringExtra2 = intent.getStringExtra(EVENT_PARAMS);
        try {
            $jacocoInit[3] = true;
            $jacocoInit[4] = true;
            this.mWXSDKInstance.fireGlobalEventCallback(stringExtra, (HashMap) JSON.parseObject(stringExtra2, HashMap.class));
            $jacocoInit[5] = true;
        } catch (Exception e) {
            $jacocoInit[6] = true;
            WXLogUtils.e("global-receive", (Throwable) e);
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
    }
}
