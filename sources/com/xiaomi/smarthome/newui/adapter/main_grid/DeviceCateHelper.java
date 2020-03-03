package com.xiaomi.smarthome.newui.adapter.main_grid;

import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.log.LogUtil;
import java.util.HashMap;
import java.util.Map;

public class DeviceCateHelper {

    /* renamed from: a  reason: collision with root package name */
    private static DeviceCateHelper f20420a = new DeviceCateHelper();
    private Map<String, CateType> b = new HashMap();

    public static DeviceCateHelper a() {
        return f20420a;
    }

    /* access modifiers changed from: private */
    public DeviceTagInterface.Category b(Device device) {
        return SmartHomeDeviceHelper.a().b().c(device.model);
    }

    public CateType a(Device device) {
        CateType cateType = this.b.get(device.model);
        if (cateType != null) {
            return cateType;
        }
        for (CateType cateType2 : CateType.values()) {
            if (cateType2.predict(device)) {
                this.b.put(device.model, cateType2);
                return cateType2;
            }
        }
        return null;
    }

    enum CateType {
        FLOWERPOT("23"),
        AIR_MONITOR("40"),
        PLANT_MONITOR(Constant.TRANS_TYPE_LOAD),
        WASHING_MACHINE("61"),
        ELECTRIC_COOKER("87"),
        WEATHER_MONITOR(Constants.Plugin.PLUGINID_ORDER),
        PRESSURE_COOKER("149"),
        DISH_WASHING_MACHINE("153"),
        JUICER("154"),
        STEAMER_ROAST("165"),
        HEALTH_CARE_POT("175");
        
        private static final String TAG = "CateType";
        private String mCateId;

        private CateType(String str) {
            this.mCateId = str;
        }

        /* access modifiers changed from: package-private */
        public boolean predict(Device device) {
            DeviceTagInterface.Category a2 = DeviceCateHelper.a().b(device);
            if (a2 == null) {
                return false;
            }
            LogUtil.a(TAG, "predict: " + a2.d);
            return this.mCateId.equals(a2.f15435a);
        }
    }
}
