package com.xiaomi.smarthome.scenenew;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.devicesubscribe.SubscribeCallback;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.stat.a.j;
import com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SceneStatusManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile SceneStatusManager f21793a = null;
    private static final int b = 180;
    private static final int c = 1;
    /* access modifiers changed from: private */
    public Handler d = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                SceneStatusManager.this.c();
            }
        }
    };
    private List<OnStatusChangeListener> e = new CopyOnWriteArrayList();

    public interface OnStatusChangeListener {
        void a(List<SceneApi.SmartHomeScene> list);
    }

    public static SceneStatusManager a() {
        if (f21793a == null) {
            synchronized (SceneStatusManager.class) {
                if (f21793a == null) {
                    f21793a = new SceneStatusManager();
                }
            }
        }
        return f21793a;
    }

    private SceneStatusManager() {
    }

    public void b() {
        this.d.removeMessages(1);
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("user_scene_switch_changed");
        try {
            jSONObject.put("pushId", SHApplication.getPushManager().g());
            jSONObject.put(j.b, jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/mipush/user_event_unsub").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, (AsyncCallback) null);
    }

    public void c() {
        a(180, new SubscribeCallback() {
            public void a(Error error) {
            }

            public void a(String str, String str2, JSONArray jSONArray) {
            }

            public void a(String str) {
                SceneStatusManager.this.d.removeMessages(1);
                SceneStatusManager.this.d.sendMessageDelayed(SceneStatusManager.this.d.obtainMessage(1), 180000);
            }
        });
    }

    private void a(int i, final SubscribeCallback subscribeCallback) {
        if (subscribeCallback != null) {
            if (SHApplication.getStateNotifier().a() != 4) {
                subscribeCallback.a(new Error(-1, "not logged in"));
            } else if (i <= 0) {
                subscribeCallback.a(new Error(-1, "expire <=0"));
            } else {
                if (i > 180) {
                    i = 180;
                }
                a(SHApplication.getAppContext(), i, new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (jSONObject != null && subscribeCallback != null) {
                            subscribeCallback.a(jSONObject.toString());
                        }
                    }

                    public void onFailure(Error error) {
                        if (subscribeCallback != null) {
                            subscribeCallback.a(error);
                        }
                    }
                });
            }
        }
    }

    private void a(Context context, int i, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(SHApplication.getPushManager().g())) {
            this.d.removeMessages(1);
            this.d.sendEmptyMessageDelayed(1, 500);
            return;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("user_scene_switch_changed");
        try {
            jSONObject.put("pushId", SHApplication.getPushManager().g());
            jSONObject.put("expire", i);
            jSONObject.put(j.b, jSONArray);
            jSONObject.put(OpenSdkPlayStatisticUpload.r, 0);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/v2/mipush/user_event_sub").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void a(List<SceneApi.SmartHomeScene> list) {
        b(list);
    }

    private void b(List<SceneApi.SmartHomeScene> list) {
        for (OnStatusChangeListener next : this.e) {
            if (next != null) {
                next.a(list);
            }
        }
    }

    public void a(OnStatusChangeListener onStatusChangeListener) {
        synchronized (this.e) {
            if (!this.e.contains(onStatusChangeListener)) {
                this.e.add(onStatusChangeListener);
            }
        }
    }

    public void b(OnStatusChangeListener onStatusChangeListener) {
        synchronized (this.e) {
            this.e.remove(onStatusChangeListener);
        }
    }

    private void d() {
        synchronized (this.e) {
            this.e.clear();
        }
    }
}
