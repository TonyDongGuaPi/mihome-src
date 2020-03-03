package com.youpin.weex.app.module.payment;

import com.taobao.weex.bridge.JSCallback;
import java.util.Map;

public interface IWXPayAdapter {
    void getSupportPayList(JSCallback jSCallback);

    void pay(String str, Map<String, Object> map, JSCallback jSCallback);
}
