package com.xiaomi.smarthome.device;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.DeviceSearch;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MiioSubDeviceSearch extends MiioDeviceSearchBase {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14900a = 3;

    public int g() {
        return 3;
    }

    /* access modifiers changed from: protected */
    public void a(Collection<? extends Device> collection, DeviceSearch.REMOTESTATE remotestate) {
        if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_SUCCESS) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Device device : collection) {
                if ((device instanceof MiioDeviceV2) && !TextUtils.isEmpty(device.parentId)) {
                    if (device.isBinded()) {
                        device.userId = CoreApi.a().s();
                        arrayList.add((MiioDeviceV2) device);
                    } else {
                        arrayList2.add((MiioDeviceV2) device);
                    }
                }
            }
            arrayList.addAll(arrayList2);
            a((List<MiioDeviceV2>) arrayList);
        } else if (remotestate == DeviceSearch.REMOTESTATE.REMOTE_FAILED) {
            a((List<MiioDeviceV2>) null);
        }
    }
}
