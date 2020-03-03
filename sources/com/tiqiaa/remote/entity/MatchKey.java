package com.tiqiaa.remote.entity;

import com.imi.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;

@Table(name = "tb_match_key")
public class MatchKey implements IJsonable, Comparable<MatchKey> {
    @JSONField(name = "appliance_type")
    int appliance_type;
    @Id
    int id;
    @JSONField(name = "key_type")
    int key_type;
    @JSONField(name = "seq")
    int seq;

    public int getAppliance_type() {
        return this.appliance_type;
    }

    public void setAppliance_type(int i) {
        this.appliance_type = i;
    }

    public int getKey_type() {
        return this.key_type;
    }

    public void setKey_type(int i) {
        this.key_type = i;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int i) {
        this.seq = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int compareTo(MatchKey matchKey) {
        return this.seq - matchKey.seq;
    }
}
