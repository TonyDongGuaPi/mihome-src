package com.youpin.weex.app.module;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.WXPageActivity;

public class WXEventModule extends WXModule {
    @JSMethod(uiThread = true)
    public void openURL(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            if (scheme.equals("http") || scheme.equals("https") || scheme.equals("file")) {
                Intent intent = new Intent(this.mWXSDKInstance.getContext(), WXPageActivity.class);
                intent.setData(parse);
                this.mWXSDKInstance.getContext().startActivity(intent);
                return;
            }
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(parse);
            this.mWXSDKInstance.getContext().startActivity(intent2);
        }
    }
}
