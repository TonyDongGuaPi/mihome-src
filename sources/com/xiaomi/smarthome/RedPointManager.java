package com.xiaomi.smarthome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class RedPointManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13399a = "red_point_device_page";
    public static final String b = "red_point_shop_page";
    public static final String c = "red_point_setting_page";
    private static RedPointManager d;
    private Map<String, List<RedPointListener>> e = new WeakHashMap();
    private Map<String, RedPointAction> f = new WeakHashMap();

    public interface RedPointAction {
        void a(boolean z);
    }

    public interface RedPointListener {
        boolean a();
    }

    public static RedPointManager a() {
        if (d == null) {
            d = new RedPointManager();
        }
        return d;
    }

    private RedPointManager() {
    }

    public void a(String str, RedPointAction redPointAction) {
        this.f.put(str, redPointAction);
    }

    public void a(String str) {
        this.f.remove(str);
    }

    public void a(String str, RedPointListener redPointListener) {
        List list = this.e.get(str);
        if (list == null) {
            list = new ArrayList();
        }
        list.add(redPointListener);
        this.e.put(str, list);
    }

    public void b(String str, RedPointListener redPointListener) {
        List list = this.e.get(str);
        if (list != null) {
            list.remove(redPointListener);
        }
    }

    public void b(String str) {
        boolean z;
        List<RedPointListener> list = this.e.get(str);
        if (list != null) {
            z = false;
            for (RedPointListener a2 : list) {
                z |= a2.a();
            }
        } else {
            z = false;
        }
        if (this.f.get(str) == null) {
            return;
        }
        if (z) {
            this.f.get(str).a(true);
        } else {
            this.f.get(str).a(false);
        }
    }

    public void b() {
        this.f.clear();
        this.e.clear();
    }
}
