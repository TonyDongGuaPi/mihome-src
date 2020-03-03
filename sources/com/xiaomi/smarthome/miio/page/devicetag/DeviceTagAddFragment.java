package com.xiaomi.smarthome.miio.page.devicetag;

import com.xiaomi.smarthome.R;
import java.util.Set;

public class DeviceTagAddFragment extends DeviceTagFragment {
    public void a(String str) {
        if (this.h != null && (this.h instanceof DeviceTagAddAdapter)) {
            ((DeviceTagAddAdapter) this.h).a(str);
            M_();
        }
    }

    /* access modifiers changed from: protected */
    public DeviceTagAdapter a() {
        if (this.g != null) {
            this.g.setBackgroundColor(getResources().getColor(R.color.std_main_bg));
        }
        return new DeviceTagAddAdapter(getActivity(), getTag());
    }

    public void b(String str) {
        if (this.h != null && (this.h instanceof DeviceTagAddAdapter)) {
            ((DeviceTagAddAdapter) this.h).b(str);
        }
    }

    public String b() {
        if (this.h == null || !(this.h instanceof DeviceTagAddAdapter)) {
            return null;
        }
        return ((DeviceTagAddAdapter) this.h).h();
    }

    public Set<String> c() {
        if (this.h == null || !(this.h instanceof DeviceTagAddAdapter)) {
            return null;
        }
        return ((DeviceTagAddAdapter) this.h).i();
    }
}
