package com.xiaomi.smarthome.notificationquickop;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.coloros.mcssdk.PushManager;
import com.xiaomi.smarthome.application.SHApplication;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class NotiQuickOpManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20964a = "quick_op_data_v2_";
    private static NotiQuickOpManager b;
    private Context c;
    private BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "action_on_logout")) {
                NotiQuickOpManager.this.b();
            }
        }
    };

    private NotiQuickOpManager(Context context) {
        this.c = context.getApplicationContext();
    }

    public static NotiQuickOpManager a(Context context) {
        if (b == null) {
            synchronized (NotiQuickOpManager.class) {
                if (b == null) {
                    b = new NotiQuickOpManager(context);
                }
            }
        }
        return b;
    }

    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.d, intentFilter);
    }

    public void b() {
        ((NotificationManager) this.c.getSystemService(PushManager.MESSAGE_TYPE_NOTI)).cancelAll();
    }

    public static class QuickOpItem {

        /* renamed from: a  reason: collision with root package name */
        public String f20966a;
        public String b;
        public Map<String, ItemState> c = new HashMap();

        public static class ItemState {

            /* renamed from: a  reason: collision with root package name */
            public static final int f20967a = 0;
            public static final int b = 1;
            public String c;
            public int d = 0;
        }

        public static JSONObject a(QuickOpItem quickOpItem) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", quickOpItem.f20966a);
                jSONObject.put("tag", quickOpItem.b);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        public static QuickOpItem a(JSONObject jSONObject) {
            QuickOpItem quickOpItem = new QuickOpItem();
            quickOpItem.f20966a = jSONObject.optString("did");
            quickOpItem.b = jSONObject.optString("tag");
            return quickOpItem;
        }

        public int hashCode() {
            return TextUtils.isEmpty(this.b) ? super.hashCode() : this.b.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof QuickOpItem)) {
                return false;
            }
            return TextUtils.isEmpty(this.b) ? super.equals(obj) : this.b.equals(((QuickOpItem) obj).b);
        }
    }
}
