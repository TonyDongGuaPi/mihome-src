package com.xiaomi.smarthome.newui.widget.topnavi;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PageBean {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20911a = "all_type";
    public static final String b = "other_type";
    public static final String c = "special_type";
    public static final String d = "category_type";
    private static final String i = "PageBean";
    public String e = "";
    public String f = "";
    public Room g;
    public boolean h = false;

    public PageBean(String str, String str2) {
        this.e = str;
        this.f = str2;
    }

    public PageBean(Room room) {
        this.e = room.e();
        this.f = room.d();
        this.g = room;
    }

    public static List<Classify> a(Context context, List<Room> list, boolean z) {
        PageBean pageBean = new PageBean(context.getString(R.string.tag_mine), HomeManager.h);
        PageBean pageBean2 = new PageBean(context.getString(R.string.tag_share), HomeManager.e);
        PageBean pageBean3 = new PageBean(context.getString(R.string.nearby_device), HomeManager.f);
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(pageBean);
            List<Device> e2 = MultiHomeDeviceManager.a().e();
            if (e2 != null && e2.size() > 0) {
                arrayList2.add(pageBean2);
            }
            List<Device> g2 = MultiHomeDeviceManager.a().g();
            if (g2 != null && g2.size() > 0) {
                arrayList2.add(pageBean3);
            }
            arrayList.add(new Classify(f20911a, arrayList2));
            if (z) {
                a((ArrayList<Classify>) arrayList);
            }
        }
        return arrayList;
    }

    private static void a(ArrayList<Classify> arrayList) {
        DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
        Map<String, List<String>> j = b2.j(HomeManager.a().l());
        if (j.isEmpty()) {
            LogUtil.a(i, "wrapperCategory: is not ready");
        } else {
            LogUtil.a(i, "wrapperCategory: is ready");
        }
        Set<Map.Entry<String, List<String>>> entrySet = j.entrySet();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry next : entrySet) {
            String str = (String) next.getKey();
            DeviceTagInterface.Category d2 = b2.d(str);
            if (d2 != null && !TextUtils.isEmpty(d2.f15435a) && !TextUtils.isEmpty(d2.d) && next.getValue() != null && ((List) next.getValue()).size() > 0) {
                PageBean pageBean = new PageBean(str, d2.f15435a);
                pageBean.h = true;
                arrayList2.add(pageBean);
            }
        }
        if (arrayList2.size() > 0) {
            arrayList.add(new Classify(d, arrayList2));
        }
    }

    public static List<Classify> a(Context context) {
        PageBean pageBean = new PageBean(context.getString(R.string.tag_mine), HomeManager.h);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(pageBean);
        arrayList2.add(new Classify(f20911a, arrayList));
        return arrayList2;
    }

    public static PageBean a(Room room) {
        return new PageBean(room);
    }

    public static PageBean a() {
        return new PageBean(SHApplication.getAppContext().getString(R.string.tag_mine), HomeManager.h);
    }

    public static PageBean b() {
        return new PageBean(SHApplication.getAppContext().getString(R.string.default_room), HomeManager.d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PageBean pageBean = (PageBean) obj;
        if (this.e == null ? pageBean.e != null : !this.e.equals(pageBean.e)) {
            return false;
        }
        if (this.f != null) {
            return this.f.equals(pageBean.f);
        }
        if (pageBean.f == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = (this.e != null ? this.e.hashCode() : 0) * 31;
        if (this.f != null) {
            i2 = this.f.hashCode();
        }
        return hashCode + i2;
    }

    public static class Classify {

        /* renamed from: a  reason: collision with root package name */
        public String f20912a;
        public List<PageBean> b;

        public Classify(String str, List<PageBean> list) {
            this.f20912a = str;
            this.b = list;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Classify)) {
                return false;
            }
            Classify classify = (Classify) obj;
            if (!TextUtils.equals(this.f20912a, classify.f20912a) || this.b == null || !this.b.equals(classify.b)) {
                return false;
            }
            return true;
        }
    }
}
