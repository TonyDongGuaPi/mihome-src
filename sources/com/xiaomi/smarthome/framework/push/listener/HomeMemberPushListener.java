package com.xiaomi.smarthome.framework.push.listener;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.miio.page.MessageCenterListActivity;
import com.xiaomi.smarthome.newui.MultiHomeManagerActivity;
import org.json.JSONObject;

public class HomeMemberPushListener extends PushListener {
    public boolean a(String str, String str2) {
        a(str2);
        return true;
    }

    public boolean b(String str, String str2) {
        a(str2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        try {
            HomeMemberPushProcessor.a(new JSONObject(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class HomeMemberPushProcessor {

        /* renamed from: a  reason: collision with root package name */
        public static final String f17673a = "invite_tips";
        public static final String b = "invite_request";
        public static final int c = 0;
        public static final int d = 1;
        public static final int e = 2;

        private HomeMemberPushProcessor() {
        }

        public static void a(JSONObject jSONObject) {
            jSONObject.optLong("home_id");
            jSONObject.optLong("home_owner");
            jSONObject.optString("request");
            final long optLong = jSONObject.optLong("share_to");
            jSONObject.optInt("status");
            jSONObject.optString("type");
            if (GlobalSetting.q) {
                Log.d("HomeMemberPushProcessor", jSONObject.toString());
            }
            CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsAccountReadyCallback) new CoreApi.IsAccountReadyCallback() {
                        public void a(boolean z, String str) {
                            if (SHApplication.getStateNotifier().a() == 4) {
                                if (!TextUtils.equals(str, String.valueOf(optLong))) {
                                    Intent intent = new Intent(SHApplication.getAppContext(), MessageCenterListActivity.class);
                                    intent.putExtra("message_type", 8);
                                    intent.putExtra(MessageCenterListActivity.INTENT_KEY_TITLE, SHApplication.getAppContext().getString(R.string.home_share));
                                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                    SHApplication.getAppContext().startActivity(intent);
                                    return;
                                }
                                Intent intent2 = new Intent(SHApplication.getAppContext(), MultiHomeManagerActivity.class);
                                intent2.putExtra("from", 6);
                                intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                                SHApplication.getAppContext().startActivity(intent2);
                            }
                        }
                    });
                }
            });
        }
    }
}
