package com.xiaomi.smarthome.scene.push;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public abstract class ScenePushCallback {
    public abstract List<String> getEvents();

    public abstract String getModel();

    public abstract boolean onReceiveMessage(String str);

    public abstract boolean onReceiveNotifiedMessage(String str);

    /* access modifiers changed from: package-private */
    public final boolean isMatch(String str, String str2) {
        boolean z;
        String model = getModel();
        List<String> events = getEvents();
        if (TextUtils.isEmpty(model) || events.isEmpty() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !model.equals(str)) {
            return false;
        }
        Iterator<String> it = events.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().equals(str2)) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            return true;
        }
        return false;
    }
}
