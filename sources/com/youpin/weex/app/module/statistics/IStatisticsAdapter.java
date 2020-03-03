package com.youpin.weex.app.module.statistics;

import com.taobao.weex.bridge.JSCallback;
import java.util.Map;

public interface IStatisticsAdapter {
    void reportCached(boolean z, JSCallback jSCallback);

    void stat(String str, String str2, String str3, String str4, JSCallback jSCallback);

    void stat3(String str, String str2, String str3, String str4, Map<String, String> map, JSCallback jSCallback);
}
