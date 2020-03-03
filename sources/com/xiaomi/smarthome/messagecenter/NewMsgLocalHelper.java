package com.xiaomi.smarthome.messagecenter;

import com.xiaomi.smarthome.messagecenter.DevicePushMessageManager;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NewMsgLocalHelper {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Boolean> f10466a = new ConcurrentHashMap();

    public static void a() {
        f10466a.clear();
    }

    public static void a(List<IMessage> list) {
        for (IMessage next : list) {
            if ((next instanceof DevicePushMessageManager.DevicePushMessage) && next.h()) {
                f10466a.put(next.c(), true);
            }
        }
    }

    public static void a(IMessage iMessage) {
        if ((iMessage instanceof DevicePushMessageManager.DevicePushMessage) && iMessage.h()) {
            f10466a.put(iMessage.c(), true);
        }
    }

    public static void a(String str) {
        f10466a.remove(str);
    }

    public static boolean b(String str) {
        return f10466a.containsKey(str);
    }
}
