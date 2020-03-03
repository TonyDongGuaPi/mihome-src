package com.tiqiaa.tv.entity;

import com.imi.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;

@Table(name = "tb_channel_num")
public class ChannelNum implements IJsonable {
    int cfg_id;
    @JSONField(name = "channel_id")
    int channel_id;
    boolean enable;
    int id;
    @JSONField(name = "num")
    int num;

    public int getChannel_id() {
        return this.channel_id;
    }

    public void setChannel_id(int i) {
        this.channel_id = i;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }
}
