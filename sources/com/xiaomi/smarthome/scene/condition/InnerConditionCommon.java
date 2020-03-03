package com.xiaomi.smarthome.scene.condition;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.common.Constants;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.ExternalLoadManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.plugin.util.Callback;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
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
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.amappoi.AmapGeofencingActivity;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import com.xiaomi.smarthome.scene.SceneNumberPicker;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.StartConditionActivityNew;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.setting.PluginSetting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class InnerConditionCommon extends BaseInnerCondition {
    public static final int d = -99;
    public static final int f = -2;
    static final int g = 103;
    static final int h = 104;
    protected int[] e;
    private CommonSceneOnline i;
    private String[] j;
    private int[] k;
    private List<CommonSceneOnline.CommonSceneCondition> l = new ArrayList();
    private int[] m;

    public InnerConditionCommon(Device device, SmartHomeSceneCreateEditActivity.DefaultSceneItemSet defaultSceneItemSet) {
        super(device);
        boolean z;
        if (SceneManager.x().a(device.model, device.did) != null && SceneManager.x().a(device.model, device.did).e.size() > 0) {
            this.i = SceneManager.x().a(device.model, device.did);
            if (defaultSceneItemSet == null || defaultSceneItemSet.c == null || defaultSceneItemSet.c.length == 0) {
                this.l.addAll(this.i.e);
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
                Iterator<CommonSceneOnline.CommonSceneCondition> it = this.i.e.iterator();
                while (it.hasNext()) {
                    CommonSceneOnline.CommonSceneCondition next = it.next();
                    int length2 = keyArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            z = false;
                            break;
                        } else {
                            RecommendSceneItem.Key key2 = keyArr[i4];
                            i4 = (!key2.mKey.equalsIgnoreCase(next.f) || (!(key2 == null && next.g == null) && (key2 == null || next.g == null || !key2.mValues.equals(next.g)))) ? i4 + 1 : i4;
                        }
                    }
                    z = true;
                    if (z) {
                        this.l.add(next);
                    }
                }
            }
            this.b = new String[this.l.size()];
            this.f21535a = new int[this.l.size()];
            this.e = new int[this.l.size()];
            for (int i5 = 0; i5 < this.l.size(); i5++) {
                this.f21535a[i5] = i5;
                this.e[i5] = this.l.get(i5).d;
                this.b[i5] = this.i.e.get(i5).f21200a;
            }
            if (this.i.g.size() > 0) {
                this.j = new String[this.i.g.size()];
                this.k = new int[this.i.g.size()];
                for (int i6 = 0; i6 < this.i.g.size(); i6++) {
                    this.j[i6] = this.i.g.get(i6).f21201a;
                    this.k[i6] = this.i.g.get(i6).b;
                }
                this.m = new int[this.l.size()];
                for (int i7 = 0; i7 < this.m.length; i7++) {
                    this.m[i7] = this.i.e.get(i7).c;
                }
            }
        }
    }

    public CommonSceneOnline a() {
        return this.i;
    }

    public void a(SimpleDraweeView simpleDraweeView) {
        DeviceFactory.b(this.c.model, simpleDraweeView);
    }

    public String d(int i2) {
        for (int i3 = 0; i3 < this.k.length; i3++) {
            if (this.k[i3] == i2) {
                return this.j[i3];
            }
        }
        return null;
    }

    public int[] h() {
        return this.k;
    }

    public int e(int i2) {
        return this.m[i2];
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.scene.api.SceneApi.Condition a(int r6, android.content.Intent r7) {
        /*
            r5 = this;
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r0 = new com.xiaomi.smarthome.scene.api.SceneApi$Condition
            r0.<init>()
            com.xiaomi.smarthome.scene.api.SceneApi$Condition$LAUNCH_TYPE r1 = com.xiaomi.smarthome.scene.api.SceneApi.Condition.LAUNCH_TYPE.DEVICE
            r0.f21522a = r1
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDeviceCommon r1 = new com.xiaomi.smarthome.scene.api.SceneApi$ConditionDeviceCommon
            r1.<init>()
            com.xiaomi.smarthome.device.Device r2 = r5.c
            java.lang.String r2 = r2.did
            r1.f21523a = r2
            r2 = 0
            r3 = 0
        L_0x0016:
            int[] r4 = r5.f21535a
            int r4 = r4.length
            if (r3 >= r4) goto L_0x002a
            int[] r4 = r5.f21535a
            r4 = r4[r3]
            if (r6 != r4) goto L_0x0027
            int[] r4 = r5.e
            r4 = r4[r3]
            r0.k = r4
        L_0x0027:
            int r3 = r3 + 1
            goto L_0x0016
        L_0x002a:
            com.xiaomi.smarthome.scene.CommonSceneOnline r3 = r5.i
            java.util.ArrayList<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition> r3 = r3.e
            java.lang.Object r3 = r3.get(r6)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition r3 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneCondition) r3
            java.lang.String r3 = r3.f21200a
            r1.b = r3
            if (r7 == 0) goto L_0x004a
            java.lang.String r3 = "key_name"
            boolean r3 = r7.hasExtra(r3)
            if (r3 == 0) goto L_0x004a
            java.lang.String r3 = "key_name"
            java.lang.String r3 = r7.getStringExtra(r3)
            r1.b = r3
        L_0x004a:
            com.xiaomi.smarthome.device.Device r3 = r5.c
            java.lang.String r3 = r3.name
            r1.c = r3
            r3 = 0
        L_0x0051:
            int[] r4 = r5.f21535a
            int r4 = r4.length
            if (r3 >= r4) goto L_0x0065
            int[] r4 = r5.f21535a
            r4 = r4[r3]
            if (r6 != r4) goto L_0x0062
            int[] r4 = r5.e
            r4 = r4[r3]
            r0.k = r4
        L_0x0062:
            int r3 = r3 + 1
            goto L_0x0051
        L_0x0065:
            com.xiaomi.smarthome.scene.CommonSceneOnline r3 = r5.i
            java.util.ArrayList<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition> r3 = r3.e
            java.lang.Object r3 = r3.get(r6)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition r3 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneCondition) r3
            java.lang.String r3 = r3.f
            r1.j = r3
            com.xiaomi.smarthome.device.Device r3 = r5.c
            java.lang.String r3 = r3.model
            r1.d = r3
            com.xiaomi.smarthome.scene.CommonSceneOnline r3 = r5.i
            java.util.ArrayList<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition> r3 = r3.e
            java.lang.Object r3 = r3.get(r6)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition r3 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneCondition) r3
            java.lang.String r3 = r3.f
            r1.j = r3
            com.xiaomi.smarthome.scene.CommonSceneOnline r3 = r5.i
            java.util.ArrayList<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition> r3 = r3.e
            java.lang.Object r3 = r3.get(r6)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition r3 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneCondition) r3
            int r3 = r3.b
            r1.k = r3
            r0.c = r1
            if (r7 == 0) goto L_0x00e1
            java.lang.String r3 = "value"
            boolean r3 = r7.hasExtra(r3)
            if (r3 == 0) goto L_0x00e1
            java.lang.String r6 = "value"
            java.lang.String r6 = r7.getStringExtra(r6)
            if (r6 == 0) goto L_0x00d4
            r6 = 1
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00b8 }
            java.lang.String r4 = "value"
            java.lang.String r4 = r7.getStringExtra(r4)     // Catch:{ JSONException -> 0x00b8 }
            r3.<init>(r4)     // Catch:{ JSONException -> 0x00b8 }
            r1.l = r3     // Catch:{ JSONException -> 0x00b8 }
            r2 = 1
        L_0x00b8:
            if (r2 != 0) goto L_0x00c8
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r4 = "value"
            java.lang.String r4 = r7.getStringExtra(r4)     // Catch:{ JSONException -> 0x00c8 }
            r3.<init>(r4)     // Catch:{ JSONException -> 0x00c8 }
            r1.l = r3     // Catch:{ JSONException -> 0x00c8 }
            goto L_0x00c9
        L_0x00c8:
            r6 = r2
        L_0x00c9:
            if (r6 != 0) goto L_0x00ef
            java.lang.String r6 = "value"
            java.lang.String r6 = r7.getStringExtra(r6)
            r1.l = r6
            goto L_0x00ef
        L_0x00d4:
            android.os.Bundle r6 = r7.getExtras()
            java.lang.String r7 = "value"
            java.lang.Object r6 = r6.get(r7)
            r1.l = r6
            goto L_0x00ef
        L_0x00e1:
            com.xiaomi.smarthome.scene.CommonSceneOnline r7 = r5.i
            java.util.ArrayList<com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition> r7 = r7.e
            java.lang.Object r6 = r7.get(r6)
            com.xiaomi.smarthome.scene.CommonSceneOnline$CommonSceneCondition r6 = (com.xiaomi.smarthome.scene.CommonSceneOnline.CommonSceneCondition) r6
            java.lang.Object r6 = r6.g
            r1.l = r6
        L_0x00ef:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.condition.InnerConditionCommon.a(int, android.content.Intent):com.xiaomi.smarthome.scene.api.SceneApi$Condition");
    }

    public int c(int i2) {
        for (int i3 = 0; i3 < this.f21535a.length; i3++) {
            if (i2 == this.f21535a[i3]) {
                return this.e[i3];
            }
        }
        return -1;
    }

    public int a(SceneApi.Condition condition) {
        if (!(this.c == null || condition == null || condition.c == null || !this.c.did.equalsIgnoreCase(condition.c.f21523a) || condition.c == null || !(condition.c instanceof SceneApi.ConditionDeviceCommon))) {
            for (int i2 = 0; i2 < this.f21535a.length; i2++) {
                if (condition.c.k == -1 || this.i.e.get(i2).b == -1) {
                    if (this.i.e.get(i2).f.equalsIgnoreCase(condition.c.j) && ((this.i.e.get(i2).g == null && ((SceneApi.ConditionDeviceCommon) condition.c).l == null) || (this.i.e.get(i2).g != null && ((SceneApi.ConditionDeviceCommon) condition.c).l != null && SmartHomeSceneUtility.a(this.i.e.get(i2).g, ((SceneApi.ConditionDeviceCommon) condition.c).l)))) {
                        return i2;
                    }
                    if (this.i.e.get(i2).f.equalsIgnoreCase(condition.c.j)) {
                        return -2;
                    }
                } else if (condition.c.k == this.i.e.get(i2).b) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public int a(String str, Object obj) {
        for (int i2 = 0; i2 < this.f21535a.length; i2++) {
            if (this.i.e.get(i2).f.equalsIgnoreCase(str) && ((this.i.e.get(i2).g == null && obj == null) || (this.i.e.get(i2).g != null && obj != null && SmartHomeSceneUtility.a(this.i.e.get(i2).g, obj)))) {
                return i2;
            }
        }
        return -1;
    }

    public int a(int i2, final Activity activity, SceneApi.Condition condition) {
        JSONObject a2;
        if (this.l.get(i2).i != null) {
            if (this.l.get(i2).i instanceof CommonSceneOnline.SceneAttrNumberPicker) {
                CommonSceneOnline.SceneAttrNumberPicker sceneAttrNumberPicker = (CommonSceneOnline.SceneAttrNumberPicker) this.l.get(i2).i;
                Intent intent = new Intent(activity, SceneNumberPicker.class);
                intent.putExtra("max_value", sceneAttrNumberPicker.b);
                intent.putExtra("min_value", sceneAttrNumberPicker.c);
                intent.putExtra(Constants.Name.INTERVAL, sceneAttrNumberPicker.d);
                intent.putExtra("degree", sceneAttrNumberPicker.e);
                intent.putExtra("json_tag", sceneAttrNumberPicker.f);
                intent.putExtra("show_tags", sceneAttrNumberPicker.i);
                intent.putExtra("default_value", sceneAttrNumberPicker.h);
                intent.putExtra("title", this.l.get(i2).f21200a);
                intent.putExtra("formatter", sceneAttrNumberPicker.g);
                if (condition != null) {
                    intent.putExtra("last_value", String.valueOf(((SceneApi.ConditionDeviceCommon) condition.c).l));
                }
                activity.startActivityForResult(intent, 103);
                return 103;
            } else if (this.l.get(i2).i instanceof CommonSceneOnline.SceneAttrFencing) {
                ExternalLoadManager.instance.loadExternal(new Callback(i2, activity) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ Activity f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final Object call(Object obj) {
                        return InnerConditionCommon.this.a(this.f$1, this.f$2, (Integer) obj);
                    }
                });
                return 104;
            }
        }
        PluginRecord d2 = CoreApi.a().d(this.c.model);
        if (d2 != null && d2.l() && d2.h().q()) {
            if (DevelopSharePreManager.a().g() && (a2 = RnDebugFileUtil.a(this.c.model)) != null) {
                boolean optBoolean = a2.optBoolean(RnDebugConstant.c, false);
                boolean optBoolean2 = a2.optBoolean(RnDebugConstant.e, false);
                String optString = a2.optString(RnDebugConstant.d, "");
                RnPluginLog.a("native scene debug,  model: " + this.c.model + "  isCheck: " + optBoolean + "   sceneId: " + optString + "   scenIdIsCheck: " + optBoolean2 + " id: " + this.l.get(i2).b);
                if (optBoolean && optBoolean2) {
                    if (optString.equals(this.l.get(i2).b + "")) {
                        RnPluginLog.a("native scene debug, will launch rn plugin  condition is " + this.l.get(i2).b);
                        b(i2, activity, condition);
                        return 0;
                    }
                }
            }
            List<String> b = PluginSetting.b(d2.w());
            if (b.contains(this.l.get(i2).b + "")) {
                RnPluginLog.a("will launch rn plugin  condition is " + this.l.get(i2).b);
                b(i2, activity, condition);
                return 0;
            }
        }
        if (TextUtils.isEmpty(this.l.get(i2).h)) {
            return -2;
        }
        final Intent intent2 = new Intent(this.l.get(i2).h);
        intent2.putExtra("action", this.l.get(i2).f);
        intent2.putExtra("value", String.valueOf(this.l.get(i2).g));
        if (condition != null) {
            intent2.putExtra("last_value", String.valueOf(((SceneApi.ConditionDeviceCommon) condition.c).l));
        }
        intent2.putExtra("actionId", this.l.get(i2).b);
        intent2.putExtra("name", this.l.get(i2).f21200a);
        intent2.putExtra("plug_id", this.l.get(i2).h);
        intent2.putExtra("tr_id", this.l.get(i2).d + "");
        intent2.putExtra("scene_type", 1);
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                InnerConditionCommon.this.a(activity, InnerConditionCommon.this.c, 3, intent2);
            }
        });
        return 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer a(int i2, Activity activity, Integer num) throws Exception {
        if (num.intValue() == 3) {
            CommonSceneOnline.SceneAttrFencing sceneAttrFencing = (CommonSceneOnline.SceneAttrFencing) this.l.get(i2).i;
            Intent intent = new Intent(activity, Class.forName("com.xiaomi.smarthome.newui.amappoi.AmapGeofencingActivity"));
            intent.putExtra(AmapGeofencingActivity.EXTRA_MIN_R, 500);
            intent.putExtra(AmapGeofencingActivity.EXTRA_MAX_R, 2000);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_LATITUDE, (double) sceneAttrFencing.c);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_LONGITUDE, (double) sceneAttrFencing.b);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_RADIUS, sceneAttrFencing.d);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_SUB_TITLE, sceneAttrFencing.e);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_ACT_NAME, sceneAttrFencing.g);
            intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_RADIUS_DEGREE, sceneAttrFencing.f);
            activity.startActivityForResult(intent, 104);
        } else {
            ToastUtil.a((int) R.string.mapload_fail);
        }
        return num;
    }

    public void a(Context context, Device device, int i2, Intent intent) {
        Context context2 = context;
        PluginRecord d2 = CoreApi.a().d(device.model);
        if (d2 != null) {
            final XQProgressHorizontalDialog b = XQProgressHorizontalDialog.b(context, context.getString(R.string.plugin_downloading) + d2.p() + context.getString(R.string.plugin));
            final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
            final boolean z = !d2.l() && !d2.k();
            final Context context3 = context;
            final PluginRecord pluginRecord = d2;
            PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), pluginRecord, i2, intent, device.newDeviceStat(), (RunningProcess) null, true, new PluginApi.SendMessageCallback() {
                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    pluginDownloadTask.a(pluginDownloadTask);
                    if (b != null && (context3 instanceof Activity) && !((Activity) context3).isFinishing()) {
                        if (Build.VERSION.SDK_INT < 17 || !((Activity) context3).isDestroyed()) {
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
                    ((StartConditionActivityNew) context3).onActivityResult(-1, intent);
                }

                public void onMessageFailure(int i, String str) {
                    ((StartConditionActivityNew) context3).onActivityResult(0, (Intent) null);
                }
            });
        }
    }

    public void b(int i2, final Activity activity, SceneApi.Condition condition) {
        final Intent intent = new Intent(this.l.get(i2).h);
        intent.putExtra("action", this.l.get(i2).f);
        intent.putExtra("value", String.valueOf(this.l.get(i2).g));
        if (condition != null) {
            intent.putExtra("last_value", String.valueOf(((SceneApi.ConditionDeviceCommon) condition.c).l));
        }
        intent.putExtra("actionId", this.l.get(i2).b);
        intent.putExtra("name", this.l.get(i2).f21200a);
        intent.putExtra("plug_id", this.l.get(i2).h);
        intent.putExtra("tr_id", this.l.get(i2).d + "");
        intent.putExtra("scene_type", 1);
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                InnerConditionCommon.this.a(activity, InnerConditionCommon.this.c, 3, intent);
            }
        });
    }
}
