package com.tiqiaa.tv.entity;

import com.imi.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;
import com.tiqiaa.database.DataBaseManager;
import java.util.Date;

@Table(name = "tb_tvforennotice")
public class TvForenotice implements IJsonable, Comparable<TvForenotice> {
    Date cacheDate;
    @JSONField(name = "channel_id")
    int channel_id;
    @JSONField(name = "et")
    Date et;
    @JSONField(name = "fid")
    int fid;
    @JSONField(name = "fid2")
    int fid2;
    @NoAutoIncrement
    @JSONField(name = "id")
    int id;
    @JSONField(name = "js")
    int js;
    String next_play;
    String playDate;
    @JSONField(name = "pn")
    String pn;
    @JSONField(name = "pp")
    String pp;
    @JSONField(name = "pt")
    Date pt;
    @JSONField(name = "tvshow")
    TvShow tvshow;
    @JSONField(name = "tvshow_img")
    TvShowImg tvshow_img;
    int type;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getChannel_id() {
        return this.channel_id;
    }

    public void setChannel_id(int i) {
        this.channel_id = i;
    }

    public Date getPt() {
        return this.pt;
    }

    public void setPt(Date date) {
        this.pt = date;
    }

    public Date getEt() {
        return this.et;
    }

    public void setEt(Date date) {
        this.et = date;
    }

    public String getPn() {
        if (this.pn == null) {
            this.pn = "";
        }
        return this.pn;
    }

    public void setPn(String str) {
        this.pn = str;
    }

    public int getFid() {
        return this.fid;
    }

    public void setFid(int i) {
        this.fid = i;
    }

    public int getFid2() {
        return this.fid2;
    }

    public void setFid2(int i) {
        this.fid2 = i;
    }

    public int getJs() {
        return this.js;
    }

    public void setJs(int i) {
        this.js = i;
    }

    public TvShowImg getTvshow_img() {
        if (this.tvshow_img == null) {
            this.tvshow_img = DataBaseManager.getInstance().getTvShowImgByshowId(getFid2() <= 0 ? getFid() : getFid2());
        }
        return this.tvshow_img;
    }

    public void setTvshow_img(TvShowImg tvShowImg) {
        this.tvshow_img = tvShowImg;
    }

    public String getPp() {
        return this.pp;
    }

    public void setPp(String str) {
        this.pp = str;
    }

    public String getNext_play() {
        return this.next_play;
    }

    public void setNext_play(String str) {
        this.next_play = str;
    }

    public TvShow getTvshow() {
        return this.tvshow;
    }

    public void setTvshow(TvShow tvShow) {
        this.tvshow = tvShow;
    }

    public Date getCacheDate() {
        return this.cacheDate;
    }

    public void setCacheDate(Date date) {
        this.cacheDate = date;
    }

    public String getPlayDate() {
        return this.playDate;
    }

    public void setPlayDate(String str) {
        this.playDate = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TvForenotice)) {
            return false;
        }
        TvForenotice tvForenotice = (TvForenotice) obj;
        if (this.id == tvForenotice.getId()) {
            return true;
        }
        if (this.channel_id == tvForenotice.getChannel_id() && this.pt.getTime() == tvForenotice.getPt().getTime()) {
            return true;
        }
        return false;
    }

    public int compareTo(TvForenotice tvForenotice) {
        if (tvForenotice.getPt() == null || this.pt == null) {
            return 0;
        }
        return (int) (this.pt.getTime() - tvForenotice.getPt().getTime());
    }
}
