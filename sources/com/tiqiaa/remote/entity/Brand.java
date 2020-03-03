package com.tiqiaa.remote.entity;

import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;

@JSONType(orders = {"id", "brand_cn", "brand_tw", "brand_en", "brand_other", "pinyin", "py"})
@Table(name = "tb_brand")
public class Brand implements IJsonable, Cloneable, Comparable<Brand> {
    public static final long BRAND_ID_ALL = 0;
    public static final long BRAND_ID_OTHER = -1;
    @JSONField(name = "brand_cn")
    private String brand_cn;
    @JSONField(name = "brand_en")
    private String brand_en;
    @JSONField(name = "brand_other")
    private String brand_other;
    @JSONField(name = "brand_tw")
    private String brand_tw;
    @NoAutoIncrement
    @JSONField(name = "id")
    long id;
    @JSONField(name = "pinyin")
    private String pinyin;
    @JSONField(name = "py")
    private String py;
    @JSONField(name = "remarks")
    private String remarks;

    public static long getBrandIdAll() {
        return 0;
    }

    public static long getBrandIdOther() {
        return -1;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getBrand_cn() {
        return this.brand_cn;
    }

    public void setBrand_cn(String str) {
        this.brand_cn = str;
    }

    public String getBrand_tw() {
        return this.brand_tw;
    }

    public void setBrand_tw(String str) {
        this.brand_tw = str;
    }

    public String getBrand_en() {
        return this.brand_en;
    }

    public void setBrand_en(String str) {
        this.brand_en = str;
    }

    public String getBrand_other() {
        return this.brand_other;
    }

    public void setBrand_other(String str) {
        this.brand_other = str;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String str) {
        this.pinyin = str;
    }

    public String getPy() {
        return this.py;
    }

    public void setPy(String str) {
        this.py = str;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }

    public Brand clone() {
        Brand brand = new Brand();
        brand.setId(this.id);
        brand.setBrand_cn(this.brand_cn);
        brand.setBrand_tw(this.brand_tw);
        brand.setBrand_en(this.brand_en);
        brand.setBrand_other(this.brand_other);
        brand.setPinyin(this.pinyin);
        brand.setPy(this.py);
        return brand;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Brand) || ((Brand) obj).getId() != this.id) {
            return false;
        }
        return true;
    }

    public int compareTo(Brand brand) {
        if (this.pinyin == null || this.pinyin.equals("")) {
            return -1;
        }
        if (brand.pinyin == null || brand.pinyin.equals("")) {
            return 1;
        }
        return this.pinyin.toLowerCase().compareTo(brand.pinyin.toLowerCase());
    }
}
