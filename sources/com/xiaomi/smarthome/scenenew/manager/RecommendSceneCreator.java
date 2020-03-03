package com.xiaomi.smarthome.scenenew.manager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.config.model.SupportBleGatewayFirmwareVersion;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.lumiscene.LocalSceneBuilder;
import com.xiaomi.smarthome.scenenew.lumiscene.SceneExtraBuilder;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import com.xiaomi.smarthome.scenenew.model.RecommendSceneDetailInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendSceneCreator {
    private static RecommendSceneCreator i;

    /* renamed from: a  reason: collision with root package name */
    public PluginRecommendSceneInfo.RecommendSceneItem f21963a = null;
    public Map<String, JSONObject> b = new HashMap();
    public Map<Integer, SceneApi.Condition> c = new HashMap();
    public Map<Integer, SceneApi.Action> d = new HashMap();
    public Map<String, List<SceneApi.Condition>> e = new HashMap();
    public Map<String, List<SceneApi.Action>> f = new HashMap();
    public SelectionBuilder g;
    public SceneApi.SmartHomeScene h;

    public interface OnSelectCallback {
        void a(int i, Intent intent);
    }

    public interface SaveSceneCallback {
        void onSaveCloudFail(int i, String str);

        void onSaveCloudSuccess(boolean z);

        void onSaveLocalFail();
    }

    public interface SelectionBuilder {
        List<SceneApi.Action> getAdditionActions();

        List<SceneApi.Condition> getAdditionConditions();

        Map<String, List<SceneApi.Condition>> getSelctionConditions(Map<Integer, SceneApi.Condition> map);

        Map<String, List<SceneApi.Action>> getSelectionActions(Map<Integer, SceneApi.Action> map);
    }

    private RecommendSceneCreator() {
    }

    public static RecommendSceneCreator a() {
        if (i == null) {
            synchronized (RecommendSceneCreator.class) {
                if (i == null) {
                    i = new RecommendSceneCreator();
                }
            }
        }
        return i;
    }

    public static void a(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem, String str, SaveSceneCallback saveSceneCallback) {
        SceneApi.SmartHomeScene a2 = a(recommendSceneItem, str);
        if (a2 != null) {
            a(a2, saveSceneCallback);
        } else if (saveSceneCallback != null) {
            saveSceneCallback.onSaveCloudFail(-9990, "create scene from RecommendSceneItem fail");
        }
    }

    public static SceneApi.SmartHomeScene a(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem, String str) {
        SceneApi.Action a2;
        SceneApi.Condition b2;
        if (recommendSceneItem == null) {
            return null;
        }
        RecommendSceneDetailInfo recommendSceneDetailInfo = new RecommendSceneDetailInfo();
        recommendSceneDetailInfo.a(recommendSceneItem);
        SceneApi.SmartHomeScene smartHomeScene = new SceneApi.SmartHomeScene();
        smartHomeScene.u = new SceneApi.EffectiveTimeSpan();
        smartHomeScene.g = recommendSceneDetailInfo.f;
        smartHomeScene.n = recommendSceneDetailInfo.g == 1;
        smartHomeScene.o = recommendSceneDetailInfo.h == 1;
        smartHomeScene.i = Integer.valueOf(recommendSceneDetailInfo.e).intValue();
        if (recommendSceneDetailInfo.i == 30) {
            smartHomeScene.t = true;
        } else {
            smartHomeScene.t = false;
        }
        if (recommendSceneDetailInfo.f21985a != null) {
            for (int i2 = 0; i2 < recommendSceneDetailInfo.f21985a.size(); i2++) {
                RecommendSceneDetailInfo.ConditionItem conditionItem = recommendSceneDetailInfo.f21985a.get(i2);
                if (conditionItem.f.equalsIgnoreCase("device")) {
                    if (conditionItem.b.size() != 0) {
                        for (int i3 = 0; i3 < conditionItem.b.size(); i3++) {
                            Device device = conditionItem.b.get(i3);
                            if (!(conditionItem.d.get(device.did) == null || !conditionItem.d.get(device.did).booleanValue() || (b2 = RecommendSceneDetailInfo.b(device.model, conditionItem.e.optString(device.model))) == null || b2.c == null || smartHomeScene.l == null)) {
                                b2.c.f21523a = device.did;
                                smartHomeScene.l.add(b2);
                            }
                        }
                    }
                } else if (!(conditionItem.f21987a == null || smartHomeScene.l == null)) {
                    smartHomeScene.l.add(conditionItem.f21987a);
                }
            }
        }
        if (recommendSceneDetailInfo.b != null) {
            for (int i4 = 0; i4 < recommendSceneDetailInfo.b.size(); i4++) {
                RecommendSceneDetailInfo.ActionItem actionItem = recommendSceneDetailInfo.b.get(i4);
                if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                    if (actionItem.b.size() != 0) {
                        for (int i5 = 0; i5 < actionItem.b.size(); i5++) {
                            Device device2 = actionItem.b.get(i5);
                            if (!(actionItem.d.get(device2.did) == null || !actionItem.d.get(device2.did).booleanValue() || ((!TextUtils.isEmpty(str) && (TextUtils.isEmpty(str) || !TextUtils.equals(str, device2.did))) || (a2 = RecommendSceneDetailInfo.a(device2.model, actionItem.e.optString(device2.model))) == null || a2.g == null))) {
                                a2.g.e = device2.did;
                                SceneApi.SHSceneItemPayload sHSceneItemPayload = a2.g;
                                sHSceneItemPayload.c = device2.model + "." + a2.g.c;
                                a2.b = device2.name;
                                smartHomeScene.k.add(a2);
                            }
                        }
                    }
                } else if (actionItem.f21986a != null) {
                    smartHomeScene.k.add(actionItem.f21986a);
                }
            }
        }
        return smartHomeScene;
    }

    public static void a(final SceneApi.SmartHomeScene smartHomeScene, final SaveSceneCallback saveSceneCallback) {
        Device a2 = a(smartHomeScene);
        if (a2 == null) {
            c(smartHomeScene, saveSceneCallback);
        } else if (a2.isOnline || saveSceneCallback == null) {
            SceneApi.a(smartHomeScene);
            SceneInfo f2 = SceneManager.f(smartHomeScene);
            final boolean[] zArr = {false};
            final SaveSceneCallback saveSceneCallback2 = saveSceneCallback;
            final boolean[] zArr2 = zArr;
            final AnonymousClass1 r2 = new CountDownTimer(10000, 1000) {
                public void onTick(long j) {
                }

                public void onFinish() {
                    if (saveSceneCallback2 != null) {
                        saveSceneCallback2.onSaveLocalFail();
                    }
                    zArr2[0] = true;
                }
            };
            r2.start();
            SceneExtraBuilder.a().a(f2, (Callback<SceneInfo>) new Callback<SceneInfo>() {
                /* renamed from: a */
                public void onSuccess(SceneInfo sceneInfo) {
                    if (!zArr[0]) {
                        r2.cancel();
                        zArr[0] = true;
                        if (sceneInfo != null) {
                            for (int i = 0; i < sceneInfo.mLaunchList.size(); i++) {
                                if (smartHomeScene.l.get(i).c != null && (smartHomeScene.l.get(i).c instanceof SceneApi.ConditionDeviceCommon)) {
                                    ((SceneApi.ConditionDeviceCommon) smartHomeScene.l.get(i).c).m = sceneInfo.mLaunchList.get(i).mExtra;
                                }
                            }
                            for (int i2 = 0; i2 < sceneInfo.mActions.size(); i2++) {
                                if (smartHomeScene.k.get(i2).g != null && (smartHomeScene.k.get(i2).g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                                    ((SceneApi.SHSceneItemPayloadCommon) smartHomeScene.k.get(i2).g).f21531a = sceneInfo.mActions.get(i2).mExtra;
                                }
                            }
                        }
                        RecommendSceneCreator.c(smartHomeScene, saveSceneCallback);
                    }
                }

                public void onFailure(int i, String str) {
                    r2.cancel();
                    zArr[0] = true;
                    if (saveSceneCallback != null) {
                        saveSceneCallback.onSaveLocalFail();
                    }
                }
            });
        } else {
            saveSceneCallback.onSaveCloudFail(-9000, "gateway offline");
        }
    }

    /* access modifiers changed from: private */
    public static void c(final SceneApi.SmartHomeScene smartHomeScene, final SaveSceneCallback saveSceneCallback) {
        if (SmartHomeConfig.c) {
            RemoteSceneApi.a().a(SHApplication.getAppContext(), smartHomeScene, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("us_id");
                    boolean optBoolean = jSONObject.optBoolean("local");
                    String optString2 = jSONObject.optString("local_dev");
                    smartHomeScene.f = optString;
                    if (!TextUtils.isEmpty(optString2) && optBoolean) {
                        RecommendSceneCreator.b(optString2, jSONObject.optJSONObject("data").toString(), smartHomeScene, saveSceneCallback);
                    } else if (saveSceneCallback != null) {
                        saveSceneCallback.onSaveCloudSuccess(true);
                    }
                }

                public void onFailure(Error error) {
                    if (saveSceneCallback != null) {
                        saveSceneCallback.onSaveCloudFail(error.a(), error.b());
                    }
                }
            });
        } else if (saveSceneCallback != null) {
            saveSceneCallback.onSaveCloudFail(-9990, "is not smarttttttt home");
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str, String str2, final SceneApi.SmartHomeScene smartHomeScene, final SaveSceneCallback saveSceneCallback) {
        Device n = SmartHomeDeviceManager.a().n(str);
        if (n != null) {
            LocalSceneBuilder.a().a(XmPluginHostApi.instance().getDeviceByDid(n.did), str2, (MessageCallback) new MessageCallback() {
                public void onSuccess(Intent intent) {
                    RemoteSceneApi.a().b(SHApplication.getAppContext(), smartHomeScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (saveSceneCallback != null) {
                                saveSceneCallback.onSaveCloudSuccess(true);
                            }
                        }

                        public void onFailure(Error error) {
                            if (saveSceneCallback != null) {
                                saveSceneCallback.onSaveCloudSuccess(false);
                            }
                        }
                    });
                }

                public void onFailure(int i, String str) {
                    if (saveSceneCallback != null) {
                        saveSceneCallback.onSaveCloudSuccess(false);
                    }
                }
            });
            final SaveSceneCallback saveSceneCallback2 = saveSceneCallback;
            new CountDownTimer(10000, 1000) {
                public void onTick(long j) {
                }

                public void onFinish() {
                    if (saveSceneCallback2 != null) {
                        saveSceneCallback2.onSaveCloudSuccess(false);
                    }
                }
            }.start();
        }
    }

    private static Device a(SceneApi.SmartHomeScene smartHomeScene) {
        Device n;
        String str;
        Device n2;
        String str2;
        boolean z = false;
        String str3 = null;
        for (SceneApi.Condition next : smartHomeScene.l) {
            if (!(next.c == null || next.c.d == null || (n2 = SmartHomeDeviceManager.a().n(next.c.f21523a)) == null)) {
                if (n2.isSubDevice()) {
                    str2 = n2.parentId;
                } else {
                    str2 = n2.did;
                }
                if (SmartHomeSceneUtility.a(SmartHomeDeviceManager.a().n(str2))) {
                    str3 = str2;
                    z = true;
                }
            }
        }
        if (!z) {
            for (SceneApi.Action next2 : smartHomeScene.k) {
                if (!(next2.e == null || next2.g.e == null || (n = SmartHomeDeviceManager.a().n(next2.g.e)) == null)) {
                    if (n.isSubDevice()) {
                        str = n.parentId;
                    } else {
                        str = n.did;
                    }
                    if (SmartHomeSceneUtility.a(SmartHomeDeviceManager.a().n(str))) {
                        str3 = str;
                        z = true;
                    }
                }
            }
        }
        if (!z || TextUtils.isEmpty(str3)) {
            return null;
        }
        return SmartHomeDeviceManager.a().n(str3);
    }

    public void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (this.f21963a.mConditionList != null && jSONObject != null) {
            for (PluginRecommendSceneInfo.ConditionActionItem next : this.f21963a.mConditionList) {
                if (next.modelListJobj == null) {
                    break;
                }
                Iterator<String> keys = next.modelListJobj.keys();
                while (keys.hasNext()) {
                    int optInt = next.modelListJobj.optInt(keys.next());
                    if (!this.b.containsKey(Integer.valueOf(optInt)) && optInt > 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject(optInt + "");
                        if (optJSONObject != null) {
                            Map<String, JSONObject> map = this.b;
                            map.put(optInt + "", optJSONObject);
                        }
                    }
                }
            }
        }
        if (this.f21963a.mActionList != null && jSONObject2 != null) {
            for (PluginRecommendSceneInfo.ConditionActionItem next2 : this.f21963a.mActionList) {
                if (next2.modelListJobj != null) {
                    Iterator<String> keys2 = next2.modelListJobj.keys();
                    while (keys2.hasNext()) {
                        String next3 = keys2.next();
                        int optInt2 = next2.modelListJobj.optInt(next3);
                        if (!this.d.containsKey(Integer.valueOf(optInt2)) && optInt2 > 0) {
                            JSONObject optJSONObject2 = jSONObject2.optJSONObject(optInt2 + "");
                            if (optJSONObject2 != null) {
                                try {
                                    SceneApi.Action a2 = SceneApi.Action.a(optJSONObject2);
                                    if (a2.f21521a == 0) {
                                        SceneApi.SHSceneItemPayload sHSceneItemPayload = a2.g;
                                        sHSceneItemPayload.c = next3 + "." + a2.g.c;
                                        a2.g.h = 0;
                                    }
                                    this.d.put(Integer.valueOf(optInt2), a2);
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void b() {
        this.f.clear();
        this.e.clear();
        if (this.g != null) {
            this.f.putAll(this.g.getSelectionActions(this.d));
            this.e.putAll(this.g.getSelctionConditions(this.c));
        }
    }

    public static List<Device> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Device next : HomeManager.a().o(str)) {
            PluginRecord d2 = CoreApi.a().d(next.model);
            if (!(!next.isOwner() || d2 == null || d2.c() == null)) {
                boolean z = true;
                if (d2.c().J() == 1) {
                    if (next instanceof MiioDeviceV2) {
                        String str2 = ((MiioDeviceV2) next).D;
                        List<SupportBleGatewayFirmwareVersion> f2 = AndroidCommonConfigManager.a().f();
                        if (f2 != null && f2.size() > 0 && !TextUtils.isEmpty(str2)) {
                            Iterator<SupportBleGatewayFirmwareVersion> it = f2.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                SupportBleGatewayFirmwareVersion next2 = it.next();
                                if (TextUtils.equals(next2.f13958a, next.model)) {
                                    if (BluetoothHelper.a(str2, next2.b) < 0) {
                                        z = false;
                                    }
                                }
                            }
                        }
                    }
                    if (z) {
                        arrayList.add(next);
                    }
                }
            }
        }
        return arrayList;
    }

    public void a(SelectionBuilder selectionBuilder) {
        this.g = selectionBuilder;
    }

    public void c() {
        i = null;
    }

    public void a(BaseActivity baseActivity, SceneApi.Action action, Object obj, OnSelectCallback onSelectCallback) {
        final Intent intent = new Intent(((SceneApi.SHSceneItemPayloadCommon) action.g).j);
        intent.putExtra("action", action.g.c);
        if (obj != null && (obj instanceof String)) {
            intent.putExtra("value", (String) obj);
        } else if (obj != null) {
            intent.putExtra("value", obj.toString());
        }
        intent.putExtra("actionId", action.f);
        intent.putExtra("plug_id", ((SceneApi.SHSceneItemPayloadCommon) action.g).j);
        intent.putExtra("name", action.b);
        intent.putExtra("tr_id", action.d);
        intent.putExtra("scene_type", 2);
        final BaseActivity baseActivity2 = baseActivity;
        final SceneApi.Action action2 = action;
        final OnSelectCallback onSelectCallback2 = onSelectCallback;
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                RecommendSceneCreator.this.a(baseActivity2, SmartHomeDeviceManager.a().b(action2.g.e), 3, intent, onSelectCallback2);
            }
        });
    }

    public void a(BaseActivity baseActivity, SceneApi.Condition condition, Object obj, OnSelectCallback onSelectCallback) {
        PluginRecommendSceneInfo.CommonSceneCondition commonSceneCondition;
        if (this.f21963a.mCommonConditions != null && (commonSceneCondition = this.f21963a.mCommonConditions.get(Integer.valueOf(condition.c.k))) != null) {
            final Intent intent = new Intent(commonSceneCondition.mParamAction);
            intent.putExtra("action", commonSceneCondition.mKey);
            intent.putExtra("value", String.valueOf(commonSceneCondition.mValue));
            if (obj != null && (obj instanceof String)) {
                intent.putExtra("last_value", String.valueOf(((SceneApi.ConditionDeviceCommon) condition.c).l));
            } else if (obj != null) {
                intent.putExtra("value", obj.toString());
            }
            intent.putExtra("actionId", commonSceneCondition.id);
            intent.putExtra("name", commonSceneCondition.mName);
            intent.putExtra("plug_id", commonSceneCondition.mParamAction);
            intent.putExtra("tr_id", commonSceneCondition.mCompatibleId + "");
            intent.putExtra("scene_type", 1);
            final BaseActivity baseActivity2 = baseActivity;
            final SceneApi.Condition condition2 = condition;
            final OnSelectCallback onSelectCallback2 = onSelectCallback;
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RecommendSceneCreator.this.a(baseActivity2, SmartHomeDeviceManager.a().b(condition2.c.f21523a), 3, intent, onSelectCallback2);
                }
            });
        }
    }

    public void a(BaseActivity baseActivity, Device device, int i2, Intent intent, OnSelectCallback onSelectCallback) {
        BaseActivity baseActivity2 = baseActivity;
        PluginRecord d2 = CoreApi.a().d(device.model);
        if (d2 != null) {
            final XQProgressHorizontalDialog b2 = XQProgressHorizontalDialog.b(baseActivity2, baseActivity2.getString(R.string.plugin_downloading) + d2.p() + baseActivity2.getString(R.string.plugin));
            final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
            final boolean z = !d2.l() && !d2.k();
            final BaseActivity baseActivity3 = baseActivity;
            final PluginRecord pluginRecord = d2;
            final OnSelectCallback onSelectCallback2 = onSelectCallback;
            PluginApi.getInstance().sendMessage(baseActivity, d2, i2, intent, device.newDeviceStat(), (RunningProcess) null, true, new PluginApi.SendMessageCallback((Object) null) {
                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    pluginDownloadTask.a(pluginDownloadTask);
                    if (b2 != null && !baseActivity3.isFinishing() && !baseActivity3.isDestroyed()) {
                        b2.a(100, 0);
                        b2.c();
                        b2.setCancelable(true);
                        b2.show();
                        b2.setOnCancelListener(new DialogInterface.OnCancelListener(pluginDownloadTask) {
                            private final /* synthetic */ PluginDownloadTask f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void onCancel(DialogInterface dialogInterface) {
                                CoreApi.a().a(PluginRecord.this.o(), this.f$1, (CoreApi.CancelPluginDownloadCallback) null);
                            }
                        });
                    }
                }

                public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                    if (z) {
                        int i = (int) (f2 * 100.0f);
                        if (i >= 99) {
                            i = 99;
                        }
                        if (b2 != null) {
                            b2.a(100, i);
                        }
                    } else if (b2 != null) {
                        b2.a(100, (int) (f2 * 100.0f));
                    }
                }

                public void onDownloadSuccess(PluginRecord pluginRecord) {
                    if (!z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onDownloadFailure(PluginError pluginError) {
                    if (!z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onDownloadCancel() {
                    if (!z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onSendSuccess(Bundle bundle) {
                    if (z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onSendFailure(Error error) {
                    if (z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onSendCancel() {
                    if (z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onMessageSuccess(Intent intent) {
                    baseActivity3.mHandler.post(new Runnable(intent) {
                        private final /* synthetic */ Intent f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            RecommendSceneCreator.AnonymousClass8.a(RecommendSceneCreator.OnSelectCallback.this, this.f$1);
                        }
                    });
                    this.mObj = null;
                }

                /* access modifiers changed from: private */
                public static /* synthetic */ void a(OnSelectCallback onSelectCallback, Intent intent) {
                    if (onSelectCallback != null) {
                        onSelectCallback.a(-1, intent);
                    }
                }

                public void onMessageFailure(int i, String str) {
                    baseActivity3.mHandler.post(new Runnable() {
                        public final void run() {
                            RecommendSceneCreator.AnonymousClass8.a(RecommendSceneCreator.OnSelectCallback.this);
                        }
                    });
                    this.mObj = null;
                }

                /* access modifiers changed from: private */
                public static /* synthetic */ void a(OnSelectCallback onSelectCallback) {
                    if (onSelectCallback != null) {
                        onSelectCallback.a(0, (Intent) null);
                    }
                }
            });
        }
    }

    public static boolean b(String str) {
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 != null && DeviceCategory.fromPid(b2.pid) == DeviceCategory.Bluetooth) {
            return true;
        }
        return false;
    }
}
