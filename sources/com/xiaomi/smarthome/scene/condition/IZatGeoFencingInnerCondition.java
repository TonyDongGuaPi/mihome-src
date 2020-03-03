package com.xiaomi.smarthome.scene.condition;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.ExternalLoadManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.util.Callback;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.amappoi.AmapGeofencingActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.geofence.manager.GrayLogDelegate;
import org.json.JSONException;
import org.json.JSONObject;

public class IZatGeoFencingInnerCondition extends BaseInnerCondition implements LocaleGetResourceFixHelper {
    public static final int d = 107;

    public int a() {
        return R.string.scene_enter_or_leave_fence;
    }

    public int c(int i) {
        return 101;
    }

    public IZatGeoFencingInnerCondition() {
        super((Device) null);
        this.b = new String[2];
        this.f21535a = new int[2];
        this.b[0] = SHApplication.getAppContext().getString(R.string.enter);
        this.f21535a[0] = 0;
        this.b[1] = SHApplication.getAppContext().getString(R.string.leave);
        this.f21535a[1] = 1;
    }

    public SceneApi.Condition a(int i, Intent intent) {
        SceneApi.Condition condition = new SceneApi.Condition();
        condition.k = 101;
        SceneApi.ConditionIZatGeoFencing conditionIZatGeoFencing = new SceneApi.ConditionIZatGeoFencing();
        conditionIZatGeoFencing.o = "user";
        StringBuilder sb = new StringBuilder();
        if (i == 0) {
            condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE;
            sb.append(SHApplication.getAppContext().getString(R.string.enter));
            conditionIZatGeoFencing.q = SceneApi.ConditionIZatGeoFencing.l;
        } else {
            condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE;
            sb.append(SHApplication.getAppContext().getString(R.string.leave));
            conditionIZatGeoFencing.q = SceneApi.ConditionIZatGeoFencing.m;
        }
        if (intent != null) {
            String stringExtra = intent.getStringExtra("poi_name");
            if (!TextUtils.isEmpty(stringExtra)) {
                sb.append(stringExtra);
                conditionIZatGeoFencing.n = stringExtra;
            }
            long longExtra = intent.getLongExtra(AmapGeofencingActivity.EXTRA_DATA_POID, -1);
            if (longExtra > 0) {
                conditionIZatGeoFencing.p = longExtra;
            }
            conditionIZatGeoFencing.b = sb.toString();
            String stringExtra2 = intent.getStringExtra("value");
            if (!TextUtils.isEmpty(stringExtra2)) {
                try {
                    JSONObject jSONObject = new JSONObject(stringExtra2);
                    GrayLogDelegate.a("select GCJ", "lat:" + jSONObject.optDouble("latitude") + "  ,lon: " + jSONObject.optDouble("longitude"));
                    conditionIZatGeoFencing.s = jSONObject.optDouble("longitude");
                    conditionIZatGeoFencing.r = jSONObject.optDouble("latitude");
                    conditionIZatGeoFencing.t = (float) jSONObject.optDouble("radius");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        condition.i = conditionIZatGeoFencing;
        return condition;
    }

    public int a(int i, Activity activity, SceneApi.Condition condition) {
        ExternalLoadManager.instance.loadExternal(new Callback(activity, condition, i) {
            private final /* synthetic */ Activity f$0;
            private final /* synthetic */ SceneApi.Condition f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call(Object obj) {
                return IZatGeoFencingInnerCondition.a(this.f$0, this.f$1, this.f$2, (Integer) obj);
            }
        });
        return 107;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer a(Activity activity, SceneApi.Condition condition, int i, Integer num) throws Exception {
        if (num.intValue() == 3) {
            Intent intent = new Intent(activity, Class.forName("com.xiaomi.smarthome.newui.amappoi.AmapGeofencingActivity"));
            if (condition == null || condition.i == null) {
                intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_RADIUS, 100.0f);
            } else {
                intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_LATITUDE, condition.i.r);
                intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_LONGITUDE, condition.i.s);
                intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_RADIUS, condition.i.t);
                intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_POID, condition.i.p);
                intent.putExtra("poi_name", condition.i.n);
            }
            intent.putExtra(AmapGeofencingActivity.EXTRA_MIN_R, 200);
            intent.putExtra(AmapGeofencingActivity.EXTRA_MAX_R, 1000);
            if (condition != null && condition.i != null) {
                String str = condition.i.q;
                if (TextUtils.equals(SceneApi.ConditionIZatGeoFencing.l, str)) {
                    intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_SUB_TITLE, activity.getString(R.string.scene_enter_fence));
                } else if (TextUtils.equals(SceneApi.ConditionIZatGeoFencing.m, str)) {
                    intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_SUB_TITLE, activity.getString(R.string.scene_exit_fence));
                }
            } else if (i == 0) {
                intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_SUB_TITLE, activity.getString(R.string.scene_enter_fence));
            } else if (i == 1) {
                intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_SUB_TITLE, activity.getString(R.string.scene_exit_fence));
            }
            activity.startActivityForResult(intent, 107);
        } else {
            ToastUtil.a((int) R.string.mapload_fail);
        }
        return num;
    }

    public int a(SceneApi.Condition condition) {
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE) {
            return 0;
        }
        return condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE ? 1 : -1;
    }

    public void a(SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_fence));
    }

    public String e() {
        return SHApplication.getAppContext().getString(R.string.scene_enter_or_leave_fence);
    }
}
