package com.xiaomi.smarthome.framework.push.listener;

import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManagerNew;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataNew;
import org.json.JSONObject;

public class UserBannerPropPushListener extends PushListener {
    public boolean a(String str, String str2) {
        a(str2);
        return true;
    }

    private void a(String str) {
        try {
            TopWidgetDataManagerNew.b().a(TopWidgetDataNew.Detail.a(new JSONObject(str)));
        } catch (Exception unused) {
        }
    }
}
