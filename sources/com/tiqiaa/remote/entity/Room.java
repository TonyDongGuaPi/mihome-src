package com.tiqiaa.remote.entity;

import com.imi.fastjson.JSON;
import com.imi.fastjson.TypeReference;
import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.imi.fastjson.parser.Feature;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;
import java.util.ArrayList;
import java.util.List;

@JSONType(ignores = {"remote_ids", "remote_ids_json"})
@Table(name = "tb_room")
public class Room implements IJsonable, Cloneable {
    @JSONField(name = "name")
    String name;
    @Id
    @NoAutoIncrement
    @JSONField(name = "no")

    /* renamed from: no  reason: collision with root package name */
    int f1363no;
    @JSONField(name = "remote_ids")
    List<String> remote_ids;
    @JSONField(name = "remote_ids_json")
    String remote_ids_json;
    @JSONField(name = "remotes")
    List<Remote> remotes;

    public int getNo() {
        return this.f1363no;
    }

    public void setNo(int i) {
        this.f1363no = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<String> getRemote_ids() {
        if (this.remote_ids == null && this.remote_ids_json != null) {
            try {
                this.remote_ids = (List) JSON.parseObject(this.remote_ids_json, new TypeReference<List<String>>() {
                }, new Feature[0]);
            } catch (Exception unused) {
            }
        }
        return this.remote_ids;
    }

    public void setRemote_ids(List<String> list) {
        this.remote_ids = list;
    }

    public List<Remote> getRemotes() {
        return this.remotes;
    }

    public void setRemotes(List<Remote> list) {
        this.remotes = list;
    }

    public String getRemote_ids_json() {
        return this.remote_ids_json;
    }

    public void setRemote_ids_json(String str) {
        this.remote_ids_json = str;
    }

    public Room clone() {
        Room room = new Room();
        room.setName(this.name);
        room.setNo(this.f1363no);
        if (this.remotes != null) {
            ArrayList arrayList = new ArrayList();
            for (Remote add : this.remotes) {
                arrayList.add(add);
            }
            room.setRemotes(this.remotes);
        }
        return room;
    }
}
