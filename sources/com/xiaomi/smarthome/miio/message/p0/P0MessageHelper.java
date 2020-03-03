package com.xiaomi.smarthome.miio.message.p0;

import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.miio.message.p0.model.P0MessageList;

public class P0MessageHelper {
    public static void a(final CommonActivity commonActivity) {
        if (commonActivity != null && commonActivity.isValid()) {
            RemoteFamilyApi.a().i(new AsyncCallback<P0MessageList, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(P0MessageList p0MessageList) {
                    if (p0MessageList != null && p0MessageList.a() != null && !p0MessageList.a().isEmpty() && commonActivity.isValid()) {
                        P0MessageAlertActivity.startActivity(commonActivity, p0MessageList);
                    }
                }
            });
        }
    }
}
