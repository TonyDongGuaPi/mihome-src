package com.xiaomi.smarthome.scenenew.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.scene.action.PushInnerAction;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.ClickInnerCondition;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.scenenew.model.RecommendSceneInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendSceneDetailInfo implements Parcelable {
    public static final Parcelable.Creator<RecommendSceneDetailInfo> CREATOR = new Parcelable.Creator<RecommendSceneDetailInfo>() {
        /* renamed from: a */
        public RecommendSceneDetailInfo createFromParcel(Parcel parcel) {
            return new RecommendSceneDetailInfo(parcel);
        }

        /* renamed from: a */
        public RecommendSceneDetailInfo[] newArray(int i) {
            return new RecommendSceneDetailInfo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public List<ConditionItem> f21985a;
    public List<ActionItem> b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public int h;
    public int i;

    public int describeContents() {
        return 0;
    }

    public class ConditionItem {

        /* renamed from: a  reason: collision with root package name */
        public SceneApi.Condition f21987a;
        public List<Device> b = new ArrayList();
        public JSONArray c = new JSONArray();
        public HashMap<String, Boolean> d = new HashMap<>();
        public JSONObject e = new JSONObject();
        public String f;
        public String g;
        public String h;
        public String i;

        public ConditionItem() {
        }
    }

    public class ActionItem {

        /* renamed from: a  reason: collision with root package name */
        public SceneApi.Action f21986a;
        public List<Device> b = new ArrayList();
        public JSONArray c = new JSONArray();
        public HashMap<String, Boolean> d = new HashMap<>();
        public JSONObject e = new JSONObject();
        public int f;
        public String g;
        public String h;

        public ActionItem() {
        }
    }

    public void a(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        if (recommendSceneItem != null) {
            this.c = recommendSceneItem.gifUrl;
            this.d = recommendSceneItem.url;
            this.g = recommendSceneItem.enable;
            this.h = recommendSceneItem.enable_push;
            this.i = recommendSceneItem.st_id;
            this.e = recommendSceneItem.sr_id;
            this.f = recommendSceneItem.intro;
            try {
                List<PluginRecommendSceneInfo.ConditionActionItem> list = recommendSceneItem.mConditionList;
                this.f21985a.clear();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    PluginRecommendSceneInfo.ConditionActionItem conditionActionItem = list.get(i2);
                    ConditionItem conditionItem = new ConditionItem();
                    conditionItem.c = conditionActionItem.mGidJArray;
                    conditionItem.e = conditionActionItem.modelListJobj;
                    conditionItem.f = conditionActionItem.mConditionSrc;
                    conditionItem.g = conditionActionItem.mConditionKey;
                    conditionItem.h = conditionActionItem.name;
                    if (conditionItem.f.equalsIgnoreCase("device")) {
                        JSONObject jSONObject = conditionActionItem.modelListJobj;
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            List<Device> g2 = SmartHomeDeviceManager.a().g(keys.next());
                            if (g2 != null) {
                                conditionItem.b.addAll(g2);
                            }
                        }
                        if (conditionItem.b.size() > 0) {
                            conditionItem.f21987a = b(conditionItem.b.get(0).model, jSONObject.getString(conditionItem.b.get(0).model));
                            if (!(RecommendSceneManager.a().b().b == null || (optJSONObject2 = RecommendSceneManager.a().b().b.optJSONObject(jSONObject.getString(conditionItem.b.get(0).model))) == null)) {
                                conditionItem.i = optJSONObject2.optString("fw_version", "");
                            }
                        } else {
                            Iterator<String> keys2 = jSONObject.keys();
                            if (keys2.hasNext()) {
                                String next = keys2.next();
                                conditionItem.f21987a = b(next, jSONObject.getString(next));
                            }
                        }
                    } else if (conditionItem.f.equalsIgnoreCase("user")) {
                        if (conditionActionItem.mConditionKey.equalsIgnoreCase("click") || TextUtils.isEmpty(conditionActionItem.mConditionKey)) {
                            conditionItem.f21987a = new ClickInnerCondition((Device) null).a(0, (Intent) null);
                        }
                    } else if (conditionActionItem.mConditionSrc.equalsIgnoreCase("cloud")) {
                        String optString = conditionActionItem.modelListJobj.optString("virtual");
                        if (!TextUtils.isEmpty(optString)) {
                            conditionItem.f21987a = SceneApi.Condition.a(RecommendSceneManager.a().b().b.optJSONObject(optString));
                            if (conditionItem.f21987a.j == null) {
                                conditionItem.f21987a.j = new SceneApi.ConditionWeather();
                            }
                            conditionItem.f21987a.j.e = AreaInfoManager.a().f();
                            conditionItem.f21987a.j.f = AreaInfoManager.a().n();
                            SceneApi.ConditionWeather conditionWeather = conditionItem.f21987a.j;
                            conditionWeather.g = AreaInfoManager.a().n() + conditionItem.f21987a.j.b;
                        }
                    } else {
                        if (conditionActionItem.mConditionSrc.equalsIgnoreCase("timer")) {
                            String optString2 = conditionActionItem.modelListJobj.optString("virtual");
                            if (!TextUtils.isEmpty(optString2)) {
                                conditionItem.f21987a = SceneApi.Condition.a(RecommendSceneManager.a().b().b.optJSONObject(optString2));
                            }
                        }
                    }
                    this.f21985a.add(conditionItem);
                }
                List<PluginRecommendSceneInfo.ConditionActionItem> list2 = recommendSceneItem.mActionList;
                this.b.clear();
                for (int i3 = 0; i3 < list2.size(); i3++) {
                    PluginRecommendSceneInfo.ConditionActionItem conditionActionItem2 = list2.get(i3);
                    ActionItem actionItem = new ActionItem();
                    actionItem.e = conditionActionItem2.modelListJobj;
                    actionItem.c = conditionActionItem2.mGidJArray;
                    actionItem.f = conditionActionItem2.actionType;
                    actionItem.g = conditionActionItem2.name;
                    if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                        JSONObject jSONObject2 = conditionActionItem2.modelListJobj;
                        Iterator<String> keys3 = jSONObject2.keys();
                        while (keys3.hasNext()) {
                            List<Device> g3 = SmartHomeDeviceManager.a().g(keys3.next());
                            if (g3 != null) {
                                actionItem.b.addAll(g3);
                            }
                        }
                        if (actionItem.b.size() > 0) {
                            actionItem.f21986a = a(actionItem.b.get(0).model, jSONObject2.getString(actionItem.b.get(0).model));
                            if (!(RecommendSceneManager.a().b().f21988a == null || (optJSONObject = RecommendSceneManager.a().b().f21988a.optJSONObject(jSONObject2.getString(actionItem.b.get(0).model))) == null)) {
                                actionItem.h = optJSONObject.optString("fw_version", "");
                            }
                        } else {
                            Iterator<String> keys4 = jSONObject2.keys();
                            if (keys4.hasNext()) {
                                String next2 = keys4.next();
                                actionItem.f21986a = a(next2, jSONObject2.getString(next2));
                            }
                        }
                    } else if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_PUSH.value) {
                        actionItem.f21986a = new PushInnerAction().a("", 0, (Object) null, (Intent) null);
                        ((SceneApi.SHScenePushPayload) actionItem.f21986a.g).b = this.f;
                    }
                    this.b.add(actionItem);
                }
                b();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(RecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        if (recommendSceneItem != null) {
            this.c = recommendSceneItem.h;
            this.d = recommendSceneItem.g;
            this.g = recommendSceneItem.d;
            this.h = recommendSceneItem.e;
            this.i = recommendSceneItem.c;
            this.e = recommendSceneItem.f21990a;
            this.f = recommendSceneItem.b;
            try {
                List<RecommendSceneInfo.ConditionActionItem> list = recommendSceneItem.i;
                this.f21985a.clear();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    RecommendSceneInfo.ConditionActionItem conditionActionItem = list.get(i2);
                    ConditionItem conditionItem = new ConditionItem();
                    conditionItem.c = conditionActionItem.c;
                    conditionItem.e = conditionActionItem.f21989a;
                    conditionItem.f = conditionActionItem.d;
                    conditionItem.g = conditionActionItem.e;
                    conditionItem.h = conditionActionItem.b;
                    if (conditionItem.f.equalsIgnoreCase("device")) {
                        JSONObject jSONObject = conditionActionItem.f21989a;
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            List<Device> g2 = SmartHomeDeviceManager.a().g(keys.next());
                            if (g2 != null) {
                                conditionItem.b.addAll(g2);
                            }
                        }
                        if (conditionItem.b.size() > 0) {
                            conditionItem.f21987a = b(conditionItem.b.get(0).model, jSONObject.getString(conditionItem.b.get(0).model));
                            if (!(RecommendSceneManager.a().b().b == null || (optJSONObject2 = RecommendSceneManager.a().b().b.optJSONObject(jSONObject.getString(conditionItem.b.get(0).model))) == null)) {
                                conditionItem.i = optJSONObject2.optString("fw_version", "");
                            }
                        } else {
                            Iterator<String> keys2 = jSONObject.keys();
                            if (keys2.hasNext()) {
                                String next = keys2.next();
                                conditionItem.f21987a = b(next, jSONObject.getString(next));
                            }
                        }
                    } else if (conditionItem.f.equalsIgnoreCase("user")) {
                        if (conditionActionItem.e.equalsIgnoreCase("click") || TextUtils.isEmpty(conditionActionItem.e)) {
                            conditionItem.f21987a = new ClickInnerCondition((Device) null).a(0, (Intent) null);
                        }
                    } else if (conditionActionItem.d.equalsIgnoreCase("cloud")) {
                        String optString = conditionActionItem.f21989a.optString("virtual");
                        if (!TextUtils.isEmpty(optString)) {
                            conditionItem.f21987a = SceneApi.Condition.a(RecommendSceneManager.a().b().b.optJSONObject(optString));
                            if (conditionItem.f21987a.j == null) {
                                conditionItem.f21987a.j = new SceneApi.ConditionWeather();
                            }
                            conditionItem.f21987a.j.e = AreaInfoManager.a().f();
                            conditionItem.f21987a.j.f = AreaInfoManager.a().n();
                            SceneApi.ConditionWeather conditionWeather = conditionItem.f21987a.j;
                            conditionWeather.g = AreaInfoManager.a().n() + conditionItem.f21987a.j.b;
                        }
                    } else {
                        if (conditionActionItem.d.equalsIgnoreCase("timer")) {
                            String optString2 = conditionActionItem.f21989a.optString("virtual");
                            if (!TextUtils.isEmpty(optString2)) {
                                conditionItem.f21987a = SceneApi.Condition.a(RecommendSceneManager.a().b().b.optJSONObject(optString2));
                            }
                        }
                    }
                    this.f21985a.add(conditionItem);
                }
                List<RecommendSceneInfo.ConditionActionItem> list2 = recommendSceneItem.j;
                this.b.clear();
                for (int i3 = 0; i3 < list2.size(); i3++) {
                    RecommendSceneInfo.ConditionActionItem conditionActionItem2 = list2.get(i3);
                    ActionItem actionItem = new ActionItem();
                    actionItem.e = conditionActionItem2.f21989a;
                    actionItem.c = conditionActionItem2.c;
                    actionItem.f = conditionActionItem2.f;
                    actionItem.g = conditionActionItem2.b;
                    if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                        JSONObject jSONObject2 = conditionActionItem2.f21989a;
                        Iterator<String> keys3 = jSONObject2.keys();
                        while (keys3.hasNext()) {
                            List<Device> g3 = SmartHomeDeviceManager.a().g(keys3.next());
                            if (g3 != null) {
                                actionItem.b.addAll(g3);
                            }
                        }
                        if (actionItem.b.size() > 0) {
                            actionItem.f21986a = a(actionItem.b.get(0).model, jSONObject2.getString(actionItem.b.get(0).model));
                            if (!(RecommendSceneManager.a().b().f21988a == null || (optJSONObject = RecommendSceneManager.a().b().f21988a.optJSONObject(jSONObject2.getString(actionItem.b.get(0).model))) == null)) {
                                actionItem.h = optJSONObject.optString("fw_version", "");
                            }
                        } else {
                            Iterator<String> keys4 = jSONObject2.keys();
                            if (keys4.hasNext()) {
                                String next2 = keys4.next();
                                actionItem.f21986a = a(next2, jSONObject2.getString(next2));
                            }
                        }
                    } else if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_PUSH.value) {
                        actionItem.f21986a = new PushInnerAction().a("", 0, (Object) null, (Intent) null);
                        ((SceneApi.SHScenePushPayload) actionItem.f21986a.g).b = this.f;
                    }
                    this.b.add(actionItem);
                }
                b();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void b() {
        Room p;
        if (this.i == 30) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                ActionItem actionItem = this.b.get(i2);
                if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                    for (int i3 = 0; i3 < actionItem.b.size(); i3++) {
                        Device device = actionItem.b.get(i3);
                        if (device == null || !SmartHomeDeviceManager.a().h(device.did) || !RecommendSceneManager.a().a(actionItem.h, RecommendSceneManager.a().a(device))) {
                            actionItem.d.put(device.did, false);
                        } else {
                            actionItem.d.put(device.did, true);
                        }
                    }
                }
            }
        } else if (this.i == 15) {
            Room room = null;
            for (int i4 = 0; i4 < this.f21985a.size(); i4++) {
                ConditionItem conditionItem = this.f21985a.get(i4);
                if (conditionItem.f.equalsIgnoreCase("device")) {
                    Room room2 = room;
                    for (int i5 = 0; i5 < conditionItem.b.size(); i5++) {
                        Device device2 = conditionItem.b.get(i5);
                        if (RecommendSceneManager.a().a(conditionItem.i, RecommendSceneManager.a().a(device2)) && (p = HomeManager.a().p(device2.did)) != null) {
                            room2 = a(p);
                        }
                    }
                    room = room2;
                }
            }
            if (room == null) {
                for (int i6 = 0; i6 < this.f21985a.size(); i6++) {
                    ConditionItem conditionItem2 = this.f21985a.get(i6);
                    if (conditionItem2.f.equalsIgnoreCase("device")) {
                        int i7 = 0;
                        while (true) {
                            if (i7 < conditionItem2.b.size()) {
                                Device device3 = conditionItem2.b.get(i7);
                                if (device3 != null && SmartHomeDeviceManager.a().h(device3.did) && RecommendSceneManager.a().a(conditionItem2.i, RecommendSceneManager.a().a(device3))) {
                                    conditionItem2.d.put(device3.did, true);
                                    break;
                                } else {
                                    conditionItem2.d.put(device3.did, false);
                                    i7++;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
                for (int i8 = 0; i8 < this.b.size(); i8++) {
                    ActionItem actionItem2 = this.b.get(i8);
                    if (actionItem2.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                        int i9 = 0;
                        while (true) {
                            if (i9 < actionItem2.b.size()) {
                                Device device4 = actionItem2.b.get(i9);
                                if (device4 != null && SmartHomeDeviceManager.a().h(device4.did) && RecommendSceneManager.a().a(actionItem2.h, RecommendSceneManager.a().a(device4))) {
                                    actionItem2.d.put(device4.did, true);
                                    break;
                                } else {
                                    actionItem2.d.put(device4.did, false);
                                    i9++;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
                return;
            }
            for (int i10 = 0; i10 < this.f21985a.size(); i10++) {
                ConditionItem conditionItem3 = this.f21985a.get(i10);
                if (conditionItem3.f.equalsIgnoreCase("device")) {
                    int i11 = 0;
                    while (true) {
                        if (i11 >= conditionItem3.b.size()) {
                            break;
                        }
                        Device device5 = conditionItem3.b.get(i11);
                        Room p2 = HomeManager.a().p(device5.did);
                        if (device5 != null && SmartHomeDeviceManager.a().h(device5.did) && p2 != null && p2.d().equalsIgnoreCase(room.d()) && RecommendSceneManager.a().a(conditionItem3.i, RecommendSceneManager.a().a(device5))) {
                            conditionItem3.d.put(device5.did, true);
                            break;
                        } else {
                            conditionItem3.d.put(device5.did, false);
                            i11++;
                        }
                    }
                }
            }
            for (int i12 = 0; i12 < this.b.size(); i12++) {
                ActionItem actionItem3 = this.b.get(i12);
                if (actionItem3.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                    int i13 = 0;
                    while (true) {
                        if (i13 >= actionItem3.b.size()) {
                            break;
                        }
                        Device device6 = actionItem3.b.get(i13);
                        Room p3 = HomeManager.a().p(device6.did);
                        if (device6 != null && SmartHomeDeviceManager.a().h(device6.did) && p3 != null && p3.d().equalsIgnoreCase(room.d()) && RecommendSceneManager.a().a(actionItem3.h, RecommendSceneManager.a().a(device6))) {
                            actionItem3.d.put(device6.did, true);
                            break;
                        } else {
                            actionItem3.d.put(device6.did, false);
                            i13++;
                        }
                    }
                }
            }
        }
    }

    private Room a(Room room) {
        Room p;
        Room room2 = null;
        int i2 = 0;
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            ActionItem actionItem = this.b.get(i3);
            if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                int i4 = 0;
                while (true) {
                    if (i4 >= actionItem.b.size()) {
                        break;
                    }
                    Device device = actionItem.b.get(i4);
                    if (RecommendSceneManager.a().a(actionItem.h, RecommendSceneManager.a().a(device)) && (p = HomeManager.a().p(device.did)) != null && room != null && room.d().equalsIgnoreCase(p.d())) {
                        i2++;
                        room2 = p;
                        break;
                    }
                    i4++;
                }
            }
        }
        if (i2 == this.b.size()) {
            return room2;
        }
        return null;
    }

    public static SceneApi.Action a(String str, String str2) {
        try {
            if (RecommendSceneManager.a().b() != null) {
                if (RecommendSceneManager.a().b().f21988a != null) {
                    JSONObject optJSONObject = RecommendSceneManager.a().b().f21988a.optJSONObject(str2);
                    if (optJSONObject == null) {
                        return null;
                    }
                    JSONObject jSONObject = new JSONObject(optJSONObject.toString());
                    jSONObject.put("model", str);
                    try {
                        return SceneApi.Action.a(jSONObject);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
            }
            return new SceneApi.Action();
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static SceneApi.Condition b(String str, String str2) {
        try {
            if (RecommendSceneManager.a().b() != null) {
                if (RecommendSceneManager.a().b().b != null) {
                    JSONObject jSONObject = new JSONObject(RecommendSceneManager.a().b().b.optJSONObject(str2).toString());
                    jSONObject.put("key", String.format(jSONObject.optString("key"), new Object[]{str}));
                    jSONObject.put("model", str);
                    return SceneApi.Condition.a(jSONObject);
                }
            }
            return new SceneApi.Condition();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.f21985a);
        parcel.writeList(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
    }

    public RecommendSceneDetailInfo() {
        this.f21985a = new ArrayList();
        this.b = new ArrayList();
    }

    protected RecommendSceneDetailInfo(Parcel parcel) {
        this.f21985a = new ArrayList();
        this.b = new ArrayList();
        this.f21985a = new ArrayList();
        parcel.readList(this.f21985a, ConditionItem.class.getClassLoader());
        this.b = new ArrayList();
        parcel.readList(this.b, ActionItem.class.getClassLoader());
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
    }

    public boolean a() {
        if (this.i == 30) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.b.size(); i3++) {
                ActionItem actionItem = this.b.get(i3);
                if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                    int i4 = i2;
                    for (int i5 = 0; i5 < actionItem.b.size(); i5++) {
                        if (RecommendSceneManager.a().a(actionItem.h, RecommendSceneManager.a().a(actionItem.b.get(i5)))) {
                            i4++;
                        }
                    }
                    i2 = i4;
                } else {
                    i2++;
                }
            }
            return i2 >= 2;
        } else if (this.i != 15) {
            return false;
        } else {
            for (int i6 = 0; i6 < this.f21985a.size(); i6++) {
                ConditionItem conditionItem = this.f21985a.get(i6);
                if (conditionItem.f.equalsIgnoreCase("device")) {
                    if (conditionItem.b.size() == 0) {
                        return false;
                    }
                    int i7 = 0;
                    for (int i8 = 0; i8 < conditionItem.b.size(); i8++) {
                        if (RecommendSceneManager.a().a(conditionItem.i, RecommendSceneManager.a().a(conditionItem.b.get(i8)))) {
                            i7++;
                        }
                    }
                    if (i7 == 0) {
                        return false;
                    }
                }
            }
            for (int i9 = 0; i9 < this.b.size(); i9++) {
                ActionItem actionItem2 = this.b.get(i9);
                if (actionItem2.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                    if (actionItem2.b.size() == 0) {
                        return false;
                    }
                    int i10 = 0;
                    for (int i11 = 0; i11 < actionItem2.b.size(); i11++) {
                        if (RecommendSceneManager.a().a(actionItem2.h, RecommendSceneManager.a().a(actionItem2.b.get(i11)))) {
                            i10++;
                        }
                    }
                    if (i10 == 0) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
