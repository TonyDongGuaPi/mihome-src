package com.youpin.weex.app.module.cookie;

import com.taobao.weex.bridge.JSCallback;
import java.util.Map;

public interface ICookieAdapter {
    void clearAll(JSCallback jSCallback) throws Exception;

    void clearByName(String str, String str2, JSCallback jSCallback) throws Exception;

    void get(String str, JSCallback jSCallback) throws Exception;

    void getAll(JSCallback jSCallback) throws Exception;

    void getFromResponse(String str, JSCallback jSCallback);

    void set(Map<String, Object> map, JSCallback jSCallback) throws Exception;

    void setFromResponse(String str, Map<String, Object> map, JSCallback jSCallback) throws Exception;
}
