package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.os.Bundle;
import com.xiaomi.smarthome.framework.corereceiver.ActivityListener;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class BaseWhiteActivity extends CommonActivity implements ActivityListener {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TitleBarUtil.b((Activity) this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ViewUtils.b((Activity) this);
        ViewUtils.a((Activity) this);
    }
}
