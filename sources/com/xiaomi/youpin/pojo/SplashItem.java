package com.xiaomi.youpin.pojo;

import com.google.gson.annotations.SerializedName;

public class SplashItem {
    private String background;
    private String bgimg;
    @SerializedName("bgimg-height")
    private int bgimg_height;
    @SerializedName("bgimg-width")
    private int bgimg_width;
    private long end;
    private String img;
    @SerializedName("img-height")
    private int img_height;
    @SerializedName("img-width")
    private int img_width;
    private String link_param;
    private int link_type;
    private int priority;
    private boolean repeat;
    private long start;
    private int stay;

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String str) {
        this.background = str;
    }

    public String getBgimg() {
        return this.bgimg;
    }

    public void setBgimg(String str) {
        this.bgimg = str;
    }

    public int getBgimg_width() {
        return this.bgimg_width;
    }

    public void setBgimg_width(int i) {
        this.bgimg_width = i;
    }

    public int getBgimg_height() {
        return this.bgimg_height;
    }

    public void setBgimg_height(int i) {
        this.bgimg_height = i;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public int getImg_width() {
        return this.img_width;
    }

    public void setImg_width(int i) {
        this.img_width = i;
    }

    public int getImg_height() {
        return this.img_height;
    }

    public void setImg_height(int i) {
        this.img_height = i;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long j) {
        this.end = j;
    }

    public int getStay() {
        return this.stay;
    }

    public void setStay(int i) {
        this.stay = i;
    }

    public int getLink_type() {
        return this.link_type;
    }

    public void setLink_type(int i) {
        this.link_type = i;
    }

    public String getLink_param() {
        return this.link_param;
    }

    public void setLink_param(String str) {
        this.link_param = str;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public boolean isRepeat() {
        return this.repeat;
    }

    public void setRepeat(boolean z) {
        this.repeat = z;
    }
}
