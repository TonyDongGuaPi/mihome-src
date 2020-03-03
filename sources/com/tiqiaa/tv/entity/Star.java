package com.tiqiaa.tv.entity;

import com.imi.fastjson.annotation.JSONField;
import com.tiqiaa.common.IJsonable;

public class Star implements IJsonable {
    @JSONField(name = "birthday")
    String birthday;
    @JSONField(name = "file")
    String file;
    @JSONField(name = "id")
    int id;
    @JSONField(name = "name")
    String name;
    @JSONField(name = "photo")
    String photo;
    @JSONField(name = "sex")
    String sex;

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

    public String getFile() {
        if (this.file == null) {
            this.file = "";
        }
        return this.file;
    }

    public void setFile(String str) {
        this.file = str;
    }

    public String getPhoto() {
        if (this.photo == null) {
            this.photo = "";
        }
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public String getSex() {
        if (this.sex == null) {
            this.sex = "";
        }
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getBirthday() {
        if (this.birthday == null) {
            this.birthday = "";
        }
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }
}
