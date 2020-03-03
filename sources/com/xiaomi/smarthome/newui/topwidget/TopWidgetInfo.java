package com.xiaomi.smarthome.newui.topwidget;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.newui.HomeEnvInfoViewModel;
import com.xiaomi.smarthome.newui.roomenv.model.RoomEnvDataItem;
import org.json.JSONException;
import org.json.JSONObject;

public class TopWidgetInfo {

    /* renamed from: a  reason: collision with root package name */
    String f20742a;
    String b;
    String c;

    public static TopWidgetInfo a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        TopWidgetInfo topWidgetInfo = new TopWidgetInfo();
        if (!jSONObject.isNull(RoomEnvDataItem.b)) {
            topWidgetInfo.f20742a = String.valueOf((int) (jSONObject.optDouble(RoomEnvDataItem.b) + 0.5d));
        }
        if (!jSONObject.isNull(RoomEnvDataItem.c)) {
            topWidgetInfo.b = String.valueOf((int) (jSONObject.optDouble(RoomEnvDataItem.c) + 0.5d));
        }
        if (!jSONObject.isNull("temp")) {
            topWidgetInfo.c = String.valueOf((int) (jSONObject.optDouble("temp") + 0.5d));
        }
        return topWidgetInfo;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(RoomEnvDataItem.b, this.f20742a);
            jSONObject.put(RoomEnvDataItem.c, this.b);
            jSONObject.put("temp", this.c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String b() {
        return this.f20742a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        if (TextUtils.isEmpty(this.f20742a)) {
            return "";
        }
        String[] stringArray = SHApplication.getAppContext().getResources().getStringArray(R.array.home_env_info_item_title);
        int indexOf = HomeEnvInfoViewModel.e.indexOf(RoomEnvDataItem.b);
        if (indexOf >= stringArray.length || indexOf < 0 || stringArray.length <= 0) {
            return "";
        }
        return stringArray[indexOf] + this.f20742a;
    }

    public String f() {
        if (TextUtils.isEmpty(this.b)) {
            return "";
        }
        String[] stringArray = SHApplication.getAppContext().getResources().getStringArray(R.array.home_env_info_item_title);
        int indexOf = HomeEnvInfoViewModel.e.indexOf(RoomEnvDataItem.c);
        if (indexOf >= stringArray.length || indexOf < 0 || stringArray.length <= 0) {
            return "";
        }
        return stringArray[indexOf] + this.b + Operators.MOD;
    }

    public String g() {
        if (TextUtils.isEmpty(this.c)) {
            return "";
        }
        String[] stringArray = SHApplication.getAppContext().getResources().getStringArray(R.array.home_env_info_item_title);
        int indexOf = HomeEnvInfoViewModel.e.indexOf("temp");
        if (indexOf >= stringArray.length || indexOf < 0 || stringArray.length <= 0) {
            return "";
        }
        return stringArray[indexOf] + this.c + "℃";
    }
}
