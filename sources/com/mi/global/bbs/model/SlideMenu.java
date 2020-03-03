package com.mi.global.bbs.model;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.ArrayList;

public class SlideMenu implements Serializable {
    private static final long serialVersionUID = -1495756615508283196L;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("parentid")
    private String parentid;
    @SerializedName("submenu")
    private ArrayList<SlideMenu> submenu = new ArrayList<>();
    @SerializedName("url")
    private String url;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getParentid() {
        return this.parentid;
    }

    public void setParentid(String str) {
        this.parentid = str;
    }

    public ArrayList<SlideMenu> getSubmenu() {
        return this.submenu;
    }

    public void setSubmenu(ArrayList<SlideMenu> arrayList) {
        this.submenu = arrayList;
    }

    public String toString() {
        return "SlideMenu{name='" + this.name + Operators.SINGLE_QUOTE + ", id='" + this.id + Operators.SINGLE_QUOTE + ", url='" + this.url + Operators.SINGLE_QUOTE + ", parentid='" + this.parentid + Operators.SINGLE_QUOTE + ", submenu=" + this.submenu + Operators.BLOCK_END;
    }
}
