package com.xiaomi.smarthome.miio.page;

import com.xiaomi.smarthome.framework.page.TabFragment;

public abstract class BaseClientAllPage extends TabFragment {
    public void e() {
    }

    public abstract void g();

    public abstract void h();

    public void i() {
    }

    public boolean isValid() {
        return isAdded() && !isDetached() && getValidActivity() != null;
    }
}
