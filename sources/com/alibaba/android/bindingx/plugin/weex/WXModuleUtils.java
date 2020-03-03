package com.alibaba.android.bindingx.plugin.weex;

import android.support.annotation.Nullable;
import android.view.View;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;

public class WXModuleUtils {
    private WXModuleUtils() {
    }

    @Nullable
    public static View a(@Nullable String str, @Nullable String str2) {
        WXComponent b = b(str, str2);
        if (b == null) {
            return null;
        }
        return b.getHostView();
    }

    @Nullable
    public static WXComponent b(@Nullable String str, @Nullable String str2) {
        return WXSDKManager.getInstance().getWXRenderManager().getWXComponent(str, str2);
    }
}
