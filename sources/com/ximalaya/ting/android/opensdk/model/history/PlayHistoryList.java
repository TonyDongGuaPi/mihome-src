package com.ximalaya.ting.android.opensdk.model.history;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayHistoryList extends XimalayaResponse {
    @SerializedName("sync_at")

    /* renamed from: a  reason: collision with root package name */
    private int f2078a;
    @SerializedName("uid")
    private int b;
    @SerializedName("play_histories")
    private List<PlayHistory> c;

    public PlayHistoryList(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                a(jSONObject.optInt("sync_at"));
                b(jSONObject.optInt("uid"));
                JSONArray optJSONArray = jSONObject.optJSONArray("play_histories");
                if (optJSONArray != null) {
                    Gson gson = new Gson();
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        arrayList.add(new PlayHistory(optJSONArray.getJSONObject(i), gson));
                    }
                    a((List<PlayHistory>) arrayList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public int a() {
        return this.f2078a;
    }

    public void a(int i) {
        this.f2078a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public List<PlayHistory> c() {
        return this.c;
    }

    public void a(List<PlayHistory> list) {
        this.c = list;
    }
}
