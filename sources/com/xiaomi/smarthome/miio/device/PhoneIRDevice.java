package com.xiaomi.smarthome.miio.device;

import android.content.Context;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.IRRemoteInfo;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthomedevice.R;
import java.util.List;
import org.json.JSONObject;

public class PhoneIRDevice extends MiioDeviceV2 {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13562a = DeviceUtils.a();

    public boolean a(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
    }

    public boolean b(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        return true;
    }

    /* access modifiers changed from: protected */
    public JSONObject c() {
        return null;
    }

    public boolean isOwner() {
        return true;
    }

    public PhoneIRDevice() {
        this.did = f13562a;
        this.model = IRDeviceUtil.a();
        this.canAuth = false;
        setOwner(true);
        this.isOnline = true;
    }

    public PhoneIRDevice(String str, String str2) {
        super(str, str2);
    }

    public CharSequence getStatusDescription(Context context) {
        int i;
        if (CoreApi.a().c(this.model)) {
            List<IRRemoteInfo> b = IRDeviceUtil.b(context);
            if (b == null) {
                i = 0;
            } else {
                i = b.size();
            }
        } else {
            i = IRDeviceUtil.d(context);
        }
        if (i <= 0) {
            return context.getString(R.string.my_phone_ir_desc_empty);
        }
        return context.getResources().getQuantityString(R.plurals.my_phone_ir_desc_count, i, new Object[]{Integer.valueOf(i)});
    }
}
