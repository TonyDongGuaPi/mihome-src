package com.xiaomi.smarthome.share;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.ApplicationLifeCycle;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.FamilyActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.framework.push.PushType;
import com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import com.xiaomi.smarthome.miio.page.MessageCenterListActivity;
import org.json.JSONObject;

public class ShareManager extends ApplicationLifeCycle {
    private static ShareManager b;

    /* renamed from: a  reason: collision with root package name */
    private PushListener f1569a = new PushListener() {
        public boolean a(String str, String str2) {
            try {
                ShareManager.this.a(new JSONObject(str2).optString("action"));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }

        public boolean b(String str, String str2) {
            try {
                ShareManager.this.a(new JSONObject(str2).optString("action"));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
    };

    public static ShareManager a() {
        if (b == null) {
            b = new ShareManager();
        }
        return b;
    }

    private ShareManager() {
    }

    public void b() {
        super.b();
        SHApplication.getPushManager().a(PushType.SHARE, this.f1569a);
    }

    public void a(String str) {
        if (TextUtils.equals("share_request", str) || TextUtils.equals("share_to_family_request", str) || TextUtils.equals("share_response", str)) {
            Bundle bundle = new Bundle();
            bundle.putInt("message_type", 1);
            bundle.putString(MessageCenterListActivity.INTENT_KEY_TITLE, SHApplication.getAppContext().getString(R.string.miio_setting_share));
            OpenApi.a(MessageCenterListActivity.class, bundle, true, 0);
        } else if (TextUtils.equals("share_message", str) || TextUtils.equals("share_to_family_message", str)) {
            SHApplication application = SHApplication.getApplication();
            if (application != null && application.isAppForeground()) {
                MessageCenter a2 = MessageCenter.a();
                a2.b();
                Context appContext = SHApplication.getAppContext();
                a2.a(PreferenceUtils.b(appContext, ProfileRedPointManager.d + CoreApi.a().s(), System.currentTimeMillis()), 2);
                Context appContext2 = SHApplication.getAppContext();
                a2.a(PreferenceUtils.b(appContext2, ProfileRedPointManager.e + CoreApi.a().s(), System.currentTimeMillis()), 1);
            }
        } else if (str.equalsIgnoreCase("invalidate_request")) {
            OpenApi.a(FamilyActivity.class, (Bundle) null, true, 0);
        }
    }
}
