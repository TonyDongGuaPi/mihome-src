package com.mi.global.bbs.model;

import java.util.ArrayList;
import java.util.List;

public class HomeData {
    public static final int TYPE_AD_BANNER = 10;
    public static final int TYPE_ANNOUCE = 2;
    public static final int TYPE_FORUM_BIG_PIC = 14;
    public static final int TYPE_FORUM_DEBATE = 19;
    public static final int TYPE_FORUM_MULTI_PIC = 16;
    public static final int TYPE_FORUM_NO_PIC = 12;
    public static final int TYPE_FORUM_ONE_PIC = 13;
    public static final int TYPE_FORUM_TWO_PIC = 15;
    public static final int TYPE_FORUM_VOTE = 18;
    public static final int TYPE_FORYOU_EMPTY = 21;
    public static final int TYPE_FORYOU_HEADER = 23;
    public static final int TYPE_FORYOU_RECOMMEND = 20;
    public static final int TYPE_HOME_RD_COLUMN = 24;
    public static final int TYPE_LOAD_MORE = 7;
    public static final int TYPE_RECOMMEND = 4;
    private ArrayList<ForYouRecoomend> forYouRecoomends;
    private HomeBanner homeAdBanner;
    private HomeColumnList homeColumnList;
    private HomeForumBean homeForumBean;
    private List<HomePostBean> homeHots;
    private HomeNotify homeNotify;
    private HomeLoadMore loadMore;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public HomeBanner getHomeAdBanner() {
        return this.homeAdBanner;
    }

    public void setHomeAdBanner(HomeBanner homeBanner) {
        this.homeAdBanner = homeBanner;
    }

    public HomeNotify getHomeNotify() {
        return this.homeNotify;
    }

    public void setHomeNotify(HomeNotify homeNotify2) {
        this.homeNotify = homeNotify2;
    }

    public List<HomePostBean> getHomeHots() {
        return this.homeHots;
    }

    public void setHomeHots(List<HomePostBean> list) {
        this.homeHots = list;
    }

    public HomeForumBean getHomeForumBean() {
        return this.homeForumBean;
    }

    public void setHomeForumBean(HomeForumBean homeForumBean2) {
        this.homeForumBean = homeForumBean2;
        setType(getForumType());
    }

    public HomeLoadMore getLoadMore() {
        return this.loadMore;
    }

    public void setLoadMore(HomeLoadMore homeLoadMore) {
        this.loadMore = homeLoadMore;
    }

    public ArrayList<ForYouRecoomend> getForYouRecoomends() {
        return this.forYouRecoomends;
    }

    public void setForYouRecoomends(ArrayList<ForYouRecoomend> arrayList) {
        setType(20);
        this.forYouRecoomends = arrayList;
    }

    public HomeColumnList getHomeColumnList() {
        return this.homeColumnList;
    }

    public void setHomeColumnList(HomeColumnList homeColumnList2) {
        this.homeColumnList = homeColumnList2;
    }

    private int getForumType() {
        int i = this.homeForumBean.special;
        if (i == 1) {
            return 18;
        }
        if (i == 5) {
            return 19;
        }
        if (i != 10) {
            List<String> list = this.homeForumBean.showpiclist;
            if (list == null || list.size() <= 0) {
                return 12;
            }
            return 13;
        }
        List<String> list2 = this.homeForumBean.showpiclist;
        if (list2 == null) {
            return 12;
        }
        switch (list2.size()) {
            case 0:
                return 12;
            case 1:
                return 14;
            case 2:
                return 15;
            default:
                return 16;
        }
    }
}
