package com.xiaomi.smarthome.scenenew.model;

import android.text.TextUtils;
import android.text.format.Time;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.scenenew.debug.MyDebugLogger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SceneLogInfo {

    /* renamed from: a  reason: collision with root package name */
    public long f21991a;
    public boolean b = false;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public int i;
    public int j = 0;
    public List<Detail> k = new ArrayList();
    public String l;

    public boolean a(SceneLogInfo sceneLogInfo) {
        if (TextUtils.isEmpty(sceneLogInfo.c) || TextUtils.isEmpty(sceneLogInfo.d) || TextUtils.isEmpty(sceneLogInfo.d)) {
            return true;
        }
        if (!TextUtils.equals(sceneLogInfo.c, this.c) || !TextUtils.equals(sceneLogInfo.d, this.d) || !TextUtils.equals(sceneLogInfo.f, this.f)) {
            return false;
        }
        return true;
    }

    public static SceneLogInfo a(JSONObject jSONObject) {
        SceneLogInfo sceneLogInfo = new SceneLogInfo();
        sceneLogInfo.f21991a = jSONObject.optLong("time");
        if (sceneLogInfo.f21991a == 0) {
            MyDebugLogger a2 = MyDebugLogger.a();
            a2.a(System.currentTimeMillis() + "====SceneLogInfo cFJson: time=0");
        }
        Time time = new Time();
        time.set(sceneLogInfo.f21991a * 1000);
        sceneLogInfo.c = String.valueOf(time.monthDay);
        sceneLogInfo.d = String.valueOf(time.month + 1);
        sceneLogInfo.e = String.valueOf(time.weekDay);
        sceneLogInfo.f = String.valueOf(time.year);
        sceneLogInfo.g = jSONObject.optString("name");
        sceneLogInfo.l = jSONObject.optString("userSceneId");
        if (sceneLogInfo.f21991a != 0 && TextUtils.equals("1", sceneLogInfo.c) && TextUtils.equals("1", sceneLogInfo.d) && TextUtils.equals("1970", sceneLogInfo.f)) {
            MyDebugLogger a3 = MyDebugLogger.a();
            a3.a(System.currentTimeMillis() + "====SceneLogInfo new Time error");
        }
        sceneLogInfo.i = R.drawable.scene_log_new_icon;
        StringBuilder sb = new StringBuilder();
        sb.append(time.hour < 10 ? "0" : "");
        sb.append(String.valueOf(time.hour));
        sb.append(":");
        sb.append(time.minute < 10 ? "0" : "");
        sb.append(String.valueOf(time.minute));
        sceneLogInfo.h = sb.toString();
        JSONArray optJSONArray = jSONObject.optJSONArray("msg");
        sceneLogInfo.j = 0;
        if (optJSONArray != null) {
            int i2 = 0;
            boolean z = true;
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                Detail detail = new Detail();
                detail.f21992a = optJSONObject.optInt("t");
                if (detail.f21992a == 0) {
                    detail.b = optJSONObject.optString("targetDesc");
                    detail.c = optJSONObject.optString("methodDesc");
                }
                z &= optJSONObject.optBoolean("dev_con_state", false);
                detail.e = optJSONObject.optInt("error");
                detail.f = optJSONObject.optString("us_id");
                if (detail.e != 0) {
                    i2++;
                }
                if (!TextUtils.isEmpty(detail.b) || !TextUtils.isEmpty(detail.c)) {
                    sceneLogInfo.k.add(detail);
                }
            }
            if (i2 == optJSONArray.length()) {
                sceneLogInfo.j = -1;
            } else if (i2 == 0) {
                sceneLogInfo.j = 0;
            } else {
                sceneLogInfo.j = 1;
            }
            if (z) {
                return null;
            }
        }
        if (sceneLogInfo.f21991a == 0 && sceneLogInfo.j == 0 && TextUtils.isEmpty(sceneLogInfo.g) && sceneLogInfo.k.size() == 0) {
            MyDebugLogger a4 = MyDebugLogger.a();
            a4.a(System.currentTimeMillis() + " ===all empty createFromJson:   " + jSONObject.toString());
            MyDebugLogger a5 = MyDebugLogger.a();
            a5.a(System.currentTimeMillis() + " ===all empty mSceneLog: " + sceneLogInfo.toString());
            return null;
        }
        if (sceneLogInfo.f21991a == 0 || TextUtils.isEmpty(sceneLogInfo.g)) {
            MyDebugLogger a6 = MyDebugLogger.a();
            a6.a("SceneLog createFromJson:   " + jSONObject.toString());
            MyDebugLogger a7 = MyDebugLogger.a();
            a7.a("SceneLog mSceneLog: " + sceneLogInfo.toString());
        }
        return sceneLogInfo;
    }

    public static List<SceneLogInfo> a(JSONArray jSONArray) {
        SceneLogInfo a2;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (!(optJSONObject == null || (a2 = a(optJSONObject)) == null)) {
                if (!TextUtils.equals("1", a2.c) || !TextUtils.equals("1", a2.d) || !TextUtils.equals("1970", a2.f)) {
                    if (arrayList.size() == 0 || !((SceneLogInfo) arrayList.get(arrayList.size() - 1)).a(a2)) {
                        SceneLogInfo sceneLogInfo = new SceneLogInfo();
                        sceneLogInfo.b = true;
                        sceneLogInfo.c = a2.c;
                        sceneLogInfo.d = a2.d;
                        sceneLogInfo.e = a2.e;
                        sceneLogInfo.f = a2.f;
                        arrayList.add(sceneLogInfo);
                    }
                    arrayList.add(a2);
                } else {
                    MyDebugLogger a3 = MyDebugLogger.a();
                    a3.a(System.currentTimeMillis() + "====SceneLogInfo convert time item error");
                }
            }
        }
        MyDebugLogger a4 = MyDebugLogger.a();
        a4.a(System.currentTimeMillis() + "====SceneLogInfo logList size is " + arrayList.size());
        return arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bTimeTag: " + this.b + "   day: " + this.c);
        sb.append("  month: " + this.d + "  week: " + this.e);
        sb.append("  year: " + this.f + "  title: " + this.g);
        sb.append("  time: " + this.h + "  icon： " + this.i);
        sb.append("  result： " + this.j + "  mSceneId: " + this.l);
        for (Detail detail : this.k) {
            sb.append("\n");
            sb.append(detail.toString());
            sb.append("   ");
        }
        return sb.toString();
    }

    public static class Detail {

        /* renamed from: a  reason: collision with root package name */
        public int f21992a;
        public String b;
        public String c;
        public String d;
        public int e;
        public String f;

        public String toString() {
            return "type: " + this.f21992a + "   name: " + this.b + "   detail: " + this.c + "   result: " + this.d + "   usId: " + this.f;
        }
    }
}
