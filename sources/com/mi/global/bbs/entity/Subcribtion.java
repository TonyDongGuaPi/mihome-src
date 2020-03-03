package com.mi.global.bbs.entity;

public class Subcribtion {
    public Long columnID;
    public Long id;
    public Long lastAppendTime;

    public Subcribtion(Long l, Long l2, Long l3) {
        this.id = l;
        this.columnID = l2;
        this.lastAppendTime = l3;
    }

    public Subcribtion() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public Long getColumnID() {
        return this.columnID;
    }

    public void setColumnID(Long l) {
        this.columnID = l;
    }

    public Long getLastAppendTime() {
        return this.lastAppendTime;
    }

    public void setLastAppendTime(Long l) {
        this.lastAppendTime = l;
    }
}
