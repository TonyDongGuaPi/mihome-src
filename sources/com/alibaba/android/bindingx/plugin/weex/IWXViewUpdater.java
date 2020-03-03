package com.alibaba.android.bindingx.plugin.weex;

import android.support.annotation.NonNull;
import android.view.View;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.taobao.weex.ui.component.WXComponent;
import java.util.Map;

public interface IWXViewUpdater {
    void a(@NonNull WXComponent wXComponent, @NonNull View view, @NonNull Object obj, @NonNull PlatformManager.IDeviceResolutionTranslator iDeviceResolutionTranslator, @NonNull Map<String, Object> map);
}
