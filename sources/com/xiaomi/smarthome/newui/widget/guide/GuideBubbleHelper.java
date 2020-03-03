package com.xiaomi.smarthome.newui.widget.guide;

import android.graphics.PointF;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.DeviceInitChecker;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.stat.STAT;
import java.util.HashSet;
import java.util.List;

public class GuideBubbleHelper {
    public static GuideBubbleManager a(DeviceMainPage deviceMainPage, String str) {
        FragmentActivity activity;
        if (deviceMainPage == null || TextUtils.isEmpty(str) || (activity = deviceMainPage.getActivity()) == null) {
            return null;
        }
        GuideBubbleManager guideBubbleManager = new GuideBubbleManager(activity);
        guideBubbleManager.a(new PointF(), 81, str);
        guideBubbleManager.a(false);
        guideBubbleManager.a((GuideNextStepCallback) new GuideNextStepCallback() {
            public void a() {
            }

            public void a(int i) {
            }
        });
        guideBubbleManager.c();
        a();
        return guideBubbleManager;
    }

    private static void a() {
        try {
            List<String> list = DeviceInitChecker.f17939a;
            HashSet<String> hashSet = new HashSet<>();
            for (int i = 0; i < list.size(); i++) {
                Device b = SmartHomeDeviceManager.a().b(list.get(i));
                if (b != null) {
                    hashSet.add(b.model);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String str : hashSet) {
                sb.append(str + i.b);
            }
            STAT.e.a(sb.toString(), list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
