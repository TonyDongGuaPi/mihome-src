package com.tiqiaa.tv.entity;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;
import java.util.List;

@Table(name = "tb_province")
public class Province implements IJsonable {
    List<City> cities;
    @NoAutoIncrement
    int id;
    int province_id;
    String province_name;
    String py;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getProvince_id() {
        return this.province_id;
    }

    public void setProvince_id(int i) {
        this.province_id = i;
    }

    public String getProvince_name() {
        return this.province_name;
    }

    public void setProvince_name(String str) {
        this.province_name = str;
    }

    public List<City> getCities() {
        return this.cities;
    }

    public void setCities(List<City> list) {
        this.cities = list;
    }

    public String getPy() {
        return this.py;
    }

    public void setPy(String str) {
        this.py = str;
    }
}
