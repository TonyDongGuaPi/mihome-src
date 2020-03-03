package com.mi.global.bbs.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class HomePostData {
    private ArrayList<HomePostBean> homePostBeens = new ArrayList<>();
    private String moreLink;
    private String title;

    public HomePostData(String str) {
        Gson gson = new Gson();
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.title = jSONObject.optString("title");
            this.moreLink = jSONObject.optString("link");
            this.homePostBeens = (ArrayList) gson.fromJson(jSONObject.optString("list"), new TypeToken<List<HomePostBean>>() {
            }.getType());
        } catch (Exception unused) {
        }
    }
}
