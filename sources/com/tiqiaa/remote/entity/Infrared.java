package com.tiqiaa.remote.entity;

import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.imi.fastjson.serializer.SerializerFeature;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;

@JSONType(ignores = {"key_id"}, orders = {"key_type", "freq", "func", "mark", "ir_mark", "data", "data_str"})
@Table(name = "tb_infrared")
public class Infrared implements IJsonable, Cloneable {
    @JSONField(name = "data", serialzeFeatures = {SerializerFeature.BrowserCompatible, SerializerFeature.UseSingleQuotes})
    private byte[] data;
    @JSONField(name = "data_str")
    private String data_str;
    @JSONField(name = "freq")
    private int freq;
    @JSONField(name = "func")
    private int func;
    @Id
    @JSONField(name = "id")
    long id;
    @JSONField(name = "ir_mark")
    int ir_mark;
    @JSONField(name = "key_id")
    private long keyId;
    @JSONField(name = "key_type")
    private int key_type;
    @JSONField(name = "mark")
    int mark;
    @JSONField(name = "priority")
    int priority;
    @JSONField(name = "quality")
    int quality;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getKey_id() {
        return this.keyId;
    }

    public void setKey_id(long j) {
        this.keyId = j;
    }

    public int getKey_type() {
        return this.key_type;
    }

    public void setKey_type(int i) {
        this.key_type = i;
    }

    public int getFunc() {
        return this.func;
    }

    public void setFunc(int i) {
        this.func = i;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public int getFreq() {
        return this.freq;
    }

    public void setFreq(int i) {
        this.freq = i;
    }

    public int getMark() {
        return this.mark;
    }

    public void setMark(int i) {
        this.mark = i;
    }

    public int getIr_mark() {
        return this.ir_mark;
    }

    public void setIr_mark(int i) {
        this.ir_mark = i;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setQuality(int i) {
        this.quality = i;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public String getData_str() {
        return this.data_str;
    }

    public void setData_Str(String str) {
        this.data_str = str;
    }

    public Infrared clone() {
        Infrared infrared = new Infrared();
        infrared.setFreq(this.freq);
        infrared.setFunc(this.func);
        infrared.setId(this.id);
        infrared.setIr_mark(this.ir_mark);
        infrared.setKey_id(this.keyId);
        infrared.setKey_type(this.key_type);
        infrared.setMark(this.mark);
        if (this.data != null && this.data.length > 0) {
            byte[] bArr = new byte[this.data.length];
            System.arraycopy(this.data, 0, bArr, 0, this.data.length);
            infrared.setData(bArr);
        }
        infrared.setData_Str(this.data_str);
        return infrared;
    }
}
