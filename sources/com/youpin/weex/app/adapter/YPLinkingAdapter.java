package com.youpin.weex.app.adapter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.taobao.weex.bridge.JSCallback;
import com.xiaomi.youpin.log.LogUtils;
import com.youpin.weex.app.common.ModuleUtils;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.module.linking.ILinkingAdapter;

public class YPLinkingAdapter implements ILinkingAdapter {
    public void openURL(String str, JSCallback jSCallback) {
        LogUtils.e("------------", "openUrl" + str);
        if (str == null || str.equals("")) {
            ModuleUtils.b("url is null", jSCallback);
            return;
        }
        try {
            Activity f = WXAppStoreApiManager.b().f();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            String packageName = WXAppStoreApiManager.b().d().getPackageName();
            ComponentName resolveActivity = intent.resolveActivity(WXAppStoreApiManager.b().d().getPackageManager());
            String packageName2 = resolveActivity != null ? resolveActivity.getPackageName() : "";
            if (f == null || !packageName.equals(packageName2)) {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
            }
            if (f != null) {
                f.startActivity(intent);
            } else {
                WXAppStoreApiManager.b().d().startActivity(intent);
            }
            ModuleUtils.a("open url:" + str + "successfully", jSCallback);
        } catch (Exception e) {
            ModuleUtils.b(e.toString(), jSCallback);
        }
    }

    public void canOpenURL(String str, JSCallback jSCallback) {
        if (str == null || str.equals("")) {
            ModuleUtils.b("url is null", jSCallback);
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            if (intent.resolveActivity(WXAppStoreApiManager.b().d().getPackageManager()) != null) {
                ModuleUtils.a("can open url:" + str, jSCallback);
                return;
            }
            ModuleUtils.b("can not open url", jSCallback);
        } catch (Exception e) {
            ModuleUtils.b(e.toString(), jSCallback);
        }
    }
}
