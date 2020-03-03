package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.push.bf;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ac {

    /* renamed from: a  reason: collision with root package name */
    private static Object f12869a = new Object();

    /* renamed from: a  reason: collision with other field name */
    private static Map<String, Queue<String>> f270a = new HashMap();

    public static boolean a(XMPushService xMPushService, String str, String str2) {
        synchronized (f12869a) {
            SharedPreferences sharedPreferences = xMPushService.getSharedPreferences("push_message_ids", 0);
            Queue queue = f270a.get(str);
            if (queue == null) {
                String[] split = sharedPreferences.getString(str, "").split(",");
                LinkedList linkedList = new LinkedList();
                for (String add : split) {
                    linkedList.add(add);
                }
                f270a.put(str, linkedList);
                queue = linkedList;
            }
            if (queue.contains(str2)) {
                return true;
            }
            queue.add(str2);
            if (queue.size() > 25) {
                queue.poll();
            }
            String a2 = bf.a((Collection<?>) queue, ",");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(str, a2);
            edit.commit();
            return false;
        }
    }
}
