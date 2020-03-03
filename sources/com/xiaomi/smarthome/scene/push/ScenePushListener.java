package com.xiaomi.smarthome.scene.push;

import android.content.Intent;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.plugin.mpk.MpkPluginApi;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.messagecenter.CommonMessageManager;
import com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.json.JSONException;
import org.json.JSONObject;

public class ScenePushListener extends PushListener {

    /* renamed from: a  reason: collision with root package name */
    SmartHomeDeviceManager.IClientDeviceListener f21619a = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            if (i == 3) {
                boolean unused = ScenePushListener.this.b = true;
                SmartHomeDeviceManager.a().c(ScenePushListener.this.f21619a);
                CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsPluginCacheReadyCallback) new CoreApi.IsPluginCacheReadyCallback() {
                            public void onPluginCacheReady() {
                                CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsPluginReadyCallback) new CoreApi.IsPluginReadyCallback() {
                                    public void a() {
                                        SencePushMessage sencePushMessage;
                                        do {
                                            try {
                                                sencePushMessage = (SencePushMessage) ScenePushListener.this.c.removeLast();
                                                ScenePushListener.a(sencePushMessage.f21631a, sencePushMessage.b, sencePushMessage.c, ScenePushListener.this.d);
                                                continue;
                                            } catch (NoSuchElementException unused) {
                                                sencePushMessage = null;
                                                continue;
                                            }
                                        } while (sencePushMessage != null);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }

        public void b(int i) {
            if (i == 3) {
                boolean unused = ScenePushListener.this.b = true;
                SmartHomeDeviceManager.a().c(ScenePushListener.this.f21619a);
                CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsPluginReadyCallback) new CoreApi.IsPluginReadyCallback() {
                    public void a() {
                        SencePushMessage sencePushMessage;
                        do {
                            try {
                                sencePushMessage = (SencePushMessage) ScenePushListener.this.c.removeLast();
                                ScenePushListener.a(sencePushMessage.f21631a, sencePushMessage.b, sencePushMessage.c, ScenePushListener.this.d);
                                continue;
                            } catch (NoSuchElementException unused) {
                                sencePushMessage = null;
                                continue;
                            }
                        } while (sencePushMessage != null);
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean b = false;
    /* access modifiers changed from: private */
    public LinkedList<SencePushMessage> c = new LinkedList<>();
    /* access modifiers changed from: private */
    public HashSet<ScenePushCallback> d = new HashSet<>();

    class SencePushMessage {

        /* renamed from: a  reason: collision with root package name */
        public String f21631a;
        public String b;
        public boolean c;

        SencePushMessage() {
        }
    }

    public boolean a(String str, String str2) {
        SencePushMessage sencePushMessage = new SencePushMessage();
        sencePushMessage.f21631a = str;
        sencePushMessage.b = str2;
        sencePushMessage.c = false;
        a(sencePushMessage);
        return true;
    }

    public boolean b(String str, String str2) {
        this.b = false;
        SencePushMessage sencePushMessage = new SencePushMessage();
        sencePushMessage.f21631a = str;
        sencePushMessage.b = str2;
        sencePushMessage.c = true;
        a(sencePushMessage);
        return true;
    }

    private void a(final SencePushMessage sencePushMessage) {
        try {
            JSONObject jSONObject = new JSONObject(sencePushMessage.b);
            final String optString = jSONObject.optString("did");
            if (TextUtils.isEmpty(optString)) {
                CommonMessageManager.a();
                return;
            }
            final String optString2 = jSONObject.optString("alertType");
            if (SmartHomeDeviceManager.a().b(optString) != null) {
                CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsPluginReadyCallback) new CoreApi.IsPluginReadyCallback() {
                    public void a() {
                        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsPluginCacheReadyCallback) new CoreApi.IsPluginCacheReadyCallback() {
                            public void onPluginCacheReady() {
                                if (!TextUtils.isEmpty(optString2)) {
                                    RemoteFamilyApi.a().a(sencePushMessage.f21631a, optString, optString2, (AsyncCallback<JSONObject, Error>) null);
                                }
                                ScenePushListener.a(sencePushMessage.f21631a, sencePushMessage.b, sencePushMessage.c, ScenePushListener.this.d);
                            }
                        });
                    }
                });
            } else if (!this.b) {
                CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsPluginCacheReadyCallback) new CoreApi.IsPluginCacheReadyCallback() {
                            public void onPluginCacheReady() {
                                CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsPluginReadyCallback) new CoreApi.IsPluginReadyCallback() {
                                    public void a() {
                                        if (!TextUtils.isEmpty(optString2)) {
                                            RemoteFamilyApi.a().a(sencePushMessage.f21631a, optString, optString2, (AsyncCallback<JSONObject, Error>) null);
                                        }
                                        ScenePushListener.this.c.addFirst(sencePushMessage);
                                        SmartHomeDeviceManager.a().a(ScenePushListener.this.f21619a);
                                        SmartHomeDeviceManager.a().p();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        } catch (Exception | JSONException unused) {
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str, String str2, String str3, long j, String str4, boolean z) {
        if (SmartHomeDeviceManager.a().b(str2) == null) {
            try {
                Intent intent = new Intent();
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.setClass(SHApplication.getAppContext(), MessageCenterActivity.class);
                SHApplication.getAppContext().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            PluginRecord d2 = CoreApi.a().d(str);
            if (d2 != null) {
                MpkPluginApi.onReceiveScenePush(d2, str2, str3, j, str4, z);
                return;
            }
            LogUtil.c("ScenePushListener", "not match model:" + str);
        }
    }

    public static void a(String str, String str2, boolean z, HashSet<ScenePushCallback> hashSet) {
        try {
            if (GlobalSetting.q || GlobalSetting.w) {
                LogUtil.a("ScenePushListener", "dispatchMessage:" + str2);
            }
            JSONObject jSONObject = new JSONObject(str2);
            final String optString = jSONObject.optString("model");
            final String optString2 = jSONObject.optString("did");
            final String optString3 = jSONObject.optString("event");
            final long optLong = jSONObject.optLong("time");
            final String optString4 = jSONObject.optString("extra");
            final boolean z2 = z;
            final HashSet<ScenePushCallback> hashSet2 = hashSet;
            final String str3 = str2;
            CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    if (CoreApi.a().c(optString)) {
                        ScenePushListener.b(optString, optString2, optString3, optLong, optString4, z2);
                    } else if (hashSet2 != null) {
                        Iterator it = hashSet2.iterator();
                        ScenePushCallback scenePushCallback = null;
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ScenePushCallback scenePushCallback2 = (ScenePushCallback) it.next();
                            if (scenePushCallback2.isMatch(optString, optString3)) {
                                scenePushCallback = scenePushCallback2;
                                break;
                            }
                        }
                        if (scenePushCallback != null) {
                            if (z2) {
                                scenePushCallback.onReceiveNotifiedMessage(str3);
                            } else {
                                scenePushCallback.onReceiveMessage(str3);
                            }
                            LogUtil.c("ScenePushListener", "targetCallback isNotified:" + z2);
                            return;
                        }
                        LogUtil.c("ScenePushListener", "targetCallback is null");
                    } else {
                        LogUtil.c("ScenePushListener", "not plugin and pushCallbacks is null");
                    }
                }
            });
        } catch (Exception | JSONException unused) {
        }
    }

    public void a(ScenePushCallback scenePushCallback) {
        if (scenePushCallback != null) {
            this.d.add(scenePushCallback);
        }
    }

    public void b(ScenePushCallback scenePushCallback) {
        if (scenePushCallback != null) {
            this.d.remove(scenePushCallback);
        }
    }
}
