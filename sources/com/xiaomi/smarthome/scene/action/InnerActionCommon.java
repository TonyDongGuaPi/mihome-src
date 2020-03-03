package com.xiaomi.smarthome.scene.action;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugConstant;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugFileUtil;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import com.xiaomi.smarthome.scene.SceneNumberPicker;
import com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.setting.PluginSetting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class InnerActionCommon extends BaseInnerAction {

    /* renamed from: a  reason: collision with root package name */
    public static final int f21497a = -99;
    private CommonSceneOnline f;
    private Device g;
    private int h = -1;
    private String[] i;
    private int[] j;
    private int[] k;
    private List<CommonSceneOnline.CommonSceneAction> l = new ArrayList();

    public InnerActionCommon(Device device, SmartHomeSceneCreateEditActivity.DefaultSceneItemSet defaultSceneItemSet) {
        boolean z;
        if (!(SceneManager.x().a(device.model, device.did) == null || SceneManager.x().a(device.model, device.did).f == null || SceneManager.x().a(device.model, device.did).f.size() <= 0)) {
            this.f = SceneManager.x().a(device.model, device.did);
            if (defaultSceneItemSet == null || defaultSceneItemSet.c == null || defaultSceneItemSet.c.length == 0) {
                this.l.addAll(this.f.f);
            } else {
                RecommendSceneItem.Key[] keyArr = new RecommendSceneItem.Key[defaultSceneItemSet.c.length];
                for (int i2 = 0; i2 < keyArr.length; i2++) {
                    keyArr[i2] = new RecommendSceneItem.Key();
                    keyArr[i2].mKey = String.valueOf(defaultSceneItemSet.c[i2].mKey);
                    keyArr[i2].mValues = defaultSceneItemSet.c[i2].mValues;
                }
                if (keyArr.length > 0 && !keyArr[0].mKey.contains(device.model)) {
                    String[] strArr = defaultSceneItemSet.b;
                    int length = strArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            break;
                        }
                        String str = strArr[i3];
                        if (keyArr[0].mKey.contains(str)) {
                            for (RecommendSceneItem.Key key : keyArr) {
                                key.mKey = key.mKey.replace(str, device.model);
                            }
                        } else {
                            i3++;
                        }
                    }
                }
                Iterator<CommonSceneOnline.CommonSceneAction> it = this.f.f.iterator();
                while (it.hasNext()) {
                    CommonSceneOnline.CommonSceneAction next = it.next();
                    int length2 = keyArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            z = false;
                            break;
                        } else {
                            RecommendSceneItem.Key key2 = keyArr[i4];
                            i4 = (!key2.mKey.equalsIgnoreCase(next.e) || (!(key2 == null && next.f == null) && TextUtils.isEmpty(next.g) && (key2 == null || next.f == null || !key2.mValues.equals(next.f)))) ? i4 + 1 : i4;
                        }
                    }
                    z = true;
                    if (z) {
                        this.l.add(next);
                    }
                }
            }
            this.c = new int[this.l.size()];
            this.b = new String[this.l.size()];
            this.d = new int[this.l.size()];
            for (int i5 = 0; i5 < this.c.length; i5++) {
                this.c[i5] = i5;
                this.d[i5] = this.l.get(i5).h;
                this.b[i5] = this.l.get(i5).f21199a;
            }
            if (this.f.h.size() > 0) {
                this.i = new String[this.f.h.size()];
                this.j = new int[this.f.h.size()];
                for (int i6 = 0; i6 < this.f.h.size(); i6++) {
                    this.i[i6] = this.f.h.get(i6).f21201a;
                    this.j[i6] = this.f.h.get(i6).b;
                }
                this.k = new int[this.l.size()];
                for (int i7 = 0; i7 < this.k.length; i7++) {
                    this.k[i7] = this.l.get(i7).c;
                }
            }
        }
        this.g = device;
    }

    public boolean d(int i2) {
        return TextUtils.isEmpty(this.l.get(i2).g);
    }

    public String e(int i2) {
        for (int i3 = 0; i3 < this.j.length; i3++) {
            if (this.j[i3] == i2) {
                return this.i[i3];
            }
        }
        return null;
    }

    public String[] a() {
        return this.i;
    }

    public int[] f() {
        return this.j;
    }

    public int f(int i2) {
        return this.k[i2];
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.scene.api.SceneApi.Action a(java.lang.String r4, int r5, java.lang.Object r6, android.content.Intent r7) {
        /*
            r3 = this;
            com.xiaomi.smarthome.device.Device r6 = (com.xiaomi.smarthome.device.Device) r6
            com.xiaomi.smarthome.scene.api.SceneApi$Action r0 = new com.xiaomi.smarthome.scene.api.SceneApi$Action
            r0.<init>()
            if (r6 != 0) goto L_0x000a
            return r0
        L_0x000a:
            r1 = 0
            r0.f21521a = r1
            com.xiaomi.smarthome.scene.CommonSceneOnline r2 = r3.f
            java.lang.String r2 = r2.c
            r0.b = r2
            r0.c = r4
            java.lang.String r4 = r6.model
            r0.e = r4
            java.util.List<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction> r4 = r3.l
            java.lang.Object r4 = r4.get(r5)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction r4 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneAction) r4
            int r4 = r4.b
            r0.f = r4
            r4 = 0
        L_0x0026:
            int[] r2 = r3.c
            int r2 = r2.length
            if (r4 >= r2) goto L_0x003a
            int[] r2 = r3.c
            r2 = r2[r4]
            if (r5 != r2) goto L_0x0037
            int[] r2 = r3.d
            r2 = r2[r4]
            r0.d = r2
        L_0x0037:
            int r4 = r4 + 1
            goto L_0x0026
        L_0x003a:
            com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayloadCommon r4 = new com.xiaomi.smarthome.scene.api.SceneApi$SHSceneItemPayloadCommon
            r4.<init>()
            java.lang.String r6 = r6.did
            r4.e = r6
            java.util.List<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction> r6 = r3.l
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction r6 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneAction) r6
            int r6 = r6.b
            r4.i = r6
            java.util.List<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction> r6 = r3.l
            java.lang.Object r6 = r6.get(r5)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction r6 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneAction) r6
            java.lang.String r6 = r6.e
            r4.c = r6
            if (r7 == 0) goto L_0x006d
            java.lang.String r6 = "key_name"
            boolean r6 = r7.hasExtra(r6)
            if (r6 == 0) goto L_0x006d
            java.lang.String r6 = "key_name"
            java.lang.String r6 = r7.getStringExtra(r6)
            r0.c = r6
        L_0x006d:
            if (r7 == 0) goto L_0x00b7
            java.lang.String r6 = "value"
            boolean r6 = r7.hasExtra(r6)
            if (r6 == 0) goto L_0x00b7
            java.lang.String r5 = "value"
            java.lang.String r5 = r7.getStringExtra(r5)
            if (r5 == 0) goto L_0x00aa
            r5 = 1
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x008e }
            java.lang.String r2 = "value"
            java.lang.String r2 = r7.getStringExtra(r2)     // Catch:{ JSONException -> 0x008e }
            r6.<init>(r2)     // Catch:{ JSONException -> 0x008e }
            r4.f = r6     // Catch:{ JSONException -> 0x008e }
            r1 = 1
        L_0x008e:
            if (r1 != 0) goto L_0x009e
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ JSONException -> 0x009e }
            java.lang.String r2 = "value"
            java.lang.String r2 = r7.getStringExtra(r2)     // Catch:{ JSONException -> 0x009e }
            r6.<init>(r2)     // Catch:{ JSONException -> 0x009e }
            r4.f = r6     // Catch:{ JSONException -> 0x009e }
            goto L_0x009f
        L_0x009e:
            r5 = r1
        L_0x009f:
            if (r5 != 0) goto L_0x00c3
            java.lang.String r5 = "value"
            java.lang.String r5 = r7.getStringExtra(r5)
            r4.f = r5
            goto L_0x00c3
        L_0x00aa:
            android.os.Bundle r5 = r7.getExtras()
            java.lang.String r6 = "value"
            java.lang.Object r5 = r5.get(r6)
            r4.f = r5
            goto L_0x00c3
        L_0x00b7:
            java.util.List<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction> r6 = r3.l
            java.lang.Object r5 = r6.get(r5)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneAction r5 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneAction) r5
            java.lang.Object r5 = r5.f
            r4.f = r5
        L_0x00c3:
            r0.g = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.action.InnerActionCommon.a(java.lang.String, int, java.lang.Object, android.content.Intent):com.xiaomi.smarthome.scene.api.SceneApi$Action");
    }

    public String a(Device device) {
        return (device == null || TextUtils.isEmpty(device.name)) ? "" : device.name;
    }

    public int a(int i2) {
        for (int i3 = 0; i3 < this.c.length; i3++) {
            if (i2 == this.c[i3]) {
                return this.d[i3];
            }
        }
        return -1;
    }

    public Device g() {
        return this.g;
    }

    public int a(SceneApi.Action action, Object obj) {
        Device device = (Device) obj;
        if (!device.model.equals(action.e) || action.g == null || !(action.g instanceof SceneApi.SHSceneItemPayloadCommon) || !((SceneApi.SHSceneItemPayloadCommon) action.g).e.equalsIgnoreCase(device.did)) {
            return -1;
        }
        SceneApi.SHSceneItemPayloadCommon sHSceneItemPayloadCommon = (SceneApi.SHSceneItemPayloadCommon) action.g;
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            if (((SceneApi.SHSceneItemPayloadCommon) action.g).i != -1 && this.l.get(i2).b != -1 && ((SceneApi.SHSceneItemPayloadCommon) action.g).i == this.l.get(i2).b) {
                return i2;
            }
            if (this.l.get(i2).e.equalsIgnoreCase(action.g.c) && ((this.l.get(i2).f == null && sHSceneItemPayloadCommon.f == null) || (this.l.get(i2).f != null && sHSceneItemPayloadCommon.f != null && SmartHomeSceneUtility.a(this.l.get(i2).f, sHSceneItemPayloadCommon.f)))) {
                return i2;
            }
        }
        return -1;
    }

    public int a(String str, Object obj, Device device) {
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            if (this.l.get(i2).e.equalsIgnoreCase(str) && ((this.l.get(i2).f == null && obj == null) || (this.l.get(i2).f != null && obj != null && SmartHomeSceneUtility.a(this.l.get(i2).f, obj)))) {
                return i2;
            }
        }
        return -1;
    }

    public String a(Object obj) {
        return a(obj instanceof Device ? (Device) obj : null);
    }

    public int a(SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback, Context context, String str, int i2, Object obj, Object obj2) {
        JSONObject a2;
        if (this.l.get(i2).i == null || !(this.l.get(i2).i instanceof CommonSceneOnline.SceneAttrNumberPicker)) {
            PluginRecord d = CoreApi.a().d(this.g.model);
            if (d != null && d.l() && d.h().q()) {
                if (DevelopSharePreManager.a().g() && (a2 = RnDebugFileUtil.a(this.g.model)) != null) {
                    boolean optBoolean = a2.optBoolean(RnDebugConstant.c, false);
                    boolean optBoolean2 = a2.optBoolean(RnDebugConstant.e, false);
                    String optString = a2.optString(RnDebugConstant.d, "");
                    RnPluginLog.a("native scene debug,  model: " + this.g.model + "  isCheck: " + optBoolean + "   sceneId: " + optString + "   scenIdIsCheck: " + optBoolean2 + "  id: " + this.l.get(i2).b);
                    if (optBoolean && optBoolean2) {
                        if (optString.equals(this.l.get(i2).b + "")) {
                            a(innerValueCallback, context, i2, obj, obj2);
                            RnPluginLog.a("native debug, will launch rn plugin  action is " + this.l.get(i2).b);
                            return 0;
                        }
                    }
                }
                List<String> c = PluginSetting.c(d.w());
                if (!c.contains(this.l.get(i2).b + "")) {
                    return -2;
                }
                a(innerValueCallback, context, i2, obj, obj2);
                RnPluginLog.a("will launch rn plugin  action is " + this.l.get(i2).b);
                return 0;
            } else if (TextUtils.isEmpty(this.l.get(i2).g) || obj == null) {
                return -2;
            } else {
                final Intent intent = new Intent(this.l.get(i2).g);
                intent.putExtra("action", this.l.get(i2).e);
                if (obj2 != null && (obj2 instanceof String)) {
                    intent.putExtra("value", (String) obj2);
                } else if (obj2 != null) {
                    intent.putExtra("value", obj2.toString());
                }
                intent.putExtra("actionId", this.l.get(i2).b);
                intent.putExtra("plug_id", this.l.get(i2).g);
                intent.putExtra("name", this.l.get(i2).f21199a);
                intent.putExtra("tr_id", this.l.get(i2).h + "");
                intent.putExtra("scene_type", 2);
                final Context context2 = context;
                final Object obj3 = obj;
                final SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback2 = innerValueCallback;
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        InnerActionCommon.this.a(context2, (Device) obj3, 3, intent, innerValueCallback2);
                    }
                });
                return 0;
            }
        } else {
            CommonSceneOnline.SceneAttrNumberPicker sceneAttrNumberPicker = (CommonSceneOnline.SceneAttrNumberPicker) this.l.get(i2).i;
            Intent intent2 = new Intent(context, SceneNumberPicker.class);
            intent2.putExtra("max_value", sceneAttrNumberPicker.b);
            intent2.putExtra("min_value", sceneAttrNumberPicker.c);
            intent2.putExtra(Constants.Name.INTERVAL, sceneAttrNumberPicker.d);
            intent2.putExtra("degree", sceneAttrNumberPicker.e);
            intent2.putExtra("show_tags", sceneAttrNumberPicker.i);
            intent2.putExtra("default_value", sceneAttrNumberPicker.h);
            intent2.putExtra("json_tag", sceneAttrNumberPicker.f);
            intent2.putExtra("title", this.l.get(i2).f21199a);
            if (obj2 != null && (obj2 instanceof String)) {
                intent2.putExtra("last_value", (String) obj2);
            } else if (obj2 != null) {
                intent2.putExtra("last_value", obj2.toString());
            }
            innerValueCallback.a(intent2, 103);
            return 103;
        }
    }

    public void a(SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback, Context context, int i2, Object obj, Object obj2) {
        final Intent intent = new Intent(this.l.get(i2).g);
        intent.putExtra("action", this.l.get(i2).e);
        if (obj2 != null && (obj2 instanceof String)) {
            intent.putExtra("value", (String) obj2);
        } else if (obj2 != null) {
            intent.putExtra("value", obj2.toString());
        }
        intent.putExtra("actionId", this.l.get(i2).b);
        intent.putExtra("plug_id", this.l.get(i2).g);
        intent.putExtra("name", this.l.get(i2).f21199a);
        intent.putExtra("tr_id", this.l.get(i2).h + "");
        intent.putExtra("scene_type", 2);
        final Context context2 = context;
        final Object obj3 = obj;
        final SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback2 = innerValueCallback;
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                InnerActionCommon.this.a(context2, (Device) obj3, 3, intent, innerValueCallback2);
            }
        });
    }

    public CommonSceneOnline h() {
        return this.f;
    }

    public void a(Context context, Device device, int i2, Intent intent, SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback) {
        Context context2 = context;
        PluginRecord d = CoreApi.a().d(device.model);
        if (d != null) {
            final XQProgressHorizontalDialog b = XQProgressHorizontalDialog.b(context2, context2.getString(R.string.plugin_downloading) + d.p() + context2.getString(R.string.plugin));
            final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
            final boolean z = !d.l() && !d.k();
            final Context context3 = context;
            final PluginRecord pluginRecord = d;
            PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d, i2, intent, device.newDeviceStat(), (RunningProcess) null, true, new PluginApi.SendMessageCallback(new ValueCallback(innerValueCallback)) {
                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    pluginDownloadTask.a(pluginDownloadTask);
                    if (b != null && (context3 instanceof Activity) && !((Activity) context3).isFinishing() && !((Activity) context3).isDestroyed()) {
                        b.a(100, 0);
                        b.c();
                        b.setCancelable(true);
                        b.show();
                        b.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialogInterface) {
                                CoreApi.a().a(pluginRecord.o(), pluginDownloadTask, (CoreApi.CancelPluginDownloadCallback) null);
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
                        if (b != null) {
                            b.a(100, i);
                        }
                    } else if (b != null) {
                        b.a(100, (int) (f2 * 100.0f));
                    }
                }

                public void onDownloadSuccess(PluginRecord pluginRecord) {
                    if (!z && b != null) {
                        b.dismiss();
                    }
                }

                public void onDownloadFailure(PluginError pluginError) {
                    if (!z && b != null) {
                        b.dismiss();
                    }
                }

                public void onDownloadCancel() {
                    if (!z && b != null) {
                        b.dismiss();
                    }
                }

                public void onSendSuccess(Bundle bundle) {
                    if (z && b != null) {
                        b.dismiss();
                    }
                }

                public void onSendFailure(Error error) {
                    if (z && b != null) {
                        b.dismiss();
                    }
                }

                public void onSendCancel() {
                    if (z && b != null) {
                        b.dismiss();
                    }
                }

                public void onMessageSuccess(Intent intent) {
                    if (this.mObj != null && (this.mObj instanceof ValueCallback)) {
                        ((ValueCallback) this.mObj).f21502a.a(-1, intent);
                    }
                    this.mObj = null;
                }

                public void onMessageFailure(int i, String str) {
                    if (this.mObj != null && (this.mObj instanceof ValueCallback)) {
                        ((ValueCallback) this.mObj).f21502a.a(0, (Intent) null);
                    }
                    this.mObj = null;
                }
            });
        }
    }

    static class ValueCallback {

        /* renamed from: a  reason: collision with root package name */
        SmartHomeSceneActionChooseActivity.InnerValueCallback f21502a;

        public ValueCallback(SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback) {
            this.f21502a = innerValueCallback;
        }
    }
}
