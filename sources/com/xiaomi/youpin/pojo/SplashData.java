package com.xiaomi.youpin.pojo;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class SplashData {
    private ArrayList<String> rnmap;
    private ArrayList<SplashItem> splash;
    @SerializedName("switch")
    private ArrayList<String> switchList = new ArrayList<>();
    private ArrayList<String> tablinks;
    private ArrayList<WeexCache> weexcache;

    public ArrayList<String> getRnmap() {
        return this.rnmap;
    }

    public void setRnmap(ArrayList<String> arrayList) {
        this.rnmap = arrayList;
    }

    public ArrayList<SplashItem> getSplash() {
        return this.splash;
    }

    public void setSplash(ArrayList<SplashItem> arrayList) {
        this.splash = arrayList;
    }

    public ArrayList<String> getSwitchList() {
        return this.switchList;
    }

    public void setSwitchList(ArrayList<String> arrayList) {
        this.switchList = arrayList;
    }

    public ArrayList<WeexCache> getWeexcache() {
        return this.weexcache;
    }

    public void setWeexcache(ArrayList<WeexCache> arrayList) {
        this.weexcache = arrayList;
    }

    public void setTablinks(ArrayList<String> arrayList) {
        this.tablinks = arrayList;
    }

    public ArrayList<String> getTablinks() {
        return this.tablinks;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SplashData)) {
            return false;
        }
        SplashData splashData = (SplashData) obj;
        if (this.splash == null || this.splash.size() == 0 || splashData.splash == null || splashData.splash.size() == 0 || this.splash.size() != splashData.splash.size()) {
            return false;
        }
        for (int i = 0; i < this.splash.size(); i++) {
            if (!this.splash.get(i).getLink_param().equals(splashData.getSplash().get(i).getLink_param())) {
                return false;
            }
        }
        return true;
    }
}
