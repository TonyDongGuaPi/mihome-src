package com.mi.global.bbs.model;

import java.io.Serializable;

public class RegionLocalBean implements Serializable {
    private static final long serialVersionUID = 1;
    public String country;
    public String local;
    public int logoSource;

    public RegionLocalBean() {
    }

    public RegionLocalBean(String str, String str2, int i) {
        this.local = str;
        this.country = str2;
        this.logoSource = i;
    }

    public String getLocal() {
        return this.local;
    }

    public void setLocal(String str) {
        this.local = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public int getLogoSource() {
        return this.logoSource;
    }

    public void setLogoSource(int i) {
        this.logoSource = i;
    }
}
