package com.mi.global.bbs.model;

import com.google.gson.annotations.SerializedName;

public class HomeSuggest {
    @SerializedName("fname")
    private String fname;
    @SerializedName("icon")
    private String icon;
    @SerializedName("link")
    private String link;

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String str) {
        this.fname = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
