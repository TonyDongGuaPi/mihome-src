package com.youpin.weex.app.module.netinfo;

import com.taobao.weex.bridge.JSCallback;
import java.util.Map;

public interface INetInfoAdapter {
    void fetch(Map<String, String> map, JSCallback jSCallback);

    void startMonitor(Map<String, String> map, JSCallback jSCallback);

    void stopMonitor(JSCallback jSCallback);
}
