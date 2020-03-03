package com.mi.global.bbs.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class HomeForumData {
    private ArrayList<HomeForumBean> homeForums;
    private String moreLink;
    private String title;

    public HomeForumData() {
    }

    public HomeForumData(String str) {
        Gson gson = new Gson();
        this.homeForums = new ArrayList<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.title = jSONObject.optString("title");
            this.moreLink = jSONObject.optString("link");
            this.homeForums = (ArrayList) gson.fromJson(jSONObject.optString("list"), new TypeToken<List<HomeForumBean>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMoreLink() {
        return this.moreLink;
    }

    public void setMoreLink(String str) {
        this.moreLink = str;
    }

    public ArrayList<HomeForumBean> getHomeForums() {
        return this.homeForums;
    }

    public void setHomeForums(ArrayList<HomeForumBean> arrayList) {
        this.homeForums = arrayList;
    }
}
