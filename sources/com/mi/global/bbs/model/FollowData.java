package com.mi.global.bbs.model;

import java.util.List;

public class FollowData {
    public static final int TYPE_BIG_PIC = 153;
    public static final int TYPE_BIRTHDAY = 18;
    public static final int TYPE_DEASE = 12;
    public static final int TYPE_DEFAULT = 999;
    public static final int TYPE_EMPTY = 91;
    public static final int TYPE_FOLLOW_OTHER_PEOPLE = 7;
    public static final int TYPE_JOIN_ACTIVITY = 17;
    public static final int TYPE_LAUNCH_ACTIVITY = 16;
    public static final int TYPE_LINK = 193;
    public static final int TYPE_MULTI_PIC = 155;
    public static final int TYPE_NO_PIC = 151;
    public static final int TYPE_ONE_PIC = 152;
    public static final int TYPE_PHOTOGRAPHY = 15;
    public static final int TYPE_POST_INTO_COLUMN = 8;
    public static final int TYPE_POST_THREAD = 2;
    public static final int TYPE_RECOMMEND = 9;
    public static final int TYPE_RECOMMEND_EMPTY = 92;
    public static final int TYPE_SHORT_CONTENT = 19;
    public static final int TYPE_SHORT_CONTENT_ONE_PIG = 190;
    public static final int TYPE_SHORT_CONTENT_THREE_PIG = 192;
    public static final int TYPE_SHORT_CONTENT_TWO_PIG = 191;
    public static final int TYPE_SIGN_ON = 14;
    public static final int TYPE_TOP_SHARE = 200;
    public static final int TYPE_TWO_PIC = 154;
    public static final int TYPE_VOTE = 4;
    FollowingFeedModel followingFeedModel;
    FollowingUserDataModel followingUserDataModel;
    private List<FollowingUserDataModel> followingUserDataModels;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public FollowingFeedModel getFollowingFeedModel() {
        return this.followingFeedModel;
    }

    public void setFollowingFeedModel(FollowingFeedModel followingFeedModel2) {
        this.followingFeedModel = followingFeedModel2;
        setType(followingFeedModel2.Type);
    }

    public List<FollowingUserDataModel> getFollowingUserDataModels() {
        return this.followingUserDataModels;
    }

    public void setFollowingUserDataModels(List<FollowingUserDataModel> list) {
        setType(9);
        this.followingUserDataModels = list;
    }

    public FollowingUserDataModel getFollowingUserDataModel() {
        return this.followingUserDataModel;
    }

    public void setFollowingUserDataModels(FollowingUserDataModel followingUserDataModel2) {
        setType(92);
        this.followingUserDataModel = followingUserDataModel2;
    }
}
