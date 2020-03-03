package com.mi.global.bbs.model;

import com.google.gson.annotations.SerializedName;

public class HomeNotify {
    @SerializedName("author")
    private String author;
    @SerializedName("authorenc")
    private String authorenc;
    @SerializedName("displayorder")
    private String displayorder;
    @SerializedName("endtime")
    private String endtime;
    @SerializedName("groups")
    private String groups;
    @SerializedName("id")
    private String id;
    @SerializedName("link")
    private String link;
    @SerializedName("message")
    private String message;
    @SerializedName("starttime")
    private String starttime;
    @SerializedName("subject")
    private String subject;
    @SerializedName("type")
    private String type;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getDisplayorder() {
        return this.displayorder;
    }

    public void setDisplayorder(String str) {
        this.displayorder = str;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(String str) {
        this.starttime = str;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setEndtime(String str) {
        this.endtime = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getGroups() {
        return this.groups;
    }

    public void setGroups(String str) {
        this.groups = str;
    }

    public String getAuthorenc() {
        return this.authorenc;
    }

    public void setAuthorenc(String str) {
        this.authorenc = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
