package com.xiaomi.smarthome.framework.redpoint;

import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class RedPointManagerNew {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17680a = "red_point_device_page";
    public static final String b = "red_point_shop_page";
    public static final String c = "red_point_community_page";
    public static final String d = "red_point_shop_page";
    public static final String e = "red_point_setting_page";
    public static final String f = "red_point_message_center";
    public static final String g = "red_point_consumables";
    public static final String h = "loc_setting_btn_help";
    public static final String i = "loc_setting_btn_detail_setting";
    public static final String j = "loc_setting_btn_check_update";
    public static final String k = "loc_voice_control";
    private static RedPointManagerNew n;
    private RedPointController l;
    private RedPointController m;
    private Map<String, List<RedPointListener>> o = new WeakHashMap();
    /* access modifiers changed from: private */
    public Map<String, RedPointAction> p = new WeakHashMap();

    public interface RedPointAction {
        void showRedPoint(boolean z);
    }

    public interface RedPointListener {
        void a();

        boolean a(String str);

        void b(String str);
    }

    public static RedPointManagerNew a() {
        if (n == null) {
            n = new RedPointManagerNew();
        }
        return n;
    }

    private RedPointManagerNew() {
    }

    public Map<String, RedPointAction> b() {
        return this.p;
    }

    public void a(String str, RedPointAction redPointAction) {
        this.p.put(str, redPointAction);
        d(str);
    }

    public void a(String str) {
        this.p.remove(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r3.o.get(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d(final java.lang.String r4) {
        /*
            r3 = this;
            java.util.Map<java.lang.String, java.util.List<com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew$RedPointListener>> r0 = r3.o
            boolean r0 = r0.containsKey(r4)
            if (r0 == 0) goto L_0x001f
            java.util.Map<java.lang.String, java.util.List<com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew$RedPointListener>> r0 = r3.o
            java.lang.Object r0 = r0.get(r4)
            java.util.List r0 = (java.util.List) r0
            if (r0 != 0) goto L_0x0013
            return
        L_0x0013:
            android.os.Handler r1 = com.xiaomi.smarthome.application.SHApplication.getGlobalHandler()
            com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew$1 r2 = new com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew$1
            r2.<init>(r0, r4)
            r1.post(r2)
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew.d(java.lang.String):void");
    }

    public void a(String str, RedPointListener redPointListener) {
        List list = this.o.get(str);
        if (list == null) {
            list = new ArrayList();
        }
        list.add(redPointListener);
        this.o.put(str, list);
        d(str);
    }

    public void b(String str, RedPointListener redPointListener) {
        List list = this.o.get(str);
        if (list != null) {
            list.remove(redPointListener);
        }
        d(str);
    }

    public void b(String str) {
        d(str);
    }

    public void c(String str) {
        if (this.o.containsKey(str)) {
            for (RedPointListener b2 : this.o.get(str)) {
                b2.b(str);
            }
        }
        d(str);
    }

    public void c() {
        LogUtil.a(OpenApi.e, "clearRedPointListener");
        this.p.clear();
        this.o.clear();
        n = null;
    }
}
