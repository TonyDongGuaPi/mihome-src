package com.tiqiaa.tv.entity;

import com.imi.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;

@Table(name = "tb_tvshowimg")
public class TvShowImg implements IJsonable {
    @Id
    @JSONField(name = "id")
    int id;
    @JSONField(name = "pictype")
    int pictype;
    @JSONField(name = "tvshow_id")
    int tvshow_id;
    @JSONField(name = "url")
    String url;
    @JSONField(name = "url_small")
    String url_small;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getTvshow_id() {
        return this.tvshow_id;
    }

    public void setTvshow_id(int i) {
        this.tvshow_id = i;
    }

    public String getUrl() {
        if (this.url == null) {
            this.url = "";
        }
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getUrl_small() {
        if (this.url_small == null) {
            this.url_small = "";
        }
        return this.url_small;
    }

    public void setUrl_small(String str) {
        this.url_small = str;
    }

    public int getPictype() {
        return this.pictype;
    }

    public void setPictype(int i) {
        this.pictype = i;
    }
}
