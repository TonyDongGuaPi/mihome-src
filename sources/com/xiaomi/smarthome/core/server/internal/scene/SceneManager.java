package com.xiaomi.smarthome.core.server.internal.scene;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.alipay.sdk.sys.a;
import com.taobao.weex.ui.component.richtext.node.RichTextNode;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.entity.scene.Scene;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.statistic.StatManager;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SceneManager {

    /* renamed from: a  reason: collision with root package name */
    static final Object f14703a = new Object();
    private static final int b = 1;
    private static SceneManager c;
    private HashMap<String, ArrayList<Scene>> d = new HashMap<>();
    private HandlerThread e = new HandlerThread("scene_executor");
    private Handler f;
    /* access modifiers changed from: private */
    public boolean g = false;

    public static SceneManager a() {
        if (c == null) {
            synchronized (f14703a) {
                c = new SceneManager();
            }
        }
        return c;
    }

    private SceneManager() {
        this.e.start();
        this.f = new Handler(this.e.getLooper()) {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    Object obj = message.obj;
                    if (obj == null) {
                        SceneManager.this.a((String) null, (String) null);
                    } else {
                        SceneManager.this.a((String) null, (String) obj);
                    }
                }
            }
        };
    }

    public void a(String str, boolean z) {
        List list = this.d.get(str);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                a((Scene) list.get(i), z);
            }
        }
        if (HostSetting.g && list == null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Toast.makeText(CoreService.getAppContext(), "cannot find scene in mSceneMap", 1).show();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final Scene scene, final boolean z) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", scene.b);
            jSONObject.put("us_id", scene.f13996a);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        SmartHomeRc4Api.a().a(new NetRequest.Builder().a("POST").b("/scene/start").b((List<KeyValuePair>) arrayList).a(), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (netResult.c != null) {
                    Log.e("SceneManager", netResult.c);
                }
                if (HostSetting.g) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            Context appContext = CoreService.getAppContext();
                            Toast.makeText(appContext, scene.e + " 执行成功！", 1).show();
                        }
                    });
                }
                if (z) {
                    Intent intent = new Intent("com.xiaomi.smarthome.scene.execute_success");
                    intent.putExtra("id", scene.f13996a);
                    intent.putExtra("name", scene.e);
                    CoreService.getAppContext().sendBroadcast(intent);
                }
                StatManager c2 = StatManager.c();
                String value = StatType.EVENT.getValue();
                c2.a(value, "mihome", "geofence_exe_scene_suc", scene.b + "," + scene.f13996a, (String) null, false);
            }

            public void a(NetError netError) {
                if (z) {
                    Intent intent = new Intent("com.xiaomi.smarthome.scene.execute_failed");
                    intent.putExtra("id", scene.f13996a);
                    if (!HostSetting.g) {
                        intent.putExtra("name", scene.e);
                    } else if (netError != null) {
                        intent.putExtra("name", netError.a() + "," + netError.b() + "," + scene.e);
                    } else {
                        intent.putExtra("name", scene.e);
                    }
                    CoreService.getAppContext().sendBroadcast(intent);
                    StatManager.c().a(StatType.EVENT.getValue(), "mihome", "geofence_exe_scene_fail", scene.b + "," + scene.f13996a, netError.a() + "," + netError.b(), false);
                }
            }
        });
    }

    public void b() {
        this.f.sendEmptyMessage(1);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2) {
        Log.e("SceneManager", "Start Get Scene");
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("st_id", "15");
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        SmartHomeRc4Api.a().a(new NetRequest.Builder().a("POST").b("/scene/list").b((List<KeyValuePair>) arrayList).a(), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (netResult == null || netResult.c == null || netResult.c.isEmpty()) {
                    LocalBroadcastManager.getInstance(CoreService.getAppContext()).sendBroadcast(new Intent("scene_manager_init_fail_normal_scene"));
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(netResult.c);
                    Log.e("SceneManager", "Get Scene Success");
                    JSONObject optJSONObject = jSONObject.optJSONObject("result");
                    if (optJSONObject != null) {
                        SceneManager.this.a(optJSONObject);
                        boolean unused = SceneManager.this.g = true;
                        LocalBroadcastManager.getInstance(CoreService.getAppContext()).sendBroadcast(new Intent("scene_manager_init_success_normal_scene"));
                    }
                } catch (JSONException unused2) {
                    LocalBroadcastManager.getInstance(CoreService.getAppContext()).sendBroadcast(new Intent("scene_manager_init_fail_normal_scene"));
                }
            }

            public void a(NetError netError) {
                LocalBroadcastManager.getInstance(CoreService.getAppContext()).sendBroadcast(new Intent("scene_manager_init_fail_normal_scene"));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        this.d.clear();
        int i = 0;
        while (jSONObject.has(String.valueOf(i))) {
            JSONObject optJSONObject = jSONObject.optJSONObject(String.valueOf(i));
            Scene scene = new Scene();
            scene.f13996a = optJSONObject.optString("us_id");
            scene.e = optJSONObject.optString("name");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject(a.j);
            scene.c = optJSONObject2.optString(AutoSceneAction.f21495a).equals("1");
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("launch");
            if (optJSONObject3 == null) {
                i++;
            } else {
                JSONObject optJSONObject4 = optJSONObject3.optJSONObject("sub_launch");
                JSONArray optJSONArray2 = optJSONObject3.optJSONArray(RichTextNode.ATTR);
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        try {
                            Object obj = optJSONArray2.get(i2);
                            if (obj != null) {
                                if (obj instanceof JSONObject) {
                                    if (TextUtils.equals("user", ((JSONObject) obj).optString("src"))) {
                                        String optString = optJSONArray2.optJSONObject(i2).optString("key");
                                        if (!TextUtils.isEmpty(optString)) {
                                            scene.b = optString;
                                            ArrayList arrayList = this.d.get(optString);
                                            if (arrayList == null) {
                                                arrayList = new ArrayList();
                                                this.d.put(optString, arrayList);
                                            }
                                            arrayList.add(scene);
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (!(optJSONObject4 == null || (optJSONArray = optJSONObject4.optJSONArray(RichTextNode.ATTR)) == null || optJSONArray.length() <= 0)) {
                    for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                        if (optJSONArray.optJSONObject(i3).optString("src").equalsIgnoreCase("user")) {
                            String optString2 = optJSONArray.optJSONObject(i3).optString("key");
                            if (!TextUtils.isEmpty(optString2)) {
                                scene.b = optString2;
                                if (!this.d.containsKey(optString2)) {
                                    this.d.put(optString2, new ArrayList());
                                }
                                this.d.get(optString2).add(scene);
                            }
                        }
                    }
                }
                i++;
            }
        }
    }

    public boolean c() {
        return this.g;
    }

    public void d() {
        c = null;
        try {
            if (this.e != null) {
                this.e.quit();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
