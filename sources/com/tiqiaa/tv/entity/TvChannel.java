package com.tiqiaa.tv.entity;

import com.imi.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;

@Table(name = "tb_tvchannel")
public class TvChannel implements IJsonable {
    @JSONField(name = "country_codes")
    private String country_codes;
    @JSONField(name = "en_name")
    private String en_name;
    @JSONField(name = "enable")
    private boolean enable;
    @Id
    @NoAutoIncrement
    @JSONField(name = "id")
    int id;
    @JSONField(name = "logo_url")
    private String logo_url;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "other_name")
    private String other_name;
    @JSONField(name = "priority")
    private int priority;
    @JSONField(name = "sort_key")
    private String sort_key;
    @JSONField(name = "tv_id")
    private int tv_id;

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

    public String getEn_name() {
        if (this.en_name == null) {
            this.en_name = "";
        }
        return this.en_name;
    }

    public void setEn_name(String str) {
        this.en_name = str;
    }

    public String getOther_name() {
        if (this.other_name == null) {
            this.other_name = "";
        }
        return this.other_name;
    }

    public void setOther_name(String str) {
        this.other_name = str;
    }

    public int getTv_id() {
        return this.tv_id;
    }

    public void setTv_id(int i) {
        this.tv_id = i;
    }

    public String getLogo_url() {
        return this.logo_url;
    }

    public void setLogo_url(String str) {
        this.logo_url = str;
    }

    public String getSort_key() {
        return this.sort_key;
    }

    public void setSort_key(String str) {
        this.sort_key = str;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public String getCountry_codes() {
        return this.country_codes;
    }

    public void setCountry_codes(String str) {
        this.country_codes = str;
    }
}
