package com.xiaomi.smarthome.homeroom;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\t\u001a\u00020\nJ \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\u0010\u001a\u00020\u00072\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0012H\u0002¨\u0006\u0013"}, d2 = {"Lcom/xiaomi/smarthome/homeroom/DeviceListAssemble;", "", "()V", "getCategoryDeviceList", "", "Lcom/xiaomi/smarthome/device/Device;", "name", "", "getDeviceList", "pageBean", "Lcom/xiaomi/smarthome/newui/widget/topnavi/PageBean;", "getRoomDeviceList", "roomId", "room", "Lcom/xiaomi/smarthome/homeroom/model/Room;", "reorderCategory", "categoryName", "set", "", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class DeviceListAssemble {

    /* renamed from: a  reason: collision with root package name */
    public static final DeviceListAssemble f17943a = new DeviceListAssemble();

    private DeviceListAssemble() {
    }

    @NotNull
    public final List<Device> a(@NotNull PageBean pageBean) {
        Intrinsics.f(pageBean, "pageBean");
        if (pageBean.h) {
            String str = pageBean.e;
            Intrinsics.b(str, "pageBean.name");
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (Object next : DeviceListAssembleKt.a((List<? extends Device>) DeviceListAssembleKt.a(a(str)), pageBean)) {
                if (hashSet.add(((Device) next).did)) {
                    arrayList.add(next);
                }
            }
            return arrayList;
        }
        String str2 = pageBean.f;
        Intrinsics.b(str2, "pageBean.id");
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object next2 : DeviceListAssembleKt.a((List<? extends Device>) DeviceListAssembleKt.a(a(str2, pageBean.g)), pageBean)) {
            if (hashSet2.add(((Device) next2).did)) {
                arrayList2.add(next2);
            }
        }
        return arrayList2;
    }

    private final List<Device> a(String str, Room room) {
        if (TextUtils.isEmpty(str)) {
            return CollectionsKt.a();
        }
        int hashCode = str.hashCode();
        if (hashCode != -2077299665) {
            if (hashCode != -252753263) {
                if (hashCode != 491886639) {
                    if (hashCode == 1189320177 && str.equals(HomeManager.h)) {
                        HomeManager a2 = HomeManager.a();
                        Intrinsics.b(a2, "HomeManager.getInstance()");
                        List<GridViewData> F = a2.F();
                        Intrinsics.b(F, "HomeManager.getInstance().commonUseDevices");
                        Iterable<GridViewData> iterable = F;
                        Collection arrayList = new ArrayList(CollectionsKt.a(iterable, 10));
                        for (GridViewData gridViewData : iterable) {
                            arrayList.add(gridViewData.b);
                        }
                        return DeviceListAssembleKt.a((List<? extends Device>) (List) arrayList, "commonUseDevices");
                    }
                } else if (str.equals(HomeManager.e)) {
                    MultiHomeDeviceManager a3 = MultiHomeDeviceManager.a();
                    Intrinsics.b(a3, "MultiHomeDeviceManager.getInstance()");
                    List<Device> e = a3.e();
                    Intrinsics.b(e, "MultiHomeDeviceManager.g…nstance().shareDeviceList");
                    return e;
                }
            } else if (str.equals(HomeManager.d)) {
                HomeManager a4 = HomeManager.a();
                Intrinsics.b(a4, "HomeManager.getInstance()");
                List<Device> j = a4.j();
                Intrinsics.b(j, "HomeManager.getInstance().defaultRoomDeviceList");
                return j;
            }
        } else if (str.equals(HomeManager.f)) {
            MultiHomeDeviceManager a5 = MultiHomeDeviceManager.a();
            Intrinsics.b(a5, "MultiHomeDeviceManager.getInstance()");
            List<Device> g = a5.g();
            Intrinsics.b(g, "MultiHomeDeviceManager.g…stance().nearbyDeviceList");
            return g;
        }
        List<Device> c = HomeManager.a().c(room);
        Intrinsics.b(c, "HomeManager.getInstance().getRoomDeviceList(room)");
        return c;
    }

    private final List<Device> a(String str) {
        SmartHomeDeviceHelper a2 = SmartHomeDeviceHelper.a();
        Intrinsics.b(a2, "SmartHomeDeviceHelper.getInstance()");
        DeviceTagInterface<Device> b = a2.b();
        HomeManager a3 = HomeManager.a();
        Intrinsics.b(a3, "HomeManager.getInstance()");
        Collection arrayList = new ArrayList();
        for (String b2 : a(str, b.e(a3.l(), str))) {
            Device b3 = SmartHomeDeviceManager.a().b(b2);
            if (b3 != null) {
                arrayList.add(b3);
            }
        }
        return (List) arrayList;
    }

    private final List<String> a(String str, Set<String> set) {
        if (set == null || set.isEmpty()) {
            return CollectionsKt.a();
        }
        try {
            SmartHomeDeviceHelper a2 = SmartHomeDeviceHelper.a();
            Intrinsics.b(a2, "SmartHomeDeviceHelper.getInstance()");
            List<String> a3 = HomeManager.a().a((String) null, a2.b().d(str).f15435a);
            if (a3 != null) {
                if (!a3.isEmpty()) {
                    ArrayList arrayList = new ArrayList(set);
                    ArrayList arrayList2 = new ArrayList();
                    int size = a3.size();
                    for (int i = 0; i < size; i++) {
                        String str2 = a3.get(i);
                        if (!TextUtils.isEmpty(str2)) {
                            if (arrayList.contains(str2)) {
                                arrayList2.add(str2);
                                arrayList.remove(str2);
                            }
                        }
                    }
                    arrayList2.addAll(0, arrayList);
                    return arrayList2;
                }
            }
            return CollectionsKt.r(set);
        } catch (Exception e) {
            e.printStackTrace();
            return CollectionsKt.a();
        }
    }
}
