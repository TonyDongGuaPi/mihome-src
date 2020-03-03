package com.youpin.weex.app;

import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.taobao.weex.appfram.navigator.IActivityNavBarSetter;
import com.taobao.weex.utils.WXLogUtils;
import com.youpin.weex.app.common.WXAppStoreApiManager;

public class YPCustomNavBarSetter implements IActivityNavBarSetter {
    public boolean clearNavBarLeftItem(String str) {
        return false;
    }

    public boolean clearNavBarMoreItem(String str) {
        return false;
    }

    public boolean clearNavBarRightItem(String str) {
        return false;
    }

    public boolean pop(String str) {
        return false;
    }

    public boolean setNavBarLeftItem(String str) {
        return false;
    }

    public boolean setNavBarMoreItem(String str) {
        return false;
    }

    public boolean setNavBarRightItem(String str) {
        return false;
    }

    public boolean setNavBarTitle(String str) {
        return false;
    }

    public boolean push(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                String string = JSON.parseObject(str).getString("url");
                if (!TextUtils.isEmpty(string)) {
                    Uri parse = Uri.parse(string);
                    String scheme = parse.getScheme();
                    Uri.Builder buildUpon = parse.buildUpon();
                    if (!TextUtils.isEmpty(scheme)) {
                        buildUpon.scheme("http");
                        WXAppStoreApiManager.b().c().a(parse.toString());
                        return true;
                    }
                }
            } catch (Exception e) {
                WXLogUtils.eTag("ContentValues", e);
                return false;
            }
        }
        return false;
    }
}
