package com.xiaomi.smarthome.homeroom;

import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\b\u0012\u0004\u0012\u00020\u00040\u0003\u001a\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\b\u001a\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"TAG", "", "bringNewDeviceToFront", "", "Lcom/xiaomi/smarthome/device/Device;", "log", "room", "logDeviceOrder", "pageBean", "Lcom/xiaomi/smarthome/newui/widget/topnavi/PageBean;", "app_GooglePlayRelease"}, k = 2, mv = {1, 1, 13})
public final class DeviceListAssembleKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f17944a = "DeviceListAssemble";

    @NotNull
    public static final List<Device> a(@NotNull List<? extends Device> list, @NotNull PageBean pageBean) {
        Intrinsics.f(list, "receiver$0");
        Intrinsics.f(pageBean, "pageBean");
        return list;
    }

    @NotNull
    public static final List<Device> a(@NotNull List<? extends Device> list) {
        Intrinsics.f(list, "receiver$0");
        ArrayList arrayList = (ArrayList) CollectionsKt.a(list, new ArrayList());
        Collection arrayList2 = new ArrayList();
        for (Object next : arrayList) {
            if (((Device) next).isNew) {
                arrayList2.add(next);
            }
        }
        Collection collection = (List) arrayList2;
        arrayList.removeAll(collection);
        arrayList.addAll(0, collection);
        return arrayList;
    }

    @NotNull
    public static final List<Device> a(@NotNull List<? extends Device> list, @NotNull String str) {
        Intrinsics.f(list, "receiver$0");
        Intrinsics.f(str, "room");
        if (GlobalSetting.q || GlobalSetting.u) {
            LogUtilGrey.a(f17944a, "roomName: " + str + " , devices size: " + list.size());
        }
        return list;
    }
}
