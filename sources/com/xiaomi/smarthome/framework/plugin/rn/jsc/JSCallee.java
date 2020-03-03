package com.xiaomi.smarthome.framework.plugin.rn.jsc;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.taobao.weex.annotation.JSMethod;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class JSCallee extends BaseJavaModule {
    private AtomicLong callId = new AtomicLong(1000);
    private Map<String, JSCallback> callbackMap = new ConcurrentHashMap();

    public String getName() {
        return "JSCallee";
    }

    JSCallee() {
    }

    public String add(JSCallback jSCallback) {
        String str = JSMethod.NOT_SET + this.callId.incrementAndGet();
        this.callbackMap.put(str, jSCallback);
        return str;
    }

    public boolean checkInvokedAndRemove(String str) {
        return this.callbackMap.remove(str) == null;
    }

    @ReactMethod
    public void receiveResult(String str, String str2, String str3) {
        JSCallback remove = this.callbackMap.remove(str);
        if (remove == null) {
            return;
        }
        if (str2 == null || str2.length() <= 0) {
            remove.a(str3);
        } else {
            remove.b(str2);
        }
    }
}
