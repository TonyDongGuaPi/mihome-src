package com.mi.global.bbs.model;

public class HomeLoadMore {
    public static final int STATE_FAILED = 2;
    public static final int STATE_LOADING = 1;
    private int state;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }
}
