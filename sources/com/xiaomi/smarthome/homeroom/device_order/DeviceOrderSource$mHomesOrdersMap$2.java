package com.xiaomi.smarthome.homeroom.device_order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001aB\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00040\u0004 \u0003* \u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Ljava/util/HashMap;", "", "kotlin.jvm.PlatformType", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "invoke"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$mHomesOrdersMap$2 extends Lambda implements Function0<HashMap<String, HomeOrder>> {
    public static final DeviceOrderSource$mHomesOrdersMap$2 INSTANCE = new DeviceOrderSource$mHomesOrdersMap$2();

    DeviceOrderSource$mHomesOrdersMap$2() {
        super(0);
    }

    public final HashMap<String, HomeOrder> invoke() {
        boolean z;
        HomeOrder homeOrder;
        Iterable<String> a2 = DeviceOrderSource.b.b();
        Collection arrayList = new ArrayList(CollectionsKt.a(a2, 10));
        boolean z2 = true;
        for (String str : a2) {
            String c = DeviceOrderSource.b.b(str);
            DeviceOrderSource.b.a("getCachedHomeOrder", c);
            try {
                HomeOrder a3 = DeviceOrderSource.b.a(new JSONObject(c), true);
                z = z2;
                homeOrder = a3;
            } catch (Exception e) {
                z = false;
                e.printStackTrace();
                homeOrder = new HomeOrder(str, true, false, (FrontOrder) null, (ArrayList) null, (HashSet) null, 60, (DefaultConstructorMarker) null);
            }
            arrayList.add(homeOrder);
            z2 = z;
        }
        Map hashMap = new HashMap();
        for (Object next : (List) arrayList) {
            hashMap.put(((HomeOrder) next).a(), next);
        }
        HashMap<String, HomeOrder> hashMap2 = (HashMap) hashMap;
        if (!z2) {
            UpgradeOrderCompat g = DeviceOrderSource.k;
            Intrinsics.b(g, "mUpgradeOrderCompat");
            if (g.c()) {
                UpgradeOrderCompat g2 = DeviceOrderSource.k;
                Intrinsics.b(g2, "mUpgradeOrderCompat");
                HashMap<String, HomeOrder> e2 = g2.e();
                DeviceOrderSource.k.b();
                return e2;
            }
        }
        return hashMap2;
    }
}
