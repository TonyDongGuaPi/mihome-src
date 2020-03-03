package com.xiaomi.smarthome.homeroom.device_order;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
final class DeviceOrderSource$mHomesListOrder$2 extends Lambda implements Function0<ArrayList<String>> {
    public static final DeviceOrderSource$mHomesListOrder$2 INSTANCE = new DeviceOrderSource$mHomesListOrder$2();

    DeviceOrderSource$mHomesListOrder$2() {
        super(0);
    }

    public final ArrayList<String> invoke() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String f = DeviceOrderSource.b.d();
            DeviceOrderSource.b.a("getCachedHomeListOrder", f);
            UtilKt.a(new JSONArray(f), arrayList, AnonymousClass1.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayList.isEmpty()) {
            UpgradeOrderCompat g = DeviceOrderSource.k;
            Intrinsics.b(g, "mUpgradeOrderCompat");
            if (g.c()) {
                UpgradeOrderCompat g2 = DeviceOrderSource.k;
                Intrinsics.b(g2, "mUpgradeOrderCompat");
                return g2.d();
            }
        }
        return arrayList;
    }
}
