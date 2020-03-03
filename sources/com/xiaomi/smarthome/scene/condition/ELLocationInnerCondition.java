package com.xiaomi.smarthome.scene.condition;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity;
import com.xiaomi.smarthome.scene.location.model.SceneConditionWifiManager;
import com.xiaomi.smarthome.scene.location.model.WifiGroupData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class ELLocationInnerCondition extends BaseInnerCondition implements LocaleGetResourceFixHelper {
    public int a() {
        return R.string.scene_enter_or_leave_wifi;
    }

    public int c(int i) {
        return 101;
    }

    public ELLocationInnerCondition() {
        super((Device) null);
        this.b = new String[2];
        this.f21535a = new int[2];
        this.b[0] = SHApplication.getAppContext().getString(R.string.enter);
        this.f21535a[0] = 0;
        this.b[1] = SHApplication.getAppContext().getString(R.string.leave);
        this.f21535a[1] = 1;
    }

    public SceneApi.Condition a(int i, Intent intent) {
        String str;
        SceneApi.Condition condition = new SceneApi.Condition();
        condition.k = 101;
        SceneApi.ConditionELLocation conditionELLocation = new SceneApi.ConditionELLocation();
        if (intent != null) {
            int intExtra = intent.getIntExtra("wifi_size", -1);
            if (i == 0) {
                str = SHApplication.getAppContext().getString(R.string.enter);
            } else {
                str = SHApplication.getAppContext().getString(R.string.leave);
            }
            if (intExtra == 1) {
                conditionELLocation.b = SHApplication.getAppContext().getString(R.string.one_wifi, new Object[]{str, intent.getStringExtra("name")});
            } else if (intExtra <= 0) {
                conditionELLocation.b = "";
            } else {
                conditionELLocation.b = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.etc, intExtra, new Object[]{str, intent.getStringExtra("name")});
            }
            conditionELLocation.n = intent.getStringExtra("value");
        }
        if (i == 0) {
            condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.COME_LOC;
            conditionELLocation.o = SceneApi.ConditionELLocation.l + SceneConditionWifiManager.a().c();
        } else {
            condition.f21522a = SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC;
            conditionELLocation.o = SceneApi.ConditionELLocation.m + SceneConditionWifiManager.a().c();
        }
        condition.h = conditionELLocation;
        return condition;
    }

    public int a(SceneApi.Condition condition) {
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC) {
            return 0;
        }
        return condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC ? 1 : -1;
    }

    public int a(int i, Activity activity, SceneApi.Condition condition) {
        Intent intent = new Intent(activity, ScenePoiSelectWifiActivity.class);
        if (!(condition == null || condition.h == null)) {
            WifiGroupData wifiGroupData = new WifiGroupData();
            String str = condition.h.o;
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith(SceneApi.ConditionELLocation.l)) {
                    wifiGroupData.a(str.substring(SceneApi.ConditionELLocation.l.length()));
                    intent.putExtra("mode", SceneApi.ConditionELLocation.l);
                } else if (str.startsWith(SceneApi.ConditionELLocation.m)) {
                    wifiGroupData.a(str.substring(SceneApi.ConditionELLocation.m.length()));
                    intent.putExtra("mode", SceneApi.ConditionELLocation.m);
                }
            }
            if (!TextUtils.isEmpty(condition.h.n)) {
                try {
                    JSONArray jSONArray = new JSONArray(condition.h.n);
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        arrayList.add(jSONArray.optString(i2));
                    }
                    wifiGroupData.a((List<String>) arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            intent.putExtra("data", wifiGroupData);
        }
        activity.startActivityForResult(intent, 103);
        return 103;
    }

    public String e() {
        return SHApplication.getAppContext().getString(R.string.scene_enter_or_leave_wifi);
    }

    public void a(SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_wifi_poi));
    }
}
