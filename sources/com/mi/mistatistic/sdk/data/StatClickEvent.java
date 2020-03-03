package com.mi.mistatistic.sdk.data;

import com.mi.mistatistic.sdk.controller.Logger;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.miui.tsmclient.net.TSMAuthContants;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class StatClickEvent extends CustomDataEvent {
    private String c;
    private String d;
    private String e;
    private ArrayList<EventData> f;
    private String g;

    public String a() {
        return RemoteDataUploadManager.j;
    }

    public StatClickEvent() {
    }

    public StatClickEvent(String str, String str2, String str3, ArrayList<EventData> arrayList) {
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = arrayList;
    }

    public String c() {
        return this.f7357a;
    }

    public void a(String str) {
        this.f7357a = str;
    }

    public long d() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public String g() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String h() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String i() {
        return this.d;
    }

    public void f(String str) {
        this.d = str;
    }

    public ArrayList<EventData> j() {
        return this.f;
    }

    public void a(ArrayList<EventData> arrayList) {
        this.f = arrayList;
    }

    public String k() {
        return this.g;
    }

    public void g(String str) {
        this.g = str;
    }

    public String b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TSMAuthContants.PARAM_SESSION_ID, this.f7357a);
            jSONObject.put("timestamp", this.b);
            jSONObject.put("eventId", this.c);
            jSONObject.put("pageId", this.d);
            jSONObject.put("label", this.e);
            jSONObject.put("data", l());
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public String l() {
        try {
            JSONArray jSONArray = new JSONArray();
            if (this.f != null && this.f.size() > 0) {
                Iterator<EventData> it = this.f.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().d());
                }
            }
            return jSONArray.toString();
        } catch (Exception e2) {
            Logger.a("dataToJsonArrayString  Exception: ", (Throwable) e2);
            return "";
        }
    }
}
