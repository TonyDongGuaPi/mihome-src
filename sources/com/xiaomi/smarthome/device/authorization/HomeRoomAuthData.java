package com.xiaomi.smarthome.device.authorization;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.authorization.BaseAuthData;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomeRoomAuthData extends BaseAuthData {
    private boolean e = true;
    private boolean f = true;
    private boolean g = true;
    private String h;
    private List<String> i = new ArrayList();

    private HomeRoomAuthData() {
    }

    public static HomeRoomAuthData a(String str, String str2) {
        HomeRoomAuthData homeRoomAuthData = new HomeRoomAuthData();
        homeRoomAuthData.f15013a = str;
        Home j = HomeManager.a().j(str2);
        if (j != null) {
            homeRoomAuthData.h = j.j();
            if (j.d() != null) {
                for (Room d : j.d()) {
                    homeRoomAuthData.i.add(d.d());
                }
            }
        }
        homeRoomAuthData.f = true;
        homeRoomAuthData.g = true;
        homeRoomAuthData.e = true;
        return homeRoomAuthData;
    }

    public static HomeRoomAuthData a(JSONObject jSONObject) {
        return b(jSONObject);
    }

    public static HomeRoomAuthData b(JSONObject jSONObject) {
        JSONArray optJSONArray;
        HomeRoomAuthData homeRoomAuthData = new HomeRoomAuthData();
        if (!jSONObject.isNull("voice_did")) {
            homeRoomAuthData.a(jSONObject.optString("voice_did"));
        }
        if (!jSONObject.isNull("ctrl_devices") && (optJSONArray = jSONObject.optJSONArray("ctrl_devices")) != null && optJSONArray.length() > 0) {
            homeRoomAuthData.c.clear();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (!optJSONObject.isNull("did") && !optJSONObject.isNull("ctrlable")) {
                    BaseAuthData.VoiceContrlData voiceContrlData = new BaseAuthData.VoiceContrlData(optJSONObject.optString("did"), optJSONObject.optString("ctrlable"), i2);
                    voiceContrlData.c = optJSONObject.optString("name");
                    voiceContrlData.d = optJSONObject.optString("model");
                    homeRoomAuthData.c.add(voiceContrlData);
                }
            }
        }
        if (!jSONObject.isNull("auth_all_room")) {
            homeRoomAuthData.e = jSONObject.optBoolean("auth_all_room");
        }
        if (!jSONObject.isNull("ctrl_share_device")) {
            homeRoomAuthData.f = jSONObject.optBoolean("ctrl_share_device");
        }
        if (!jSONObject.isNull("ctrl_othercloud_device")) {
            homeRoomAuthData.g = jSONObject.optBoolean("ctrl_othercloud_device");
        }
        if (!jSONObject.isNull("home")) {
            homeRoomAuthData.h = jSONObject.optString("home");
        }
        if (homeRoomAuthData.e) {
            Home j = HomeManager.a().j(homeRoomAuthData.h);
            if (!(j == null || j.d() == null)) {
                for (Room d : j.d()) {
                    homeRoomAuthData.i.add(d.d());
                }
            }
        } else if (!jSONObject.isNull("rooms")) {
            JSONArray optJSONArray2 = jSONObject.optJSONArray("rooms");
            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                String optString = optJSONArray2.optString(i3);
                Room i4 = HomeManager.a().i(optString);
                if (i4 != null && TextUtils.equals(i4.f(), homeRoomAuthData.h)) {
                    homeRoomAuthData.i.add(optString);
                }
            }
        }
        return homeRoomAuthData;
    }

    public String b() {
        return this.f15013a;
    }

    public void a(String str) {
        this.f15013a = str;
    }

    public boolean c() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean d() {
        return this.f;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public boolean e() {
        return this.g;
    }

    public void c(boolean z) {
        this.g = z;
    }

    public String f() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public List<String> g() {
        return this.i;
    }

    public void a(List<String> list) {
        this.i = list;
    }
}
