package com.tiqiaa.icontrol.util;

import com.imi.fastjson.annotation.JSONField;
import com.tiqiaa.common.IJsonable;
import java.util.Date;

public class TvForeRequestParams implements IJsonable {
    @JSONField(name = "channel_ids")
    int[] channel_ids;
    @JSONField(name = "date")
    Date date;
    @JSONField(name = "playing")
    boolean playing;
    @JSONField(name = "pp")
    String pp;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public int[] getChannel_ids() {
        return this.channel_ids;
    }

    public void setChannel_ids(int[] iArr) {
        this.channel_ids = iArr;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public void setPlaying(boolean z) {
        this.playing = z;
    }

    public String getPp() {
        return this.pp;
    }

    public void setPp(String str) {
        this.pp = str;
    }
}
