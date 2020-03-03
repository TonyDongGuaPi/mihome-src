package com.xiaomi.smarthome.framework.push;

import android.content.Context;
import android.os.Bundle;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.ApplicationLifeCycle;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.FamilyActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import com.xiaomi.smarthome.miio.page.MessageCenterListActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyPushManager extends ApplicationLifeCycle {
    private static FamilyPushManager b;

    /* renamed from: a  reason: collision with root package name */
    private PushListener f1545a = new PushListener() {
        public boolean a(String str, String str2) {
            try {
                FamilyPushManager.this.a(new JSONObject(str2).optString("action"));
                return true;
            } catch (JSONException unused) {
                return true;
            }
        }

        public boolean b(String str, String str2) {
            try {
                FamilyPushManager.this.a(new JSONObject(str2).optString("action"));
                return true;
            } catch (JSONException unused) {
                return true;
            }
        }
    };

    public static FamilyPushManager a() {
        if (b == null) {
            b = new FamilyPushManager();
        }
        return b;
    }

    private FamilyPushManager() {
    }

    public void b() {
        super.b();
        SHApplication.getPushManager().a(PushType.ACCEPT_RELATION, this.f1545a);
        SHApplication.getPushManager().a(PushType.ADD_RELATION, this.f1545a);
        SHApplication.getPushManager().a(PushType.DENY_RELATION, this.f1545a);
        SHApplication.getPushManager().a(PushType.REVOKE_RELATION, this.f1545a);
        SHApplication.getPushManager().a(PushType.ADD_RELATION_MSG, this.f1545a);
        SHApplication.getPushManager().a(PushType.DENY_RELATION_MSG, this.f1545a);
        SHApplication.getPushManager().a(PushType.ACCEPT_RELATION_MSG, this.f1545a);
        SHApplication.getPushManager().a(PushType.REVOKE_RELATION_MSG, this.f1545a);
    }

    public void a(String str) {
        if (str.equalsIgnoreCase("addRelation_request")) {
            Bundle bundle = new Bundle();
            bundle.putInt("message_type", 5);
            bundle.putString(MessageCenterListActivity.INTENT_KEY_TITLE, SHApplication.getAppContext().getString(R.string.family_invitation));
            OpenApi.a(MessageCenterListActivity.class, bundle, true, 0);
        } else if (str.equalsIgnoreCase("acceptRelation_request") || str.equalsIgnoreCase("denyRelation_request") || str.equalsIgnoreCase("revokeRelation_request")) {
            OpenApi.a(FamilyActivity.class, (Bundle) null, true, 0);
        } else if (str.equalsIgnoreCase(PushType.ADD_RELATION_MSG.getValue()) || str.equalsIgnoreCase(PushType.DENY_RELATION_MSG.getValue()) || str.equalsIgnoreCase(PushType.ACCEPT_RELATION_MSG.getValue()) || str.equalsIgnoreCase(PushType.REVOKE_RELATION_MSG.getValue())) {
            MessageCenter a2 = MessageCenter.a();
            a2.b();
            Context appContext = SHApplication.getAppContext();
            a2.a(PreferenceUtils.b(appContext, ProfileRedPointManager.d + CoreApi.a().s(), System.currentTimeMillis()), 2);
            Context appContext2 = SHApplication.getAppContext();
            a2.a(PreferenceUtils.b(appContext2, ProfileRedPointManager.e + CoreApi.a().s(), System.currentTimeMillis()), 1);
        }
    }
}
