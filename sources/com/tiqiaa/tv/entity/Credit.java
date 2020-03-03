package com.tiqiaa.tv.entity;

import com.imi.fastjson.annotation.JSONField;
import com.tiqiaa.common.IJsonable;

public class Credit implements IJsonable {
    @JSONField(name = "id")
    int id;
    @JSONField(name = "js")
    String js;
    @JSONField(name = "name")
    String name;
    @JSONField(name = "sType")
    String sType;
    @JSONField(name = "star_id")
    int star_id;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        if (this.name == null) {
            this.name = "";
        }
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getJs() {
        if (this.js == null) {
            this.js = "";
        }
        return this.js;
    }

    public void setJs(String str) {
        this.js = str;
    }

    public int getStar_id() {
        return this.star_id;
    }

    public void setStar_id(int i) {
        this.star_id = i;
    }

    public String getsType() {
        if (this.sType == null) {
            this.sType = "";
        }
        return this.sType;
    }

    public void setsType(String str) {
        this.sType = str;
    }
}
