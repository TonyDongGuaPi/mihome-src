package com.mi.global.bbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.LeaderBoardBean;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.InfiniteScrollListener;
import com.mi.global.bbs.view.CircleImageView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RankingAdapter extends BaseLoadMoreAdapter {
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 2;
    private LeaderBoardBean.LeaderBoard mLeaderBoard;
    private List<LeaderBoardBean.LeaderBoard.RanklistBean> ranklist = new ArrayList();

    public int getNormalViewType(int i) {
        return i == 0 ? 1 : 2;
    }

    public class LeaderBoardHeader_ViewBinding implements Unbinder {
        private LeaderBoardHeader target;

        @UiThread
        public LeaderBoardHeader_ViewBinding(LeaderBoardHeader leaderBoardHeader, View view) {
            this.target = leaderBoardHeader;
            leaderBoardHeader.mMonthText = (TextView) Utils.findRequiredViewAsType(view, R.id.month_text, "field 'mMonthText'", TextView.class);
            leaderBoardHeader.mMyIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.my_icon, "field 'mMyIcon'", CircleImageView.class);
            leaderBoardHeader.mMyName = (TextView) Utils.findRequiredViewAsType(view, R.id.my_name, "field 'mMyName'", TextView.class);
            leaderBoardHeader.mMyRanking = (TextView) Utils.findRequiredViewAsType(view, R.id.my_ranking, "field 'mMyRanking'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            LeaderBoardHeader leaderBoardHeader = this.target;
            if (leaderBoardHeader != null) {
                this.target = null;
                leaderBoardHeader.mMonthText = null;
                leaderBoardHeader.mMyIcon = null;
                leaderBoardHeader.mMyName = null;
                leaderBoardHeader.mMyRanking = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class LeaderBoardItem_ViewBinding implements Unbinder {
        private LeaderBoardItem target;

        @UiThread
        public LeaderBoardItem_ViewBinding(LeaderBoardItem leaderBoardItem, View view) {
            this.target = leaderBoardItem;
            leaderBoardItem.mUserIcon = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.user_icon, "field 'mUserIcon'", CircleImageView.class);
            leaderBoardItem.mUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.user_name, "field 'mUserName'", TextView.class);
            leaderBoardItem.mUserContinuous = (TextView) Utils.findRequiredViewAsType(view, R.id.user_continuous, "field 'mUserContinuous'", TextView.class);
            leaderBoardItem.mUserRankingIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.user_ranking_icon, "field 'mUserRankingIcon'", ImageView.class);
            leaderBoardItem.mUserRankingText = (TextView) Utils.findRequiredViewAsType(view, R.id.user_ranking_text, "field 'mUserRankingText'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            LeaderBoardItem leaderBoardItem = this.target;
            if (leaderBoardItem != null) {
                this.target = null;
                leaderBoardItem.mUserIcon = null;
                leaderBoardItem.mUserName = null;
                leaderBoardItem.mUserContinuous = null;
                leaderBoardItem.mUserRankingIcon = null;
                leaderBoardItem.mUserRankingText = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public RankingAdapter(Activity activity, InfiniteScrollListener.DataLoading dataLoading) {
        super(activity, dataLoading);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new LeaderBoardHeader(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bbs_leaderboard_top_item, viewGroup, false));
            case 2:
                return new LeaderBoardItem(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bbs_leaderboard_item, viewGroup, false));
            default:
                return super.onCreateViewHolder(viewGroup, i);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 1:
                bindHeader((LeaderBoardHeader) viewHolder);
                return;
            case 2:
                bindItem((LeaderBoardItem) viewHolder, i - 1);
                return;
            default:
                super.onBindViewHolder(viewHolder, i);
                return;
        }
    }

    private void bindItem(LeaderBoardItem leaderBoardItem, int i) {
        final LeaderBoardBean.LeaderBoard.RanklistBean ranklistBean = this.ranklist.get(i);
        ImageLoader.showHeadIcon(leaderBoardItem.mUserIcon, ranklistBean.usericon);
        leaderBoardItem.mUserName.setText(ranklistBean.username);
        if (i == 0) {
            leaderBoardItem.mUserRankingIcon.setVisibility(0);
            leaderBoardItem.mUserRankingText.setVisibility(4);
            leaderBoardItem.mUserRankingIcon.setImageResource(R.drawable.bbs_sign_first);
        } else if (i == 1) {
            leaderBoardItem.mUserRankingIcon.setVisibility(0);
            leaderBoardItem.mUserRankingText.setVisibility(4);
            leaderBoardItem.mUserRankingIcon.setImageResource(R.drawable.bbs_sign_second);
        } else if (i == 2) {
            leaderBoardItem.mUserRankingIcon.setVisibility(0);
            leaderBoardItem.mUserRankingText.setVisibility(4);
            leaderBoardItem.mUserRankingIcon.setImageResource(R.drawable.bbs_sign_third);
        } else {
            leaderBoardItem.mUserRankingIcon.setVisibility(4);
            leaderBoardItem.mUserRankingText.setVisibility(0);
            leaderBoardItem.mUserRankingText.setText(String.valueOf(ranklistBean.rank));
        }
        leaderBoardItem.mUserIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context = view.getContext();
                UserCenterActivity.jump(context, ranklistBean.uid + "");
            }
        });
        leaderBoardItem.mUserName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context = view.getContext();
                UserCenterActivity.jump(context, ranklistBean.uid + "");
            }
        });
    }

    private void bindHeader(LeaderBoardHeader leaderBoardHeader) {
        ImageLoader.showHeadIcon(leaderBoardHeader.mMyIcon, this.mLeaderBoard.myicon);
        leaderBoardHeader.mMyName.setText(this.mLeaderBoard.myname);
        leaderBoardHeader.mMyRanking.setText(String.valueOf(this.mLeaderBoard.myrank));
        leaderBoardHeader.mMonthText.setText(String.format(leaderBoardHeader.mMonthText.getContext().getString(R.string.month_checked_in_leaderboard), new Object[]{leaderBoardHeader.mMonthText.getContext().getResources().getStringArray(R.array.bbs_months)[Calendar.getInstance().get(2)]}));
    }

    public int getDataItemCount() {
        if (this.mLeaderBoard != null) {
            return this.ranklist.size() + 1;
        }
        return 0;
    }

    public void setLeaderBoard(LeaderBoardBean.LeaderBoard leaderBoard) {
        this.mLeaderBoard = leaderBoard;
        this.ranklist.clear();
        this.ranklist.addAll(leaderBoard.ranklist);
        notifyDataSetChanged();
    }

    public void setRankingList(List<LeaderBoardBean.LeaderBoard.RanklistBean> list) {
        this.ranklist.addAll(list);
        notifyDataSetChanged();
    }

    public class LeaderBoardHeader extends RecyclerView.ViewHolder {
        @BindView(2131493649)
        TextView mMonthText;
        @BindView(2131493710)
        CircleImageView mMyIcon;
        @BindView(2131493711)
        TextView mMyName;
        @BindView(2131493712)
        TextView mMyRanking;

        public LeaderBoardHeader(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class LeaderBoardItem extends RecyclerView.ViewHolder {
        @BindView(2131494210)
        TextView mUserContinuous;
        @BindView(2131494213)
        CircleImageView mUserIcon;
        @BindView(2131494225)
        TextView mUserName;
        @BindView(2131494238)
        ImageView mUserRankingIcon;
        @BindView(2131494239)
        TextView mUserRankingText;

        public LeaderBoardItem(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
