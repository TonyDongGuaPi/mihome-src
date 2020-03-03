package com.xiaomi.smarthome.miio.page.devicetag;

import java.util.List;
import java.util.Set;

public class DeviceTagBatchFragment extends DeviceTagFragment {
    /* access modifiers changed from: protected */
    public DeviceTagAdapter a() {
        return new DeviceTagBatchAdapter(getActivity(), getTag());
    }

    /* access modifiers changed from: protected */
    public void M_() {
        super.M_();
    }

    public void a(List<String> list) {
        if (this.h instanceof DeviceTagBatchAdapter) {
            ((DeviceTagBatchAdapter) this.h).a(list);
            M_();
        }
    }

    public Set<String> c() {
        if (this.h instanceof DeviceTagBatchAdapter) {
            return ((DeviceTagBatchAdapter) this.h).h();
        }
        return null;
    }
}
