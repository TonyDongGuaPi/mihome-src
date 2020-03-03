package com.tiqiaa.tv.entity;

import com.imi.fastjson.JSON;
import com.imi.fastjson.TypeReference;
import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.imi.fastjson.parser.Feature;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;
import com.tiqiaa.database.DataBaseManager;
import java.util.Date;
import java.util.List;

@JSONType(ignores = {"pp", "previews_json", "type", "cacheTime", "img"})
@Table(name = "tb_tvshow")
public class TvShow implements IJsonable {
    @JSONField(name = "brief_content")
    String brief_content;
    @JSONField(name = "cacheTime")
    Date cacheTime;
    @JSONField(name = "content")
    String content;
    @JSONField(name = "director")
    String director;
    @NoAutoIncrement
    @JSONField(name = "id")
    int id;
    @JSONField(name = "img")
    TvShowImg img;
    @JSONField(name = "js")
    int js;
    @JSONField(name = "keywords")
    String keywords;
    @JSONField(name = "pp")
    String pp;
    @JSONField(name = "presenter")
    String presenter;
    @JSONField(name = "previews")
    List<TvShowPreview> previews;
    @JSONField(name = "previews_json")
    String previews_json;
    @JSONField(name = "title")
    String title;
    @JSONField(name = "type")
    int type;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getTitle() {
        if (this.title == null) {
            this.title = "";
        }
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getKeywords() {
        if (this.keywords == null) {
            this.keywords = "";
        }
        return this.keywords;
    }

    public void setKeywords(String str) {
        this.keywords = str;
    }

    public String getBrief_content() {
        if (this.brief_content == null) {
            this.brief_content = "";
        }
        return this.brief_content;
    }

    public void setBrief_content(String str) {
        this.brief_content = str;
    }

    public String getContent() {
        if (this.content == null) {
            this.content = "";
        }
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getDirector() {
        if (this.director == null) {
            this.director = "";
        }
        return this.director;
    }

    public void setDirector(String str) {
        this.director = str;
    }

    public String getPresenter() {
        if (this.presenter == null) {
            this.presenter = "";
        }
        return this.presenter;
    }

    public void setPresenter(String str) {
        this.presenter = str;
    }

    public int getJs() {
        return this.js;
    }

    public void setJs(int i) {
        this.js = i;
    }

    public List<TvShowPreview> getPreviews() {
        if (this.previews == null && this.previews_json != null) {
            try {
                this.previews = (List) JSON.parseObject(this.previews_json, new TypeReference<List<TvShowPreview>>() {
                }, new Feature[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.previews;
    }

    public void setPreviews(List<TvShowPreview> list) {
        this.previews = list;
    }

    public String getPreviews_json() {
        return this.previews_json;
    }

    public void setPreviews_json(String str) {
        this.previews_json = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public Date getCacheTime() {
        return this.cacheTime;
    }

    public void setCacheTime(Date date) {
        this.cacheTime = date;
    }

    public String getPp() {
        return this.pp;
    }

    public void setPp(String str) {
        this.pp = str;
    }

    public TvShowImg getImg() {
        if (this.img == null) {
            this.img = DataBaseManager.getInstance().getTvShowImgByshowId(getId());
        }
        return this.img;
    }

    public void setImg(TvShowImg tvShowImg) {
        this.img = tvShowImg;
    }
}
