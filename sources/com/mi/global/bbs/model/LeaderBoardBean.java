package com.mi.global.bbs.model;

import java.util.List;

public class LeaderBoardBean extends BaseResult {
    public LeaderBoard data;

    public static class LeaderBoard {
        public int consecutivedays;
        public String myicon;
        public String myname;
        public int myrank;
        public List<RanklistBean> ranklist;

        public static class RanklistBean {
            public String consecutivedays;
            public int rank;
            public long uid;
            public String usericon;
            public String username;
        }
    }
}
