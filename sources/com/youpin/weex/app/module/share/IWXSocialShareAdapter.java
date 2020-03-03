package com.youpin.weex.app.module.share;

import com.taobao.weex.bridge.JSCallback;
import java.util.Map;
import org.json.JSONException;

public interface IWXSocialShareAdapter {
    void getSupportShareList(JSCallback jSCallback);

    void share(Map<String, Object> map, JSCallback jSCallback) throws JSONException;

    void shareCustom(Map<String, Object> map, JSCallback jSCallback) throws JSONException;
}
