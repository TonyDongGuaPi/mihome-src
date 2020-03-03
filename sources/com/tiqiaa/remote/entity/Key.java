package com.tiqiaa.remote.entity;

import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;
import com.tiqiaa.icontrol.util.RemoteUtils;
import java.util.ArrayList;
import java.util.List;

@JSONType(ignores = {"id", "remote_id"}, orders = {"name", "type", "protocol", "infrareds"})
@Table(name = "tb_key")
public class Key implements IJsonable, Cloneable {
    @Id
    @NoAutoIncrement
    @JSONField(name = "id")
    long id;
    @JSONField(name = "infrareds")
    private List<Infrared> infrareds;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "positions")
    private List<KeyPosition> positions;
    @JSONField(name = "protocol")
    private int protocol;
    @JSONField(name = "remarks")
    String remarks;
    @JSONField(name = "remote_id")
    private String remoteId;
    @JSONField(name = "type")
    private int type;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getRemote_id() {
        return this.remoteId;
    }

    public void setRemote_id(String str) {
        this.remoteId = str;
    }

    public List<Infrared> getInfrareds() {
        return this.infrareds;
    }

    public void setInfrareds(List<Infrared> list) {
        this.infrareds = list;
    }

    public int getProtocol() {
        return this.protocol;
    }

    public void setProtocol(int i) {
        this.protocol = i;
    }

    public List<KeyPosition> getPositions() {
        return this.positions;
    }

    public void setPositions(List<KeyPosition> list) {
        this.positions = list;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }

    public String getLocalName() {
        if (this.name == null || this.name.equals("")) {
            return RemoteUtils.getLocalKeyName(this.type);
        }
        return this.name;
    }

    public Key clone() {
        Key key = new Key();
        if (this.infrareds != null && this.infrareds.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (Infrared clone : this.infrareds) {
                arrayList.add(clone.clone());
            }
            key.setInfrareds(arrayList);
        }
        if (this.positions != null && this.positions.size() > 0) {
            ArrayList arrayList2 = new ArrayList();
            for (KeyPosition clone2 : this.positions) {
                arrayList2.add(clone2.clone());
            }
            key.setPositions(arrayList2);
        }
        key.setId(this.id);
        key.setName(this.name);
        key.setProtocol(this.protocol);
        key.setType(this.type);
        key.setRemote_id(this.remoteId);
        key.setRemarks(this.remarks);
        return key;
    }
}
